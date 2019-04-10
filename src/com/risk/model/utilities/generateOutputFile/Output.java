/**
 * This class is necessary for generating a file
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.utilities.generateOutputFile;

import com.risk.model.MapModel;
import com.risk.model.map.Country;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.PrintStream;
import java.util.ArrayList;

public class Output 
{

    /**
     * This is a constructor for the Output class
     */
    private Output()
    {
    }

    /**
     * This method is used to check if the file is generated
     *
     * @param nameOfFile file name
     * @return true if file has been generated, otherwise false
     */
    public static boolean generate(String nameOfFile)
    {

        ArrayList<Country> countries = MapModel.getMapModel().getCountries();
        PrintStream printOut = System.out;
        PrintStream fileOut = null;
        
        try
        {
           
            fileOut = new PrintStream("src/com/risk/main/mapTextFiles/" + nameOfFile + ".txt");
            System.setOut(fileOut);

            System.out.println("SET COUNTRIES IN CONTINENTS");
            for (Country country : countries)
            {
                System.out.println(country.getContinent().getName() + "," + country.getName());
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
           
            return true;
        }
        catch (Exception e)
        {
            Logger.logMsg(0, e.getMessage());
            return false;
        }
        finally 
        {
        	 fileOut.close();
             System.setOut(printOut);
        }
    }
}
