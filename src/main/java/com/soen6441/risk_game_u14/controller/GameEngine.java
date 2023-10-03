package com.soen6441.risk_game_u14.controller;

import java.util.Scanner;

import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;

/**
 * The GameEngine Class Controls the Phases of Game
 * <ul>
 * <li>There are 3 phases
 * 
 * </ul>
 * 
 * @author Devansh,Meshva, Gowtham
 */

public class GameEngine {

	private GameModel d_GameModel;
	private MapController d_MapController;// =new MapController(new Map());
	private PlayerController d_PlayerController;
	int l_PhaseController;

	public GameEngine(GameModel p_GameModel) {
		d_GameModel = p_GameModel;
		d_MapController = new MapController(p_GameModel.getD_Map());
		d_PlayerController = new PlayerController(p_GameModel);
		l_PhaseController = 1;
	}

	public GameEngine() {

	}

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

			String l_CommandSection = l_Command.split(" ")[0];

			switch (l_CommandSection) {

			case "editcontinent":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.addContinentCommand(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "editcountry":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.addCountryCommand(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "editneighbor":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.addNeighborsCommand(l_Command));
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
				if (l_PhaseController > 1)
					System.out.println("\n\nGame Status: ");
				d_GameModel.showplayer();
				break;
			case "savemap":
				if (l_PhaseController == 1) {
					System.out.println(d_MapController.validateMap());
					System.out.println("Output: " + d_MapController.saveMap(l_Command));
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "loadmap":
				if (l_PhaseController == 1) {
					System.out.println("Output: " + d_MapController.loadMap(l_Command));
					System.out.println(d_MapController.validateMap());
					l_PhaseController = 2;
					System.out.println("In " + returnPhase(l_PhaseController));
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
					l_PhaseController = 3;
					System.out.println("In " + returnPhase(l_PhaseController));
					d_GameModel.startUpPhase();
					d_GameModel.showplayer();
					d_PlayerController.playerIssueOrder();
					d_PlayerController.playerExecuteOrder();
				} else {
					System.out.println("Invalid command in " + returnPhase(l_PhaseController));
				}
				break;
			case "quit":
				l_StopGame = !l_StopGame;
				l_ScannerObj.close();
				break;
			default:
				System.out.println("Enter Valid Command");

			}
		}
	}

	public String returnPhase(int p_PhaseController) {
		if (p_PhaseController == 1)
			return "Edit Phase";
		if (p_PhaseController == 2)
			return "GameStart Phase";
		return "Game Loop Phase";
	}

	public void showCommands() {
		System.out.println("Edit Phase Command:\n\n"+"editcontinent -add continentID continentvalue -remove continentID\r\n"
				+ "editcountry -add countryID continentID -remove countryID\r\n"
				+ "editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID\n"
				+ "savemap filename\r\n" + "editmap filename\r\n" + "validatemap\r\n" +"----------------------------------------------\n"+"StartUp Phase Commands:\n\n"+"loadmap filename\r\n"
				+ "gameplayer -add playername -remove playername\r\n" + "assigncountries\r\n"
				+"----------------------------------------------\n"
				+"In Game Phase Command\n\n"
				+ "deploy countryID num\r\n");

	}

}
