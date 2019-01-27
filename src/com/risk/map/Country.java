/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.map;

import com.risk.army.Player;
import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class Country {

    private String name;
    private boolean isOccupied;
    private Player ruler;
    private ArrayList<Country> connectedCountries = new ArrayList<>();

    public Country(String name, boolean isOccupied, Player ruler)
    {
        this.name = name;
        this.isOccupied = isOccupied;
        this.ruler = ruler;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isIsOccupied()
    {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }

    public Player getRuler()
    {
        return ruler;
    }

    public void setRuler(Player ruler)
    {
        this.ruler = ruler;
    }

    public ArrayList<Country> getConnectedCountries()
    {
        return connectedCountries;
    }

    public void setConnectedCountries(ArrayList<Country> connectedCountries)
    {
        this.connectedCountries = connectedCountries;
    }

}
