/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.map;

import com.risk.model.player.Player;
import com.risk.model.player.Player;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for continent
 * 
 * @author Natt
 * @author Tianyi
 */
public class ContinentTest {
    private Continent continent;
    private Country c1;
    private Country c2;
    private Country c3;
    private Player p;
    private Player p1;
    public ContinentTest()
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
    	continent = new Continent("Asia",10);
    	c1 = new Country("China");
    	c2 = new Country("Japan");
    	c3 = new Country("Siam");
    	continent.setCountry(c1);
    	continent.setCountry(c2);
    	p = new Player("Green");
    	p1 = new Player("Red");
    	continent.setRuler(p1);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getName method, of class Continent.
     */
    @Test
    public void testGetName()
    {
        String expResult = "Asia";
        String result = continent.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountries method, of class Continent.
     */
    @Test
    public void testGetCountries()
    {
        ArrayList<Country> expResult = new ArrayList<Country>();
        expResult.add(c1);
        expResult.add(c2);
        ArrayList<Country> result = continent.getCountries();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRuler method, of class Continent.
     */
    @Test
    public void testSetRuler()
    {
        continent.setRuler(p);
        Player result = continent.getRuler();
        assertEquals(p, result);
    }

    /**
     * Test of getRuler method, of class Continent.
     */
    @Test
    public void testGetRuler()
    {
    	 Player result = continent.getRuler();
         assertEquals(p1, result);
    }

    /**
     * Test of setCountry method, of class Continent.
     */
    @Test
    public void testSetCountry()
    {
       continent.setCountry(c3);
       ArrayList<Country> expResult = new ArrayList<Country>();
       expResult.add(c1);
       expResult.add(c2);
       expResult.add(c3);
       ArrayList<Country> result = continent.getCountries();
       assertEquals(expResult, result);
    }

    /**
     * Test of getPointsWhenFullyOccupied method, of class Continent.
     */
    @Test
    public void testGetPointsWhenFullyOccupied()
    {
    	int expResult = 10;
        int result = continent.getPointsWhenFullyOccupied();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPointsWhenFullyOccupied method, of class Continent.
     */
    @Test
    public void testSetPointsWhenFullyOccupied()
    {
    	int pointsWhenFullyOccupied = 20;
        continent.setPointsWhenFullyOccupied(pointsWhenFullyOccupied);
        int expResult = 20;
        int result = continent.getPointsWhenFullyOccupied();
        assertEquals(expResult, result);
    }
    
}
