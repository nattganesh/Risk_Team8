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
        ReinforcementController instance = null;
        instance.initialize(url, resourceBundle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeArmyField method, of class ReinforcementController.
     */
    @Test
    public void testInitializeArmyField()
    {
        System.out.println("initializeArmyField");
        ReinforcementController instance = null;
        instance.initializeArmyField();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeTerritory method, of class ReinforcementController.
     */
    @Test
    public void testInitializeTerritory()
    {
        System.out.println("initializeTerritory");
        ReinforcementController instance = null;
        instance.initializeTerritory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReinforcement method, of class ReinforcementController.
     */
    @Test
    public void testGetReinforcement()
    {
        System.out.println("getReinforcement");
        ReinforcementController instance = null;
        int expResult = 0;
        int result = instance.getReinforcement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ReinforcementController.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        ReinforcementController instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
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
        ReinforcementController instance = null;
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
        ReinforcementController instance = null;
        instance.goToAttackPhase(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
