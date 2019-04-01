/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.human;

import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyFortify;

/**
 * A human player that requires user interaction to make decisions.
 *
 * @author Natheepan
 */
public class HumanFortifyStrategy implements StrategyFortify {

    private final Country from;
    private final Country to;

    public HumanFortifyStrategy(Country from, Country to)
    {
        this.from = from;
        this.to = to;
    }

   /**
     * This method is necessary for fortify a country
     * @param Armyinput The number of armies to move
     */
    @Override
    public void fortify(int Armyinput)
    {
        from.reduceArmyCount(Armyinput);
        to.setArmyCount(Armyinput);
    }
}
