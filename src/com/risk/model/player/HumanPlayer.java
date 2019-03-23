/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;
import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name)
    {
        super(name);
    }

    @Override
    public void attack(Country attack, Country defend, int caseType)
    {
        switch (caseType)
        {
            case 1:
                defend.reduceArmyCount(1);
                break;
            case 2:
                defend.setRuler(this);
                defend.getRuler().removeCountry(defend);
                addCountry(defend);
                break;
            case 3:
                attack.reduceArmyCount(1);
                break;

        }
    }

    @Override
    public void reinforce(Country myCountry, int Armyinput)
    {
        myCountry.setArmyCount(Armyinput);
    }

    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {
        from.reduceArmyCount(Armyinput);
        to.setArmyCount(Armyinput);
    }

    /**
     * This method is used to get all accessible countries of the country player
     * chooses to move armies from
     *
     * @param country The country which is being checked to find all accessible
     * countries to it
     * @param firstCountry The country the player chooses to move armies from
     * @param countries The list that saves all countries accessible to the
     * country the player chooses
     * @return The list of accessible countries corresponding to the country
     *
     */
    @Override
    public ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry, ArrayList<Country> countries)
    {
//        Player p = country.getRuler();
        for (Country c : country.getConnectedCountries())
        {
            Player player = c.getRuler();
            if (player.getName().equals(getName()))
            {
                if (isCountryDuplicated(c, firstCountry, countries))
                {
                    countries.add(c);
                    countries = getCountriesArrivedbyPath(c, firstCountry, countries);
                }
            }
        }
        return countries;
    }

    /**
     * This method is used to check if the country accessible is already in the
     * result list And avoid adding the origin country the player choose to the
     * result list
     *
     * @param country The country which is being checked to find all accessible
     * countries to it
     * @param firstCountry The country the player chooses to move armies from
     * @param countries The list that saves all countries accessible to the
     * country the player chooses
     * @return true if the country accessible is not in the result list;
     * otherwise return false
     */
    public boolean isCountryDuplicated(Country country, Country firstCountry, ArrayList<Country> countries)
    {
        int i = 0;
        if (country.getName().equalsIgnoreCase(firstCountry.getName()))
        {
            i = 1;
        }
        else
        {
            for (Country c : countries)
            {
                if (c.getName().equalsIgnoreCase(country.getName()))
                {
                    i = 1;
                }
            }
        }
        if (i == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * This method checks if there is any country has accessible countries and
     * the country which has accessible countries has enough armies for move
     *
     * @return true if there is a country which has accessible countries and it
     * has enough armies for move. Otherwise, return false.
     */
    @Override
    public boolean isAnyCountriesConnected()
    {
        int i = 0;

        for (Country c : getOccupiedCountries())
        {
            ArrayList<Country> result = new ArrayList<>();
            if ((!getCountriesArrivedbyPath(c, c, result).isEmpty()) && (c.getArmyCount() > 1))
            {
                i = 1;
                break;
            }
        }
        return i == 1;
    }

}
