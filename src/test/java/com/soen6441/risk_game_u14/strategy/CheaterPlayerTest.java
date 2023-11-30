package com.soen6441.risk_game_u14.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Player;
/**
 * Test class for the CheaterPlayerStrategy class.
 * Uses JUnit 5 annotations for defining tests.
 */
public class CheaterPlayerTest {
	/**
	 * Player object
	 */
	Player d_Player1,d_Player2;
	/**
	 * Map objects
	 */
	Map d_Map;
	/**
	 * GameModel object
	 */
	GameModel d_GameModel;
	/**
	 * Strategy Object
	 */
	Strategy d_Strategy1,d_Strategy2;
	/**
	 * Continent Object
	 */
	Continent d_Continent;
	/**
	 * Country Objects
	 */
	Country d_Country1, d_Country2, d_Country3,d_Country4;
	/**
	 * Sets up the context for testing.
	 *
	 * @throws Exception Throws an exception if setup fails.
	 */
	@BeforeEach
	public void setTestContext() throws Exception {
		d_Map = new Map();
		d_GameModel = new GameModel(d_Map);
		d_Player1 = new Player("Devansh", d_GameModel);
		d_Player2 = new Player("Meshva", d_GameModel);
		d_Continent = new Continent("asia",3);
		d_Map.addContinent("asia", 3);
		d_Map.addCountries("india","asia");
		d_Map.addCountries("china","asia");
		d_Map.addCountries("pak","asia");
		d_Map.addCountries("nepal","asia");
		d_Map.addCountryNeighbour("india", "china");
		d_Map.addCountryNeighbour("china", "india");
		d_Map.addCountryNeighbour("india", "nepal");
		d_Map.addCountryNeighbour("nepal", "india");
		d_Map.addCountryNeighbour("india", "pak");
		d_Map.addCountryNeighbour("pak", "india");
		
		
		d_Country1 = new Country("india","asia");
		d_Country2 = new Country("china","asia");
		d_Country3 = new Country("pak","asia");
		d_Country4 = new Country("nepal","asia");
		
		d_Player1.addCountry(d_Country1);
		d_Player1.addCountry(d_Country3);
		d_Player2.addCountry(d_Country2);
		d_Player2.addCountry(d_Country4);
		d_Player1.setD_ArmiesCount(3);
		d_Player2.setD_ArmiesCount(3);
		d_Country1.setD_NoOfArmies(2);
		d_Country2.setD_NoOfArmies(3);
		d_Country1.addNeighbours("china");
		d_Country2.addNeighbours("india");
		d_Country1.addNeighbours("nepal");
		d_Country4.addNeighbours("india");
		d_Country3.addNeighbours("india");
		d_Country1.addNeighbours("india");
				
	}
	/**
	 * Tests if the CheaterPlayerStrategy class creates a null order when attempting to create an advance order.
	 *
	 */
    @Test
    public void testOrderCreationToBeNull(){
    	d_Player1.setD_PlayerStrategy(new CheaterPlayerStrategy(d_Player1, d_GameModel));
    	String l_receivedOrder = d_Player1.getD_PlayerStrategy().createAdvanceOrder();
       
    	assertNull(l_receivedOrder);
    }

	/**
	 * Tests if the CheaterPlayerStrategy class creates a null deploy order.
	 */
    @Test
    public void testOrderDeployCreationToBeNull(){
    	d_Player1.setD_PlayerStrategy(new CheaterPlayerStrategy(d_Player1, d_GameModel));
    	String l_receivedOrder = d_Player1.getD_PlayerStrategy().createDeployOrder();
       
    	assertNull(l_receivedOrder);
    }

	/**
	 * Tests if the CheaterPlayerStrategy class creates a null card order.
	 */
    @Test
    public void testOrderCardCreationToBeNull(){
    	d_Player1.setD_PlayerStrategy(new CheaterPlayerStrategy(d_Player1, d_GameModel));
    	String l_receivedOrder = d_Player1.getD_PlayerStrategy().createCardOrder("Card");
       
    	assertNull(l_receivedOrder);
    }

}
