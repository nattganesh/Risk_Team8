/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy;

/**
 *
 * @author Natheepan
 */
public interface Strategy {
    
    public void reinforce(int Armyinput);
    public void attack();
    public void fortify(int Armyinput);
}
