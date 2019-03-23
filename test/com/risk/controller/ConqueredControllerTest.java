/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.map.Country;
import java.net.URL;
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
public class ConqueredControllerTest {
    
    public ConqueredControllerTest()
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
     * Test of initialize method, of class ConqueredController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL arg0 = null;
        ResourceBundle arg1 = null;
        ConqueredController instance = new ConqueredController();
        instance.initialize(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveArmyHandler method, of class ConqueredController.
     */
    @Test
    public void testMoveArmyHandler()
    {
        System.out.println("moveArmyHandler");
        ConqueredController instance = new ConqueredController();
        instance.moveArmyHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveComplete method, of class ConqueredController.
     */
    @Test
    public void testMoveComplete()
    {
        System.out.println("moveComplete");
        ConqueredController instance = new ConqueredController();
        instance.moveComplete();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConquringArmy method, of class ConqueredController.
     */
    @Test
    public void testSetConquringArmy()
    {
        System.out.println("setConquringArmy");
        Country c = null;
        ConqueredController instance = new ConqueredController();
        instance.setConquringArmy(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiceRoll method, of class ConqueredController.
     */
    @Test
    public void testSetDiceRoll()
    {
        System.out.println("setDiceRoll");
        int roll = 0;
        ConqueredController instance = new ConqueredController();
        instance.setDiceRoll(roll);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderView method, of class ConqueredController.
     */
    @Test
    public void testRenderView()
    {
        System.out.println("renderView");
        ConqueredController instance = new ConqueredController();
        instance.renderView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
