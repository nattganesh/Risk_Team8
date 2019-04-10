/**
 * Exception thrown when input.txt file formatting is different from standard format.
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.exceptions;

public class CannotFindException extends Exception 
{

    public CannotFindException(String msg)
    {
        super(msg);
    }

}
