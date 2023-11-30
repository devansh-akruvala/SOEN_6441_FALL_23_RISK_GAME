package com.soen6441.risk_game_u14.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Player;

public class RandomPlayerTest {
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
	 * Checks if it creates an Order String and first order is deploy.
	 *
	 * @throws IOException Exception
	 */
	@Test
	public void testOrderCreation() {
		d_Player1.setD_PlayerStrategy(new RandomPlayerStrategy(d_Player1, d_GameModel));
    	String l_receivedOrder = d_Player1.getD_PlayerStrategy().createDeployOrder();
    	
		assertEquals(l_receivedOrder.split(" ")[0], "deploy");
	}
}
