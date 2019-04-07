package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.ActionModel;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

public class Cheater implements Strategy{
	@Override
	public void attack(Player p) {
		ActionModel.getActionModel().addAction("Attack Phase:");
		ArrayList<Country> occupy = p.getOccupiedCountries();
		
		for (int i = 0; i< occupy.size(); i++)
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
