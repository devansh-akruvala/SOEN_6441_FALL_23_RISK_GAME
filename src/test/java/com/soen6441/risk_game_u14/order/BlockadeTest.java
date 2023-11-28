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
 * This class tests the methods written in Blockade order class.
 */
public class BlockadeTest {
    GameModel d_GameModel;
    GameEngine d_Ge;
    Continent d_C0, d_C1;
    Country d_Country1, d_Country2, d_Country3, d_Country4, d_Country5;

    Player d_P1, d_P2;
    Map d_Map;
    Deploy d_Deploy;
    private MapController d_MapController;
    Blockade d_Block;

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
     * This method tests the scenario of player not having a blockade card and still issuing the order of it.
     */
    @Test
    public void testCardCheck() {
        String l_Actual = "", l_Expected = "Player does not have a blockade card"
                + "\nSkipping all the following commands of Devansh";
        d_Block = new Blockade(d_P1, d_Country1);
        d_Block.execute();
        l_Actual = d_Block.getPlayer().getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

    /**
     * This method tests if the country belongs to the same player who issued the blockade order.
     */
    @Test
    public void testCountryCheck() {
        d_P1.setCard("Blockade");
        String l_Actual = "", l_Expected = "Devansh: Blockade was Success!!";
        d_Block = new Blockade(d_P1, d_Country5);
        d_Block.execute();
        l_Actual = d_Block.getPlayer().getD_Result();
        assertEquals(l_Expected, l_Actual);

    }

    /**
     * This method tests that after using the blockade card, it is removed from the player's list and not able to use again.
     */
    @Test
    public void testBlockadeAgain() {
        d_P1.setCard("Blockade");
        String l_Actual = "", l_Expected = "Player does not have a blockade card"
                + "\nSkipping all the following commands of Devansh";
        d_Block = new Blockade(d_P1, d_Country1);
        d_Block.execute();
        d_Block.execute();
        l_Actual = d_Block.getPlayer().getD_Result();

        assertEquals(l_Expected, l_Actual);

    }
}
