/**
 * This class is necessary for creating the Continent
 *
 * @author Natheepan
 */
package com.risk.model.map;

import java.util.ArrayList;

import com.risk.model.player.Player;

public class Continent {

    public static final int MAX_NUMBER_OF_COUNTRIES_IN_NORTH_AMERICA = 9;
    public static final int MAX_NUMBER_OF_COUNTRIES_IN_SOUTH_AMERICA = 4;
    public static final int MAX_NUMBER_OF_COUNTRIES_IN_AFRICA = 6;
    public static final int MAX_NUMBER_OF_COUNTRIES_IN_EUROPE = 7;
    public static final int MAX_NUMBER_OF_COUNTRIES_IN_ASIA = 12;
    public static final int MAX_NUMBER_OF_COUNTRIES_IN_AUSTRALIA = 4;
    private String name;
    private ArrayList<Country> countries = new ArrayList<>();
    private int pointsWhenFullyOccupied;
    private Player ruler;

    
    /**
     * This is the constructor for Continent
     *
     * @param name this continent's name
     * @param pointsWhenFullyOccupied points given when continents is occupied
     * by 1 ruler
     */
    public Continent(String name, int pointsWhenFullyOccupied)
    {
        this.name = name;
        this.pointsWhenFullyOccupied = pointsWhenFullyOccupied;
    }
    

    /**
     * this method is a getter for this continent name
     *
     * @return this returns name of continent
     */
    public String getName()
    {
        return name;
    }

    /**
     * this method is a getter for country within the continent
     *
     * @return this returns conutries inside continent
     */
    public ArrayList<Country> getCountries()
    {
        return countries;
    }
    
    public void setRuler(Player p)
    {
    	ruler = p;
    }
    
    public Player getRuler()
    {
    	return ruler;
    }
    
    /**
     * This method is a setter for adding country to a continent
     *
     * @param country Country to ba added
     */
    public void setCountry(Country country)
    {
        countries.add(country);
    }

    /**
     * @return this returns number of army when fully occupied by one ruler
     */
    public int getPointsWhenFullyOccupied()
    {
        return pointsWhenFullyOccupied;
    }

    /**
     * This method sets the continent control of the continent
     *
     * @param pointsWhenFullyOccupied army points when fully occupied
     */
    public void setPointsWhenFullyOccupied(int pointsWhenFullyOccupied)
    {
        this.pointsWhenFullyOccupied = pointsWhenFullyOccupied;
    }



    
   
}
