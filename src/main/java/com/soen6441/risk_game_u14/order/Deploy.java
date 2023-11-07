package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

public class Deploy implements Order {

	private Player d_Player;
	private Country d_Country;
	private int d_NumArmies;

	/**
	 * Constructor which is created on creation of the object
	 * 
	 * @param p_Player    Player who issues the deploy order.
	 * @param p_Country   Country on which the player issues deploy order.
	 * @param p_NumArmies Number of armies to be deployed on the given country.
	 */
	public Deploy(Player p_Player, Country p_Country, int p_NumArmies) {
		d_Player = p_Player;
		d_Country = p_Country;
		d_NumArmies = p_NumArmies;
	}

	public void execute() {
		if (isValid()) {
			d_Player.setD_ArmiesCount(d_Player.getD_ArmiesCount() - d_NumArmies);
			d_Country.setD_NoOfArmies(d_Country.getD_NoOfArmies() + d_NumArmies);
		} else {
			d_Player.setD_SkipCommands(true);
			System.out.println("Skipping all the following commands of " + d_Player.getD_PlayerName());
		}
	}

	public boolean isValid() {
		if (d_NumArmies <= d_Player.getD_ArmiesCount()) {
			if (d_Country != null) {
				System.out.println("\n" + d_Player.getD_PlayerName() + ": Deployed " + d_NumArmies + " to "
						+ d_Country.getD_CountryName() + " was successful");
				return true;
			} else {
				System.out.println("\n"+ d_Player.getD_PlayerName() +"This country  doesnot belongs to "
						+ d_Player.getD_PlayerName());
				return false;
			}
		} else {
			System.out.println("\n" + d_Player.getD_PlayerName() + "Player doesnot have enough armies");
			return false;
		}

	}
}
