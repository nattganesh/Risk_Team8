/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.army;

import com.risk.card.Card;
import com.risk.map.Country;
import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class Player {

    private String name;
    private ArrayList<Country> occupiedCountries = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();

    public Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Country> getOccupiedCountries()
    {
        return occupiedCountries;
    }

    public void setOccupiedCountries(ArrayList<Country> occupiedCountries)
    {
        this.occupiedCountries = occupiedCountries;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

}
