
package com.risk.model.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite of test cases for utilities
 * 
 * @author Natt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    com.risk.model.utilities.FileParserTest.class, com.risk.model.utilities.ValidateTest.class
})
public class UtilitiesSuite {

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
}
