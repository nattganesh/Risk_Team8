/**
 * 
 */
package com.risk.utilities;

import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.map.Continent;
import com.risk.model.MapModel;

/**
 * @author DKM
 *
 */
public class Validate {
	MapModel map;
	public Validate(MapModel m) {
		map = m;
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
                    throw ex2;
            }

            if (maxCount != count)
            {
                CountLimitException ex3 = new CountLimitException(cont.getName(), count, maxCount);
                throw ex3;
            }
        }
    }
}
