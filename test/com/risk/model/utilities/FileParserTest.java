/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.utilities;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natt
 */
public class FileParserTest {
    
    public FileParserTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of setCountriesInContinents method, of class FileParser.
     */
    @Test
    public void testSetCountriesInContinents() throws Exception
    {
        System.out.println("setCountriesInContinents");
        Scanner input = null;
        FileParser instance = new FileParser();
        instance.setCountriesInContinents(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNeighboringCountries method, of class FileParser.
     */
    @Test
    public void testSetNeighboringCountries() throws Exception
    {
        System.out.println("setNeighboringCountries");
        Scanner input = null;
        FileParser instance = new FileParser();
        instance.setNeighboringCountries(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class FileParser.
     */
    @Test
    public void testInit() throws Exception
    {
        System.out.println("init");
        Scanner input = null;
        FileParser instance = new FileParser();
        boolean expResult = false;
        boolean result = instance.init(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
