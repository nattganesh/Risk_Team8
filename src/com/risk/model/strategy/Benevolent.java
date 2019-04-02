package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.map.Country;
import com.risk.model.player.Player;

public class Benevolent implements Strategy {
	@Override
	public boolean attack(Player p, boolean status) {
		status = true;
		return status;
	}

	@Override
	public void reinforce(Player p) {
		ArrayList<Country> weakestCountries = p.getWeakestCountries();
		int Armyinput = p.getReinforcementArmy();
		if (weakestCountries.size() == 1) {
			weakestCountries.get(0).setArmyCount(Armyinput);
			return;
		}
		int army = Armyinput;
		while (army > 0) {
			for (Country c : weakestCountries) {
				c.setArmyCount(1);
				army--;
				if (army == 0) {
					return;
				}
			}
		}
	}

	@Override
	public void fortify(Player p) {
		ArrayList<Country> weakestCountries = p.getWeakestCountries();
		int Armyinput=0;
		ArrayList<Country> fortify = new ArrayList<Country>();
		if(p.isAnyCountriesConnected()) {
			for(Country c: weakestCountries) {
				ArrayList<Country> connected = new ArrayList<Country>();
				connected = p.getCountriesArrivedbyPath(c, c, connected);
				if(!connected.isEmpty()) {
					for(Country connect : connected) {
						if(connect.getArmyCount()>Armyinput) {
							Armyinput = connect.getArmyCount();
							fortify.clear();
							fortify.add(c);
							fortify.add(connect);
						}
					}
				}
				
			}
			fortify.get(0).setArmyCount(Armyinput-1);
			fortify.get(1).reduceArmyCount(Armyinput-1);
		}
		
	}
}