/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.map;

import com.risk.model.player.Player;
import java.util.ArrayList;
import javafx.collections.ObservableList;
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
public class CountryTest {
    
    public CountryTest()
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
     * Test of setContinent method, of class Country.
     */
    @Test
    public void testSetContinent()
    {
        System.out.println("setContinent");
        Continent continent = null;
        Country instance = null;
        instance.setContinent(continent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Country.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Country instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContinent method, of class Country.
     */
    @Test
    public void testGetContinent()
    {
        System.out.println("getContinent");
        Country instance = null;
        Continent expResult = null;
        Continent result = instance.getContinent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArmyCount method, of class Country.
     */
    @Test
    public void testGetArmyCount()
    {
        System.out.println("getArmyCount");
        Country instance = null;
        int expResult = 0;
        int result = instance.getArmyCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArmyCount method, of class Country.
     */
    @Test
    public void testSetArmyCount()
    {
        System.out.println("setArmyCount");
        int armyCount = 0;
        Country instance = null;
        instance.setArmyCount(armyCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceArmyCount method, of class Country.
     */
    @Test
    public void testReduceArmyCount()
    {
        System.out.println("reduceArmyCount");
        int armyCount = 0;
        Country instance = null;
        instance.reduceArmyCount(armyCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIsOccupied method, of class Country.
     */
    @Test
    public void testIsIsOccupied()
    {
        System.out.println("isIsOccupied");
        Country instance = null;
        boolean expResult = false;
        boolean result = instance.isIsOccupied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsOccupied method, of class Country.
     */
    @Test
    public void testSetIsOccupied()
    {
        System.out.println("setIsOccupied");
        boolean isOccupied = false;
        Country instance = null;
        instance.setIsOccupied(isOccupied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuler method, of class Country.
     */
    @Test
    public void testGetRuler()
    {
        System.out.println("getRuler");
        Country instance = null;
        Player expResult = null;
        Player result = instance.getRuler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRuler method, of class Country.
     */
    @Test
    public void testSetRuler()
    {
        System.out.println("setRuler");
        Player ruler = null;
        Country instance = null;
        instance.setRuler(ruler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectedCountries method, of class Country.
     */
    @Test
    public void testGetConnectedCountries()
    {
        System.out.println("getConnectedCountries");
        Country instance = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = instance.getConnectedCountries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConnectedCountries method, of class Country.
     */
    @Test
    public void testSetConnectedCountries()
    {
        System.out.println("setConnectedCountries");
        ArrayList<Country> connectedCountries = null;
        Country instance = null;
        instance.setConnectedCountries(connectedCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectedCountry method, of class Country.
     */
    @Test
    public void testGetConnectedCountry()
    {
        System.out.println("getConnectedCountry");
        String name = "";
        Country instance = null;
        Country expResult = null;
        Country result = instance.getConnectedCountry(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectedOwned method, of class Country.
     */
    @Test
    public void testGetConnectedOwned()
    {
        System.out.println("getConnectedOwned");
        Country instance = null;
        ObservableList<Country> expResult = null;
        ObservableList<Country> result = instance.getConnectedOwned();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectedEnemy method, of class Country.
     */
    @Test
    public void testGetConnectedEnemy()
    {
        System.out.println("getConnectedEnemy");
        Country instance = null;
        ObservableList<Country> expResult = null;
        ObservableList<Country> result = instance.getConnectedEnemy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
