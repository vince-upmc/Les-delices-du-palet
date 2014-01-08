package com.delices.datastore.contents;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

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

	@Persistent
	private Estatus status;

	public Pari(Key user, Key match, int mise) {
		this.user = user;
		this.match = match;
		this.mise = mise;
		this.status = Estatus.Waiting;
		this.date = Calendar.getInstance().getTime();
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

}
