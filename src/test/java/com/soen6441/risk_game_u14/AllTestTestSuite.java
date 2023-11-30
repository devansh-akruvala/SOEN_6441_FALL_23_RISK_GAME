package com.soen6441.risk_game_u14;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.soen6441.risk_game_u14.controller.ControllerTestTestSuite;
import com.soen6441.risk_game_u14.order.OrderTestTestSuite;
import com.soen6441.risk_game_u14.state.StateTestTestSuite;
import com.soen6441.risk_game_u14.strategy.StrategyTestSuite;
import com.soen6441.risk_game_u14.tournament.TournamentTest;

/**
 * This Class is the test suite, it calls all the tests of Project
 */

@Suite
@SelectClasses({ControllerTestTestSuite.class, OrderTestTestSuite.class, StateTestTestSuite.class,StrategyTestSuite.class,TournamentTest.class})
public class AllTestTestSuite {

}
