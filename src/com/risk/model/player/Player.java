/**
 * Necessary to create a player
 *
 * @author Natheepan
 *
 *
 */
package com.risk.model.player;

import com.risk.model.MapModel;
import com.risk.model.card.Card;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Player extends Observable {

    private String name;
    private ArrayList<Country> occupiedCountries = new ArrayList<>();
    
    
//    private ArrayList<Card> cards = new ArrayList<>();
    private ObservableList<Card> cards = FXCollections.observableArrayList();
        
    private int totalArmy;
    private int startingPoints;
   
    private boolean playerLost = false;
    
    public void setTotalArmy(int count)
    {
    	totalArmy  = totalArmy + count;
    }
    
    public void reduceTotalArmy(int count)
    {
    	totalArmy  =  totalArmy - count;
    }
    
    public int getTotalArmy()
    {
    	return totalArmy;
    }
    
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
     * 
     * @param country country to be added in the player's occupied territories
     */
    public void addCountry(Country country)
    {
    	
    	System.out.println("notify from addCountry");
    	occupiedCountries.add(country);
    	setChanged();
    	notifyObservers(this);
    }

    /** 
     * 
     * @param country country to be removed in the player's occupied territories
     */
    public void removeCountry(Country country)
    {
    	
    	System.out.println("notify from removeCountry");
    	occupiedCountries.remove(country);
    	setChanged();
    	notifyObservers(this);
    }
    
    public void isContinentOccupied()
    {
    	boolean isOccupied = true;
    	
    	
    	
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
    public ObservableList<Card> getCards()
    {
        return cards;
    }
    public void addCard(Card card) {
    	cards.add(card);
    }
    
    public void removeCard(Card card) {
    	for(Card c: cards) {
    		if(c.getCatagory().equals(card.getCatagory())) {
    			cards.remove(c);
    			break;
    		}
    	}
    }
    
    /**
     *
     * @param cards
     */
    public void setCards(ObservableList<Card> cards)
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
    
    /**
     * This method is used to calculate the extra armies earned if the player
     * has occupied continents
     *
     * @param currentPlayer The player who is in his reinforcement round
     * @return The result corresponding to the countries the player occupied
     */
    public int calculateReinforcementContinentControl()
    {
        String currentRuler = getName();
        int reinforcement = 0;
        for (Continent continent : MapModel.getMapModel().getContinents())
        {
            boolean control = true;
            for (Country country : continent.getCountries())
            {
                if (country.getRuler().getName() != currentRuler)
                {
                    control = false;
                    break;
                }
            }
            if (control)
            {
                reinforcement = reinforcement + continent.getPointsWhenFullyOccupied();
            }
        }
        return reinforcement;
    }

    /**
     * This method is used to calculate the extra armies based on the number of
     * countries the player already occupied
     *
     * @param currentPlayer The player who is in his reinforcement round
     * @return The result corresponding to the countries the player occupied
     */
    public int calculateReinforcementOccupiedTerritory()
    {
        int reinforcement = (int) Math.floor(numbOccupied() / 3);
        return reinforcement;
    }
    
    
    /**
     * This method is used to calculate the extra armies earned by exchanging
     * cards
     *
     * @return The result corresponding to the total exchange time.
     */
    public int calculateReinforcementFromCards()
    {
        int currentExchange = MapModel.getMapModel().getExchangeTime();
        int reinforcement = (currentExchange + 1) * 5;
        MapModel.getMapModel().setExchangeTime(currentExchange + 1);
        return reinforcement;
    }





    
    
    
}
