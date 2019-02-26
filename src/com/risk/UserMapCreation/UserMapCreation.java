/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.UserMapCreation;

import com.risk.map.Continent;
import com.risk.map.Country;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Natheepan
 */
public class UserMapCreation {

    private UserMapCreation()
    {

    }

    public static ArrayList<Country> countries = new ArrayList<>();
    public static ArrayList<Continent> continents = new ArrayList<>();

    private static Scanner key = new Scanner(System.in);
    private static String[] continentNames =
    {
        "North America", "South America", "Europe", "Asia", "Africa", "Australia"
    };

    private static void setCountriesInContinents()
    {
        System.out.println("This game has a fixed number of Continents. They are North America, South America, Europe, Asia, Africa, Australia.");

        for (String name : continentNames)
        {
            System.out.println("Please Enter the number of Countries for " + name);
            int num = key.nextInt();
            Continent continent = new Continent(name, num);
            continents.add(continent);
            for (int i = 1; i <= num; i++)
            {
                System.out.println("Please Enter the name of country number " + i);
                String countryName = key.nextLine();
                while (countryExists(countryName))
                {
                    System.out.println("A country already exists with the name of " + countryName);
                    System.out.println("Please Enter the name of country number " + i);
                    countryName = key.nextLine();
                }
                Country country = new Country(countryName, name);
                countries.add(country);
                continent.getCountries().add(country);
            }
        }
    }

    private static void setNeighbouringCountries()
    {
        for (Country c : countries)
        {
            System.out.println("Please Enter the number of neighbouring country of " + c.getName());
            int num = key.nextInt();

            System.out.println("Please Enter the name of neighbouring country of " + c.getName());
            String countryName = key.nextLine();

            for (int i = 1; i <= num; i++)
            {
                System.out.println(i + " Please Enter the name of the neighbouring country of " + c.getName());
                countryName = key.nextLine();
                while (!countryExists(countryName))
                {
                    System.out.println("Invalid name of Country. " + countryName + " does not exist in game.");
                    System.out.println(i + " Please Enter the name of the neighbouring country of " + c.getName());
                    countryName = key.nextLine();
                }
                if (c.getConnectedCountry(countryName) != null)
                {
                    i--;
                    System.out.println("Country named " + countryName + " has already been added as a neighbouring country of " + c.getName());
                    continue;
                }

                c.getConnectedCountries().add(getCountry(countryName));
            }
        }
    }

    private static Country getCountry(String name)
    {
        for (Country country : countries)
        {
            if (country.getName().equals(name))
            {
                return country;
            }
        }
        return null;
    }

    private static boolean countryExists(String countryName)
    {
        for (Country c : countries)
        {
            if (c.getName() == countryName)
            {
                return true;
            }
        }
        return false;
    }

    public static void createMap()
    {
        setCountriesInContinents();
        setNeighbouringCountries();
    }

}
