/**
 * Necessary for validating the connectivity of the parsed map
 *
 * @author Natheepan
 */
package com.risk.model.utilities;

import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.controller.MapEditorController;
import com.risk.model.ActionModel;
import com.risk.model.MapModel;
import java.util.ArrayList;

public class Validate {

    static int counter = 0;
    static ArrayList<Country> countriesModelValidationList = new ArrayList<>();
    private static Validate validate;

    MapModel map = MapModel.getMapModel();

    public Validate()
    {

    }

    public int getValidateSize()
    {
        return countriesModelValidationList.size();
    }

    public void continentChecks() throws CannotFindException, CountLimitException
    {
        for (Continent cont : map.getContinents())
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
                    CannotFindException ex2 = new CannotFindException(cont.getName()
                            + " is not predefined. Size of continent is not known. Please resolve this issue.");
                    ActionModel.getActionModel().addAction(cont.getName() + " is not predefined: resolve this issue");
                    throw ex2;
            }

            if (maxCount != count)
            {
                CountLimitException ex3 = new CountLimitException(cont.getName(), count, maxCount);
                ActionModel.getActionModel().addAction(cont.getName() + " must be equal to " + maxCount + ", currently it is " + count + ". Please resolve this issue, and try again.");
                throw ex3;
            }
        }
    }

    /**
     * Given a Country, this method recursively goes thru this country's
     * neighbours and adds then to countriesModelValidationList If
     * (countriesModelValidationList.size() == map.getCountries().size()) then
     * from a given country, we can travel to all other countries, so map is
     * connected
     */
    public void mapConnected(Country origin)
    {
        // if ever this method enters an infinite recussion
        // All precautions have been taken, but just incase
        if (counter == 1000)
        {
            return;
        }
        counter++;

        //if all countries have been added
        if (countriesModelValidationList.size() == map.getCountries().size())
        {
            return;
        }

        //if country is already checked before
        for (Country a : countriesModelValidationList)
        {
            if (a.getName() == origin.getName())
            {
                return;
            }
        }
        countriesModelValidationList.add(origin);

        //Recursively call next connected country
        for (Country a : origin.getConnectedCountries())
        {
            mapConnected(a);
        }
    }

    public void validateMap() throws CannotFindException, CountLimitException
    {

        counter = 0;
        countriesModelValidationList.clear();
//        continentChecks();
        mapConnected(map.getCountries().get(0));

    }

    public static Validate getValidate()
    {
        if (validate == null)
        {
            validate = new Validate();
        }

        return validate;
    }

}
