/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.map;

import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class Continent {

    private String name;
    private ArrayList<Country> countries = new ArrayList<>();
    private int pointsWhenFullyOccupied;

    public Continent(String name, int pointsWhenFullyOccupied)
    {
        this.name = name;
        this.pointsWhenFullyOccupied = pointsWhenFullyOccupied;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Country> getCountries()
    {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries)
    {
        this.countries = countries;
    }

    public int getPointsWhenFullyOccupied()
    {
        return pointsWhenFullyOccupied;
    }

    public void setPointsWhenFullyOccupied(int pointsWhenFullyOccupied)
    {
        this.pointsWhenFullyOccupied = pointsWhenFullyOccupied;
    }

}
