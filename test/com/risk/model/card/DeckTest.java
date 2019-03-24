/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.card;

import com.risk.model.player.Player;

import javafx.collections.ObservableList;

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
 * @author Tianyi
 */
public class DeckTest 
{
	private Deck deck;
	private Card card[];
	private Card cards[];
	private int n = 44;
	private Player p;

	public DeckTest() 
	{
	}

	@Before
	public void setUp() {
		deck = new Deck();
		p = new Player("Green");
	}

	@After
	public void tearDown() {

	}

	/**
	 * Test of initialize method, of class Deck.
	 */
	@Test
	public void testInitialize() 
	{
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		deck.initialize();
		cards = deck.getCards();
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
	 * Test of getCards method, of class Deck.
	 */
	@Test
	public void testGetCards() 
	{
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		deck.initialize();
		Card cards[] = deck.getCards();
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
	 * Test of shuffleCard method, of class Deck.
	 */
	@Test
	public void testShuffleCard() 
	{
		String category[] = { "Infantry", "Cavalry", "Artillery", "Wild" };
		card = new Card[n];
		for (int i = 0; i < card.length; i++) 
		{
			if (i < 14) 
			{
				card[i] = new Card(category[0]);
			} else if (i < 28) 
			{
				card[i] = new Card(category[1]);
			} else if (i < 42) 
			{
				card[i] = new Card(category[2]);
			} else 
			{
				card[i] = new Card(category[3]);
			}
		}
		assertFalse(card.equals(cards));
	}

	/**
	 * Test of sendCard method, of class Deck.
	 */
	@Test
	public void testSendCard() 
	{
		Card result = deck.sendCard(p);
		ObservableList<Card> resultList = p.getCards();
		Player resultPlayer = result.getOwner();
		assertEquals(p, resultPlayer);
		assertTrue(resultList.contains(result));
	}

}
