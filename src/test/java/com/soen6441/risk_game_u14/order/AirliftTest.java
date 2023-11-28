package com.soen6441.risk_game_u14.order;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.controller.MapController;
import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Player;
/**
 * This class tests the methods written in Airlift order class.
 */
public class AirliftTest {
    GameModel d_GameModel;
    GameEngine d_Ge;
    Continent d_C0, d_C1;
    Country d_Country1, d_Country2, d_Country3, d_Country4, d_Country5;

    Player d_P1, d_P2;
    Map d_Map;
    Deploy d_Deploy;
    private MapController d_MapController;
    Airlift d_Air;

    /**
     * This method sets the context before each method is executed.
     *
     * @throws Exception any exception that is thrown while setting up the context.
     */
    @BeforeEach
    public void setTestContext() throws Exception {
        d_C0 = new Continent("asia", 0);
        d_C1 = new Continent("africa", 0);

        d_Country1 = new Country("india", "asia");
        d_Country2 = new Country("china", "asia");
        d_Country3 = new Country("japan", "asia");
        d_Country4 = new Country("kenya", "africa");
        d_Country5 = new Country("egypt", "africa");

        d_Map = new Map();
        d_Map.addContinent(d_C0.getD_ContinentName(), 1);
        d_Map.addContinent(d_C1.getD_ContinentName(), 1);

        d_Map.addCountries("india", "asia");
        d_Map.addCountries("china", "asia");
        d_Map.addCountries("japan", "asia");
        d_Map.addCountries("kenya", "africa");
        d_Map.addCountries("egypt", "africa");

        d_Map.addCountryNeighbour("egypt", "kenya");
        d_Map.addCountryNeighbour("kenya", "japan");
        d_Map.addCountryNeighbour("japan", "china");
        d_Map.addCountryNeighbour("china", "india");
        d_Map.addCountryNeighbour("india", "kenya");
        d_Map.addCountryNeighbour("kenya", "egypt");
        d_Map.addCountryNeighbour("india", "japan");
        d_Map.addCountryNeighbour("kenya", "india");
        d_Map.addCountryNeighbour("japan", "india");


        d_MapController = new MapController(d_Map);


        d_GameModel = new GameModel(d_Map);
        d_Ge = new GameEngine(d_GameModel);
        d_GameModel.addPlayers("Devansh","human");
        d_GameModel.addPlayers("Meshva","human");
        d_P1 = new Player("Devansh", d_GameModel);
        d_P2 = new Player("Meshva", d_GameModel);

        d_P1.getD_PlayerOwnedCountries().add(d_Country1);
        d_P2.getD_PlayerOwnedCountries().add(d_Country4);
        d_P1.getD_PlayerOwnedCountries().add(d_Country3);

        d_P2.getD_PlayerOwnedCountries().add(d_Country2);
        d_P2.getD_PlayerOwnedCountries().add(d_Country5);

        d_Country1.setD_Owner(d_P1);
        d_Country2.setD_Owner(d_P2);
        d_Country3.setD_Owner(d_P1);

        d_Country4.setD_Owner(d_P2);
        d_Country5.setD_Owner(d_P2);


        d_P1.setD_ArmiesCount(3);
        d_P2.setD_ArmiesCount(3);
        d_Country1.setD_NoOfArmies(3);
        d_Country5.setD_NoOfArmies(3);

    }


    /**
     * This method tests if the source and target countries are not same
     */
    @Test
    public void testSourceTargetTerritory() {
        d_P1.setCard("Airlift");
        String l_Actual = "", l_Expected = "The source country and target country cannot be same!"
                + "\nSkipping all the following commands of Devansh";
        d_Air = new Airlift(d_P1, d_Country1, d_Country1, 2);
        d_Air.execute();
        l_Actual = d_Air.d_Player.getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

    /**
     * This method tests if after airlift source country is left with atleast one army.
     */
    @Test
    public void testPlayerArmies() {
        d_P1.setCard("Airlift");
        String l_Actual = "", l_Expected = "The source country should be left with at least one army!"
                + "\nSkipping all the following commands of Devansh";
        d_Air = new Airlift(d_P1, d_Country1, d_Country3, 3);
        d_Air.execute();
        l_Actual = d_Air.d_Player.getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

    /**
     * This method tests if the source and target countries belong to the same player.
     */
    @Test
    public void testSourceTargetsame() {
        d_P1.setCard("Airlift");
        String l_Actual = "", l_Expected = "The source country and target country belong to the same player";
        d_Air = new Airlift(d_P1, d_Country1, d_Country3, 2);
        d_Air.execute();
        l_Actual = d_Air.d_Player.getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

    /**
     * This method tests if the airlift is happening between the countries of the same owner.
     */
    @Test
    public void testOtherPlayerCountry() {
        d_P1.setCard("Airlift");
        String l_Actual = "";
        String l_Expected = "You can only airlift armies to your own countries. egypt does not belongs to Devansh"
                + "\nSkipping all the following commands of Devansh";
        d_Air = new Airlift(d_P1, d_Country1, d_Country5, 2);
        d_Air.execute();
        l_Actual = d_Air.d_Player.getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

    /**
     * This method tests if the player doesn't have airlift card after he has already used it.
     */
    @Test
    public void testAirliftAgain() {
        d_P1.setCard("Airlift");
        String l_Actual = "", l_Expected = "Player does not have a Airlift card"
                + "\nSkipping all the following commands of Devansh";
        d_Air = new Airlift(d_P1, d_Country1, d_Country3, 2);
        d_Air.execute();
        d_Air.execute();
        l_Actual = d_Air.d_Player.getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

}
