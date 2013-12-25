package com.delices.datastore.contents;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User {

	public final static int START_CREDIT = 5;

	@PrimaryKey
	@Persistent
	private Key key;

	@Persistent
	private Set<Key> paris;

	@Persistent
	private int credit;

	public User(Key key) {
		this.key = key;
		this.paris = new HashSet<>(5);
		this.credit = START_CREDIT;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Key getKey() {
		return key;
	}

	public Set<Key> getParis() {
		return paris;
	}

	@Override
	public String toString() {
		return "User [key=" + key + ", paris=" + paris + ", credit=" + credit
				+ "]";
	}
	
}
