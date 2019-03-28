/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;

/**
 * A random computer player strategy that reinforces random a random country,
 * attacks a random number of times a random country, and fortifies a random
 * country, all following the standard rules for each phase.
 *
 * @author Natheepan
 */
public class RandomPlayer extends ComputerPlayer {

    public RandomPlayer(String name)
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
