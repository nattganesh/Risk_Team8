/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.utilities.generateOutputFile;

import com.risk.model.MapModel;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class Output {

    private Output()
    {
    }

    public static boolean generate(String nameOfFile)
    {
        ArrayList<Continent> continents = MapModel.getMapModel().getContinents();
        ArrayList<Country> countries = MapModel.getMapModel().getCountries();

        try
        {
        	PrintStream printOut = 	System.out;
            PrintStream fileOut = new PrintStream("src/com/risk/main/mapTextFiles/" + nameOfFile + ".txt");
            System.setOut(fileOut);

            System.out.println("SET COUNTRIES IN CONTINENTS");
            for (Country country : countries)
            {
                System.out.println(country.getContinentName() + "," + country.getName());
            }

            System.out.println("SET NEIGHBORS");
            for (Country country : countries)
            {
                for (Country neighCountry : country.getConnectedCountries())
                {
                    System.out.println(country.getName() + "," + neighCountry.getName());
                }
            }
            fileOut.close();
            System.setOut(printOut);
            return true;
        }
        catch (Exception e)
        {
            Logger.logMsg(0, e.getMessage());
            return false;
        }

    }
}
