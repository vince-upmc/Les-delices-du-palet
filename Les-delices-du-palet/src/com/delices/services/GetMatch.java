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
import com.delices.datastore.contents.Team;
import com.delices.utils.Logger;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class GetMatch extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String id = req.getParameter("match-id");
		if (id.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Match m = pm.getObjectById(Match.class, id);
			Team home = pm.getObjectById(Team.class, m.getHome());
			Team away = pm.getObjectById(Team.class, m.getAway());

			JSONObject obj = m.toJSON(home.toJSON(), away.toJSON());

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			// debug
			System.out.println(obj);
			resp.getWriter().print(obj);
			resp.flushBuffer();

		} catch (JDOObjectNotFoundException e) {
			Logger.writeLog("GetMatch : Impossible de trouver le match");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} catch (JSONException e) {
			Logger.writeLog("GetMatch : Génération du json echouée");
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
