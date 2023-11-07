package com.soen6441.risk_game_u14.state;



import java.util.Scanner;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class ExecuteOrder extends Phase {

	
	private LogEntryBuffer d_LEB;

	public ExecuteOrder(GameEngine p_Ge) {
		super(p_Ge);
		try {
			d_LEB = new LogEntryBuffer();
			d_LEB.setResult("In Execute Order Phase");
			p_Ge.getD_PlayerController().playerExecuteOrder();
			p_Ge.getD_PlayerController().show();
			Scanner s = new Scanner(System.in);
			System.out.println("Do you want to continue y=Yes n = NO");
			String inpString = s.nextLine();
			if(inpString.equalsIgnoreCase("y")) {
				p_Ge.getD_GameModel().assignReinforcementArmies();
				p_Ge.setD_GamePhase(new IssueOrder(p_Ge));
			}
			else {
				System.out.println("Byee");
			}
			
//			p_Ge.setD_GamePhase(new ExecuteOrder(p_Ge));
			}catch(Exception p_E) {
				d_LEB.setResult(p_E.getMessage());
			}
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

}
