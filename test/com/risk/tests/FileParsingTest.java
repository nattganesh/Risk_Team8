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
class FileParsingTest {
	private static MapModel map;
	private RiskMap riskMap;
	

	private String EmptyFile = "src/com/risk/run/inputtext/EmptyFile.txt";
	private String NonExistentFile = "src/com/risk/run/inputtext/NonExistentFile.txt";
		
	/**
	 * @see Instantiates the model and parser.
	 */
	@BeforeEach
	public void setUp() {
		map = new MapModel();
	}

	/**
	 * Tests parser.setCountriesInContinents(Scanner) for wrong format or empty file
	 */
	@Test
	public void testEmptyFile() {
		riskMap = new RiskMap(map, EmptyFile);
		assertThrows(CannotFindException.class,
				() -> riskMap.setUp());
	}
	
	/**
	 * Tests parser.setCountriesInContinents(Scanner) for file that doesn't exist
	 */
	@Test
	public void testNonExistenceFile() {
		riskMap = new RiskMap(map, NonExistentFile);
		assertThrows(FileNotFoundException.class,
				() ->  riskMap.setUp());
	}
}
