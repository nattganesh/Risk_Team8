/**
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

/**
 * @author DKM
 *
 */
public class SaveGameTest {
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
    
    @Test
    public void testGenerate()
    {	
    	LoadGame.generate("savegame");
    	assertTrue(SaveGame.generate("TestSaveGame", "valid1"));
    }
    
  
}
