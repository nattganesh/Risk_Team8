package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.map.Country;
import com.risk.model.player.Player;

public class Cheater implements Strategy{
	@Override
	public boolean attack(Player p, boolean status) {
		for (Country c : p.getOccupiedCountries())
        {
            for (Country cNeighbors : c.getConnectedEnemyArrayList())
            {
            	cNeighbors.getRuler().removeCountry(cNeighbors);
				if (cNeighbors.getRuler().isPlayerLost()) {
					p.moveCards(cNeighbors.getRuler());
				}
				cNeighbors.setRuler(p);
				p.addCountry(cNeighbors);
            }
        }
		status = true;
		return status;
	}

	@Override
	public void reinforce(Player p) {
		Country strongestCountry = p.getStrongestCountry();
		int Armyinput = p.getReinforcementArmy();
		strongestCountry.setArmyCount(Armyinput);
	}

	@Override
	public void fortify(Player p) {
		for (Country c : p.getOccupiedCountries())
        {
			if(!c.getConnectedEnemyArrayList().isEmpty()) {
				int army = c.getArmyCount();
				c.setArmyCount(army*2);
			}
        }
	}
}
