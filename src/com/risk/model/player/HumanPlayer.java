/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;

/**
 * A human player that requires user interaction to make decisions.
 *
 * @author Natheepan
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name)
    {
        super(name);
        setIsComputerPlayer(false);
    }

    /**
     * This method is used to decrease the army amount or change the owner of
     * country when it is occupied In case1, the defender lost in dice so it
     * lost 1 army In case2, the attacker conquered the defender, so the owner
     * of defender changes In case3, the attacker lost in dice so it lost 1 army
     *
     * @param attack country that is attacking
     * @param defend country being attacked
     * @param caseType the type of attack
     *
     */
    @Override
    public void attack(Country attack, Country defend, int caseType)
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
                defend.setRuler(this);
                addCountry(defend);
                break;
            case 3:
                attack.reduceArmyCount(1);
                break;
        }
    }

    /**
     * This method is necessary for reinforcement The number of armies in the
     * country will be add with the number the player inputs
     *
     * @param myCountry the country to be reinforced
     * @param Armyinput the number of army to reinforce
     */
    @Override
    public void reinforce(Country myCountry, int Armyinput)
    {
        myCountry.setArmyCount(Armyinput);
    }

    /**
     * This method is necessary for fortify a country
     *
     * @param from The country which the player moves the armies from
     * @param to The country which the player moves the armies to
     * @param Armyinput The number of armies to move
     */
    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {
        from.reduceArmyCount(Armyinput);
        to.setArmyCount(Armyinput);
        
    }

}
