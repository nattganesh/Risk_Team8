package com.risk;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.model.MapModel;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.exceptions.DuplicatesException;
import com.risk.model.utilities.FileParser;
import com.risk.model.utilities.Validate;

import junit.framework.TestCase;

/**
 * This class test for validity of the connected graph
 *
 * @author DKM
 */
public class ValidateTest extends TestCase {

    // added North America,Alaska two timess
    private String DuplicateCountry = "src/com/risk/main/mapTextfiles/DuplicateCountry.txt";

    // added North America,Alaska2 and removed South America,Venezuela
    private String ExceedsContinentLimitFile = "src/com/risk/main/mapTextfiles/ExceedsContinentLimit.txt";

    // added North America2,Alaska
    private String NonExistentContinent = "src/com/risk/main/mapTextfiles/NonExistentContinent.txt";

    // removed all neighbours of indonesia
    private String NoConnectivity = "src/com/risk/main/mapTextfiles/NoConnectivity.txt";

    /**
     * Clears MapModel for new file parsing
     */
    public void setUp()
    {
        MapModel.getMapModel().getContinents().clear();
        MapModel.getMapModel().getCountries().clear();
    }

    /**
     * Tests parser.setCountriesInContinents(Scanner) for duplicate country in
     * the map
     *
     * @throws FileNotFoundException
     */
    public void testDuplicateCountry() throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(DuplicateCountry));
        FileParser fileParser = new FileParser();
        assertThrows(DuplicatesException.class,
                () -> fileParser.init(scan));
    }

    /**
     * Test for file that has a continent that does not exist
     *
     * @throws FileNotFoundException
     * @throws DuplicatesException
     * @throws CannotFindException
     */
    public void testNonExistentContinentFile() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
        Scanner scan = new Scanner(new File(NonExistentContinent));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        assertThrows(CannotFindException.class,
                () -> Validate.getValidate().continentChecks());
    }

    /**
     * Tests for file that has exceeding number of country in the continent
     *
     * @throws FileNotFoundException
     * @throws DuplicatesException
     * @throws CannotFindException
     */
    public void testExceedsContinentLimitFile() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
        Scanner scan = new Scanner(new File(ExceedsContinentLimitFile));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        assertThrows(CountLimitException.class,
                () -> Validate.getValidate().continentChecks());
    }

    /**
     * Tests for file that has no connectivity
     *
     * @throws FileNotFoundException
     * @throws DuplicatesExceptions
     * @throws CannotFindException
     * @throws CountLimitException
     */
    public void testContinentCountLimit() throws FileNotFoundException, CannotFindException, DuplicatesException
    {
        Scanner scan = new Scanner(new File(NoConnectivity));
        FileParser fileParser = new FileParser();
        fileParser.init(scan);
        Validate.getValidate().mapConnected(MapModel.getMapModel().getCountries().get(0));

        assertFalse(Validate.getValidate().getValidateSize() == MapModel.getMapModel().getCountries().size());
    }
}
