
/**
 * This method is a test for output file
 * @author DKM
 *
 */
package com.risk.model.utilities.generateOutputFile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.controller.FortificationController;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.ObservableList;


public class OutputTest {
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    /**
     * this method clears the MapModel and PlayerPhaseModel before each test
     */
    @Before
    public void setUp()
    {
    
    	PlayerPhaseModel.getPlayerModel().getPlayers().clear();
    	MapModel.getMapModel().getCountries().clear();
    	
    }
    
    @After
    public void tearDown()
    {

    }
    
    /**
     * This method test that output method correctly runs
     */
    @Test
    public void testGenerate1()
    {
    	
    	assertTrue(Output.generate("OutPutTestFile"));
    }
    
    /**
     * this method test the Output.generate() with correct MapModel
     * If this method runs correctly it should return true
     */
    @Test
    public void testGenerate2()
    {
    	
    	ObservableList<Continent> continents = MapModel.getMapModel().getContinents();
    	ArrayList<Country> country = MapModel.getMapModel().getCountries();
    	
    	Continent continent1 = new Continent("dummy1", 10);
    	Continent continent2 = new Continent("dummy2", 10);
    	
    	continents.add(continent1);
    	continents.add(continent2);
    	
    	
    	
    	Country country1 = new Country("country1");
    	Country country2 = new Country("country2");
    	
    	country1.setContinent(continent1);
    	country2.setContinent(continent2);
    	
    	country1.getConnectedCountries().add(country2);
    	country2.getConnectedCountries().add(country1);
    	
    	country.add(country1);
    	country.add(country2);
    	
    	assertTrue(Output.generate("OutPutTestFile"));
    }
    
    /**
     * this method test the Output.generate with incorrect MapModel
     * If this method runs correctly it should return false
     */
    @Test
    public void testGenerate3()
    {
    	
    	ObservableList<Continent> continents = MapModel.getMapModel().getContinents();
    	ArrayList<Country> country = MapModel.getMapModel().getCountries();
    	
    	Continent continent1 = new Continent("dummy1", 10);
    	Continent continent2 = new Continent("dummy2", 10);
    	
    	continents.add(continent1);
    	continents.add(continent2);
    	
    	
    	
    	Country country1 = new Country("country1");
    	Country country2 = new Country("country2");
    	    	
    	country1.getConnectedCountries().add(country2);
    	country2.getConnectedCountries().add(country1);
    	
    	country.add(country1);
    	country.add(country2);
    	
    	assertFalse(Output.generate("OutPutTestFile"));
    }
  
}
