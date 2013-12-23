package com.delices.datastore.contents;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Team {

	@PrimaryKey
	@Persistent
	private Key key;

	@Persistent
	private String name;
	@Persistent
	private String alias;
	@Persistent
	private String market;

	// private Venue venue; => localisation
	@Persistent
	private String division;
	@Persistent
	private String conference;

	// Rankings
	@Persistent
	private int games_played;
	@Persistent
	private int wins;
	@Persistent
	private int losses;
	@Persistent
	private int points;
	@Persistent
	private double win_pct;

	@Persistent
	private int regulation_wins;
	@Persistent
	private int shoutout_wins;
	@Persistent
	private int shoutout_losses;


	@Persistent
	private int overtime_losses;
	@Persistent
	private int goals_for;
	@Persistent
	private int goals_against;
	@Persistent
	private int goals_diff;

	public Team(Key key, String name, String alias, String market,
			String division, String conference) {
		this.key = key;
		this.name = name;
		this.alias = alias;
		this.market = market;
		this.division = division;
		this.conference = conference;
	}

	public int getGames_played() {
		return games_played;
	}

	public void setGames_played(int games_played) {
		this.games_played = games_played;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getWin_pct() {
		return win_pct;
	}

	public void setWin_pct(double win_pct) {
		this.win_pct = win_pct;
	}

	public int getRegulation_wins() {
		return regulation_wins;
	}

	public void setRegulation_wins(int regulation_wins) {
		this.regulation_wins = regulation_wins;
	}

	public int getShoutout_wins() {
		return shoutout_wins;
	}

	public void setShoutout_wins(int shoutout_wins) {
		this.shoutout_wins = shoutout_wins;
	}

	public int getShoutout_losses() {
		return shoutout_losses;
	}

	public void setShoutout_losses(int shoutout_losses) {
		this.shoutout_losses = shoutout_losses;
	}

	public int getOvertime_losses() {
		return overtime_losses;
	}

	public void setOvertime_losses(int overtime_losses) {
		this.overtime_losses = overtime_losses;
	}

	public int getGoals_for() {
		return goals_for;
	}

	public void setGoals_for(int goals_for) {
		this.goals_for = goals_for;
	}

	public int getGoals_against() {
		return goals_against;
	}

	public void setGoals_against(int goals_against) {
		this.goals_against = goals_against;
	}

	public int getGoals_diff() {
		return goals_diff;
	}

	public void setGoals_diff(int goals_diff) {
		this.goals_diff = goals_diff;
	}
	
	public Key getId() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getMarket() {
		return market;
	}

	public String getDivision() {
		return division;
	}

	public String getConference() {
		return conference;
	}

}
