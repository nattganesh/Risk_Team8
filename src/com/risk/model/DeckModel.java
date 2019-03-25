/**
 *
 */
package com.risk.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import com.risk.model.card.Card;
import com.risk.model.player.Player;

/**
 * @author DKM
 * @author Tianyi
 *
 */
public class DeckModel extends Observable {
    private static DeckModel deckModel;
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
    	card = new Card[n];
        String category[] =
        {
            "Infantry", "Cavalry", "Artillery", "Wild"
        };

        for (int i = 0; i < n; i++)
        {
        	if(i<14) 
        	{
        		card[i]=new Card(category[0]);
        	}else if(i<28) 
        	{
        		card[i]=new Card(category[1]);
        	}else if(i<42) 
        	{
        		card[i]=new Card(category[2]);
        	}else 
        	{
        		card[i]=new Card(category[3]);
        	}
        }
        shuffleCard();
    }
    
    /**
     * This method is used to shuffle the deck randomly.
     */
    public void shuffleCard()
    {
        Random r = new Random();
        for (int i = 0; i < n; i++)
        {
            int ran = r.nextInt(n);
            Card tmp = card[i];
            card[i] = card[ran];
            card[ran] = tmp;
        }
    }


    public Card[] getCards()
    {
        return card;
    }
    
    /**
     * This method is used to send the card to a player.
     *
     * @param player The name of the player
     */
    public Card sendCard(Player player)
    {
        int i = 0;
        while (true)
        {
            if (card[i].getOwner() == null)
            {
            	card[i].setOwner(player);
                player.addCard(card[i]);
                return card[i];
            }
            i++;
        }
    }
}
