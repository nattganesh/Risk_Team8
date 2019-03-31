/**
 * Necessary to create a dice
 *
 * @author Natheepan
 *
 */
package com.risk.model.dice;

public class Dice {

    /**
     * This method calculates the random number between 1-6 representing the
     * dice roll
     *
     * @return number of the dice roll between 1-6
     */
    public static int roll()
    {
        return (int) (Math.random() * 6) + 1;
    }

}
