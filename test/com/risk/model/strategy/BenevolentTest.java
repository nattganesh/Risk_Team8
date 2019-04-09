
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
 * Test cases of Benevolent Player
 * 
 * @author DKM
 * @author Tianyi
 */
public class BenevolentTest 
{
	private Player p1;
	private Player p2;
	private Country c1;
	private Country c2;
	private Country c3;
	private ArrayList<Country> occupiedCountries1 = new ArrayList<Country>();
	private ArrayList<Country> occupiedCountries2 = new ArrayList<Country>();
	private Continent continent;
	
    public BenevolentTest()
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
    	p1.setStrategy(new Benevolent());
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
    
    @Test public void testAttack()
    {
    	
    }
    
    /**
     * This is used to test reinforcement for benevolent player
     * If it runs correctly, all three weakest countries will get the reinforcement
     * 
     */
    @Test public void testReinforce1()
    {
		c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		c1.setIsOccupied(true);
		c2.setIsOccupied(true);
		c3.setIsOccupied(true);
    	occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
    	c1.setArmyCount(1);
    	c2.setArmyCount(1);
		c3.setArmyCount(1);
    	p1.reinforceStrategy(p1);
    	int expectResult = 2;
    	int result1 = c1.getArmyCount();
    	int result2 = c2.getArmyCount();
    	int result3 = c3.getArmyCount();
    	assertEquals(expectResult, result1);
    	assertEquals(expectResult, result2);
    	assertEquals(expectResult, result3);
    }
    
    /**
     * This is used to test reinforcement for benevolent player
     * If it runs correctly, only the weakest country will get the reinforcement
     * 
     */
    @Test public void testReinforce2()
    {
		c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		c1.setIsOccupied(true);
		c2.setIsOccupied(true);
		c3.setIsOccupied(true);
    	occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
    	c1.setArmyCount(1);
    	c2.setArmyCount(3);
		c3.setArmyCount(4);
    	p1.reinforceStrategy(p1);
    	int expectResult = 4;
    	int result1 = c1.getArmyCount();
    	assertEquals(expectResult, result1);
    }
    
    /**
     * This is used to test fortification for benevolent player
     * If it runs correctly, the weakest country gets armies from one of its connected countries, which has armies at most
     * 
     */
    @Test public void testFortify()
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		c1.setIsOccupied(true);
		c2.setIsOccupied(true);
		c3.setIsOccupied(true);
    	occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
    	c1.setArmyCount(1);
    	c2.setArmyCount(3);
		c3.setArmyCount(4);
    	p1.fortifyStrategy(p1);
    	int expectResult1 = 4;
    	int expectResult2 = 1;
    	int result1 = c1.getArmyCount();
    	int result2 = c3.getArmyCount();
    	assertEquals(expectResult1, result1);
    	assertEquals(expectResult2, result2);
    }
    
    /**
     * This is used to test fortification for benevolent player
     * If it runs correctly, no country gets armies because the weakest countries have not connected countries with enough armies
     * 
     */
    @Test public void testFortify1()
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		c1.setIsOccupied(true);
		c2.setIsOccupied(true);
		c3.setIsOccupied(true);
    	occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		Country c4 = new Country("Korean");
		Country c5 = new Country("Mongolia");
		c5.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c5);
		c4.setRuler(p1);
		c5.setRuler(p1);
		occupiedCountries1.add(c4);
		occupiedCountries1.add(c5);
		continent.setCountry(c4);
		continent.setCountry(c5);
    	c4.setContinent(continent);
    	c5.setContinent(continent);
    	c4.setIsOccupied(true);
		c5.setIsOccupied(true);
		p1.setOccupiedCountries(occupiedCountries1);
    	c1.setArmyCount(1);
    	c2.setArmyCount(1);
		c3.setArmyCount(1);
		c4.setArmyCount(3);
		c5.setArmyCount(3);
    	p1.fortifyStrategy(p1);
    	int expectResult = 1;
    	int result1 = c1.getArmyCount();
    	int result2 = c2.getArmyCount();
    	int result3 = c3.getArmyCount();
    	assertEquals(expectResult, result1);
    	assertEquals(expectResult, result2);
    	assertEquals(expectResult, result3);
    }

    /**
     * This is used to test setup for benevolent player
     * If it runs correctly, each country gets the same armies from starting armies
     * 
     */
    @Test
    public void testSetup()
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		c1.setIsOccupied(true);
		c2.setIsOccupied(true);
		c3.setIsOccupied(true);
    	occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
    	Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c4.setRuler(p1);
		occupiedCountries1.add(c4);
		continent.setCountry(c4);
    	c4.setContinent(continent);
		c4.setIsOccupied(true);
		p1.setOccupiedCountries(occupiedCountries1);
    	p1.setStartingPoints(12);
    	p1.setupStrategy(p1);
    	int expectResult1 = 3;
    	p1.setupStrategy(p1);
    	int result1 = p1.getStartingPoints();
    	assertEquals(0,result1);
    	assertEquals(expectResult1, c1.getArmyCount());
    	assertEquals(expectResult1, c2.getArmyCount());
    	assertEquals(expectResult1, c3.getArmyCount());
    	assertEquals(expectResult1, c4.getArmyCount());
    }
 
}
