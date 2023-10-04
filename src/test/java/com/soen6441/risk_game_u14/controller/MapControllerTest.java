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
	
}
