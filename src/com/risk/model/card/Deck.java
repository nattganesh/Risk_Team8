/**
 * Necessary for creating deck owned by each player
 *
 * @author Tianyi
 *
 */
package com.risk.model.card;

import java.util.Random;

public class Deck {

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

        card = new Card[n];
        for (int i = 0; i < n; i++)
        {
            if (i == 0 || i == 1)
            {
                card[i] = new Card(category[3], owner);
            }
            else if (i < 16)
            {
                card[i] = new Card(category[0], owner);
            }
            else if (i < 30)
            {
                card[i] = new Card(category[1], owner);
            }
            else
            {
                card[i] = new Card(category[2], owner);
            }
        }
        for (int i = 0; i < n; i++)
        {
            System.out.print(card[i]);
        }
        System.out.print("\n");

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
    public void sendCard(String player)
    {
        Card cardforp = card[0];
        cardforp.setOwner(player);
        for (int i = 0; i < n - 1; i++)
        {
            card[i] = card[i + 1];
        }
        card[n - 1] = cardforp;
        for (int i = 0; i < n; i++)
        {
            System.out.print(card[i]);
        }
        System.out.print("\n");
    }
}
