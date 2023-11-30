/**
 * The SaveGame class represents the state of the game during the "Save Game" phase.
 * It extends the Phase class and implements the Serializable interface.
 * This class is responsible for handling operations related to saving the game state.
 * It includes methods for editing continents, countries, neighbors, loading and saving maps,
 * validating maps, editing maps, adding players, assigning countries, showing the map, etc.
 * Additionally, it contains a LogEntryBuffer to store log entries related to the save game phase.
 *
 * @author Smridhi 
 * @version 1.0
 * @see Phase
 * @see Serializable
 */

package com.soen6441.risk_game_u14.state;

import java.io.Serializable;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class SaveGame extends Phase implements Serializable {
	/**
	 * The LogEntryBuffer to store log entries for the SaveGame phase.
	 */
	LogEntryBuffer d_LEB;

	/**
	 * Constructor for the SaveGame class.
	 * Initializes the GameEngine object, the LogEntryBuffer, and sets the result in
	 * the log.
	 *
	 * @param p_Ge The GameEngine object.
	 */
	public SaveGame(GameEngine p_Ge) {
		super(p_Ge);
		d_LEB = new LogEntryBuffer();
		d_LEB.setResult("In Saved game Phase");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String editContinent(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String editCountry(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String editNeighbor(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loadMap(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveMap(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String validateMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String editMap(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addPlayers(String p_command) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showMap() {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPhaseName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String tournament(String p_String, String p_CommandStringFromInput) {
		// TODO Auto-generated method stub
		return null;
	}

}
