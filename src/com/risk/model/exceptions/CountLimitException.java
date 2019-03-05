/**
 * Exception thrown when number of country in a continent is different from fixed number.
 *
 * @author Natheepan
 */
package com.risk.model.exceptions;

public class CountLimitException extends Exception {

    public CountLimitException(String name, int count, int maxCount)
    {

        super(name + " must be equal to " + maxCount + ", currently it is " + count + ". Please resolve this issue, and try again.");

    }

    public CountLimitException(String name, int count, int minCount, int maxCount)
    {
        super("The number of " + name + " must be equal between " + minCount + " and " + maxCount + ", currently it is " + count + ". Please resolve this issue, and try again.");
    }

}
