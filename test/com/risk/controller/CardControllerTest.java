/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.card.Card;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
 * @author Tianyi
 */
public class CardControllerTest {
	private CardController instance;
    public CardControllerTest()
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
    	 instance = new CardController();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of renderView method, of class CardController.
     */
    @Test
    public void testRenderView()
    {
        System.out.println("renderView");
        CardController instance = new CardController();
        instance.renderView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class CardController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        instance = new CardController();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of yourCardHandler method, of class CardController.
     */
    @Test
    public void testYourCardHandler()
    {
        System.out.println("yourCardHandler");
        CardController instance = new CardController();
        instance.yourCardHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of yourTradeHandler method, of class CardController.
     */
    @Test
    public void testYourTradeHandler()
    {
        System.out.println("yourTradeHandler");
        CardController instance = new CardController();
        instance.yourTradeHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tradeCard method, of class CardController.
     */
    @Test
    public void testTradeCard()
    {
        System.out.println("tradeCard");
        CardController instance = new CardController();
        instance.tradeCard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of skipExchangeHandler method, of class CardController.
     */
    @Test
    public void testSkipExchangeHandler()
    {
        System.out.println("skipExchangeHandler");
        CardController instance = new CardController();
        instance.skipExchangeHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cardValidation method, of class CardController.
     */
    @Test
    public void testCardValidation1()
    {
        System.out.println("cardValidation");
        ObservableList<Card> selectedCards = FXCollections.observableArrayList(new Card("Infantry"), new Card("Cavalry"), new Card("Artillery"));
        boolean expResult = true;
        boolean result = instance.cardValidation(selectedCards);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCardValidation2()
    {
        System.out.println("cardValidation");
        ObservableList<Card> selectedCards = FXCollections.observableArrayList(new Card("Infantry"), new Card("Infantry"), new Card("Infantry"));
        boolean expResult = true;
        boolean result = instance.cardValidation(selectedCards);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCardValidation3()
    {
        System.out.println("cardValidation");
        ObservableList<Card> selectedCards = FXCollections.observableArrayList(new Card("Infantry"), new Card("Infantry"), new Card("Artillery"));
        boolean expResult = false;
        boolean result = instance.cardValidation(selectedCards);
        assertEquals(expResult, result);
    }
    
}
