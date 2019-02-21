/**
 * 
 */
package com.risk.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.DuplicatesException;
import com.risk.run.FileParser;
import com.risk.run.Model;

/**
 * @author DKM
 *
 */
class FileParsingTest {
	private static Model model;
	private FileParser parser;
	private String DuplicateFile = "src/com/risk/run/inputtext/DuplicateCountry.txt";
	private String EmptyFile = "src/com/risk/run/inputtext/EmptyFile.txt";
	private String NonExistentFile = "src/com/risk/run/inputtext/NonExistentFile.txt";
	
	private String ExceedsCountLimitFile = "src/com/risk/run/inputtext/ExceedsCountLimit.txt";
	private String BelowCountLimitFile = "src/com/risk/run/inputtext/BelowCountLimit.txt";
	private String NonExistentCountry = "src/com/risk/run/inputtext/NonExistentCountry.txt";
	private String NonExistentContinent = "src/com/risk/run/inputtext/NonExistentContinent.txt";
	private String NoConnectivty =  "src/com/risk/run/inputtext/NoConnectivity.txt";
	

	@BeforeEach
	public void setUp() {
		model = new Model();
		parser = new FileParser(model);
	}

	@Test
	public void testDuplicateCountry() {
		assertThrows(DuplicatesException.class,
				() -> parser.setCountriesInContinents(new Scanner(new File(DuplicateFile))));
	}

	@Test
	public void testEmptyFile() {
		assertThrows(CannotFindException.class,
				() -> parser.setCountriesInContinents(new Scanner(new File(EmptyFile))));
	}

	@Test
	public void testNonExistenceFile() {
		assertThrows(FileNotFoundException.class,
				() -> parser.setCountriesInContinents(new Scanner(new File(NonExistentFile))));
	}

	@Test
	public void testExceedsCountLimitFile() {
		assertThrows(FileNotFoundException.class,
				() -> parser.setCountriesInContinents(new Scanner(new File(ExceedsCountLimitFile))));
	}

}
