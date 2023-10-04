package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * This is a model class for the game players
 * 
 * @author Devansh, Aditya, Karandeep
 */
public class Player {
	private static int D_PlayerCount = 0;
	private int d_Playerid;
	private String d_PlayerName;
	private int d_ArmiesCount;
	private Orders d_CurrentOrder;
	private String d_Result;
	private List<Country> d_PlayerOwnedCountries;
	private List<Continent> d_PlayerOwnedContinent;
	private Queue<Orders> d_PlayerOrderQueue;

	/***
	 * This constructor initializes the player
	 * 
	 * @param p_PlayerName this is the game player name
	 */
	public Player(String p_PlayerName) {
		setD_Playerid(++D_PlayerCount);
		this.d_PlayerName = p_PlayerName;
		d_PlayerOwnedCountries = new ArrayList<>();
		d_PlayerOwnedContinent = new ArrayList<>();
		d_PlayerOrderQueue = new LinkedList<>();
		d_Result = "";
	}

	/**
	 * Getter for d_PlayerCount
	 * 
	 * @return Player count
	 */

	public static int getD_PlayerCount() {
		return D_PlayerCount;
	}

	/**
	 * Setter for d_playercount
	 * 
	 * @param d_PlayerCount
	 */
	public static void setD_PlayerCount(int d_PlayerCount) {
		D_PlayerCount = d_PlayerCount;
	}

	/**
	 * Getter for d_PlayerId
	 * 
	 * @return Player Id
	 */
	public int getD_Playerid() {
		return d_Playerid;
	}

	/**
	 * Setter for player id
	 * 
	 * @param d_Playerid
	 */
	public void setD_Playerid(int d_Playerid) {
		this.d_Playerid = d_Playerid;

	}

	/**
	 * Getter for d_PlayerName
	 * 
	 * @return Player name
	 */
	public String getD_PlayerName() {
		return d_PlayerName;
	}

	/**
	 * Setter for d_PlayerName
	 * 
	 * @param d_PlayerName
	 */
	public void setD_PlayerName(String d_PlayerName) {
		this.d_PlayerName = d_PlayerName;
	}

	/**
	 * Getter for d_ArmiesCount
	 * 
	 * @return Armies count
	 */
	public int getD_ArmiesCount() {
		return d_ArmiesCount;
	}

	/**
	 * Setter for Armies count
	 * 
	 * @param d_ArmiesCount
	 */
	public void setD_ArmiesCount(int d_ArmiesCount) {
		this.d_ArmiesCount = d_ArmiesCount;
	}

	/**
	 * Getter for current command
	 * 
	 * @return Player corrent command
	 */
	public Orders getD_CurrentCommand() {
		return d_CurrentOrder;
	}

	/**
	 * Setter for current order
	 * 
	 * @param p_CurrentOredr
	 */
	public void setD_CurrentCommand(Orders p_CurrentOredr) {
		this.d_CurrentOrder = p_CurrentOredr;
	}

	/**
	 * Getter for current orders
	 * 
	 * @return current order
	 */
	public Orders getD_CurrentOrder() {
		return d_CurrentOrder;
	}

	/**
	 * Setter for current order String
	 * 
	 * @param d_CurrentOrder
	 */
	public void setD_CurrentOrder(Orders d_CurrentOrder) {
		this.d_CurrentOrder = d_CurrentOrder;
	}

	/**
	 * Getter for result
	 * 
	 * @return Result of order if error else ""
	 */
	public String getD_Result() {
		return d_Result;
	}

	/**
	 * Setter for d_Result
	 * 
	 * @param d_Result
	 */
	public void setD_Result(String d_Result) {
		this.d_Result = d_Result;
	}

	/**
	 * Getter for player owned counties
	 * 
	 * @return list of player owned countries
	 */

	public List<Country> getD_PlayerOwnedCountries() {
		return d_PlayerOwnedCountries;
	}

	/**
	 * Setter for list of countries owned by player
	 * 
	 * @param d_PlayerOwnedCountries List of countries
	 */
	public void setD_PlayerOwnedCountries(List<Country> d_PlayerOwnedCountries) {
		this.d_PlayerOwnedCountries = d_PlayerOwnedCountries;
	}

	/**
	 * Getter for player queue
	 * 
	 * @return queue of players
	 */

	public Queue<Orders> getD_PlayerOrderQueue() {
		return d_PlayerOrderQueue;
	}

	/**
	 * Setter for player queue
	 * 
	 * @param d_PlayerOrderQueue player queue
	 */
	public void setD_PlayerOrderQueue(Queue<Orders> d_PlayerOrderQueue) {
		this.d_PlayerOrderQueue = d_PlayerOrderQueue;
	}

	/**
	 * Getter for getD_PlayerOwnedContinent returns Continent owned by player
	 */
	public List<Continent> getD_PlayerOwnedContinent() {
		return d_PlayerOwnedContinent;
	}

	/**
	 * Setter for player owned continent
	 * 
	 * @param d_PlayerOwnedContinent
	 */
	public void setD_PlayerOwnedContinent(List<Continent> d_PlayerOwnedContinent) {
		this.d_PlayerOwnedContinent = d_PlayerOwnedContinent;
	}

	/***
	 * This method checks that the player has captured the whole continent and if
	 * yes, adds the continent value (reward) to the reinforcement armies
	 * 
	 * @param p_Map this is the game map
	 */
	public void setPlayerContinent(Map p_Map) {
		for (Continent l_continent : p_Map.getD_ContinentObjects()) {
			int l_CountryCounter = 0;
			for (Country l_CountriesInCoontinent : l_continent.getD_CountryList()) {
				if (d_PlayerOwnedCountries.indexOf(l_CountriesInCoontinent) >= 0)
					l_CountryCounter++;
			}
			if (l_CountryCounter == l_continent.getD_CountryList().size())
				d_PlayerOwnedContinent.add(l_continent);
		}
	}

	/***
	 * This method adds an order to the player's order queue
	 */
	public void issueOrder() {
		String l_InputCommandSplit[] = d_CurrentOrder.getD_Orders().split(" ");
		if (Integer.parseInt(l_InputCommandSplit[2]) <= d_ArmiesCount)
			d_PlayerOrderQueue.add(d_CurrentOrder);
		else
			setD_Result("Player doesn't have enough armies!!");
		;
	}

	/***
	 * This method pops an order to the player's order
	 * 
	 * @return it returns the order
	 */
	public Orders nextOrder() {
		return d_PlayerOrderQueue.remove();
	}
}
