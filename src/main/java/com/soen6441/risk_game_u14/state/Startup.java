package com.soen6441.risk_game_u14.state;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class Startup extends Phase{
	private LogEntryBuffer d_LEB;
	
	public Startup(GameEngine p_Ge) {
		super(p_Ge);
		d_LEB = new LogEntryBuffer();
		d_LEB.setResult("In Startup Phase");
	}
	
	@Override
	public String editContinent(String p_command) {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String editCountry(String p_command) {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String editNeighbor(String p_command) {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String loadMap(String p_command) {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String saveMap(String p_command) {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String validateMap() {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String editMap(String p_command) {
		return "Invalid Command in Startup phase";
	}

	@Override
	public String addPlayers(String p_command) {
		return d_GameEngine.getD_PlayerController().gamePlayerCommand(p_command);
	}

	@Override
	public String assignCountries() {
		try {
			d_GameEngine.getD_GameModel().startUpPhase();
		} catch (Exception e) {
			return e.getMessage();
		}
		d_GameEngine.setD_GamePhase(new Reinforcement(d_GameEngine));
		return "";
		
	}

	@Override
	public void showMap() {
		d_GameEngine.getD_MapController().showMap();
	}

	@Override
	public String getPhaseName() {
		return "Startup Phase";
	}

}
