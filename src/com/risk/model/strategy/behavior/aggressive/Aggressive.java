package com.risk.model.strategy.behavior.aggressive;

import java.util.ArrayList;

import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.strategy.*;

public class Aggressive implements Strategy {
	@Override
	public boolean attack(Player p, boolean status) {
		Country strongestCountry = p.getStrongestCountry();
		ArrayList<Country> neighboursOfStrongestCountry = strongestCountry.getConnectedEnemyArrayList();
		Country defendingCountry = neighboursOfStrongestCountry.get(0);
		int result[] = p.setRollLimit(strongestCountry, defendingCountry);
		int[] dattack = p.rollResult(result[0]);
		int[] ddefend = p.rollResult(result[1]);
		int rolltime = Integer.min(result[0], result[1]);
		for (int i = 0; i < rolltime; i++) {
			if (dattack[i] > ddefend[i]) {
				defendingCountry.reduceArmyCount(1);
				if (defendingCountry.getArmyCount() == 0) {
					defendingCountry.getRuler().removeCountry(defendingCountry);
					if (defendingCountry.getRuler().isPlayerLost()) {
						p.moveCards(defendingCountry.getRuler());
					}
					defendingCountry.setRuler(p);
					p.addCountry(defendingCountry);
					break;
				}
			} else {
				strongestCountry.reduceArmyCount(1);
			}
		}
		if(!neighboursOfStrongestCountry.isEmpty() && strongestCountry.getArmyCount() > 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean reinforce(Player p, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fortify(Player p, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}

}
