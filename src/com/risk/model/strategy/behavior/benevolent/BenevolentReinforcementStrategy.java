/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.benevolent;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyReinforcement;
import java.util.ArrayList;

/**
 * A benevolent computer player strategy that focuses on protecting its weak
 * countries (reinforces its weakest countries, never attacks, then fortifies in
 * order to move armies to weaker countries).
 *
 * @author Natheepan
 */
public class BenevolentReinforcementStrategy implements StrategyReinforcement {

    private final Player player;

    public BenevolentReinforcementStrategy(Player player)
    {
        this.player = player;
    }

    @Override
    public void reinforce(int Armyinput)
    {
        ArrayList<Country> weakestCountries = player.getWeakestCountries();
        if (weakestCountries.size() == 1)
        {
            weakestCountries.get(0).setArmyCount(Armyinput);
            return;
        }
        int army = Armyinput;
        while (army > 0)
        {
            for (Country c : weakestCountries)
            {
                c.setArmyCount(1);
                army--;
                if (army == 0)
                {
                    return;
                }
            }
        }
    }
}
