/**
 * Necessary to create the model of the players in the game play. Important for getting current
 * player
 *
 * @author Natheepan
 * @author Tianyi
 * @author DKM
 */
package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

public class PlayerPhaseModel extends Observable implements Observer {

    private ArrayList<Player> playerList = new ArrayList<Player>();
    public static final String[] playerName =
    {
        "Player1", "Player2", "Player3", "Player4", "Player5", "Player6"
    };
    private int currentPlayerIndex = 0;
    private Player playerWins = null;
    private static PlayerPhaseModel playerModel;

    /**
     * Constructor for PlayerModel class
     */
    PlayerPhaseModel()
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
     * @param increment this is the index of player to increment
     */
    public void setPlayerIndex(int increment)
    {
        currentPlayerIndex = increment;
    }

    /**
     *
     * @return the index of the current player
     */
    public int getPlayerIndex()
    {
        return currentPlayerIndex;
    }
    
    public void setNextPlayer()
    {
    	
    	while (true)
    	{
    		if (!playerList.get(++currentPlayerIndex % playerList.size()).isPlayerLost())
    		{
    			playerList.get(currentPlayerIndex % playerList.size());
    			break;
    		}
    	}
    }

    /**
     *
     * @return number of total players
     */
    public int getNumberOfPlayer()
    {
        return playerList.size();
    }

    /**
     * this method gets the current player
     *
     * @return current player
     */
    public Player getCurrentPlayer()
    {
        return playerList.get(currentPlayerIndex % playerList.size());
    }

    /**
     * this method adds player the player model
     *
     * @param player player to be added to the model
     */
    public void addPlayer(Player player)
    {
        playerList.add(player);
        player.addObserver(this);
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

    /**
     *
     * @return this PlayerModel class as a singleton
     */
    public static PlayerPhaseModel getPlayerModel()
    {
        if (playerModel == null)
        {
            playerModel = new PlayerPhaseModel();
        }
        return playerModel;
    }

    /**
     * This method receives update from Player class
     * This method notifies GamePhaseModel, updating world domination phase
     */
    @Override
    public void update(Observable o, Object country)
    {
        setChanged();
        notifyObservers((Country) country);
    }
}
