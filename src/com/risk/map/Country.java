/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.map;

import com.risk.army.Player;
import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class Country {

    public static final int MAX_NUMBER_OF_COUNTRIES = Continent.MAX_NUMBER_OF_COUNTRIES_IN_NORTH_AMERICA
            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_SOUTH_AMERICA
            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_EUROPE
            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_AFRICA
            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_ASIA
            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_AUSTRALIA;
    private String name;
    private boolean isOccupied;
    private Player ruler;
    private ArrayList<Country> connectedCountries = new ArrayList<>();
    private String continentName;
    private int armyCount = 0;

    public Country(String name, String continentName)
    {
        this.name = name;
        this.continentName = continentName;
        this.isOccupied = false;
        this.ruler = null;
    }

    public String getName()
    {
        return name;
    }

    public String getContinentName()
    {
        return continentName;
    }

    public int getArmyCount()
    {
        return armyCount;
    }

    public void setArmyCount(int armyCount)
    {
        this.armyCount += armyCount;
    }

    public boolean isIsOccupied()
    {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }

    public Player getRuler()
    {
        return ruler;
    }

    public void setRuler(Player ruler)
    {
        this.ruler = ruler;
    }

    public ArrayList<Country> getConnectedCountries()
    {
        return connectedCountries;
    }

    public void setConnectedCountries(ArrayList<Country> connectedCountries)
    {
        this.connectedCountries = connectedCountries;
    }
    
    public Country getConnectedCountry(String name)
    {
        for (Country country : connectedCountries)
        {
            if (country.getName().equals(name))
            {
                return country;
            }
        }
        return null;
    }

}
