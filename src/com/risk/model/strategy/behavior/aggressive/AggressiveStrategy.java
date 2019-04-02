/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.aggressive;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyAttack;
import java.util.ArrayList;

/**
 * An aggressive computer player strategy that focuses on attack (reinforces its
 * strongest country, then always attack with it until it cannot attack anymore,
 * then fortifies in order to maximize aggregation of forces in one country).
 *
 * @author Natheepan
 */
public class AggressiveAttackStrategy implements StrategyAttack {

    private final Player player;

    public AggressiveAttackStrategy(Player player)
    {
        this.player = player;
    }

    @Override
    public void attack()
    {
        Country strongestCountry = player.getStrongestCountry();
        ArrayList<Country> neighboursOfStrongestCountry = strongestCountry.getConnectedEnemyArrayList();

        while (!neighboursOfStrongestCountry.isEmpty() && strongestCountry.getArmyCount() > 1)
        {
            Country defendingCountry = neighboursOfStrongestCountry.get(0);
            int[] dattack = player.rollResult(3);
            int[] ddefend = player.rollResult(2);
            int rolltime = player.setRollTime(3, 2);

            for (int i = 0; i < rolltime; i++)
            {
                if (dattack[i] > ddefend[i])
                {
                    defendingCountry.reduceArmyCount(1);
                    if (defendingCountry.getArmyCount() == 0)
                    {
                        defendingCountry.getRuler().removeCountry(defendingCountry);
                        if (defendingCountry.getRuler().isPlayerLost())
                        {
                            player.getCards().addAll(defendingCountry.getRuler().getCards());
                        }
                        defendingCountry.setRuler(player);
                        player.addCountry(defendingCountry);
                        neighboursOfStrongestCountry.remove(0);
                        break;
                    }
                }
                else
                {
                    strongestCountry.reduceArmyCount(1);
                }
            }
        }
    }
}
