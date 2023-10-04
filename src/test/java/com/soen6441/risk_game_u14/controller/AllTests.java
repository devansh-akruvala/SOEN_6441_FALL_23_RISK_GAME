package com.soen6441.risk_game_u14.controller;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * This Class is the test suite, it calls all the tests
 * 
 */
@Suite
@SelectClasses({ MapControllerTest.class, GameModelTest.class })
public class AllTests {

}
