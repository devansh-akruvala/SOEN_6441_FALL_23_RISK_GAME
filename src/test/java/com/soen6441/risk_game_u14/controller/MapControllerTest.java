package com.soen6441.risk_game_u14.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Map;

public class MapControllerTest {
	
	
		Map d_Map = new Map();
//		Continent d_ContinentObj1 = new Continent("C1", 10);
//		Continent d_ContinentObj2 = new Continent("C2", 5);
//		
//		Country c1 = new Country("a1", "c1");
//		Country c2 = new Country("a2", "c1");
//		Country c3 = new Country("a3", "c2");
//		Country c4 = new Country("a4", "c2");
		
		
		MapController d_MapController = new MapController(d_Map);  
		
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
	public void checkContinentCommand1() {
		String command = "editcontinent -add A a";
		String ans="Enter Integer value";
		
		try {
			System.out.println(d_MapController.addContinentCommand(command));
			assertEquals(ans, d_MapController.addContinentCommand(command));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
