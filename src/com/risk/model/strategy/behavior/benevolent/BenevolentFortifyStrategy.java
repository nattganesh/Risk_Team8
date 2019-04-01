/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.benevolent;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyFortify;

/**
 * A benevolent computer player strategy that focuses on protecting its weak
 * countries (reinforces its weakest countries, never attacks, then fortifies in
 * order to move armies to weaker countries).
 *
 * @author Natheepan
 */
public class BenevolentFortifyStrategy implements StrategyFortify {

    private final Player player;

    public BenevolentFortifyStrategy(Player player)
    {
        this.player = player;
    }

    @Override
    public void fortify(int Armyinput)
    {
        player.getWeakestCountries().get(0).setArmyCount(Armyinput);
    }
}
