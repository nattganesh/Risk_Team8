/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.cheater;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyFortify;

/**
 * A cheater computer player strategy whose reinforce() method doubles the
 * number of armies on all its countries, whose attack() method automatically
 * conquers all the neighbors of all its countries, and whose fortify() method
 * doubles the number of armies on its countries that have neighbors that belong
 * to other players.
 *
 * @author Natheepan
 */
public class CheaterFortifyStrategy implements StrategyFortify {

    private final Player player;

    public CheaterFortifyStrategy(Player player)
    {
        this.player = player;
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
