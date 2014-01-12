package com.delices.datastore.contents;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.delices.datastore.PMF;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Pari {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Key user;
	@Persistent
	private Key match;

	@Persistent
	private int mise;

	@Persistent
	private Date date;

	@Persistent
	public boolean isDone;

	public enum BetObjective {
		Home, Away, Tie;
	}

	public enum Difference {
		None, OneToThree, FourToSeven, EightOrMore;
	}

	@Persistent
	private BetObjective objective;

	@Persistent
	private Difference difference;

	public Pari(Key user, Key match, int mise, BetObjective objective,
			Difference diff) {
		this.user = user;
		this.match = match;
		this.mise = mise;
		this.date = Calendar.getInstance().getTime();
		this.objective = objective;
		this.difference = diff;
		this.isDone = false;
	}

	public int getMise() {
		return mise;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Key getUser() {
		return user;
	}

	public Key getMatch() {
		return match;
	}

	public boolean isDone() {
		return isDone;
	}
	
	public BetObjective getBetObjective () {
		return objective;
	}
	
	public String getDifferenceDescr () {
		switch (difference) {
		case None : return "-";
		case OneToThree : return "1-3";
		case FourToSeven : return "4-7";
		case EightOrMore : return "8+";
		default : return "Pari mystique";
		}
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isBetSuccessful() {
		// Récupérer le match
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Match m = pm.getObjectById(Match.class, match);
		return isBetSuccessful(m);
	}

	public boolean isBetSuccessful(Match m) {

		// Récupérer le score du match
		Score s = m.getBoxScore();
		int homescore = s.getHomeScore();
		int awayscore = s.getAwayScore();

		boolean isHome = true;
		switch (objective) {
		case Home:
			isHome = true;
			break;
		case Away:
			isHome = false;
			break;
		case Tie:
			return homescore == awayscore;
		}

		switch (difference) {
		case None:
			return (isHome && homescore > awayscore)
					|| (!isHome && awayscore > homescore);
		case OneToThree:
			return (isHome && homescore > awayscore && homescore < awayscore + 4)
					|| (!isHome && awayscore > homescore && awayscore < homescore + 4);
		case FourToSeven:
			return (isHome && homescore > awayscore && homescore < awayscore + 7)
					|| (!isHome && awayscore > homescore && awayscore < homescore + 7);
		case EightOrMore:
			return (isHome && homescore > awayscore + 8)
					|| (!isHome && awayscore > homescore + 8);
		}
		// Dead code
		return false;
	}

	public int getWinningSum() {
		switch (difference) {
		case None:
			return mise;
		case OneToThree:
			return mise * 2;
		case FourToSeven:
			return mise * 3;
		case EightOrMore:
			return mise * 4;
		}
		return mise;
	}

}