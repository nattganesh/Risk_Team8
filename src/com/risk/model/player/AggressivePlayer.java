/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;

/**
 * An aggressive computer player strategy that focuses on attack (reinforces its
 * strongest country, then always attack with it until it cannot attack anymore,
 * then fortifies in order to maximize aggregation of forces in one country).
 *
 * @author Natheepan
 */
public class AggressivePlayer extends ComputerPlayer {

    public AggressivePlayer(String name)
    {
        super(name);
    }

    @Override
    public void attack(Country attack, Country defend, int caseType)
    {

    }

    @Override
    public void reinforce(Country myCountry, int Armyinput)
    {

    }

    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {

    }

}
