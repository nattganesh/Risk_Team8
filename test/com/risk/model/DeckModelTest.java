/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

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
public class DeckModelTest {
    
    public DeckModelTest()
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
     * Test of getCardModel method, of class DeckModel.
     */
    @Test
    public void testGetCardModel()
    {
        System.out.println("getCardModel");
        DeckModel expResult = null;
        DeckModel result = DeckModel.getCardModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class DeckModel.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        DeckModel instance = new DeckModel();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendCard method, of class DeckModel.
     */
    @Test
    public void testSendCard()
    {
        System.out.println("sendCard");
        Player player = null;
        DeckModel instance = new DeckModel();
        instance.sendCard(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
