/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite for test cases of models
 * 
 * @author Natt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    com.risk.model.DeckModelTest.class, com.risk.model.card.CardSuite.class, com.risk.model.utilities.UtilitiesSuite.class, com.risk.model.PlayerPhaseModelTest.class, com.risk.model.map.MapSuite.class, com.risk.model.player.PlayerSuite.class, com.risk.model.dice.DiceSuite.class, com.risk.model.MapModelTest.class, com.risk.model.PlayerPhaseModelTest.class
})
public class ModelSuite {

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
