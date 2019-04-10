/**
 * Necessary for validating the connectivity of the parsed map
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.utilities;

import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import com.risk.model.ActionModel;
import com.risk.model.MapModel;
import java.util.ArrayList;

public class Validate 
{

    static int counter = 0;
    static ArrayList<Country> countriesModelValidationList = new ArrayList<>();
    private static Validate validate;

    MapModel map = MapModel.getMapModel();

    public Validate()
    {

    }

    /**
     * This method is used to get the size of the validation list
     *
     * @return return the size of validation list of countries model
     */
    public int getValidateSize()
    {
        return countriesModelValidationList.size();
    }

    /**
     * This method is used to check if the number of countries in continents is
     * equal to the maximum number that is set to the continent
     *
     * @throws CannotFindException Exception thrown when file formatting is
     * @throws CountLimitException Exception thrown when number of country in a
     * continent is different from fixed number.
     */
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
     * Given a Country, this method recursively goes through this country's
     * neighbours and adds then to countriesModelValidationList If
     * (countriesModelValidationList.size() == map.getCountries().size()) then
     * from a given country, we can travel to all other countries, so map is
     * connected
     *
     * @param origin The origin country whose neighbours will be added to
     * countriesModelValidationList
     */
    public void mapConnected(Country origin)
    {
        if (counter == 1000)
        {
            return;
        }
        counter++;

        if (countriesModelValidationList.size() == map.getCountries().size())
        {
            return;
        }

        for (Country a : countriesModelValidationList)
        {
            if (a.getName().equalsIgnoreCase(origin.getName()))
            {
                return;
            }
        }
        countriesModelValidationList.add(origin);

        for (Country a : origin.getConnectedCountries())
        {
            mapConnected(a);
        }
    }

    /**
     * This method is used to validate the map by connecting country with its
     * neighbours
     *
     * @throws CannotFindException Exception thrown when file formatting is
     * @throws CountLimitException Exception thrown when number of country in a
     * continent is different from fixed number.
     */
    public void validateMap() throws CannotFindException, CountLimitException
    {
        counter = 0;
        countriesModelValidationList.clear();
        mapConnected(map.getCountries().get(0));
    }

    /**
     * This method is used to get the validation of connected countries
     *
     * @return validate The validation of connected map
     */
    public static Validate getValidate()
    {
        if (validate == null)
        {
            validate = new Validate();
        }
        return validate;
    }
}
