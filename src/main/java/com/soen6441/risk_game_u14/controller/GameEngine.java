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
 * @author Devansh 
 */

public class GameEngine {
	
	MapController d_MapController=new MapController(new Map());
	
	public void listenCommand() throws Exception {
		showCommands();
		System.out.println("Waiting for Input:\n");
		Scanner s = new Scanner(System.in);
		boolean stopGame=false;
		while(!stopGame) {
			String command = s.nextLine();
			System.out.println(command);
			
			
			String commandSection = command.split(" ")[0];
			
			switch(commandSection) {
				
			case "editcontinent":
				System.out.println("Edit Continent");
				
				System.out.println("Calling Method It will take care of all commands");
				System.out.println("Displaying Result");
				d_MapController.addContinentCommand(command);
				break;
				
			case "editcountry":
				System.out.println("Edit countries");
				System.out.println("Calling Method");
				System.out.println("Displaying Result");
				d_MapController.addCountryCommand(command);
				break;
			case "editneighbor":
				System.out.println("edit neighbor command...");
				System.out.println("Calling Method");
				System.out.println("Displaying Result");
				d_MapController.addNeighborsCommand(command);
				break;
			
			case "showmap":
				System.out.println("ShowMap command...");
				System.out.println("Calling Method");
				System.out.println("Displaying Result");
				d_MapController.showMap();
				break;
			case "quit":
				stopGame=!stopGame;
				s.close();
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
