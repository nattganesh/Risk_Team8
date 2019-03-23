/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.model.card.Card;
import com.risk.model.map.Country;
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
     * Test of setTotalArmy method, of class Player.
     */
    @Test
    public void testSetTotalArmy()
    {
        System.out.println("setTotalArmy");
        int count = 0;
        Player instance = null;
        instance.setTotalArmy(count);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceTotalArmy method, of class Player.
     */
    @Test
    public void testReduceTotalArmy()
    {
        System.out.println("reduceTotalArmy");
        int count = 0;
        Player instance = null;
        instance.reduceTotalArmy(count);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalArmy method, of class Player.
     */
    @Test
    public void testGetTotalArmy()
    {
        System.out.println("getTotalArmy");
        Player instance = null;
        int expResult = 0;
        int result = instance.getTotalArmy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of getStartingPoints method, of class Player.
     */
    @Test
    public void testGetStartingPoints()
    {
        System.out.println("getStartingPoints");
        Player instance = null;
        int expResult = 0;
        int result = instance.getStartingPoints();
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
     * Test of addCountry method, of class Player.
     */
    @Test
    public void testAddCountry()
    {
        System.out.println("addCountry");
        Country country = null;
        Player instance = null;
        instance.addCountry(country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCountry method, of class Player.
     */
    @Test
    public void testRemoveCountry()
    {
        System.out.println("removeCountry");
        Country country = null;
        Player instance = null;
        instance.removeCountry(country);
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
     * Test of getStartingP method, of class Player.
     */
    @Test
    public void testGetStartingP()
    {
        System.out.println("getStartingP");
        Player instance = null;
        int expResult = 0;
        int result = instance.getStartingP();
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
        ObservableList<Card> expResult = null;
        ObservableList<Card> result = instance.getCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCard method, of class Player.
     */
    @Test
    public void testAddCard()
    {
        System.out.println("addCard");
        Card card = null;
        Player instance = null;
        instance.addCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCard method, of class Player.
     */
    @Test
    public void testRemoveCard()
    {
        System.out.println("removeCard");
        Card card = null;
        Player instance = null;
        instance.removeCard(card);
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
        ObservableList<Card> cards = null;
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
     * Test of attack method, of class Player.
     */
    @Test
    public void testAttack()
    {
        System.out.println("attack");
        Country attack = null;
        Country defend = null;
        int caseType = 0;
        Player instance = null;
        instance.attack(attack, defend, caseType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reinforce method, of class Player.
     */
    @Test
    public void testReinforce()
    {
        System.out.println("reinforce");
        Country myCountry = null;
        int Armyinput = 0;
        Player instance = null;
        instance.reinforce(myCountry, Armyinput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fortify method, of class Player.
     */
    @Test
    public void testFortify()
    {
        System.out.println("fortify");
        Country from = null;
        Country to = null;
        int Armyinput = 0;
        Player instance = null;
        instance.fortify(from, to, Armyinput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountriesArrivedbyPath method, of class Player.
     */
    @Test
    public void testGetCountriesArrivedbyPath()
    {
        System.out.println("getCountriesArrivedbyPath");
        Country country = null;
        Country firstCountry = null;
        ArrayList<Country> countries = null;
        Player instance = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = instance.getCountriesArrivedbyPath(country, firstCountry, countries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAnyCountriesConnected method, of class Player.
     */
    @Test
    public void testIsAnyCountriesConnected()
    {
        System.out.println("isAnyCountriesConnected");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isAnyCountriesConnected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlayerImpl extends Player {

        public PlayerImpl()
        {
            super("");
        }

        public void attack(Country attack, Country defend, int caseType)
        {
        }

        public void reinforce(Country myCountry, int Armyinput)
        {
        }

        public void fortify(Country from, Country to, int Armyinput)
        {
        }

        public ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry, ArrayList<Country> countries)
        {
            return null;
        }

        public boolean isAnyCountriesConnected()
        {
            return false;
        }
    }
    
}
