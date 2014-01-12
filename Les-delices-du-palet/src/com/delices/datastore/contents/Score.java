package com.delices.datastore.contents;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
/**
 * stockage des scores des matchs dans le datastore
 * @author yoyo
 *
 */
@PersistenceCapable
public class Score {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;

	// Stats
	@Persistent
	private int homeScore;

	@Persistent
	private int awayScore;

	// Qu'est-ce qu'on veut d'autre, genre stats, joueurs qui ont marqués?

	public Score() {
		this.homeScore = 0;
		this.awayScore = 0;
	}

	public Key getId() {
		return id;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	/**
	 * retourne un Json contenant le score de home et away
	 * @return
	 * @throws JSONException
	 */
	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();

		obj.put("homeScore", homeScore);
		obj.put("awayScore", awayScore);

		return obj;
	}

}
