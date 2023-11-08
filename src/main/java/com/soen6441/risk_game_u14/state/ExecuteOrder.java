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
			if (p_Ge.getD_PlayerController().checkTheWinner() == 1) {
				Scanner s = new Scanner(System.in);
				System.out.println("Do you want to continue y = Yes n = No");
				String inpString = s.nextLine();
				if (inpString.equalsIgnoreCase("y")) {
					p_Ge.setD_GamePhase(new Reinforcement(p_Ge));
				} else {
					System.out.println("Byee");
				}
			} else {
				p_Ge.setD_GamePhase(new Gameover(p_Ge));

			}
		} catch (Exception p_E) {
			d_LEB.setResult(p_E.getMessage());
		}
	}

	@Override
	public String editContinent(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String editCountry(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String editNeighbor(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String loadMap(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String saveMap(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String validateMap() {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String editMap(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String addPlayers(String p_command) {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
		d_LEB.setResult(result);
		return result;
	}

	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		String result = "Invalid Command in Execute Order Phase" ;
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
		return "Execute Order" ;
		
	}

}
