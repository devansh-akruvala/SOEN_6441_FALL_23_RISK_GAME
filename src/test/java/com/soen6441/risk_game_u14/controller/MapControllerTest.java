package com.soen6441.risk_game_u14.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Map;

/**
 * This Class test the map controller
 */
public class MapControllerTest {

    private Continent d_C0, d_C1;
    private Country d_Country1, d_Country2, d_Country3, d_Country4, d_Country5;
    private ArrayList<Country> d_Check;
    private ArrayList<Continent> d_CheckContinent;
    private Map d_Map;
    private MapController d_MapController;

    @BeforeEach
    public void initMapcontrollerTest() throws Exception {
        d_C0 = new Continent("Asia", 3);
        d_C1 = new Continent("Europe", 5);
        d_Country1 = new Country("India", "Asia");
        d_Country2 = new Country("Singapore", "Asia");
        d_Country3 = new Country("Japan", "Asia");
        d_Country4 = new Country("France", "Europe");
        d_Country5 = new Country("Spain", "Europe");
        d_Check = new ArrayList<Country>();
        d_CheckContinent = new ArrayList<Continent>();
        d_Map = new Map();
        d_MapController = new MapController(d_Map);
        d_CheckContinent.add(d_C0);
        d_CheckContinent.add(d_C1);
        d_Check.add(d_Country1);
        d_Check.add(d_Country2);
        d_Check.add(d_Country3);
        d_Check.add(d_Country4);
        d_Check.add(d_Country5);
        d_Map.addContinent(d_C0.getD_ContinentName(), d_C0.getD_ContinentValue());
        d_Map.addContinent(d_C1.getD_ContinentName(), d_C1.getD_ContinentValue());
        d_Map.addCountries("India", "Asia");
        d_Map.addCountries("Singapore", "Asia");
        d_Map.addCountries("Japan", "Asia");
        d_Map.addCountries("France", "Europe");
        d_Map.addCountries("Spain", "Europe");
        d_Map.addCountryNeighbour("France", "Spain");
        d_Map.addCountryNeighbour("France", "Japan");
        d_Map.addCountryNeighbour("Japan", "Singapore");
        d_Map.addCountryNeighbour("Singapore", "India");

    }

    /**
     * This test checks the functionality of addCountry() to see if it adds a
     * country to the continent that does not exists
     */
    @Test
    public void testAddCountryContinentNotExists() {
        String l_ExpectedMessage = "Continent Doesnt Exist";
        String l_ActualMessage = "";
        try {
            d_Map.addCountries("brazil", "SA");
        } catch (Exception p_Exception) {
            l_ActualMessage = p_Exception.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * Checks if country already exists then it cannot be added
     */
    @Test
    public void testAddCountryCountryExists() {
        String l_ExpectedMessage = "Country Already Exist!!";
        String l_ActualMessage = "";
        try {
            d_Map.addCountries("India", "Asia");
        } catch (Exception p_Exception) {
            l_ActualMessage = p_Exception.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * Checks that if country exist it can be removed
     *
     * @throws Exception If country does not exists
     */
    @Test
    public void testRemoveCountry() throws Exception {
        d_Map.removeCountry("India");
        assertFalse(d_Map.getD_CountryObjects().contains(d_Country5));
    }

    /**
     * This test checks the functionality of removeCountry() to see if the exception
     * is thrown for country does not exists
     */
    @Test
    public void testRemoveCountryThatDoesNotExists() {
        String l_ExpectedMessage = "Country Doesnt exist!!";
        String l_ActualMessage = "";
        try {
            d_Map.removeCountry("Pakistan");
        } catch (Exception p_Exception) {
            l_ActualMessage = p_Exception.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * checks that if neighbor doesnt exist then connection between countries cant
     * be made
     */
    @Test
    public void testAddBorderNeighborDoesNotExist() {
        String l_ExpectedMessage = "Invalid Command Check country names";
        String l_ActualMessage = "";
        try {
            d_Map.addCountryNeighbour("India", "Pakistan");
        } catch (Exception p_Exception) {
            l_ActualMessage = p_Exception.getMessage();
        }

        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * Checks that if continent already exists then new continent with same name
     * cant be added..
     */
    @Test
    public void testAddContinentContinentExists() {
        String l_ExpectedMessage = "Continent Already Exist!!";
        String l_ActualMessage = "";
        try {
            d_Map.addContinent("Asia", 1);
        } catch (Exception p_Exception) {
            l_ActualMessage = p_Exception.getMessage();
        }
        assertEquals(l_ExpectedMessage, l_ActualMessage);
    }

    /**
     * To test the Validation of Map and Check whether it is a connected graph or
     * not
     *
     * @throws Exception if map is not connected
     */
    @Test
    public void testValidateMap() throws Exception {
        String l_Actual = "", l_Expected = "Map is Valid";
        d_Map.addCountryNeighbour("Spain", "France");
        d_Map.addCountryNeighbour("Japan", "France");
        d_Map.addCountryNeighbour("Singapore", "Japan");
        d_Map.addCountryNeighbour("India", "Singapore");
        l_Actual = d_MapController.validateMap();
        assertEquals(l_Expected, l_Actual);
    }

    /**
     * Checks that if two continent is not connected than map is invalid (Here
     * continent is a connnected subgraph but the two continents are not)
     *
     * @throws Exception if map is not valid
     */
//	@Test
//	public void testValidateMapFalse() throws Exception {
//		String l_Actual = "", l_Expected = " Map is not valid!!";
//		d_Map.addCountryNeighbour("Spain", "France");
//		d_Map.addCountryNeighbour("India", "Japan");
//		l_Actual = d_MapController.validateMap();
//		assertEquals(l_Expected, l_Actual);
//	}

    /**
     * Checks that if continent is not a sub graph
     *
     * @throws Exception if continent is not a subgraph
     */
    @Test
    public void testValidateMapForContinents() throws Exception {
        boolean l_Result = true; // true means its valid

        l_Result = d_Map.checkCountriesInsideContinentIsConnected();
        assertFalse(l_Result);
    }
}
