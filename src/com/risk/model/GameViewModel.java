/**
 * Necessary to create the model of the game state. It is important for keeping track of game phase
 * and notify GamePhaseController to set the new scene for the game when the state changes.
 *
 * @author DKM
 * @author Natheepan
 */
package com.risk.model;

import java.util.Observable;

public class GameViewModel extends Observable {

    String phase = "setup";
    private static GameViewModel gameViewModel;

    /**
     * Constructor for GamePhaseModel class
     */
    private GameViewModel()
    {
    }

    /**
     *
     * @param p name of the game phase
     */
    public void setPhase(String p)
    {
        phase = p;
        setChanged();
        notifyObservers(phase);
    }
    
    public String getPhase()
    {
    	return phase;
    }

    /**
     *
     * @return this returns the GamePhaseModel class as a singleton
     */
    public static GameViewModel getGameViewModel()
    {
        if (gameViewModel == null)
        {
            gameViewModel = new GameViewModel();
        }
        return gameViewModel;
    }
}
