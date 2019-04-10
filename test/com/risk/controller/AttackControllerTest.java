/**
 * This is test for attack controller
 * 
 * @author DKM
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
public class AttackControllerTest 
{
	

    
    public AttackControllerTest()
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
    
    @Test
    public void testAttackConstructor()
    {
    	PlayerPhaseModel.getPlayerModel().addPlayer(new Player("dummy1"));
    	AttackController attackController = new AttackController();
    	assertNotNull(attackController);
    }
    
  
}
