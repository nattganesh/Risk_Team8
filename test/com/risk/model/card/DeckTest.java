/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.card;

import com.risk.model.player.Player;
import java.util.LinkedList;
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
public class DeckTest {
    
    public DeckTest()
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
     * Test of initialize method, of class Deck.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        Deck instance = new Deck();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCards method, of class Deck.
     */
    @Test
    public void testGetCards()
    {
        System.out.println("getCards");
        LinkedList<Card> expResult = null;
        LinkedList<Card> result = Deck.getCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shuffleCard method, of class Deck.
     */
    @Test
    public void testShuffleCard()
    {
        System.out.println("shuffleCard");
        Deck instance = new Deck();
        instance.shuffleCard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendCard method, of class Deck.
     */
    @Test
    public void testSendCard()
    {
        System.out.println("sendCard");
        Player player = null;
        Deck instance = new Deck();
        instance.sendCard(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
