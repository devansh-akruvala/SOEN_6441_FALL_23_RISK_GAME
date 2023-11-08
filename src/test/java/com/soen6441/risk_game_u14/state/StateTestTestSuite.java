package com.soen6441.risk_game_u14.state;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;



/**
 * This Class is the test suite, it calls all the tests of state package
 * 
 */

@Suite
@SelectClasses({ EditTest.class, ExecuteOrderTest.class,IssueOrderTest.class })
public class StateTestTestSuite {

}
