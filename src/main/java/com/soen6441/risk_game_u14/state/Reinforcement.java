package com.soen6441.risk_game_u14.state;

import java.io.Serializable;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
/**
 *The Reinforcement Phase extends the phase class and implements all the methods suitable for that particular phase.
 *It returns invalid command for others which are not compatible with this phase
 */
public class Reinforcement extends Phase implements Serializable{

    LogEntryBuffer d_LEB;
    /**
     * This is the constructor of Reinforcement class which initializes Game engine object and command prompt object and assigning log entry buffer
     * It then calls the assign reinforcement armies method and then displays all the assigned armies and territories
     * @param p_Ge object of game engine
     */
    public Reinforcement(GameEngine p_Ge) {
        super(p_Ge);
        d_LEB = new LogEntryBuffer();
        d_LEB.setResult("In Reinforcement Phase");
        try {
            d_GameEngine.getD_GameModel().assignReinforcementArmies();
            d_GameEngine.getD_GameModel().showplayer();
            d_GameEngine.setD_GamePhase(new IssueOrder(d_GameEngine));
        } catch (Exception e) {
            d_LEB.setResult(e.getMessage().toString());
        }
    }

    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editContinent(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editCountry(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editNeighbor(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String loadMap(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String saveMap(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String validateMap() {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editMap(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String addPlayers(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String assignCountries() {
        // TODO Auto-generated method stub
        String result = "Invalid Command In Reinforcement Phase";
        d_LEB.setResult(result);
        return result;
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public void showMap() {
        // TODO Auto-generated method stub

    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String getPhaseName() {
        // TODO Auto-generated method stub
        return "Reinforcement Phase";
    }

	@Override
	public String tournament(String p_String, String p_CommandStringFromInput) {
		// TODO Auto-generated method stub
		return "Invalid Command in Reinforcement Phase";
	}
}
