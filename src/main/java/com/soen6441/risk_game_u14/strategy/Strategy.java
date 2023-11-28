package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;

/**
 * This is a parent class for the strategy pattern. 
 * Each player has their different strategy and they extends this class and implements the methods accordingly. 
 */
public abstract class Strategy implements Serializable {
	LogEntryBuffer d_Leb = new LogEntryBuffer();
	/**
	 * This method creates different types of orders for each type of player based on their strategy. 
	 * @return order created according to the player type 
	 */
	public abstract ArrayList<Order> createOrder();
	
	/**
	 * This method returns the name of the strategy that player holds. 
	 * @return strategy name
	 */
	public abstract String strategyName();
	
	/**
	 * This method returns the source country for deploy or advance order. 
	 * @return country to which armies are deployed or from where attack is happening
	 */
	public abstract Country toDefend();
	
	/**
	 * This method returns the list of source and target country for the attack. \
	 * This is internally used for the advance order creation to pass as target source and target country. 
	 * @return returns list with first parameter as source country and second as target country. 
	 */
	public abstract ArrayList<Country> toAttack();
	
	
}
