/**
 * Necessary to create the model of the map in the game play. It's important for getting
 * continents and countries of the map, and also keeping track of how many time the card was exchanged
 *
 * @author DKM
 * @author Natheepan
 * @version 3.0
 *
 */
package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MapModel extends Observable implements Observer 
{

    private ObservableList<Continent> continentsModel = FXCollections.observableArrayList();
    private ArrayList<Country> countriesModel = new ArrayList<>();
    private int exchangeTime = 0;
    private String mapType;
    private static MapModel mapModel;

    /**
     * This is a constructor for map model
     */
    public MapModel()
    {

    }
    
    /**
     * This method clears the state
     */
    public void clear()
    {
        continentsModel.clear();
        countriesModel.clear();
        exchangeTime = 0;
    }
    
    /**
     * this sets the map type
     * @param map map name in string
     */
    public void setMapType(String map)
    {
    	mapType = map;
    }
    
    /**
     * this gets the map type
     * @return the map type
     */
    public String getMapType()
    {
    	return mapType;
    }
    
    /**
     *
     * @return this gets the number of time the card was exchanged in the
     * current game
     */
    public int getExchangeTime()
    {
        return exchangeTime;
    }

    /**
     *
     * @param exchange sets the number of times the cards were exchanged in the
     * game
     */
    public void setExchangeTime(int exchange)
    {
        exchangeTime = exchange;
    }

    /**
     * This method adds a country to the country model
     *
     * @param country Country being added to the model
     */
    public void addCountry(Country country)
    {
        countriesModel.add(country);
        country.addObserver(this);
    }

    /**
     * This method adds a continent to the continent model
     *
     * @param continent Continent to be added to the model
     */
    public void addContinent(Continent continent)
    {
        continentsModel.add(continent);
    }

    /**
     * This method gets the countries in the country model
     *
     * @return countriesModel Returns the country model
     */
    public ArrayList<Country> getCountries()
    {
        return countriesModel;
    }

    /**
     * This method sets the countries to MapModel 
     *
     * @param countries ArrayList of countries
     */
    public void setCountries(ArrayList<Country> countries)
    {
        countriesModel = countries;
    }

    /**
     * This method gets the continents in the continent model
     *
     * @return continentModel Returns the continent model
     */
    public ObservableList<Continent> getContinents()
    {
        return continentsModel;
    }
    
    
    /**
     * This method is used to get the map model
     *
     * @return this returns MapModel class as a singleton
     */
    public static MapModel getMapModel()
    {
        if (mapModel == null)
        {
            mapModel = new MapModel();
        }
        return mapModel;
    }

    /**
     * This method receives update from change in army from country
     */
    @Override
    public void update(Observable o, Object arg)
    {
        Country country = (Country) arg;
        setChanged();
        notifyObservers(country);
    }
    
}
