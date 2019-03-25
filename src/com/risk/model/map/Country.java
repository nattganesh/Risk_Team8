/**
 * This class is necessary for creating country
 *
 * @author Natheepan
 */
package com.risk.model.map;

import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Observable;

public class Country extends Observable {

//    public static final int MAX_NUMBER_OF_COUNTRIES = Continent.MAX_NUMBER_OF_COUNTRIES_IN_NORTH_AMERICA
//            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_SOUTH_AMERICA
//            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_EUROPE
//            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_AFRICA
//            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_ASIA
//            + Continent.MAX_NUMBER_OF_COUNTRIES_IN_AUSTRALIA;
	
	
    
    private String name;
    private boolean isOccupied;
    private Player ruler;

    private ArrayList<Country> connectedCountries = new ArrayList<>();
    private Continent continent;
    private int armyCount = 0;

    /**
     * This is the constructor for Country
     *
     * @param name The name of this country
     */
    public Country(String name)
    {
        this.name = name;
        this.isOccupied = false;
        this.ruler = null;
    }

    /**
     * This method sets the continent of the country
     * 
     * @param continent object that is the continent of the country
     */
    public void setContinent(Continent continent)
    {
        this.continent = continent;
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
    public Continent getContinent()
    {
        return continent;
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
        ruler.setTotalArmy(armyCount);
        setChanged();
        notifyObservers(this);

    }

    /**
     *
     * @param armyCount army to decrease for a country
     */
    public void reduceArmyCount(int armyCount)
    {
        this.armyCount -= armyCount;
        ruler.reduceTotalArmy(armyCount);
        setChanged();
        notifyObservers(this);
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

    /**
     * This is a method for get countries that are connected to the country the player chooses
     * And all of those countries are occupied by the player
     * 
     * @return list The connected countries to the country the player selects
     */
    public ObservableList<Country> getConnectedOwned()
    {
        ObservableList<Country> list = FXCollections.observableArrayList();
        for (Country country : connectedCountries)
        {
            if (country.getRuler().getName().equals(ruler.getName()))
            {
                list.add(country);
            }
        }
        return list;
    }

    /**
     * This is a method for returning connected enemy countries of the country the player selects
     * And those countries are not owned by that player
     * 
     * @return list The enemy countries connected to the country the player selects
     */
    public ObservableList<Country> getConnectedEnemy()
    {
        ObservableList<Country> list = FXCollections.observableArrayList();
        for (Country country : connectedCountries)
        {
            if (!country.getRuler().getName().equals(ruler.getName()))
            {
                list.add(country);
            }
        }
        return list;
    }

}
