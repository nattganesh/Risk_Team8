
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
 * Test cases of Random Player
 * 
 * @author DKM
 * @author Tianyi
 */
public class RandomTest 
{
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
    }
    
    /**
     * This is setup for test cases, where continent, country and player are initialized
     * 
     */
    @Before
    public void setUp()
    {
    	DeckModel.getCardModel().initialize();
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
    
    @After
    public void tearDown()
    {
    	
    }
    
    /**
     * This is used to do attack for random player
     * @return different true if the number of armies each country of the player changed or the number of countries owned by the player changed. Otherwise, return false
     */
    public int[] attack() 
    {
    	int[] different = new int[2];
    	different[0] = 0;
    	different[1] = 0;
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
		c4.setArmyCount(1);
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
		if(expectResult!=result) 
		{
			if(countriesBeforeFortify.size()!=countriesAfterFortify.size()) 
			{
				different[1]=1;
			}
			for(String c: countriesAfterFortify.keySet()) 
			{
				if(countriesAfterFortify.get(c)!=countriesBeforeFortify.get(c))
				{
					different[0]=1;
				}
			}
		}
		return different;
    }
    
    
    /**
     * This is used to test attack for random player
     * If it runs correctly, the defender might lose the game, or the number of armies of each country of the random player might change
     * 
     */
    @Test public void testAttack1()
    {
    	boolean different= false;
    	for(int i =0 ; i<200; i++)
    	{
    		int[] attack = attack();
    		if(attack[0]==1||attack[1]==1) 
    		{
    			different = true;
    			if(attack[1]==1) 
    			{
        			assertTrue(p2.isPlayerLost());
        			
        		}
    			break;
    		}
    	}
    	assertTrue(different);
    }
    
    
    /**
     * This is used to test reinforcement for random player
     * If it runs correctly, the total number of armies will change
     * 
     */
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
    
    /**
     * This is used to test fortification for random player
     * If it runs correctly, the number of armies of two countries will change, but keeping the total number the same
     * 
     */
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

    /**
     * This is used to test setup for random player
     * If it runs correctly, countries get all starting armies
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
    
    /**
     * This is used to test conquer for random player
     * If it runs correctly, the number of armies of attack and defend countries will be the same
     * 
     */
    @Test
    public void testConquer() 
    {
    	c1.setRuler(p1);
		occupiedCountries1.add(c1);
		p1.setOccupiedCountries(occupiedCountries1);
		c1.setArmyCount(20);
		Country c4 = new Country("Korean");
    	c1.getConnectedCountries().add(c4);
		c4.getConnectedCountries().add(c1);
		c4.setRuler(p2);
		occupiedCountries2.add(c4);
		p2.setOccupiedCountries(occupiedCountries2);
		c4.setArmyCount(0);
		int expectResult = c1.getArmyCount()+c4.getArmyCount();
		p1.conquerStrategy(c1,c4,3);
		int result = c1.getArmyCount()+c4.getArmyCount();
		assertEquals(expectResult, result);
    }
}
