package com.risk;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.FortificationController;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * This class test for fortification controller
 *
 * @author DKM
 * @author Tianyi
 */
public class FortificationControllerTest extends TestCase {

    private String countryName;
    private Player firstPlayer;
    private Player secondPlayer;
    private ArrayList<Country> occupiedCountries1;
    private ArrayList<Country> occupiedCountries2;
    private Country firstCountry;
    private Country c1;
	private Country c2;
	private Country c3;
	private Country c4;
	private Country c5;
	private Country c6;

    public void setUp()
    {
        firstPlayer = new Player("Green");
        occupiedCountries1 = new ArrayList<Country>();
        c1 = new Country("China");
        c2 = new Country("Quebec");
        c3 = new Country("Siam");
        c4 = new Country("India");
        c5 = new Country("Congo");
        c6 = new Country("Indonesia");
        occupiedCountries1.add(c1);
        occupiedCountries1.add(c2);
        occupiedCountries1.add(c3);
        occupiedCountries1.add(c6);
        c1.getConnectedCountries().add(c3);
        c1.getConnectedCountries().add(c4);
        c3.getConnectedCountries().add(c4);
        c3.getConnectedCountries().add(c6);
        firstPlayer.setOccupiedCountries(occupiedCountries1);
        c1.setRuler(firstPlayer);
        c2.setRuler(firstPlayer);
        c3.setRuler(firstPlayer);
        c6.setRuler(firstPlayer);
        secondPlayer = new Player("Red");
        occupiedCountries2 = new ArrayList<Country>();
        occupiedCountries2.add(c4);
        occupiedCountries2.add(c5);
        secondPlayer.setOccupiedCountries(occupiedCountries2);
        c4.setRuler(secondPlayer);
        c5.setRuler(secondPlayer);
        countryName = "China";
        firstCountry = firstPlayer.getCountry(countryName);

    }

    /**
     * Method test for all countries arrived by path for fortification
     */
    public void testGetCountriesArrivedbyPath1()
    {
        ArrayList<Country> expected = new ArrayList<>();
        expected.add(firstPlayer.getCountry("Siam"));
        expected.add(firstPlayer.getCountry("Indonesia"));
        ArrayList<Country> result = new ArrayList<>();
        FortificationController fController = new FortificationController();
        result = fController.getCountriesArrivedbyPath(firstCountry, firstCountry, result);
        assertEquals(expected, result);
    }

    /**
     * Method test for all countries arrived by path for fortification
     */
    public void testGetCountriesArrivedbyPath2()
    {
        ArrayList<Country> result = new ArrayList<>();
        Country testCountry = firstPlayer.getCountry("Quebec");
        FortificationController fController = new FortificationController();
        result = fController.getCountriesArrivedbyPath(testCountry, testCountry, result);
        assertTrue(result.isEmpty());
    }
    
    /**
     * Method test for if there are countries accessible to each other and have enough armies
     */
    public void testIsAnyCountriesConnected() 
    {
    	c3.setArmyCount(2);
    	c6.setArmyCount(1);
    	FortificationController fController = new FortificationController();
    	assertTrue(fController.isAnyCountriesConnected(firstPlayer));
    }
    
    /**
     * Method test for if there are countries accessible to each other and have enough armies
     */
    public void testIsAnyCountriesConnected2() 
    {
    	c3.setArmyCount(1);
    	c6.setArmyCount(1);
    	FortificationController fController = new FortificationController();
    	assertFalse(fController.isAnyCountriesConnected(firstPlayer));
    }
    
    /**
     * Method test for if there are countries accessible to each other and have enough armies
     */
    public void testIsAnyCountriesConnected3() 
    {
    	c4.setArmyCount(2);
    	c5.setArmyCount(2);
    	FortificationController fController = new FortificationController();
    	assertFalse(fController.isAnyCountriesConnected(secondPlayer));
    }


}
