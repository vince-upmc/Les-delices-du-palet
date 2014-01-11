package com.delices.datastore.updaters;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.delices.datastore.Infos;
import com.delices.datastore.PMF;
import com.delices.datastore.contents.Team;
import com.delices.datastore.jaxb.hierarchy.ConferenceType;
import com.delices.datastore.jaxb.hierarchy.DivisionType;
import com.delices.datastore.jaxb.hierarchy.LeagueType;
import com.delices.datastore.jaxb.hierarchy.TeamType;
import com.delices.utils.Logger;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TeamUpdater extends DataUpdater<LeagueType> {

	@Override
	public void updateContent() throws UpdateFailureException {
		URL s = null;
		try {
			s = UrlFactory.createLeagueHierarchyRequest(Infos.NHL_API_KEY);
		} catch (MalformedURLException e) {
			String msg = "URL mal formée : " + e.getMessage();
			Logger.writeLog(msg);
			throw new UpdateFailureException(msg);
		}
		try (InputStream in = RequestMaker.makeRequest(s)) {
		//try (InputStream in = new FileInputStream("tmp/hierarchy.xml")) {
			if (in != null) {
				LeagueType league = super.unmarshallContent(in, "hierarchy");

				List<Team> l = new ArrayList<>();
				for (ConferenceType c : league.getConference()) {
					String confName = c.getName();
					for (DivisionType d : c.getDivision()) {
						String divName = d.getName();
						for (TeamType t : d.getTeam()) {
							Key k = KeyFactory.createKey(
									Team.class.getSimpleName(), t.getId());
							l.add(new Team(k, t.getName(), t.getAlias(), t
									.getMarket(), divName, confName));
						}
					}
				}
				PersistenceManager pm = null;
				try {
					pm = PMF.get().getPersistenceManager();
					pm.makePersistentAll(l);
				} finally {
					pm.close();
				}

				Logger.writeLog("Equipes mise-à-jour avec succès");
			}
		} catch (Exception e) {
			String msg = "Impossible de mettre à jour le contenu de l'équipe : "
					+ e.getMessage();
			Logger.writeLog(msg);
			throw new UpdateFailureException(msg);
		}

	}
}
