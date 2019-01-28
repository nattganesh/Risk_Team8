/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.run;

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
        try
        {
            Scanner input = new Scanner(new File("src/com/risk/run/inputtext/input.txt"));
            int totalNumberOfCountries = 0;
            while (input.hasNextLine())
            {
                String text = input.nextLine();
                String nameOfContinent = text.substring(0, text.indexOf(","));
                String nameOfCountry = text.substring(text.indexOf(",") + 1, text.length());
                System.out.println(nameOfContinent + " " + nameOfCountry);

                boolean continentExists = false;

                for (Continent cont : continents)
                {
                    if (cont.getName().equalsIgnoreCase(nameOfContinent))
                    {
                        continentExists = true;
                        cont.getCountries().add(new Country(nameOfCountry, false, null));
                    }
                }

                if (!continentExists)
                {
                    continents.add(new Continent(nameOfContinent, 10));
                    continents.get(continents.size() - 1).getCountries().add(new Country(nameOfCountry, false, null));
                }

                totalNumberOfCountries++;
            }
            System.out.println(totalNumberOfCountries);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Continent cont : continents)
        {
            for (Country country : cont.getCountries())
            {
                System.out.println(cont.getName() + " " + country.getName() + " " + country.isIsOccupied());
            }
        }

    }
}
