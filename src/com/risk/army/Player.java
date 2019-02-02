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
    private static int startingPoints;
    private boolean playerLost = false;

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

    public static int getStartingPoints()
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

    public static void setStartingPoints(int numberOfPlayers)
    {
        switch (numberOfPlayers)
        {
            case 2:
                Player.startingPoints = 40;
                break;
            case 3:
                Player.startingPoints = 35;
                break;
            case 4:
                Player.startingPoints = 30;
                break;
            case 5:
                Player.startingPoints = 25;
                break;
            case 6:
                Player.startingPoints = 20;
                break;
        }
    }

    public int playersLeft()
    {
        int sum = 0;
        for (Country country : this.occupiedCountries)
        {
            sum += country.getArmyCount();
        }

        return startingPoints - sum;
    }

}
