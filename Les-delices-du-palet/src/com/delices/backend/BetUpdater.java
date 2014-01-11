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

		double modifier;
		
		int betkind = bet.getBetkind();
		//Choisir modifier en fonction de betkin
		switch(betkind){
		case (0) : modifier = 2;break;
		case (1) : modifier = 2;break;
		case (2) : modifier = 2;break;
		case (3) : modifier = 3;break;
		case (4) : modifier = 4;break;
		case (5) : modifier = 5;break;
		case (6) : modifier = 3;break;
		case (7) : modifier = 4;break;
		case (8) : modifier = 5;break;
		default: modifier = 2;break;
		}

		boolean betSuccessful = bet.isBetSuccessful();

		if (betSuccessful) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			User us = pm.getObjectById(User.class, bet.getUser());
			us.setCredit((int) (bet.getMise() * modifier));
			// Envoyer un mail?
		}

		// Else => la somme est déjà déduite donc, on laisse tel quel
	}
}