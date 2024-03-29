package com.soen6441.risk_game_u14.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.soen6441.risk_game_u14.strategy.AggresivePlayerStrategy;
import com.soen6441.risk_game_u14.strategy.BenevolentPlayerStrategy;
import com.soen6441.risk_game_u14.strategy.CheaterPlayerStrategy;
import com.soen6441.risk_game_u14.strategy.HumanPlayerStrategy;
import com.soen6441.risk_game_u14.strategy.RandomPlayerStrategy;

//import com.soen6441.risk_game_u14.strategy.AggresivePlayerStrategy;
//import com.soen6441.risk_game_u14.strategy.BenevolentPlayerStrategy;
//import com.soen6441.risk_game_u14.strategy.CheaterPlayerStrategy;
//import com.soen6441.risk_game_u14.strategy.HumanPlayerStrategy;
//import com.soen6441.risk_game_u14.strategy.RandomPlayerStrategy;

/***
 *
 * This Is the Model class for game it has reference of map and players, it
 * assign armies according to warzone rules and add/remove player from the game
 *
 * @author Karandeep, Devansh
 */
public class GameModel implements Serializable {
    private Map d_Map;
    private ArrayList<Player> d_Players;
    private Queue<Player> d_PlayersQueue;

    /***
     *
     * This is the default constructor which initializes the map, players list and
     * player queue
     */
    public GameModel() {
        this.d_Map = new Map();
        this.d_Players = new ArrayList<Player>();
        d_PlayersQueue = new LinkedList<Player>();
    }

    /***
     * This is the default constructor which initializes the map, players list and
     * player queue
     *
     * @param p_Map Map object for initialization
     */

    public GameModel(Map p_Map) {
        this.d_Map = p_Map;
        this.d_Players = new ArrayList<Player>();
        d_PlayersQueue = new LinkedList<Player>();
    }

    /**
     * Getter for map
     *
     * @return Map object
     */
    public Map getD_Map() {
        return d_Map;
    }

    /**
     * setter for
     *
     * @param d_Map Map object
     */
    public void setD_Map(Map d_Map) {
        this.d_Map = d_Map;
    }

    /**
     * Getter for list of game players
     *
     * @return list of game players
     */
    public ArrayList<Player> getD_Players() {
        return d_Players;
    }

    /**
     * setter for list of players
     *
     * @param d_Players list of players
     */
    public void setD_Players(ArrayList<Player> d_Players) {
        this.d_Players = d_Players;
    }

    /**
     * Getter for Queue of game players
     *
     * @return Queue of game players
     */
    public Queue<Player> getD_PlayersQueue() {
        return d_PlayersQueue;
    }

    /**
     * setter for queue of players
     *
     * @param d_PlayersQueue Queue of players
     */
    public void setD_PlayersQueue(Queue<Player> d_PlayersQueue) {
        this.d_PlayersQueue = d_PlayersQueue;
    }

    /**
     * This method handles add player command and add player to the list of game
     * players
     *
     * @param p_PlayerName Name of the player to be added
     * @throws Exception if player already exist or more players cannot be added
     */
    public void addPlayers(String p_PlayerName,String p_Strategy) throws Exception {
        if ((d_Players.size() >= d_Map.getD_CountryObjects().size())) {
            throw new Exception("Reached max number of players can be added to the game");
        }
        if (duplicatePlayerExist(p_PlayerName)) {
            throw new Exception("Please enter a different player name as this name already exists");
        }
        Player l_TempPlayer = new Player(p_PlayerName, this);
        
		switch(p_Strategy) {
		case "aggressive" :
			l_TempPlayer.setD_PlayerStrategy(new AggresivePlayerStrategy(l_TempPlayer,this));
			break;

		case "human" :
			l_TempPlayer.setD_PlayerStrategy(new HumanPlayerStrategy(l_TempPlayer,this));
			break;

		case "benevolent" :				
			l_TempPlayer.setD_PlayerStrategy(new BenevolentPlayerStrategy(l_TempPlayer,this));
			break;

		case "random": 
			l_TempPlayer.setD_PlayerStrategy(new RandomPlayerStrategy(l_TempPlayer,this));
			break;
		case "cheater": 
			
			l_TempPlayer.setD_PlayerStrategy(new CheaterPlayerStrategy(l_TempPlayer,this));
			
			break;
			
		default:
			System.out.println("Invalid Command. Please try again.\n");
			break;
		}

        d_Players.add(l_TempPlayer);
        
    }

    /**
     * This method handles remove player command and removes player from the list of
     * players
     *
     * @param p_PlayerName Name of the player to be removed
     * @throws Exception if there is no players in the game or Player with given
     *                   name does not exist
     */

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

    /**
     * This method checks that player already exist in the game
     *
     * @param p_PlayerName Name of the player
     * @return 'true' if player exist else 'false'
     */
    public boolean duplicatePlayerExist(String p_PlayerName) {
        for (Player l_Player : d_Players) {
            if (l_Player.getD_PlayerName().equalsIgnoreCase(p_PlayerName))
                return true;
        }
        return false;
    }

