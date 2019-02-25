/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.army;

import com.risk.card.Card;
import com.risk.map.Country;
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
public class PlayerTest {
    
    public PlayerTest()
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
     * Test of setStartingPoints method, of class Player.
     */
    @Test
    public void testSetStartingPoints()
    {
        System.out.println("setStartingPoints");
        int i = 0;
        Player instance = null;
        instance.setStartingPoints(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReinforcementOccupiedTerritory method, of class Player.
     */
    @Test
    public void testGetReinforcementOccupiedTerritory()
    {
        System.out.println("getReinforcementOccupiedTerritory");
        Player instance = null;
        int expResult = 0;
        int result = instance.getReinforcementOccupiedTerritory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReinforcementContinentControl method, of class Player.
     */
    @Test
    public void testGetReinforcementContinentControl()
    {
        System.out.println("getReinforcementContinentControl");
        Player instance = null;
        int expResult = 0;
        int result = instance.getReinforcementContinentControl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReinforcementTradeInCard method, of class Player.
     */
    @Test
    public void testGetReinforcementTradeInCard()
    {
        System.out.println("getReinforcementTradeInCard");
        Player instance = null;
        int expResult = 0;
        int result = instance.getReinforcementTradeInCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateReinforcement method, of class Player.
     */
    @Test
    public void testCalculateReinforcement()
    {
        System.out.println("calculateReinforcement");
        Player instance = null;
        int expResult = 0;
        int result = instance.calculateReinforcement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReinforcement method, of class Player.
     */
    @Test
    public void testGetReinforcement()
    {
        System.out.println("getReinforcement");
        Player instance = null;
        int expResult = 0;
        int result = instance.getReinforcement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReinforcement method, of class Player.
     */
    @Test
    public void testSetReinforcement()
    {
        System.out.println("setReinforcement");
        int assign = 0;
        Player instance = null;
        int expResult = 0;
        int result = instance.setReinforcement(assign);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Player instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOccupiedCountries method, of class Player.
     */
    @Test
    public void testGetOccupiedCountries()
    {
        System.out.println("getOccupiedCountries");
        Player instance = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = instance.getOccupiedCountries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numbOccupied method, of class Player.
     */
    @Test
    public void testNumbOccupied()
    {
        System.out.println("numbOccupied");
        Player instance = null;
        int expResult = 0;
        int result = instance.numbOccupied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOccupiedCountries method, of class Player.
     */
    @Test
    public void testSetOccupiedCountries()
    {
        System.out.println("setOccupiedCountries");
        ArrayList<Country> occupiedCountries = null;
        Player instance = null;
        instance.setOccupiedCountries(occupiedCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class Player.
     */
    @Test
    public void testGetCountry()
    {
        System.out.println("getCountry");
        String name = "";
        Player instance = null;
        Country expResult = null;
        Country result = instance.getCountry(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCards method, of class Player.
     */
    @Test
    public void testGetCards()
    {
        System.out.println("getCards");
        Player instance = null;
        ArrayList<Card> expResult = null;
        ArrayList<Card> result = instance.getCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCards method, of class Player.
     */
    @Test
    public void testSetCards()
    {
        System.out.println("setCards");
        ArrayList<Card> cards = null;
        Player instance = null;
        instance.setCards(cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPlayerLost method, of class Player.
     */
    @Test
    public void testIsPlayerLost()
    {
        System.out.println("isPlayerLost");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isPlayerLost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerLost method, of class Player.
     */
    @Test
    public void testSetPlayerLost()
    {
        System.out.println("setPlayerLost");
        boolean playerLost = false;
        Player instance = null;
        instance.setPlayerLost(playerLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of armiesLeft method, of class Player.
     */
    @Test
    public void testArmiesLeft()
    {
        System.out.println("armiesLeft");
        Player instance = null;
        int expResult = 0;
        int result = instance.armiesLeft();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
