package com.risk.MapUtill;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.run.FileParser;
import com.risk.run.Model;


public class RiskMapParser{
	private static Model model;
	static FileParser parser;
	private String inputFile = "src/com/risk/run/inputtext/input.txt";

	
	public RiskMapParser(Model m){
		model = m;
		parser = new FileParser(model);	
	}
	public FileParser getParser() {
		return parser;
	}
	public Scanner getFile() throws FileNotFoundException {
		return new Scanner(new File(inputFile));
	}
	public void parseInputs(Scanner input) throws CountLimitException, CannotFindException, DuplicatesException {
		parser.setPlayers(input);
        parser.setCountriesInContinents(input);
        parser.setNeighboringCountries(input);
        parser.continentChecks();
	}
	public void setUp() throws FileNotFoundException, CountLimitException, CannotFindException, DuplicatesException {
		parseInputs(getFile());
	}
}