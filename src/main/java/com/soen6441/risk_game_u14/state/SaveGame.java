package com.soen6441.risk_game_u14.state;

import java.io.Serializable;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class SaveGame extends Phase implements Serializable{

	LogEntryBuffer d_LEB;

	/**
	 * This is the constructor of Edit class which initializes Game engine object
	 * and command prompt object and assigning log entry buffer
	 * 
	 * @param p_Ge object of game engine
	 */
	public SaveGame(GameEngine p_Ge) {
		super(p_Ge);
		d_LEB = new LogEntryBuffer();
		d_LEB.setResult("In Saved game Phase");
	}
	
	@Override
	public String editContinent(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editCountry(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editNeighbor(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadMap(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveMap(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editMap(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addPlayers(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPhaseName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tournament(String p_String, String p_CommandStringFromInput) {
		// TODO Auto-generated method stub
		return null;
	}

}
