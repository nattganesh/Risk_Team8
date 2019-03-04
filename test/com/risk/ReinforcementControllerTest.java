/**
 * This class test for reinforcement contoller
 * 
 * @author DKM
 *
 */
package com.risk;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.ReinforcementController;
import com.risk.model.MapModel;
import com.risk.model.card.Card;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;


public class ReinforcementControllerTest extends TestCase{
	
	 private ArrayList<Country> occupiedCountries;
	 private ArrayList<Continent> continents;
	 private ObservableList<Card> selectedCards;
	 private Player testPlayer1;
	 private Player testPlayer2;
	
	
	

	public void setUp() 
	{
		testPlayer1 = new Player("dummy player1");
		testPlayer2 = new Player("dummy player2");
		
	}

	public void tearDown() 
	{
		MapModel.getMapModel().getContinents().clear();
	}
	
	/**
	 *  testing reinforcement from 6 territory occupied
	 */
	 public void testReinforcementNumbOccupied() {
		
		occupiedCountries = new ArrayList<Country>();
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		
		testPlayer1.setOccupiedCountries(occupiedCountries);
		ReinforcementController rController = new ReinforcementController();
		assertEquals(2, rController.calculateReinforcementOccupiedTerritory(testPlayer1));
	
	}
	/**
	 *  testing reinforcement from 11 territory occupied
	 */
	 public void testReinforcementNumbOccupied1() {
		
		occupiedCountries = new ArrayList<Country>();
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		
		testPlayer1.setOccupiedCountries(occupiedCountries);
		ReinforcementController rController = new ReinforcementController();
		assertEquals(3, rController.calculateReinforcementOccupiedTerritory(testPlayer1));
	
	}
	
	/**
	 * testing reinforcement from continent control when both player doesn't own any continents
	 */
	 public void testReinforcementContinentControl() 
	{
		continents = new ArrayList<Continent>();
		Continent continent1 = new Continent("continent1", 10);
		Continent continent2 = new Continent("continent2", 10);
		
		Country country1 = new Country("country1","continent1");
		Country country2 = new Country("country2","continent2");
		Country country3 = new Country("country3","continent1");
		Country country4 = new Country("country4","continent2");
		Country country5 = new Country("country5","continent2");
		
		country1.setRuler(testPlayer1);
		country2.setRuler(testPlayer1);
		country3.setRuler(testPlayer2);
		country4.setRuler(testPlayer2);
		country5.setRuler(testPlayer1);
		
		continent1.setCountry(country1);
		continent1.setCountry(country2);
		continent1.setCountry(country3);
		continent2.setCountry(country4);
		continent2.setCountry(country5);
		
		MapModel.getMapModel().addContinent(continent1);
		MapModel.getMapModel().addContinent(continent2);
		
		ReinforcementController rController = new ReinforcementController();
		assertEquals(0, rController.calculateReinforcementContinentControl(testPlayer1));	
		assertEquals(0, rController.calculateReinforcementContinentControl(testPlayer2));	
			
	}
	
	
	/**
	 * testing reinforcement from continent control when both player owns 1 continents
	 */
	 public void testReinforcementContinentControl1() 
	{
		continents = new ArrayList<Continent>();
		Continent continent1 = new Continent("continent1", 10);
		Continent continent2 = new Continent("continent2", 10);
		
		Country country1 = new Country("country1","continent1");
		Country country2 = new Country("country2","continent2");
		Country country3 = new Country("country3","continent1");
		Country country4 = new Country("country4","continent2");
		Country country5 = new Country("country5","continent2");
		
		country1.setRuler(testPlayer1);
		country2.setRuler(testPlayer1);
		country3.setRuler(testPlayer1);
		country4.setRuler(testPlayer2);
		country5.setRuler(testPlayer2);
		
		continent1.setCountry(country1);
		continent1.setCountry(country2);
		continent1.setCountry(country3);
		continent2.setCountry(country4);
		continent2.setCountry(country5);
		
		MapModel.getMapModel().addContinent(continent1);
		MapModel.getMapModel().addContinent(continent2);
		
		ReinforcementController rController = new ReinforcementController();
		assertEquals(10, rController.calculateReinforcementContinentControl(testPlayer1));	
		assertEquals(10, rController.calculateReinforcementContinentControl(testPlayer2));	
			
	}
	
