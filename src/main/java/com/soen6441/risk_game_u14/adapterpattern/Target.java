package com.soen6441.risk_game_u14.adapterpattern;

import com.soen6441.risk_game_u14.controller.GameEngine;

/**
 * Target class for the adapter pattern.
 * This class is designed to invoke the existing implementation of loadMap and saveMap functionality for the domination map type.
 */
public class Target {
	/**
	 * GameEngine object used to invoke the loadMap and saveMap methods for the domination type.
	 */
	private GameEngine d_GameEngine;
	/**
	 * Default constructor used for testing.
	 */
	public Target() {}

	/**
	 * Constructor that assigns a GameEngine object to be used in this class.
	 * @param p_GameEngine Object of the GameEngine class.
	 */
	public Target(GameEngine p_GameEngine)
	{
		d_GameEngine=p_GameEngine;
	}


	/**
	 * Invokes the existing loadMap method written for loading/editing the domination map type.
	 * @param p_S Name of the map file.
	 * @return Acknowledgment of the loadMap function (success or failure).
	 * @throws Exception Any exception thrown during the loadMap function call.
	 */
	public String loadMap(String p_S) throws Exception
	{
		return d_GameEngine.getD_MapController().loadMap(p_S);
	}

	/**
	 * Invokes the existing saveMap method written for saving the domination map type.
	 * @param p_S Name of the map file in which it is to be stored.
	 * @return Acknowledgment of the saveMap function (success or failure).
	 * @throws Exception Any exception thrown during the saveMap function call.
	 */
	public String saveMap(String p_S) throws Exception
	{
		return d_GameEngine.getD_MapController().saveMap(p_S);
	}
}
