/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;

/**
 * A random computer player strategy that reinforces random a random country,
 * attacks a random number of times a random country, and fortifies a random
 * country, all following the standard rules for each phase.
 *
 * @author Natheepan
 */
public class RandomPlayer extends ComputerPlayer {

    public RandomPlayer(String name)
    {
        super(name);
    }

    @Override
    public void reinforce(Country myCountry, int Armyinput)
    {
        reinforce(Armyinput);
    }

    public void reinforce(int Armyinput)
    {
        getOccupiedCountries().get(getRandomNumber(getOccupiedCountries().size())).setArmyCount(Armyinput);
    }

    @Override
    public void attack(Country attack, Country defend, int caseType)
    {
        attack();
    }

    public void attack()
    {
        int numberOfAttacks = getRandomNumber(getTotalArmy() + 1);
        Country myAttackingCountry = getOccupiedCountries().get(getRandomNumber(getOccupiedCountries().size()));
        while (myAttackingCountry.getConnectedEnemyArrayList().isEmpty())
        {
            myAttackingCountry = getOccupiedCountries().get(getRandomNumber(getOccupiedCountries().size()));
        }
        Country defendingCountry = myAttackingCountry.getConnectedEnemyArrayList().get(getRandomNumber(myAttackingCountry.getConnectedEnemyArrayList().size()));
        while (numberOfAttacks != 0 && myAttackingCountry.getArmyCount() > 1)
        {
            int[] dattack = rollResult(3);
            int[] ddefend = rollResult(2);
            int rolltime = setRollTime(3, 2);

            for (int i = 0; i < rolltime; i++)
            {
                numberOfAttacks--;
                if (dattack[i] > ddefend[i])
                {
                    defendingCountry.reduceArmyCount(1);
                    if (defendingCountry.getArmyCount() == 0)
                    {
                        defendingCountry.getRuler().removeCountry(defendingCountry);
                        if (defendingCountry.getRuler().isPlayerLost())
                        {
                            getCards().addAll(defendingCountry.getRuler().getCards());
                        }
                        defendingCountry.setRuler(this);
                        addCountry(defendingCountry);
                    }
                }
                else
                {
                    myAttackingCountry.reduceArmyCount(1);
                }
                if (numberOfAttacks == 0)
                {
                    return;
                }
            }
        }
    }

    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {
        fortify(Armyinput);
    }

    public void fortify(int Armyinput)
    {
        getOccupiedCountries().get(getRandomNumber(getOccupiedCountries().size())).setArmyCount(Armyinput);
    }

    public int getRandomNumber(int limit)
    {
        return (int) (Math.random() * limit);
    }

}
