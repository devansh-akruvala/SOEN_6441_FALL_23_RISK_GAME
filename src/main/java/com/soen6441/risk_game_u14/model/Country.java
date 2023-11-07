package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;

/**
 * This class contains all info about countries
 * 
 * @author Devansh
 */

public class Country {
	private static int D_Count = 0;
	private int d_CountryId;
	private String d_CountryName;
	private String d_CountryContinent;
	private ArrayList<String> d_Neighbors;
	private int d_NoOfArmies;
	private Player d_Owner;

	/**
	 * 
	 * Constructor for Country class
	 * 
	 * @param p_countryName name of country
	 * @param p_continent   name of continent
	 * 
	 */

	
	
	public Country(String p_countryName, String p_continent) {
		setD_CountryId(++D_Count);
		this.d_CountryName = p_countryName;
		this.d_CountryContinent = p_continent;
		this.d_Neighbors = new ArrayList<>();
		this.d_NoOfArmies = 0;
	}

	public Player getD_Owner() {
		return d_Owner;
	}

	public void setD_Owner(Player d_Owner) {
		this.d_Owner = d_Owner;
	}

	/**
	 * getter for d_count
	 * 
	 * @return D_Count
	 */
	public static int getD_Count() {
		return D_Count;
	}

	/**
	 * setter for d_count
	 * 
	 * @param d_Count
	 */
	public static void setD_Count(int d_Count) {
		D_Count = d_Count;
	}

	/**
	 * getter for country id
	 * 
	 * @return country id
	 */
	public int getD_CountryId() {
		return d_CountryId;
	}

	/**
	 * setter for country id
	 * 
	 * @param d_CountryId
	 */
	public void setD_CountryId(int d_CountryId) {
		this.d_CountryId = d_CountryId;
	}

	/**
	 * getter for country name
	 * 
	 * @return country name
	 */
	public String getD_CountryName() {
		return d_CountryName;
	}

	/**
	 * setter for country name
	 * 
	 * @param d_CountryName Name of country
	 */
	public void setD_CountryName(String d_CountryName) {
		this.d_CountryName = d_CountryName;
	}

	/**
	 * getter for country's continent
	 * 
	 * @return country's continent
	 */
	public String getD_CountryContinent() {
		return d_CountryContinent;
	}

	/**
	 * setter for country's continent
	 * 
	 * @param d_CountryContinent country's continent
	 */
	public void setD_CountryContinent(String d_CountryContinent) {
		this.d_CountryContinent = d_CountryContinent;
	}

	/**
	 * getter for country's neighbor
	 * 
	 * @return list of country's neighbors
	 */
	public ArrayList<String> getD_Neighbors() {
		return d_Neighbors;
	}

	/**
	 * setter for country's neighbor
	 * 
	 * @param d_Neighbors List of neighbors
	 */
	public void setD_Neighbors(ArrayList<String> d_Neighbors) {
		this.d_Neighbors = d_Neighbors;
	}

	/**
	 * getter for number of armies
	 * 
	 * @return number of armies
	 */
	public int getD_NoOfArmies() {
		return d_NoOfArmies;
	}

	/**
	 * setter for number of armies
	 * 
	 * @param d_NoOfArmies d_NoOfArmies
	 */
	public void setD_NoOfArmies(int d_NoOfArmies) {
		this.d_NoOfArmies = d_NoOfArmies;
	}

	/**
	 * This method add neighbor to the list of neighbor countries
	 * 
	 * @param countryName Neighbor country name
	 */
	public void addNeighbours(String countryName) {
		this.d_Neighbors.add(countryName);
	}

}
