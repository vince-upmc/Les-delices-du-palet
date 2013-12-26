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

@SuppressWarnings("serial")
public class TestPari extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// c488998b-bc50-4d70-8f14-d0b5b1e7dc2a

		String matchid = req.getParameter("matchid");
		int mise = Integer.parseInt(req.getParameter("mise"));

		if (matchid == null) {
			resp.getWriter().println("je veux un match id");
			return;
		}

		resp.getWriter().println(
				"Création d'un pari pour le match " + matchid
						+ " et une mise de : " + mise);

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
			resp.getWriter().println(
					"User non trouvé dans la base de donnée, création...");
			Key k = KeyFactory.createKey(
					com.delices.datastore.contents.User.class.getSimpleName(),
					user.getUserId());
			dbuser = new com.delices.datastore.contents.User(k);
			pm.makePersistent(dbuser);
		}

		resp.getWriter().println(dbuser);

		Match m = null;
		try {
			m = pm.getObjectById(Match.class, matchid);
		} catch (JDOObjectNotFoundException e) {
			resp.getWriter()
					.println(
							"Match non trouvé dans le datastore. Id à vérifier ou matchs à mettre à jour");
			return;
		}

		Pari p = new Pari(dbuser.getKey(), m.getKey(), mise);
		// Il faut rendre le pari persistant avant afin de générer l'id, sinon
		// nullpointer
		pm.makePersistent(p);
		dbuser.getParis().add(
				KeyFactory.createKey(Pari.class.getSimpleName(), p.getId()));
		pm.close();

		resp.getWriter().println("---");
		resp.getWriter().println("Pari créé : " + p);
		resp.getWriter().println(dbuser);

		resp.getWriter().println("done");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}