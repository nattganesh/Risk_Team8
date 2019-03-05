package com.risk;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.FortificationController;
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

    public void setUp()
    {
        firstPlayer = new Player("Green");
        occupiedCountries1 = new ArrayList<Country>();
        Country c1 = new Country("China", "Asia");
        Country c2 = new Country("Quebec", "North America");
        Country c3 = new Country("Siam", "Asia");
        Country c4 = new Country("India", "Asia");
        Country c5 = new Country("Congo", "Africa");
        Country c6 = new Country("Indonesia", "Australia");
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

}
