package com.soen6441.risk_game_u14.strategy;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({AggressivePlayerTest.class,BenevolentPlayerTest.class,CheaterPlayerTest.class,RandomPlayerTest.class})
public class StrategyTestSuite {

}
