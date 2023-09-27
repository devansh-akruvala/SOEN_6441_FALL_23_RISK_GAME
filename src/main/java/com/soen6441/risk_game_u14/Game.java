package com.soen6441.risk_game_u14;

import com.soen6441.risk_game_u14.controller.GameEngine;
/**
 * 
 * 
 *@author Devansh, Meshva 
 * 
 */

public class Game {

	public static void main(String[] args) throws Exception {
		GameEngine riskGameEngine =  new GameEngine();
		riskGameEngine.listenCommand();
	}
	
}
