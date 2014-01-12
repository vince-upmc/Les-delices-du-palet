package com.delices.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.jdo.PersistenceManager;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Pari;
import com.delices.datastore.contents.Team;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * classe contenant divers outils
 * @author yoyo
 *
 */
public class Tools {
	
	/**
	 * retourne un json contenant un champ erreur avec un message
	 * @param s
	 * @return
	 */
	public static JSONObject erreur(String s) {
		JSONObject json = new JSONObject();
		try {
			json.put("error", s);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	final static DateFormat df = new SimpleDateFormat("dd/MM HH:mm", Locale.FRANCE);

	/**
	 * retourne une chaine de charactères décrivant un pari
	 * @param b
	 * @return
	 */
	public static String betPrettyPrinter(Pari b) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Match m = pm.getObjectById(Match.class, b.getMatch().getName());
		Team home = pm.getObjectById(Team.class, m.getHome());
		Team away = pm.getObjectById(Team.class, m.getAway());

		String res = df.format(m.getStartingTime()) + " - Mise de : "
				+ b.getMise() + ", " + home.getName() + " vs " + away.getName();
		if (m.getStatus().equals("closed")) {
			res += " Score final : " + m.getBoxScore().getHomeScore() + " - "
					+ m.getBoxScore().getAwayScore();
		}

		return res
				+ " => "
				+ (b.isDone ? (b.isBetSuccessful() ? "Pari réussi"
						: "Pari échoué") : "Pari en cours");

	}
}
