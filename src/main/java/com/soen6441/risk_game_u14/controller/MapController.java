package com.soen6441.risk_game_u14.controller;

import com.soen6441.risk_game_u14.model.Map;



/**
 * 
 * 
 *@author Devansh Meshva
 * 
 */
public class MapController {
	private Map d_Map;

	public MapController(Map map) {
		this.d_Map = map;
	}

	public void addContinentCommand(String p_Command) throws Exception {
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;
		
		if (l_CommandSplit[0].equalsIgnoreCase("editcontinent")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplitLength) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex+2<l_CommandSplitLength) {				
					try {
						d_Map.addContinent(l_CommandSplit[l_WordIndex + 1], Integer.parseInt(l_CommandSplit[l_WordIndex + 2]));
						//System.out.println("Continent "+l_CommandSplit[l_WordIndex+1]+" with value "+l_CommandSplit[l_WordIndex+2]+" is added...");
					} catch (Exception e) {
						throw new Exception("Enter Int value");
					}
					l_WordIndex+=3;
				}
				else if(l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove") && l_WordIndex+1<l_CommandSplitLength) {
					System.out.println("Hand;le remove");
					l_WordIndex+=2;
				}
				else {
					throw new Exception("Invalid Command");
				}
			}
			System.out.println("Continents added succcessfully");
		} else {
			System.out.println("Invalid Command");
		}
	}

	public void addCountryCommand(String p_Command) throws Exception {
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;
		if (l_CommandSplit[0].equalsIgnoreCase("editcountry")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplitLength) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex+2<l_CommandSplitLength) {
					d_Map.addCountries(l_CommandSplit[l_WordIndex+1], l_CommandSplit[l_WordIndex+2]);
					//System.out.println("Country "+l_CommandSplit[l_WordIndex+1]+" is added in "+l_CommandSplit[l_WordIndex+2]+" continent");
					l_WordIndex+=3;
				}
				else if(l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove") && l_WordIndex+1<l_CommandSplitLength) {
					System.out.println("Hand;le remove");
					l_WordIndex+=2;
				}
				else {
					throw new Exception("Invalid Command");
				}
			}
			System.out.println("Countries added succcessfully");
		} else {
			System.out.println("Invalid Command Country");
		}
	}
	
	public void addNeighborsCommand(String p_Command) throws Exception {
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;
		if (l_CommandSplit[0].equalsIgnoreCase("editneighbor")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplit.length) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add")  && l_WordIndex+2<l_CommandSplitLength) {
					d_Map.addCountryNeighbour(l_CommandSplit[l_WordIndex+1], l_CommandSplit[l_WordIndex+2]);
					l_WordIndex+=3;
				}
				else if(l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove") && l_WordIndex+1<l_CommandSplitLength) {
					l_WordIndex+=2;
				}
				else {
					throw new Exception("Invalid Command");
				}
			}
			System.out.println("Neighbors added succcessfully");
		} else {
			System.out.println("Invalid Command Country");
		}
	}
	
	
	
	public void showMap() {
		d_Map.showMap();
	}
}
