/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class SetUpControllerTest {
    
    public SetUpControllerTest()
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
     * Test of initialize method, of class SetUpController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle resourceBundle = null;
        SetUpController instance = new SetUpController();
        instance.initialize(url, resourceBundle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArmy method, of class SetUpController.
     */
    @Test
    public void testSetArmy()
    {
        System.out.println("setArmy");
        SetUpController instance = new SetUpController();
        instance.setArmy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArmies method, of class SetUpController.
     */
    @Test
    public void testGetArmies()
    {
        System.out.println("getArmies");
        SetUpController instance = new SetUpController();
        int expResult = 0;
        int result = instance.getArmies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartingPoints method, of class SetUpController.
     */
    @Test
    public void testSetStartingPoints()
    {
        System.out.println("setStartingPoints");
        SetUpController instance = new SetUpController();
        instance.setStartingPoints();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfEachCountryHasOneArmy method, of class SetUpController.
     */
    @Test
    public void testCheckIfEachCountryHasOneArmy()
    {
        System.out.println("checkIfEachCountryHasOneArmy");
        SetUpController instance = new SetUpController();
        boolean expResult = false;
        boolean result = instance.checkIfEachCountryHasOneArmy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class SetUpController.
     */
    @Test
    public void testNext() throws Exception
    {
        System.out.println("next");
        ActionEvent event = null;
        SetUpController instance = new SetUpController();
        instance.next(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderView method, of class SetUpController.
     */
    @Test
    public void testRenderView()
    {
        System.out.println("renderView");
        SetUpController instance = new SetUpController();
        instance.updateView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
