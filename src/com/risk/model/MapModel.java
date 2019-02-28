/**
 *
 */
package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;

/**
 * @author DKM
 *
 */
public class MapModel extends Observable {

    private ArrayList<Continent> continentsModel = new ArrayList<>();
    private ArrayList<Country> countriesModel = new ArrayList<>();
    private static MapModel mapModel;
    private MapModel(){}
    /**
     * this method adds a country to the country model
     *
     * @param country country being added to the model
     */
    public void addCountry(Country country)
    {
        countriesModel.add(country);
    }

    /**
     * this method adds a continent to the continent model
     *
     * @param continent continent to be added to the model
     */
    public void addContinent(Continent continent)
    {
        continentsModel.add(continent);
    }

    /**
     * this method gets the countries in the country model
     *
     * @return countriesModel returns the country model
     */
    public ArrayList<Country> getCountries()
    {
        return countriesModel;
    }
    

    /**
     * this method gets the continents in the continent model
     *
     * @return continentModel returns the continent model
     */
    public ArrayList<Continent> getContinents()
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
