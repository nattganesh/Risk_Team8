/**
 * Necessary for creating deck owned by each player
 *
 * @author Tianyi
 *
 */
package com.risk.model.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import com.risk.model.player.Player;

public class Deck {

    private static LinkedList<Card> cards = new LinkedList<>();
    private static Card card[];
    int n = 44;
    String owner = null;

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
        	if(i<14) {
        		card[i]=new Card(category[0]);
        	}else if(i<28) {
        		card[i]=new Card(category[1]);
        	}else if(i<42) {
        		card[i]=new Card(category[2]);
        	}else {
        		card[i]=new Card(category[3]);
        	}
        }
        shuffleCard();
    }

    public Card[] getCards()
    {
        return card;
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
        for (int i = 0; i < n; i++)
        {
            System.out.print(card[i]);
        }
        System.out.print("\n");
    }

    /**
     * This method is used to send the card to a player.
     *
     * @param player The name of the player
     */
    public Card sendCard(Player player)
    {
        while (true)
        {
            int i = 0;
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
