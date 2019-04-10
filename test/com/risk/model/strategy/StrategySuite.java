/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.strategy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;;

/**
 * Suite for test cases of strategies
 * 
 * @author Tianyi
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    com.risk.model.strategy.AggressiveTest.class, com.risk.model.strategy.BenevolentTest.class, com.risk.model.strategy.CheaterTest.class, com.risk.model.strategy.RandomTest.class
})
public class StrategySuite {

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
