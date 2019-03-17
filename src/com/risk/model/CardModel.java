/**
 * 
 */
package com.risk.model;

import java.util.Observable;

/**
 * @author DKM
 *
 */
public class CardModel extends Observable {

	private static CardModel cardModel;
	

	
	
	/**
	 * singleton pattern for returning only once instance of object
	 * 
	 * @return actionModel object
	 */
    public static CardModel getCardModel()
    {
        if (cardModel == null)
        {
            cardModel = new CardModel();
        }
        return cardModel;
    }

}
