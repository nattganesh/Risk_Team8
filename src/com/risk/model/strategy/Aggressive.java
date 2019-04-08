/**
 * Necessary for implement aggressive strategy for aggressive player
 * Including the implementation of setup, reinforcement, attack and fortification.
 * @author Tianyi
 * @author Nat
 */
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
	
	/**
	 * This method is used to do the attack for aggressive player
	 * The aggressive player uses his strongest country to do the attack
	 * until it has no enough armies
	 * 
	 * @param p The player who is going to attack
	 */
	@Override
	public void attack(Player p) {

		ActionModel.getActionModel().addAction("======= Rule =======");
		ActionModel.getActionModel().addAction("attacks with strongest");
		ActionModel.getActionModel().addAction("==================");
		System.out.println("Attack Phase");
		Country strongestCountry = p.getStrongestCountry();
		ActionModel.getActionModel().addAction("strongest country (" + strongestCountry.getName() + ")" + "("+ strongestCountry.getArmyCount()+")");
		System.out.println("strongest country (" + strongestCountry.getName() + ")" + "("+ strongestCountry.getArmyCount()+")");
		ArrayList<Country> neighboursOfStrongestCountry = strongestCountry.getConnectedEnemyArrayList();
		while(!neighboursOfStrongestCountry.isEmpty() && strongestCountry.getArmyCount() > 1) {
			Country defendingCountry = neighboursOfStrongestCountry.get(0);
			ActionModel.getActionModel().addAction("defending country (" + defendingCountry.getName() + ")" + "("+defendingCountry.getArmyCount()+")");
			System.out.println("defending country (" + defendingCountry.getName() + ")" + "("+defendingCountry.getArmyCount()+")");
			int result[] = p.setRollLimit(strongestCountry, defendingCountry);
			
			int[] dattack = p.rollResult(result[0]);
			int[] ddefend = p.rollResult(result[1]);
			ActionModel.getActionModel().addAction("attacker rolled " + result[0] + " dice");
			ActionModel.getActionModel().addAction("defender rolled " + result[1] + " dice");
			System.out.println("attacker rolled " + result[0] + " dice");
			System.out.println("defender rolled " + result[1] + " dice");
			int rolltime = Integer.min(result[0], result[1]);
			for (int i = 0; i < rolltime; i++) {
				if (dattack[i] > ddefend[i]) {
					defendingCountry.reduceArmyCount(1);
					ActionModel.getActionModel().addAction("removed " + 1 + " from " + defendingCountry.getName());
					System.out.println("removed " + 1 + " from " + defendingCountry.getName());
					if (defendingCountry.getArmyCount() == 0) {
						ActionModel.getActionModel().addAction(defendingCountry.getRuler().getName() + " lost " + "("+ defendingCountry.getName() +")");
						System.out.println(p.getName()+" conquer "+defendingCountry.getRuler().getName()+" "+defendingCountry.getName());
						defendingCountry.getRuler().removeCountry(defendingCountry);
						
						if (defendingCountry.getRuler().getOccupiedCountries().size()==0) {
							defendingCountry.getRuler().setPlayerLost(true);
							System.out.println(defendingCountry.getRuler().getName() + " lost ");
							p.moveCards(defendingCountry.getRuler());
							ActionModel.getActionModel().addAction("defender (" + defendingCountry.getRuler().getName() + ") lost");
							ActionModel.getActionModel().addAction("moving cards");					
							
							System.out.println("defender (" + defendingCountry.getRuler().getName() + ") lost");
							System.out.println("moving cards");	
							
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
					ActionModel.getActionModel().addAction("attacker (" +strongestCountry.getName() + ") lost 1 army");
					System.out.println("attacker (" +strongestCountry.getName() + ") lost 1 army");
					strongestCountry.reduceArmyCount(1);
				}
			}	
		}
	}

	/**
	 * This method is used to do the reinforcement for aggressive player
	 * The aggressive player only reinforces his strongest country
	 * 
	 * @param p The player who is going to reinforce
	 */
	@Override
	public void reinforce(Player p) {
		ActionModel.getActionModel().addAction("======= Rule =======");
		ActionModel.getActionModel().addAction("reinforcing strongest");
		ActionModel.getActionModel().addAction("==================");
		Country strongestCountry = p.getStrongestCountry();
		int initialArmy = strongestCountry.getArmyCount();
		int Armyinput = p.getReinforcementArmy();
		
		strongestCountry.setArmyCount(Armyinput);
		ActionModel.getActionModel().addAction("added " + Armyinput + " to " + strongestCountry.getName() + "(" + initialArmy + ")");
		System.out.println(p.getName()+":added " + Armyinput + " to " + strongestCountry.getName() + "(" + initialArmy + ")");
	}
	
	/**
	 * This method is used to do the fortification for aggressive player
	 * The aggressive player only fortifies his strongest country
	 * 
	 * @param p The player who is going to fortify
	 */
	@Override
	public void fortify(Player p) {
		
		ActionModel.getActionModel().addAction("======= Rule =======");
		ActionModel.getActionModel().addAction("fortifies strongest");
		ActionModel.getActionModel().addAction("==================");
		int tmp = 0;
		ArrayList<Country> maximumArmy = new ArrayList<Country>();
		if(p.isAnyCountriesConnected()) {
			for(Country c: p.getOccupiedCountries() ) {
				ArrayList<Country> connected = new ArrayList<Country>();
				connected = p.getCountriesArrivedbyPath(c, c, connected);
				if(!connected.isEmpty()&&c.getArmyCount()>1) {
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
			
			if(c1.getConnectedEnemyArrayList().size()>c2.getConnectedEnemyArrayList().size()&&c2.getArmyCount()>1) {
				int armyinput = c2.getArmyCount()-1;
				c2.reduceArmyCount(armyinput);
				c1.setArmyCount(armyinput);
				ActionModel.getActionModel().addAction(" move " + armyinput+ " army from "+ c2.getName() + " to " + c1.getName());
				System.out.println(p.getName()+": move " + armyinput+ " army from "+ c2.getName() + " to " + c1.getName());
			}
			else {
				int armyinput = c1.getArmyCount()-1;
				c1.reduceArmyCount(armyinput);
				c2.setArmyCount(armyinput);
				ActionModel.getActionModel().addAction(" move " + armyinput+ " army from "+ c1.getName() + " to " + c2.getName());
				System.out.println(p.getName()+": move " + armyinput+ " army from "+ c1.getName() + " to " + c2.getName());
			}
		}
	}

	/**
	 * This method is used to get a random number within the given limit
	 * 
	 * @param limit The limit of numbers, like the amount of armies
	 * @return The random number 
	 */
	public int getRandomNumber(int limit)
	{ 
		return (int) (Math.random() * limit);
	}
	 
	/**
	 * This method is used to do the set up for aggressive player
	 * The aggressive player will choose one country randomly and put all his starting armies to that country
	 * 
	 * @param p The player who is going to set up
	 */
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
