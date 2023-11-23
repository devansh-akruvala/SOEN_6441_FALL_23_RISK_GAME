package com.soen6441.risk_game_u14.state;

import java.io.File;
import java.util.Scanner;

import com.soen6441.risk_game_u14.adapterpattern.Adaptee;
import com.soen6441.risk_game_u14.adapterpattern.Adapter;
import com.soen6441.risk_game_u14.adapterpattern.Target;
import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

/**
 * The Edit Phase extends the phase class and implements all the methods
 * suitable for that particular phase. It returns invalid command for others
 * which are not compatible with this phase
 */
public class Edit extends Phase {

	LogEntryBuffer d_LEB;

	/**
	 * This is the constructor of Edit class which initializes Game engine object
	 * and command prompt object and assigning log entry buffer
	 * 
	 * @param p_Ge object of game engine
	 */
	public Edit(GameEngine p_Ge) {
		super(p_Ge);
		d_LEB = new LogEntryBuffer();
		d_LEB.setResult("In Edit Phase");
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String editContinent(String p_command) {
		String result = d_GameEngine.getD_MapController().editContinentCommand(p_command);
		d_LEB.setResult(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String editCountry(String p_command) {
		String result = d_GameEngine.getD_MapController().editCountryCommand(p_command);
		d_LEB.setResult(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String editNeighbor(String p_command) {
		String result = d_GameEngine.getD_MapController().editNeighborsCommand(p_command);
		d_LEB.setResult(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String loadMap(String p_command) {
		String l_response = "";

		boolean l_Flag = false;
		String l_AckMsg;
		try {
			String l_Path = "saved_maps\\";
			File l_File = new File(l_Path + p_command.split(" ")[1]);
			Scanner l_Sc = new Scanner(l_File);
			while (l_Sc.hasNextLine()) {

				String l_Line = l_Sc.nextLine();
				if (l_Line.contains("Territories")) {
					l_Flag = true;
					break;
				}
			}
			l_Sc.close();
			if (l_Flag) {
				Target l_TargetObject = new Adapter(new Adaptee(), d_GameEngine);
				l_AckMsg = l_TargetObject.loadMap(p_command.split(" ")[1]);
			} else {
				l_response = d_GameEngine.getD_MapController().loadMap(p_command);
			}

		} catch (Exception p_Exception) {
			l_response = "Reseting loaded map!! please load a valid map";
			d_LEB.setResult(l_response);
			d_GameEngine.setD_GamePhase(new Edit(d_GameEngine));
			return l_response;
		}
		String l_ValidResult = d_GameEngine.getD_MapController().validateMap();
		System.out.println(l_ValidResult);
        if (l_ValidResult.equalsIgnoreCase(" Map is not valid!!")) {
            l_response = "Reseting loaded map!! please load a valid map";
            d_GameEngine.getD_MapController().resetMap();
            return l_response;
        }
        d_GameEngine.setD_GamePhase(new Startup(d_GameEngine));
		
		d_LEB.setResult(l_response);
		return l_response;

	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String saveMap(String p_command) {
		String l_response = "";
		String l_ValidResult = d_GameEngine.getD_MapController().validateMap();
		System.out.println(l_ValidResult);
		if (l_ValidResult.equalsIgnoreCase(" Map is not valid!!")) {
			l_response = "Map cannot be saved!!";
		} else {
			Scanner s = new Scanner(System.in);
			try {
				while (true) {
					System.out.println("Enter 1 to save map in Conquest Format   2 to save in Domination format\n");
					int choice = s.nextInt();

					if (choice == 1) {

						Target l_TargetObject = new Adapter(new Adaptee(), d_GameEngine);
						l_response = l_TargetObject.saveMap(p_command.split(" ")[1]);
						break;
					} else if (choice == 2) {
						Target l_TargetObject = new Target(d_GameEngine);
						l_response = l_TargetObject.saveMap(p_command);
						break;
					} else {
						System.out.println("Please Enter your choice 1 or 2");
					}
				}
			} catch (Exception p_Exception) {
				l_response = p_Exception.getMessage();
			}

		}
		d_LEB.setResult(l_response);

		return l_response;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String validateMap() {
		String result = d_GameEngine.getD_MapController().validateMap();
		d_LEB.setResult(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String editMap(String p_command) {
//		return d_GameEngine.getD_MapController().editMap(p_command);

		String l_response = "";

		boolean l_Flag = false;
		String l_AckMsg;
		try {
			String l_Path = "saved_maps\\";
			File l_File = new File(l_Path + p_command.split(" ")[1]);
			Scanner l_Sc = new Scanner(l_File);
			while (l_Sc.hasNextLine()) {

				String l_Line = l_Sc.nextLine();
				if (l_Line.contains("Territories")) {
					l_Flag = true;
					break;
				}
			}
			l_Sc.close();
			if (l_Flag) {
				Target l_TargetObject = new Adapter(new Adaptee(), d_GameEngine);
				l_AckMsg = l_TargetObject.loadMap(p_command.split(" ")[1]);
			} else {
				l_response = d_GameEngine.getD_MapController().loadMap(p_command);

			}

		} catch (Exception p_Exception) {
			l_response = "Reseting loaded map!! please load a valid map";
			d_LEB.setResult(l_response);
			d_GameEngine.setD_GamePhase(new Edit(d_GameEngine));
			return l_response;
		}
		String l_ValidResult = d_GameEngine.getD_MapController().validateMap();
		System.out.println(l_ValidResult);
        if (l_ValidResult.equalsIgnoreCase(" Map is not valid!!")) {
            l_response = "Reseting loaded map!! please load a valid map";
            d_GameEngine.getD_MapController().resetMap();
            return l_response;
        }		
		d_LEB.setResult(l_response);
		return l_response;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String addPlayers(String p_command) {
		String result = "Invalid Command in Edit phase";
		d_LEB.setResult(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String assignCountries() {
		String result = "Invalid Command in Edit phase";
		d_LEB.setResult(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public void showMap() {
		d_GameEngine.getD_MapController().showMap();

	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String getPhaseName() {
		// TODO Auto-generated method stub
		return "Edit Phase";
	}

}
