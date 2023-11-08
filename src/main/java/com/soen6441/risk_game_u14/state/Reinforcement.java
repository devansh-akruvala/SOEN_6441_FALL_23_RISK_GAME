package com.soen6441.risk_game_u14.state;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class Reinforcement extends Phase{

	LogEntryBuffer d_LEB;
	
	public Reinforcement(GameEngine p_Ge) {
		super(p_Ge);
		d_LEB = new LogEntryBuffer();
		d_LEB.setResult("In Reinforcement Phase");
		try{
			d_GameEngine.getD_GameModel().assignReinforcementArmies();
			d_GameEngine.getD_GameModel().showplayer();
			d_GameEngine.setD_GamePhase(new IssueOrder(d_GameEngine));
			}catch(Exception e){
				d_LEB.setResult(e.getMessage().toString());
			}
	}
	
	
	@Override
	public String editContinent(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String editCountry(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String editNeighbor(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String loadMap(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String saveMap(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String validateMap() {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String editMap(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String addPlayers(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		String result = "Invalid Command In Reinforcement Phase";
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public void showMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPhaseName() {
		// TODO Auto-generated method stub
		return "Reinforcement Phase";
	}

	
}
