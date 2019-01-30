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
public class DuplicatesException extends Exception{

    public DuplicatesException(String duplicateName)
    {
        super(duplicateName + " exists in more than one instance. Please resolve this issue, and try again.");
    }
   
}
