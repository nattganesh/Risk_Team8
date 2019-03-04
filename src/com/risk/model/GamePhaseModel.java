/**
 * Necessary to create the model of the game state. It is important for keeping track of game phase
 * and notify GamePhaseController to set the new scene for the game when the state changes.
 * @author DKM
 */
package com.risk.model;

import java.util.Observable;

public class GamePhaseModel extends Observable {

    String phase = "setup";
    private static GamePhaseModel gamePhaseModel;

    /**
     * Constructor for GamePhaseModel class
     */
    private GamePhaseModel()
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

    /**
     * 
     * @return this returns the GamePhaseModel class as a singleton
     */
    public static GamePhaseModel getGamePhaseModel()
    {
        if (gamePhaseModel == null)
        {
            gamePhaseModel = new GamePhaseModel();
        }
        return gamePhaseModel;
    }
}
