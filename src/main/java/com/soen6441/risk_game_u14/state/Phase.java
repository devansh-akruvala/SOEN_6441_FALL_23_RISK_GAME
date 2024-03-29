package com.soen6441.risk_game_u14.state;
import java.io.Serializable;

import com.soen6441.risk_game_u14.controller.GameEngine;


/**
 * The phase class is abstract class whose duty is to implement all the command in game
 * All the commands in game will be implementing by relevant phases by using methods of phase class
 */

public abstract class Phase implements Serializable{
    public GameEngine d_GameEngine;

    public Phase() {
    }

    /**
     * This is the constructor for Phase class. It initializes Game engine and command prompt object
     *
     * @param p_GameEngine Object of Game Engine
     *
     */
    public Phase(GameEngine p_GameEngine) {
        this.d_GameEngine = p_GameEngine;
    }

    /**
     * This method deals with edit continent command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */
    abstract public String editContinent(String p_command);

    /**
     * This method deals with edit country command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */
    abstract public String editCountry(String p_command);

    /**
     * This method deals with edit neighbor command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */
    abstract public String editNeighbor(String p_command);

    /**
     * This method deals with loadmap command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */
    abstract public String loadMap(String p_command);

    /**
     * This method deals with savemap command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */
    abstract public String saveMap(String p_command);

    /**
     * This method deals with validatemap command and returns results
     *
     * @return String which gives the proper acknowledgement to player
     */

    abstract public String validateMap();

    /**
     * This method deals with edit command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */

    abstract public String editMap(String p_command);

    /**
     * This method deals with gameplayer command and returns results
     *
     *
     * @param p_command Command that has been entered by the player
     * @return String which gives the proper acknowledgement to player
     */

    abstract public String addPlayers(String p_command);

    /**
     * This method in phase calls the assign countries method of the game engine .
     * It will assign countries to all the players.
     */
    abstract public String assignCountries();

    /**
     * This method deals with showMap command and returns results
     */
    abstract public void showMap();

    /**
     * This method returns the current phase names
     * @return String which gives the proper acknowledgement to player
     */
    abstract public String getPhaseName();
	/**
	 * It is the tournament method of the game engine to play the game in tournament mode.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_String string input
	 * @param p_CommandStringFromInput input entered by the user. 
	 * @return Acknowledgement
	 */
	abstract public String tournament(String p_String, String p_CommandStringFromInput);
}
