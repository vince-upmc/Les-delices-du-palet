package com.delices.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Team;
import com.delices.datastore.updaters.GameUpdater;
import com.delices.datastore.updaters.SeasonScheduleUpdater;
import com.delices.datastore.updaters.StandingsUpdater;
import com.delices.datastore.updaters.TeamUpdater;
import com.delices.datastore.updaters.UpdateFailureException;
/**
 * mise à jour du calendrier
 * @author yoyo
 *
 */
@SuppressWarnings("serial")
public class TestDisplaySchedule extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// test match id : c488998b-bc50-4d70-8f14-d0b5b1e7dc2a

		boolean update = true;
		if (update) {
			try {
				new TeamUpdater().updateContent();
				resp.getWriter().println("Team update : done");
				new StandingsUpdater().updateContent();
				resp.getWriter().println("Standings update : done");
				new SeasonScheduleUpdater().updateContent();
				resp.getWriter().println("Schedule update : done");
			} catch (UpdateFailureException e) {
				resp.getWriter().println("Erreur : " + e.getMessage());
			}
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0);
		Date d1 = today.getTime();
		Calendar tomorrow = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Date d2 = tomorrow.getTime();
		Query q = pm.newQuery(Match.class);
		q.setOrdering("startingTime asc");
		q.setFilter("startingTime > d1 && startingTime < d2");
		q.declareParameters("java.util.Date d1, java.util.Date d2");

		resp.getWriter().println("Matchs du jour : ");
		for (Match m : (List<Match>) q.execute(d1, d2)) {
			String home = pm.getObjectById(Team.class, m.getHome()).getName();
			String away = pm.getObjectById(Team.class, m.getAway()).getName();

			resp.getWriter().println(
					"Match : " + home + " vs " + away + " at "
							+ m.getStartingTime().toString());
		}
		q.closeAll();

		resp.getWriter().println("Matchs de la saison : ");
		q = pm.newQuery(Match.class);
		q.setOrdering("startingTime asc");
		for (Match m : (List<Match>) q.execute()) {
			String home = pm.getObjectById(Team.class, m.getHome()).getName();
			String away = pm.getObjectById(Team.class, m.getAway()).getName();

			resp.getWriter().println(
					"Match : " + home + " vs " + away + " at "
							+ m.getStartingTime().toString() + " id : "
							+ m.getId());
		}
		q.closeAll();

		Match m = pm.getObjectById(Match.class,
				"c488998b-bc50-4d70-8f14-d0b5b1e7dc2a");
		try {
			new GameUpdater(m).updateContent();
		} catch (UpdateFailureException e) {
			System.out.println("problayme");
		}
		resp.getWriter().println(
				"score : Flyers = " + m.getBoxScore().getHomeScore()
						+ " Capitals = " + m.getBoxScore().getAwayScore());
		resp.getWriter().println("expected : 0, 7");

		pm.close();

		resp.getWriter().println("done");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
