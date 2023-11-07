package com.soen6441.risk_game_u14.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/***
 * This class adds players to the game and randomly assigns countries amongst
 * players It also allocates armies to the players according to the WarZone
 * rules Each player is asked to enter their orders in a round-robin fashion
 * until their reinforcements are exhausted
 * 
 * @author Devansh, Aditya
 */
public class PlayerController {
	private GameModel d_GameModel;

	public PlayerController() {

	}

	/***
	 * Constructor for initializing the game
	 * 
	 * @param d_GameModel model object
	 */
	public PlayerController(GameModel d_GameModel) {
		this.d_GameModel = d_GameModel;
	}

	/***
	 * Adds the players to the game
	 * 
	 * @param p_Command pass the input command from the user
	 * @return the outcome of the command
	 */
	public String gamePlayerCommand(String p_Command) {
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;

		if (l_CommandSplit[0].equalsIgnoreCase("gameplayer")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplitLength) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex + 1 < l_CommandSplitLength) {
					try {
						d_GameModel.addPlayers(l_CommandSplit[l_WordIndex + 1]);
					} catch (Exception e) {
						return e.getMessage();
					}
					l_WordIndex += 2;
				} else if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")
						&& l_WordIndex + 1 < l_CommandSplitLength) {
					try {
						d_GameModel.removePlayers(l_CommandSplit[l_WordIndex + 1]);
					} catch (Exception e) {
						return e.getMessage();
					}
					l_WordIndex += 2;
				} else {
					// throw new Exception("Invalid Command");
					return "Invalid Command";
				}
			}
			return "Players command executed successfully";
		} else {
			return "Invalid Command";
		}

	}

	/***
	 * Ask orders from the players in a round-robin fashion and store them in an
	 * order queue
	 */

	public boolean isIntParsable(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isCountryExist(String p_CountryName) {
		boolean l_isExist = d_GameModel.getD_Map().countryAlreadyExist(p_CountryName);
		if(l_isExist)
			return true;
		else {
			System.out.println("Country "+p_CountryName+ " Doesn't Exist");
			return false;
		}
	}

	public boolean isCommandValid(String p_Command) {

		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandLength = l_CommandSplit.length;

		if (l_CommandSplit[0].equalsIgnoreCase("deploy") && l_CommandLength == 3 && isCountryExist(l_CommandSplit[1])
				&& isIntParsable(l_CommandSplit[2])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("advance") && l_CommandLength == 4
				&& isCountryExist(l_CommandSplit[1])
				&& isCountryExist(l_CommandSplit[2]) && isIntParsable(l_CommandSplit[3])) {
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
				&& isCountryExist(l_CommandSplit[1])) {
			return true;
		} else if (l_CommandSplit[0].equalsIgnoreCase("exit")) {
			return true;
		}
		return false;
	}

	public void playerIssueOrder() {
		// loop through player
		// ask for order
		// check order format
		// check country exist
		// check country belongs to player
		// if everything is ok then add player order to his queue;

		List<Player> l_PlayerList = d_GameModel.getD_Players();

		Map<Player, Boolean> l_IsPlayerExit = new HashMap<>();
		for (Player l_Player : l_PlayerList) {
			l_Player.setD_SkipCommands(false);
			l_IsPlayerExit.put(l_Player, false);
		}

		boolean l_AllPlayerDone = false;

		while (!l_AllPlayerDone) {

			l_AllPlayerDone = true;
			for (Player l_Player : l_PlayerList) {

				if (!l_Player.getD_PlayerName().equalsIgnoreCase("Neutral Player")) {

					if (l_IsPlayerExit.get(l_Player) == false) {

						l_AllPlayerDone = false;
						System.out.println(l_Player.getD_PlayerName() + "'s turn:");

						int l_PlayerArmies = l_Player.getD_ArmiesCount();
						boolean l_CorrectCommand = false;

						// if correct command then dongt ask again
						String l_InputCommand = "";
						Scanner sc = new Scanner(System.in);

						while (!l_CorrectCommand) {

							String l_EnteredComamnd = sc.nextLine();

							if (isCommandValid(l_EnteredComamnd)) {
								l_CorrectCommand = true;
								l_InputCommand = l_EnteredComamnd;
							} else {
								System.out.println("Invalid Command!!");
							}
						}
						if (l_InputCommand.equalsIgnoreCase("exit")) {
							l_IsPlayerExit.put(l_Player, true);
						} else {
							l_Player.issueOrder(l_InputCommand);
						}

					}
				}
			}

		}
	}

	/***
	 * Executes the orders from the queue in FIFO order
	 */
	public void playerExecuteOrder() {
		System.out.println("Executing Orders:");
		List<Player> l_PlayerList = d_GameModel.getD_Players();
		boolean l_AllPlayerDone = false;
		while (!l_AllPlayerDone) {
			l_AllPlayerDone = true;
			for (Player l_Player : l_PlayerList) {
				if (!l_Player.getD_PlayerName().equalsIgnoreCase("Neutral Player")) {
					if (l_Player.getD_PlayerOrderQueue().size() > 0 && l_Player.getD_SkipCommands() == false) {
						l_AllPlayerDone = false;
						Order l_nextOrder = l_Player.nextOrder();
						l_nextOrder.execute();
					}
				}
			}
		}
	}

	/***
	 * This is the display method for the game map
	 */
	public void show() {
		d_GameModel.showplayer();
	}

}
