package com.risk;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

    public static Test suite()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(FileParsingTest.class);
        suite.addTestSuite(FortificationControllerTest.class);
        suite.addTestSuite(ReinforcementControllerTest.class);
        suite.addTestSuite(ValidateTest.class);
        //$JUnit-END$
        return suite;
    }

}
