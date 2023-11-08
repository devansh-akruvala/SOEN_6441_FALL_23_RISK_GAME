package com.soen6441.risk_game_u14.state;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.GameModel;

public class ExecuteOrderTest {
	GameModel d_GameModel;
	GameEngine d_Ge;
	ExecuteOrder d_Eo;
	Phase d_P;

	
	/**
	 * This method sets the context before each method is executed. 
	 * @throws Exception any exception that is thrown while setting up the context. 
	 */
	@BeforeEach
	public void setTestContext() throws Exception {
		d_GameModel=new GameModel();
		d_Ge= new GameEngine(d_GameModel);
		d_Eo= new ExecuteOrder(d_Ge);
		
	}
	/**
	 * This method tests that editmap method is invalid for the Execute Order phase. 
	 */
	@Test
	public void testEditMap() {
		try {
			String l_ExpectedMessage="Invalid Command in Execute Order Phase";
			String l_ActualMessage = "";
			d_Eo.editMap("editmap map1");
			d_P=d_Ge.getD_GamePhase();
			l_ActualMessage=d_Eo.d_LEB.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
		
	}
	
	/**
	 * This method tests that editCountry method is invalid for the execute phase. 
	 */
	@Test
	public void testEditCountry() {
		try {
			String l_ExpectedMessage="Invalid Command in Execute Order Phase";
			String l_ActualMessage = "";
			d_Eo.editCountry("editcountries -add india asia -add pakistan asia");
			l_ActualMessage=d_Eo.d_LEB.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	
	@Test
	public void testAddPlayers() {
		try {
			String l_ExpectedMessage="Invalid Command in Execute Order Phase";
			String l_ActualMessage = "";
			d_Eo.addPlayers("gameplayer -add p1 -add p2");
			l_ActualMessage=d_Eo.d_LEB.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}

}
