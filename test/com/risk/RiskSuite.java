/**
 *
 * @author Tianyi
 * @author DKM
 */
package com.risk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.risk.controller.*;
import com.risk.utilities.FileParsingTest;
import com.risk.utilities.ValidateTest;


@RunWith(Suite.class)
@Suite.SuiteClasses(
{
   ReinforcementControllerTest.class,
   FortificationControllerTest.class,
   FileParsingTest.class,
   ValidateTest.class
})
public class RiskSuite {
	
}
