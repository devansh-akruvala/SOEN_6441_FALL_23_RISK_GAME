package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/***
 * @author Aditya, Karandeep, Devansh
 */
public class Player {
	private static int D_PlayerCount = 0;
	private int d_Playerid;
	private String d_PlayerName;
	private int d_ArmiesCount;
	private Orders d_CurrentOrder;
	private List<Country> d_PlayerOwnedCountries;
	private List<Continent> d_PlayerOwnedContinent;
	private Queue<Orders> d_PlayerOrderQueue;
	
	public Player(String p_PlayerName) {
		setD_Playerid(++D_PlayerCount);
		this.d_PlayerName=p_PlayerName;
		d_PlayerOwnedCountries = new ArrayList<>();
		d_PlayerOwnedContinent = new ArrayList<>();
		d_PlayerOrderQueue = new LinkedList<>();
	}
	
	
	public static int getD_PlayerCount() {
		return D_PlayerCount;
	}
	public static void setD_PlayerCount(int d_PlayerCount) {
		D_PlayerCount = d_PlayerCount;
	}
	public int getD_Playerid() {
		return d_Playerid;
	}
	public void setD_Playerid(int d_Playerid) {
		this.d_Playerid = d_Playerid;
	}
	public String getD_PlayerName() {
		return d_PlayerName;
	}
	public void setD_PlayerName(String d_PlayerName) {
		this.d_PlayerName = d_PlayerName;
	}
	public int getD_ArmiesCount() {
		return d_ArmiesCount;
	}
	public void setD_ArmiesCount(int d_ArmiesCount) {
		this.d_ArmiesCount = d_ArmiesCount;
	}
	public Orders getD_CurrentCommand() {
		return d_CurrentOrder;
	}


	public void setD_CurrentCommand(Orders p_CurrentOredr) {
		this.d_CurrentOrder = p_CurrentOredr;
	}


	public List<Country> getD_PlayerOwnedCountries() {
		return d_PlayerOwnedCountries;
	}
	public void setD_PlayerOwnedCountries(List<Country> d_PlayerOwnedCountries) {
		this.d_PlayerOwnedCountries = d_PlayerOwnedCountries;
	}
	public Queue<Orders> getD_PlayerOrderQueue() {
		return d_PlayerOrderQueue;
	}
	public List<Continent> getD_PlayerOwnedContinent() {
		return d_PlayerOwnedContinent;
	}

	public void setD_PlayerOwnedContinent(List<Continent> d_PlayerOwnedContinent) {
		this.d_PlayerOwnedContinent = d_PlayerOwnedContinent;
	}


	public void setD_PlayerOrderQueue(Queue<Orders> d_PlayerOrderQueue) {
		this.d_PlayerOrderQueue = d_PlayerOrderQueue;
	}
	
	public void setPlayerContinent(Map p_Map) {
		for(Continent l_continent : p_Map.getD_ContinentObjects()) {
			int l_CountryCounter = 0;
			for(Country l_CountriesInCoontinent: l_continent.getD_CountryList()) {
				if(d_PlayerOwnedCountries.indexOf(l_CountriesInCoontinent)>=0)
					l_CountryCounter++;
			}
			if(l_CountryCounter == l_continent.getD_CountryList().size())
				d_PlayerOwnedContinent.add(l_continent);
		}
	}
	
	public void issueOrder() {
		d_PlayerOrderQueue.add(d_CurrentOrder);
	}
	public Orders nextOrder() {
		return d_PlayerOrderQueue.remove();
	}
}
