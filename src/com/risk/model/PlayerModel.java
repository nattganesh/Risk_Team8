/**
 * Necessary to create the model of the gameplay. Keeps track of parsed map and players.
 * 
 * @author Natheepan
 * @author Tianyi
 * @author DKM
 */
package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;

import com.risk.army.Player;
import com.risk.map.Continent;
import com.risk.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author DKM
 *
 */
public class PlayerModel extends Observable {

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
        currentPlayerIndex = (currentPlayerIndex + 1) % getNumberOfPlayer();
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
	 * this method adds player the player model 
	 * 
	 * @param player player to be added to the model
	 */
    public void addPlayer(Player player)
    {
        playersModel.add(player);
    }

	/**
	 * this method gets the number of players in the game
	 * 
	 * @return returns number of players
	 */
    public int getNumberOfPlayer()
    {
        return playersModel.size();
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

}
