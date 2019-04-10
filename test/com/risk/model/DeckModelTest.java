/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import com.risk.model.card.Card;
import com.risk.model.player.Player;

import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for deck model
 * 
 * @author Natt
 * @author Tianyi
 */
public class DeckModelTest 
{
    private DeckModel deckModel;
	private Card cards[];
	private Player p;
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
    	deckModel = new DeckModel();
    	p = new Player("Green");
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of initialize method, of class DeckModel.
     */
    @Test
    public void testInitialize()
    {
    	int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		deckModel.initialize();
		cards = deckModel.getCards();
		for (int i = 0; i < cards.length; i++) 
		{
			switch (cards[i].getCatagory()) 
			{
			case "Infantry":
				n1++;
				break;
			case "Cavalry":
				n2++;
				break;
			case "Artillery":
				n3++;
				break;
			case "Wild":
				n4++;
				break;
			}
		}
		int expResult1 = 14;
		int expResult2 = 2;
		assertEquals(expResult1, n1);
		assertEquals(expResult1, n2);
		assertEquals(expResult1, n3);
		assertEquals(expResult2, n4);
    }

    /**
     * Test of sendCard method, of class DeckModel.
     */
    @Test
    public void testSendCard()
    {
    	deckModel.initialize();
    	Card result = deckModel.sendCard(p);
		ObservableList<Card> resultList = p.getCards();
		Player resultPlayer = result.getOwner();
		assertEquals(p, resultPlayer);
		assertTrue(resultList.contains(result));
    }
    
}
