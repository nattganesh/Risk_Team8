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
public class ReinforcementControllerTest {
    
    public ReinforcementControllerTest()
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
     * Test of initialize method, of class ReinforcementController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle resourceBundle = null;
        ReinforcementController instance = new ReinforcementController();
        instance.initialize(url, resourceBundle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of territoryHandler method, of class ReinforcementController.
     */
    @Test
    public void testTerritoryHandler()
    {
        System.out.println("territoryHandler");
        ReinforcementController instance = new ReinforcementController();
        instance.territoryHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArmy method, of class ReinforcementController.
     */
    @Test
    public void testSetArmy()
    {
        System.out.println("setArmy");
        ReinforcementController instance = new ReinforcementController();
        instance.setArmy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goToAttackPhase method, of class ReinforcementController.
     */
    @Test
    public void testGoToAttackPhase() throws Exception
    {
        System.out.println("goToAttackPhase");
        ActionEvent event = null;
        ReinforcementController instance = new ReinforcementController();
        instance.goToAttackPhase(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateView method, of class ReinforcementController.
     */
    @Test
    public void testUpdateView()
    {
        System.out.println("updateView");
        ReinforcementController instance = new ReinforcementController();
        instance.updateView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
