package com.soen6441.risk_game_u14.strategy;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite class to group and run test classes for different player strategies in the game.
 * Uses JUnit's annotations to select the test classes.
 */
@Suite
@SelectClasses({AggressivePlayerTest.class,BenevolentPlayerTest.class,CheaterPlayerTest.class,RandomPlayerTest.class})
public class StrategyTestSuite {

}
