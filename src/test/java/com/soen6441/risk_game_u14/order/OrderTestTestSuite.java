package com.soen6441.risk_game_u14.order;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


/**
 * This Class is the test suite, it calls all the tests of order package
 */

@Suite
@SelectClasses({AdvanceTest.class, AirliftTest.class, BlockadeTest.class, BombTest.class, DeployTest.class, NegotiateTest.class})
public class OrderTestTestSuite {

}
