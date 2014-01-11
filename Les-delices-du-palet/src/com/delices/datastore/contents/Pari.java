package com.delices.datastore.contents;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.delices.datastore.PMF;
import com.delices.utils.Logger;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Pari {

	public enum Estatus {
		Waiting, // Le match n'a pas commencé
		Started, // Le match est en cours
		Done; // Le match est terminé
	}

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

	// betkind 0 = Victoire home, 1 = Victoire away, 2 = nul, 3 = home ecart n°1
	@Persistent
	private int betkind;

	@Persistent
	private Estatus status;

	@Persistent
	private boolean payed;

	public Pari(Key user, Key match, int mise, int betkind) {
		this.user = user;
		this.match = match;
		this.mise = mise;
		this.status = Estatus.Waiting;
		this.date = Calendar.getInstance().getTime();
		this.setBetkind(betkind);
		this.payed = false;
	}

	public int getMise() {
		return mise;
	}

	public void setMise(int mise) {
		if (status != Estatus.Waiting) {
			Logger.writeLog("Attention, la mise est modifiée mais le match est déjà commencé ou terminé");
		}
		this.mise = mise;
	}

	public Estatus getStatus() {
		return status;
	}

	public void setStatus(Estatus status) {
		this.status = status;
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

	public int getBetkind() {
		return betkind;
	}

	public void setBetkind(int betkind) {
		this.betkind = betkind;
	}

	public boolean isBetSuccessful() {
		//Récupérer le match
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Match m = pm.getObjectById(Match.class, match.getId());
		
		//Récupérer le score du match
		Score s = m.getBoxScore();
		int homescore = s.getHomeScore();
		int awayscore = s.getAwayScore();
		
		boolean isSuccessfull = false;
		//Comparer le pari avec le score et dire si on est successfull
		switch(betkind){
		case (0) : isSuccessfull = (homescore > awayscore);break;//home win
		case (1) : isSuccessfull = (homescore < awayscore);break;//away win
		case (2) : isSuccessfull = (homescore == awayscore);break;//match nul
		case (3) : isSuccessfull = (homescore > awayscore && homescore < awayscore+4);break;//home win ecart 1-3
		case (4) : isSuccessfull = (homescore > awayscore+3 && homescore < awayscore+8);break;//home win ecart 4-7
		case (5) : isSuccessfull = (homescore > awayscore+7);break;//home win ecart 8+
		case (6) : isSuccessfull = (awayscore > homescore && awayscore < homescore+4);break;//away win ecart 1-3
		case (7) : isSuccessfull = (awayscore > homescore+3 && awayscore < homescore+8);break;//away win ecart 4-7
		case (8) : isSuccessfull = (awayscore > homescore+7);break;//away win ecart 8+
		default: isSuccessfull = false;break;
		}
		
		return isSuccessfull;
	}

}