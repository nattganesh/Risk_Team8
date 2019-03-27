/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.ActionModel;
import com.risk.model.DeckModel;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for game phase controller
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
    	Country country1 = new Country("c1");
    	Country country2 = new Country("c2");
    	MapModel.getMapModel().addCountry(country1);
    	MapModel.getMapModel().addCountry(country2);
    	Player p = new Player("player1");
    	p.addCountry(country1);
      	p.addCountry(country2);
    	
    	boolean result = p.getOccupiedCountries().size()  == MapModel.getMapModel().getCountries().size();
        assertTrue(result);
    }
    
    /**
     * This method tests the observer of the GamePhaseModel
     */
    @Test	
    public void testUpdate()
    {
    	GamePhaseController gController = new GamePhaseController();
    	GamePhaseModel gamePhase = GamePhaseModel.getGamePhaseModel();
    	gamePhase.setPhase("hello");
    	String expResult = "hello";
    	assertEquals(gController.view, expResult);
    }
    


   
    
}
