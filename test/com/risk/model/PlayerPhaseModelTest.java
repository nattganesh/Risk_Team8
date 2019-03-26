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
 * Test cases for player phase model
 * @author Natt
 * @author Tianyi
 */
public class PlayerPhaseModelTest {
    private PlayerPhaseModel PlayerPhaseModel;
    private Player p1;
    private Player p2;
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
    	PlayerPhaseModel = new PlayerPhaseModel();
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	PlayerPhaseModel.addPlayer(p1);
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
    	Player result = PlayerPhaseModel.setWinner(p1);
    	assertEquals(p1 , result);
    }

    /**
     * Test of setPlayerIndex method, of class PlayerPhaseModel.
     */
    @Test
    public void testSetPlayerIndex()
    {
        int increment = 2;
        PlayerPhaseModel.setPlayerIndex(increment);
        int expResult =2;
        int result = PlayerPhaseModel.getPlayerIndex();
        assertEquals(expResult , result);
    }

    /**
     * Test of getPlayerIndex method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayerIndex()
    {
    	 int expResult = 0;
         int result = PlayerPhaseModel.getPlayerIndex();
         assertEquals(expResult , result);
    }

    /**
     * Test of getNumberOfPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetNumberOfPlayer()
    {
    	int expResult = 1;
        int result = PlayerPhaseModel.getNumberOfPlayer();
        assertEquals(expResult , result);
    }

    /**
     * Test of getCurrentPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetCurrentPlayer()
    {
        Player result = PlayerPhaseModel.getCurrentPlayer();
        assertEquals(p1, result);
    }

    /**
     * Test of addPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testAddPlayer()
    {
    	PlayerPhaseModel.addPlayer(p2);
    	ArrayList<Player> expResult = new ArrayList<Player>();
        expResult.add(p1);
        expResult.add(p2);
        ArrayList<Player> result = PlayerPhaseModel.getPlayers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayers method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayers()
    {
        ArrayList<Player> expResult = new ArrayList<Player>();
        expResult.add(p1);
        ArrayList<Player> result = PlayerPhaseModel.getPlayers();
        assertEquals(expResult, result);
    }
    
}
