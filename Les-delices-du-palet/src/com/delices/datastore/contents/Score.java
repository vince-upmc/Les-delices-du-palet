package com.delices.datastore.contents;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

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

	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();

		obj.put("home-score", homeScore);
		obj.put("away-score", awayScore);

		return obj;
	}

}
