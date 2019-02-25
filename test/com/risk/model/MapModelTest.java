/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import com.risk.map.Continent;
import com.risk.map.Country;
import java.util.ArrayList;
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
     * Test of addCountry method, of class MapModel.
     */
    @Test
    public void testAddCountry()
    {
        System.out.println("addCountry");
        Country country = null;
        MapModel instance = new MapModel();
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
        MapModel instance = new MapModel();
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
        MapModel instance = new MapModel();
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
        MapModel instance = new MapModel();
        ArrayList<Continent> expResult = null;
        ArrayList<Continent> result = instance.getContinents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
