/**
 * Exception thrown when input.txt file formatting is different from standard format.
 *
 * @author Natheepan
 */
package com.risk.model.exceptions;

public class CannotFindException extends Exception {

    public CannotFindException(String msg)
    {
        super(msg);
    }

}