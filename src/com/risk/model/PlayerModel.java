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
import java.util.Observer;

import com.risk.model.player.Player;
import com.risk.model.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author DKM
 *
 */
public class PlayerModel extends Observable {

    private ArrayList<Player> playerList = new ArrayList<Player>();
    public static final String[] PLAYERCOLOR =
    {
        "Red", "Blue", "Green", "Yellow", "Orange", "Purple"
    };
    private int currentPlayerIndex = 0;
    private Player playerWins = null;
    private static PlayerModel playerModel;

    private PlayerModel()
    {
    }

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
    public void setPlayerIndex(int increment)
    {
        currentPlayerIndex = increment;
    }
    
    public int getPlayerIndex() {
    	return currentPlayerIndex;
    }


    public int getNumberOfPlayer() {
    	return playerList.size();
    }
    
    /**
     * this method gets the current player
     *
     * @return current player
     */
    public Player getCurrentPlayer()
    {
        return playerList.get(currentPlayerIndex);
    }

    /**
     * this method adds player the player model
     *
     * @param player player to be added to the model
     */
    public void addPlayer(Player player)
    {
        playerList.add(player);
    }

    /**
     * this method gets the players in the player models
     *
     * @return playersModel returns the player model
     */
    public ArrayList<Player> getPlayers()
    {
        return playerList;
    }

    public static PlayerModel getPlayerModel()
    {
        if (playerModel == null)
        {
            playerModel = new PlayerModel();
        }
        return playerModel;
    }

}
