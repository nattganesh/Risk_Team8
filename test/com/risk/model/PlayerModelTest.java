/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import com.risk.army.Player;
import com.risk.map.Country;
import java.util.ArrayList;
import java.util.Observable;
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
public class PlayerModelTest {
    
    public PlayerModelTest()
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
     * Test of setWinner method, of class PlayerModel.
     */
    @Test
    public void testSetWinner()
    {
        System.out.println("setWinner");
        Player player = null;
        PlayerModel instance = new PlayerModel();
        Player expResult = null;
        Player result = instance.setWinner(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IncrementPlayerIndex method, of class PlayerModel.
     */
    @Test
    public void testIncrementPlayerIndex()
    {
        System.out.println("IncrementPlayerIndex");
        PlayerModel instance = new PlayerModel();
        instance.IncrementPlayerIndex();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCurrentTerritory method, of class PlayerModel.
     */
    @Test
    public void testUpdateCurrentTerritory()
    {
        System.out.println("updateCurrentTerritory");
        PlayerModel instance = new PlayerModel();
        instance.updateCurrentTerritory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPlayer method, of class PlayerModel.
     */
    @Test
    public void testGetNextPlayer()
    {
        System.out.println("getNextPlayer");
        PlayerModel instance = new PlayerModel();
        Player expResult = null;
        Player result = instance.getNextPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentPlayerCountryObs method, of class PlayerModel.
     */
    @Test
    public void testSetCurrentPlayerCountryObs()
    {
        System.out.println("setCurrentPlayerCountryObs");
        PlayerModel instance = new PlayerModel();
        instance.setCurrentPlayerCountryObs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTerritory method, of class PlayerModel.
     */
    @Test
    public void testGetTerritory()
    {
        System.out.println("getTerritory");
        PlayerModel instance = new PlayerModel();
        ObservableList<Country> expResult = null;
        ObservableList<Country> result = instance.getTerritory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class PlayerModel.
     */
    @Test
    public void testGetCurrentPlayer()
    {
        System.out.println("getCurrentPlayer");
        PlayerModel instance = new PlayerModel();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayer method, of class PlayerModel.
     */
    @Test
    public void testAddPlayer()
    {
        System.out.println("addPlayer");
        Player player = null;
        PlayerModel instance = new PlayerModel();
        instance.addPlayer(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfPlayer method, of class PlayerModel.
     */
    @Test
    public void testGetNumberOfPlayer()
    {
        System.out.println("getNumberOfPlayer");
        PlayerModel instance = new PlayerModel();
        int expResult = 0;
        int result = instance.getNumberOfPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayers method, of class PlayerModel.
     */
    @Test
    public void testGetPlayers()
    {
        System.out.println("getPlayers");
        PlayerModel instance = new PlayerModel();
        ArrayList<Player> expResult = null;
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PlayerModel.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Observable arg0 = null;
        Object arg1 = null;
        PlayerModel instance = new PlayerModel();
        instance.update(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
