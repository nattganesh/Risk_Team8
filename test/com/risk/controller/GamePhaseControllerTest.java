/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
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
public class GamePhaseControllerTest {
    
    public GamePhaseControllerTest()
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
     * Test of update method, of class GamePhaseController.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Observable o = null;
        Object phase = null;
        GamePhaseController instance = new GamePhaseController();
        instance.update(o, phase);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateContinentDominationView method, of class GamePhaseController.
     */
    @Test
    public void testUpdateContinentDominationView()
    {
        System.out.println("updateContinentDominationView");
        GamePhaseController instance = new GamePhaseController();
        instance.updateContinentDominationView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class GamePhaseController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL arg0 = null;
        ResourceBundle arg1 = null;
        GamePhaseController instance = new GamePhaseController();
        instance.initialize(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
