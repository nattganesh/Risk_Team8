/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Test cases for game phase controller
 * 
 * @author DKM
 */
public class GamePhaseControllerTest 
{
	

    
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
    
    	PlayerPhaseModel.getPlayerModel().getPlayers().clear();
    	MapModel.getMapModel().getCountries().clear();
    	
    }
    
    @After
    public void tearDown()
    {
    	
    }
    
    /**
     * This test checks to see the winner
     */
    @Test
    public void testCheckWinner()
    {
    	GamePhaseController gController = new GamePhaseController();
    	Country country1 = new Country("c1");
    	Country country2 = new Country("c2");
    	MapModel.getMapModel().addCountry(country1);
    	MapModel.getMapModel().addCountry(country2);
    	Player p = new Player("player1");
    	p.addCountry(country1);
      	p.addCountry(country2);
    	
    	boolean result = gController.checkWinner(p);
        assertTrue(result);
    }    
    
    /**
     * This method tests for computer phase changer
     */
    @Test
    public void testDelayNextPhase()
    {
    	
    	PlayerPhaseModel.getPlayerModel().addPlayer(new Player("dummy1"));
    	PlayerPhaseModel.getPlayerModel().addPlayer(new Player("dummy2"));
    	
    	GamePhaseController gController = new GamePhaseController();
    	gController.delayNextPhase("attack", 0);
    	String expResult = "attack";
    	String result = GamePhaseModel.getGamePhaseModel().getPhase();
    	assertEquals(expResult, result);
    	
    	gController.delayNextPhase("fortification", 0);
    	expResult = "fortification";
    	result = GamePhaseModel.getGamePhaseModel().getPhase();
    	assertEquals(expResult, result);
    	
    	gController.delayNextPhase("next", 0);
    	expResult = "reinforcement";
    	result = GamePhaseModel.getGamePhaseModel().getPhase();
    	assertEquals(expResult, result);
    }
    
    /**
     * this method test for method that delays the computer reinforcement strategy
     */
    @Test
    public void testDelayPhaseAction()
    {
    	
    	Player p1 = new Player("BenevolentPlayer1");
    	Country c1 = new Country("dummy1");
    	Country c2 = new Country ("dummy2");
    	Continent cc1 = new Continent("dummyC1", 1);
    	
    	c1.setRuler(p1);
    	c2.setRuler(p1);
    	
    	c1.setArmyCount(1);
    	c2.setArmyCount(1);
    	
    	cc1.getCountries().add(c1);
    	cc1.getCountries().add(c2);
    	
    	c1.setContinent(cc1);
    	c2.setContinent(cc1);
            	
    	p1.getOccupiedCountries().add(c1);
    	p1.getOccupiedCountries().add(c2);

    	PlayerPhaseModel.getPlayerModel().addPlayer(p1);

    	
    	GamePhaseController gController = new GamePhaseController();
    	gController.delayPhaseAction("reinforced", 0);
    	
    	int expResult = 5;
    	int result = p1.getTotalArmy();
    	
    	assertEquals(expResult, result);
    	
    }

    
    /**
     * this method test for method that delays the computer attack strategy using benevolent player
     */
    @Test
    public void testDelayPhaseAction2()
    {
    	
    	Player p1 = new Player("BenevolentPlayer1");
    	Country c1 = new Country("dummy1");
    	Country c2 = new Country ("dummy2");
    	Continent cc1 = new Continent("dummyC1", 1);
    	
    	c1.setRuler(p1);
    	c2.setRuler(p1);
    	
    	c1.setArmyCount(1);
    	c2.setArmyCount(1);
    	
    	cc1.getCountries().add(c1);
    	cc1.getCountries().add(c2);
    	
    	c1.setContinent(cc1);
    	c2.setContinent(cc1);
            	
    	p1.getOccupiedCountries().add(c1);
    	p1.getOccupiedCountries().add(c2);

    	PlayerPhaseModel.getPlayerModel().addPlayer(p1);

    	
    	GamePhaseController gController = new GamePhaseController();
    	gController.delayPhaseAction("attacked", 0);
    	
    	int expResult = 2;
    	int result = p1.getTotalArmy();
    	
    	assertEquals(expResult, result);
    	
    }
    
    /**
     * this method test for method that delays the computer fortify strategy using benevolent player
     */
    @Test
    public void testDelayPhaseAction3()
    {
    	
    	Player p1 = new Player("BenevolentPlayer1");
    	Country c1 = new Country("dummy1");
    	Country c2 = new Country ("dummy2");
    	Continent cc1 = new Continent("dummyC1", 1);
    	
    	c1.setRuler(p1);
    	c2.setRuler(p1);
    	
    	c1.setArmyCount(1);
    	c2.setArmyCount(1);
    	
    	cc1.getCountries().add(c1);
    	cc1.getCountries().add(c2);
    	
    	c1.setContinent(cc1);
    	c2.setContinent(cc1);
            	
    	p1.getOccupiedCountries().add(c1);
    	p1.getOccupiedCountries().add(c2);

    	PlayerPhaseModel.getPlayerModel().addPlayer(p1);

    	
    	GamePhaseController gController = new GamePhaseController();
    	gController.delayPhaseAction("fortified", 0);
    	
    	int expResult = 2;
    	int result = p1.getTotalArmy();
    	
    	assertEquals(expResult, result);
    	
    }
    

    
}
