package com.soen6441.risk_game_u14.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Map;

public class MapControllerTest {
	
	MapController d_MapController=null;
	Map d_Map = null;
	
	@BeforeEach
	public void initTest() {
		d_Map = new Map();
		d_MapController = new MapController(d_Map); 
	}
	@Test
	public void checkContinentCommand() {
		String command = "editcontinent -add A 1";
		String ans="Continents added succcessfully";
		
		try {
			assertEquals(ans, d_MapController.addContinentCommand(command));
		} catch (Exception e) {
			assertTrue(false);
			e.printStackTrace();
		}	
	}
	
	
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
	
	@Test
	public void checkCountry() {
		try {
			d_Map.addContinent("A", 10);
		} catch (Exception e) {

		}
		String command = "editcountry -add Country1 A";
		String ans="Countries added successfully";
		
		try {
			assertEquals(ans, d_MapController.addCountryCommand(command));
		} catch (Exception e) {

			assertTrue(false);
		}
	}
	
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
