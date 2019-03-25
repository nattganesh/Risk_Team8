/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.map.Country;
import java.net.URL;
import java.util.Observable;
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
public class AttackControllerTest {
    
    public AttackControllerTest()
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
     * Test of initialize method, of class AttackController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL arg0 = null;
        ResourceBundle arg1 = null;
        AttackController instance = new AttackController();
        instance.initialize(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateView method, of class AttackController.
     */
    @Test
    public void testUpdateView()
    {
        System.out.println("updateView");
        AttackController instance = new AttackController();
        instance.updateView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of territoryHandler method, of class AttackController.
     */
    @Test
    public void testTerritoryHandler()
    {
        System.out.println("territoryHandler");
        AttackController instance = new AttackController();
        instance.territoryHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearDiceRolls method, of class AttackController.
     */
    @Test
    public void testClearDiceRolls()
    {
        System.out.println("clearDiceRolls");
        AttackController instance = new AttackController();
        instance.clearDiceRolls();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjacentEnemyHandler method, of class AttackController.
     */
    @Test
    public void testAdjacentEnemyHandler()
    {
        System.out.println("adjacentEnemyHandler");
        AttackController instance = new AttackController();
        instance.adjacentEnemyHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goToFortificationPhase method, of class AttackController.
     */
    @Test
    public void testGoToFortificationPhase_0args()
    {
        System.out.println("goToFortificationPhase");
        AttackController instance = new AttackController();
        instance.goToFortificationPhase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeDice method, of class AttackController.
     */
    @Test
    public void testInitializeDice()
    {
        System.out.println("initializeDice");
        AttackController instance = new AttackController();
        instance.initializeDice();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of rollDiceHandler method, of class AttackController.
     */
    @Test
    public void testRollDiceHandler()
    {
        System.out.println("rollDiceHandler");
        AttackController instance = new AttackController();
        instance.rollDiceHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollDice method, of class AttackController.
     */
    @Test
    public void testRollDice()
    {
        System.out.println("rollDice");
        int diceattack = 0;
        int dicedefend = 0;
        Country attackingCountry = null;
        Country defendingCountry = null;
        AttackController instance = new AttackController();
        instance.rollDice(diceattack, dicedefend, attackingCountry, defendingCountry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AllOut method, of class AttackController.
     */
    @Test
    public void testAllOut()
    {
        System.out.println("AllOut");
        AttackController instance = new AttackController();
        instance.AllOut();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of goToFortificationPhase method, of class AttackController.
     */
    @Test
    public void testGoToFortificationPhase_ActionEvent()
    {
        System.out.println("goToFortificationPhase");
        ActionEvent event = null;
        AttackController instance = new AttackController();
        instance.goToFortificationPhase(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AttackController.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        AttackController instance = new AttackController();
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
