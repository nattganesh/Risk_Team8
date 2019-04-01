/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.human;

import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyReinforcement;

/**
 * A human player that requires user interaction to make decisions.
 *
 * @author Natheepan
 */
public class HumanReinforcementStrategy implements StrategyReinforcement {

    private final Country myCountry;

    public HumanReinforcementStrategy(Country myCountry)
    {
        this.myCountry = myCountry;
    }

        /**
     * This method is necessary for reinforcement The number of armies in the
     * country will be add with the number the player inputs
     * 
     * @param Armyinput the number of army to reinforce
     */
    @Override
    public void reinforce(int Armyinput)
    {
        myCountry.setArmyCount(Armyinput);
    }
}
