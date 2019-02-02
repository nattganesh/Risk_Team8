/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.card;

import java.util.Random;

/**
 *
 * @author Natheepan
 */
public class Card {

    public String catagory;
    public String owner;

    public Card(String catagory, String owner)
    {
        this.catagory = catagory;
        this.owner = owner;
    }

    public String toString()
    {
        return catagory + " " + owner;
    }

    public String getCatagory()
    {
        return catagory;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
}
