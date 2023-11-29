package com.soen6441.risk_game_u14.state;

import java.io.Serializable;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
/**
 *The Startup Phase extends the phase class and implements all the methods suitable for that particular phase.
 *It returns invalid command for others which are not compatible with this phase
 */
public class Startup extends Phase implements Serializable {

    LogEntryBuffer d_LEB;
    /**
     * This is the constructor of Startup which initializes Game engine object and command prompt object and assigning log entry buffer
     * @param p_Ge object of game engine
     */
    public Startup(GameEngine p_Ge) {
        super(p_Ge);
        d_LEB = new LogEntryBuffer();
        d_LEB.setResult("In Startup Phase");
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editContinent(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editCountry(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editNeighbor(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String loadMap(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String saveMap(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String validateMap() {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editMap(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String addPlayers(String p_command) {
        String result = d_GameEngine.getD_PlayerController().gamePlayerCommand(p_command);
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String assignCountries() {
        try {
            d_GameEngine.getD_GameModel().startUpPhase();
        } catch (Exception e) {
            return e.getMessage();
        }
        d_GameEngine.setD_GamePhase(new Reinforcement(d_GameEngine));
        String result = "";
        return result;

    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public void showMap() {
        d_GameEngine.getD_MapController().showMap();
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String getPhaseName() {
        return "Startup Phase";
    }
	@Override
	public String tournament(String p_String, String p_CommandStringFromInput) {
		// TODO Auto-generated method stub
		return "Invalid Command in Startup Phase";
	}

}
