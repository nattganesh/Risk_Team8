/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

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
 */
public class ActionModelTest {
    
    public ActionModelTest()
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
     * Test of addAction method, of class ActionModel.
     */
    @Test
    public void testAddAction()
    {
        System.out.println("addAction");
        String action = "";
        ActionModel instance = new ActionModel();
        instance.addAction(action);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearAction method, of class ActionModel.
     */
    @Test
    public void testClearAction()
    {
        System.out.println("clearAction");
        ActionModel instance = new ActionModel();
        instance.clearAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActions method, of class ActionModel.
     */
    @Test
    public void testGetActions()
    {
        System.out.println("getActions");
        ActionModel instance = new ActionModel();
        ObservableList<String> expResult = null;
        ObservableList<String> result = instance.getActions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActionModel method, of class ActionModel.
     */
    @Test
    public void testGetActionModel()
    {
        System.out.println("getActionModel");
        ActionModel expResult = null;
        ActionModel result = ActionModel.getActionModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
