package com.soen6441.risk_game_u14.state;

import java.util.Scanner;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
/**
 *The GameOver Phase extends the phase class and implements all the methods suitable for that particular phase.
 *It returns invalid command for others which are not compatible with this phase
 */
public class Gameover extends Phase {

    LogEntryBuffer d_LEB;
    /**
     * This is the constructor of GameOver which initializes Game engine object and command prompt object and assigning log entry buffer
     * It then ends the game by giving relevant acknowledgement.
     * @param p_Ge object of game engine
     */
    public Gameover(GameEngine p_Ge) {
        super(p_Ge);
        try {
            d_LEB = new LogEntryBuffer();
            d_LEB.setResult("In GameOver Phase");
            System.out.println("Game Over!!");
        } catch (Exception p_E) {
            d_LEB.setResult(p_E.getMessage());
        }
    }
    /**
     *{@inheritDoc}
     *
     */
    @Override
    public String editContinent(String p_command) {
        // TODO Auto-generated method stub
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        String result = "Invalid Command in GameOver Phase";
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
        return "GameOver Phase";
    }

}
