/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.aggressive;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyReinforcement;

/**
 * An aggressive computer player strategy that focuses on attack (reinforces its
 * strongest country, then always attack with it until it cannot attack anymore,
 * then fortifies in order to maximize aggregation of forces in one country).
 *
 * @author Natheepan
 */
public class AggressiveReinforcementStrategy implements StrategyReinforcement {

    private final Player player;

    public AggressiveReinforcementStrategy(Player player)
    {
        this.player = player;
    }

    @Override
    public void reinforce(int Armyinput)
    {
        Country strongestCountry = player.getStrongestCountry();
        strongestCountry.setArmyCount(Armyinput);
    }
}
