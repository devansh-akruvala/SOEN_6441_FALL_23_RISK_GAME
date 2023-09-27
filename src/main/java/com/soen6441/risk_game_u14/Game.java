package com.soen6441.risk_game_u14;

import com.soen6441.risk_game_u14.controller.GameEngine;
/**
 * 
 * 
 *@author Devansh
 * 
 */

public class Game {

	public static void main(String[] args) throws Exception {
		GameEngine d_RiskGameEngine =  new GameEngine();
		d_RiskGameEngine.listenCommand();
	}
	
}
