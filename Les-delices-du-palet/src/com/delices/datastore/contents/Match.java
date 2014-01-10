package com.delices.datastore.contents;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@PersistenceCapable
public class Match implements Comparable<Match> {

	@PrimaryKey
	@Persistent
	private Key key;

	@Persistent
	private Key home;
	@Persistent
	private Key away;

	// created halftime closed postponed unnecessary time-tbd
	@Persistent
	private String status;
	@Persistent
	private String title;

	@Persistent
	private Date startingTime;

	@Persistent(dependent = "true")
	private Score boxScore;

	public Match(Key key, Key home, Key visitor, String status, String title,
			Date startDate) {
		this.key = key;
		this.home = home;
		this.away = visitor;
		this.status = status;
		this.startingTime = startDate;
		this.boxScore = new Score();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Key getKey() {
		return key;
	}

	public String getId() {
		return key.getName();
	}

	public Key getHome() {
		return home;
	}

	public Key getAway() {
		return away;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public String getTitle() {
		return title;
	}

	public Score getBoxScore() {
		return boxScore;
	}

	@Override
	public String toString() {
		return "Match [key=" + key + ", home=" + home + ", away=" + away
				+ ", status=" + status + ", title=" + title + ", startingTime="
				+ startingTime + ", boxscore=" + boxScore + "]";
	}

	public JSONObject toJSON(JSONObject homejson, JSONObject awayjson)
			throws JSONException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM hh:mm");

		JSONObject obj = new JSONObject();
		obj.put("id", getId());
		obj.put("home", homejson);
		obj.put("away", awayjson);
		obj.put("status", status);
		obj.put("title", title);
		obj.put("startingTime", sdf.format(startingTime).toString());
		obj.put("boxScore", boxScore.toJSON());

		return obj;
	}

	@Override
	public int compareTo(Match o) {
		return this.getStartingTime().compareTo(o.getStartingTime());
	}

}
