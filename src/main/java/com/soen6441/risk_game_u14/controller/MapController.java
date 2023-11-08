package com.soen6441.risk_game_u14.controller;

import com.soen6441.risk_game_u14.model.Map;

/**
 * This is the controller class for maps of the game
 *
 * @author Devansh Meshva Smridhi Aditya
 */
public class MapController {
    private Map d_Map;

    public MapController(Map map) {
        this.d_Map = map;
    }

    /***
     * This method adds the continents to the map, as entered by the user
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the user command
     */
    public String editContinentCommand(String p_Command) {
        String l_CommandSplit[] = p_Command.split(" ");
        int l_CommandSplitLength = l_CommandSplit.length;

        if (l_CommandSplit[0].equalsIgnoreCase("editcontinent")) {
            int l_WordIndex = 1;
            while (l_WordIndex < l_CommandSplitLength) {
                if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex + 2 < l_CommandSplitLength) {
                    try {
                        d_Map.addContinent(l_CommandSplit[l_WordIndex + 1],
                                Integer.parseInt(l_CommandSplit[l_WordIndex + 2]));
                    } catch (NumberFormatException ne) {
                        return "Enter integer for Continent value";
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 3;
                } else if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")
                        && l_WordIndex + 1 < l_CommandSplitLength) {
                    try {
                        d_Map.removeContinent(l_CommandSplit[l_WordIndex + 1]);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 2;
                } else {
                    // throw new Exception("Invalid Command");
                    return "Invalid Command";
                }
            }
            return "Continents command executed successfully";
        } else {
            return "Invalid Command";
        }
    }

    /***
     * This method adds the countries and the continent they belong to, in the map,
     * as entered by the user
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the user command
     */
    public String editCountryCommand(String p_Command) {
        String l_CommandSplit[] = p_Command.split(" ");
        int l_CommandSplitLength = l_CommandSplit.length;
        if (l_CommandSplit[0].equalsIgnoreCase("editcountry")) {
            int l_WordIndex = 1;
            while (l_WordIndex < l_CommandSplitLength) {
                if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex + 2 < l_CommandSplitLength) {
                    try {
                        d_Map.addCountries(l_CommandSplit[l_WordIndex + 1], l_CommandSplit[l_WordIndex + 2]);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 3;
                } else if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")
                        && l_WordIndex + 1 < l_CommandSplitLength) {
                    try {
                        d_Map.removeCountry(l_CommandSplit[l_WordIndex + 1]);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 2;
                } else {
                    System.out.println("Invalid Command");
                    return "Invalid Command";
                }
            }
            return "Countries command executed successfully";
        } else {
            return "Invalid Command";
        }
    }

    /***
     * This method declares a country as neighbor to the specified country, as
     * decided by the user.
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the user command
     */
    public String editNeighborsCommand(String p_Command) {
        String l_CommandSplit[] = p_Command.split(" ");
        int l_CommandSplitLength = l_CommandSplit.length;
        if (l_CommandSplit[0].equalsIgnoreCase("editneighbor")) {
            int l_WordIndex = 1;
            while (l_WordIndex < l_CommandSplit.length) {
                if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex + 2 < l_CommandSplitLength) {
                    try {
                        d_Map.addCountryNeighbour(l_CommandSplit[l_WordIndex + 1], l_CommandSplit[l_WordIndex + 2]);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 3;
                } else if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")
                        && l_WordIndex + 2 < l_CommandSplitLength) {
                    try {
                        d_Map.removeNeighbor(l_CommandSplit[l_WordIndex + 1], l_CommandSplit[l_WordIndex + 2], false);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 3;
                } else {
                    return "Invalid Command";
                }
            }
            return "Neighbors command executed successfully";
        } else {
            return "Invalid Command";
            // System.out.println("Invalid Command Country");
        }
    }

    /***
     * This method removes a country in the map, as chosen by the user
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the user command
     * @throws Exception in case of the country or neighbor to be removed is invalid
     */
    public String removeCountryCommand(String p_Command) throws Exception {

        String l_CommandSplit[] = p_Command.split(" ");
        int l_CommandSplitLength = l_CommandSplit.length;

        // Check if the first word in the command is "editcountry"
        if (l_CommandSplit[0].equalsIgnoreCase("editcountry")) {
            int l_WordIndex = 1;

            while (l_WordIndex < l_CommandSplitLength) {
                // Check if the current word is "-remove" and there is at least one more word in
                // the command
                if (l_WordIndex + 1 < l_CommandSplitLength && l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")) {
                    // Call method d_Map.removeCountry to remove a country
                    d_Map.removeCountry(l_CommandSplit[l_WordIndex + 1]);

                    // index forward by 2 to skip the removed country name
                    l_WordIndex += 2;
                } else {
                    return "Invalid Command";
                }
            }

            System.out.println("Countries removed successfully");
            return "Countries removed successfully";
        } else {
            // If the first word is not "editcountry"
            return "Invalid Command";
        }
    }

    /***
     * This method creates a local copy of the map that can be loaded later on.
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the command
     */
    public String saveMap(String p_Command) {
        String l_FileName[] = p_Command.split(" ");
        try {
            d_Map.saveFile(l_FileName[1]);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Map file saved successfully in saved_map directory!";
    }

    /***
     * This method loads the locally saved map file from the saveMap() method to the
     * current game instance.
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the command
     */
    public String loadMap(String p_Command) {
        String l_FileName[] = p_Command.split(" ");
        try {
            d_Map.loadFile(l_FileName[1]);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Map file loaded successfully!!";
    }

    /***
     * This method checks if the map is valid based on the game rules
     *
     * @return the outcome of the command
     */
    public String validateMap() {
        try {
            d_Map.ValidateMap();
        } catch (Exception e) {
            return e.getMessage() + " Map is not valid!!";
        }
        return "Map is Valid";
    }

    /***
     * This method can edit the map until load map phase
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the command
     */
    public String editMap(String p_Command) {
        String l_FileName[] = p_Command.split(" ");
        try {
            d_Map.loadFile(l_FileName[1]);
        } catch (Exception e) {
            return e.getMessage() + "\n You can create a new map.";
        }
        return "Map file loaded successfully!! Now you can edit it";
    }

    /***
     * This is the display method for the game map
     */
    public void showMap() {
        d_Map.showMap();
    }

    public void resetMap() {
        d_Map.reset();
    }
}
