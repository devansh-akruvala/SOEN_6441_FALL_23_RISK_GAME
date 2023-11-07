package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.soen6441.risk_game_u14.order.Advance;
import com.soen6441.risk_game_u14.order.Deploy;

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
	private Order d_CurrentOrder;
	private String d_Result;
	private List<Country> d_PlayerOwnedCountries;
	private List<Continent> d_PlayerOwnedContinent;
	private Queue<Order> d_PlayerOrderQueue;
	private Boolean d_SkipCommands;
	private GameModel d_GameModel;
	private ArrayList<Player> d_NegotiatedPlayers;
	private boolean d_AtleastOneBattleWon;
	private ArrayList<String> d_Cards;

	/***
	 * This constructor initializes the player
	 * 
	 * @param p_PlayerName this is the game player name
	 */
	public Player(String p_PlayerName, GameModel p_GameModel) {
		setD_Playerid(++D_PlayerCount);
		this.d_PlayerName = p_PlayerName;
		d_GameModel = p_GameModel;
		d_PlayerOwnedCountries = new ArrayList<>();
		d_PlayerOwnedContinent = new ArrayList<>();
		d_PlayerOrderQueue = new LinkedList<>();
		d_Result = "";
		d_SkipCommands = false;
		d_NegotiatedPlayers = new ArrayList<Player>();
		d_AtleastOneBattleWon = false;
		d_Cards = new ArrayList<String>();
	}

	public ArrayList<String> getD_Cards() {
		return d_Cards;
	}

	public void setD_Cards(ArrayList<String> d_Cards) {
		this.d_Cards = d_Cards;
	}

	public GameModel getD_GameModel() {
		return d_GameModel;
	}

	public void setD_Cards(GameModel p_GameModel) {
		this.d_GameModel = p_GameModel;
	}

	public boolean isD_AtleastOneBattleWon() {
		return d_AtleastOneBattleWon;
	}

	public ArrayList<Player> getD_NegotiatedPlayers() {
		return d_NegotiatedPlayers;
	}

	public void setD_NegotiatedPlayers(ArrayList<Player> d_NegotiatedPlayers) {
		this.d_NegotiatedPlayers = d_NegotiatedPlayers;
	}

	public boolean setD_AtleastOneBattleWon() {
		return d_AtleastOneBattleWon;
	}

	public void setD_AtleastOneBattleWon(boolean d_AtleastOneBattleWon) {
		this.d_AtleastOneBattleWon = d_AtleastOneBattleWon;
	}

	public Boolean getD_SkipCommands() {
		return d_SkipCommands;
	}

	public void setD_SkipCommands(Boolean d_SkipCommands) {
		this.d_SkipCommands = d_SkipCommands;
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
	public Order getD_CurrentCommand() {
		return d_CurrentOrder;
	}

	/**
	 * Setter for current order
	 * 
	 * @param p_CurrentOredr
	 */
	public void setD_CurrentCommand(Order p_CurrentOredr) {
		this.d_CurrentOrder = p_CurrentOredr;
	}

	/**
	 * Getter for current orders
	 * 
	 * @return current order
	 */
	public Order getD_CurrentOrder() {
		return d_CurrentOrder;
	}

	/**
	 * Setter for current order String
	 * 
	 * @param d_CurrentOrder
	 */
	public void setD_CurrentOrder(Order d_CurrentOrder) {
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

	public Queue<Order> getD_PlayerOrderQueue() {
		return d_PlayerOrderQueue;
	}

	/**
	 * Setter for player queue
	 * 
	 * @param d_PlayerOrderQueue player queue
	 */
	public void setD_PlayerOrderQueue(Queue<Order> d_PlayerOrderQueue) {
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

	public Country checkCountryBelongstoPlayer(String p_Country) {
		for (Country l_country : d_PlayerOwnedCountries) {
			if (l_country.getD_CountryName().equalsIgnoreCase(p_Country))
				return l_country;
		}
		return null;
	}

	public void addCountry(Country p_Country) {
		d_PlayerOwnedCountries.add(p_Country);
	}

	/**
	 * removeCountry removes the given country from the player's country list
	 * 
	 * @param p_Country Name of the country to be removed
	 */
	public void removeCountry(Country p_Country) {
		d_PlayerOwnedCountries.remove(p_Country);
	}

	/***
	 * This method adds an order to the player's order queue
	 */
	public void issueOrder(String p_Orders) {
		String l_InputCommandSplit[] = p_Orders.split(" ");

		switch (l_InputCommandSplit[0]) {

			case "deploy":
				Country l_TargetCountryObject = checkCountryBelongstoPlayer(l_InputCommandSplit[1]);
				getD_PlayerOrderQueue()
						.add(new Deploy(this, l_TargetCountryObject, Integer.parseInt(l_InputCommandSplit[2])));
				break;
			case "advance":
				Country l_SourceCountry = checkCountryBelongstoPlayer(l_InputCommandSplit[1]);
				Country l_TargetCountry = d_GameModel.getD_Map().findCountryByName(l_InputCommandSplit[2]);
				int l_NumArmies1 = Integer.parseInt(l_InputCommandSplit[3]);
				getD_PlayerOrderQueue().add(new Advance(this, l_SourceCountry, l_TargetCountry, l_NumArmies1));
				break;
		}
	}

	/***
	 * This method pops an order to the player's order
	 * 
	 * @return it returns the order
	 */
	public Order nextOrder() {
		return d_PlayerOrderQueue.remove();
	}
}
