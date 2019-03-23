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
    private PlayerPhaseModel()
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
    public static PlayerPhaseModel getPlayerModel()
    {
        if (playerModel == null)
        {
            playerModel = new PlayerPhaseModel();
        }
        return playerModel;
    }

    /**
     * This method notifies GamePhaseModel, updating world domination phase
     */
    @Override
    public void update(Observable o, Object country)
    {
        setChanged();
        notifyObservers((Country) country);
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

        int reinforcement = 0;
        for (Continent continent : MapModel.getMapModel().getContinents())
        {
            boolean control = true;
            for (Country country : continent.getCountries())
            {
                if (country.getRuler().getName() != playerList.get(0).getName())
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
        int reinforcement = (int) Math.floor(playerList.get(0).numbOccupied() / 3);
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
