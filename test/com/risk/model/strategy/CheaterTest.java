
package com.risk.model.strategy;

import com.risk.controller.FortificationController;
import com.risk.controller.ReinforcementController;
import com.risk.model.MapModel;
import com.risk.model.card.Card;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases of Cheater Player
 * 
 * @author DKM
 * @author Tianyi
 */
public class CheaterTest 
{
	private Player p1;
	private Player p2;
	private Country c1;
	private Country c2;
	private Country c3;
	private ArrayList<Country> occupiedCountries1 = new ArrayList<Country>();
	private ArrayList<Country> occupiedCountries2 = new ArrayList<Country>();
	private Continent continent;
	
    public CheaterTest()
    {
    }
    
    /**
     * This is setup for test cases, where continent, country and player are initialized
     * 
     */
    @Before
    public void setUp()
    {
    	continent = new Continent("Asia",0);
    	p1 = new Player("Green");
    	p1.setStrategy(new Cheater());
    	p2 = new Player("Red");
    	p2.setStrategy(new Benevolent());
    	c1 = new Country("China");
		c2 = new Country("Quebec");
		c3 = new Country("Siam");
		continent.setCountry(c1);
    	continent.setCountry(c2);
    	continent.setCountry(c3);
    	c1.setContinent(continent);
    	c2.setContinent(continent);
    	c3.setContinent(continent);
		c1.getConnectedCountries().add(c2);
		c1.getConnectedCountries().add(c3);
		c2.getConnectedCountries().add(c1);
		c3.getConnectedCountries().add(c1);
    }
    
    @After
    public void tearDown()
    {
    	
    }
    
    /**
     * This is used to test attack for cheater player
     * If it runs correctly, all enemy countries connected to the player's countries will be conquered
     * 
     */
    @Test 
    public void testAttack()
    {
		c1.setRuler(p1);
		c2.setRuler(p2);
		c3.setRuler(p2);
		occupiedCountries1.add(c1);
		occupiedCountries2.add(c2);
		occupiedCountries2.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
		p2.setOccupiedCountries(occupiedCountries2);
		c1.setArmyCount(10);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
		p1.attackStrategy(p1);
		assertTrue(p2.getOccupiedCountries().size()==0);
    }
    
    /**
     * This is used to test reinforcement for cheater player
     * If it runs correctly, the number of armies of all countries gets doubled
     * 
     */
    @Test 
    public void testReinforce()
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
		c1.setArmyCount(10);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
		p1.reinforceStrategy(p1);
		int expectResult1 = 20;
		int expectResult2 = 4;
		int expectResult3 = 4;
		int result1 =c1.getArmyCount();
		int result2 =c2.getArmyCount();
		int result3 =c3.getArmyCount();
		assertEquals(expectResult1, result1);
		assertEquals(expectResult2, result2);
		assertEquals(expectResult3, result3);
    }
    
    /**
     * This is used to test fortification for cheater player
     * If it runs correctly, the number of armies of countries which have enemy neighbors gets doubled
     * 
     */
    @Test 
    public void testFortify()
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
		c1.setArmyCount(10);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
		Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c4.setRuler(p2);
		occupiedCountries2.add(c4);
		p2.setOccupiedCountries(occupiedCountries2);
		c4.setArmyCount(4);
		p1.fortifyStrategy(p1);
		int expectResult1 = 20;
		int expectResult2 = 2;
		int expectResult3 = 2;
		int result1 =c1.getArmyCount();
		int result2 =c2.getArmyCount();
		int result3 =c3.getArmyCount();
		assertEquals(expectResult1, result1);
		assertEquals(expectResult2, result2);
		assertEquals(expectResult3, result3);
    }

    /**
     * This is used to test setup for cheater player
     * If it runs correctly, countries get all starting armies randomly
     * 
     */
    @Test
    public void testSetup() 
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
		p1.setStartingPoints(10);
		p1.setupStrategy(p1);
		int expectResult1 = 10;
		int result = c1.getArmyCount()+ c2.getArmyCount()+c3.getArmyCount();
		assertEquals(expectResult1, result);
    }
}
