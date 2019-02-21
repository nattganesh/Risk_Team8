/**
 * Necessary to create the model of the gameplay. Keeps track of parsed map and players.
 * 
 * @author Natheepan
 * @author Tianyi
 * @author DKM
 */
package com.risk.model;

import java.util.ArrayList;

import com.risk.army.Player;
import com.risk.map.Continent;
import com.risk.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author DKM
 *
 */
public class Model {

    private ArrayList<Continent> continentsModel = new ArrayList<>();
    private ArrayList<Country> countriesModel = new ArrayList<>();
    private ObservableList<Player> playersModel = FXCollections.observableArrayList();
    private ObservableList<Country> currentPlayerCountry = FXCollections.observableArrayList();

    private int currentPlayerIndex = 0;
    private Player playerWins = null;

	/**
	 * Sets the winner of the game
	 * 
	 * @param player player who has won
	 * @return playerWins returns the player who has won
	 */
    public Player setWinner(Player player)
    {
        playerWins = player;
        return playerWins;
    }

	/**
	 * This method increments the current player index
	 * 
	 */
    public void IncrementPlayerIndex()
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % getPlayerSize();
        setView();
    }

	/**
	 * This method sets the observable list to countries occupied by currentPlayer
	 */
    public void setView()
    {
        currentPlayerCountry.clear();
        currentPlayerCountry.addAll(getCurrentPlayer().getOccupiedCountries());
    }

	/**
	 * This method gets the next player and increments the player index.
	 * 
	 * @return current returns the next player
	 */
    public Player getNextPlayer()
    {
        Player current = playersModel.get(currentPlayerIndex);
        IncrementPlayerIndex();
        return current;
    }

	/**
	 * This method sets the observable list of current player's occupied country
	 */
    public void setCurrentPlayerCountryObs()
    {
        currentPlayerCountry.addAll(getCurrentPlayer().getOccupiedCountries());
    }

	/**
	 * This method returns the observable list for countries occupied by current player
	 * 
	 * @return currentPlayerCountry returns ObservableList<Country> of current player
	 */
    public ObservableList<Country> getCurrentPlayerCountryObs()
    {
        return currentPlayerCountry;
    }

	/** 
	 * this method gets the current player
	 * 
	 * @return current player
	 */
    public Player getCurrentPlayer()
    {
        return playersModel.get(currentPlayerIndex);
    }

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
	 * this method adds player the player model 
	 * 
	 * @param player player to be added to the model
	 */
    public void addPlayer(Player player)
    {
        playersModel.add(player);
        player.setStartingPoints(getPlayerSize());
    }

	/**
	 * this method gets the number of players in the game
	 * 
	 * @return returns number of players
	 */
    public int getPlayerSize()
    {
        return playersModel.size();
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

	/**
	 * this method gets the players in the player model
	 * 
	 * @return playersModel returns the player model
	 */
    public ObservableList<Player> getPlayers()
    {
        return playersModel;
    }

	/**
	 * this method gets the country associated with the name of the country from the country model
	 * 
	 * @param name name of the country
	 * @return country returns country object
	 */
    public Country getCountry(String name)
    {
        for (Country country : countriesModel)
        {
            if (country.getName().equals(name))
            {
                return country;
            }
        }
        return null;
    }

	/**
	 * this method returns the player associated with the name of the player from the player model
	 * 
	 * @param name name of the player
	 * @return player returns player object
	 */
    public Player getPlayer(String name)
    {
        for (Player player : playersModel)
        {
            if (player.getName().equals(name))
            {
                return player;
            }
        }
        return null;
    }

	/**
	 * this method returns the continent associated with the name of the continent from the continent model
	 * 
	 * @param name name of the continent
	 * @return continent returns continent object
	 */
    public Continent getContinent(String name)
    {
        for (Continent continent : continentsModel)
        {
            if (continent.getName().equals(name))
            {
                return continent;
            }
        }
        return null;
    }

}
