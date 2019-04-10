/**
 * This method a test for loading game
 * @author DKM
 *
 */
package com.risk.model.utilities.loadGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.controller.FortificationController;
import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.player.Player;


public class LoadGameTest {
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    /**
     * this method clears theh MapModel and PlayerPhaseModel
     */
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
     * This method test the load game with savegame.txt
     * If method runs properly fortification phase should be set and method should return true
     */
    @Test
    public void testGenerateCorrectFile()
    {
    	Boolean result = LoadGame.generate("savegame");
    	String expResult = "fortification";
    	assertEquals(expResult, GamePhaseModel.getGamePhaseModel().getPhase());
    	assertTrue(result);
   
    }
    
    /**
     * This method test that load fails if given empty file
     */
    @Test
    public void testGenerateEmptyFile()
    {
   
    	assertFalse(LoadGame.generate("EmptyFile"));
   
    }
    
    /**
     * This method test the load game savegame2.txt (varying card number)
     * If method runs properly fortification phase should be set and should return true
     */
    @Test
    public void testGenerateOneCard()
    {
    	Boolean result = LoadGame.generate("savegame2");
    	String expResult = "fortification";
    	assertEquals(expResult, GamePhaseModel.getGamePhaseModel().getPhase());
    	assertTrue(result);
   
    }
}
