package com.soen6441.risk_game_u14;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.soen6441.risk_game_u14.controller.ControllerTestTestSuite;
import com.soen6441.risk_game_u14.order.OrderTestTestSuite;
import com.soen6441.risk_game_u14.state.StateTestTestSuite;

/**
 * This Class is the test suite, it calls all the tests of Project
 */

@Suite
@SelectClasses({ControllerTestTestSuite.class, OrderTestTestSuite.class, StateTestTestSuite.class})
public class AllTestTestSuite {

}
