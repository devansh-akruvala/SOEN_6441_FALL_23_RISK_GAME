package com.soen6441.risk_game_u14.controller;

import com.soen6441.risk_game_u14.model.Map;



/**
 * 
 * 
 *@author Devansh 
 * 
 */
public class MapController {
	private Map d_Map;

	public MapController(Map map) {
		this.d_Map = map;
	}

	public void addContinentCommand(String command) throws Exception {
		String commandSplit[] = command.split(" ");
		System.out.println(commandSplit[0]);
		if (commandSplit[0].equalsIgnoreCase("editcontinent")) {
			int i = 1;
			while (i < commandSplit.length) {
				System.out.println(commandSplit[i]);
				if (commandSplit[i].equalsIgnoreCase("-add")) {
					System.out.println("Handling Add Please Add size chevk");
					try {
						d_Map.addContinent(commandSplit[i + 1], Integer.parseInt(commandSplit[i + 2]));
					} catch (Exception e) {
						throw new Exception("Enter Int value");
					}
					i+=3;
				}
				else if(commandSplit[i].equalsIgnoreCase("-remove")) {
					System.out.println("Handling Remove Please Add size chevk");

					System.out.println("Hand;le remove");
					i+=2;
				}
				else {
					throw new Exception("Invalid Command");
				}
			}
		} else {
			System.out.println("Invalid Command");
		}
	}

	public void addCountryCommand(String command) throws Exception {
		String commandSplit[] = command.split(" ");
		System.out.println(commandSplit[0]);
		if (commandSplit[0].equalsIgnoreCase("editcountry")) {
			int i = 1;
			while (i < commandSplit.length) {
				if (commandSplit[i].equalsIgnoreCase("-add")) {
					System.out.println("Handling Add Please Add size chevk");
					d_Map.addCountries(commandSplit[i+1], commandSplit[i+2]);
					i+=3;
				}
				else if(commandSplit[i].equalsIgnoreCase("-remove")) {
					System.out.println("Handling Remove Please Add size chevk");

					System.out.println("Hand;le remove");
					i+=2;
				}
				else {
					throw new Exception("Invalid Command");
				}
			}
		} else {
			System.out.println("Invalid Command Country");
		}
	}
	
	public void addNeighborsCommand(String command) throws Exception {
		String commandSplit[] = command.split(" ");
		System.out.println(commandSplit[0]);
		if (commandSplit[0].equalsIgnoreCase("editneighbor")) {
			int i = 1;
			while (i < commandSplit.length) {
				if (commandSplit[i].equalsIgnoreCase("-add")) {
					System.out.println("Handling Add Please Add size chevk");
					d_Map.addCountryNeighbour(commandSplit[i+1], commandSplit[i+2]);
					i+=3;
				}
				else if(commandSplit[i].equalsIgnoreCase("-remove")) {
					System.out.println("Handling Remove Please Add size chevk");

					System.out.println("Hand;le remove");
					i+=2;
				}
				else {
					throw new Exception("Invalid Command");
				}
			}
		} else {
			System.out.println("Invalid Command Country");
		}
	}
	
	
	
	public void showMap() {
		d_Map.showMap();
	}
}
