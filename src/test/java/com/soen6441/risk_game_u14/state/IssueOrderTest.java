package com.soen6441.risk_game_u14.state;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.GameModel;

public class IssueOrderTest {
    GameModel d_GameModel;
    GameEngine d_Ge;
    IssueOrder d_Io;
    Phase d_P;

    /**
     * This method sets the context before each method is executed.
     *
     * @throws Exception any exception that is thrown while setting up the context.
     */
    @BeforeEach
    public void setTestContext() throws Exception {

        d_GameModel = new GameModel();
        d_Ge = new GameEngine(d_GameModel);
        d_Io = new IssueOrder(d_Ge);
    }

    /**
     * Tests that editNeighbor method is invalid for issue order phase.
     */
    @Test
    public void testEditNeighbor() {
        String l_ExpectedMessage = "Invalid Command In Issue Order Phase";
        String l_ActualMessage = "";
        d_Io.editNeighbor("editneighbor -add india asia");
        l_ActualMessage = d_Io.d_LEB.getResult();
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * Tests that addplayer method is invalid for issue order phase.
     */
    @Test
    public void testAddPlayers() {
        String l_ExpectedMessage = "Invalid Command In Issue Order Phase";
        String l_ActualMessage = "";
        d_Io.addPlayers("gameplayer -add p1 -add p2");
        l_ActualMessage = d_Io.d_LEB.getResult();
        assertEquals(l_ExpectedMessage, l_ActualMessage);

    }

    /**
     * Tests that assigncountries method is invalid for issue order phase.
     */
    @Test
    public void testAssignCountries() {
        String l_ExpectedMessage = "Invalid Command In Issue Order Phase";
        String l_ActualMessage = "";
        d_Io.assignCountries();
        l_ActualMessage = d_Io.d_LEB.getResult();
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    //	/**
//	 * Tests that validatemap method is invalid for issue order phase.
//	 */
    @Test
    public void testValidateMap() {
        String l_ExpectedMessage = "Invalid Command In Issue Order Phase";
        String l_ActualMessage = "";
        d_Io.validateMap();

        l_ActualMessage = d_Io.d_LEB.getResult();
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

}
