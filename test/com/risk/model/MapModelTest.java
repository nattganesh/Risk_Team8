/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for map model
 *
 * @author Natt
 * @author Tianyi
 */
public class MapModelTest 
{
    private MapModel mapModel;
    private Country country1;
    private Continent continent1;
    public MapModelTest()
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
    	mapModel = new MapModel();
    	country1 = new Country("China");
    	continent1 = new Continent("Asia", 10);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getExchangeTime method, of class MapModel.
     */
    @Test
    public void testGetExchangeTime()
    {
        int expResult =0;
        int result = mapModel.getExchangeTime();
        assertEquals(expResult,result);
    }

    /**
     * Test of setExchangeTime method, of class MapModel.
     */
    @Test
    public void testSetExchangeTime()
    {
        int exchange = 2;
        mapModel.setExchangeTime(exchange);
        int expResult =2;
        int result = mapModel.getExchangeTime();
        assertEquals(expResult,result);
    }


    /**
     * Test of getCountries method, of class MapModel.
     */
    @Test
    public void testGetCountries()
    {
        ArrayList<Country> result = mapModel.getCountries();
        assertTrue(result.isEmpty());
    }
    
    /**
     * Test of getCountries method, of class MapModel.
     */
    @Test
    public void testGetCountries1()
    {
    	mapModel.addCountry(country1);
    	ArrayList<Country> expResult = new ArrayList<Country>();
    	expResult.add(country1);
    	ArrayList<Country> result = mapModel.getCountries();
    	assertEquals(expResult,result);
    }

    /**
     * Test of getContinents method, of class MapModel.
     */
    @Test
    public void testGetContinents()
    {
        ObservableList<Continent> result = mapModel.getContinents();
        assertTrue(result.isEmpty());
    }
    
    /**
     * Test of getContinents method, of class MapModel.
     */
    @Test
    public void testGetContinents1()
    {
    	mapModel.addContinent(continent1);
    	ObservableList<Continent> expResult = FXCollections.observableArrayList(continent1);
        ObservableList<Continent> result = mapModel.getContinents();
        assertEquals(expResult,result);
    }

    
}
