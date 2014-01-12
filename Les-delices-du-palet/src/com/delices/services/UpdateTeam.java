package com.delices.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.updaters.StandingsUpdater;
import com.delices.datastore.updaters.TeamUpdater;
import com.delices.datastore.updaters.UpdateFailureException;
/**
 * mise à jour des équipes
 * @author yoyo
 *
 */
@SuppressWarnings("serial")
public class UpdateTeam extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			new TeamUpdater().updateContent();
			new StandingsUpdater().updateContent();
			resp.getWriter().println("done");
		} catch (UpdateFailureException e) {
			resp.getWriter().println("Erreur : "+e.getMessage());
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