    /**
     * This method handles assigncountries command and takes game to 'gameplay'
     * phase. It randomly assigns countries to gameplayers and then assign them
     * reinforcement armies to them according to warzone rule
     *
     * @throws Exception if there is 0 or 1 players
     */
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
                l_CountryList.get(l_Index).setD_Owner(l_TempPlayer);
                d_PlayersQueue.add(l_TempPlayer);
                l_CountryList.remove(l_Index);
            }
        } else {
            if (d_Players.size() == 0) {
                throw new Exception("Please enter players using gameplayer add command");
            } else {
                throw new Exception("One Player Found. Please enter more players using gameplayer add command");
            }
        }
        // adding neutral player
        this.d_Players.add(new Player("Neutral Player", this));
    }

    /**
     * This method randomly assign armies to all players according to warzone rules
     * @throws Exception
     */
    public void assignReinforcementArmies() throws Exception {
        for (Player l_Player : d_Players) {
            l_Player.clearPlayerContinent();
            l_Player.setPlayerContinent();
        }

        if (d_Players.size() > 0) {
            for (Player l_Player : d_Players) {
                if (!l_Player.getD_PlayerName().equalsIgnoreCase("Neutral Player")) {
                    int l_ContinentValue = 0;
                    int l_ArmyCount = ((l_Player.getD_PlayerOwnedCountries().size()) / 3);
                    for (Continent l_Continent : l_Player.getD_PlayerOwnedContinent()) {
                        l_ContinentValue += l_Continent.getD_ContinentValue();
                    }
                    l_ArmyCount = Math.max(l_ArmyCount, 3);
                    l_Player.setD_ArmiesCount(Math.abs(l_Player.getD_ArmiesCount() + l_ArmyCount + l_ContinentValue));
                    System.out.println(l_Player.getD_PlayerName() + " got " + l_Player.getD_ArmiesCount() + " armies");

                } else {
                    l_Player.setD_ArmiesCount(0);
                }
            }
        } else {
            throw new Exception("\"Please enter players using gameplayer add command");
        }
    }

    /**
     * This method shows info of each players like their Reinforcement Armies,
     * continents and countries owned by them along with number of armies deployed
     * in the country
     */
    public void showplayer() {
        for (Player l_player : d_Players) {
            System.out.println("\n---------------------------------------");
            System.out.println(l_player.getD_PlayerName() + " => Reinforcement Armies: " + l_player.getD_ArmiesCount());

            System.out.print("Owned Cards: [");

            for (String l_Card : l_player.getD_Cards())
                System.out.print(l_Card + " ,");
            System.out.print("]");
            System.out.println(" ");

            System.out.println("Owned Continents:");

            for (Continent l_Continent : l_player.getD_PlayerOwnedContinent())
                System.out.println("\t" +
                        l_Continent.getD_ContinentName() + " => Continent Value: " + l_Continent.getD_ContinentValue());

            System.out.println("Owned Countries:");

            for (Country l_Country : l_player.getD_PlayerOwnedCountries())
                System.out
                        .println("\t" + l_Country.getD_CountryName() + " => Armies deployed: " + l_Country.getD_NoOfArmies());

        }
        System.out.println("---------------------------------------");
    }
    
    /**
	 * <p>
	 * This Method is the startup Phase method for TournamentPhase
	 * </p>
	 * <ul>
	 * <li>This Method will take all the players in a a list.</li>
	 * <li>Then it will check for all the countries available in a map.</li> 
	 * <li>It will run the loop until all the countries are assigned to players.</li>
	 * <li>Now it will Randomly assign countries to all players and remove the assigned countries from the list.</li>
	 * </ul> 
	 */
	public void tournamentstartUpPhase() {
		List<Country> l_CountryList = (List<Country>) this.getD_Map().getD_CountryObjects().clone();	
		int l_PlayerIndex = 0;
		int l_PlayerCount = this.getD_Players().size();
		ArrayList<Integer> l_TempList = new ArrayList<>();
		ArrayList<Player> l_playerList= this.getD_Players();
		// Here creating the list with indexes
		for (int i = 0; i < l_CountryList.size(); i++) {
			l_TempList.add(i);
		}
		// Shuffling the list for randomness
		Collections.shuffle(l_TempList, new Random());
		// assigning the shuffled countries from tempList to the players one by one
		for (int i = 0; i < l_CountryList.size(); i++) {
			if (l_PlayerIndex == l_PlayerCount)
				l_PlayerIndex = 0;
			Country l_NewCountry = l_CountryList.get(l_TempList.get(i));
			l_playerList.get(l_PlayerIndex).addCountry(l_NewCountry);
			l_NewCountry.setD_Owner(l_playerList.get(l_PlayerIndex));
			l_CountryList.remove(l_TempList.get(i));
			l_PlayerIndex++;
		}
		
	}
	
	/**
	 * This method takes the filename from the user and saves the game as in saves the game model object in to the file 
	 * @param p_FileName name of the file to save the game
	 */
	public  void saveGame(String p_FileName) {
		String l_File = p_FileName;
		System.out.println(p_FileName);
		try (FileOutputStream fileOut = new FileOutputStream("savedgames\\" + l_File);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(this);
		} catch (IOException i) {
			i.printStackTrace();
		}
		
	}
	/**
	 * This methods loads the saved game.
	 * It takes the name of the saved game file as input and converts it into the game model object and returns this object
	 * @param p_FileName name of the saved game file 
	 * @return game  object of gamemodel
	 */
	public static GameModel loadGame(String p_FileName) {
		GameModel game = null;
		try (FileInputStream fileIn = new FileInputStream("savedgames\\" + p_FileName);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			game = (GameModel) in.readObject();
		} catch (IOException i) {
			return null;
		} catch (ClassNotFoundException c) {
			return null;
		}
		return game;
	}
}
