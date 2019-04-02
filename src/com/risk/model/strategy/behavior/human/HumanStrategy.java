/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.human;

import com.risk.model.player.Player;
import com.risk.model.map.Country;
import com.risk.model.strategy.Strategy;

/**
 * A human player that requires user interaction to make decisions.
 *
 * @author Natheepan
 */
public class HumanStrategy implements Strategy {

    private Player player = null;
    private Country attackingCountry = null;
    private Country defendingCountry = null;
    private Country reinforceingCountry = null;
    private Country fortifyingFrom = null;
    private Country fortifyingTo = null;
    private int caseType = -1;

    public HumanStrategy(Country attackingCountry, Country defendingCountry, int attackingCaseType)
    {
        this.player = attackingCountry.getRuler();
        this.attackingCountry = attackingCountry;
        this.defendingCountry = defendingCountry;
        this.caseType = attackingCaseType;
    }
    
    public HumanStrategy(Country reinforceingCountry)
    {
        this.reinforceingCountry = reinforceingCountry;
    }
    
    public HumanStrategy(Country fortifyingFrom, Country fortifyingTo)
    {
        this.fortifyingFrom = fortifyingFrom;
        this.fortifyingTo = fortifyingTo;
    }

    public void setCaseType(int attackingCaseType)
    {
        this.caseType = attackingCaseType;
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
        reinforceingCountry.setArmyCount(Armyinput);
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
                defendingCountry.reduceArmyCount(1);
                break;
            case 2:
                defendingCountry.getRuler().removeCountry(defendingCountry);
                if (defendingCountry.getRuler().isPlayerLost())
                {
                    attackingCountry.getRuler().getCards().addAll(defendingCountry.getRuler().getCards());
                }
                defendingCountry.setRuler(player);
                player.addCountry(defendingCountry);
                break;
            case 3:
                attackingCountry.reduceArmyCount(1);
                break;
        }
    }

    /**
     * This method is necessary for fortify a country
     *
     * @param Armyinput The number of armies to move
     */
    @Override
    public void fortify(int Armyinput)
    {
        fortifyingFrom.reduceArmyCount(Armyinput);
        fortifyingTo.setArmyCount(Armyinput);
    }
}
