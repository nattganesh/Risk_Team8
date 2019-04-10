/**
 * This method a test for saving game
 * @author DKM
 *
 */
package com.risk.model.utilities.saveGame;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.controller.FortificationController;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.player.Player;
import com.risk.model.utilities.loadGame.LoadGame;


public class SaveGameTest {
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    /**
     * This method clears the mapmodel and playerphasemodel before each test
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
     * This method test for SaveGame.generate() with correct models
     * If method runs correctly it should return true
     */
    @Test
    public void testGenerate()
    {	
    	LoadGame.generate("savegame");
    	assertTrue(SaveGame.generate("TestSaveGame", "valid1"));
    }
    
  
}
