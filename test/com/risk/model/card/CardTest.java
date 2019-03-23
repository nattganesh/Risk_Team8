/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.card;

import com.risk.model.player.Player;
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
public class CardTest {
    
    public CardTest()
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
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Card instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCatagory method, of class Card.
     */
    @Test
    public void testGetCatagory()
    {
        System.out.println("getCatagory");
        Card instance = null;
        String expResult = "";
        String result = instance.getCatagory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwner method, of class Card.
     */
    @Test
    public void testGetOwner()
    {
        System.out.println("getOwner");
        Card instance = null;
        Player expResult = null;
        Player result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwner method, of class Card.
     */
    @Test
    public void testSetOwner()
    {
        System.out.println("setOwner");
        Player player = null;
        Card instance = null;
        instance.setOwner(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCard method, of class Card.
     */
    @Test
    public void testRemoveCard()
    {
        System.out.println("removeCard");
        Player player = null;
        Card instance = null;
        instance.removeCard(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
