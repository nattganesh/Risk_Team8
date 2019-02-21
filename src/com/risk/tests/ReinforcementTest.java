/**
 * 
 */
package com.risk.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.army.Player;
import com.risk.map.Country;

/**
 * @author DKM
 *
 */
class ReinforcementTest {
	
	 private ArrayList<Country> occupiedCountries;
	 private Player testPlayer;
	
	 
	public int ReinforcementHelper() {
		int Expected = (int) Math.floor(occupiedCountries.size() / 3);
		if (Expected / 3 < 3) {
			Expected = 3;
		} 
		return Expected;
	}
	
	@BeforeEach
	public void setUp() {
		testPlayer = new Player("dummy player");
		occupiedCountries = new ArrayList<>();
		
	}
	
	@Test public void testGetReinforcement1() {
		
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		testPlayer.setOccupiedCountries(occupiedCountries);
		
	
		assertEquals(ReinforcementHelper(), testPlayer.getReinforcement());
	}
	@Test public void testGetReinforcement2() {
		
		occupiedCountries.add(new Country("dummy country","dummy continent"));
		testPlayer.setOccupiedCountries(occupiedCountries);
		assertEquals(ReinforcementHelper(), testPlayer.getReinforcement());
	}
	
	@Test public void testGetReinforcement3() {
		
		testPlayer.setOccupiedCountries(occupiedCountries);
		assertEquals(ReinforcementHelper(), testPlayer.getReinforcement());
	}
}
