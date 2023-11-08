package com.soen6441.risk_game_u14.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.log_observer_pattern.Logger;
import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/***
 * This class adds players to the game and randomly assigns countries amongst
 * players It also allocates armies to the players according to the WarZone
 * rules Each player is asked to enter their orders in a round-robin fashion
 * until their reinforcements are exhausted
 *
 * @author Devansh, Aditya
 */
public class PlayerController {
    private GameModel d_GameModel;
    private HashMap<Integer, String> d_AllCards;
    private Random d_Rand;
    private LogEntryBuffer d_LEB;

    public PlayerController() {

    }

    /***
     * Constructor for initializing the game
     *
     * @param d_GameModel model object
     */
    public PlayerController(GameModel d_GameModel) {
        this.d_GameModel = d_GameModel;
        d_AllCards = new HashMap<>();
        d_AllCards.put(0, "Bomb");
        d_AllCards.put(1, "Blockade");
        d_AllCards.put(2, "Negotiate");
        d_AllCards.put(3, "Airlift");
        d_Rand = new Random();
        d_LEB = new LogEntryBuffer();
    }

    /***
     * Adds the players to the game
     *
     * @param p_Command pass the input command from the user
     * @return the outcome of the command
     */
    public String gamePlayerCommand(String p_Command) {
        String l_CommandSplit[] = p_Command.split(" ");
        int l_CommandSplitLength = l_CommandSplit.length;

        if (l_CommandSplit[0].equalsIgnoreCase("gameplayer")) {
            int l_WordIndex = 1;
            while (l_WordIndex < l_CommandSplitLength) {
                if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-add") && l_WordIndex + 1 < l_CommandSplitLength) {
                    try {
                        d_GameModel.addPlayers(l_CommandSplit[l_WordIndex + 1]);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 2;
                } else if (l_CommandSplit[l_WordIndex].equalsIgnoreCase("-remove")
                        && l_WordIndex + 1 < l_CommandSplitLength) {
                    try {
                        d_GameModel.removePlayers(l_CommandSplit[l_WordIndex + 1]);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    l_WordIndex += 2;
                } else {
                    // throw new Exception("Invalid Command");
                    return "Invalid Command";
                }
            }
            return "Players command executed successfully";
        } else {
            return "Invalid Command";
        }

    }

    /***
     * Ask orders from the players in a round-robin fashion and store them in an
     * order queue
     * @param number The integer number to be parsed
     * @return A boolean value
     */

    public boolean isIntParsable(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            System.out.println("Enter Integer Value");
            return false;
        }
    }
    /**
     * Checks if a country exists.
     *
     * @param p_CountryName The name of the country to be checked.
     * @return True if the country exists; otherwise, false.
     */
    public boolean isCountryExist(String p_CountryName) {
        boolean l_isExist = d_GameModel.getD_Map().countryAlreadyExist(p_CountryName);
        if (l_isExist)
            return true;
        else {
            System.out.println("Country " + p_CountryName + " Doesn't Exist");
            return false;
        }
    }
    /**
     * Checks if a player exists in the game.
     *
     * @param p_name The name of the player being searched for.
     * @return True if the player exists; otherwise, false.
     */
    public Boolean isPlayerExist(String p_name) {
        for (Player l_tp : d_GameModel.getD_Players()) {
            if (l_tp.getD_PlayerName().equalsIgnoreCase(p_name)) {
                return true;
            }
        }
        System.out.println("Player with " + p_name + "Doesnot Exist");
        return false;
    }
    /**
     * Checks if a command entered by a player is valid.
     *
     * @param p_Command The command entered by the player.
     * @return True if the command is valid; otherwise, false.
     */
    public boolean isCommandValid(String p_Command) {

        String l_CommandSplit[] = p_Command.split(" ");
        int l_CommandLength = l_CommandSplit.length;

        if (l_CommandSplit[0].equalsIgnoreCase("deploy") && l_CommandLength == 3 && isCountryExist(l_CommandSplit[1])
                && isIntParsable(l_CommandSplit[2])) {
            return true;
        } else if (l_CommandSplit[0].equalsIgnoreCase("advance") && l_CommandLength == 4
                && isCountryExist(l_CommandSplit[1])
                && isCountryExist(l_CommandSplit[2]) && isIntParsable(l_CommandSplit[3])) {
            return true;
        } else if (l_CommandSplit[0].equalsIgnoreCase("bomb") && l_CommandLength == 2
                && isCountryExist(l_CommandSplit[1])) {
            return true;
        } else if (l_CommandSplit[0].equalsIgnoreCase("blockade") && l_CommandLength == 2
                && isCountryExist(l_CommandSplit[1])) {
            return true;
        } else if (l_CommandSplit[0].equalsIgnoreCase("airlift") && l_CommandLength == 4
                && isCountryExist(l_CommandSplit[1]) && isCountryExist(l_CommandSplit[2])
                && isIntParsable(l_CommandSplit[3])) {
            return true;
        } else if (l_CommandSplit[0].equalsIgnoreCase("negotiate") && l_CommandLength == 2
                && isPlayerExist(l_CommandSplit[1])) {
            return true;
        } else if (l_CommandSplit[0].equalsIgnoreCase("exit")) {
            return true;
        }
        return false;
    }
    /**
     * Manages the issuance of orders by players during the game.
     * This method handles the order issuing process for players in the game.
     */
    public void playerIssueOrder() {
        // loop through player
        // ask for order
        // check order format
        // check country exist
        // check country belongs to player
        // if everything is ok then add player order to his queue;

        List<Player> l_PlayerList = d_GameModel.getD_Players();

        Map<Player, Boolean> l_IsPlayerExit = new HashMap<>();
        for (Player l_Player : l_PlayerList) {
            l_Player.setD_SkipCommands(false);
            l_IsPlayerExit.put(l_Player, false);
        }

        boolean l_AllPlayerDone = false;

        while (!l_AllPlayerDone) {

            l_AllPlayerDone = true;
            for (Player l_Player : l_PlayerList) {

                if (!l_Player.getD_PlayerName().equalsIgnoreCase("Neutral Player")) {

                    if (l_IsPlayerExit.get(l_Player) == false) {

                        l_AllPlayerDone = false;
                        System.out.print(l_Player.getD_PlayerName() + "'s turn: ");

                        int l_PlayerArmies = l_Player.getD_ArmiesCount();
                        boolean l_CorrectCommand = false;

                        // if correct command then dongt ask again
                        String l_InputCommand = "";
                        Scanner sc = new Scanner(System.in);

                        while (!l_CorrectCommand) {

                            String l_EnteredComamnd = sc.nextLine();

                            if (isCommandValid(l_EnteredComamnd)) {
                                l_CorrectCommand = true;
                                l_InputCommand = l_EnteredComamnd;
                            } else {
                                System.out.println("Invalid Command!!");
                            }
                        }
                        if (l_InputCommand.equalsIgnoreCase("exit")) {
                            l_IsPlayerExit.put(l_Player, true);
                        } else {
                            d_LEB.setResult(l_InputCommand);
                            l_Player.issueOrder(l_InputCommand);
                        }

                    }
                }
            }

        }
    }

