/**
 * Necessary to create a dice
 * 
 * @author Natheepan
 * 
 */
package com.risk.dice;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This method calculates the random number between 1-6 representing the dice roll
 * 
 * @return number of the dice roll between 1-6
 */
public class Dice {

    public static int roll()
    {
        return (int) (Math.random() * 6) + 1;
    }

}
