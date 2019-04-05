package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.strategy.*;

public class Aggressive implements Strategy {
	@Override
	public void attack(Player p) {
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
					strongestCountry.reduceArmyCount(result[0]);
					defendingCountry.setArmyCount(result[0]);
					break;
				}
			} else {
				strongestCountry.reduceArmyCount(1);
			}
		}
		while(!neighboursOfStrongestCountry.isEmpty() && strongestCountry.getArmyCount() > 1)
		{
			attack(p);	
		}
	}

	@Override
	public void reinforce(Player p) {
		Country strongestCountry = p.getStrongestCountry();
		int Armyinput = p.getReinforcementArmy();
		strongestCountry.setArmyCount(Armyinput);
	}

	@Override
	public void fortify(Player p) {
		int tmp = 0;
		ArrayList<Country> maximumArmy = new ArrayList<Country>();
		if(p.isAnyCountriesConnected()) {
			for(Country c: p.getOccupiedCountries() ) {
				ArrayList<Country> connected = new ArrayList<Country>();
				connected = p.getCountriesArrivedbyPath(c, c, connected);
				if(!connected.isEmpty()) {
					for(Country connect: connected) {
						int armysum = c.getArmyCount()+connect.getArmyCount()-1;
						if(armysum>tmp) {
							tmp = armysum;
							maximumArmy.clear();
							maximumArmy.add(c);
							maximumArmy.add(connect);
						}
					}
				}
			}
			Country c1 = maximumArmy.get(0);
			Country c2 = maximumArmy.get(1);
			int armyinput = c1.getArmyCount()-1;
			c1.reduceArmyCount(armyinput);
			c2.setArmyCount(armyinput);
		}
	}

	public int getRandomNumber(int limit)
	{ 
		return (int) (Math.random() * limit);
	}
	 
	@Override
	public void setup(Player p) {
		Country c = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
		int armyimput = p.getStartingPoints();
		c.setArmyCount(armyimput);
		p.setStartingPoints(0);
	}

}
