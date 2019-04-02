/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy.behavior.benevolent;

import com.risk.model.strategy.StrategyAttack;

/**
 * A benevolent computer player strategy that focuses on protecting its weak
 * countries (reinforces its weakest countries, never attacks, then fortifies in
 * order to move armies to weaker countries).
 *
 * @author Natheepan
 */
public class BenevolentAttackStrategy implements StrategyAttack {

    public BenevolentAttackStrategy()
    {

    }

    @Override
    public void attack()
    {
        // Never Attacks
    }
}
