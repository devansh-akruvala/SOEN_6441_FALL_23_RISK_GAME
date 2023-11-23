package com.soen6441.risk_game_u14.controller;

import java.util.Scanner;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.state.Edit;
import com.soen6441.risk_game_u14.state.Phase;

/**
 * The GameEngine Class Controls the Phases of Game
 * <ul>
 * <li>There are 3 phases
 *
 * </ul>
 *
 * @author Devansh, Meshva, Gowtham
 */

public class GameEngine {

    private GameModel d_GameModel;
    private MapController d_MapController;
    private PlayerController d_PlayerController;
    int l_PhaseController;
    private LogEntryBuffer d_LEB;
    private Phase d_GamePhase;

    public GameEngine() {

    }

    /***
     * This is the main game engine constructor method which is responsible for
     * controlling the map and player controllers and the game and map models in
     * case of assign-counties.
     *
     * @param p_GameModel instance for a game
     */
    public GameEngine(GameModel p_GameModel) {
        d_GameModel = p_GameModel;
        d_MapController = new MapController(p_GameModel.getD_Map());
        d_PlayerController = new PlayerController(p_GameModel);
        l_PhaseController = 1;
        d_LEB = new LogEntryBuffer();
        // initial phase will be edit phase hardcoding it
        setD_GamePhase(new Edit(this));
    }

    public GameModel getD_GameModel() {
        return d_GameModel;
    }

    public void setD_GameModel(GameModel d_GameModel) {
        this.d_GameModel = d_GameModel;
    }

    public MapController getD_MapController() {
        return d_MapController;
    }

    public void setD_MapController(MapController d_MapController) {
        this.d_MapController = d_MapController;
    }

    public PlayerController getD_PlayerController() {
        return d_PlayerController;
    }

    public void setD_PlayerController(PlayerController d_PlayerController) {
        this.d_PlayerController = d_PlayerController;
    }

    public LogEntryBuffer getD_LEB() {
        return d_LEB;
    }

    public void setD_LEB(LogEntryBuffer d_LEB) {
        this.d_LEB = d_LEB;
    }

    public Phase getD_GamePhase() {
        return d_GamePhase;
    }

    public void setD_GamePhase(Phase d_GamePhase) {
        this.d_GamePhase = d_GamePhase;
    }

    /***
     * This method is responsible for mapping the inputs from the user interface to
     * the game engine
     *
     * @throws Exception handles the exception at startup phase during
     *                   assign-countries where the number of players is only one
     *                   Or, more than the total number of countries
     */
    public void listenCommand() throws Exception {
        showCommands();

        
        Scanner l_ScannerObj = new Scanner(System.in);
        boolean l_StopGame = false;
        while (!l_StopGame) {
        	System.out.println("\nIn " + d_GamePhase.getPhaseName());
            System.out.println("----------------------------------------");
            System.out.println("Waiting for Input:");
        	String l_Command = l_ScannerObj.nextLine();
            d_LEB.setResult("Command Entered: " + l_Command);
            String l_CommandSection = l_Command.split(" ")[0];

            switch (l_CommandSection) {

                case "editcontinent":
                    String result = d_GamePhase.editContinent(l_Command);
                    System.out.println("Output: " + result);
                    d_LEB.setResult(result);
                    break;
                case "editcountry":
                    String result1 = "Output: " + d_GamePhase.editCountry(l_Command);
                    System.out.println(result1);
                    d_LEB.setResult(result1);
                    break;
                case "editneighbor":

                    String result2 = "Output: " + d_GamePhase.editNeighbor(l_Command);
                    System.out.println(result2);
                    d_LEB.setResult(result2);
                    break;
                case "editmap":
                    String result3 = "Output: " + d_GamePhase.editMap(l_Command);
                    System.out.println(result3);
                    d_LEB.setResult(result3);
                    break;
                case "showmap":
                    d_GamePhase.showMap();
                    d_LEB.setResult("Executed Showmap");
                    break;
                case "savemap":
                    String result4 = "Output: " + d_GamePhase.saveMap(l_Command);
                    System.out.println(result4);
                    d_LEB.setResult(result4);
                    break;
                case "loadmap":
                    String result5 = "Output: " + d_GamePhase.loadMap(l_Command);
                    System.out.println(result5);
                    d_LEB.setResult(result5);
                    break;
                case "validatemap":
                    String result6 = "Output: " + d_GamePhase.validateMap();
                    System.out.println(result6);
                    d_LEB.setResult(result6);
                    break;
                case "gameplayer":

                    String result7 = "Output: " + d_GamePhase.addPlayers(l_Command);
                    System.out.println(result7);
                    d_LEB.setResult(result7);
                    break;

                case "assigncountries":
                    String result8 =d_GamePhase.assignCountries();
                    System.out.println(result8);
                    d_LEB.setResult(result8);
                    break;
                case "quit":
                    l_StopGame = !l_StopGame;
                    l_ScannerObj.close();
                    break;
                default:
                    System.out.println("Output: Invalid Command!!");
                    d_LEB.setResult("Output: Invalid Command!!");
            }
        }
    }

    /***
     * This method lists the valid game commands to the user
     */
    public void showCommands() {
        System.out.println(
                "Edit Phase Command:\n\n" + "editcontinent -add continentID continentvalue -remove continentID\r\n"
                        + "editcountry -add countryID continentID -remove countryID\r\n"
                        + "editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID\n"
                        + "savemap filename\r\n" + "editmap filename\r\n" + "validatemap\r\n"
                        + "loadmap filename\r\n" +
                        "----------------------------------------------\n" + 
                        "StartUp Phase Commands:\n\n"+
                        "gameplayer -add playername -remove playername\r\n"
                        + "assigncountries\r\n" 
                        + "----------------------------------------------\n"
                        + "In Issue Order Phase Command\n\n" 
                        + "deploy countryID numarmies\r\n"
                        + "advance countrynamefrom countynameto numarmies\r\n"
                        + "bomb countryID (requires bomb card)\r\n"
                        + "blockade countryID (required blockade card)\r\n"
                        + "airlift sourcecountryID targetcountryID numarmies (requires the airlift card)\r\n"
                        + "negotiate playerID (requires the diplomacy card)\n");
        System.out.println("*********************************************************************************************************\n");

    }

}
