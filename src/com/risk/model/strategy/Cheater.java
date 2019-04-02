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
				c.reduceArmyCount(1);
				cNeighbors.setArmyCount(1);
            }
        }
		status = true;
		return status;
	}

	@Override
	public void reinforce(Player p) {
		for (Country c : p.getOccupiedCountries())
        {
			int army = c.getArmyCount();
			c.setArmyCount(army*2);
        }
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
	public int getRandomNumber(int limit)
	{
		return (int) (Math.random() * limit);
	}
	@Override
	public void setup(Player p) {
		int army = p.getStartingPoints();
		for(int i=0; i<army; i++) {
			Country random = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
	        random.setArmyCount(1);
		}
		p.setStartingPoints(0);	
	}
}
