/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;
import java.util.ArrayList;

/**
 * An aggressive computer player strategy that focuses on attack (reinforces its
 * strongest country, then always attack with it until it cannot attack anymore,
 * then fortifies in order to maximize aggregation of forces in one country).
 *
 * @author Natheepan
 */
public class AggressivePlayer extends ComputerPlayer {

    private Country strongestCountry;

    public AggressivePlayer(String name)
    {
        super(name);
    }

    public Country getStrongestCountry()
    {
        strongestCountry = getOccupiedCountries().get(0);
        for (int i = 1; i < getOccupiedCountries().size(); i++)
        {
            if (getOccupiedCountries().get(i).getArmyCount() > strongestCountry.getArmyCount())
            {
                strongestCountry = getOccupiedCountries().get(i);
            }
        }
        return strongestCountry;
    }

    @Override
    public void reinforce(Country myCountry, int Armyinput)
    {
        reinforce(Armyinput);
    }

    public void reinforce(int Armyinput)
    {
        Country strongestCountry = getStrongestCountry();
        strongestCountry.setArmyCount(Armyinput);
    }

    @Override
    public void attack(Country attack, Country defend, int caseType)
    {
        attack();
    }

    public void attack()
    {
        ArrayList<Country> neighboursOfStrongestCountry = strongestCountry.getConnectedEnemyArrayList();

        while (!neighboursOfStrongestCountry.isEmpty() && strongestCountry.getArmyCount() > 1)
        {
            Country defendingCountry = neighboursOfStrongestCountry.get(0);
            int[] dattack = rollResult(3);
            int[] ddefend = rollResult(2);
            int rolltime = setRollTime(3, 2);

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
                            getCards().addAll(defendingCountry.getRuler().getCards());
                        }
                        defendingCountry.setRuler(this);
                        addCountry(defendingCountry);
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

    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {
        fortify(Armyinput);
    }

    public void fortify(int Armyinput)
    {
        Country strongestCountry = getStrongestCountry();
        strongestCountry.setArmyCount(Armyinput);
    }

}
