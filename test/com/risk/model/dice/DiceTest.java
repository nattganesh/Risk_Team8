/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.dice;

import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.player.Player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for dice
 * 
 * @author Natt
 * @author Tianyi
 */
public class DiceTest {
	private Country country1;
	private Country country2;
	private Player p1;
	private Player p2;
    
    public DiceTest()
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
    	p1 = new Player("Green");
    	p2 = new Player("Red");
    	country1 = new Country("China");
    	country2 = new Country("Siam");
    	p1.addCountry(country1);
    	p2.addCountry(country2);
    	country1.setRuler(p1);
    	country2.setRuler(p2);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of roll method, of class Dice.
     */
    @Test
    public void testRoll()
    {
        int result = Dice.roll();
        assertTrue(0<result&&result<=6);
    }
    
    /**
     * Test the dice constructor
     */
    @Test
    public void testDiceConstructor()
    {
    	Dice dice = new Dice();
    	assertNotNull(dice);
    }
    
}