    /***
     * Executes the orders from the queue in FIFO order
     */
    public void playerExecuteOrder() {
        System.out.println("Executing Orders:");
        List<Player> l_PlayerList = d_GameModel.getD_Players();
        boolean l_AllPlayerDone = false;
        while (!l_AllPlayerDone) {
            l_AllPlayerDone = true;
            for (Player l_Player : l_PlayerList) {
                if (!l_Player.getD_PlayerName().equalsIgnoreCase("Neutral Player")) {
                    if (l_Player.getD_PlayerOrderQueue().size() > 0 && l_Player.getD_SkipCommands() == false) {
                        l_AllPlayerDone = false;
                        Order l_nextOrder = l_Player.nextOrder();
                        l_nextOrder.execute();
                        d_LEB.setResult(l_Player.getD_PlayerName() + "'s Order Result : " + l_Player.getD_Result());
                        System.out.println(l_Player.getD_PlayerName() + "'s Order Result : " + l_Player.getD_Result());
                    } else if (l_Player.getD_PlayerOrderQueue().size() > 0 && l_Player.getD_SkipCommands() == true) {
                        d_LEB.setResult(l_Player.getD_PlayerName() + "'s Order Result : Order Skipped");
                        System.out.println(l_Player.getD_PlayerName() + "'s Order Result : Order Skipped");
                    }
                }
            }
        }
        // Assigning cards to players that have won a battle in this round.
        for (Player l_TempPlayer : l_PlayerList) {
            if (l_TempPlayer.isD_AtleastOneBattleWon()) {
                int l_cardInteger = d_Rand.nextInt(4);

                String l_TempCard = d_AllCards.get(l_cardInteger);
                l_TempPlayer.setCard(l_TempCard);
                System.out.println("Player " + l_TempPlayer.getD_PlayerName() + " got " + l_TempCard);
                d_LEB.setResult("Player " + l_TempPlayer.getD_PlayerName() + " got " + l_TempCard);
                l_TempPlayer.setD_AtleastOneBattleWon(false);
            }
        }
        System.out.println("\nOrders are Succesfully Executed!!");
        d_LEB.setResult("\nOrders are Succesfully Executed!!");
        clearNegotiatedPlayerList();
        removePlayerWithNoCountry();

    }

    /**
     * This method is used to remove the player with no countries on its name.
     */
    public void removePlayerWithNoCountry() {
        Iterator<Player> l_PlayerIterator = d_GameModel.getD_Players().iterator();
        while (l_PlayerIterator.hasNext()) {
            Player l_TempPlayer = (Player) l_PlayerIterator.next();
            if (l_TempPlayer.getD_PlayerOwnedCountries().size() <= 0 && !l_TempPlayer.getD_PlayerName().equals("Neutral Player")) {
                l_PlayerIterator.remove();
            }
        }
    }

    /**
     * This method is used to clear all the individual players' negotiated players list after each round of issuing and execution of orders.
     */
    public void clearNegotiatedPlayerList() {

        for (Player l_TempPlayer : d_GameModel.getD_Players()) {
            l_TempPlayer.removeNegotiatedPlayer();

        }

    }

    /**
     * This method is used to check if one player owns all the countries of the map and hence can be declared as the winner of the game.
     * @return An integer value if a player wins the game
     */
    public int checkTheWinner() {
        ArrayList<Country> l_CountryList = d_GameModel.getD_Map().getD_CountryObjects();
        Iterator<Country> itr = l_CountryList.iterator();
        Player l_CheckPlayer = (Player) ((Country) itr.next()).getD_Owner();
        int l_flag = 0;
        while (itr.hasNext()) {
            if (!((Player) ((Country) itr.next()).getD_Owner() == l_CheckPlayer)) {
                l_flag = 1;
                break;
            }
        }
        if (l_flag == 0) {
            System.out.println("\n" + l_CheckPlayer.getD_PlayerName() + " is the winner of the game!");
            d_LEB.setResult("\n" + l_CheckPlayer.getD_PlayerName() + " is the winner of the game!");
        }
        return l_flag;
    }

    /***
     * This is the display method for the game map
     */
    public void show() {
        d_GameModel.showplayer();
    }

}
