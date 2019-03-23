/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.map.Country;
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
public class HumanPlayerTest {
    
    public HumanPlayerTest()
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
     * Test of attack method, of class HumanPlayer.
     */
    @Test
    public void testAttack()
    {
        System.out.println("attack");
        Country attack = null;
        Country defend = null;
        int caseType = 0;
        HumanPlayer instance = null;
        instance.attack(attack, defend, caseType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reinforce method, of class HumanPlayer.
     */
    @Test
    public void testReinforce()
    {
        System.out.println("reinforce");
        Country myCountry = null;
        int Armyinput = 0;
        HumanPlayer instance = null;
        instance.reinforce(myCountry, Armyinput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fortify method, of class HumanPlayer.
     */
    @Test
    public void testFortify()
    {
        System.out.println("fortify");
        Country from = null;
        Country to = null;
        int Armyinput = 0;
        HumanPlayer instance = null;
        instance.fortify(from, to, Armyinput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountriesArrivedbyPath method, of class HumanPlayer.
     */
    @Test
    public void testGetCountriesArrivedbyPath()
    {
        System.out.println("getCountriesArrivedbyPath");
        Country country = null;
        Country firstCountry = null;
        ArrayList<Country> countries = null;
        HumanPlayer instance = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = instance.getCountriesArrivedbyPath(country, firstCountry, countries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCountryDuplicated method, of class HumanPlayer.
     */
    @Test
    public void testIsCountryDuplicated()
    {
        System.out.println("isCountryDuplicated");
        Country country = null;
        Country firstCountry = null;
        ArrayList<Country> countries = null;
        HumanPlayer instance = null;
        boolean expResult = false;
        boolean result = instance.isCountryDuplicated(country, firstCountry, countries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAnyCountriesConnected method, of class HumanPlayer.
     */
    @Test
    public void testIsAnyCountriesConnected()
    {
        System.out.println("isAnyCountriesConnected");
        HumanPlayer instance = null;
        boolean expResult = false;
        boolean result = instance.isAnyCountriesConnected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
