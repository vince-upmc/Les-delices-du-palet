package com.delices.backend;

/**
 * Classe du backend pour la mise à jour des scores
 * @author yoyo
 *
 */
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
import com.delices.datastore.contents.Pari;
import com.delices.datastore.updaters.GameUpdater;
import com.delices.datastore.updaters.UpdateFailureException;

@SuppressWarnings("serial")
public class ScoreUpdater extends HttpServlet {

	private static final Date SERVER_STARTUP_DATE = getServeurStartupDate();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, c.get(Calendar.HOUR) - 2);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 30);
		Date twoHoursAndAHalfAgo = c.getTime();

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Match.class);

		q.setOrdering("this.startingTime asc");
		q.setFilter("this.startingTime >= startupDate");
		// Impossible de faire 2 comparaisons sur un même champ..
		/*
		 * && " + "this.startingTime < twoHoursAndAHalfAgo");
		 */

		q.declareImports("import java.util.Date");
		q.declareParameters("Date startupDate");// , Date twoHoursAndHalfAgo");

		List<Match> l = (List<Match>) q.execute(SERVER_STARTUP_DATE);

		final int MAX_UPDATE = 5;
		int cpt = 0;
		for (Match m : l) {
			if (m.getStartingTime().before(twoHoursAndAHalfAgo)
					&& (!m.getStatus().equals("closed")) && cpt < MAX_UPDATE) {
				// Logger.writeLog(this, "Mise à jour du match : " + m.getId());
				// resp.getWriter().println("Mise à jour du match : " +
				// m.getId());
				try {
					// System.out.println("Mise à jour du match du : "
					// + m.getStartingTime());
					resp.getWriter().println(
							"Mise à jour du match du : " + m.getStartingTime());
					pm.currentTransaction().begin();
					new GameUpdater(m).updateContent();
					pm.currentTransaction().commit();
					
					if (!m.getStatus().equals("closed"))
						continue;

					Query q2 = pm.newQuery(Pari.class);
					q2.setFilter("match == key");
					q2.declareParameters("com.google.appengine.api.datastore.Key m");
					List<Pari> lp = (List<Pari>) q2.execute(m.getKey());
					for (Pari pari : lp) {
						pm.currentTransaction().begin();
						BetUpdater.updateBet(m, pari);
						pm.currentTransaction().commit();
					}

				} catch (UpdateFailureException e) {
					resp.getWriter().println(
							"Impossible de mettre à jour le match : "
									+ m.getId());
					// Logger.writeLog(
					// this,
					// "Impossible de mettre à jour le match : "
					// + m.getId());
					break;
				}
				cpt++;
				resp.getWriter().println(
						"Mise à jour " + cpt + " du match : " + m.getId()
								+ " accomplie");
				// Logger.writeLog(this,
				// "Mise à jour " + cpt + " du match : " + m.getId()
				// + " accomplie");
			} else {
				/*
				 * String msg = "Deuxième cond : date compar => " +
				 * (m.getStartingTime().compareTo(twoHoursAndAHalfAgo) < 0) +
				 * "status : " + m.getStatus();
				 */
				// resp.getWriter().println(msg);
				// Logger.writeLog(this, msg);

			}
			// resp.getWriter().println("Fin de la tâche cron");
			// Logger.writeLog(this, "Fin de la tâche cron");
		}
	}

	private static Date getServeurStartupDate() {
		Calendar c = Calendar.getInstance();
		// bonne année!
		c.set(2013, 12, 31, 23, 59, 59);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		return c.getTime();
	}

}
