package com.soen6441.risk_game_u14.state;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;

public class Edit extends Phase {

    LogEntryBuffer d_LEB;

    public Edit(GameEngine p_Ge) {
        super(p_Ge);
        d_LEB = new LogEntryBuffer();
        d_LEB.setResult("In Edit Phase");
    }

    @Override
    public String editContinent(String p_command) {
        String result = d_GameEngine.getD_MapController().editContinentCommand(p_command);
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String editCountry(String p_command) {
        String result = d_GameEngine.getD_MapController().editCountryCommand(p_command);
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String editNeighbor(String p_command) {
        String result = d_GameEngine.getD_MapController().editNeighborsCommand(p_command);
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String loadMap(String p_command) {
        String l_response = "";

        l_response = d_GameEngine.getD_MapController().loadMap(p_command);
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

    @Override
    public String saveMap(String p_command) {
        String l_response = "";
        String l_ValidResult = d_GameEngine.getD_MapController().validateMap();
        System.out.println(l_ValidResult);
        if (l_ValidResult.equalsIgnoreCase(" Map is not valid!!")) {
            l_response = "Map cannot be saved!!";
        } else {
            l_response = d_GameEngine.getD_MapController().saveMap(p_command);
        }
        d_LEB.setResult(l_response);
        return l_response;
    }

    @Override
    public String validateMap() {
        String result = d_GameEngine.getD_MapController().validateMap();
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String editMap(String p_command) {
        return d_GameEngine.getD_MapController().editMap(p_command);
    }

    @Override
    public String addPlayers(String p_command) {
        String result = "Invalid Command in Edit phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public String assignCountries() {
        String result = "Invalid Command in Edit phase";
        d_LEB.setResult(result);
        return result;
    }

    @Override
    public void showMap() {
        d_GameEngine.getD_MapController().showMap();

    }

    @Override
    public String getPhaseName() {
        // TODO Auto-generated method stub
        return "Edit Phase";
    }

}
