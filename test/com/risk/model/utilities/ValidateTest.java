/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.utilities;


import com.risk.model.MapModel;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.exceptions.DuplicatesException;
import com.risk.model.map.Country;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Natt
 */
public class ValidateTest {
    
    @Before
    public void setUp()
    {
    	 Validate.countriesModelValidationList.clear();
    	 MapModel.getMapModel().getContinents().clear();
         MapModel.getMapModel().getCountries().clear();
    }
    

    /**
     * Test of getValidateSize method, of class Validate.
     * To test if the size of validationList is correct.
     */
    @Test
    public void testGetValidateSize()
    {
    	Validate val = Validate.getValidate();
    	val.countriesModelValidationList.add(new Country("dummy"));
    	val.countriesModelValidationList.add(new Country("dummy"));
    	val.countriesModelValidationList.add(new Country("dummy"));
    	val.countriesModelValidationList.add(new Country("dummy"));
    	
        int expResult = 4;
        int result = val.getValidateSize();
        assertEquals(expResult, result);
        
    }


    /**
     * Test of continentChecks method, of class Validate.
     * 
     * @throws Exception 
     */
    @Test
    public void testContinentChecks() throws Exception
    {
    	 String ExceedsContinentLimitFile = "src/com/risk/main/mapTextfiles/ExceedsContinentLimit.txt";
    	 Scanner scan = new Scanner(new File(ExceedsContinentLimitFile));
         FileParser fileParser = new FileParser();
         fileParser.init(scan);
         assertThrows(CountLimitException.class,
                 () -> Validate.getValidate().continentChecks());
    }

    
    /**
     * Test of mapConnected method, of class Validate.
     * @throws FileNotFoundException 
     * @throws DuplicatesException 
     * @throws CannotFindException 
     */
    @Test
    public void testMapConnected() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
    	String validMap = "src/com/risk/main/mapTextfiles/valid1.txt";
        System.out.println("getValidateSize");
     
        Scanner scan = new Scanner(new File(validMap));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        Validate.getValidate().mapConnected(MapModel.getMapModel().getCountries().get(0));
        
        int expResult = 42;
        int result = Validate.getValidate().getValidateSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateMap method, of class Validate.
     * 
     * @throws Exception
     */
    @Test
    public void testValidateMap() throws Exception
    {
    	
    	Validate val = Validate.getValidate();
    	String ExceedsContinentLimitFile = "src/com/risk/main/mapTextfiles/ExceedsContinentLimit.txt";
        System.out.println("getValidateSize");
     
        Scanner scan = new Scanner(new File(ExceedsContinentLimitFile));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        
        
        assertThrows(CountLimitException.class,
                () -> val.validateMap());
    }
    
    /**
     * Tests parser.setCountriesInContinents(Scanner) for duplicate country in
     * the map
     *
     * @throws FileNotFoundException
     */
    public void testDuplicateCountry() throws FileNotFoundException
    {
    	String DuplicateCountry =  "src/com/risk/main/mapTextfiles/DuplicateCountry.txt";
        Scanner scan = new Scanner(new File(DuplicateCountry));
        FileParser fileParser = new FileParser();
        assertThrows(DuplicatesException.class,
                () -> fileParser.init(scan));
    }

    /**
     * Test for file that has a continent that does not exist
     *
     * @throws FileNotFoundException
     * @throws DuplicatesException
     * @throws CannotFindException
     */
    public void testNonExistentContinentFile() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
    	String NonExistentContinent =  "src/com/risk/main/mapTextfiles/NonExistentContinent.txt";
        Scanner scan = new Scanner(new File(NonExistentContinent));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        assertThrows(CannotFindException.class,
                () -> Validate.getValidate().continentChecks());
    }

    /**
     * Tests for file that has exceeding number of country in the continent
     *
     * @throws FileNotFoundException
     * @throws DuplicatesException
     * @throws CannotFindException
     */
    public void testExceedsContinentLimitFile() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
    	
    	String ExceedsContinentLimitFile = "src/com/risk/main/mapTextfiles/ExceedsContinentLimit.txt";
        Scanner scan = new Scanner(new File(ExceedsContinentLimitFile));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        assertThrows(CountLimitException.class,
                () -> Validate.getValidate().continentChecks());
    }

    /**
     * Tests for file that has no connectivity
     *
     * @throws FileNotFoundException
     * @throws DuplicatesExceptions
     * @throws CannotFindException
     * @throws CountLimitException
     */
    public void testContinentCountLimit() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
    	String NoConnectivity =  "src/com/risk/main/mapTextfiles/NoConnectivity.txt";
        Scanner scan = new Scanner(new File(NoConnectivity));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        Validate.getValidate().mapConnected(MapModel.getMapModel().getCountries().get(0));

        assertFalse(Validate.getValidate().getValidateSize() == MapModel.getMapModel().getCountries().size());
    }
}
