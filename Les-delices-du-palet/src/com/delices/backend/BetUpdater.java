package com.delices.backend;

import javax.jdo.PersistenceManager;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Match;
import com.delices.datastore.contents.Pari;
import com.delices.datastore.contents.User;

/**
 * Classe du backend pour attribuer les gains des paris gagnés
 * @author yoyo
 *
 */

public class BetUpdater {
	
	/**
	 *
	 * Met à jour le crédit d'un joueur si son pari est gagné
	 * */
	
	static void updateBet(Match m, Pari bet) {

		bet.setDone(true);
		
		if (bet.isBetSuccessful()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			User us = pm.getObjectById(User.class, bet.getUser());
			us.setCredit(bet.getWinningSum() + bet.getMise());
			// Envoyer un mail?
		}

		// else => la somme est déjà déduite donc, on laisse tel quel
	}
}