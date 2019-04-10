/**
 * Necessary to create the model of the game state. It is important for keeping track of game phase
 * and notify GamePhaseController to set the new scene for the game when the state changes.
 *
 * @author DKM
 * @version 3.0
 */
package com.risk.model;

import java.util.Observable;

public class GamePhaseModel extends Observable 
{

    public String phase = "setup";
    private static GamePhaseModel gamePhaseModel;

    /**
     * Constructor for GamePhaseModel class
     */
    private GamePhaseModel()
    {
    }

    /**
     * This method is used to set the phase of game
     *
     * @param p name of the game phase
     */
    public void setPhase(String p)
    {
    	if (p.equals("startup"))
    	{
    		phase = p;
    	}
    	else if (!phase.equals("winner"))
        {
        	phase = p;
        } 
        setChanged();
        notifyObservers(phase);
    }

    /**
     * This method is used to get the phase of game
     *
     * @return String phase
     */
    public String getPhase()
    {
        return this.phase;
    }
    
    /**
     * This method is used to get the game phase model
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
