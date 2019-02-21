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
import com.risk.model.Model;
import com.risk.utilities.RiskMapParser;

/**
 * @author DKM
 *
 */
class FileParsingTest {
	private static Model model;
	private RiskMapParser parser;
	
	// These are current testing
	private String DuplicateFile = "src/com/risk/run/inputtext/DuplicateCountry.txt";
	private String EmptyFile = "src/com/risk/run/inputtext/EmptyFile.txt";
	private String NonExistentFile = "src/com/risk/run/inputtext/NonExistentFile.txt";
	
	// removed North America,Alaska --> results in index out of bounds (have an error message for this)
	private String SetNeighOfNullCountry = "src/com/risk/run/inputtext/SetNeighOfNullCountry.txt";
	
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
		model = new Model();
	}

	/**
	 * Tests parser.setCountriesInContinents(Scanner) for duplicate country in the map
	 */
	@Test
	public void testDuplicateCountry() {
		parser = new RiskMapParser(model, DuplicateFile);
		assertThrows(DuplicatesException.class,
				() -> parser.setUp());
	}

	/**
	 * Tests parser.setCountriesInContinents(Scanner) for wrong format or empty file
	 */
	@Test
	public void testEmptyFile() {
		parser = new RiskMapParser(model, EmptyFile);
		assertThrows(CannotFindException.class,
				() -> parser.setUp());
	}
	
	/**
	 * Tests parser.setCountriesInContinents(Scanner) for file that doesn't exist
	 */
	@Test
	public void testNonExistenceFile() {
		parser = new RiskMapParser(model, NonExistentFile);
		assertThrows(FileNotFoundException.class,
				() ->  parser.setUp());
	}

	/**
	 * Test for file that has a continent that does not exist
	 */
	@Test
	public void testNonExistentContinentFile() {
		parser = new RiskMapParser(model, NonExistentContinent);
		assertThrows(CannotFindException.class,
				() -> parser.setUp());
	}
	
	/**
	 * Tests for file that sets neighbour of non existent country. 
	 */
	@Test
	public void testSetNeighOfNullCountryFile() {
		parser = new RiskMapParser(model, SetNeighOfNullCountry);
		assertThrows(CannotFindException.class,
				() -> parser.setUp());
	}


	/**
	 * Tests for file that has exceeding number of country in the continent
	 */
	@Test
	public void testExceedsContinentLimitFile() {
		parser = new RiskMapParser(model,ExceedsContinentLimitFile);
		assertThrows(CountLimitException.class,
				() -> parser.setUp());
	}
	
	/**
	 * Tests for file that has no connectivity
	 */
	@Test
	public void testConnectivity() {
		parser = new RiskMapParser(model,NoConnectivity);
		assertThrows(CountLimitException.class,
				() -> parser.setUp());
	}
}
