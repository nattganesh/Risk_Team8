/**
 * Necessary for parsing the input text file
 *
 * @author Natheepan
 * @author DKM
 * @version 3.0
 *
 */
package com.risk.model.utilities;

import java.util.Scanner;

import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.DuplicatesException;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.ActionModel;
import com.risk.model.MapModel;

public class FileParser 
{

    static MapModel maps = MapModel.getMapModel();
    private String text = "";

    /**
     * This is used to construct a new FileParser
     */
    public FileParser()
    {
    }

    /**
     * This method sets the neighbor for each country.
     *
     * @param input Scanner of the input text file
     * @throws CannotFindException Exception thrown when file formatting is
     * wrong
     * @throws DuplicatesException Exception thrown when a country appears more
     * than once in the map
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
                while (input.hasNextLine() && !text.equals("SET NEIGHBORS"))
                {
                    b++;
                    String nameOfContinent = text.substring(0, text.indexOf(","));
                    String nameOfCountry = text.substring(text.indexOf(",") + 1, text.length());
                    boolean countryExists = false;
                    for (Country country : maps.getCountries())
                    {
                        if (country.getName().equalsIgnoreCase(nameOfCountry))
                        {
                            countryExists = true;
                        }
                    }
                    if (!countryExists)
                    {
                        boolean continentExists = false;

                        Country c = new Country(nameOfCountry);
                        maps.addCountry(c);

                        for (Continent cont : maps.getContinents())
                        {
                            if (cont.getName().equalsIgnoreCase(nameOfContinent))
                            {
                                continentExists = true;
                                cont.getCountries().add(c);
                                c.setContinent(cont);
                            }
                        }

                        if (!continentExists)
                        {
                            Continent continent = new Continent(nameOfContinent, 10);
                            continent.setCountry(c);
                            c.setContinent(continent);
                            maps.addContinent(continent);
                        }
                    }
                    else
                    {
                        DuplicatesException ex1 = new DuplicatesException("Country: " + nameOfCountry);
                        ActionModel.getActionModel().addAction(nameOfCountry + " is duplicated");
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
     * This method checks that each continent has predefined number of
     * countries. Throws error if formatting of text file is wrong or number of
     * country in each continent does not equal predefined number.
     *
     * @param input The country name the player input
     * @throws CannotFindException Exception thrown when formatting is wrong
     * each continent does not equal predefined number
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

                for (Country c : maps.getCountries())
                {
                    if (c.getName().equalsIgnoreCase(nameOfCountry1))
                    {
                        for (Country c2 : maps.getCountries())
                        {
                            if (c2.getName().equalsIgnoreCase(nameOfCountry2))
                            {
                                boolean exists = false;
                                for (Country connected : c.getConnectedCountries())
                                {
                                    if (connected.getName().equals(c2.getName()))
                                    {
                                        exists = true;
                                        break;
                                    }
                                }
                                if (!exists)
                                {
                                    c.getConnectedCountries().add(c2);
                                    c2.getConnectedCountries().add(c);
                                }

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

    /**
     * This method is called to parse a file
     *
     * @param input file to be parsed
     * @return true if the file has been parsed correctly, otherwise exception
     * thrown
     * @throws CannotFindException Exception thrown when invalid formatting of
     * file
     * @throws DuplicatesException Exception thrown when duplicate country in
     * continents
     */
    public boolean init(Scanner input) throws CannotFindException, DuplicatesException
    {
        maps.getCountries().clear();
        maps.getContinents().clear();

        setCountriesInContinents(input);
        setNeighboringCountries(input);
        return true;
    }
}
