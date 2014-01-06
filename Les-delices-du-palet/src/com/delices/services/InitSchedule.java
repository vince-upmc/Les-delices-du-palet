package com.delices.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.updaters.PartialSeasonScheduleUpdater;
import com.delices.datastore.updaters.UpdateFailureException;

@SuppressWarnings("serial")
public class InitSchedule extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String sched_id = req.getParameter("sched-id");
		int sched_int = 0;
		try {
			sched_int = Integer.parseInt(sched_id);
		} catch (NumberFormatException e) {
			return;
		}

		// TODO : Sécuriser l'appel (cron?)

		try {
			new PartialSeasonScheduleUpdater(sched_int).updateContent();
		} catch (UpdateFailureException e) {
			// todo : handle
		}
		
		resp.getWriter().println("success!");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
