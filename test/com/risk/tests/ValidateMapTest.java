/**
 * 
 */
package com.risk.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;
import com.risk.utilities.RiskMap;

/**
 * @author DKM
 *
 */
class ValidateMapTest {
	private static MapModel map;
	private RiskMap riskMap;
	

	// addedd North America,Alaska two times
	private String DuplicateCountry = "src/com/risk/run/inputtext/DuplicateCountry.txt";
	
	// added North America,Alaska2 --> Cannot find exception due to Set Neighbors not found (this exception does not correlate with the actual error)
	private String ExceedsContinentLimitFile = "src/com/risk/run/inputtext/ExceedsContinentLimit.txt";
	
	// added North America2,Alaska
	private String NonExistentContinent = "src/com/risk/run/inputtext/NonExistentContinent.txt";
	
	// removed all neighbours of indonesia --> nothing was thrown
	private String NoConnectivity =  "src/com/risk/run/inputtext/NoConnectivity.txt";
	
	/**
	 * @see Instantiates the model and parser.
	 */
	@BeforeEach
	public void setUp() {
		map = new MapModel();
	}

	/**
	 * Tests parser.setCountriesInContinents(Scanner) for duplicate country in the map
	 */
	@Test
	public void testDuplicateCountry() {
		riskMap = new RiskMap(map, DuplicateCountry);
		assertThrows(DuplicatesException.class,
				() -> riskMap.setUp());
	}


	/**
	 * Test for file that has a continent that does not exist
	 */
	@Test
	public void testNonExistentContinentFile() {
		riskMap = new RiskMap(map, NonExistentContinent);
		assertThrows(CannotFindException.class,
				() -> riskMap.setUp());
	}

	/**
	 * Tests for file that has exceeding number of country in the continent
	 */
	@Test
	public void testExceedsContinentLimitFile() {
		riskMap = new RiskMap(map,ExceedsContinentLimitFile);
		assertThrows(CountLimitException.class,
				() -> riskMap.setUp());
	}
	
	/**
	 * Tests for file that has no connectivity
	 */
	@Test
	public void testConnectivity() {
		riskMap = new RiskMap(map,NoConnectivity);
		assertThrows(CountLimitException.class,
				() -> riskMap.setUp());
	}
}
