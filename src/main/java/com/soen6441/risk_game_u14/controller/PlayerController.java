package com.soen6441.risk_game_u14.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Orders;
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
	public void playerIssueOrder() {
		// loop through player
		// ask for order
		// check order format
		// check country exist
		// check country belongs to player
		// if everything is ok then add player order to his queue;

		List<Player> l_PlayerList = d_GameModel.getD_Players();
		boolean l_AllPlayerDone = false;
		while (!l_AllPlayerDone) {
			l_AllPlayerDone = true;
			for (Player l_Player : l_PlayerList) {
				if (l_Player.getD_ArmiesCount() > 0) {
					l_AllPlayerDone = false;
					System.out.println(l_Player.getD_PlayerName() + "'s turn:");
					int l_PlayerArmies = l_Player.getD_ArmiesCount();
					Scanner sc = new Scanner(System.in);
					String l_InputCommand = sc.nextLine();
					String l_InputCommandSplit[] = l_InputCommand.split(" ");
					int l_InputCommandsize = l_InputCommandSplit.length;
					if (l_InputCommandSplit[0].equalsIgnoreCase("deploy") && l_InputCommandsize == 3) {
						String l_Country = l_InputCommandSplit[1];
						int l_noOfArmiesToBeDeployed = Integer.parseInt(l_InputCommandSplit[2]);
						Country l_TargetCountry = d_GameModel.getD_Map().findCountryByName(l_Country);
						if (l_TargetCountry != null) {
							if (l_Player.getD_PlayerOwnedCountries().indexOf(l_TargetCountry) >= 0) {
								if (l_PlayerArmies >= l_noOfArmiesToBeDeployed) {
									l_Player.setD_CurrentCommand(
											new Orders(l_InputCommand, this.d_GameModel.getD_Map()));
									l_Player.issueOrder();
									// update player armies
									l_Player.setD_ArmiesCount(l_PlayerArmies - l_noOfArmiesToBeDeployed);
								} else {
									System.out.println("Player doesn't have enough armies!!");

								}
							} else {
								System.out.println(l_Player.getD_PlayerName() + " is not owner of " + l_Country);

							}
						} else {
							System.out.println("Input Country doesn't exist!!");

						}
					} else {
						System.out.println("Invalid Command");
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
//				System.out.println(l_Player.getD_PlayerName() + "'s order: ");
				if (l_Player.getD_PlayerOrderQueue().size() > 0) {
					l_AllPlayerDone = false;
					Orders l_nextOrder = l_Player.nextOrder();
//					System.out.println(l_nextOrder.getD_Orders());
					l_nextOrder.execute();
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
