package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;
/**
 * The Deploy class implements the Order interface and overrides the execute method.
 * An instance of this class is created when a player issues a Deploy order.
 */
public class Deploy implements Order {

	private Player d_Player;
	private Country d_Country;
	private int d_NumArmies;

	/**
	 * Constructor for the Deploy class, called when creating an object.
	 * 
	 * @param p_Player  The player issuing the deploy order.
	 * @param p_Country  The country on which the player is deploying armies.
	 * @param p_NumArmies The number of armies to be deployed on the given
	 *                       country.
	 */
	public Deploy(Player p_Player, Country p_Country, int p_NumArmies) {
		d_Player = p_Player;
		d_Country = p_Country;
		d_NumArmies = p_NumArmies;
	}

	/**
	 * This method verifies the validity of the Deploy order and then assigns the
	 * specified number of armies to the country.
	 */

	public void execute() {
		if (isValid()) {
			d_Player.setD_ArmiesCount(d_Player.getD_ArmiesCount() - d_NumArmies);
			d_Country.setD_NoOfArmies(d_Country.getD_NoOfArmies() + d_NumArmies);
		} else {
			d_Player.setD_SkipCommands(true);
			System.out.println("Skipping all the following commands of " + d_Player.getD_PlayerName());
		}
	}


	/**
	 * This method checks the validity of the issued deploy order.
	 * It returns false if the country does not belong to the player or if the
	 * player doesn't have enough armies.
	 * 
	 * @return true if the order is valid; otherwise, false.
	 */

	public boolean isValid() {
		if (d_NumArmies <= d_Player.getD_ArmiesCount()) {
			if (d_Country != null) {
				System.out.println("\n" + d_Player.getD_PlayerName() + ": Deployed " + d_NumArmies + " to "
						+ d_Country.getD_CountryName() + " was successful");
				return true;
			} else {
				System.out.println("\n"+ d_Player.getD_PlayerName() +": This country  doesnot belongs to you");
				return false;
			}
		} else {
			System.out.println("\n" + d_Player.getD_PlayerName() + "Player doesnot have enough armies");
			return false;
		}

	}
}
