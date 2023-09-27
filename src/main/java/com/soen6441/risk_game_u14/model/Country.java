package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;

/**
 * This class contains all info about countries
 * @author Devansh
 * */

public class Country {
	private static int D_Count = 0;
	private int d_CountryId;
	private String d_CountryName;
	private String d_CountryContinent;
	private ArrayList<String> d_Neighbors;
	private int d_NoOfArmies;

	
	
	public Country(String countryName,String continent) {
		setD_CountryId(++D_Count);
		this.d_CountryName=countryName;
		this.d_CountryContinent=continent;
		this.d_Neighbors=new ArrayList<>();
		this.d_NoOfArmies=0;
	}

	public int getD_CountryId() {
		return d_CountryId;
	}
	public void setD_CountryId(int d_CountryId) {
		this.d_CountryId = d_CountryId;
	}
	public String getD_CountryName() {
		return d_CountryName;
	}
	public void setD_CountryName(String d_CountryName) {
		this.d_CountryName = d_CountryName;
	}


	public String getD_CountryContinent() {
		return d_CountryContinent;
	}

	public void setD_CountryContinent(String d_CountryContinent) {
		this.d_CountryContinent = d_CountryContinent;
	}

	public ArrayList<String> getD_Neighbors() {
		return d_Neighbors;
	}
	public void setD_Neighbors(ArrayList<String> d_Neighbors) {
		this.d_Neighbors = d_Neighbors;
	}
	public int getD_NoOfArmies() {
		return d_NoOfArmies;
	}
	public void setD_NoOfArmies(int d_NoOfArmies) {
		this.d_NoOfArmies = d_NoOfArmies;
	}
	
	public void addNeighbours(String countryName) {
		this.d_Neighbors.add(countryName);
	}
	
	
}
