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
    Card card[];
    int n = 44;
    String owner = null;

    /**
     * This method is used to initialize the deck with 44 cards.
     */
    public void initialize()
    {
        String category[] =
        {
            "Infantry", "Cavalry", "Artillery", "Wild"
        };

        for (int i = 0; i < n - 2; i++)
        {
            for (int j = 0; j < category.length - 1; j++)
            {
                cards.add(new Card(category[j]));
            }
        }
        cards.add(new Card(category[3]));
        cards.add(new Card(category[3]));
        Collections.shuffle(cards);
    }

    public static LinkedList<Card> getCards()
    {
        return cards;
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
    public void sendCard(Player player)
    {
        while (true)
        {
            int i = 0;
            Card tmp = cards.get(i);
            if (tmp.getOwner() == null)
            {
                tmp.setOwner(player);
                player.addCard(tmp);
                break;
            }
            i++;
        }
    }
}
