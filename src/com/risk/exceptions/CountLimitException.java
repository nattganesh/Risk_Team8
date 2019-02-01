/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.exceptions;

/**
 *
 * @author Natheepan
 */
public class CountLimitException extends Exception{

    public CountLimitException(String name, int count, int maxCount)
    {
        super(name + " must be equal to " + maxCount + ", currently it is " + count + ". Please resolve this issue, and try again.");
    }
    
    public CountLimitException(String name, int count, int minCount, int maxCount)
    {
        super("The number of " + name + " must be equal between " + minCount + " and " + maxCount + ", currently it is " + count + ". Please resolve this issue, and try again.");
    }
   
}
