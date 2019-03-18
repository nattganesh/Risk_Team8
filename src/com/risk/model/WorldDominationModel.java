/**
 * 
 */
package com.risk.model;

import java.util.Observable;
import java.util.Observer;

/**
 * @author DKM
 *
 */
public class WorldDominationModel extends Observable implements Observer {

	private static WorldDominationModel dominationModel;

	/**
	 * singleton pattern for returning only once instance of object
	 * 
	 * @return actionModel object
	 */
    public static WorldDominationModel getDominationModel()
    {
        if (dominationModel == null)
        {
            dominationModel = new WorldDominationModel();
        }
        return dominationModel;
    }
    
    private class playerObserver
    {
    	
    }
    
    private class territoryObserver{
    	
    }
    
    

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
