/**
 * Necessary for calling file parser class for parsing risk map.
 * 
 * @author DKM
 */
package com.risk.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;

public class RiskMapParser {

    private MapModel model;
    static FileParser parser;
    private String inputFile;

	/**
	 * This is the constructor for RiskMapParser. It initializes the model and instantiates the FileParser(model)
	 * 
	 * @param maps takes in model of the game
	 */
    public RiskMapParser(MapModel maps, String input)
    {
        model = maps;
        inputFile = input;
        parser = new FileParser(model);
    }

	/**
	 * This is a getter method for returning a scanner for a file
	 * 
	 * @return returns a new Scanner of the input file
	 * @throws FileNotFoundException Exception thrown when input file does not exist
	 */
    public FileParser getParser()
    {
        return parser;
    }

	/**
	 * This method calls class methods of parser to parse the file.
	 * 
	 * @param input	Scanner for input file
	 * @throws CountLimitException 
	 * @throws CannotFindException 
	 * @throws DuplicatesException
	 */
    public Scanner getFile() throws FileNotFoundException
    {
        return new Scanner(new File(inputFile));
    }

	/**
	 * This methods role is to call the parseInput method and give it the Scanner as an argument
	 * 
	 * @throws FileNotFoundException
	 * @throws CountLimitException
	 * @throws CannotFindException
	 * @throws DuplicatesException
	 */
    public void parseInputs(Scanner input) throws CountLimitException, CannotFindException, DuplicatesException
    {
       // parser.setPlayers(input);
        parser.setCountriesInContinents(input);
        parser.setNeighboringCountries(input);
        parser.continentChecks();
    }

    public void setUp() throws FileNotFoundException, CountLimitException, CannotFindException, DuplicatesException
    {
        parseInputs(getFile());
    }
}
