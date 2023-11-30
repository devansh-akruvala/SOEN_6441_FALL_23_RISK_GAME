package com.soen6441.risk_game_u14.adapterpattern;

import com.soen6441.risk_game_u14.controller.GameEngine;

/**
 * Adapter class serves as a bridge between the target and adaptee.
 * It allows calling methods of the adaptee by using the methods defined in the target.
 */
public class Adapter extends Target{
	/**
	 * Object of the adaptee class to invoke methods for loading/saving conquest files.
	 */
	private Adaptee d_Adaptee;
	/**
	 * GameEngine object.
	 */
	private GameEngine d_GameEngine;
	/**
	 * Constructor bringing the GameEngine object into the class.
	 * @param p_GameEngine GameEngine object containing the map object internally.
	 */
	public Adapter(GameEngine p_GameEngine) {
		super(p_GameEngine);
	}
	/**
	 * Constructor bringing adaptee and gameEngine into the class.
	 * @param p_Adaptee Adaptee is used to invoke its methods from the load and save map methods of the constructor.
	 * @param p_GameEngine GameEngine contains the map file under which the map object is used.
	 */
	public Adapter (Adaptee p_Adaptee,GameEngine p_GameEngine){
		super(p_GameEngine);
		this.d_Adaptee=p_Adaptee;
		this.d_GameEngine=p_GameEngine;
	}

	/**
	 * Internally calls the saveMap method of adaptee to save a conquest map file.
	 * @param p_S Name of the map file.
	 * @return Success or error message for the implementation of this function.
	 */
	@Override
	public String saveMap(String p_S){
		return this.d_Adaptee.saveConquestMap(p_S,d_GameEngine);
	}


	/**
	 * Internally calls the loadMap method of adaptee to load a conquest map file.
	 * @param p_S Name of the map file.
	 * @return Success or error message for the implementation of this function.
	 */
	@Override
	public String loadMap(String p_S){
		return this.d_Adaptee.loadConquestMap(p_S, d_GameEngine);
	}
}
