/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;
import java.util.ArrayList;

/**
 * A benevolent computer player strategy that focuses on protecting its weak
 * countries (reinforces its weakest countries, never attacks, then fortifies in
 * order to move armies to weaker countries).
 *
 * @author Natheepan
 */
public class BenevolentPlayer extends ComputerPlayer {

    public BenevolentPlayer(String name)
    {
        super(name);
    }

    public ArrayList<Country> getWeakestCountries()
    {
        Country weakestCountry = getOccupiedCountries().get(0);
        ArrayList<Country> weakCountries = new ArrayList<>();
        weakCountries.add(weakestCountry);
        for (int i = 1; i < getOccupiedCountries().size(); i++)
        {
            if (getOccupiedCountries().get(i).getArmyCount() < weakestCountry.getArmyCount())
            {
                weakCountries.clear();
                weakestCountry = getOccupiedCountries().get(i);
                weakCountries.add(weakestCountry);
            }
            else if (getOccupiedCountries().get(i).getArmyCount() == weakestCountry.getArmyCount())
            {
                weakCountries.add(getOccupiedCountries().get(i));
            }
        }
        return weakCountries;
    }

    @Override
    public void reinforce(Country myCountry, int Armyinput)
    {
        ArrayList<Country> weakestCountries = getWeakestCountries();
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

    @Override
    public void attack(Country attack, Country defend, int caseType)
    {
        // Never Attacks
    }

    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {
        getWeakestCountries().get(0).setArmyCount(Armyinput);
    }

}
