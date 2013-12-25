package com.delices.datastore.contents;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

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

	@Persistent
	private BoxScore boxScore;

	// Add boxscore?

	public Match(Key key, Key home, Key visitor, String status, String title,
			Date startDate) {
		this.key = key;
		this.home = home;
		this.away = visitor;
		this.status = status;
		this.startingTime = startDate;
		this.boxScore = new BoxScore();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public BoxScore getBoxScore() {
		return boxScore;
	}

	@Override
	public String toString() {
		return "Match [key=" + key + ", home=" + home + ", away=" + away
				+ ", status=" + status + ", title=" + title + ", startingTime="
				+ startingTime + ", boxscore=" + boxScore + "]";
	}

}
