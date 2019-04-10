/**
 * Exception thrown when a country appears more than once in the map
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.exceptions;

public class DuplicatesException extends Exception 
{

    public DuplicatesException(String duplicateName)
    {
        super(duplicateName + " exists in more than one instance. Please resolve this issue, and try again.");
    }

}
