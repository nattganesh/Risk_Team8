/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class InitializationControllerTest {
    
    public InitializationControllerTest()
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
     * Test of initialize method, of class InitializationController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        InitializationController instance = null;
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayers method, of class InitializationController.
     */
    @Test
    public void testSetPlayers()
    {
        System.out.println("setPlayers");
        int numberOfPlayer = 0;
        InitializationController instance = null;
        instance.setPlayers(numberOfPlayer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of StartGame method, of class InitializationController.
     */
    @Test
    public void testStartGame() throws Exception
    {
        System.out.println("StartGame");
        ActionEvent event = null;
        InitializationController instance = null;
        instance.StartGame(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determinePlayersStartingOrder method, of class InitializationController.
     */
    @Test
    public void testDeterminePlayersStartingOrder()
    {
        System.out.println("determinePlayersStartingOrder");
        InitializationController instance = null;
        instance.determinePlayersStartingOrder();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignCountriesToPlayers method, of class InitializationController.
     */
    @Test
    public void testAssignCountriesToPlayers()
    {
        System.out.println("assignCountriesToPlayers");
        InitializationController instance = null;
        instance.assignCountriesToPlayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeCurrentPlayer method, of class InitializationController.
     */
    @Test
    public void testInitializeCurrentPlayer()
    {
        System.out.println("initializeCurrentPlayer");
        InitializationController instance = null;
        instance.initializeCurrentPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcStartingArmies method, of class InitializationController.
     */
    @Test
    public void testCalcStartingArmies()
    {
        System.out.println("calcStartingArmies");
        InitializationController instance = null;
        instance.calcStartingArmies();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcStartingArmiesHelper method, of class InitializationController.
     */
    @Test
    public void testCalcStartingArmiesHelper()
    {
        System.out.println("calcStartingArmiesHelper");
        int getPlayerSize = 0;
        InitializationController instance = null;
        int expResult = 0;
        int result = instance.calcStartingArmiesHelper(getPlayerSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
