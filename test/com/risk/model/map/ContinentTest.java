/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.map;

import com.risk.model.player.Player;
import java.util.ArrayList;
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
public class ContinentTest {
    
    public ContinentTest()
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
     * Test of getName method, of class Continent.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Continent instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountries method, of class Continent.
     */
    @Test
    public void testGetCountries()
    {
        System.out.println("getCountries");
        Continent instance = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = instance.getCountries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRuler method, of class Continent.
     */
    @Test
    public void testSetRuler()
    {
        System.out.println("setRuler");
        Player p = null;
        Continent instance = null;
        instance.setRuler(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuler method, of class Continent.
     */
    @Test
    public void testGetRuler()
    {
        System.out.println("getRuler");
        Continent instance = null;
        Player expResult = null;
        Player result = instance.getRuler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class Continent.
     */
    @Test
    public void testSetCountry()
    {
        System.out.println("setCountry");
        Country country = null;
        Continent instance = null;
        instance.setCountry(country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsWhenFullyOccupied method, of class Continent.
     */
    @Test
    public void testGetPointsWhenFullyOccupied()
    {
        System.out.println("getPointsWhenFullyOccupied");
        Continent instance = null;
        int expResult = 0;
        int result = instance.getPointsWhenFullyOccupied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsWhenFullyOccupied method, of class Continent.
     */
    @Test
    public void testSetPointsWhenFullyOccupied()
    {
        System.out.println("setPointsWhenFullyOccupied");
        int pointsWhenFullyOccupied = 0;
        Continent instance = null;
        instance.setPointsWhenFullyOccupied(pointsWhenFullyOccupied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
