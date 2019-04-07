package com.risk.model.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import com.risk.model.ActionModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.strategy.*;

public class Aggressive implements Strategy {
	

	@Override
	public void attack(Player p) {
		ActionModel.getActionModel().addAction("Attack Phase:");
		Country strongestCountry = p.getStrongestCountry();
		ActionModel.getActionModel().addAction("strongest country (" + strongestCountry + ")" + "("+ strongestCountry.getArmyCount()+")");

		ArrayList<Country> neighboursOfStrongestCountry = strongestCountry.getConnectedEnemyArrayList();
		while(!neighboursOfStrongestCountry.isEmpty() && strongestCountry.getArmyCount() > 1) {
			Country defendingCountry = neighboursOfStrongestCountry.get(0);
			ActionModel.getActionModel().addAction("defending country (" + defendingCountry + ")" + "("+defendingCountry.getArmyCount()+")");
			int result[] = p.setRollLimit(strongestCountry, defendingCountry);
			
			int[] dattack = p.rollResult(result[0]);
			int[] ddefend = p.rollResult(result[1]);
			ActionModel.getActionModel().addAction("attacker rolled " + dattack[0] + " dice");
			ActionModel.getActionModel().addAction("defender rolled " + ddefend[0] + " dice");
			int rolltime = Integer.min(result[0], result[1]);
			for (int i = 0; i < rolltime; i++) {
				if (dattack[i] > ddefend[i]) {
					defendingCountry.reduceArmyCount(1);
					ActionModel.getActionModel().addAction("removed " + 1 + " from " + defendingCountry.getName());
					if (defendingCountry.getArmyCount() == 0) {
						ActionModel.getActionModel().addAction(defendingCountry.getRuler().getName() + " lost " + "("+ defendingCountry +")");
						defendingCountry.getRuler().removeCountry(defendingCountry);
						
						if (defendingCountry.getRuler().isPlayerLost()) {
							p.moveCards(defendingCountry.getRuler());
							ActionModel.getActionModel().addAction("defender (" + defendingCountry.getRuler().getName() + ") lost");
							ActionModel.getActionModel().addAction("moving cards");					
							
						}
						
						
						defendingCountry.setRuler(p);
						p.addCountry(defendingCountry);
						strongestCountry.reduceArmyCount(result[0]);
						defendingCountry.setArmyCount(result[0]);
						ActionModel.getActionModel().addAction(strongestCountry.getName()+" conquers "+defendingCountry.getName());
						neighboursOfStrongestCountry = strongestCountry.getConnectedEnemyArrayList();
						break;
					}
				} else {
					ActionModel.getActionModel().addAction("attacker (" +strongestCountry + ") lost 1 army");
					strongestCountry.reduceArmyCount(1);
				}
			}	
		}
	}

	@Override
	public void reinforce(Player p) {
		ActionModel.getActionModel().addAction("Reinforcement Phase:");
		Country strongestCountry = p.getStrongestCountry();
		int initialArmy = strongestCountry.getArmyCount();
		int Armyinput = p.getReinforcementArmy();
		strongestCountry.setArmyCount(Armyinput);
		ActionModel.getActionModel().addAction("added " + Armyinput + " to " + strongestCountry.getName() + "(" + initialArmy + ")");
	}
	

	@Override
	public void fortify(Player p) {
		ActionModel.getActionModel().addAction("Fortification Phase:");
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
			ActionModel.getActionModel().addAction(" move " + armyinput+ " army from "+ c1.getName() + " to " + c2.getName());
		}
	}

	public int getRandomNumber(int limit)
	{ 
		return (int) (Math.random() * limit);
	}
	 
	@Override
	public void setup(Player p) {
		if (p.getStartingPoints() == 0)
		{
			return;
		} 
		else {
			Country c = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
			int armyimput = p.getStartingPoints();
			c.setArmyCount(armyimput);
			p.setStartingPoints(0);
		}
	}



}
