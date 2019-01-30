/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.run;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.map.Continent;
import com.risk.map.Country;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natheepan
 */
public class Run {

    public static void main(String[] args)
    {
        ArrayList<Continent> continents = new ArrayList<>();
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            Scanner input = new Scanner(new File("src/com/risk/run/inputtext/input.txt"));
            while (input.hasNextLine())
            {
                String text = input.nextLine();
                if (text.equalsIgnoreCase("SET NEIGHBORS"))
                {
                    while (input.hasNextLine())
                    {
                        text = input.nextLine();
                        String nameOfCountry1 = text.substring(0, text.indexOf(","));
                        String nameOfCountry2 = text.substring(text.indexOf(",") + 1, text.length());

                        for (Country c : countries)
                        {
                            if (c.getName().equalsIgnoreCase(nameOfCountry1))
                            {
                                for (Country c2 : countries)
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
                    String nameOfContinent = text.substring(0, text.indexOf(","));
                    String nameOfCountry = text.substring(text.indexOf(",") + 1, text.length());
//                    System.out.println(nameOfContinent + " " + nameOfCountry);
                    boolean countryExists = false;
                    for (Country country : countries)
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
                        countries.add(c);
                        //here are you are assuming that:
                        // 1. number of country is fixed - it is but test case might be different
                        // 2. doesn't account for duplicate countries
                        // 3. alasaka is not always beside kambatcha say, this information can be grabbed from the polygon object
                        // 4. this leads be to believe that i should create the map

                        for (Continent cont : continents)
                        {
                            if (cont.getName().equalsIgnoreCase(nameOfContinent))
                            {
                                continentExists = true;
                                cont.getCountries().add(c);
                            }
                        }

                        if (!continentExists)
                        {
                            continents.add(new Continent(nameOfContinent, 10));
                            continents.get(continents.size() - 1).getCountries().add(c);
                        }
                    }
                    else
                    {
                        DuplicatesException ex1 = new DuplicatesException("Country: " + nameOfCountry);
                        throw ex1;
                    }
                }
            }
            for (Continent cont : continents)
            {
                int count = cont.getCountries().size();
                int maxCount = 0;

                switch (cont.getName())
                {
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
                        CannotFindException ex2 = new CannotFindException(cont.getName() + " is not predefined. Size of continent is not known. Please resolve this issue.");
                        throw ex2;
                }

                if (maxCount != count)
                {
                    CountLimitException ex3 = new CountLimitException(cont.getName(), count, maxCount);
                    throw ex3;
                }
            }

            System.out.println(countries.size());
            int i = 1;
            for (Country country : countries)
            {
                country.setName(i + " " + country.getName());
                i++;
            }
            int y = 0;
            for (Continent cont : continents)
            {
                for (Country country : cont.getCountries())
                {
                    for (Country c : country.getConnectedCountries())
                    {
                        y++;
                        System.out.println(y + " For Continent " + cont.getName() + " The country: " + country.getName() + " is neighbored by: " + c.getName());
                    }
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DuplicatesException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (CountLimitException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (CannotFindException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
