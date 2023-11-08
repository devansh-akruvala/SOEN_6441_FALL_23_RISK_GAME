package com.soen6441.risk_game_u14.state;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class Startup extends Phase {

    LogEntryBuffer d_LEB;

    public Startup(GameEngine p_Ge) {
        super(p_Ge);
        d_LEB = new LogEntryBuffer();
        d_LEB.setResult("In Startup Phase");
    }

    @Override
    public String editContinent(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String editCountry(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String editNeighbor(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String loadMap(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String saveMap(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String validateMap() {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String editMap(String p_command) {
        String result = "Invalid Command in Startup phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String addPlayers(String p_command) {
        String result = d_GameEngine.getD_PlayerController().gamePlayerCommand(p_command);
        d_LEB.setResult(result);
        return result;
    }

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

    @Override
    public void showMap() {
        d_GameEngine.getD_MapController().showMap();
    }

    @Override
    public String getPhaseName() {
        return "Startup Phase";
    }

}
