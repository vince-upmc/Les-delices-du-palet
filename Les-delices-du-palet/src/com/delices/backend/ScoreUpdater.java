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

@SuppressWarnings("serial")
public class ScoreUpdater extends HttpServlet {

	private static final Date SERVER_STARTUP_DATE = getServeurStartupDate();
	private static final long AVG_GAME_TIME = 2 * 3600000 + 3600000 / 2;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Match> match_en_cours;

		/*
		 * Je veux récuperer tous les matchs qui ont eu lieu depuis la
		 * mise-en-ligne du serveur
		 * 
		 * Cas :
		 * 
		 * - Le match récupéré est fini => ok
		 * 
		 * - Le match n'est pas fini => on le met dans une queue
		 */

		Date twoHoursAndHalfAgo = Calendar.getInstance().getTime();

		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, c.get(Calendar.HOUR) - 2);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 30);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Match.class);
		q.setOrdering("startingTime asc");
		q.setFilter("startingTime > SERVER_STARTUP_DATE && "
				+ "startingTime < twoHoursAndHalfAgo &&"
				+ "status != 'complete' && status != 'closed' &&"
				+ "status != 'cancelled' && status != 'unnecessary'");
		/*
                    <xs:enumeration value="created"/>
                    <xs:enumeration value="inprogress"/>
                    <xs:enumeration value="halftime"/>
                    <xs:enumeration value="complete"/>
                    <xs:enumeration value="closed"/>
                    <xs:enumeration value="cancelled"/>
                    <xs:enumeration value="postponed"/>
                    <xs:enumeration value="delayed"/>
                    <xs:enumeration value="unnecessary"/>
                    <xs:enumeration value="time-tbd"/>
                    */
		q.declareParameters("java.util.Date SERVER_STARTUP_DATE, java.util.Date twoHoursAndHalfAgo");

		//TODO
	}

	private static Date getServeurStartupDate() {
		Calendar c = Calendar.getInstance();
		// 28 décembre 2013
		c.set(2013, 12, 28);
		return c.getTime();
	}

}
