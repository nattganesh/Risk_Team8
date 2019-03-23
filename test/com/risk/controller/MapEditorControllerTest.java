/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.controller;

import com.risk.model.map.Country;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
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
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of initialize method, of class MapEditorController.
     */
    @Test
    public void testInitialize()
    {
        System.out.println("initialize");
        URL arg0 = null;
        ResourceBundle arg1 = null;
        MapEditorController instance = new MapEditorController();
        instance.initialize(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadTerritoryHandler method, of class MapEditorController.
     */
    @Test
    public void testLoadTerritoryHandler()
    {
        System.out.println("loadTerritoryHandler");
        MouseEvent arg0 = null;
        MapEditorController instance = new MapEditorController();
        instance.loadTerritoryHandler(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadAdjacentHandler method, of class MapEditorController.
     */
    @Test
    public void testLoadAdjacentHandler()
    {
        System.out.println("loadAdjacentHandler");
        MouseEvent arg0 = null;
        MapEditorController instance = new MapEditorController();
        instance.loadAdjacentHandler(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of territoryAddHandler method, of class MapEditorController.
     */
    @Test
    public void testTerritoryAddHandler()
    {
        System.out.println("territoryAddHandler");
        MapEditorController instance = new MapEditorController();
        instance.territoryAddHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchTerritory method, of class MapEditorController.
     */
    @Test
    public void testSearchTerritory()
    {
        System.out.println("searchTerritory");
        String countryName = "";
        MapEditorController instance = new MapEditorController();
        Country expResult = null;
        Country result = instance.searchTerritory(countryName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjacentAddHandler method, of class MapEditorController.
     */
    @Test
    public void testAdjacentAddHandler()
    {
        System.out.println("adjacentAddHandler");
        MapEditorController instance = new MapEditorController();
        instance.adjacentAddHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of territoryDeleteHandler method, of class MapEditorController.
     */
    @Test
    public void testTerritoryDeleteHandler()
    {
        System.out.println("territoryDeleteHandler");
        MapEditorController instance = new MapEditorController();
        instance.territoryDeleteHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjacentDeleteHandler method, of class MapEditorController.
     */
    @Test
    public void testAdjacentDeleteHandler()
    {
        System.out.println("adjacentDeleteHandler");
        MapEditorController instance = new MapEditorController();
        instance.adjacentDeleteHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadMapHandler method, of class MapEditorController.
     */
    @Test
    public void testLoadMapHandler() throws Exception
    {
        System.out.println("loadMapHandler");
        MapEditorController instance = new MapEditorController();
        instance.loadMapHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMapHandler method, of class MapEditorController.
     */
    @Test
    public void testSaveMapHandler() throws Exception
    {
        System.out.println("saveMapHandler");
        MapEditorController instance = new MapEditorController();
        instance.saveMapHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newMapHandler method, of class MapEditorController.
     */
    @Test
    public void testNewMapHandler()
    {
        System.out.println("newMapHandler");
        MapEditorController instance = new MapEditorController();
        instance.newMapHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGameHandler method, of class MapEditorController.
     */
    @Test
    public void testStartGameHandler()
    {
        System.out.println("startGameHandler");
        MapEditorController instance = new MapEditorController();
        instance.startGameHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderView method, of class MapEditorController.
     */
    @Test
    public void testRenderView()
    {
        System.out.println("renderView");
        MapEditorController instance = new MapEditorController();
        instance.renderView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeContinents method, of class MapEditorController.
     */
    @Test
    public void testInitializeContinents()
    {
        System.out.println("initializeContinents");
        MapEditorController instance = new MapEditorController();
        instance.initializeContinents();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializePlayers method, of class MapEditorController.
     */
    @Test
    public void testInitializePlayers()
    {
        System.out.println("initializePlayers");
        MapEditorController instance = new MapEditorController();
        instance.initializePlayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearMapEditor method, of class MapEditorController.
     */
    @Test
    public void testClearMapEditor()
    {
        System.out.println("clearMapEditor");
        MapEditorController instance = new MapEditorController();
        instance.clearMapEditor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayers method, of class MapEditorController.
     */
    @Test
    public void testSetPlayers()
    {
        System.out.println("setPlayers");
        int numberOfPlayer = 0;
        MapEditorController instance = new MapEditorController();
        instance.setPlayers(numberOfPlayer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determinePlayersStartingOrder method, of class MapEditorController.
     */
    @Test
    public void testDeterminePlayersStartingOrder()
    {
        System.out.println("determinePlayersStartingOrder");
        MapEditorController instance = new MapEditorController();
        instance.determinePlayersStartingOrder();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignCountriesToPlayers method, of class MapEditorController.
     */
    @Test
    public void testAssignCountriesToPlayers()
    {
        System.out.println("assignCountriesToPlayers");
        MapEditorController instance = new MapEditorController();
        instance.assignCountriesToPlayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of autoAssignCountriesToPlayers method, of class MapEditorController.
     */
    @Test
    public void testAutoAssignCountriesToPlayers()
    {
        System.out.println("autoAssignCountriesToPlayers");
        MapEditorController instance = new MapEditorController();
        instance.autoAssignCountriesToPlayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcStartingArmies method, of class MapEditorController.
     */
    @Test
    public void testCalcStartingArmies()
    {
        System.out.println("calcStartingArmies");
        MapEditorController instance = new MapEditorController();
        instance.calcStartingArmies();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcStartingArmiesHelper method, of class MapEditorController.
     */
    @Test
    public void testCalcStartingArmiesHelper()
    {
        System.out.println("calcStartingArmiesHelper");
        int getPlayerSize = 0;
        MapEditorController instance = new MapEditorController();
        int expResult = 0;
        int result = instance.calcStartingArmiesHelper(getPlayerSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
