/**
 * 
 */
package com.risk.run;

import java.util.ArrayList;
import java.util.Scanner;

import com.risk.army.Player;
import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.map.Continent;
import com.risk.map.Country;
import com.risk.run.Model;

/**
 * @author DKM
 *
 */
public class FileParser {
	// file parser
	Model model;
	private static String text = ""; // file parser

	public FileParser(Model m) {
		this.model = m;
	}

	public void setPlayers(Scanner input) throws CountLimitException, CannotFindException {
		if (input.hasNextInt()) {
			int numberOfPlayers = input.nextInt();
			if (numberOfPlayers < 2 || numberOfPlayers > 6) {
				CountLimitException ex = new CountLimitException("Players", numberOfPlayers, 2, 6);
				throw ex;
			} else {
				int a = 0;
				input.nextLine();
				while (a != numberOfPlayers) {
					model.addPlayer(new Player(input.nextLine())); // ****
					a++;
				}
			}
		} else {
			CannotFindException ex = new CannotFindException(
					"The first line of Input Text Document is reserved for the number of players playing the game. This is undefined. This number should be between 2 and 6. Please resolve this issue.");
			throw ex;
		}
	}
	public void setCountriesInContinents(Scanner input) throws CannotFindException, DuplicatesException {
		if (input.hasNextLine()) {
			text = input.nextLine();
			if (text.equalsIgnoreCase("SET COUNTRIES IN CONTINENTS") && input.hasNextLine()) {
				text = input.nextLine();
				int b = 1;
				while (input.hasNextLine() && b <= Country.MAX_NUMBER_OF_COUNTRIES) {
					b++;
					String nameOfContinent = text.substring(0, text.indexOf(","));
					String nameOfCountry = text.substring(text.indexOf(",") + 1, text.length());
					boolean countryExists = false;
					for (Country country : model.getCountries()) {
						if (country.getName().equalsIgnoreCase(nameOfCountry)) {
							countryExists = true;
						}
					}
					if (!countryExists) {
						boolean continentExists = false;

						Country c = new Country(nameOfCountry, nameOfContinent);
						model.addCountry(c); // ****

						for (Continent cont : model.getContinents()) {
							if (cont.getName().equalsIgnoreCase(nameOfContinent)) {
								continentExists = true;
								cont.getCountries().add(c);
							}
						}

						if (!continentExists) {
							Continent continent = new Continent(nameOfContinent, 10);
							continent.setCountry(c);
							model.addContinent(continent);
						}
					} else {
						DuplicatesException ex1 = new DuplicatesException("Country: " + nameOfCountry);
						throw ex1;
					}
					text = input.nextLine();
				}
			} else {
				CannotFindException ex = new CannotFindException(
						"The tag 'SET COUNTRIES IN CONTINENTS' is not set. Please follow the Input Text Format Please resolve this issue.");
				throw ex;
			}
		} else {
			CannotFindException ex = new CannotFindException(
					"Please follow the Input Text Format Please resolve this issue.");
			throw ex;
		}
	}

	public void setNeighboringCountries(Scanner input) throws CannotFindException {
		if (input.hasNextLine() && text.equalsIgnoreCase("SET NEIGHBORS")) {
			while (input.hasNextLine()) {
				text = input.nextLine();
				String nameOfCountry1 = text.substring(0, text.indexOf(","));
				String nameOfCountry2 = text.substring(text.indexOf(",") + 1, text.length());

				for (Country c : model.getCountries()) {
					if (c.getName().equalsIgnoreCase(nameOfCountry1)) {
						for (Country c2 : model.getCountries()) {
							if (c2.getName().equalsIgnoreCase(nameOfCountry2)) {
								c.getConnectedCountries().add(c2);
								c2.getConnectedCountries().add(c);
							}
						}
					}
				}
			}

		} else {
			CannotFindException ex = new CannotFindException(
					"The tag 'SET NEIGHBORS' is not set. Please follow the Input Text Format Please resolve this issue.");
			throw ex;
		}
	}

	public void continentChecks() throws CannotFindException, CountLimitException {
		for (Continent cont : model.getContinents()) {
			int count = cont.getCountries().size();
			int maxCount = 0;

			switch (cont.getName()) {
			case "North America":
				maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_NORTH_AMERICA;
				break;
			case "South America":
				maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_SOUTH_AMERICA;
				break;
			case "Europe":
				maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_EUROPE;
				break;
			case "Asia":
				maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_ASIA;
				break;
			case "Africa":
				maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_AFRICA;
				break;
			case "Australia":
				maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_AUSTRALIA;
				break;
			default:
				CannotFindException ex2 = new CannotFindException(cont.getName()
						+ " is not predefined. Size of continent is not known. Please resolve this issue.");
				throw ex2;
			}

			if (maxCount != count) {
				CountLimitException ex3 = new CountLimitException(cont.getName(), count, maxCount);
				throw ex3;
			}
		}
	}

}
