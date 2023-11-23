package com.soen6441.risk_game_u14.adapterpattern;

import com.soen6441.risk_game_u14.controller.GameEngine;

/**
 * Adapter class works as a bridge between the target and adaptee. 
 * We can use the adapter to call the method of adaptee by calling the methods used in target. 
 */
public class Adapter extends Target{
	/**
	 * Object of the adaptee class to call the methods to load/save conquest files
	 */
	private Adaptee d_Adaptee; 
	/**
	 * GameEngine object 
	 */
	private GameEngine d_GameEngine;
	/**
	 * Constructor which bring the GameEngine object in the class
	 * @param p_GameEngine object of GameEngine which contains the map object internally
	 */
	public Adapter(GameEngine p_GameEngine) {
		super(p_GameEngine);
	}
	/**
	 * This constructor bring adptee and gameengine to the class. 
	 * @param p_Adaptee Adaptee is used to call the method of it from the load and savemap methods of constructor. 
	 * @param p_GameEngine GameEngine contains the map file under which we are going to use map object
	 */
	public Adapter (Adaptee p_Adaptee,GameEngine p_GameEngine){
		super(p_GameEngine);
		this.d_Adaptee=p_Adaptee;
		this.d_GameEngine=p_GameEngine;
	}
	
	/**
	 * This method internally calls the savemap method of adaptee to save conquest map file. 
	 * @param p_S name of the map file
	 */
	@Override
	public String saveMap(String p_S){
		return this.d_Adaptee.saveConquestMap(p_S,d_GameEngine);
	}
	

	/**
	 * This method internally calls the loadmap method of adaptee to load the conquest map file. 
	 * @param p_S name of the map file
	 */
	@Override
	public String loadMap(String p_S){
		return this.d_Adaptee.loadConquestMap(p_S, d_GameEngine);
	}
}
