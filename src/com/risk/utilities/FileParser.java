/**
 * Necessary for parsing the input text file
 * 
 * @author Natheepan
 *
 */
package com.risk.utilities;

import java.util.ArrayList;
import java.util.Scanner;

import com.risk.army.Player;
import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.map.Continent;
import com.risk.map.Country;
import com.risk.model.MapModel;
import com.risk.model.PlayerModel;

/**
 * @author DKM
 *
 */
public class FileParser {


    MapModel model;
    private static String text = ""; // file parser

	/** 
	 * @param m this is the model of the game
	 */
    public FileParser(MapModel m)
    {
        this.model = m;
    }


	/**
	 * This method sets the neighbor for each country.
	 * 
	 * @param input Scanner of the input text file
	 * @throws CannotFindException Exception thrown when file formatting is wrong
	 */
    public void setCountriesInContinents(Scanner input) throws CannotFindException, DuplicatesException
    {
        if (input.hasNextLine())
        {
            text = input.nextLine();
            if (text.equalsIgnoreCase("SET COUNTRIES IN CONTINENTS") && input.hasNextLine())
            {
                text = input.nextLine();
                int b = 1;
                while (input.hasNextLine() && b <= Country.MAX_NUMBER_OF_COUNTRIES)
                {
                    b++;
                    String nameOfContinent = text.substring(0, text.indexOf(","));
                    String nameOfCountry = text.substring(text.indexOf(",") + 1, text.length());
                    boolean countryExists = false;
                    for (Country country : model.getCountries())
                    {
                        if (country.getName().equalsIgnoreCase(nameOfCountry))
                        {
                            countryExists = true;
                        }
                    }
                    if (!countryExists)
                    {
                        boolean continentExists = false;

                        Country c = new Country(nameOfCountry, nameOfContinent);
                        model.addCountry(c); // ****

                        for (Continent cont : model.getContinents())
                        {
                            if (cont.getName().equalsIgnoreCase(nameOfContinent))
                            {
                                continentExists = true;
                                cont.getCountries().add(c);
                            }
                        }

                        if (!continentExists)
                        {
                            Continent continent = new Continent(nameOfContinent, 10);
                            continent.setCountry(c);
                            model.addContinent(continent);
                        }
                    }
                    else
                    {
                        DuplicatesException ex1 = new DuplicatesException("Country: " + nameOfCountry);
                        throw ex1;
                    }
                    text = input.nextLine();
                }
            }
            else
            {
                CannotFindException ex = new CannotFindException(
                        "The tag 'SET COUNTRIES IN CONTINENTS' is not set. Please follow the Input Text Format Please resolve this issue.");
                throw ex;
            }
        }
        else
        {
            CannotFindException ex = new CannotFindException(
                    "Please follow the Input Text Format Please resolve this issue.");
            throw ex;
        }
    }

	/**
	 * This method checks that each continent has predefined number of countries. Throws error if formatting of text file
	 * is wrong or number of country in each continent does not equal predefined number.
	 * 
	 * @throws CannotFindException Exception thrown when formatting is wrong
	 * @throws CountLimitException Exception thrown when number of country in each continent does not equal predefined number
	 */
    public void setNeighboringCountries(Scanner input) throws CannotFindException
    {
        if (input.hasNextLine() && text.equalsIgnoreCase("SET NEIGHBORS"))
        {
            while (input.hasNextLine())
            {
                text = input.nextLine();
                String nameOfCountry1 = text.substring(0, text.indexOf(","));
                String nameOfCountry2 = text.substring(text.indexOf(",") + 1, text.length());

                for (Country c : model.getCountries())
                {
                    if (c.getName().equalsIgnoreCase(nameOfCountry1))
                    {
                        for (Country c2 : model.getCountries())
                        {
                            if (c2.getName().equalsIgnoreCase(nameOfCountry2))
                            {
                                c.getConnectedCountries().add(c2);
                                c2.getConnectedCountries().add(c);
                            }
                        }
                    }
                }
            }

        }
        else
        {
            CannotFindException ex = new CannotFindException(
                    "The tag 'SET NEIGHBORS' is not set. Please follow the Input Text Format Please resolve this issue.");
            throw ex;
        }
    }
}
