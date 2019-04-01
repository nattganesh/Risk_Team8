/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.random;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyReinforcement;
import java.util.ArrayList;

/**
 * A random computer player strategy that reinforces random a random country,
 * attacks a random number of times a random country, and fortifies a random
 * country, all following the standard rules for each phase.
 *
 * @author Natheepan
 */
public class RandomReinforcementStrategy implements StrategyReinforcement {

    private final Player player;

    public RandomReinforcementStrategy(Player player)
    {
        this.player = player;
    }

    @Override
    public void reinforce(int Armyinput)
    {
        player.getOccupiedCountries().get(getRandomNumber(player.getOccupiedCountries().size())).setArmyCount(Armyinput);
    }
    
    public int getRandomNumber(int limit)
    {
        return (int) (Math.random() * limit);
    }
}
