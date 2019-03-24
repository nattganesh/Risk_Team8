package com.risk;

import com.risk.model.utilities.FileParserTest;
import com.risk.model.utilities.UtilitiesSuite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

    public static Test suite()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(FileParserTest.class);
        
       
        return suite;
    }

}
