/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

import com.risk.controller.FortificationController;
import com.risk.controller.ReinforcementController;
import com.risk.model.MapModel;
import com.risk.model.card.Card;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
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
 *
 * @author Natt
 * @author Tianyi
 */
public class PlayerTest {
	private Continent continent;
	private Continent continent1;
    private Player p;
    private Player p2;
    private ArrayList<Country> occupiedCountries;
    private ArrayList<Country> occupiedCountries1;
    private ArrayList<Country> occupiedCountries2;
    private Country c1;
	private Country c2;
	private Country c3;
	private Country c4;
	private Country c5;
	private Country c6;
	private ObservableList<Card> cards = FXCollections.observableArrayList();
    public PlayerTest()
    {
    }
    
    @Before
    public void setUp()
    {
		p = new Player("Green");
		p2 = new Player("Red");
		occupiedCountries1 = new ArrayList<Country>();
		occupiedCountries = new ArrayList<Country>();
		continent = new Continent("Asia",10);
    	continent1 = new Continent("Europe",10);
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
		continent.setCountry(c1);
    	continent.setCountry(c2);
    	continent.setCountry(c3);
    	continent.setCountry(c4);
    	continent1.setCountry(c5);
    	continent1.setCountry(c6);
    	c1.setContinent(continent);
    	c2.setContinent(continent);
    	c3.setContinent(continent);
    	c4.setContinent(continent);
    	c5.setContinent(continent1);
    	c6.setContinent(continent1);
		p.setOccupiedCountries(occupiedCountries1);
		c1.setRuler(p);
		c1.setIsOccupied(true);
        c2.setRuler(p);
        c3.setRuler(p);
        c6.setRuler(p);
		p.setStartingPoints(20);
		occupiedCountries2 = new ArrayList<Country>();
		occupiedCountries2.add(c4);
		occupiedCountries2.add(c5);
		cards.add(new Card("Infantry"));
		cards.add(new Card("Cavalry"));
		cards.add(new Card("Artillery"));
		p.setCards(cards);
		p2.setOccupiedCountries(occupiedCountries2);
		c4.setRuler(p2);
		c4.setIsOccupied(true);
		c4.setArmyCount(1);
	    c5.setRuler(p2);
    }
    
    @After
    public void tearDown()
    {
    	MapModel.getMapModel().getContinents().clear();
    }

    /**
     * Test of setTotalArmy method, of class Player.
     */
    @Test
    public void testSetTotalArmy()
    {
        int count = 0;
        p.setTotalArmy(count);
        assertEquals(0, p.getTotalArmy());
    }
    
    /**
     * Test of setTotalArmy method, of class Player.
     */
    @Test
    public void testSetTotalArmy1()
    {
        int count = 4;
        p.setTotalArmy(count);
        assertEquals(4, p.getTotalArmy());
    }

    /**
     * Test of reduceTotalArmy method, of class Player.
     */
    @Test
    public void testReduceTotalArmy()
    {
    	p.setTotalArmy(4);
    	int count = 1;
        p.reduceTotalArmy(count);
        assertEquals(3, p.getTotalArmy());
    }

    /**
     * Test of getTotalArmy method, of class Player.
     */
    @Test
    public void testGetTotalArmy()
    {
    	 p.setTotalArmy(4);
         int count = 1;
         p.reduceTotalArmy(count);
         assertEquals(3, p.getTotalArmy());
    }

    /**
     * Test of setStartingPoints method, of class Player.
     */
    @Test
    public void testSetStartingPoints()
    {
    	int i = 20;
        p.setStartingPoints(i);
        assertEquals(20, p.getStartingPoints());
    }

