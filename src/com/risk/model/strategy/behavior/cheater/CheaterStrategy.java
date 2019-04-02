/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.cheater;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.Strategy;

/**
 * A cheater computer player strategy whose reinforce() method doubles the
 * number of armies on all its countries, whose attack() method automatically
 * conquers all the neighbors of all its countries, and whose fortify() method
 * doubles the number of armies on its countries that have neighbors that belong
 * to other players.
 *
 * @author Natheepan
 */
public class CheaterStrategy implements Strategy {

    private final Player player;

    public CheaterStrategy(Player player)
    {
        this.player = player;
    }

    @Override
    public void reinforce(int Armyinput)
    {
        for (Country c : player.getOccupiedCountries())
        {
            c.setArmyCount(2 * Armyinput);
        }
    }

    @Override
    public void attack()
    {
        for (Country c : player.getOccupiedCountries())
        {
            for (Country cNeighbors : c.getConnectedCountries())
            {
                cNeighbors.setRuler(player);
            }
        }
    }

    @Override
    public void fortify(int Armyinput)
    {
        for (Country c : player.getOccupiedCountries())
        {
            if (!c.getConnectedEnemyArrayList().isEmpty())
            {
                c.setArmyCount(2 * c.getArmyCount());
            }
        }
    }
}
