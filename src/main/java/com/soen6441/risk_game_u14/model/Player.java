package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.soen6441.risk_game_u14.order.Advance;
import com.soen6441.risk_game_u14.order.Airlift;
import com.soen6441.risk_game_u14.order.Blockade;
import com.soen6441.risk_game_u14.order.Bomb;
import com.soen6441.risk_game_u14.order.Deploy;
import com.soen6441.risk_game_u14.order.Negotiate;
import com.soen6441.risk_game_u14.strategy.Strategy;

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
    private Strategy d_PlayerStrategy;
    private Boolean isExit;
	/**
	 * Boolean to check if a game should be saved or not
	 */
	private boolean d_SaveGame=false;
	/**
	 * default constructor of Player class	
	 */

    /***
     * This constructor initializes the player
     *
     * @param p_PlayerName This is the game player name
     * @param p_GameModel  This is the game model
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
        isExit=false;
    }
    
    
    
    
    public Boolean getIsExit() {
		return isExit;
	}




	public void setIsExit(Boolean isExit) {
		this.isExit = isExit;
	}




	public Strategy getD_PlayerStrategy() {
		return d_PlayerStrategy;
	}






	public void setD_PlayerStrategy(Strategy d_PlayerStrategy) {
		this.d_PlayerStrategy = d_PlayerStrategy;
	}






	public boolean isD_SaveGame() {
		return d_SaveGame;
	}






	public void setD_SaveGame(boolean d_SaveGame) {
		this.d_SaveGame = d_SaveGame;
	}






	public void setD_GameModel(GameModel d_GameModel) {
		this.d_GameModel = d_GameModel;
	}






	/**
     * Retrieves the list of cards owned by the player.
     *
     * @return An ArrayList of Strings representing the player's cards.
     */
    public ArrayList<String> getD_Cards() {
        return d_Cards;
    }
    /**
     * Sets the list of cards owned by the player.
     *
     * @param d_Cards An ArrayList of Strings representing the player's cards to be set.
     */
    public void setD_Cards(ArrayList<String> d_Cards) {
        this.d_Cards = d_Cards;
    }
    /**
     * Retrieves the game model associated with the player.
     *
     * @return The GameModel object associated with the player.
     */
    public GameModel getD_GameModel() {
        return d_GameModel;
    }
    /**
     * Sets the game model for the player.
     *
     * @param p_GameModel The GameModel to be associated with the player.
     */
    public void setD_Cards(GameModel p_GameModel) {
        this.d_GameModel = p_GameModel;
    }
    /**
     * Checks if the player has won at least one battle.
     *
     * @return A boolean indicating whether the player has won at least one battle.
     */
    public boolean isD_AtleastOneBattleWon() {
        return d_AtleastOneBattleWon;
    }
    /**
     * Retrieves the list of players negotiated with by the current player.
     *
     * @return An ArrayList of Player objects negotiated with by the player.
     */
    public ArrayList<Player> getD_NegotiatedPlayers() {
        return d_NegotiatedPlayers;
    }
    /**
     * Sets the list of players negotiated with by the player.
     *
     * @param d_NegotiatedPlayers An ArrayList of Player objects negotiated with by the player.
     */
    public void setD_NegotiatedPlayers(ArrayList<Player> d_NegotiatedPlayers) {
        this.d_NegotiatedPlayers = d_NegotiatedPlayers;
    }
    /**
     * Checks if the player has won at least one battle and returns a boolean value.
     *
     * @return A boolean value indicating whether the player has won at least one battle.
     */
    public boolean setD_AtleastOneBattleWon() {
        return d_AtleastOneBattleWon;
    }
    /**
     * Sets the status of whether the player has won at least one battle.
     *
     * @param d_AtleastOneBattleWon A boolean value indicating whether the player has won at least one battle.
     */
    public void setD_AtleastOneBattleWon(boolean d_AtleastOneBattleWon) {
        this.d_AtleastOneBattleWon = d_AtleastOneBattleWon;
    }
    /**
     * Retrieves the status of skipping commands by the player.
     *
     * @return A Boolean value representing the status of skipping commands by the player.
     */
    public Boolean getD_SkipCommands() {
        return d_SkipCommands;
    }
    /**
     * Sets the status of skipping commands by the player.
     *
     * @param d_SkipCommands A Boolean value representing the status of skipping commands by the player.
     */
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
     * @param d_PlayerCount The player count
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
     * @param d_Playerid Id of the player
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
     * @param d_PlayerName The name of a player
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
     * @param d_ArmiesCount Indicate the number of armies
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
     * @param p_CurrentOredr The current order
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
     * @param d_CurrentOrder The curent order
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
     * @param d_Result The result
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
     * Getter for getD_PlayerOwnedContinent
     * @return Continent owned by player
     */
    public List<Continent> getD_PlayerOwnedContinent() {
        return d_PlayerOwnedContinent;
    }

    /**
     * Setter for player owned continent
     *
     * @param d_PlayerOwnedContinent The continent owned by the player
     */
    public void setD_PlayerOwnedContinent(List<Continent> d_PlayerOwnedContinent) {
        this.d_PlayerOwnedContinent = d_PlayerOwnedContinent;
    }

    /***
     * This method checks that the player has captured the whole continent and if
     * yes, adds the continent value (reward) to the reinforcement armies
     *
     *
     */
    public void setPlayerContinent() {
        ArrayList<Continent> l_MapContinents = d_GameModel.getD_Map().getD_ContinentObjects();
        for (Continent l_MapContinent : l_MapContinents) {
            int l_Flag = 0;
            for (Country l_Country : l_MapContinent.getD_CountryList()) {
                if (!d_PlayerOwnedCountries.contains(l_Country)) {
                    l_Flag = 1;
                }
            }
            if (l_Flag == 0) {
                d_PlayerOwnedContinent.add(l_MapContinent);
            }
        }
    }
    /**
     * Clears the list of continents owned by the player.
     * The player's owned continents list is reset to an empty state.
     */
    public void clearPlayerContinent() {
        d_PlayerOwnedContinent.clear();
    }
    /**
     * Checks if a specified country belongs to the player.
     *
     * @param p_Country The name of the country being checked.
     * @return The Country object if it is owned by the player, otherwise returns null.
     */
    public Country checkCountryBelongstoPlayer(String p_Country) {
        for (Country l_country : d_PlayerOwnedCountries) {
            if (l_country.getD_CountryName().equalsIgnoreCase(p_Country))
                return l_country;
        }
        return null;
    }
    /**
     * Adds a country to the player's owned countries list.
     *
     * @param p_Country The Country object to be added to the player's owned countries.
     */
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

    /**
     * get method for the card if the player owns it or not.
     *
     * @param p_TypeOfCard the string that indicates the type of card
     * @return true if the card type exists in the list of player.
     */
    public boolean getCard(String p_TypeOfCard) {
        return d_Cards.contains(p_TypeOfCard);
    }

    /**
     * set method for adding the card to the card list belonging to the player
     *
     * @param p_Card the Card object that belongs to the player
     */
    public void setCard(String p_Card) {
        d_Cards.add(p_Card);
    }

    /**
     * This method clears the negotiated players list
     */
    public void removeNegotiatedPlayer() {
        if (d_NegotiatedPlayers.size() > 0)
            d_NegotiatedPlayers.clear();
    }
    /**
     * Searches for a player by their name within the game.
     *
     * @param p_name The name of the player being searched for.
     * @return The Player object if a player with the specified name is found within the game; otherwise, returns null.
     */
    public Player findPlayerByName(String p_name) {
        for (Player l_tp : d_GameModel.getD_Players()) {
            if (l_tp.getD_PlayerName().equalsIgnoreCase(p_name)) {
                return l_tp;
            }
        }
        return null;
    }


    /**
     * The issue order method evaluates the order given by the player, which falls into one of five types:
     * <ul>
     *     <li>Deploy Order: Deploys the specified number of armies to the designated country.</li>
     *     <li>Advance Order: Initiates an attack on the target country with a specified number of armies from the source country.</li>
     *     <li>Bomb Order: Launches a bombing operation on a specified country.</li>
     *     <li>Blockade Order: Transforms the player's own country into a neutral territory by tripling its current armies.</li>
     *     <li>AirLift Order: Moves armies from the source country to a target country, even if they are not neighbors.</li>
     *     <li>Negotiate Order: Establishes a diplomatic agreement between the issuing player and another player, preventing attacks on each other's countries.</li>
     * </ul>
     * 
     */
    public void issueOrder() {

    	String playerGeneratedOrder = d_PlayerStrategy.createOrder();
    	if(playerGeneratedOrder==null)
    		return;
    	System.out.println(playerGeneratedOrder);
        String l_InputCommandSplit[] = playerGeneratedOrder.split(" "); //p_Orders.split(" ");
        String l_command = l_InputCommandSplit[0];
        if (l_command.equalsIgnoreCase("deploy")) {
            Country l_TargetCountryObject = checkCountryBelongstoPlayer(l_InputCommandSplit[1]);
            getD_PlayerOrderQueue()
                    .add(new Deploy(this, l_TargetCountryObject, Integer.parseInt(l_InputCommandSplit[2])));
        } else if (l_command.equalsIgnoreCase("advance")) {
            Country l_SourceCountry = checkCountryBelongstoPlayer(l_InputCommandSplit[1]);
            Country l_TargetCountry = d_GameModel.getD_Map().findCountryByName(l_InputCommandSplit[2]);
            int l_NumArmies1 = Integer.parseInt(l_InputCommandSplit[3]);
            getD_PlayerOrderQueue().add(new Advance(this, l_SourceCountry, l_TargetCountry, l_NumArmies1));
        } else if (l_command.equalsIgnoreCase("bomb")) {
            Country l_TargetCountry = d_GameModel.getD_Map().findCountryByName(l_InputCommandSplit[1]);
            getD_PlayerOrderQueue().add(new Bomb(this, l_TargetCountry));
        } else if (l_command.equalsIgnoreCase("blockade")) {
            Country l_SourceCountry = d_GameModel.getD_Map().findCountryByName(l_InputCommandSplit[1]);
            getD_PlayerOrderQueue().add(new Blockade(this, l_SourceCountry));
        } else if (l_command.equalsIgnoreCase("airlift")) {
            Country l_SourceCountry = checkCountryBelongstoPlayer(l_InputCommandSplit[1]);
            Country l_TargetCountry = checkCountryBelongstoPlayer(l_InputCommandSplit[2]);
            int l_NumArmies1 = Integer.parseInt(l_InputCommandSplit[3]);
            getD_PlayerOrderQueue().add(new Airlift(this, l_SourceCountry, l_TargetCountry, l_NumArmies1));
        } else if (l_command.equalsIgnoreCase("negotiate")) {
            Player l_TempPlayer = findPlayerByName(l_InputCommandSplit[1]);
            getD_PlayerOrderQueue().add(new Negotiate(this, l_TempPlayer));
        }
//    	ArrayList<Order> orders = d_PlayerStrategy.createOrder();
//		for(Order order : orders)
//		{	if(order!=null)
//			d_PlayerOrderQueue.add(order);
//		}
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
