
package com.risk.model.strategy;

import com.risk.controller.FortificationController;
import com.risk.controller.ReinforcementController;
import com.risk.model.DeckModel;
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
 * Test cases of Aggressive Player
 * 
 * @author DKM
 * @author Tianyi
 */
public class AggressiveTest 
{
	private Player p1;
	private Player p2;
	private Country c1;
	private Country c2;
	private Country c3;
	private ArrayList<Country> occupiedCountries1 = new ArrayList<Country>();
	private ArrayList<Country> occupiedCountries2 = new ArrayList<Country>();
	private Continent continent;
    public AggressiveTest()
    {
    }
    
    /**
     * This is setup for test cases, where continent, country and player are initialized
     * 
     */
    @Before
    public void setUp()
    {
    	DeckModel.getCardModel().initialize();
    	continent = new Continent("Asia",10);
    	p1 = new Player("Green");
    	p1.setStrategy(new Aggressive());
    	p2 = new Player("Red");
    	p2.setStrategy(new Benevolent());
    	c1 = new Country("China");
		c2 = new Country("Quebec");
		c3 = new Country("Siam");
		occupiedCountries1.add(c1);
		continent.setCountry(c1);
    	continent.setCountry(c2);
    	continent.setCountry(c3);
    	c1.setContinent(continent);
    	c2.setContinent(continent);
    	c3.setContinent(continent);
		c1.setRuler(p1);
		c2.setRuler(p2);
		c3.setRuler(p2);
		c1.setIsOccupied(true);
		c2.setIsOccupied(true);
		c3.setIsOccupied(true);
		c1.getConnectedCountries().add(c2);
		c1.getConnectedCountries().add(c3);
		c2.getConnectedCountries().add(c1);
		c3.getConnectedCountries().add(c1);
		p1.setOccupiedCountries(occupiedCountries1);
		
		occupiedCountries2.add(c2);
		occupiedCountries2.add(c3);
		p2.setOccupiedCountries(occupiedCountries2);
		
    }
    
    @After
    public void tearDown()	
    {
    	
    }
    
    /**
     * This is used to test attack for aggressive player
     * If it runs correctly, the defender player will lost after attack
     * 
     */
    @Test 
    public void testAttack()
    {
    	c1.setArmyCount(50);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
		p1.attackStrategy(p1);
    	
    	ArrayList<Country> result = p1.getStrongestCountry().getConnectedEnemyArrayList();
    	assertTrue(result.size()==0);
    }
    
    /**
     * This is used to test reinforcement for aggressive player
     * If it runs correctly, only the strongest country gets the reinforcement
     * 
     */
    @Test 
    public void testReinforce()
    {
    	c1.setArmyCount(50);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
    	p1.reinforceStrategy(p1);
    	int expectResult = 53;
    	int result = c1.getArmyCount();
    	assertEquals(expectResult, result);
    }
    
    /**
     * This is used to test fortification for aggressive player
     * If it runs correctly, only the strongest country with most enemy neighbors gets the fortification
     * 
     */
    @Test 
    public void testFortify1()
    {
    	Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c4.setRuler(p1);
		occupiedCountries1.add(c4);
		p1.setOccupiedCountries(occupiedCountries1);
		c1.setArmyCount(50);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
		c4.setArmyCount(4);
		int expectResult1= 1;
		int expectResult2= 53;
		p1.fortifyStrategy(p1);
		int result1 = c4.getArmyCount();
		int result2 = c1.getArmyCount();
		assertEquals(expectResult1,result1);
		assertEquals(expectResult2,result2);
    }
    
    /**
     * This is used to test fortification for aggressive player
     * If it runs correctly, only the country connected to the previous strongest country with most enemy neighbors gets the fortification
     * 
     */
    @Test 
    public void testFortify2()
    {
    	Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c4.setRuler(p1);
		occupiedCountries1.add(c4);
		p1.setOccupiedCountries(occupiedCountries1);
		c1.setArmyCount(12);
    	c2.setArmyCount(2);
		c3.setArmyCount(2);
		c4.setArmyCount(1);
		int expectResult1= 1;
		int expectResult2= 12;
		p1.fortifyStrategy(p1);
		int result1 = c1.getArmyCount();
		int result2 = c4.getArmyCount();
		assertEquals(expectResult1,result1);
		assertEquals(expectResult2,result2);
    }
    
    /**
     * This is used to test setup for aggressive player
     * If it runs correctly, only one country gets all the starting armies
     * 
     */
    @Test
    public void testSetup()
    {
    	Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c4.setRuler(p1);
		occupiedCountries1.add(c4);
		continent.setCountry(c4);
    	c4.setContinent(continent);
		c4.setIsOccupied(true);
		p1.setOccupiedCountries(occupiedCountries1);
    	p1.setStartingPoints(10);
    	p1.setupStrategy(p1);
    	int expectResult1 = 0;
    	int expectResult2 = 10;
    	int result1 = p1.getStartingPoints();
    	p1.setupStrategy(p1);
    	assertEquals(expectResult1,result1);
		assertTrue(expectResult2==c1.getArmyCount()||expectResult2==c4.getArmyCount());
    }
}
