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

import com.risk.model.player.Player;

public class PlayerModel extends Observable implements Observer {

    private ArrayList<Player> playerList = new ArrayList<Player>();
    public static final String[] PLAYERCOLOR =
    {
        "Red", "Blue", "Green", "Yellow", "Orange", "Purple"
    };
    private int currentPlayerIndex = 0;
    private Player playerWins = null;
    private static PlayerModel playerModel;

    /**
     * Constructor for PlayerModel class
     */
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
        setChanged();
        notifyObservers(playerList.get(currentPlayerIndex).getName());
    }

    /**
     *
     * @return the index of the current player
     */
    public int getPlayerIndex()
    {
        return currentPlayerIndex;
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
    public static PlayerModel getPlayerModel()
    {
        if (playerModel == null)
        {
            playerModel = new PlayerModel();
        }
        return playerModel;
    }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object player) {
		setChanged();
		notifyObservers((Player)player);
	}

}
