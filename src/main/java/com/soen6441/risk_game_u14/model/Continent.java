package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;

/**
 * This is a model class for storing continent's information
 * 
 *@author Devansh 
 * 
 */

public class Continent {
	private static int D_Count=0;
	private int d_ContinentId;
	private String d_ContinentName;
	private int d_ContinentValue;
	private ArrayList<Country> d_CountryList;


	/***
	 * Constructor for the continent class
	 * @param d_ContinentName Name of the continent
	 * @param d_ContinentValue Value of the continent (army reward)
	 */
	public Continent(String d_ContinentName, int d_ContinentValue) {
		setD_ContinentId(++D_Count);
		this.d_ContinentName = d_ContinentName;
		this.d_ContinentValue = d_ContinentValue;
		d_CountryList=new ArrayList<>();
		
	}

	public static int getD_Count() {
		return D_Count;
	}

	public static void setD_Count(int d_Count) {
		D_Count = d_Count;
	}

	public int getD_ContinentId() {
		return d_ContinentId;
	}
	public void setD_ContinentId(int p_ContinentId) {
		this.d_ContinentId = p_ContinentId;
	}
	public String getD_ContinentName() {
		return d_ContinentName;
	}
	public void setD_ContinentName(String p_ContinentName) {
		this.d_ContinentName = p_ContinentName;
	}
	public int getD_ContinentValue() {
		return d_ContinentValue;
	}
	public void setD_ContinentValue(int p_ContinentValue) {
		this.d_ContinentValue = p_ContinentValue;
	}
	public ArrayList<Country> getD_CountryList() {
		return d_CountryList;
	}
	public void setD_CountryList(ArrayList<Country> p_CountryList) {
		this.d_CountryList = p_CountryList;
	}

		
}
