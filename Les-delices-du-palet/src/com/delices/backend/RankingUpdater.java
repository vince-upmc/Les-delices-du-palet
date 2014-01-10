package com.delices.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delices.datastore.updaters.StandingsUpdater;
import com.delices.datastore.updaters.UpdateFailureException;
import com.delices.utils.Logger;

@SuppressWarnings("serial")
public class RankingUpdater extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			new StandingsUpdater().updateContent();
		} catch (UpdateFailureException e) {
			Logger.writeLog(this, "Rankings update failed");
		}

	}
}
