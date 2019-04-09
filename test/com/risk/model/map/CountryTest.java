/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.map;

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
 * Test cases for country
 * 
 * @author Natt 
 * @author Tianyi
 */
public class CountryTest {
	private Continent continent;
	private Continent continent1;
	private ArrayList<Country> countries;
	private Country c1;
	private Country c2;
	private Country c3;
	private Country c4;
	private Country c5;
	private Country c6;
	private Player p;
	private Player p1;
	private ArrayList<Country> occupiedCountries1;
    public CountryTest()
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
    	p = new Player("Green");
    	p1 = new Player("Red");
    	occupiedCountries1 = new ArrayList<Country>();
    	continent = new Continent("Asia",10);
    	continent1 = new Continent("Europe",10);
    	c1 = new Country("China");
    	c2 = new Country("Japan");
    	c3 = new Country("Siam");
    	c4 = new Country("Korean");
    	c5 = new Country("India");
    	c6 = new Country("Iceland");
    	occupiedCountries1.add(c1);
		occupiedCountries1.add(c2);
		occupiedCountries1.add(c3);
    	c1.getConnectedCountries().add(c3);
		c1.getConnectedCountries().add(c4);
		c1.getConnectedCountries().add(c5);
		c3.getConnectedCountries().add(c4);
		p.setOccupiedCountries(occupiedCountries1);
    	continent.setCountry(c1);
    	continent.setCountry(c2);
    	continent.setCountry(c3);
    	continent.setCountry(c4);
    	continent.setCountry(c5);
    	continent.setRuler(p1);
    	c1.setContinent(continent);
    	c1.setRuler(p);
    	c2.setRuler(p);
    	c3.setRuler(p);
    	c4.setRuler(p);
    	c5.setRuler(p1);
    	c1.setIsOccupied(true);
    	c1.setArmyCount(10);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of setContinent method, of class Country.
     */
    @Test
    public void testSetContinent()
    {
        c1.setContinent(continent1);
        Continent result = c1.getContinent();
        assertEquals(continent1, result);
    }

    /**
     * Test of getName method, of class Country.
     */
    @Test
    public void testGetName()
    {
        String expResult = "China";
        String result = c1.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContinent method, of class Country.
     */
    @Test
    public void testGetContinent()
    {
        Continent result = c1.getContinent();
        assertEquals(continent, result);
    }

    /**
     * Test of getArmyCount method, of class Country.
     */
    @Test
    public void testGetArmyCount()
    {
    	int expResult = 10;
        int result = c1.getArmyCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setArmyCount method, of class Country.
     */
    @Test
    public void testSetArmyCount()
    {
    	int expResult = 15;
        int armyCount = 5;
        c1.setArmyCount(armyCount);
        int result = c1.getArmyCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of reduceArmyCount method, of class Country.
     */
    @Test
    public void testReduceArmyCount()
    {
    	int expResult = 5;
        int armyCount = 5;
        c1.reduceArmyCount(armyCount);
        int result = c1.getArmyCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of isIsOccupied method, of class Country.
     */
    @Test
    public void testIsIsOccupied()
    {
        boolean expResult = true;
        boolean result = c1.isIsOccupied();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isIsOccupied method, of class Country.
     */
    @Test
    public void testIsIsOccupied1()
    {
        
    	boolean expResult = false;
        boolean result = c2.isIsOccupied();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsOccupied method, of class Country.
     */
    @Test
    public void testSetIsOccupied()
    {
        c1.setIsOccupied(false);
        boolean expResult = false;
        boolean result = c1.isIsOccupied();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRuler method, of class Country.
     */
    @Test
    public void testGetRuler()
    {
        Player result = c1.getRuler();
        assertEquals(p, result);
    }

    /**
     * Test of setRuler method, of class Country.
     */
    @Test
    public void testSetRuler()
    {
    	Player result = c6.getRuler();
    	assertEquals(null, result);
        c6.setRuler(p1);
        result = c6.getRuler();
        assertEquals(p1, result);
    }

    /**
     * Test of getConnectedCountries method, of class Country.
     */
    @Test
    public void testGetConnectedCountries()
    {
        ArrayList<Country> expResult = new ArrayList<Country>();
        expResult.add(c3);
        expResult.add(c4);
        expResult.add(c5);
        ArrayList<Country> result = c1.getConnectedCountries();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConnectedCountries method, of class Country.
     */
    @Test
    public void testSetConnectedCountries()
    {
    	ArrayList<Country> result0 = c2.getConnectedCountries();
    	assertEquals(0,result0.size());
        ArrayList<Country> connectedCountries = new ArrayList<Country>();
        connectedCountries.add(c3);
        connectedCountries.add(c4);
        c2.setConnectedCountries(connectedCountries);
        ArrayList<Country> expResult = new ArrayList<Country>();
        expResult.add(c3);
        expResult.add(c4);
        ArrayList<Country> result = c2.getConnectedCountries();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConnectedCountry method, of class Country.
     */
    @Test
    public void testGetConnectedCountry()
    {
        Country result = c1.getConnectedCountry("Siam");
        assertEquals(c3, result);
    }
    
    /**
     * Test of getConnectedCountry method. Country given is not connected
     */
    @Test
    public void testGetConnectedCountry2()
    {
        Country result = c1.getConnectedCountry("Siam2");
        assertNotEquals(c3, result);
    }


    /**
     * Test of getConnectedOwned method, of class Country.
     */
    @Test
    public void testGetConnectedOwned()
    {
        ObservableList<Country> expResult = FXCollections.observableArrayList(c3,c4);
        ObservableList<Country> result = c1.getConnectedOwned();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConnectedEnemy method, of class Country.
     */
    @Test
    public void testGetConnectedEnemy()
    {
    	ObservableList<Country> expResult = FXCollections.observableArrayList(c5);
        ObservableList<Country> result = c1.getConnectedEnemy();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getConnected owned
     */
    @Test
    public void testGetConnectedEnemyArrayList()
    {
    	int expResult = 0;
    	ArrayList <Country> country = c3.getConnectedEnemyArrayList();
    	assertEquals(expResult, country.size());
    	
    	
    }
    
    @Test
    public void testGetConnectedOwnedArrayList()
    {
    	int expResult = 1;
    	ArrayList <Country> country = c3.getConnectedOwnedArrayList();
    	assertEquals(expResult, country.size());
    }
    
}
