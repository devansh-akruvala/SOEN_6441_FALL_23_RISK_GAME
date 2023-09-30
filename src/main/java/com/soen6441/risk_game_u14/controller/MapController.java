package com.soen6441.risk_game_u14.controller;

import com.soen6441.risk_game_u14.model.Map;



/**
 * 
 * 
 *@author Devansh Meshva Smridhi
 * 
 */
public class MapController {
	private Map d_Map;
	
	public MapController(Map map) {
		this.d_Map = map;
	}

	public String addContinentCommand(String p_Command){
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;
		
		if (l_CommandSplit[0].equalsIgnoreCase("editcontinent")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplitLength) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex+2<l_CommandSplitLength) {				
					try {
						d_Map.addContinent(l_CommandSplit[l_WordIndex + 1], Integer.parseInt(l_CommandSplit[l_WordIndex + 2]));
					} catch (NumberFormatException ne) {
						return "Enter integer for Continent value";
					} 
					catch (Exception e) {
						return e.getMessage();
					}
					l_WordIndex+=3;
				}
				else if(l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove") && l_WordIndex+1<l_CommandSplitLength) {
					System.out.println("Handle remove");
					l_WordIndex+=2;
				}
				else {
					//throw new Exception("Invalid Command");
					return "Invalid Command";
				}
			}
			return "Continents added succcessfully";
		} else {
			System.out.println("Invalid Command");
			return "Invalid Command";
		}
	}

	public String addCountryCommand(String p_Command) {
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;
		if (l_CommandSplit[0].equalsIgnoreCase("editcountry")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplitLength) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex+2<l_CommandSplitLength) {
					try {
						d_Map.addCountries(l_CommandSplit[l_WordIndex+1], l_CommandSplit[l_WordIndex+2]);
					} catch (Exception e) {
						return e.getMessage();
					}
					l_WordIndex+=3;
				}
				else if(l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove") && l_WordIndex+1<l_CommandSplitLength) {
					System.out.println("Handle remove");
					l_WordIndex+=2;
				}
				else {
					System.out.println("Invalid Command");
					return "Invalid Command";
				}
			}
			return "Countries added successfully";
		} else {
			return "Invalid Command";
		}
	}
	
	public String addNeighborsCommand(String p_Command) {
		String l_CommandSplit[] = p_Command.split(" ");
		int l_CommandSplitLength = l_CommandSplit.length;
		if (l_CommandSplit[0].equalsIgnoreCase("editneighbor")) {
			int l_WordIndex = 1;
			while (l_WordIndex < l_CommandSplit.length) {
				if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add")  && l_WordIndex+2<l_CommandSplitLength) {
					try {
						d_Map.addCountryNeighbour(l_CommandSplit[l_WordIndex+1], l_CommandSplit[l_WordIndex+2]);
					} catch (Exception e) {
						return e.getMessage();
					}
					l_WordIndex+=3;
				}
				else if(l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove") && l_WordIndex+1<l_CommandSplitLength) {
					l_WordIndex+=2;
				}
				else {
					
					//throw new Exception("Invalid Command");
					return "Invalid Command";
				}
			}
			return "Neighbors added succcessfully";
		} else {
			return "Invalid Command";
//			System.out.println("Invalid Command Country");
		}
	}



	public String removeCountryCommand(String p_Command) throws Exception {
    
    String l_CommandSplit[] = p_Command.split(" ");
    int l_CommandSplitLength = l_CommandSplit.length;

    // Check if the first word in the command is "editcountry" 
    if (l_CommandSplit[0].equalsIgnoreCase("editcountry")) {
        int l_WordIndex = 1;

        while (l_WordIndex < l_CommandSplitLength) {
            // Check if the current word is "-remove" and there is at least one more word in the command
            if (l_WordIndex + 1 < l_CommandSplitLength &&
                l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")) {
                // Call method d_Map.removeCountry to remove a country
                d_Map.removeCountry(l_CommandSplit[l_WordIndex + 1]);

                // index forward by 2 to skip the removed country name
                l_WordIndex += 2;
            } else {
                
                System.out.println("Invalid Command");
                return "Invalid Command";
            }
        }

        
        System.out.println("Countries removed successfully");
        return "Countries removed successfully";
    } else {
        // If the first word is not "editcountry"
        System.out.println("Invalid Command");
        return "Invalid Command";
    }
}
	
	public String saveMap(String p_Command){
		String l_FileName[] = p_Command.split(" ");
		try {
			d_Map.saveFile(l_FileName[1]);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "map file saved successfully in saved_map directory!!";
	}

	public String loadMap(String p_Command) {
		String l_FileName[] = p_Command.split(" ");
		try {
			d_Map.loadFile(l_FileName[1]);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Map file loaded successfully!!";
	}
	
	public void showMap() {
		d_Map.showMap();
	}
}
