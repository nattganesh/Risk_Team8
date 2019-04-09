
package com.risk.model.strategy;

import com.risk.controller.FortificationController;
import com.risk.controller.ReinforcementController;
import com.risk.model.MapModel;
import com.risk.model.card.Card;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

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
public class RandomTest {
	private Player p1;
	private Player p2;
	private Country c1;
	private Country c2;
	private Country c3;
	private ArrayList<Country> occupiedCountries1 = new ArrayList<Country>();
	private ArrayList<Country> occupiedCountries2 = new ArrayList<Country>();
	private Continent continent;
	
    public RandomTest()
    {
    	continent = new Continent("Asia",0);
    	p1 = new Player("Green");
    	p1.setStrategy(new Random());
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
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    	
    }
    
    @Test public void testAttack()
    {
    	c1.setRuler(p1);
		c2.setRuler(p1);
		c3.setRuler(p1);
		occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
		p1.setOccupiedCountries(occupiedCountries1);
		c1.setArmyCount(20);
    	c2.setArmyCount(20);
		c3.setArmyCount(20);
		Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c2.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c2);
		c3.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c3);
		c4.setRuler(p2);
		occupiedCountries2.add(c4);
		p2.setOccupiedCountries(occupiedCountries2);
		c4.setArmyCount(2);
		int expectResult = c1.getArmyCount()+c2.getArmyCount()+c3.getArmyCount();
		HashMap<String,Integer> countriesBeforeFortify = new HashMap<String,Integer>();
		for(Country c: p1.getOccupiedCountries()) 
		{
			countriesBeforeFortify.put(c.getName(), c.getArmyCount());
		}
		p1.attackStrategy(p1);
		int result = c1.getArmyCount()+c2.getArmyCount()+c3.getArmyCount();
		HashMap<String,Integer> countriesAfterFortify = new HashMap<String,Integer>();
		for(Country c: p1.getOccupiedCountries()) 
		{
			countriesAfterFortify.put(c.getName(), c.getArmyCount());
		}
		assertTrue(expectResult!=result);
		boolean different = false;
		for(String c: countriesAfterFortify.keySet()) 
		{
			if(countriesAfterFortify.get(c)!=countriesBeforeFortify.get(c)||!countriesBeforeFortify.containsKey(c))
			{
				different = true;
			}
		}
		assertTrue(different);
    }
    
    @Test public void testReinforce()
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
		int expectResult1 = 14;
		p1.reinforceStrategy(p1);
		
		int result = c1.getArmyCount()+ c2.getArmyCount()+c3.getArmyCount();
		assertTrue(result>expectResult1);
    }
    
    @Test public void testFortify()
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
		int expectResult = c1.getArmyCount()+c2.getArmyCount()+c3.getArmyCount();
		HashMap<String,Integer> countriesBeforeFortify = new HashMap<String,Integer>();
		for(Country c: p1.getOccupiedCountries()) 
		{
			countriesBeforeFortify.put(c.getName(), c.getArmyCount());
		}
		p1.fortifyStrategy(p1);
		int result = c1.getArmyCount()+c2.getArmyCount()+c3.getArmyCount();
		HashMap<String,Integer> countriesAfterFortify = new HashMap<String,Integer>();
		for(Country c: p1.getOccupiedCountries()) 
		{
			countriesAfterFortify.put(c.getName(), c.getArmyCount());
		}
		assertEquals(expectResult, result);
		boolean different = false;
		for(String c: countriesBeforeFortify.keySet()) 
		{
			if(countriesBeforeFortify.get(c)!=countriesAfterFortify.get(c))
			{
				different = true;
			}
		}
		assertTrue(different);
    }

    @Test
    public void testSetup() {
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
