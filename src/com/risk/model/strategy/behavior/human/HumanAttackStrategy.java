/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.human;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.StrategyAttack;

/**
 * A human player that requires user interaction to make decisions.
 *
 * @author Natheepan
 */
public class HumanAttackStrategy implements StrategyAttack {

    private final Player player;
    private final Country attack;
    private final Country defend;
    private int caseType;

    public HumanAttackStrategy(Player player, Country attack, Country defend, int caseType)
    {
        this.player = player;
        this.attack = attack;
        this.defend = defend;
        this.caseType = caseType;
    }

    public void setCaseType(int caseType)
    {
        this.caseType = caseType;
    }

    /**
     * This method is used to decrease the army amount or change the owner of
     * country when it is occupied In case1, the defender lost in dice so it
     * lost 1 army In case2, the attacker conquered the defender, so the owner
     * of defender changes In case3, the attacker lost in dice so it lost 1 army
     *
     */
    @Override
    public void attack()
    {
        switch (caseType)
        {
            case 1:
                defend.reduceArmyCount(1);
                break;
            case 2:
                defend.getRuler().removeCountry(defend);
                if (defend.getRuler().isPlayerLost())
                {
                    attack.getRuler().getCards().addAll(defend.getRuler().getCards());
                }
                defend.setRuler(player);
                player.addCountry(defend);
                break;
            case 3:
                attack.reduceArmyCount(1);
                break;
        }
    }
}
