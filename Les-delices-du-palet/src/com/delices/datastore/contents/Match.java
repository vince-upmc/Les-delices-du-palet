package com.delices.datastore.contents;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.delices.datastore.PMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@PersistenceCapable
public class Match {

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
	
	public static List<Match> getNextMatches() {
		Calendar now = Calendar.getInstance();
		Date d1 = now.getTime();
		now.add(Calendar.DATE, 7);
		Date d2 = now.getTime();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Match.class);
		/*q.setFilter("startingTime.after(d1) && startingTime.before(d2)");*/
		q.setOrdering("startingTime asc");
		List<Match> matchs = (List<Match>) q.execute();
		return matchs;
		/*List<Match> res = new ArrayList<Match>();
		for (Match m : matchs) {
			if (m.startingTime.after(d1) && m.startingTime.before(d2)) {
				res.add(m);
			}
		}
		return res;*/
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
		if (boxScore != null)
			obj.put("boxScore", boxScore.toString());

		return obj;
	}

}
