package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;
import com.soen6441.risk_game_u14.order.Advance;
import com.soen6441.risk_game_u14.order.Airlift;
import com.soen6441.risk_game_u14.order.Blockade;
import com.soen6441.risk_game_u14.order.Bomb;
import com.soen6441.risk_game_u14.order.Deploy;
import com.soen6441.risk_game_u14.order.Negotiate;

/**
 * This Strategy class belongs to the Human Player. It encapsulates the behavior of a Human Player.
 * This strategic players issues all types of orders on its own.
 *
 */
public class HumanPlayerStrategy extends Strategy implements Serializable {
	/**
	 * GameModel new object to get the current map.
	 */
	private GameModel d_GameModel;
	/**
	 * Player reference of this strategy
	 */
	Player d_Player;
	/**
	 * CheckArmies Hashmap to get if the player is still interested in issuing orders or wanted to quit.
	 */
	HashMap <Player,Boolean> d_CheckArmies;
	/**
	 * The boolean flag set to true if the player enters "quit" keyword, else false.
	 */
	boolean d_decreasePlayerListSize;
	/**
	 * The constructor initializes the human player and the game model new object.
	 * @param p_Player The Player whose strategy is Human.
	 * @param p_GameModel The Reference of GameModel to get the Map on which the match is to be played.
	 */
	
	
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
	
	
	public HumanPlayerStrategy(Player p_Player,GameModel p_GameModel)
	{
		this.d_GameModel = p_GameModel;
		d_Player = p_Player;
	}
	/**
	 * This is a setter method of the Checkarmies hashmap.
	 * @param l_CheckArmies Shows the current status of the players, i.e whether they are playing or are out of the game.
	 */
	public void setCheckArmies(HashMap<Player,Boolean> l_CheckArmies)
	{
		d_CheckArmies= l_CheckArmies;
	}
	/**
	 * This is the getter method for the Checkarmies hashmap.
	 * @return The Hashmap -  Checkarmies
	 */
	public HashMap<Player,Boolean> getCheckArmies()
	{
		return d_CheckArmies;
	}
	/**
	 * This is the getter method for the decreasePlayerListSize flag.
	 * @return true - if the player entered "quit" else false.
	 */
	public boolean getDecreasePlayerListSize()
	{
		return d_decreasePlayerListSize;
	}
	/**
	 * This is an overridden attack method. The human player enters the country of its own choice.
	 */
	@Override
	public ArrayList<Country> toAttack()
	{
		return null;
	}
	/**
	 * This is an overridden defend method. The human player enters the country it wants to deploy its armies to or attack from.
	 */
	@Override
	public Country toDefend() 
	{
		return null;
	}
	/**
	 * This in an overridden method to create an order. Based in the input order string entered by the human player. It creates a corresponding order.
	 * <ol><li> A deploy keyword would create an object of a Deploy order.</li>
	 * <li> An advance keyword would create an object of a Advance order.</li>
	 * <li> A bomb keyword would create an object of a Bomb order.</li>
	 * <li> A blockade keyword would create an object of a Blockade order.</li>
	 * <li> An airlift keyword would create an object of a Airlift order.</li>
	 * <li> A negotiate keyword would create an object of a Negotiate order.</li>
	 * </ol>
	 * Other than these 5 orders, if a player enters the command "quit", then the value of this player in checkArmies hashmap is changed to true
	 *  and the decreasePlayerListSize boolean is also set to true.
	 *  Or if a player enters the command "savegame", then the current game is saved in a txt filename mentioned by the player.
	 *  @return It returns the order object issued.
	 */
	@Override
	public ArrayList<Order> createOrder() {
		ArrayList<Order> l_ReturnedOrder =  new ArrayList<>();
		// TODO Auto-generated method stub
//		String l_StringOrder = JOptionPane.showInputDialog(d_Player.getD_PlayerName()+" : Please Enter Your Order");
	System.out.println("Enter "+d_Player.getD_PlayerName() +" Order");
      String l_InputCommand = "";
      Scanner sc = new Scanner(System.in);
      boolean l_CorrectCommand = false;
      while (!l_CorrectCommand) {

          String l_EnteredComamnd = sc.nextLine();

          if (isCommandValid(l_EnteredComamnd)) {
              l_CorrectCommand = true;
              l_InputCommand = l_EnteredComamnd;
          } else {
              System.out.println("Invalid Command!!");
          }
      }
		
      
     String l_StringOrder=l_InputCommand;
      
		d_decreasePlayerListSize = false;
		if("exit".equalsIgnoreCase(l_StringOrder))
		{
//			d_CheckArmies.put(d_Player, true);
			d_decreasePlayerListSize = true;
			d_Player.setIsExit(true);
			//--d_PlayerListSize;
		}

		else
		{
			int l_Flag = 0;
			String[] l_StringList = l_StringOrder.split(" ");
			String l_OrderType = l_StringList[0];

			d_Leb.setResult("in human player the command is not quit "+l_StringOrder);
			switch(l_OrderType) {

			case "deploy":
				if(l_StringList.length != 3)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
				int l_NumArmies = Integer.parseInt(l_StringList[2]);
				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[1]))
					{
						l_ReturnedOrder.add(new  Deploy(d_Player,l_TempCountry,l_NumArmies));
					}
				}
				break;
			case "advance" :
				if(l_StringList.length != 4)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
				int l_NumArmies1 = Integer.parseInt(l_StringList[3]);
				Country l_SourceCountry = null;
				Country l_TargetCountry = null;
				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[1]))
					{
						l_SourceCountry = l_TempCountry;
						break;
					}
				}
				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[2]))
					{
						l_TargetCountry = l_TempCountry;
						break;
					}
				}
				l_ReturnedOrder.add(new  Advance(d_Player,l_SourceCountry,l_TargetCountry,l_NumArmies1));
			case "bomb":
				if(l_StringList.length != 2)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[1]))
					{
						l_ReturnedOrder.add(new  Bomb(d_Player,l_TempCountry));
					}
				}break;
			case "blockade":
				if(l_StringList.length != 2)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[1]))
					{
						l_ReturnedOrder.add(new  Blockade(d_Player,l_TempCountry));

					}
				}
				break;
			case "airlift":
				if(l_StringList.length != 4)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
				int l_NumArmies2 = Integer.parseInt(l_StringList[3]);
				Country l_SourceCountry1 = null;
				Country l_TargetCountry1 = null;

				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[1]))
					{
						l_SourceCountry1 = l_TempCountry;
						break;
					}
				}
				for(Country l_TempCountry: d_GameModel.getD_Map().getD_CountryObjects() )
				{
					if(l_TempCountry.getD_CountryName().equals(l_StringList[2]))
					{
						l_TargetCountry1 = l_TempCountry;
						break;
					}
				}
				l_ReturnedOrder.add(new  Airlift(d_Player,l_SourceCountry1,l_TargetCountry1,l_NumArmies2));
			case "negotiate":
				if(l_StringList.length != 2)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
				for(Player l_TempPlayer : d_GameModel.getD_Players()) {
					if(l_TempPlayer.getD_PlayerName().equals(l_StringList[1])) {
						l_ReturnedOrder.add(new  Negotiate(d_Player, l_TempPlayer));
					}
				}

				break;

			case "savegame":
				if(l_StringList.length != 2)
				{
					d_Leb.setResult("Please enter valid number of parameters");
					break;
				}
//				d_GameModel.saveGame(l_StringList[1]);
				d_Leb.setResult("Saving the current Game");
//				d_Player.setSaveGame(true);
				return null;
			default:
				break;
			}
		}
		return l_ReturnedOrder;
	}
	/**
	 * This is an overridden method to provide the strategy of the player.
	 * @return a String specifying "Human" - strategy of this class.
	 */
	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Human";
	}

}
