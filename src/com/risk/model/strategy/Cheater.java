/**
 * Necessary for implement cheater strategy for cheater player
 * Including the implementation of setup, reinforcement, attack and fortification.
 * @author Tianyi
 * @author Nat
 */
package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.ActionModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

public class Cheater implements Strategy{
	
	/**
	 * This method is used to do the attack for cheater player
	 * The cheater player occupies all his neighbor enemies and put one army to each new occupied country
	 * 
	 * @param p The player who is going to attack
	 */
	@Override
	public void attack(Player p) {
		ActionModel.getActionModel().addAction("Attack Phase:");
		ArrayList<Country> occupy = p.getOccupiedCountries();
		
		for (int i =0; i< occupy.size(); i++)
        {
			Country c = occupy.get(i);
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
				ActionModel.getActionModel().addAction(c.getName()+" conquers "+cNeighbors.getName());
            }
        }
	}
	
	/**
	 * This method is used to do the reinforcement for cheater player
	 * The cheater player gets his armies of countries doubled at reinforcement phase
	 * 
	 * @param p The player who is going to reinforce
	 */
	@Override
	public void reinforce(Player p) {
		ActionModel.getActionModel().addAction("Reinforcement Phase:");
		for (Country c : p.getOccupiedCountries())
        {
			int army = c.getArmyCount();
			c.setArmyCount(army*2);
			ActionModel.getActionModel().addAction("added " + (army) + " to " + c.getName() + "(" + army + ")");
        }
		
	}
	
	/**
	 * This method is used to do the fortification for cheater player
	 * The cheater player gets his armies of countries which have neighbor enemy doubled at fortification phase
	 * 
	 * @param p The player who is going to fortify
	 */
	@Override
	public void fortify(Player p) {
		ActionModel.getActionModel().addAction("Fortification Phase:");
		for (Country c : p.getOccupiedCountries())
        {
			if(!c.getConnectedEnemyArrayList().isEmpty()) {
				int army = c.getArmyCount();
				c.setArmyCount(army*2);
				ActionModel.getActionModel().addAction("added " + (army) + " to " + c.getName() + "(" + army + ")");
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
	 * This method is used to do the set up for cheater player
	 * The cheater player randomly assigns armies to his countries at set up phase
	 * 
	 * @param p The player who is going to set up
	 */
	@Override
	public void setup(Player p) {
		if (p.getStartingPoints() == 0)
		{
			return;
		}
		else
		{
			int army = p.getStartingPoints();
			for(int i=0; i<army; i++) {
				Country random = p.getOccupiedCountries().get(getRandomNumber(p.getOccupiedCountries().size()));
		        random.setArmyCount(1);
			}
			p.setStartingPoints(0);		
		}
	}


}
