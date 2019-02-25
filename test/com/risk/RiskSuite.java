/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Natt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    com.risk.controller.ControllerSuite.class, com.risk.army.ArmySuite.class, com.risk.map.MapSuite.class, com.risk.tests.TestsSuite.class, com.risk.run.RunSuite.class, com.risk.dice.DiceSuite.class, com.risk.view.ViewSuite.class, com.risk.utilities.UtilitiesSuite.class, com.risk.exceptions.ExceptionsSuite.class, com.risk.card.CardSuite.class, com.risk.model.ModelSuite.class
})
public class RiskSuite {

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
