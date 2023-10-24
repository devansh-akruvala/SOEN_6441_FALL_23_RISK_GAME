package com.soen6441.risk_game_u14.controller;

import java.util.Scanner;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;

/**
 * The GameEngine Class Controls the Phases of Game
 * <ul>
 * <li>There are 3 phases
 *
 * </ul>
 *
 * @author Devansh, Meshva, Gowtham
 */

public class GameEngine {

	private GameModel d_GameModel;
	private MapController d_MapController;
	private PlayerController d_PlayerController;
	int l_PhaseController;
	private LogEntryBuffer d_LEB;

	/***
	 * This is the main game engine constructor method which is responsible for
	 * controlling the map and player controllers and the game and map models in
	 * case of assign-counties.
	 * 
	 * @param p_GameModel instance for a game
	 */
	public GameEngine(GameModel p_GameModel) {
		d_GameModel = p_GameModel;
		d_MapController = new MapController(p_GameModel.getD_Map());
		d_PlayerController = new PlayerController(p_GameModel);
		l_PhaseController = 1;
		d_LEB=new LogEntryBuffer();
	}

	public GameEngine() {

	}

	/***
	 * This method is responsible for mapping the inputs from the user interface to
	 * the game engine
	 * 
	 * @throws Exception handles the exception at startup phase during
	 *                   assign-countries where the number of players is only one
	 *                   Or, more than the total number of countries
	 */
	public void listenCommand() throws Exception {
		showCommands();
		// 1 = edit phase
		// 2 = edit done load player gamestart
		// 3 = gameloop starts

		System.out.print("In " + returnPhase(l_PhaseController));
		System.out.println(" Waiting for Input:");
		System.out.println("----------------------------------------");
		Scanner l_ScannerObj = new Scanner(System.in);
		boolean l_StopGame = false;
		while (!l_StopGame) {
			String l_Command = l_ScannerObj.nextLine();
			d_LEB.setResult(l_Command);
			String l_CommandSection = l_Command.split(" ")[0];

			switch (l_CommandSection) {

			case "editcontinent":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.editContinentCommand(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "editcountry":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.editCountryCommand(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "editneighbor":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.editNeighborsCommand(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "editmap":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.editMap(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
			case "showmap":
				System.out.println("Map:  ");
				d_MapController.showMap();
				if (l_PhaseController > 1) {
					System.out.println("\n\nGame Status: ");
					d_GameModel.showplayer();
				}
				break;
			case "savemap":
				if (l_PhaseController == 1) {
					String l_ValidResult = d_MapController.validateMap();
					System.out.println(l_ValidResult);
					if (d_MapController.validateMap().equalsIgnoreCase(" Map is not valid!!")) {
						System.out.println("Map cannot be saved!!");
					} else {
						System.out.println("Output: " + d_MapController.saveMap(l_Command));
						l_PhaseController = 2;
					}
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "loadmap":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.loadMap(l_Command));
					String l_ValidResult = d_MapController.validateMap();
					System.out.println(l_ValidResult);
					if (d_MapController.validateMap().equalsIgnoreCase(" Map is not valid!!")) {
						System.out.println("Reseting loaded map!! please load a valid map");
						d_MapController.resetMap();
					} else {
						l_PhaseController = 2;
						System.out.println("In " + returnPhase(l_PhaseController));
					}
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "validatemap":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.validateMap());
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "gameplayer":
				if (l_PhaseController == 2) {
					System.out.println("Output: " + d_PlayerController.gamePlayerCommand(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "assigncountries":
				if (l_PhaseController == 2) {
					System.out.println("In " + returnPhase(l_PhaseController));
					try {
						d_GameModel.startUpPhase();
						d_GameModel.showplayer();
						d_PlayerController.playerIssueOrder();
						d_PlayerController.playerExecuteOrder();
						l_PhaseController = 3;
						d_MapController.showMap();
						d_PlayerController.show();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "quit":
				l_StopGame = !l_StopGame;
				l_ScannerObj.close();
				break;
			default:
				System.out.println("Output: Invalid Comamnd!!");

			}
		}
	}

	/***
	 * This method identifies the current game stage in order for the different
	 * sections of the game to work properly
	 * 
	 * @param p_PhaseController contains the code for current phase of game
	 * @return the current game stage
	 */
	public String returnPhase(int p_PhaseController) {
		if (p_PhaseController == 1)
			return "Edit Phase";
		if (p_PhaseController == 2)
			return "GameStart Phase";
		return "Game Loop Phase";
	}

	/***
	 * This method lists the valid game commands to the user
	 */
	public void showCommands() {
		System.out.println(
				"Edit Phase Command:\n\n" + "editcontinent -add continentID continentvalue -remove continentID\r\n"
						+ "editcountry -add countryID continentID -remove countryID\r\n"
						+ "editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID\n"
						+ "savemap filename\r\n" + "editmap filename\r\n" + "validatemap\r\n"
						+ "----------------------------------------------\n" + "StartUp Phase Commands:\n\n"
						+ "loadmap filename\r\n" + "gameplayer -add playername -remove playername\r\n"
						+ "assigncountries\r\n" + "----------------------------------------------\n"
						+ "In Game Phase Command\n\n" + "deploy countryID num\r\n");

	}

}
