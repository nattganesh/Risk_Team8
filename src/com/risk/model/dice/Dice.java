/**
 * Necessary to create a dice
 *
 * @author Natheepan
 *
 */
package com.risk.model.dice;

import java.util.ArrayList;
import java.util.Arrays;
import com.risk.model.map.Country;

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

    /**
     * This method gets the maximum of number of dices the attacker and defender
     * can roll
     *
     * @param attack The country which invokes the attack
     * @param defend The country which is attacked
     * @return The result corresponding to the number of armies of attacker and
     * defender
     */
    public static int[] setRollLimt(Country attack, Country defend)
    {
        int dicerange_attack;
        int dicerange_defend;
        int[] result = new int[2];
        if ((attack.getArmyCount() - 1) > 3)
        {
            dicerange_attack = 3;
        }
        else
        {
            dicerange_attack = attack.getArmyCount() - 1;
        }
        if (defend.getArmyCount() >= 2)
        {
            dicerange_defend = 2;
        }
        else
        {
            dicerange_defend = 1;
        }
        result[0] = dicerange_attack;
        result[1] = dicerange_defend;
        return result;
    }
}
