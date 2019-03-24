/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import com.risk.model.player.Player;
import java.util.ArrayList;
import java.util.Observable;
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
public class PlayerPhaseModelTest {
    
    public PlayerPhaseModelTest()
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
     * Test of setWinner method, of class PlayerPhaseModel.
     */
    @Test
    public void testSetWinner()
    {
        System.out.println("setWinner");
        Player player = null;
        PlayerPhaseModel instance = null;
        Player expResult = null;
        Player result = instance.setWinner(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerIndex method, of class PlayerPhaseModel.
     */
    @Test
    public void testSetPlayerIndex()
    {
        System.out.println("setPlayerIndex");
        int increment = 0;
        PlayerPhaseModel instance = null;
        instance.setPlayerIndex(increment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerIndex method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayerIndex()
    {
        System.out.println("getPlayerIndex");
        PlayerPhaseModel instance = null;
        int expResult = 0;
        int result = instance.getPlayerIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetNumberOfPlayer()
    {
        System.out.println("getNumberOfPlayer");
        PlayerPhaseModel instance = null;
        int expResult = 0;
        int result = instance.getNumberOfPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetCurrentPlayer()
    {
        System.out.println("getCurrentPlayer");
        PlayerPhaseModel instance = null;
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testAddPlayer()
    {
        System.out.println("addPlayer");
        Player player = null;
        PlayerPhaseModel instance = null;
        instance.addPlayer(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayers method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayers()
    {
        System.out.println("getPlayers");
        PlayerPhaseModel instance = null;
        ArrayList<Player> expResult = null;
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerModel method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayerModel()
    {
        System.out.println("getPlayerModel");
        PlayerPhaseModel expResult = null;
        PlayerPhaseModel result = PlayerPhaseModel.getPlayerModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PlayerPhaseModel.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Observable o = null;
        Object country = null;
        PlayerPhaseModel instance = null;
        instance.update(o, country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
