package com.soen6441.risk_game_u14.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Map;

/**
 * This Class test the map controller
 * */
public class MapControllerTest {
	
	MapController d_MapController=null;
	Map d_Map = null;
	
	@BeforeEach
	public void initTest() {
		d_Map = new Map();
		d_MapController = new MapController(d_Map); 
	}
	
	/**
	 * This method checks that a continent with correct command format works properly or not
	 * 
	 * */
	@Test
	public void checkContinentCommand() {
		String command = "editcontinent -add A 1";
		String ans="Continents command executed successfully";
		
		try {
			assertEquals(ans, d_MapController.addContinentCommand(command));
		} catch (Exception e) {
			assertTrue(false);
			e.printStackTrace();
		}	
	}
	
	/**
	 * This method checks that a continent with incorrect command format (non integer value as continent value) works properly or not
	 * */
	
	@Test
	public void checkContinentCommandInteger() {
		String command = "editcontinent -add A a";
		String ans="Enter integer for Continent value";
		
		try {
			assertEquals(ans, d_MapController.addContinentCommand(command));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	/**
	 * This method checks that a country with correct command format works properly or not
	 * */
	@Test
	public void checkCountry() {
		try {
			d_Map.addContinent("A", 10);
		} catch (Exception e) {

		}
		String command = "editcountry -add Country1 A";
		String ans="Countries command executed successfully";
		
		try {
			assertEquals(ans, d_MapController.addCountryCommand(command));
		} catch (Exception e) {

			assertTrue(false);
		}
	}
	
	/**
	 * This method checks that a country with incorrect command format(no continent name) works properly or not
	 * */
	@Test
	public void checkCountryInvalid() {
		String command = "editcountry -add Country1 ";
		String ans="Invalid Command";
		
		try {
			assertEquals(ans, d_MapController.addCountryCommand(command));
		} catch (Exception e) {
			assertTrue(false);
		}
	}


	
	@Test
	public void checkRemoveCountry() {
		try {
			// Add a continent
			d_Map.addContinent("A", 10);
		} catch (Exception e) {
			assertTrue(false);
		}

		// Add a country
		String addCommand = "editcountry -add Country1 A";
		String expectedAddResult = "Countries command execueted successfully";

		try {
			// Add the country
			assertEquals(expectedAddResult, d_MapController.addCountryCommand(addCommand));

			// Remove the country
			String removeCommand = "editcountry -remove Country1";
			String expectedRemoveResult = "Countries removed successfully";

			assertEquals(expectedRemoveResult, d_MapController.removeCountryCommand(removeCommand));
		} catch (Exception e) {
			assertTrue(false);
		}
	}


	@Test
	public void checkAddNeighbor() {
		try {
			// Add a continent
			d_Map.addContinent("A", 10);

			// Add two countries
			String addCountry1Command = "editcountry -add Country1 A";
			String addCountry2Command = "editcountry -add Country2 A";

			assertEquals("Countries command execueted successfully",
					d_MapController.addCountryCommand(addCountry1Command));
			assertEquals("Countries command execueted successfully",
					d_MapController.addCountryCommand(addCountry2Command));
		} catch (Exception e) {
			assertTrue(false);
		}

		// Add a neighbor
		String addNeighborCommand = "editneighbor -add Country1 Country2";
		String expectedAddResult = "Neighbors command executed succcessfully";

		try {
			// Add the neighbor
			assertEquals(expectedAddResult, d_MapController.addNeighborsCommand(addNeighborCommand));

			// Checking if Country1 has Country2 as a neighbor
			assertTrue(d_Map.findCountryByName("Country1").getD_Neighbors().contains("Country2"));

			
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}


	}

	
