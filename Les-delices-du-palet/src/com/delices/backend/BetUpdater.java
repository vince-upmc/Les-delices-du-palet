package com.delices.backend;

import javax.jdo.PersistenceManager;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Pari;
import com.delices.datastore.contents.Pari.Estatus;
import com.delices.datastore.contents.User;

public class BetUpdater {

	static void updateBet(Match m, Pari bet) {
		bet.setStatus(Estatus.Done);

		double modifier = 2.0;
		boolean betSuccessful = false;

		if (betSuccessful) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			User us = pm.getObjectById(User.class, bet.getUser());
			us.setCredit((int) (bet.getMise() * modifier));
			// Envoyer un mail?
		}

		// Else => la somme est déjà déduite donc, on laisse tel quel
	}
}
