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

public class RiskMap {

    private MapModel map;
    Validate validator;
    FileParser parser;
    private String inputFile;

    /**
     * This is the constructor for RiskMapParser. It initializes the model and
     * instantiates the FileParser(model)
     *
     * @param maps takes in model of the game
     */
    public RiskMap(MapModel maps, String input)
    {
        map = maps;
        inputFile = input;
        parser = new FileParser(map);
        validator = new Validate(map);
    }

    /**
     * This is a getter method for returning a scanner for a file
     *
     * @return returns a new Scanner of the input file
     * @throws FileNotFoundException Exception thrown when input file does not
     * exist
     */
    public FileParser getParser()
    {
        return parser;
    }

    /**
     * This methods role is to call the parseInput method and give it the
     * Scanner as an argument
     *
     * @throws FileNotFoundException
     * @throws CountLimitException
     * @throws CannotFindException
     * @throws DuplicatesException
     */
    public void parseFile() throws CountLimitException, CannotFindException, DuplicatesException, FileNotFoundException
    {
        Scanner input = new Scanner(new File(inputFile));
        parser.setCountriesInContinents(input);
        parser.setNeighboringCountries(input);
    }

    public void validateMap() throws CannotFindException, CountLimitException
    {
        validator.continentChecks();
        validator.mapConnected(map.getCountries().get(0));
        if (Validate.countriesModelValidationList.size()== map.getCountries().size())
        {
            System.out.println("Validated");
        }
        else
        {
            System.out.println("Not Validated");
        }
        System.out.println(Validate.counter);
//        validator.mapConnected(map.getCountries().get(0));

    }

    public void setUp() throws FileNotFoundException, CountLimitException, CannotFindException, DuplicatesException
    {
        parseFile();
        validateMap();
    }
}
