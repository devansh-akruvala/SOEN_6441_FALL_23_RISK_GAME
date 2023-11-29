package com.soen6441.risk_game_u14.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.soen6441.risk_game_u14.log_observer_pattern.LogEntryBuffer;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Player;
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
	private HashMap<String, ArrayList<String>> d_TournamentResult;
	static int NUM=0;

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
        	case "tournament":
				d_LEB.setResult(l_Command);
				System.out.println(d_GamePhase.tournament("tournament",l_Command));

				break;

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

    
    
    
    public void tournament(String p_InputString) throws Exception {

		int l_M = 0;
		int l_P = 0;
		int l_G = 0;
		int l_D = 0;
		String[] l_MapList = null;
		String[] l_PlayerStrategyList=null;
		ArrayList<Map> l_Maps=new ArrayList<Map>();
		ArrayList<File> l_Files=new ArrayList<File>();
		String l_Path="saved_maps\\";
		String[] l_CommandArray = p_InputString.split(" ");
		if("-M".equals(l_CommandArray[1])){
			l_MapList=l_CommandArray[2].split(",");
			if(l_MapList.length>5||l_MapList.length<1) {
				d_LEB.setResult("Number of Maps should be in between 1 to 5 both inclusive");
				throw new Exception("Number of Maps should be in between 1 to 5 both inclusive");//throw exception
				
			} else 
				l_M=l_MapList.length;
			for(int i=0;i<l_M;i++) {
				File l_F = new File(l_Path+l_MapList[i]);
				if (!l_F.exists()) {
					d_LEB.setResult("File does not Exists");
					throw new Exception("File does not Exists");//throw exception
					
				} else
					l_Files.add(l_F);
			}
		}
		if("-P".equals(l_CommandArray[3])){
			l_PlayerStrategyList=l_CommandArray[4].split(",");
			if(l_PlayerStrategyList.length>4||l_PlayerStrategyList.length<2) {
				d_LEB.setResult("Number of Player strategies should be in between 2 to 4 both inclusive");
				throw new Exception("Number of Player strategies should be in between 2 to 4 both inclusive");//throw exception
				
			} else {
				l_P=l_PlayerStrategyList.length;
			}
		}
		if("-G".equals(l_CommandArray[5])){
			int l_NumGames=Integer.parseInt(l_CommandArray[6]);
			if(l_NumGames>5||l_NumGames<1) {
				d_LEB.setResult("Number of Games should be in between 1 to 5 both inclusive");
				throw new Exception("Number of Games should be in between 1 to 5 both inclusive");//throw exception
				
			} else
				l_G=l_NumGames;
		}
		if("-D".equals(l_CommandArray[7])){
			int l_MaxTurns=Integer.parseInt(l_CommandArray[8]);
			if(l_MaxTurns>50||l_MaxTurns<1) {
				d_LEB.setResult("Number of turns should be in between 10 to 50 both inclusive");
				throw new Exception("Number of turns should be in between 10 to 50 both inclusive");//throw exception
				
			} else
				l_D=l_MaxTurns;
		}
		 d_TournamentResult = new HashMap<>();

		for (int i = 0; i < l_M; i++) {
			System.out.println("\n=============================================\n");
			System.out.println("\nMap number:"+(i+1)+"\n");
			d_LEB.setResult("\n=============================================\n");
			d_LEB.setResult("\nMap number:"+(i+1)+"\n");
			ArrayList<String> l_Result = new ArrayList<>();

			for (int j = 0; j < l_G; j++) {
				System.out.println(l_MapList[i]);
				d_GameModel.getD_Map().loadFile(l_MapList[i]);
				System.out.println("\n=============================================\n");
				System.out.println("\nGame number:"+(j+1)+"\n");
				d_LEB.setResult("\n=============================================\n");
				d_LEB.setResult("\nGame number:"+(j+1)+"\n");
				
				d_GameModel.getD_Players().clear();

//				for(int k=0;k<l_P;k++) {
//					d_GameModel.addPlayers("Player"+(NUM++),l_PlayerStrategyList[k]);
//				}
				
				int firstStrategyIndex =new Random().nextInt(l_P);
				int secondStrategyIndex = firstStrategyIndex;
				while(firstStrategyIndex==secondStrategyIndex) {
					secondStrategyIndex=new Random().nextInt(l_P);
				}
				d_GameModel.addPlayers("Player_"+(NUM++)+l_PlayerStrategyList[firstStrategyIndex],l_PlayerStrategyList[firstStrategyIndex]);
				d_GameModel.addPlayers("Player_"+(NUM++)+l_PlayerStrategyList[secondStrategyIndex],l_PlayerStrategyList[secondStrategyIndex]);

				d_GameModel.tournamentstartUpPhase();
				int l_Noofturns=0;
				while(true)
				{
					d_GameModel.assignReinforcementArmies();
					this.getD_PlayerController().playerIssueOrder();
					this.getD_PlayerController().playerExecuteOrder();
					
//					if(this.getD_PlayerController().getWinner()!=null) {
//						l_Result.add(this.getD_PlayerController().getWinner().getD_PlayerStrategy().strategyName());
//						System.out.println(this.getD_PlayerController().getWinner().getD_PlayerName()+"is the winner");
//						d_LEB.setResult(this.getD_PlayerController().getWinner().getD_PlayerName()+"is the winner");
//						d_GameModel.getD_Map().reset();
//						break;
//					}
					if(d_PlayerController.checkTheWinner()==0) {
						System.out.println("Gameover ===>>>>>>>>"+d_PlayerController.getWinner());
						l_Result.add(d_PlayerController.getWinner().getD_PlayerStrategy().strategyName());
						d_GameModel.getD_Map().reset();
						break;
					}

					if(l_Noofturns == l_D) {
						System.out.println("\nMatch is draw");
						d_LEB.setResult("\nMatch is draw");
						l_Result.add("Draw");
						d_GameModel.getD_Players().clear();
						d_GameModel.getD_Map().reset();
						break;
					}
					l_Noofturns++;
				}
				d_TournamentResult.put(l_MapList[i],l_Result);
			}
		}
		printTournamentResult(l_M, l_G, l_D, d_TournamentResult, l_PlayerStrategyList);
	}


	/**
	 * This method prints the tournament result for each individual map and each individual game.
	 * @param p_M Number of maps
	 * @param p_G Number of games
	 * @param p_D Number of turns
	 * @param p_tournamentResult Result of the tournament
	 * @param p_PlayerStrategyList Player strategy list
	 */
	private void printTournamentResult(int p_M, int p_G, int p_D, HashMap<String, ArrayList<String>> p_tournamentResult,
			String[] p_PlayerStrategyList) {

		String[] l_MapStrings = p_tournamentResult.keySet().toArray(new String[p_tournamentResult.keySet().size()]);
		System.out.println("\n");
		System.out.println("\n");
		d_LEB.setResult("\n=============================================\n");
		System.out.println("=============================================\n");
		System.out.println("==============TOURNAMENT RESULT===============\n");
		d_LEB.setResult("==============TOURNAMENT RESULT===============\n");
		System.out.println("=============================================\n");
		d_LEB.setResult("\n=============================================\n");
		System.out.println("\n");
		StringBuffer l_MapNameString = new StringBuffer();
		for (String l_MapString : l_MapStrings) {
			l_MapNameString.append(l_MapString+ ",");
		}
		System.out.println("\n");
		d_LEB.setResult("\n=============================================\n");
		System.out.println("M:" + l_MapNameString);
		d_LEB.setResult("M:" + l_MapNameString);
		StringBuffer stratergiesNameString = new StringBuffer();
		for (String aP_PlayerStrategyList : p_PlayerStrategyList) {
			stratergiesNameString.append(aP_PlayerStrategyList + ",");
		}
		System.out.println("P:" + stratergiesNameString);
		d_LEB.setResult("P:" + stratergiesNameString);
		System.out.println("G:" + p_G);
		d_LEB.setResult("G:" + p_G);
		System.out.println("D:" + p_D);
		d_LEB.setResult("D:" + p_D);
		System.out.println("\n");
		d_LEB.setResult("\n");
		System.out.println("\n");
		d_LEB.setResult("\n");
		StringBuilder l_StringBuilder = new StringBuilder();
		l_StringBuilder.append("|");
		l_StringBuilder.append(getFormattedString(" "));
		for (int i = 0; i < p_G; i++) {
			l_StringBuilder.append("|");
			l_StringBuilder.append(getFormattedString("Game " + (i + 1)));
		}
		l_StringBuilder.append("|");
		System.out.println("\n");
		d_LEB.setResult("\n");
		System.out.println(l_StringBuilder.toString());
		d_LEB.setResult(l_StringBuilder.toString());
		System.out.println("\n");
		d_LEB.setResult("\n");
		for (String l_MapString : l_MapStrings) {

			StringBuilder l_SbMap = new StringBuilder();
			l_SbMap.append("|");
			l_SbMap.append(getFormattedString(l_MapString));

			ArrayList<String> l_GameResults = p_tournamentResult.get(l_MapString);
			for (int j = 0; j < p_G; j++) {
				l_SbMap.append("|");
				l_SbMap.append(getFormattedString(l_GameResults.get(j)));
			}
			System.out.println("\n");
			l_SbMap.append("|");
			System.out.println(l_SbMap.toString());
			d_LEB.setResult(l_SbMap.toString());

		}
	}

	private String getFormattedString(String p_Input) {
		int l_Length = 4;

		StringBuilder l_Str = new StringBuilder(" " + p_Input);
		for (int l_I = p_Input.length(); l_I <= l_Length; l_I++)
			l_Str.append(" ");
		return l_Str.toString();
	}
    
}
