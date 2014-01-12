package com.delices.services;

import java.io.IOException;
import java.util.Calendar;
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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
/**
 * servlet de récupération des prochains matchs non joués
 * @author yoyo
 *
 */
@SuppressWarnings("serial")
public class NextMatchs extends HttpServlet {
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Match.class);
		q.setOrdering("startingTime asc");
		q.setRange(0, 10);
		q.setFilter("startingTime > today");
		q.declareParameters("java.util.Date today");

		JSONObject obj = new JSONObject();

		for (Match m : (List<Match>) q.execute(Calendar.getInstance().getTime())) {
			Team home = pm.getObjectById(Team.class, m.getHome());
			Team away = pm.getObjectById(Team.class, m.getAway());

			try {
				obj.accumulate("matchs", m.toJSON(home.toJSON(), away.toJSON()));
			} catch (JSONException e) {
			}

		}
		q.closeAll();

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		resp.getWriter().print(obj);
		resp.flushBuffer();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
