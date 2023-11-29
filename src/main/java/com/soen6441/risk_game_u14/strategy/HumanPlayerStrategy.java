package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Player;
import com.soen6441.risk_game_u14.state.IssueOrder;
import com.soen6441.risk_game_u14.state.Startup;

public class HumanPlayerStrategy extends Strategy implements Serializable {
	
	private LogEntryBuffer d_LEB;
	private Player d_Player;
	private GameModel d_GameModel;

	public HumanPlayerStrategy(Player p_Player, GameModel p_GameModel) {
		d_Player = p_Player;
		d_GameModel = p_GameModel;
		d_LEB = new LogEntryBuffer();
	}

	@Override
	public String createOrder() {
		// if correct command then dongt ask again
		String l_InputCommand = "";
		Scanner sc = new Scanner(System.in);
		boolean l_CorrectCommand = false;
		while (!l_CorrectCommand) {

			String l_EnteredComamnd = sc.nextLine();

			if (isCommandValid(l_EnteredComamnd)) {
				l_CorrectCommand = true;
				l_InputCommand = l_EnteredComamnd;
			} else {
				System.out.println("Invalid Command!!");
			}
		}
		if(l_InputCommand.split(" ")[0].equalsIgnoreCase("savegame")) {
			d_GameModel.saveGame(l_InputCommand.split(" ")[1]);
		}else if(l_InputCommand.split(" ")[0].equalsIgnoreCase("loadgame")){
			System.out.println("Invalid Command is IssueOrder Phase");
		}

		return l_InputCommand;
	}

	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createDeployOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createAdvanceOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCardOrder(String p_CardName) {
		// TODO Auto-generated method stub
		return null;
	}

	/***
	 * Ask orders from the players in a round-robin fashion and store them in an
	 * order queue
	 * 
	 * @param number The integer number to be parsed
	 * @return A boolean value
	 */

	public boolean isIntParsable(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			System.out.println("Enter Integer Value");
			return false;
		}
	}

	/**
	 * Checks if a country exists.
	 *
	 * @param p_CountryName The name of the country to be checked.
	 * @return True if the country exists; otherwise, false.
	 */
	public boolean isCountryExist(String p_CountryName) {
		boolean l_isExist = d_GameModel.getD_Map().countryAlreadyExist(p_CountryName);
		if (l_isExist)
			return true;
		else {
			System.out.println("Country " + p_CountryName + " Doesn't Exist");
			return false;
		}
	}

	/**
	 * Checks if a player exists in the game.
	 *
	 * @param p_name The name of the player being searched for.
	 * @return True if the player exists; otherwise, false.
	 */
	public Boolean isPlayerExist(String p_name) {
		for (Player l_tp : d_GameModel.getD_Players()) {
			if (l_tp.getD_PlayerName().equalsIgnoreCase(p_name)) {
				return true;
			}
		}
		System.out.println("Player with " + p_name + "Doesnot Exist");
		return false;
	}

	/**
	 * Checks if a command entered by a player is valid.
	 *
	 * @param p_Command The command entered by the player.
	 * @return True if the command is valid; otherwise, false.
	 */
	public boolean isCommandValid(String p_Command) {

		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandLength = l_CommandSplit.length;

		if (l_CommandSplit[0].equalsIgnoreCase("deploy") && l_CommandLength == 3 && isCountryExist(l_CommandSplit[1])
				&& isIntParsable(l_CommandSplit[2])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("advance") && l_CommandLength == 4
				&& isCountryExist(l_CommandSplit[1]) && isCountryExist(l_CommandSplit[2])
				&& isIntParsable(l_CommandSplit[3])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("bomb") && l_CommandLength == 2
				&& isCountryExist(l_CommandSplit[1])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("blockade") && l_CommandLength == 2
				&& isCountryExist(l_CommandSplit[1])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("airlift") && l_CommandLength == 4
				&& isCountryExist(l_CommandSplit[1]) && isCountryExist(l_CommandSplit[2])
				&& isIntParsable(l_CommandSplit[3])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("negotiate") && l_CommandLength == 2
				&& isPlayerExist(l_CommandSplit[1])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("exit")) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("savegame")) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("loadgame")) {
			return true;
		}
		return false;
	}
	
	public void saveGame(String p_Command) {
		this.d_GameModel.saveGame(p_Command.split(" ")[1]);
		//this.setD_GamePhase(new GameSaved(this));
	}
	
//	public void loadGame(String p_Command) {
//		boolean l_Flag=false;
//		this.d_GameModel=GameModel.loadGame(p_Command.split(" ")[1]);
//		if (this.d_GameModel==null) {
//			System.out.println("\nGame not found");
//			d_LEB.setResult("Game not found");
//			return;
//		}
//		d_LEB.setResult("Game Loaded");
//
//		if(this.d_GameModel.getD_Players().size()<=1) {
//			.setD_GamePhase(new Startup(this));
//		} else {
//			ArrayList<Player> l_Players=this.d_GameModel.getD_Players();
//			for(Player l_P : l_Players) {
//				if(l_P.getD_PlayerOwnedCountries().size()>0) {
//					l_Flag=true;
//					break;
//				}
//			}
//			if(l_Flag==true) {
//				this.setD_GamePhase(new IssueOrder(this));
//			} else {
//				this.setD_GamePhase(new Startup(this));
//			}
//		}
	
}
