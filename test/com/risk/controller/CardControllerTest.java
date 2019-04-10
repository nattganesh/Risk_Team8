/**
 * This is test for card controller
 * 
 * @author DKM
 */
package com.risk.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CardControllerTest 
{
	private Player p;
	private ObservableList<Card> cards = FXCollections.observableArrayList();

	@Before
	public void setUp() 
	{
		p = new Player("Green");
	}
	
	@Test
	public void testCardControllerConstructor()
	{
		PlayerPhaseModel.getPlayerModel().addPlayer(new Player("dummy1"));
    	CardController cardController = new CardController();
    	assertNotNull(cardController);
	}

	@Test
	public void testExchangeCardForComputer() 
	{
		Card c1 = new Card("Infantry");
		Card c2 = new Card("Wild");
		Card c3 = new Card("Infantry");
		Card c4 = new Card("Infantry");
		Card c5 = new Card("Cavalry");
		c1.setOwner(p);
		c2.setOwner(p);
		c3.setOwner(p);
		c4.setOwner(p);
		c5.setOwner(p);
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		p.setCards(cards);
		ObservableList<Card> expResult = FXCollections.observableArrayList(c3, c4);
		p.exchangeCardForComputer();
		ObservableList<Card> result = p.getCards();
		assertEquals(expResult.size(), result.size());
		for (int i = 0; i < expResult.size(); i++) 
		{
			Card t1 = expResult.get(i);
			Card t2 = result.get(i);
			assertEquals(t1.getCatagory(), t2.getCatagory());
		}
	}
	
	@Test
	public void testExchangeCardForComputer1() 
	{
		Card c1 = new Card("Infantry");
		Card c2 = new Card("Cavalry");
		Card c3 = new Card("Infantry");
		Card c4 = new Card("Infantry");
		Card c5 = new Card("Cavalry");
		c1.setOwner(p);
		c2.setOwner(p);
		c3.setOwner(p);
		c4.setOwner(p);
		c5.setOwner(p);
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		p.setCards(cards);
		ObservableList<Card> expResult = FXCollections.observableArrayList(c2, c5);
		p.exchangeCardForComputer();
		ObservableList<Card> result = p.getCards();
		assertEquals(expResult.size(), result.size());
		for (int i = 0; i < expResult.size(); i++) 
		{
			Card t1 = expResult.get(i);
			Card t2 = result.get(i);
			assertEquals(t1.getCatagory(), t2.getCatagory());
		}
	}
}
