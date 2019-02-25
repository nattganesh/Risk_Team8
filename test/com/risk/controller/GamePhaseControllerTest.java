/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import java.util.Observable;
import javafx.stage.Stage;
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
        GamePhaseController instance = null;
        instance.update(o, phase);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUp method, of class GamePhaseController.
     */
    @Test
    public void testSetUp() throws Exception
    {
        System.out.println("setUp");
        Stage primaryStage = null;
        GamePhaseController instance = null;
        instance.setUp(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
