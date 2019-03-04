/**
 * Necessary to create a card.
 *
 * @author Natheepan
 * @version 1.0
 */
package com.risk.model.card;

import java.util.Random;

public class Card {

    public String category;
    public String owner;

    /**
     * @param category type of card
     * @param owner player of that card
     */
    public Card(String category, String owner)
    {
        this.category = category;
        this.owner = owner;
    }

    /**
     * This method returns the category and the owner for the type of card
     */
    public String toString()
    {
        return category + " " + owner;
    }

    /**
     * This method gets the type of card
     *
     * @return category returns the type of card
     */
    public String getCatagory()
    {
        return category;
    }

    /**
     * This method gets the name of the owner for the type of card
     *
     * @return owner returns the owner name of the card
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * This method sets the owner for the the type of card
     *
     * @param owner name of the owner of the card
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
}
