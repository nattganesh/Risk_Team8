/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.army;

import com.risk.card.Card;
import com.risk.map.Country;
import java.util.ArrayList;
import com.risk.card.*;

/**
 *
 * @author Natheepan
 */
public class Player {

    private String name;
    private ArrayList<Country> occupiedCountries = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private int startingPoints;
    private int availableReinforcement = 0;
    public static final String[] PLAYERCOLOR = { "Red", "Blue", "Green", "Yellow", "Orange", "Purple" };

    private boolean playerLost = false;

    public Player(String name)
    {
        this.name = name;
    }

    public void setStartingPoints(int i)
    {
        startingPoints = i;
    }

    public int getReinforcement()
    {
        int numbArmies = numbOccupied() % 3;
        if (numbArmies < 3)
        {
            availableReinforcement = 3;
            return availableReinforcement;
        }
        availableReinforcement = numbArmies;
        System.out.println("asdf " + availableReinforcement);
        return availableReinforcement;
    }

    public int getAvailableReinforcement()
    {
        return availableReinforcement;
    }

    public int setReinforcement(int assign)
    {
        if (assign > getAvailableReinforcement())
        {
            return -1;
        }
        availableReinforcement -= assign;
        return 1;

    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Country> getOccupiedCountries()
    {
        return occupiedCountries;
    }

    public int numbOccupied()
    {
        return occupiedCountries.size();
    }

    public void setOccupiedCountries(ArrayList<Country> occupiedCountries)
    {
        this.occupiedCountries = occupiedCountries;
    }

    public Country getCountry(String name)
    {
        for (Country country : occupiedCountries)
        {
            if (country.getName().equalsIgnoreCase(name))
            {
                return country;
            }
        }
        return null;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    public int getStartingPoints()
    {
        return startingPoints;
    }

    public boolean isPlayerLost()
    {
        return playerLost;
    }

    public void setPlayerLost(boolean playerLost)
    {
        this.playerLost = playerLost;
    }

    public int armiesLeft()
    {
        int sum = 0;
        for (Country country : this.occupiedCountries)
        {
            sum += country.getArmyCount();
        }

        return startingPoints - sum;
    }
}
