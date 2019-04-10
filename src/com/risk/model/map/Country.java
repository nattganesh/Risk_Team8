/**
 * This class is necessary for creating country
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.map;

import com.risk.model.player.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Observable;

public class Country extends Observable 
{

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
     * This method is used to get the name of country
     *
     * @return name returns the country name
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method is used to get the continent where the country belongs to
     *
     * @return continentName returns the continent
     */
    public Continent getContinent()
    {
        return continent;
    }

    /**
     * This method is used to get the number of army the country has
     *
     * @return armyCount returns the army count
     */
    public int getArmyCount()
    {
        return armyCount;
    }

    /**
     * This method is used to increase the army of the country
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
     * This method is used to reduce the army of the country
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
     * This method is used to check if the country is occupied
     *
     * @return isOccupied true if country occupied, false otherwise
     */
    public boolean isIsOccupied()
    {
        return isOccupied;
    }

    /**
     * This method is used to set the occupy state of the country
     *
     * @param isOccupied boolean value to set the country's occupied state
     */
    public void setIsOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }

    /**
     * This method is used to get the owner of the country
     *
     * @return ruler The player object ruling the country
     */
    public Player getRuler()
    {
        return ruler;
    }

    /**
     * This method is used to set the owner of the country
     *
     * @param ruler sets the ruler of the country
     */
    public void setRuler(Player ruler)
    {
        this.ruler = ruler;

    }

    /**
     * This method is used to get the connected countries to the country
     *
     * @return connectedCountries connected countries for the country
     */
    public ArrayList<Country> getConnectedCountries()
    {
        return connectedCountries;
    }

    /**
     * This method is used to set the connected countries to the country
     *
     * @param connectedCountries sets the arraylist of connected countries for
     * the given country
     */
    public void setConnectedCountries(ArrayList<Country> connectedCountries)
    {
        this.connectedCountries = connectedCountries;
    }

    /**
     * This method is used to get the country which is connected to the country
     *
     * @param name The name of country which is connected to the country
     * @return country The country connected to the country
     */
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
     * This is a method for get countries that are connected to the country the
     * player chooses And all of those countries are occupied by the player
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
     * This is a method for returning connected enemy countries of the country
     * the player selects And those countries are not owned by that player
     *
     * @return list The enemy countries connected to the country the player
     * selects
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

    /**
     * This is a method for returning connected enemy countries of the current
     * country
     *
     * @return list The enemy countries connected to the country the player
     * selects
     */
    public ArrayList<Country> getConnectedEnemyArrayList()
    {
        ArrayList<Country> list = new ArrayList<>();
        for (Country country : connectedCountries)
        {
            if (!country.getRuler().getName().equals(ruler.getName()))
            {
                list.add(country);
            }
        }
        return list;
    }
    
    /**
     * This is a method for returning connected enemy countries of the current
     * country
     *
     * @return list The enemy countries connected to the country the player
     * selects
     */
    public ArrayList<Country> getConnectedOwnedArrayList()
    {
        ArrayList<Country> list = new ArrayList<>();
        for (Country country : connectedCountries)
        {
            if (country.getRuler().getName().equals(ruler.getName()))
            {
                list.add(country);
            }
        }
        return list;
    }

}
