package com.delices.datastore.contents;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Score {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	// Stats
	@Persistent
	private int homeScore;

	@Persistent
	private int awayScore;

	// On veut autre chose, genre stats, player qui ont marqués?

	public Score() {
		this.homeScore = 0;
		this.awayScore = 0;
	}
	
	public Long getId() {
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

}
