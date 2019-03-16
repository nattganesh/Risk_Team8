/**
 * Necessary to create a player
 *
 * @author Natheepan
 *
 *
 */
package com.risk.model.player;

import com.risk.model.card.Card;
import com.risk.model.map.Country;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Country> occupiedCountries = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private int startingPoints;
    private boolean playerLost = false;

    /**
     * Constructor for Player class
     *
     * @param name the name of the player in string
     */
    public Player(String name)
    {
        this.name = name;
    }

    /**
     * Sets the number of armies needed to start the game for a player
     *
     * @param i the count of the armies you need to start the game
     */
    public void setStartingPoints(int i)
    {
        this.startingPoints = i;
    }
    
    /**
     * Gets the starting points of armies left
     * 
     * @return
     */
    public int getStartingPoints()
    {
    	return startingPoints;
    }


    /**
     * gets the name of player
     *
     * @return name name of player
     */
    public String getName()
    {
        return name;
    }

    /**
     * gets the country occupied by player in arrayList
     *
     * @return occupiedCountries returns ArrayList<Country> occupiedCountries
     */
    public ArrayList<Country> getOccupiedCountries()
    {
        return occupiedCountries;
    }

    /**
     * gets the size of occupied country
     *
     * @return returns length of the ArrayList<Country> occupiedCountries
     */
    public int numbOccupied()
    {
        return occupiedCountries.size();
    }

  
    /**
     *
     * @return the index of the current player
     */
    public int getStartingP()
    {
        return startingPoints;
    }
    /**
     * gets country object associated with the name of the string
     *
     * @param name name of the country
     * @return country returns country object if it exists, otherwise null
     */
    public void setOccupiedCountries(ArrayList<Country> occupiedCountries)
    {
        this.occupiedCountries = occupiedCountries;
    }

    /**
     * gets the card associated with the player
     *
     * @return cards returns ArrayList<Card> cards
     */
    public Country getCountry(String name)
    {
        for (Country country : occupiedCountries)
        {
            if (country.getName().equalsIgnoreCase(name))
            {
                return country;
            }
        }
        return null;
    }

    /**
     * Gets the starting number of armies when the game first starts
     *
     * @return startingPoints number of armies
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    /**
     *
     * @param cards
     */
    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    /**
     * gets the value representing the lost status of player.
     *
     * @return playerLost returns true if player has lost, false otherwise
     */
    public boolean isPlayerLost()
    {
        return playerLost;
    }

    /**
     * sets the lost status of player
     *
     * @param playerLost the boolean value representing what playerLost should
     * be set by
     */
    public void setPlayerLost(boolean playerLost)
    {
        this.playerLost = playerLost;
    }
    

    
    
    

    
    
    
}
