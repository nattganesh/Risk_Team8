/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.dice;

/**
 *
 * @author Natheepan
 */
public class Dice {
    
    public static int roll(int num)
    {
        return (int) (Math.random() * 6) + 1;
    }
    
}
