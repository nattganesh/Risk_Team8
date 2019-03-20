/**
 * 
 */
package com.risk.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;

import com.risk.model.card.Card;
import com.risk.model.player.Player;

/**
 * @author DKM
 *
 */
public class DeckModel extends Observable {

	private static DeckModel deckModel;
	private static LinkedList<Card> cards = new LinkedList<>();
    Card card[];
    int n = 44;
    String owner = null;

	
	
	/**
	 * singleton pattern for returning only once instance of object
	 * 
	 * @return actionModel object
	 */
    public static DeckModel getCardModel()
    {
        if (deckModel == null)
        {
        	deckModel = new DeckModel();
        }
        return deckModel;
    }

    /**
     * This method is used to initialize the deck with 44 cards.
     */
    public void initialize()
    {
        String category[] =
        {
            "Infantry", "Cavalry", "Artillery", "Wild"
        };

        for (int i = 0; i < n-2; i++)
        {
            for(int j = 0;j<category.length-1;j++) {
            	cards.add(new Card(category[j]));
            }
        }
        cards.add(new Card(category[3]));
        cards.add(new Card(category[3]));
        Collections.shuffle(cards);
    }
    
    /**
     * This method is used to send the card to a player.
     *
     * @param player The name of the player
     */
    public void sendCard(Player player)
    {
        while(true) {
        	int i =0;
        	Card tmp = cards.get(i);
        	if(tmp.getOwner()==null) {
        		tmp.setOwner(player);
        		player.addCard(tmp);
        		break;
        	}
        	i++;
        }
    }
}
