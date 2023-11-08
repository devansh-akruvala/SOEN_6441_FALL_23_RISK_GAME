package com.soen6441.risk_game_u14;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.GameModel;

/**
 * This is the starting point of the game. This class calls the game model which
 * creates the map and players list and then calls the game engine object
 *
 * @author Devansh
 */

public class Game {
    /**
     * This is the starting point of the game it creates the object of the GameModel
     * and passes it to GameEngine class and calls the listenCommand() method
     */
    public static void main(String[] args) throws Exception {
        GameModel d_RiskGameModel = new GameModel();
        GameEngine d_RiskGameEngine = new GameEngine(d_RiskGameModel);
        d_RiskGameEngine.listenCommand();
    }

}
