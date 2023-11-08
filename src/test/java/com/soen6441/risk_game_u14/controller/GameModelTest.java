package com.soen6441.risk_game_u14.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 * This Class tests the methods of GameModel class
 *
 * @author Meshva
 */
public class GameModelTest {
    GameModel d_GameModel;
    ArrayList<Player> d_Check;
    List<String> d_Names;
    ArrayList<Player> d_List;
    List<String> d_CheckNames;
    Player d_C1, d_C2;
    private Map d_Map;

    /**
     * To set up the context for test cases
     *
     * @throws Exception relevant for the map creation phase
     */
    @BeforeEach
    public void initTestContext() throws Exception {

        d_Check = new ArrayList<Player>();
        d_Names = new ArrayList<>();
        d_CheckNames = new ArrayList<>();
        d_Map = new Map();
        d_Map.addContinent("asia", 4);
        d_Map.addCountries("India", "asia");
        d_Map.addCountries("Singapore", "asia");
        d_Map.addCountries("Japan", "asia");
        d_GameModel = new GameModel(d_Map);
        d_GameModel.addPlayers("Devansh");
        d_GameModel.addPlayers("Meshva");
        d_C1 = new Player("Devansh", d_GameModel);
        d_C2 = new Player("Meshva", d_GameModel);
        d_Check.add(d_C1);
        d_Check.add(d_C2);
    }

    /**
     * This test case checks the functionality of adding method
     */
    @Test
    public void testAddPlayer() {
        for (Player l_Player : d_Check) {
            d_Names.add(l_Player.getD_PlayerName());
        }
        for (Player l_Player : d_GameModel.getD_Players()) {
            d_CheckNames.add(l_Player.getD_PlayerName());
        }
        assertEquals(d_CheckNames, d_Names);
    }

    /**
     * To test if player already exists or not
     */
    @Test
    public void testAddPlayerAlreadyExist() {
        String l_ExpectedMessage = "Please enter a different player name as this name already exists";
        String l_ActualMessage = "";
        try {
            d_GameModel.addPlayers("Devansh");
        } catch (Exception p_E) {
            l_ActualMessage = p_E.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * To test if maximum players have been reached or not
     */

    @Test
    public void testAddPlayerReachedMax() {
        String l_ExpectedMessage = "Reached max number of players can be added to the game";
        String l_ActualMessage = "";
        try {
            d_GameModel.addPlayers("Smridhi");
            d_GameModel.addPlayers("Gowtham");
            d_GameModel.addPlayers("karan");
            d_GameModel.addPlayers("Aditya");
        } catch (Exception p_E) {
            l_ActualMessage = p_E.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * This test case checks the functionality of RemovePlayer method
     *
     * @throws Exception Player to be removed does not exist
     */
    @Test
    public void testRemovePlayer() throws Exception {
        d_GameModel.removePlayers("Meshva");
        for (Player l_Player : d_Check) {
            d_Names.add(l_Player.getD_PlayerName());
        }
        for (Player l_Player : d_GameModel.getD_Players()) {
            d_CheckNames.add(l_Player.getD_PlayerName());
        }
        assertFalse(d_CheckNames.equals(d_Names));
    }

    /**
     * To test player to be removed exist or not
     */
    @Test
    public void testRemovePlayerNotExists() {
        String l_ExpectedMessage = "Player Doesnt Exist!!";
        String l_ActualMessage = "";
        try {
            d_GameModel.removePlayers("Smridhi");
        } catch (Exception p_E) {
            l_ActualMessage = p_E.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);

    }

    /**
     * This checks if player is deploying more armies than he has in its
     * reinforcement pool
     */
    // @Test
    // public void testIssueOrderArmySize() {
    //
    // String l_Command3 = "deploy India 4";
    // String l_Expected3 = "Player doesn't have enough armies!!";
    // d_C1.setD_ArmiesCount(3);
    // d_C1.getD_PlayerOwnedCountries().add(d_Map.getD_CountryObjects().get(0));
    // d_C1.setD_CurrentCommand(new Order(l_Command3, this.d_GameModel.getD_Map()));
    // d_C1.issueOrder();
    // String l_Result3 = d_C1.getD_Result();
    //
    // assertEquals(l_Expected3, l_Result3);
    // }
    @Test
    public void testAssignReinforcements() throws Exception {
        d_GameModel.startUpPhase();
        for (Player l_Player : d_GameModel.getD_Players()) {
            int l_Value = l_Player.getD_ArmiesCount();
            assertFalse(3 < l_Value);

        }
    }
}
