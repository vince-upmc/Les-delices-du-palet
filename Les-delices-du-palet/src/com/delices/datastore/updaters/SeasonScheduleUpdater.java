package com.delices.datastore.updaters;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.delices.datastore.Infos;
import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Team;
import com.delices.datastore.jaxb.schedule.GameType;
import com.delices.datastore.jaxb.schedule.LeagueType;
import com.delices.datastore.jaxb.schedule.LeagueType.SeasonSchedule;
import com.delices.datastore.jaxb.schedule.ScheduleType.Games;
import com.delices.utils.Logger;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SeasonScheduleUpdater extends DataUpdater<LeagueType> {

	@Override
	public void updateContent() throws UpdateFailureException {
		URL s = null;
		try {
			s = UrlFactory.createScheduleRequest(2013,
					UrlFactory.NhlSeason.REGULAR, Infos.TEST_NHL_API_KEY);
		} catch (MalformedURLException e) {
			String msg = "URL mal formée : " + e.getMessage();
			Logger.writeLog(msg);
			throw new UpdateFailureException(msg);
		}
		try (InputStream in = RequestMaker.makeRequest(s)) {
		//try (InputStream in = new FileInputStream("tmp/season_schedule.xml")) {
			if (in != null) {
				LeagueType league = super.unmarshallContent(in, "schedule");

				PersistenceManager pm = PMF.get().getPersistenceManager();

				SeasonSchedule ss = league.getSeasonSchedule();
				Games g = ss.getGames();

				List<Match> buffer = new ArrayList<>();
				int cache_limit = 100;
				for (GameType game : g.getGame()) {
					Key matchKey = KeyFactory.createKey(
							Match.class.getSimpleName(), game.getId());
					Key homeTeamKey = KeyFactory.createKey(
							Team.class.getSimpleName(), game.getHome().getId());
					Key awayTeamKey = KeyFactory.createKey(
							Team.class.getSimpleName(), game.getAway().getId());
					Date startingTime = game.getScheduled()
							.toGregorianCalendar().getTime();

					Match m = new Match(matchKey, homeTeamKey, awayTeamKey,
							game.getStatus(), game.getTitle(), startingTime);

					buffer.add(m);

					if (buffer.size() >= cache_limit) {
						pm.makePersistentAll(buffer);
						buffer.clear();
					}
				}

				pm.makePersistentAll(buffer);

				pm.close();

				Logger.writeLog("Match de la saison saisis avec succès");
			}
		} catch (Exception e) {
			String msg = "Impossible de rentrer les matchs de la saison : "
					+ e.getMessage();
			Logger.writeLog(msg);
			throw new UpdateFailureException(msg);
		}

	}
	/*
	 * private static List<Team> fetchTeams() { PersistenceManager pm =
	 * PMF.get().getPersistenceManager();
	 * 
	 * pm.currentTransaction().begin();
	 * 
	 * Query q = pm.newQuery(Team.class);
	 * 
	 * @SuppressWarnings("unchecked") List<Team> l = (List<Team>) q.execute();
	 * 
	 * pm.currentTransaction().commit(); return l; }
	 */
}
