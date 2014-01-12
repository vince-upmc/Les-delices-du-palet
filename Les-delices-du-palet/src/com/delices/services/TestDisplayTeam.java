package com.delices.services;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Team;
import com.delices.datastore.updaters.StandingsUpdater;
import com.delices.datastore.updaters.TeamUpdater;
import com.delices.datastore.updaters.UpdateFailureException;
/**
 * mise à jour des équipes
 */
@SuppressWarnings("serial")
public class TestDisplayTeam extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		boolean update = false;
		if (update) {
			try {
				new TeamUpdater().updateContent();
				resp.getWriter().println("Team update : done");
				new StandingsUpdater().updateContent();
				resp.getWriter().println("Standings update : done");
			} catch (UpdateFailureException e) {
				resp.getWriter().println("Erreur : " + e.getMessage());
			}
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();


		Query q = pm.newQuery(Team.class);
		for (Team t : (List<Team>) q.execute()) {
			resp.getWriter().println(t);
		}

		pm.close();

		resp.getWriter().println("done");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
