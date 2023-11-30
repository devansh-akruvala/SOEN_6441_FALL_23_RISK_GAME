package com.soen6441.risk_game_u14.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Player;

public class BenevolentPlayerTest {
	/**
	 * Player objects
	 */
	Player d_Player1,d_Player2;
	/**
	 * Map object
	 */
	Map d_Map;
	/**
	 * GamemodelNew Object
	 */
	GameModel d_GameModel;
	/**
	 * Strategy Objects
	 */
	Strategy d_Strategy1,d_Strategy2;
	/**
	 * Continent object
	 */
	Continent d_Continent;
	/**
	 * Country objects
	 */
	Country d_Country1, d_Country2, d_Country3;

	@BeforeEach
	public void setTestContext() throws Exception {
		d_Map = new Map();
		d_GameModel = new GameModel(d_Map);
		d_Player1 = new Player("Devansh", d_GameModel);
		d_Player2 = new Player("Meshva", d_GameModel);
		d_Continent = new Continent("asia",3);
		d_Map.addContinent(d_Continent.getD_ContinentName(), 3);
		d_Map.addCountries("india","asia");
		d_Map.addCountries("china","asia");
		d_Map.addCountries("japan","asia");
		d_Map.addCountryNeighbour("india", "china");
		d_Map.addCountryNeighbour("china", "india");
		d_Map.addCountryNeighbour("japan", "china");
		d_Map.addCountryNeighbour("china", "japan");
		d_Map.addCountryNeighbour("india", "japan");
		d_Country1 = new Country("india","asia");
		d_Country2 = new Country("china","asia");
		d_Country3 = new Country("japan","asia");
		d_Player1.addCountry(d_Country1);
		d_Player1.addCountry(d_Country2);
		d_Player2.addCountry(d_Country3);
		d_Player1.setD_ArmiesCount(3);
		d_Player2.setD_ArmiesCount(3);
		d_Country1.setD_NoOfArmies(2);
		d_Country2.setD_NoOfArmies(3);
		d_Country3.setD_NoOfArmies(4);
		d_Country1.addNeighbours("china");
		d_Country1.addNeighbours("japan");
		d_Country2.addNeighbours("japan");
		d_Country3.addNeighbours("china");
		d_Country3.addNeighbours("india");
		
	}
	@Test
	public void testStrongestCountry() {
		String l_Actual="";
		String l_Expected="india";
		BenevolentPlayerStrategy l_Agress=new BenevolentPlayerStrategy(d_Player1,d_GameModel);
		Country c1=l_Agress.getWeakestCountry(d_Player1);
		l_Actual = c1.getD_CountryName();
		assertEquals(l_Expected,l_Actual);
	}
	@Test
	public void testOrderCreation() {
		d_Player1.setD_PlayerStrategy(new BenevolentPlayerStrategy(d_Player1, d_GameModel));
    	String l_receivedOrder = d_Player1.getD_PlayerStrategy().createDeployOrder();
    	
		assertEquals(l_receivedOrder.split(" ")[0], "deploy");
	}

	
	
}
