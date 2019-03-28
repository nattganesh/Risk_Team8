/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;

/**
 * A benevolent computer player strategy that focuses on protecting its weak
 * countries (reinforces its weakest countries, never attacks, then fortifies in
 * order to move armies to weaker countries).
 *
 * @author Natheepan
 */
public class BenevolentPlayer extends ComputerPlayer {

    public BenevolentPlayer(String name)
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