	/**
	 * testing reinforcement from continent control when player1 owns 2 continents and other owns No continent
	 */
	 public void testReinforcementContinentControl2() 
	{
		continents = new ArrayList<Continent>();
		Continent continent1 = new Continent("continent1", 10);
		Continent continent2 = new Continent("continent2", 10);
		Country country1 = new Country("country1","continent1");
		Country country2 = new Country("country2","continent2");
		Country country3 = new Country("country3","continent1");
		Country country4 = new Country("country4","continent2");
		Country country5 = new Country("country5","continent2");
		country1.setRuler(testPlayer1);
		country2.setRuler(testPlayer1);
		country3.setRuler(testPlayer1);
		country4.setRuler(testPlayer1);
		country5.setRuler(testPlayer1);
		
		
		continent1.setCountry(country1);
		continent1.setCountry(country2);
		continent1.setCountry(country3);
		continent2.setCountry(country4);
		continent2.setCountry(country5);
		
		MapModel.getMapModel().addContinent(continent1);
		MapModel.getMapModel().addContinent(continent2);
		
		ReinforcementController rController = new ReinforcementController();
		assertEquals(20, rController.calculateReinforcementContinentControl(testPlayer1));	
		assertEquals(0, rController.calculateReinforcementContinentControl(testPlayer2));
			
	}

	/**
	 * Reinforcement from 2nd exchange
	 */
	 public void testReinforcementTradedCard() 
	{
		ReinforcementController rController = new ReinforcementController();
		MapModel.getMapModel().setExchangeTime(2);
		
		assertEquals(15, rController.calculateReinforcementFromCards());
	}
	

	/**
	 * Reinforcement from 3rd exchange
	 */
	 public void testReinforcementTradedCard3() 
	{
		ReinforcementController rController = new ReinforcementController();
		MapModel.getMapModel().setExchangeTime(3);
		assertEquals(20, rController.calculateReinforcementFromCards());
	}
	
	/**
	 * Validate 3 of different kind of card
	 */
	 public void testCardValidation() {
		selectedCards = FXCollections.observableArrayList();
		selectedCards.add(new Card("category1", "blue"));
		selectedCards.add(new Card("category2", "blue"));
		selectedCards.add(new Card("category3", "blue"));
		ReinforcementController rController = new ReinforcementController();
		assertTrue(rController.cardValidation(selectedCards));
	}
	
	/**
	 * Validate 3 of the same kind of card
	 */
	 public void testCardValidation2() {
		selectedCards = FXCollections.observableArrayList();
		selectedCards.add(new Card("category1", "blue"));
		selectedCards.add(new Card("category1", "blue"));
		selectedCards.add(new Card("category1", "blue"));
		ReinforcementController rController = new ReinforcementController();
		assertTrue(rController.cardValidation(selectedCards));
	}

	
	/**
	 * Validate non exchangeable card
	 */
	 public void testCardValidation3() {
		selectedCards = FXCollections.observableArrayList();
		selectedCards.add(new Card("category3", "blue"));
		selectedCards.add(new Card("category3", "blue"));
		selectedCards.add(new Card("category2", "blue"));
		ReinforcementController rController = new ReinforcementController();
		assertFalse(rController.cardValidation(selectedCards));
	}
	
	/**
	 * Validate non exchangeable card
	 */
	 public void testCardValidation4() {
		selectedCards = FXCollections.observableArrayList();
		selectedCards.add(new Card("category3", "blue"));
		selectedCards.add(new Card("category2", "blue"));
		selectedCards.add(new Card("category2", "blue"));
		ReinforcementController rController = new ReinforcementController();
		assertFalse(rController.cardValidation(selectedCards));
	}
	
	/**
	 * Validate non exchangeable card
	 */
	 public void testCardValidation5() {
		selectedCards = FXCollections.observableArrayList();
		selectedCards.add(new Card("category4", "blue"));
		selectedCards.add(new Card("category3", "blue"));
		selectedCards.add(new Card("category4", "blue"));
		ReinforcementController rController = new ReinforcementController();
		assertFalse(rController.cardValidation(selectedCards));
	}
	
	
}
