package com.delices.datastore.updaters;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.delices.datastore.Infos;
import com.delices.datastore.contents.Match;
import com.delices.datastore.jaxb.game.GameType;
import com.delices.datastore.jaxb.game.TeamType;
import com.delices.utils.Logger;

public class GameUpdater extends DataUpdater<GameType> {

	private Match match;

	public GameUpdater(Match match) {
		this.match = match;
	}

	@Override
	public void updateContent() throws UpdateFailureException {
		URL s = null;
		try {
			// boxscore vs summary vs playbyplay : à voir
			// boxscore : plus concis donc plus rapide
			// summary : infos supplémentaires mais plus lourd
			// playbyplay : y a des commentaires
			/*
			 * s = UrlFactory.createGameBoxscoreRequest(match.getId(),
			 * Infos.TEST_NHL_API_KEY);
			 */
			s = UrlFactory.createGameSummaryRequest(match.getId(),
					Infos.TEST_NHL_API_KEY);
		} catch (MalformedURLException e) {
			String msg = "URL mal formée : " + e.getMessage();
			Logger.writeLog(msg);
			throw new UpdateFailureException(msg);
		}
		// try (InputStream in = RequestMaker.makeRequest(s)) {
		try (InputStream in = new FileInputStream("tmp/summary.xml")) {
			if (in != null) {
				GameType game = super.unmarshallContent(in, "game");

				for (TeamType t : game.getTeam()) {
					if (t.getId().equals(match.getHome().getName())) {
						match.getBoxScore().setHomeScore(
								t.getPoints().intValue());
					} else if (t.getId().equals(match.getAway().getName())) {
						match.getBoxScore().setAwayScore(
								t.getPoints().intValue());
					} else {
						String msg = "Equipe non-matchée : " + t.getName()
								+ " : " + t.getId();
						Logger.writeLog(msg);
						throw new UpdateFailureException(msg);
					}
				}

				Logger.writeLog("Points mis-à-jour avec succès");
			}
		} catch (Exception e) {
			String msg = "Impossible de mettre à jour les points : "
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
