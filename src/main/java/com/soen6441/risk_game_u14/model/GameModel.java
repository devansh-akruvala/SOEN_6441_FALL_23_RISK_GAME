package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/***
 * @author Karandeep, Devansh
 */
public class GameModel {
	private Map d_Map;
	private ArrayList<Player> d_Players;
	private Queue<Player> d_PlayersQueue;

	public GameModel() {
		this.d_Map = new Map();
		this.d_Players = new ArrayList<Player>();
		d_PlayersQueue = new LinkedList<Player>();
	}

	public GameModel(Map p_Map) {
		this.d_Map = p_Map;
		this.d_Players = new ArrayList<Player>();
	}

	public Map getD_Map() {
		return d_Map;
	}

	public void setD_Map(Map d_Map) {
		this.d_Map = d_Map;
	}

	public ArrayList<Player> getD_Players() {
		return d_Players;
	}

	public void setD_Players(ArrayList<Player> d_Players) {
		this.d_Players = d_Players;
	}

	public Queue<Player> getD_PlayersQueue() {
		return d_PlayersQueue;
	}

	public void setD_PlayersQueue(Queue<Player> d_PlayersQueue) {
		this.d_PlayersQueue = d_PlayersQueue;
	}

	public void addPlayers(String p_PlayerName) throws Exception {
		if ((d_Players.size() >= d_Map.getD_CountryObjects().size())) {
			throw new Exception("Reached max number of players can be added to the game");
		}
		if (duplicatePlayerExist(p_PlayerName)) {
			throw new Exception("Please enter a different player name as this name already exists");
		}
		Player l_TempPlayer = new Player(p_PlayerName);
		d_Players.add(l_TempPlayer);
	}

	public void removePlayers(String p_PlayerName) throws Exception {
		if ((d_Players.size() == 0)) {
			throw new Exception("No Player Exist in Game");
		}
		if (duplicatePlayerExist(p_PlayerName)) {
			Player l_TempPlayer = null;
			for (Player l_Player : d_Players) {
				if (l_Player.getD_PlayerName().equalsIgnoreCase(p_PlayerName)) {
					l_TempPlayer = l_Player;
					break;
				}
			}
			d_Players.remove(l_TempPlayer);
		} else {
			throw new Exception("Player Doesnt Exist!!");
		}
	}

	public boolean duplicatePlayerExist(String p_PlayerName) {
		for (Player l_Player : d_Players) {
			if (l_Player.getD_PlayerName().equalsIgnoreCase(p_PlayerName))
				return true;
		}
		return false;
	}

	public void startUpPhase() throws Exception {
		if (d_Players.size() > 1) {
			d_PlayersQueue.addAll(d_Players);

			List<Country> l_CountryList = new ArrayList<>();
			l_CountryList = (List<Country>) d_Map.getD_CountryObjects().clone();
			while (l_CountryList.size() > 0) {
				Random l_Random = new Random();
				int l_Index = l_Random.nextInt(l_CountryList.size());
				Player l_TempPlayer = d_PlayersQueue.remove();
				l_TempPlayer.getD_PlayerOwnedCountries().add(l_CountryList.get(l_Index));
				d_PlayersQueue.add(l_TempPlayer);
				l_CountryList.remove(l_Index);
			}
			for (Player l_Player : d_Players) {
				l_Player.setPlayerContinent(d_Map);
			}
			assignReinforcementArmies();
		} else {
			if (d_Players.size() == 0) {
				throw new Exception("Please enter players using gameplayer add command");
			} else {
				throw new Exception("One Player Found. Please enter more players using gameplayer add command");
			}
		}
	}

	public void assignReinforcementArmies() throws Exception {
		if (d_Players.size() > 0) {
			for (Player l_Player : d_Players) {
				int l_ContinentValue = 0;
				int l_ArmyCount = ((l_Player.getD_PlayerOwnedCountries().size()) / 3);
				for (Continent l_Continent : l_Player.getD_PlayerOwnedContinent()) {
					l_ContinentValue += l_Continent.getD_ContinentValue();
				}
				l_ArmyCount = Math.max(l_ArmyCount, 3);
				l_Player.setD_ArmiesCount(l_ArmyCount + l_ContinentValue);
			}
		} else {
			throw new Exception("\"Please enter players using gameplayer add command");
		}
	}

	public void showplayer() {
		for (Player l_player : d_Players) {
			System.out.println("---------------------------------------");
			System.out.println(l_player.getD_PlayerName() + " => Reinforcement Armies: " + l_player.getD_ArmiesCount());

			System.out.println("Owned Continents:");

			for (Continent l_Continent : l_player.getD_PlayerOwnedContinent())
				System.out.println(
						l_Continent.getD_ContinentName() + " => Continent Value: " + l_Continent.getD_ContinentValue());

			System.out.println("Owned Countries:");

			for (Country l_Country : l_player.getD_PlayerOwnedCountries())
				System.out
						.println(l_Country.getD_CountryName() + " => Armies deployed: " + l_Country.getD_NoOfArmies());
		}
	}

}
