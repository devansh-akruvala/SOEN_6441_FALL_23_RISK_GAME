package com.soen6441.risk_game_u14;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.GameModel;
/**
 * 
 * 
 *@author Devansh
 * 
 */

public class Game {

	public static void main(String[] args) throws Exception {
		GameModel d_RiskGameModel = new GameModel(); 
		GameEngine d_RiskGameEngine =  new GameEngine(d_RiskGameModel);
		d_RiskGameEngine.listenCommand();
	}
	
}
