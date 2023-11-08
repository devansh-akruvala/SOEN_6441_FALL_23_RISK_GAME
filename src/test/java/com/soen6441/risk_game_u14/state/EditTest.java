package com.soen6441.risk_game_u14.state;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.GameModel;

public class EditTest {
	GameModel d_GameModel;
	GameEngine d_Ge;
	Edit d_Ed;
	Phase d_P;
	
	/**
	 * This method sets the context before each method is executed. 
	 * @throws Exception any exception that is thrown while setting up the context. 
	 */
	@BeforeEach
	public void setTestContext() throws Exception {
		d_GameModel=new GameModel();
		d_Ge= new GameEngine(d_GameModel);
		d_Ed= new Edit(d_Ge);
		
	}
	
	/**
	 * This method tests that after the loadmap, the game goes in the startup phase. 
	 */
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Startup Phase";
		String l_ActualMessage = "";
		d_Ed.loadMap("loadmap map1");
		d_P=d_Ge.getD_GamePhase();
		l_ActualMessage=d_P.getPhaseName();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}

	/**
	 * This method tests the loading of incorrect/ invalid map. 
	 */
	@Test
	public void testIncorrectLoadMap() {
		String l_ExpectedMessage="Reseting loaded map!! please load a valid map";
		String l_ActualMessage = "";
		l_ActualMessage=d_Ed.loadMap("loadmap incorrectmap");
		assertEquals(l_ExpectedMessage,l_ActualMessage.split("\n")[0]);
	}
	
	/**
	 * This method tests that during editmap phase assigncountries method can not be executed. 
	 */
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid Command in Edit phase";
		String l_ActualMessage = "";
		d_Ed.assignCountries();
		d_P=d_Ge.getD_GamePhase();
		l_ActualMessage=d_Ed.d_LEB.getResult();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * This method tests that during Edit phase, gameplayer command can not be executed. 
	 */
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid Command in Edit phase";
		String l_ActualMessage = "";
		d_Ed.addPlayers("gameplayer -add p1 -add p2");
		l_ActualMessage=d_Ed.d_LEB.getResult();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
}
