/**
 *
 */
package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author DKM
 *
 */
public class MapModel extends Observable {

	private ObservableList<Continent> continentsModel = FXCollections.observableArrayList();
    private ArrayList<Country> countriesModel = new ArrayList<>();
    private int exchangeTime = 0;
    private static MapModel mapModel;
    private MapModel(){}
    
    /**
     * This method gets the total number of exchange times
     *
     * @return The number of exchange times
     */
    public int getExchangeTime() {
    	return exchangeTime;
    }
    
    /**
     * This method sets the total number of exchange times
     *
     */
    public void setExchangeTime(int exchange) {
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
     * This method gets the continents in the continent model
     *
     * @return continentModel Returns the continent model
     */
    public ObservableList<Continent> getContinents()
    {
        return continentsModel;
    }
    
    public static MapModel getMapModel()
    {
        if(mapModel == null)
        {
            mapModel = new MapModel();
        }
        return mapModel;
    }
}
