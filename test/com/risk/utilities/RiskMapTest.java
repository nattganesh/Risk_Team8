/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.utilities;

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
public class RiskMapTest {
    
    public RiskMapTest()
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
     * Test of getParser method, of class RiskMap.
     */
    @Test
    public void testGetParser()
    {
        System.out.println("getParser");
        RiskMap instance = null;
        FileParser expResult = null;
        FileParser result = instance.getParser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFile method, of class RiskMap.
     */
    @Test
    public void testParseFile() throws Exception
    {
        System.out.println("parseFile");
        RiskMap instance = null;
        instance.parseFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateMap method, of class RiskMap.
     */
    @Test
    public void testValidateMap() throws Exception
    {
        System.out.println("validateMap");
        RiskMap instance = null;
        instance.validateMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUp method, of class RiskMap.
     */
    @Test
    public void testSetUp() throws Exception
    {
        System.out.println("setUp");
        RiskMap instance = null;
        instance.setUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
