/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;

/**
 * A cheater computer player strategy whose reinforce() method doubles the
 * number of armies on all its countries, whose attack() method automatically
 * conquers all the neighbors of all its countries, and whose fortify() method
 * doubles the number of armies on its countries that have neighbors that belong
 * to other players.
 *
 * @author Natheepan
 */
public class CheaterPlayer extends ComputerPlayer {

    public CheaterPlayer(String name)
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
