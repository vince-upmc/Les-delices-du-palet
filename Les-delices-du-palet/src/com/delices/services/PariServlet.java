package com.delices.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Pari;
import com.delices.datastore.contents.Pari.BetObjective;
import com.delices.datastore.contents.Pari.Difference;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
/**
 * servlet pour la gestion des paris
 * vérification de la validité du pari
 * création du pari suivant la mise, le match et le type de pari
 * @author yoyo
 *
 */
@SuppressWarnings("serial")
public class PariServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		// Vérifications

		// Vérif user
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if (user == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Vous n'êtes pas connecté - Essayez de vous reconnecter");
			return;
		}

		// Vérif paramètres
		String teamid = req.getParameter("team-id");
		String matchid = req.getParameter("match-id");
		String difference = req.getParameter("difference");
		String mise_str = req.getParameter("mise");

		if (teamid == null || teamid.isEmpty() || matchid == null
				|| matchid.isEmpty() || mise_str == null || mise_str.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Paramètre manquant");
			return;
		}

		// Verif match
		Match m = null;
		try {
			m = pm.getObjectById(Match.class, matchid);
		} catch (JDOObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Le match est introuvable.");
			return;
		}

		if (m.getStatus().equals("closed")
				|| Calendar.getInstance().getTime()
						.compareTo(m.getStartingTime()) > 0) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Le match est en cours ou est déjà terminé");
			return;
		}

		// Vérif team
		boolean isHomeTeam = false;
		boolean isTie = false;
		if (teamid.equals("tie")) {
			isTie = true;
		} else if (teamid.equals(m.getHome().getName())) {
			isHomeTeam = true;
		} else if (teamid.equals(m.getAway().getName())) {
			isHomeTeam = false;
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"L'équipe sélectionnée ne joue pas dans ce match");
			return;
		}
		BetObjective objective = isHomeTeam ? BetObjective.Home
				: (isTie ? BetObjective.Tie : BetObjective.Away);

		// Vérif mise

		int mise = 0;
		try {
			mise = Integer.parseInt(mise_str);
			if (mise <= 0 || mise > 5) {
				throw new ParameterException();
			}
		} catch (NumberFormatException | ParameterException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Mauvaise mise de départ");
			return;
		}

		// Vérif diff
		// TODO ajouter les types correctement
		Difference diff = Difference.None;
		if (!isTie && difference != null && !difference.isEmpty()) {
			switch (difference) {
			case "none":
				diff = Difference.None;
				break;
			case "oneToThree":
				diff = Difference.OneToThree;
				break;
			case "fourToSeven":
				diff = Difference.FourToSeven;
				break;
			case "eightOrMore":
				diff = Difference.EightOrMore;
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Impossible de trouver la différence de buts");
				return;
			}
		} else if (!isTie){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Impossible de trouver la différence de buts");
			return;
		}

		// Récupération du dbuser. S'il n'existe pas on le crée
		com.delices.datastore.contents.User dbuser = null;
		try {
			dbuser = pm
					.getObjectById(com.delices.datastore.contents.User.class,
							user.getUserId());
		} catch (JDOObjectNotFoundException e) {
			Key k = KeyFactory.createKey(
					com.delices.datastore.contents.User.class.getSimpleName(),
					user.getUserId());
			dbuser = new com.delices.datastore.contents.User(k);
			pm.makePersistent(dbuser);
		}
		
		// Vérif pari
		Query q = pm.newQuery(Pari.class);
		q.setFilter("user == currentUser");
		q.declareParameters("com.delices.datastore.contents.User currentUser");
		@SuppressWarnings("unchecked")
		List<Pari> paris = (List<Pari>) q.execute(dbuser.getKey());
		for (Pari p : paris){
			if (p.getMatch().getName().equals(matchid)){
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Vous avez déjà un pari d'actif pour ce match");
				return;
			}
				
		}

		// Vérif crédit
		int credit = dbuser.getCredit();
		if (mise > credit) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Vous n'avez plus assez de crédit");
			return;
		} else {
			dbuser.setCredit(credit - mise);
		}

		Pari p = new Pari(dbuser.getKey(), m.getKey(), mise, objective, diff);
		// Il faut rendre le pari persistant avant afin de générer l'id,
		// sinon nullpointer
		pm.makePersistent(p);
		dbuser.getParis().add(
				KeyFactory.createKey(Pari.class.getSimpleName(), p.getId()));
		pm.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}