    /**
     * Test of getStartingPoints method, of class Player.
     */
    @Test
    public void testGetStartingPoints()
    {
    	int i = 20;
        p.setStartingPoints(i);
        assertEquals(20, p.getStartingPoints());
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName()
    {
        String expResult = "Green";
        String result = p.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOccupiedCountries method, of class Player.
     */
    @Test
    public void testGetOccupiedCountries()
    {
        ArrayList<Country> expResult = new ArrayList<Country>() ;
        expResult.add(c1);
        expResult.add(c2);
        expResult.add(c3);
        expResult.add(c6);
        ArrayList<Country> result = p.getOccupiedCountries();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCountry method, of class Player.
     */
    @Test
    public void testAddCountry()
    {
		ArrayList<Country> expResult = new ArrayList<Country>();
		expResult.add(c1);
		expResult.add(c2);
		expResult.add(c3);
		expResult.add(c6);
		expResult.add(c5);
		p.addCountry(c5);
		ArrayList<Country> result = p.getOccupiedCountries();
		assertEquals(expResult, result);
    }

    /**
     * Test of removeCountry method, of class Player.
     */
    @Test
    public void testRemoveCountry()
    {
    	ArrayList<Country> expResult = new ArrayList<Country>();
		expResult.add(c1);
		expResult.add(c2);
		expResult.add(c6);
		p.removeCountry(c3);
		ArrayList<Country> result = p.getOccupiedCountries();
		assertEquals(expResult, result);
    }

    /**
     * Test of numbOccupied method, of class Player.
     */
    @Test
    public void testNumbOccupied()
    {
        int expResult = 4;
        int result = p.numbOccupied();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOccupiedCountries method, of class Player.
     */
    @Test
    public void testSetOccupiedCountries()
    {
        p2.setOccupiedCountries(occupiedCountries2);
        ArrayList<Country> expResult = new ArrayList<Country>();
		expResult.add(c4);
		expResult.add(c5);
		ArrayList<Country> result = p2.getOccupiedCountries();
		assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Player.
     */
    @Test
    public void testGetCountry()
    {
       Country result = p.getCountry("China");
       assertEquals(c1, result);
    }

    /**
     * Test of getCards method, of class Player.
     */
    @Test
    public void testGetCards()
    {
        ObservableList<Card> expResult = FXCollections.observableArrayList(new Card("Infantry"), new Card("Cavalry"), new Card("Artillery"));
        ObservableList<Card> result = p.getCards();
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), result.size());
		for (int i = 0; i < expResult.size(); i++) 
		{
			Card t1 = expResult.get(i);
			Card t2 = result.get(i);
			assertEquals(t1.getCatagory(), t2.getCatagory());
		}
    }

    /**
     * Test of addCard method, of class Player.
     */
    @Test
    public void testAddCard()
    {
    	ObservableList<Card> expResult = FXCollections.observableArrayList(new Card("Infantry"), new Card("Cavalry"), new Card("Artillery"),new Card("Wild"));
        p.addCard(new Card("Wild"));
        ObservableList<Card> result = p.getCards();
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), result.size());
		for (int i = 0; i < expResult.size(); i++) 
		{
			Card t1 = expResult.get(i);
			Card t2 = result.get(i);
			assertEquals(t1.getCatagory(), t2.getCatagory());
		}
    }

    /**
     * Test of removeCard method, of class Player.
     */
    @Test
    public void testRemoveCard()
    {
    	ObservableList<Card> expResult = FXCollections.observableArrayList(new Card("Infantry"), new Card("Cavalry"));
        p.removeCard(new Card("Artillery"));
        ObservableList<Card> result = p.getCards();
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), result.size());
		for (int i = 0; i < expResult.size(); i++) 
		{
			Card t1 = expResult.get(i);
			Card t2 = result.get(i);
			assertEquals(t1.getCatagory(), t2.getCatagory());
		}
    }

    /**
     * Test of setCards method, of class Player.
     */
    @Test
    public void testSetCards()
    {
        p2.setCards(cards);
        ObservableList<Card> expResult = FXCollections.observableArrayList(new Card("Infantry"), new Card("Cavalry"), new Card("Artillery"));
        ObservableList<Card> result = p2.getCards();
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), result.size());
       
		for (int i = 0; i < expResult.size(); i++) 
		{
			Card t1 = expResult.get(i);
			Card t2 = result.get(i);
			assertEquals(t1.getCatagory(), t2.getCatagory());
		}

    }

    /**
     * Test of isPlayerLost method, of class Player.
     */
    @Test
    public void testIsPlayerLost()
    {
        boolean expResult = false;
        boolean result = p.isPlayerLost();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayerLost method, of class Player.
     */
    @Test
    public void testSetPlayerLost()
    {
    	p.setPlayerLost(true);
        boolean expResult = true;
        boolean result = p.isPlayerLost();
        assertEquals(expResult, result);
    }

    /**
     * Test of attack method, of class Player.
     */
    @Test
    public void testAttack()
    {
    	c1.setArmyCount(4);
        p.attack(c1, c4, 1);
        int expResult = 0;
        int result = c4.getArmyCount();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of attack method, of class Player.
     */
    @Test
    public void testAttack1()
    {
    	c1.setArmyCount(4);
        p.attack(c1, c4, 2);
        Player result = c4.getRuler();
        assertEquals(p, result);
    }
    
    /**
     * Test of attack method, of class Player.
     */
    @Test
    public void testAttack2()
    {
    	c1.setArmyCount(4);
        p.attack(c1, c4, 3);
        int expResult = 3;
        int result = c1.getArmyCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of reinforce method, of class Player.
     */
    @Test
    public void testReinforce()
    {
    	c1.setArmyCount(4);
        int Armyinput = 1;
        p.reinforce(c1, Armyinput);
        int expResult = 5;
        int result = c1.getArmyCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of fortify method, of class Player.
     */
    @Test
    public void testFortify()
    {
    	c1.setArmyCount(4);
    	c3.setArmyCount(1);
        int Armyinput = 2;
        p.fortify(c1, c3, Armyinput);
        int result = c1.getArmyCount();
        int result1 = c3.getArmyCount();
        assertEquals(2, result);
        assertEquals(3, result1);
    }

    /**
     * Test of getCountriesArrivedbyPath method, of class Player.
     */
    @Test
    public void testGetCountriesArrivedbyPath1()
    {
    	Country firstCountry = p.getCountry("China");
        ArrayList<Country> expected = new ArrayList<>();
        expected.add(c3);
        expected.add(c6);
        ArrayList<Country> result = new ArrayList<>();
        result = p.getCountriesArrivedbyPath(firstCountry, firstCountry, result);
        assertEquals(expected, result);
    }
    
    /**
     * Test of getCountriesArrivedbyPath method, of class Player.
     */
    @Test
    public void testGetCountriesArrivedbyPath2()
    {
        ArrayList<Country> result = new ArrayList<>();
        Country testCountry = p.getCountry("Quebec");
        result = p.getCountriesArrivedbyPath(testCountry, testCountry, result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of isCountryDuplicated method, of class Player.
     */
    @Test
    public void testIsCountryDuplicated()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(c3);
        countries.add(c6);
        boolean expResult = false;
        boolean result = p.isCountryDuplicated(c3, c1, countries);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isCountryDuplicated method, of class Player.
     */
    @Test
    public void testIsCountryDuplicated2()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(c3);
        boolean expResult = true;
        boolean result = p.isCountryDuplicated(c6, c1, countries);
        assertEquals(expResult, result);
    }

    
    /**
     * Method test for if there are countries accessible to each other and have enough armies
     */
    @Test
    public void testIsAnyCountriesConnected() 
    {
    	c3.setArmyCount(2);
    	c6.setArmyCount(1);
    	assertTrue(p.isAnyCountriesConnected());
    }
    
    /**
     * Method test for if there are countries accessible to each other and have enough armies
     */
    @Test
    public void testIsAnyCountriesConnected2() 
    {
    	c3.setArmyCount(1);
    	c6.setArmyCount(1);
    	assertFalse(p.isAnyCountriesConnected());
    }
    
    /**
     * Method test for if there are countries accessible to each other and have enough armies
     */
    @Test
    public void testIsAnyCountriesConnected3() 
    {
    	c4.setArmyCount(2);
    	c5.setArmyCount(2);
    	assertFalse(p2.isAnyCountriesConnected());
    }
    
    /**
     * Method test for calculation of army according to the occupy of continents
     */
    @Test
    public void testCalculateReinforcementContinentControl()
    {
    	int expResult = 0;
    	int result = p.calculateReinforcementContinentControl();
    	assertEquals(expResult, result);
    }
    
    /**
     * Method test for calculation of army according to the occupy of continents
     */
    @Test
    public void testCalculateReinforcementContinentControl1()
    {
    	c4.setRuler(p);
    	int expResult = 10;
    	int result = p.calculateReinforcementContinentControl();
    	assertEquals(expResult, result);
    }
    
    /**
     * Method test for calculation of army according to the occupy of continents
     */
    @Test
    public void testCalculateReinforcementOccupiedTerritory()
    {
    	int expResult = 1;
    	int result = p.calculateReinforcementOccupiedTerritory();
    	assertEquals(expResult, result);
    }
    
    /**
     * Method test for calculation of army according to the occupy of continents
     */
    @Test
    public void testCalculateReinforcementOccupiedTerritory1()
    {
        occupiedCountries.add(new Country("A"));
        occupiedCountries.add(new Country("B"));
        occupiedCountries.add(new Country("C"));
        occupiedCountries.add(new Country("D"));
        occupiedCountries.add(new Country("E"));
        occupiedCountries.add(new Country("F"));
        occupiedCountries.add(new Country("G"));
        occupiedCountries.add(new Country("H"));
        occupiedCountries.add(new Country("I"));
        occupiedCountries.add(new Country("J"));
        p2.setOccupiedCountries(occupiedCountries);
    	int expResult = 3;
    	int result = p2.calculateReinforcementOccupiedTerritory();
    	assertEquals(expResult, result);
    }
    
    /**
     * Method test for calculation of army according to cards exchanged
     */
    @Test
    public void testCalculateReinforcementFromCards()
    {
        MapModel.getMapModel().setExchangeTime(2);
    	int expResult = 15;
    	int result = p2.calculateReinforcementFromCards();
    	assertEquals(expResult, result);
    }
    
    /**
     * Method test for validation of attack and defend countries
     */
    @Test
    public void testValidateTerritorySelections()
    {
    	Country test = null;
    	boolean expResult = false;
        boolean result = p.validateTerritorySelections(test, c4);
        assertEquals(expResult, result);
    }
    
    /**
     * Method test for validation of attacker and defender countries
     */
    @Test
    public void testValidateTerritorySelections1()
    {
    	boolean expResult = true;
        boolean result = p.validateTerritorySelections(c1, c4);
        assertEquals(expResult, result);
    }
    
    /**
     * Method test for validation of the number of dice of attacker and defender
     */
    @Test
    public void testValidateDiceSelections()
    {
    	boolean expResult = true;
        boolean result = p.validateDiceSelections(3, 2);
        assertEquals(expResult, result);
    }
    
    /**
     * Method test for validation of the number of dice of attacker and defender
     */
    @Test
    public void testValidateDiceSelections1()
    {
    	boolean expResult = false;
        boolean result = p.validateDiceSelections(null, 2);
        assertEquals(expResult, result);
    }
    
    /**
     * Method test for validation of the number of armies of attacker
     */
    @Test
    public void testValidateAttackerHasEnoughArmy()
    {
    	boolean expResult = false;
        boolean result = p.validateAttackerHasEnoughArmy(c1);
        assertEquals(expResult, result);
    }
    
    /**
     * Method test for validation of the number of armies of attacker
     */
    @Test
    public void testValidateAttackerHasEnoughArmy1()
    {
    	c1.setArmyCount(10);
    	boolean expResult = true;
        boolean result = p.validateAttackerHasEnoughArmy(c1);
        assertEquals(expResult, result);
    }
    
}
