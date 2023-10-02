package com.soen6441.risk_game_u14.controller;

import java.util.Scanner;

import com.soen6441.risk_game_u14.model.Map;

/**
 * The GameEngine Class Controls the Phases of Game
 * <ul>
 * <li>There are 3 phases 
 * 
 * </ul>
 * 
 * @author Devansh,Meshva
 */

public class GameEngine {
	
	MapController d_MapController=new MapController(new Map());
	
	public void listenCommand() throws Exception {
		showCommands();
		System.out.println("Waiting for Input:\n");
		Scanner l_ScannerObj = new Scanner(System.in);
		boolean l_StopGame=false;
		while(!l_StopGame) {
			String l_Command = l_ScannerObj.nextLine();
			
			
			String l_CommandSection = l_Command.split(" ")[0];
			
			switch(l_CommandSection) {
				
			case "editcontinent":
				System.out.println("Output: "+d_MapController.addContinentCommand(l_Command));
				break;
				
			case "editcountry":
				System.out.println("Output: "+d_MapController.addCountryCommand(l_Command));
				break;
			case "editneighbor":
				System.out.println("Output: "+d_MapController.addNeighborsCommand(l_Command));
				break;
			
			case "showmap":
				System.out.println("Output: ");
				d_MapController.showMap();
				break;
			case "savemap":
				System.out.println(d_MapController.validateMap());
				System.out.println("Output: "+d_MapController.saveMap(l_Command));
				break;
			case "loadmap":
				System.out.println("Output: "+d_MapController.loadMap(l_Command));
				System.out.println(d_MapController.validateMap());
				break;
			case "validatemap":
				System.out.println("Output: "+d_MapController.validateMap());
				break;
			case "quit":
				l_StopGame=!l_StopGame;
				l_ScannerObj.close();
				break;
			default:
				System.out.println("Enter Valid Command");
			
			}	
		}
	}
	

	public void showCommands() {
		
		System.out.println("editcontinent -add continentID continentvalue -remove continentID\r\n"
				+ "editcountry -add countryID continentID -remove countryID\r\n"
				+ "editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID\n");

	}

	
}
