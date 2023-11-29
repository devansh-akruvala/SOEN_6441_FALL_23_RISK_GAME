package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;

/**
 * Abstract parent class for implementing the strategy pattern.
 * Players define unique strategies by extending this class and implementing its methods.
 */
public abstract class Strategy implements Serializable {
	LogEntryBuffer d_Leb = new LogEntryBuffer();
	/**
	 * Generates specific orders based on the player's strategy.
	 * @return Order created according to the player's strategy.
	 */
	public abstract String createOrder();

	/**
	 * Retrieves the name of the player's strategy.
	 * @return Strategy name.
	 */
	public abstract String strategyName();

	/**
	 * Retrieves the source country for deploy or advance orders.
	 * @return Country where armies are deployed or from where an attack originates.
	 */
	public abstract String createDeployOrder();

	/**
	 * Retrieves the list of source and target countries for an attack.
	 * Internally used for creating advance orders, providing source and target countries.
	 * @return List with the first parameter as the source country and the second as the target country.
	 */
	public abstract String createAdvanceOrder();

	/**
	 * Generates orders based on the player's cards.
	 * @param p_CardName Name of the card to create an order for.
	 * @return Order created based on the specified card.
	 */
	public abstract String createCardOrder(String p_CardName);
	
	
}
