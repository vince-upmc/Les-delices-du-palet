package com.delices.datastore.updaters;

@SuppressWarnings("serial")
public class UpdateFailureException extends Exception {
	public UpdateFailureException(String msg) {
		super(msg);
	}
}
