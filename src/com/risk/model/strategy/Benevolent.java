package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.ActionModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Benevolent implements Strategy {
	
	@Override
	public void attack(Player p) {
		ActionModel.getActionModel().addAction("skips attack");
	}

	@Override
	public void reinforce(Player p) {
		ActionModel.getActionModel().addAction("Reinforcement Phase:");
		ArrayList<Country> weakestCountries = p.getWeakestCountries();
		int Armyinput = p.getReinforcementArmy();
		if (weakestCountries.size() == 1) {
			
			int initialArmy = weakestCountries.get(0).getArmyCount();
			weakestCountries.get(0).setArmyCount(Armyinput);
			ActionModel.getActionModel().addAction("added " + Armyinput + " to " + weakestCountries.get(0).getName() + "(" + initialArmy + ")");
			
			return;
		}
		int army = Armyinput;
		while (army > 0) {
			for (Country c : weakestCountries) {
				int initialArmy = c.getArmyCount();
				c.setArmyCount(1);
				ActionModel.getActionModel().addAction("added " + 1 + " to " + c.getName() + "(" + initialArmy + ")");
				army--;
				if (army == 0) {
					return;
				}
			}
		}
	}

	
	@Override
	public void fortify(Player p) {
		ActionModel.getActionModel().addAction("Fortification Phase:");
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
			ActionModel.getActionModel().addAction(" move " + (Armyinput-1) + " army from "+ fortify.get(1).getName() + " to " + fortify.get(0).getName());
		}
	}

	@Override
	public void setup(Player p) {
		if (p.getStartingPoints() == 0)
		{
			return;
		}
		else {
			int army = p.getStartingPoints();
			while (army > 0) {
				for (Country c : p.getOccupiedCountries()) {
					c.setArmyCount(1);
					army--;
					if (army == 0) {
						p.setStartingPoints(0);
						return;
					}
				}
			}	
		}
	}
}