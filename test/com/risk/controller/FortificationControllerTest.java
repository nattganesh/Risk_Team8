
package com.risk.controller;


import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.risk.model.map.Country;
import com.risk.model.player.Player;

import static org.junit.Assert.*;

/**
 * This class test for fortification controller
 *
 * @author DKM
 */
public class FortificationControllerTest {

	 
	 private ArrayList <Country> connected;
	 private Country selectedCountry;
	 
	@BeforeEach
	public void setUp() {
		selectedCountry = new Country("country0", "continent1");
		selectedCountry.setRuler(new Player("red"));
		connected = new ArrayList<Country>();
	}

	/**
	 * test for number of connected country you own, which is 1
	 */
    @Test 
    public void testgetConnectedCountryYouOwn() {

    	Country country1 = new Country("country1", "continent1");
    	Country country2 = new Country("country2", "continent1");
    	
    	country1.setRuler(new Player("blue"));
    	country2.setRuler(new Player("red"));
    	
    	connected.add(country1);
    	connected.add(country2);

    	selectedCountry.setConnectedCountries(connected);
    	
    	FortificationController fController = new FortificationController();
    	assertEquals(1, fController.getConnectedCountryYouOwn(selectedCountry).size());    	
    	
    	
    }
    
	/**
	 * test for number of connected country you own, which is 6
	 */
    @Test 
    public void testgetConnectedCountryYouOwn1() {

    	Country country1 = new Country("country1", "continent1");
    	Country country2 = new Country("country2", "continent1");
    	Country country3 = new Country("country3", "continent2");
    	Country country4 = new Country("country4", "continent1");
    	Country country5 = new Country("country5", "continent1");
    	Country country6 = new Country("country6", "continent2");
    	Country country7 = new Country("country7", "continent3");
    	Country country8 = new Country("country8", "continent3");
    	Country country9 = new Country("country9", "continent3");
    	
    	
    	country1.setRuler(new Player("blue"));
    	country2.setRuler(new Player("red"));
    	country3.setRuler(new Player("red"));
    	country4.setRuler(new Player("red"));
    	country5.setRuler(new Player("red"));
    	country6.setRuler(new Player("red"));
    	country7.setRuler(new Player("red"));
    	country8.setRuler(new Player("yellow"));
    	country9.setRuler(new Player("green"));

    	connected.add(country1);
    	connected.add(country2);
    	connected.add(country3);
    	connected.add(country4);
    	connected.add(country5);
    	connected.add(country6);
    	connected.add(country7);
    	connected.add(country8);
    	connected.add(country9);
    	
    	selectedCountry.setConnectedCountries(connected);
    	
    	FortificationController fController = new FortificationController();
    	assertEquals(6, fController.getConnectedCountryYouOwn(selectedCountry).size());    	
    	
    	
    }
}
