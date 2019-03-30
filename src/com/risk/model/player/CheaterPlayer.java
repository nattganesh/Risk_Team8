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
    public void reinforce(Country myCountry, int Armyinput)
    {
        reinforce(Armyinput);
    }
    
    public void reinforce(int Armyinput)
    {
        for (Country c : getOccupiedCountries())
        {
            c.setArmyCount(2 * Armyinput);
        }
    }
    
    @Override
    public void attack(Country attack, Country defend, int caseType)
    {
        attack();
    }
    
    public void attack()
    {
        for (Country c : getOccupiedCountries())
        {
            for (Country cNeighbors : c.getConnectedCountries())
            {
                cNeighbors.setRuler(this);
            }
        }
    }
    
    @Override
    public void fortify(Country from, Country to, int Armyinput)
    {
        fortify(Armyinput);
    }
    
    public void fortify(int Armyinput)
    {
        for (Country c : getOccupiedCountries())
        {
            if (!c.getConnectedEnemyArrayList().isEmpty())
            {
                c.setArmyCount(2 * c.getArmyCount());
            }
        }
    }
    
}
