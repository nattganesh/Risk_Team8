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
public class FortificationControllerTest {
    
    public FortificationControllerTest()
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
     * Test of initialize method, of class FortificationController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL arg0 = null;
        ResourceBundle arg1 = null;
        FortificationController instance = null;
        instance.initialize(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onNextPlayer method, of class FortificationController.
     */
    @Test
    public void testOnNextPlayer() throws Exception
    {
        System.out.println("onNextPlayer");
        ActionEvent event = null;
        FortificationController instance = null;
        instance.onNextPlayer(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
