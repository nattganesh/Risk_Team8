/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import com.risk.model.player.Player;
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
public class PlayerPhaseModelTest 
{
    private PlayerPhaseModel playerPhaseModel;
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
    	playerPhaseModel = PlayerPhaseModel.getPlayerModel();
    	playerPhaseModel.setPlayerIndex(0);
    	
    }
    
    @After
    public void tearDown()
    {
    	playerPhaseModel.getPlayers().clear();
    	playerPhaseModel.setPlayerIndex(0);
    	
    }

    /**
     * Test of setWinner method, of class PlayerPhaseModel.
     */
    @Test
    public void testSetWinner()
    {
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	playerPhaseModel.addPlayer(p1);
    	Player result = playerPhaseModel.setWinner(p1);
    	assertEquals(p1 , result);
    }

    /**
     * Test of setPlayerIndex method, of class PlayerPhaseModel.
     */
    @Test
    public void testSetPlayerIndex()
    {
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	playerPhaseModel.addPlayer(p1);
        int increment = 2;
        playerPhaseModel.setPlayerIndex(increment);
        int expResult =2;
        int result = playerPhaseModel.getPlayerIndex();
        assertEquals(expResult , result);
    }

    /**
     * Test of getPlayerIndex method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayerIndex()
    {
    	 p1 = new Player("Green");
    	 p2 = new Player("Red");
    	 playerPhaseModel.addPlayer(p1);
    	 int expResult = 0;
         int result = playerPhaseModel.getPlayerIndex();
         System.out.println("HERE : " + playerPhaseModel.getPlayers().size());
         assertEquals(expResult , result);
    }

    /**
     * Test of getNumberOfPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetNumberOfPlayer()
    {
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	playerPhaseModel.addPlayer(p1);
    	int expResult = 1;
        int result = playerPhaseModel.getNumberOfPlayer();
        assertEquals(expResult , result);
    }

    /**
     * Test of getCurrentPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetCurrentPlayer()
    {
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	playerPhaseModel.addPlayer(p1);
        Player result = playerPhaseModel.getCurrentPlayer();
        assertEquals(p1, result);
    }

    /**
     * Test of addPlayer method, of class PlayerPhaseModel.
     */
    @Test
    public void testAddPlayer()
    {
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	playerPhaseModel.addPlayer(p1);
    	playerPhaseModel.addPlayer(p2);
    	ArrayList<Player> expResult = new ArrayList<Player>();
        expResult.add(p1);
        expResult.add(p2);
        ArrayList<Player> result = playerPhaseModel.getPlayers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayers method, of class PlayerPhaseModel.
     */
    @Test
    public void testGetPlayers()
    {
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	playerPhaseModel.addPlayer(p1);
        ArrayList<Player> expResult = new ArrayList<Player>();
        expResult.add(p1);
        ArrayList<Player> result = playerPhaseModel.getPlayers();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setting the next player that hasn't lost
     */
    @Test
    public void testSetNextPlayer()
    {
    	ArrayList<Player> players = playerPhaseModel.getPlayers();
    	Player player1 = new Player("1");
    	Player player2 = new Player("2");
    	Player player3 = new Player("3");
    	Player player4 = new Player("4");
    	Player player5 = new Player("5");
    	Player player6 = new Player("6");
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	players.add(player4);
    	players.add(player5);
    	players.add(player6);
    	player1.setPlayerLost(false);
    	player2.setPlayerLost(false);
    	player3.setPlayerLost(false);
    	player4.setPlayerLost(false);
    	player5.setPlayerLost(false);
    	player6.setPlayerLost(false);
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("2", playerPhaseModel.getCurrentPlayer().getName());
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("3", playerPhaseModel.getCurrentPlayer().getName());
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("4", playerPhaseModel.getCurrentPlayer().getName());
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("5", playerPhaseModel.getCurrentPlayer().getName());
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("6", playerPhaseModel.getCurrentPlayer().getName());
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("1", playerPhaseModel.getCurrentPlayer().getName());
    	
    }
    
    /**
     * Test of setting the next player that hasn't lost
     */
    @Test
    public void testSetNextPlayer2()
    {
    	ArrayList<Player> players = playerPhaseModel.getPlayers();
    	Player player1 = new Player("1");
    	Player player2 = new Player("2");
    	Player player3 = new Player("3");
    	Player player4 = new Player("4");
    	Player player5 = new Player("5");
    	Player player6 = new Player("6");
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	players.add(player4);
    	players.add(player5);
    	players.add(player6);
    	player1.setPlayerLost(false);
    	player2.setPlayerLost(true);
    	player3.setPlayerLost(false);
    	player4.setPlayerLost(true);
    	player5.setPlayerLost(false);
    	player6.setPlayerLost(false);
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("3", playerPhaseModel.getCurrentPlayer().getName());    
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("5", playerPhaseModel.getCurrentPlayer().getName());   
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("6", playerPhaseModel.getCurrentPlayer().getName());   
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("1", playerPhaseModel.getCurrentPlayer().getName());    
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("3", playerPhaseModel.getCurrentPlayer().getName());    
    	
    	player1.setPlayerLost(false);
    	player2.setPlayerLost(true);
    	player3.setPlayerLost(true);
    	player4.setPlayerLost(true);
    	player5.setPlayerLost(true);
    	player6.setPlayerLost(true);
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("1", playerPhaseModel.getCurrentPlayer().getName());  
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("1", playerPhaseModel.getCurrentPlayer().getName());  
    	
    }
    
    /**
     * Test of setting the next player that hasn't lost
     */
    @Test
    public void testSetNextPlayer3()
    {
    	ArrayList<Player> players = playerPhaseModel.getPlayers();
    	Player player1 = new Player("1");
    	Player player2 = new Player("2");
    	Player player3 = new Player("3");
    	Player player4 = new Player("4");
    	Player player5 = new Player("5");
    	Player player6 = new Player("6");
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	players.add(player4);
    	players.add(player5);
    	players.add(player6);
    	player1.setPlayerLost(false);
    	player2.setPlayerLost(true);
    	player3.setPlayerLost(true);
    	player4.setPlayerLost(true);
    	player5.setPlayerLost(false);
    	player6.setPlayerLost(true);
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("5", playerPhaseModel.getCurrentPlayer().getName());  
    	
    	playerPhaseModel.setNextPlayer();
    	assertEquals("1", playerPhaseModel.getCurrentPlayer().getName());  
    }
}
