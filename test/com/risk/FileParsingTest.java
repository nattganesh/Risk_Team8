package com.risk;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.risk.model.MapModel;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.utilities.FileParser;

import junit.framework.TestCase;

/**
 * This class tests file parsing
 * @author DKM
 *
 */
public class FileParsingTest extends TestCase{
	

	private String EmptyFile = "src/com/risk/main/mapTextfiles/EmptyFile.txt";
	private String InvalidFormat = "src/com/risk/main/mapTextfiles/InvalidFormat.txt";
	
	/**
	 * @see Clears the MapModel before parsing a new file
	 */

	public void setUp() {
		MapModel.getMapModel().getContinents().clear();
		MapModel.getMapModel().getCountries().clear();
	}

	/**
	 * Tests empty file
	 * @throws FileNotFoundException 
	 */

	public void testEmptyFile() throws FileNotFoundException {
		Scanner scan = new Scanner(new File(EmptyFile));
		FileParser fileParser = new FileParser();
		assertThrows(CannotFindException.class,
				() -> fileParser.init(scan));
	}
	
	/**
	 * Tests invalid format file
	 * @throws FileNotFoundException 
	 */

	public void testInvalidFormatFile() throws FileNotFoundException {
		Scanner scan = new Scanner(new File(InvalidFormat));
		FileParser fileParser = new FileParser();
		assertThrows(CannotFindException.class,
				() -> fileParser.init(scan));
	}
}
