
/**
 * Necessary for creating deck owned by each player
 *
 * @author Tianyi
 * 
 */

package com.risk.card;

import java.util.Random;

/**
 *
 * @author Tianyi
 */
public class Deck {

    Card card[];
    int n = 44;
    String owner = null;

    public void initialize()
    {
        String catagory[] =
        {
            "Infantry", "Cavalry", "Artillery", "Wild"
        };

        card = new Card[n];
        for (int i = 0; i < n; i++)
        {
            if (i == 0 || i == 1)
            {
                card[i] = new Card(catagory[3], owner);
            }
            else if (i < 16)
            {
                card[i] = new Card(catagory[0], owner);
            }
            else if (i < 30)
            {
                card[i] = new Card(catagory[1], owner);
            }
            else
            {
                card[i] = new Card(catagory[2], owner);
            }
        }
        for (int i = 0; i < n; i++)
        {
            System.out.print(card[i]);
        }
        System.out.print("\n");

    }

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
