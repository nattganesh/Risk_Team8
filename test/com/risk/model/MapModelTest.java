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
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natt
 */
public class MapModelTest {
    
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
        System.out.println("getExchangeTime");
        MapModel instance = null;
        int expResult = 0;
        int result = instance.getExchangeTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExchangeTime method, of class MapModel.
     */
    @Test
    public void testSetExchangeTime()
    {
        System.out.println("setExchangeTime");
        int exchange = 0;
        MapModel instance = null;
        instance.setExchangeTime(exchange);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCountry method, of class MapModel.
     */
    @Test
    public void testAddCountry()
    {
        System.out.println("addCountry");
        Country country = null;
        MapModel instance = null;
        instance.addCountry(country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addContinent method, of class MapModel.
     */
    @Test
    public void testAddContinent()
    {
        System.out.println("addContinent");
        Continent continent = null;
        MapModel instance = null;
        instance.addContinent(continent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountries method, of class MapModel.
     */
    @Test
    public void testGetCountries()
    {
        System.out.println("getCountries");
        MapModel instance = null;
        ArrayList<Country> expResult = null;
        ArrayList<Country> result = instance.getCountries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContinents method, of class MapModel.
     */
    @Test
    public void testGetContinents()
    {
        System.out.println("getContinents");
        MapModel instance = null;
        ObservableList<Continent> expResult = null;
        ObservableList<Continent> result = instance.getContinents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMapModel method, of class MapModel.
     */
    @Test
    public void testGetMapModel()
    {
        System.out.println("getMapModel");
        MapModel expResult = null;
        MapModel result = MapModel.getMapModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class MapModel.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        MapModel instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
