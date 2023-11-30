package com.soen6441.risk_game_u14.tournament;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.GameModel;

public class TournamentTest {

	GameModel d_GameModel;
	GameEngine d_Engine;
	
	@BeforeEach
	public void setTestContext() throws Exception {
		d_GameModel = new GameModel();
		d_Engine = new GameEngine(d_GameModel);
	}
	
	@Test
	public void testInvalidMapArgs(){
		String command = "tournament -M map1,abc -P cheater,aggressive,benevolent,random -G 5 -D 10";
		boolean isValid = true;
		try {
		d_Engine.tournament(command);
		}catch (Exception e) {	
			isValid = false;
		}
		
		assertFalse(isValid);
	}
	
	@Test
	public void testInvalidGameNumberArgs(){
		String command = "tournament -M map1 -P cheater,aggressive,benevolent,random -G 10 -D 10";
		boolean isValid = true;
		try {
		d_Engine.tournament(command);
		}catch (Exception e) {	
			isValid = false;
		}
		
		assertFalse(isValid);
	}
	
	@Test
	public void testInvalidPlayersCountMapArgs(){
		String command = "tournament -M map1 -P cheater -G 5 -D 10";
		boolean isValid = true;
		try {
		d_Engine.tournament(command);
		}catch (Exception e) {	
			isValid = false;
		}
		
		assertFalse(isValid);
	}
	
	@Test
	public void testInvalidTurnArgs(){
		String command = "tournament -M map1 -P cheater,aggressive,benevolent,random -G 5 -D 70";
		boolean isValid = true;
		try {
		d_Engine.tournament(command);
		}catch (Exception e) {	
			isValid = false;
		}
		
		assertFalse(isValid);
	}
	
	
}
