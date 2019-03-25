/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.ActionModel;
import com.risk.model.DeckModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
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
public class MapEditorControllerTest {
    
    public MapEditorControllerTest()
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
     * Test of searchTerritory method, of class MapEditorController.
     */
    @Test
    public void testSearchTerritory()
    {
        System.out.println("searchTerritory");
        MapEditorController instance = new MapEditorController();
        Country country1 = new Country("country1");
        Country country2 = new Country("country2");
        Continent continent = new Continent("continent1", 10);
        
        continent.getCountries().add(country1);
        continent.getCountries().add(country2);
        country1.setContinent(continent);
        country2.setContinent(continent);
        ObservableList<Continent> continentslist = FXCollections.observableArrayList();
        continentslist.add(continent);
        
        
             
        Country expResult = country1;
        Country result = instance.searchTerritory(continentslist, country1.getName());
      
        
        assertEquals(expResult, result);
    }


    /**
     * Test of setPlayers method, of class MapEditorController.
     */
    @Test
    public void testSetPlayers()
    {
        System.out.println("setPlayers");
        
        MapEditorController instance = new MapEditorController();
	        
        int numbPlayer = 5;
        instance.setPlayers(numbPlayer);
        
        int expResult = 5;
        int result =  PlayerPhaseModel.getPlayerModel().getPlayers().size();
        
        assertEquals(expResult, result);
      
        
    }

    /**
     * Test of setDeck method, of class MapEditorController.
     */
    @Test
    public void testSetDeck()
    {
        System.out.println("setDeck");
        MapEditorController instance = new MapEditorController();
        instance.setDeck();
        int expResult = 44;
        int result =  DeckModel.getCardModel().getCards().length;
        
        assertEquals(result, expResult);
    }

    /**
     * Test of determinePlayersStartingOrder method, of class MapEditorController.
     */
    @Test
    public void testDeterminePlayersStartingOrder()
    {
        System.out.println("determinePlayersStartingOrder");
        MapEditorController instance = new MapEditorController();
        
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy1"));
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy2"));
        instance.determinePlayersStartingOrder();
        
        int expResult = 2;
        int result =  PlayerPhaseModel.getPlayerModel().getNumberOfPlayer();
        
        assertEquals(expResult, result);
       
    }

    /**
     * This is a test for assigning country to players
     */
    @Test
    public void testAssignCountriesToPlayers()
    {
        System.out.println("assignCountriesToPlayers");
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy1"));
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy2"));
        MapModel.getMapModel().getCountries().add(new Country("country1"));
        MapModel.getMapModel().getCountries().add(new Country("country2"));
        
        MapEditorController instance = new MapEditorController();
        instance.assignCountriesToPlayers();
        
        
        int expResult = 1;
        int result =  PlayerPhaseModel.getPlayerModel().getCurrentPlayer().getOccupiedCountries().get(0).getArmyCount();
        
        assertEquals(expResult, result);
      
    }

    /**
     * Test of autoAssignCountriesToPlayers method, of class MapEditorController.
     */
    @Test
    public void testAutoAssignCountriesToPlayers()
    {
    	System.out.println("assignCountriesToPlayers");
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy1"));
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy2"));

        MapModel.getMapModel().getCountries().add(new Country("country1"));
        MapModel.getMapModel().getCountries().add(new Country("country2"));

        
        MapEditorController instance = new MapEditorController();
        instance.calcStartingArmies();
        instance.autoAssignCountriesToPlayers();
      
        int expResult = 80;
        int result1 =  PlayerPhaseModel.getPlayerModel().getPlayers().get(0).getOccupiedCountries().get(0).getArmyCount();
        int result2 =  PlayerPhaseModel.getPlayerModel().getPlayers().get(1).getOccupiedCountries().get(0).getArmyCount();
        assertEquals(expResult, result1 + result2);
    }

    /**
     * Test of calcStartingArmies method, of class MapEditorController.
     */
    @Test
    public void testCalcStartingArmies()
    {
        System.out.println("calcStartingArmies");
        
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy1"));
        PlayerPhaseModel.getPlayerModel().getPlayers().add(new Player("dummy2"));

        MapModel.getMapModel().getCountries().add(new Country("country1"));
        MapModel.getMapModel().getCountries().add(new Country("country2"));
        
        MapEditorController instance = new MapEditorController();
        instance.calcStartingArmies();
        int result = 40;
        int expResult = PlayerPhaseModel.getPlayerModel().getPlayers().get(0).getStartingPoints();
        assertEquals(result, expResult);
    }

    /**
     * Test of calcStartingArmiesHelper method, of class MapEditorController.
     */
    @Test
    public void testCalcStartingArmiesHelper()
    {
        System.out.println("calcStartingArmiesHelper");
        int getPlayerSize = 6;
        MapEditorController instance = new MapEditorController();
        int expResult = 150;
        int result = 0;
        for (int i = 2; i <= getPlayerSize; i++)
        {
        	 result += instance.calcStartingArmiesHelper(i);
        }
        assertEquals(expResult, result);
      
    }
    
    /**
     * This is a test for removing adjacent country
     */
    @Test
    public void testRemoveAdjacentCountry()
    {
    	Country country = new Country("country1");
    	Country country1 = new Country("country2");
    	Continent continent1 = new Continent("continent1", 10);
    	Continent continent2 = new Continent("continent2", 10);
    	
    	country.getConnectedCountries().add(country1);
    	country1.getConnectedCountries().add(country);
    	
    	continent1.getCountries().add(country);
    	continent2.getCountries().add(country1);
    
	    ObservableList<Continent> continentslist = FXCollections.observableArrayList();
	    continentslist.add(continent1);
	    continentslist.add(continent2);
	    
	    MapEditorController instance = new MapEditorController();
	    instance.removeAdjacentCountry(continentslist, country, country1);
	    
	
	    int expResult = 0; 
	    int result = country1.getConnectedCountries().size();
	    
	    assertEquals(expResult, result);
	    
	     
    }
    
    
}
