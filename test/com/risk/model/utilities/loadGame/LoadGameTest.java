/**
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

/**
 * @author DKM
 *
 */
public class LoadGameTest {
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
    public void testGenerateCorrectFile()
    {
    	Boolean result = LoadGame.generate("savegame");
    	String expResult = "fortification";
    	assertEquals(expResult, GamePhaseModel.getGamePhaseModel().getPhase());
    	assertTrue(result);
   
    }
    
    @Test
    public void testGenerateEmptyFile()
    {
   
    	assertFalse(LoadGame.generate("EmptyFile"));
   
    }
    
    @Test
    public void testGenerateOneCard()
    {
    	Boolean result = LoadGame.generate("savegame2");
    	String expResult = "fortification";
    	assertEquals(expResult, GamePhaseModel.getGamePhaseModel().getPhase());
    	assertTrue(result);
   
    }
}
