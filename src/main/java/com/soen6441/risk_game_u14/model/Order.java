package com.soen6441.risk_game_u14.model;

import java.io.Serializable;

/***
 * This class is a model for the orders given by the game players
 *
 * 
 */
public interface Order extends Serializable{

	void execute();
	
}
