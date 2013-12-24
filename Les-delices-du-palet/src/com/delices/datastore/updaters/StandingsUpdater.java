package com.delices.datastore.updaters;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jdo.PersistenceManager;

import com.delices.datastore.Infos;
import com.delices.datastore.PMF;
import com.delices.datastore.contents.Team;
import com.delices.datastore.jaxb.standings.LeagueType;
import com.delices.datastore.jaxb.standings.LeagueType.Season.Conference;
import com.delices.datastore.jaxb.standings.LeagueType.Season.Conference.Division;
import com.delices.datastore.jaxb.standings.TeamRecordType;
import com.delices.utils.Logger;

public class StandingsUpdater extends DataUpdater<LeagueType> {

	@Override
	public void updateContent() throws UpdateFailureException {
		URL s = null;
		try {
			// 2013 ?
			s = UrlFactory.createStandingsRequest(2013,
					UrlFactory.NhlSeason.REGULAR, Infos.TEST_NHL_API_KEY);
		} catch (MalformedURLException e) {
			String msg = "URL mal formée : " + e.getMessage();
			Logger.writeLog(msg);
			throw new UpdateFailureException(msg);
		}
		//try (InputStream in = RequestMaker.makeRequest(s)) {
		try (InputStream in = new FileInputStream("tmp/standings.xml")) {
			if (in != null) {
				LeagueType league = super.unmarshallContent(in, "standings");

				PersistenceManager pm = PMF.get().getPersistenceManager();

				for (Conference conf : league.getSeason().getConference()) {
					for (Division div : conf.getDivision()) {
						for (TeamRecordType trt : div.getTeam()) {
							try {
								Team t = pm.getObjectById(Team.class,
										trt.getId());

								t.setGames_played(trt.getGamesPlayed()
										.intValue());
								t.setWins(trt.getWins().intValue());
								t.setLosses(trt.getLosses().intValue());
								t.setWin_pct(trt.getWinPct().doubleValue());
								t.setRegulation_wins(trt.getRegulationWins()
										.intValue());
								t.setShoutout_wins(trt.getShootoutWins()
										.intValue());
								t.setShoutout_losses(trt.getShootoutLosses()
										.intValue());
								t.setOvertime_losses(trt.getOvertimeLosses()
										.intValue());
								t.setGoals_for(trt.getGoalsFor().intValue());
								t.setGoals_against(trt.getGoalsAgainst()
										.intValue());
								t.setGoals_diff(trt.getGoalDiff().intValue());
							} catch (Exception e) {
								Logger.writeLog(e.getMessage());
							}
						}
					}
				}

				pm.close();

				Logger.writeLog("Standings mis-à-jour avec succès");
			}
		} catch (Exception e) {
			String msg = "Impossible de mettre à jour les standings : "
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
