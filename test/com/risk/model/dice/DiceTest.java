/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.dice;

import com.risk.model.map.Country;
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
public class DiceTest {
    
    public DiceTest()
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
     * Test of roll method, of class Dice.
     */
    @Test
    public void testRoll()
    {
        System.out.println("roll");
        int expResult = 0;
        int result = Dice.roll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRollLimt method, of class Dice.
     */
    @Test
    public void testSetRollLimt()
    {
        System.out.println("setRollLimt");
        Country attack = null;
        Country defend = null;
        int[] expResult = null;
        int[] result = Dice.setRollLimt(attack, defend);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
