package com.delices.services;

import java.io.IOException;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Pari;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class TestPari extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// TODO Verifier que le match n'est pas fini
		
		// c488998b-bc50-4d70-8f14-d0b5b1e7dc2a

		//Vérification de la présence du type de pari
		String bet=req.getParameter("betkind");
		if (bet == null) {
			resp.getWriter().println("Aucun type de pari selectionné");
			return;
		}
		int betkind = Integer.parseInt(bet);
		if (betkind <0 || betkind > 8) {
			resp.getWriter().println("Aucun type de pari selectionné");
			return;
		}

		//Vérification de la présence du match id
		String matchid = req.getParameter("matchid");
		int mise = Integer.parseInt(req.getParameter("mise"));

		if (matchid == null) {
			resp.getWriter().println("je veux un match id");
			return;
		}

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		/*
		 * // TODO if (user == null) { us.createLoginURL(req.getRequestURI());
		 * return; }
		 */
		PersistenceManager pm = PMF.get().getPersistenceManager();

		// est-ce que ça fail?
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

		Match m = null;
		try {
			m = pm.getObjectById(Match.class, matchid);
		} catch (JDOObjectNotFoundException e) {
			resp.getWriter()
			.println(
					"Match non trouvé dans le datastore. Id à vérifier ou matchs à mettre à jour");
			return;
		}

		int credit = dbuser.getCredit();

		if(mise>credit || mise == 0){
			resp.getWriter()
			.println(
					"Crédit ou mise insuffisant");
			return;
		}
		else{
			dbuser.setCredit(credit-mise);
		}

		Pari p = new Pari(dbuser.getKey(), m.getKey(), mise, betkind);
		// Il faut rendre le pari persistant avant afin de générer l'id, sinon
		// nullpointer
		pm.makePersistent(p);
		dbuser.getParis().add(
				KeyFactory.createKey(Pari.class.getSimpleName(), p.getId()));
		pm.close();



		JSONObject json = new JSONObject();
		try {
			json.put("output", "ok");
			resp.getWriter().println(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}