package com.delices.backend;

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
import com.delices.datastore.updaters.GameUpdater;
import com.delices.datastore.updaters.UpdateFailureException;

@SuppressWarnings("serial")
public class ScoreUpdater extends HttpServlet {

	private static final Date SERVER_STARTUP_DATE = getServeurStartupDate();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Date twoHoursAndAHalfAgo = Calendar.getInstance().getTime();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, c.get(Calendar.HOUR) - 2);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 30);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Match.class);

		q.setOrdering("this.startingTime asc");
		q.setFilter("this.startingTime > startupDate");
		// Impossible de faire 2 comparaisons sur un même champ..
		// ça m'soule, on fait sans.
		/*
		 * && " + "this.startingTime < twoHoursAndAHalfAgo");
		 */

		q.declareImports("import java.util.Date");
		q.declareParameters("Date startupDate");// , Date twoHoursAndHalfAgo");

		for (Match m : (List<Match>) q.execute(SERVER_STARTUP_DATE)) {
			if (m.getStartingTime().compareTo(twoHoursAndAHalfAgo) < 0
					&& (!m.getStatus().equals("closed"))) {
				try {
					System.out.println("Mise à jour du match du : "
							+ m.getStartingTime());
					new GameUpdater(m).updateContent();
				} catch (UpdateFailureException e) {
				}
			}
		}
	}

	private static Date getServeurStartupDate() {
		Calendar c = Calendar.getInstance();
		// bonne année!
		c.set(2014, 1, 1, 0, 0, 0);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		return c.getTime();
	}

}
