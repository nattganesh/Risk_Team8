package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.ActionModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.ObservableList;

public class Random implements Strategy{
	@Override
	public void attack(Player p) {
		ActionModel.getActionModel().addAction("Attack Phase:");
		ArrayList<Country> countryWithEnoughArmy = new ArrayList<Country>();
		for(Country c: p.getOccupiedCountries()) {
			if(c.getArmyCount()>1&&(!c.getConnectedEnemyArrayList().isEmpty())) {
				countryWithEnoughArmy.add(c);
			}
		}
		if(!countryWithEnoughArmy.isEmpty()) {
			Country attack = countryWithEnoughArmy.get(getRandomNumber(countryWithEnoughArmy.size()));
			Country defend = attack.getConnectedEnemyArrayList().get(getRandomNumber(attack.getConnectedEnemyArrayList().size()));
			ActionModel.getActionModel().addAction("Random country to attack: "+attack.getName());
			ActionModel.getActionModel().addAction("Random country to defend: "+defend.getName());
			int limit = Integer.max(attack.getArmyCount(), defend.getArmyCount());
			limit = getRandomNumber(limit);
			int time = 0;
			boolean occupy = false;
			while(time<limit&&attack.getArmyCount()>1&&!occupy) {
				int result[] = p.setRollLimit(attack, defend);
				int randomAttack = getRandomNumber(result[0]);
				int randomDefend = getRandomNumber(result[1]);
				int[] dattack = p.rollResult(randomAttack);
				int[] ddefend = p.rollResult(randomDefend);
				int rolltime = Integer.min(randomAttack, randomDefend);
				for (int i = 0; i < rolltime; i++) {
					if (dattack[i] > ddefend[i]) {
						defend.reduceArmyCount(1);
						if (defend.getArmyCount() == 0) {
							defend.getRuler().removeCountry(defend);
							if (defend.getRuler().isPlayerLost()) {
								p.moveCards(defend.getRuler());
							}
							defend.setRuler(p);
							p.addCountry(defend);
							occupy = true;
							int army = attack.getArmyCount()-1-result[0];
							int armyinput = result[0] + getRandomNumber(army);
							attack.reduceArmyCount(armyinput);
							defend.setArmyCount(armyinput);
							ActionModel.getActionModel().addAction(attack.getName()+" conquers "+defend.getName());
							break;
						}
					} else {
						attack.reduceArmyCount(1);
					}
				}
				time++;
			}
		}
	}

	@Override
    public void reinforce(Player p)
    {
		ActionModel.getActionModel().addAction("Reinforcement Phase:");
		int Armyinput = p.getReinforcementArmy();
        Country random = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
        int initialArmy = random.getArmyCount();
        random.setArmyCount(Armyinput);
        ActionModel.getActionModel().addAction("added " + Armyinput + " to " + random.getName() + "(" + initialArmy + ")");
    }
    
    public int getRandomNumber(int limit)
    {
        return (int) (Math.random() * limit);
    }

	@Override
	public void fortify(Player p) {
		ActionModel.getActionModel().addAction("Fortification Phase:");
		ArrayList<Country> countriesHaveConnectedPath = new ArrayList<Country>();
		ArrayList<Country> fortify = new ArrayList<Country>();
		if(p.isAnyCountriesConnected()) {
			for(Country c: p.getOccupiedCountries()) {
				ArrayList<Country> connected = new ArrayList<Country>();
				connected = p.getCountriesArrivedbyPath(c, c, connected);
				if(!connected.isEmpty()) {
					countriesHaveConnectedPath.add(c);
				}
				
			}
			Country randomFrom = countriesHaveConnectedPath.get(getRandomNumber(countriesHaveConnectedPath.size()));
			fortify = p.getCountriesArrivedbyPath(randomFrom, randomFrom, fortify);
			Country randomTo = fortify.get(getRandomNumber(fortify.size()));
			int armyInput = randomFrom.getArmyCount()-1;
			armyInput = getRandomNumber(armyInput);
			randomFrom.reduceArmyCount(armyInput);
			randomTo.setArmyCount(armyInput);
			ActionModel.getActionModel().addAction(" move " + armyInput + " army from "+ randomFrom + " to " + randomTo);
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
			for(int i=0; i<army; i++) {
				Country random = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
		        random.setArmyCount(1);
			}
			p.setStartingPoints(0);	
		}
	}


}