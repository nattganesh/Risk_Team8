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
 * Test cases for set up controller
 * 
 * @author Natt
 */
public class SetUpControllerTest 
{
    
    public SetUpControllerTest()
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
     * This method tests when country all has one army
     */
    @Test
    public void testCheckIfEachCountryHasOneArmy()
    {
    	 ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    	 Country country1 = new Country("dummy");
    	 Country country2 = new Country("dummy");
    	 Country country3 = new Country("dummy");
    	 Country country4 = new Country("dummy");
    	 Country country5 = new Country("dummy");
    	 
    	 country1.setRuler(new Player("1"));
    	 country2.setRuler(new Player("2"));
    	 country3.setRuler(new Player("3"));
    	 country4.setRuler(new Player("4"));
    	 country5.setRuler(new Player("5"));
    	 
    	 
    	 country1.setArmyCount(1);
    	 country2.setArmyCount(1);
    	 country3.setArmyCount(1);
    	 country4.setArmyCount(1);
    	 country5.setArmyCount(1);
    	 
    	 territoryObservableList.addAll(country1, country2, country3, country4, country5);
    	 SetUpController instance = new SetUpController();
    	 
    	 assertTrue(instance.checkIfEachCountryHasOneArmy(territoryObservableList));
    }
    
    /**
     * This method tests when one country has 0 army
     */
    @Test
    public void testCheckIfEachCountryHasOneArmy2()
    {
    	 ObservableList<Country> territoryObservableList = FXCollections.observableArrayList();
    	 Country country1 = new Country("dummy");
    	 Country country2 = new Country("dummy");
    	 Country country3 = new Country("dummy");
    	 Country country4 = new Country("dummy");
    	 Country country5 = new Country("dummy");
    	 
    	 country1.setRuler(new Player("1"));
    	 country2.setRuler(new Player("2"));
    	 country3.setRuler(new Player("3"));
    	 country4.setRuler(new Player("4"));
    	 country5.setRuler(new Player("5"));
    	 
    	 
    	 country1.setArmyCount(1);
    	 country2.setArmyCount(1);
    	 country3.setArmyCount(1);
    	 country4.setArmyCount(1);
    	 
    	 territoryObservableList.addAll(country1, country2, country3, country4, country5);
    	 SetUpController instance = new SetUpController();
    	 
    	 assertFalse(instance.checkIfEachCountryHasOneArmy(territoryObservableList));
    }
    

}
