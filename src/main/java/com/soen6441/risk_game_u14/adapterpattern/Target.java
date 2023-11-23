package com.soen6441.risk_game_u14.adapterpattern;

import com.soen6441.risk_game_u14.controller.GameEngine;

/**
 * This is the target class for the adapter pattern. 
 * This class is used to call the existing implementation of loadmap and savemap functionality of domination map type.
 */
public class Target {
	/**
	 * GameEngine object to call the loadmap/savemap methods of domination type
	 */
	private GameEngine d_GameEngine;
	/**
	 * Default constructor used for testing
	 */
	public Target() {}
	
	/**
	 * Constructor which assigns GameEngine object to be used in this class
	 * @param p_GameEngine object of the GameEngine class
	 */
	public Target(GameEngine p_GameEngine)
	{
		d_GameEngine=p_GameEngine;
	}
	
	
	/**
	 * This method calls the existing loadmap method written for loading/editing the domination map type.
	 * @param p_S argument which contains the name of the map file.
	 * @return acknowlegement of the loadmap function. Success or failure
	 * @throws Exception any exception thrown during the loadmap function call
	 */
	public String loadMap(String p_S) throws Exception
	{
		return d_GameEngine.getD_MapController().loadMap(p_S);
	}
	
	/**
	 * This method calls the existing savemap method written for saving the domination map type.
	 * @param p_S argument which contains the name of the map file in which it is to be stored.
	 * @return acknowlegement of the savemap function. Success or failure
	 * @throws Exception any exception thrown during the savemap function call
	 */
	public String saveMap(String p_S) throws Exception
	{
		return d_GameEngine.getD_MapController().saveMap(p_S);
	}
}
