/**
 * Necessary for implement random strategy for random player
 * Including the implementation of setup, reinforcement, attack and fortification.
 * @author Tianyi
 * @author Nat
 */
package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.ActionModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.ObservableList;

public class Random implements Strategy{
	/**
	 * This method is used to do the attack for cheater player
	 * The cheater player occupies all his neighbor enemies and put one army to each new occupied country
	 * 
	 * @param p The player who is going to attack
	 */
	@Override
	public void attack(Player p) {
		
		ActionModel.getActionModel().addAction("======= Rule =======");
		ActionModel.getActionModel().addAction("attacks random number ");
		ActionModel.getActionModel().addAction(" of times random country");
		ActionModel.getActionModel().addAction("==================");
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
			System.out.println("Random country to attack: "+attack.getName()+" "+attack.getArmyCount());
			System.out.println("Random country to defend: "+defend.getName()+" "+defend.getArmyCount());
			int limit = getRandomNumber(500);
			int time = 0;
			boolean occupy = false;
			System.out.println("Roll time: "+limit);
			while(time<limit&&attack.getArmyCount()>1&&!occupy) {
				int result[] = p.setRollLimit(attack, defend);
				int randomAttack = getRandomNumber(result[0])+1;
				int randomDefend = getRandomNumber(result[1])+1;
				int[] dattack = p.rollResult(randomAttack);
				int[] ddefend = p.rollResult(randomDefend);
				int rolltime = Integer.min(randomAttack, randomDefend);
				for (int i = 0; i < rolltime; i++) {
					if (dattack[i] > ddefend[i]) {
						defend.reduceArmyCount(1);
						System.out.println("removed " + 1 + " from " + defend.getName());
						if (defend.getArmyCount() == 0) {
							System.out.println(p.getName()+" conquer "+defend.getRuler().getName()+" "+defend.getName());
							defend.getRuler().removeCountry(defend);
							if (defend.getRuler().getOccupiedCountries().size()==0) {
								defend.getRuler().setPlayerLost(true);
								ActionModel.getActionModel().addAction("defender "+(defend.getRuler()+" lost"));
								System.out.println(defend.getRuler()+" lost");
								p.moveCards(defend.getRuler());
							}
							defend.setRuler(p);
							p.addCountry(defend);
							occupy = true;
							int army = attack.getArmyCount()-1-result[0];
							int armyinput = result[0] + getRandomNumber(army);
							attack.reduceArmyCount(armyinput);
							defend.setArmyCount(armyinput);
							ActionModel.getActionModel().addAction("("+attack.getRuler().getName()+")" + attack.getName()+" conquers "+defend.getName());
							System.out.println(p.getName()+": "+ attack.getName()+" conquers "+defend.getName());
							break;
						}
					} else {
						attack.reduceArmyCount(1);
						System.out.println("attacker (" +attack.getName() + ") lost 1 army");
						ActionModel.getActionModel().addAction("("+attack.getRuler().getName()+")" + attack.getName()+" lost 1 army");
						
					}
				}
				time++;
			}
		}
	}

	@Override
    public void reinforce(Player p)
    {
		ActionModel.getActionModel().addAction("======= Rule =======");
		ActionModel.getActionModel().addAction("fortifies a random ");
		ActionModel.getActionModel().addAction(" 	 country");
		ActionModel.getActionModel().addAction("==================");
		int Armyinput = p.getReinforcementArmy();
        Country random = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
        int initialArmy = random.getArmyCount();
        random.setArmyCount(Armyinput);
        ActionModel.getActionModel().addAction("added " + Armyinput + " to " + random.getName() + "(" + initialArmy + ")");
        System.out.println(p.getName()+": added " + Armyinput + " to " + random.getName() + "(" + initialArmy + ")");
    }
    
    public int getRandomNumber(int limit)
    {
        return (int) (Math.random() * limit);
    }

	@Override
	public void fortify(Player p) {
		
		ActionModel.getActionModel().addAction("======= Rule =======");
		ActionModel.getActionModel().addAction("Reinforces random a ");
		ActionModel.getActionModel().addAction(" 	random country");
		ActionModel.getActionModel().addAction("==================");
		ArrayList<Country> countriesHaveConnectedPath = new ArrayList<Country>();
		ArrayList<Country> fortify = new ArrayList<Country>();
		System.out.println(p.isAnyCountriesConnected());
		if(p.isAnyCountriesConnected()) {
			for(Country c: p.getOccupiedCountries()) {
				ArrayList<Country> connected = new ArrayList<Country>();
				connected = p.getCountriesArrivedbyPath(c, c, connected);
				if(!connected.isEmpty()&&c.getArmyCount()>1) {
					countriesHaveConnectedPath.add(c);
				}
				
			}
			Country randomFrom = countriesHaveConnectedPath.get(getRandomNumber(countriesHaveConnectedPath.size()));
			fortify = p.getCountriesArrivedbyPath(randomFrom, randomFrom, fortify);
			Country randomTo = fortify.get(getRandomNumber(fortify.size()));
			int armyInput = randomFrom.getArmyCount()-1;
			armyInput = getRandomNumber(armyInput)+1;
			randomFrom.reduceArmyCount(armyInput);
			randomTo.setArmyCount(armyInput);
			ActionModel.getActionModel().addAction(" move " + armyInput + " army from "+ randomFrom.getName() + " to " + randomTo.getName());
			System.out.println(p.getName()+": move " + armyInput + " army from "+ randomFrom.getName() + " to " + randomTo.getName());
		}
		else 
		{
			ActionModel.getActionModel().addAction("No connection, can't reinforce");
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
