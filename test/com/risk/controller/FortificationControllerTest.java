/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.map.Country;
import com.risk.model.player.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class FortificationControllerTest {
    
    public FortificationControllerTest()
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
     * Test of initialize method, of class FortificationController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL arg0 = null;
        ResourceBundle arg1 = null;
        FortificationController instance = new FortificationController();
        instance.initialize(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateView method, of class FortificationController.
     */
    @Test
    public void testUpdateView()
    {
        System.out.println("updateView");
        FortificationController instance = new FortificationController();
        instance.updateView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of territoryHandler method, of class FortificationController.
     */
    @Test
    public void testTerritoryHandler()
    {
        System.out.println("territoryHandler");
        FortificationController instance = new FortificationController();
        instance.territoryHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjacentHandler method, of class FortificationController.
     */
    @Test
    public void testAdjacentHandler()
    {
        System.out.println("adjacentHandler");
        FortificationController instance = new FortificationController();
        instance.adjacentHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveHandler method, of class FortificationController.
     */
    @Test
    public void testMoveHandler()
    {
        System.out.println("moveHandler");
        FortificationController instance = new FortificationController();
        instance.moveHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountriesArrivedbyPath method, of class FortificationController.
     */
    @Test
    public void testGetCountriesArrivedbyPath()
    {
        System.out.println("getCountriesArrivedbyPath");
        Country country = null;
        Country firstCountry = null;
        ArrayList<Country> countries = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = FortificationController.getCountriesArrivedbyPath(country, firstCountry, countries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCountryDuplicated method, of class FortificationController.
     */
    @Test
    public void testIsCountryDuplicated()
    {
        System.out.println("isCountryDuplicated");
        Country country = null;
        Country firstCountry = null;
        ArrayList<Country> countries = null;
        boolean expResult = false;
        boolean result = FortificationController.isCountryDuplicated(country, firstCountry, countries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAnyCountriesConnected method, of class FortificationController.
     */
    @Test
    public void testIsAnyCountriesConnected()
    {
        System.out.println("isAnyCountriesConnected");
        Player p = null;
        boolean expResult = false;
        boolean result = FortificationController.isAnyCountriesConnected(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
