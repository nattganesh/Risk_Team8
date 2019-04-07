
package com.risk.model.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.DuplicatesException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test cases for file parser
 * @author dkm
 */
public class FileParserTest {
    
	
    /**
     * Test of setCountriesInContinents method, of class FileParser.
     * @throws FileNotFoundException exception thrown when file does not exist
     * 
     */
    @Test
    public void testSetCountriesInContinents() throws FileNotFoundException 
    {
        System.out.println("setCountriesInContinents");
        
        // the file has duplicate country
        Scanner input =  new Scanner(new File("src/com/risk/main/mapTextfiles/testSetCountriesInContinents.txt"));
        FileParser instance = new FileParser();
        
        assertThrows(DuplicatesException.class,
                () ->  instance.setCountriesInContinents(input));
    }

    /**
     * Test of setNeighboringCountries method, of class FileParser.
     * 
     * @throws FileNotFoundException  exception thrown when file does not exist
     */
    @Test
    public void testSetNeighboringCountries() throws FileNotFoundException
    {
        System.out.println("setNeighboringCountries");
        Scanner input =  new Scanner(new File("src/com/risk/main/mapTextfiles/testSetNeighboringCountries.txt"));
        FileParser instance = new FileParser();
        assertThrows(CannotFindException.class,
                () ->  instance.setNeighboringCountries(input));
    }

    /**
     * Tests empty file
     *
     * @throws FileNotFoundException exception thrown when file does not exist
     */
    @Test
    public void testEmptyFile() throws FileNotFoundException
    {
    	String EmptyFile = "src/com/risk/main/mapTextfiles/EmptyFile.txt";
        Scanner scan = new Scanner(new File(EmptyFile));
        FileParser fileParser = new FileParser();
        assertThrows(CannotFindException.class,
                () -> fileParser.init(scan));
    }

    /**
     * Tests invalid format file
     *
     * @throws FileNotFoundException exception thrown when file does not exist
     */
    @Test
    public void testInvalidFormatFile() throws FileNotFoundException
    {
    	String InvalidFormat = "src/com/risk/main/mapTextfiles/InvalidFormat.txt";
        Scanner scan = new Scanner(new File(InvalidFormat));
        FileParser fileParser = new FileParser();
        assertThrows(CannotFindException.class,
                () -> fileParser.init(scan));
    }
    
}
