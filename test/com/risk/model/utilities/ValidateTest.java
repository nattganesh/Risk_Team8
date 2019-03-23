/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.utilities;

import com.risk.model.map.Country;
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
public class ValidateTest {
    
    public ValidateTest()
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
     * Test of getValidateSize method, of class Validate.
     */
    @Test
    public void testGetValidateSize()
    {
        System.out.println("getValidateSize");
        Validate instance = new Validate();
        int expResult = 0;
        int result = instance.getValidateSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of continentChecks method, of class Validate.
     */
    @Test
    public void testContinentChecks() throws Exception
    {
        System.out.println("continentChecks");
        Validate instance = new Validate();
        instance.continentChecks();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mapConnected method, of class Validate.
     */
    @Test
    public void testMapConnected()
    {
        System.out.println("mapConnected");
        Country origin = null;
        Validate instance = new Validate();
        instance.mapConnected(origin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateMap method, of class Validate.
     */
    @Test
    public void testValidateMap() throws Exception
    {
        System.out.println("validateMap");
        Validate instance = new Validate();
        instance.validateMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidate method, of class Validate.
     */
    @Test
    public void testGetValidate()
    {
        System.out.println("getValidate");
        Validate expResult = null;
        Validate result = Validate.getValidate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
