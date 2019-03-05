/**
 * This class is necessary for creating country
 *
 * @author Natheepan
 */
package com.risk.model.map;

import com.risk.model.player.Player;

import javafx.beans.Observable;

import java.util.ArrayList;

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

    /**
     * This is the constructor for Country
     *
     * @param name this country name
     * @param continentName this continent name
     */
    public Country(String name, String continentName)
    {
        this.name = name;
        this.continentName = continentName;
        this.isOccupied = false;
        this.ruler = null;
    }

    /**
     *
     * @return name returns the country name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return continentName returns the continent name
     */
    public String getContinentName()
    {
        return continentName;
    }

    /**
     *
     * @return armyCount returns the army count
     */
    public int getArmyCount()
    {
        return armyCount;
    }

    /**
     *
     * @param armyCount army to increase for a country
     */
    public void setArmyCount(int armyCount)
    {
        this.armyCount += armyCount;
    }

    /**
     *
     * @param armyCount army to decrease for a country
     */
    public void reduceArmyCount(int armyCount)
    {
        this.armyCount -= armyCount;
    }

    /**
     *
     * @return isOccupied true if country occupied, false otherwise
     */
    public boolean isIsOccupied()
    {
        return isOccupied;
    }

    /**
     *
     * @param isOccupied boolean value to set the country's occupied state
     */
    public void setIsOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }

    /**
     *
     * @return ruler The player object ruling the country
     */
    public Player getRuler()
    {
        return ruler;
    }

    /**
     *
     * @param ruler sets the ruler of the country
     */
    public void setRuler(Player ruler)
    {
        this.ruler = ruler;
    }

    /**
     *
     * @return connectedCountries connected countries for the country
     */
    public ArrayList<Country> getConnectedCountries()
    {
        return connectedCountries;
    }

    /**
     *
     * @param connectedCountries sets the arraylist of connected countries for
     * the given country
     */
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
