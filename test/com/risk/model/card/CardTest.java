/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.card;

import com.risk.model.player.Player;

import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for cards
 * 
 * @author Natt
 * @author Tianyi
 */
public class CardTest {
    private Card card;
    private Card card2;
    private Player p;
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
    	card = new Card("Wild");
    	card2 = new Card("Infantry");
    	p = new Player("Green");
    	card.setOwner(p);
    	p.addCard(card);
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
        String expResult = card.getCatagory()+" "+card.getOwner();
        String result = card.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCatagory method, of class Card.
     */
    @Test
    public void testGetCatagory()
    {
        String expResult = "Wild";
        String result = card.getCatagory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOwner method, of class Card.
     */
    @Test
    public void testGetOwner()
    {
        Player result = card.getOwner();
        assertEquals(p, result);
    }

    /**
     * Test of setOwner method, of class Card.
     */
    @Test
    public void testSetOwner()
    {
        card2.setOwner(p);
        Player result = card2.getOwner();
        assertEquals(p, result);
    }

    
}
