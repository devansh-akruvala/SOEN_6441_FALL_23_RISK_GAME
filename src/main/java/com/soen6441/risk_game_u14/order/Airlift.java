package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 * The Airlift class implements the Order interface and contains the
 * execute method for processing airlift orders issued by a player.
 *
 * @author karandeepsingh
 */
public class Airlift implements Order {
    Player d_Player;
    Country d_SourceCountry, d_TargetCountry;
    int d_NumArmies;

    /**
     * Constructor for creating instances of the Airlift class.
     *
     * @param p_Player        The player who issues the airlift order.
     * @param p_SourceCountry The country from which armies are to be airlifted.
     * @param p_TargetCountry The country to which armies are being sent.
     * @param p_NumArmies     The number of armies to airlift from one country to another.
     */
    public Airlift(Player p_Player, Country p_SourceCountry, Country p_TargetCountry, int p_NumArmies) {
        d_Player = p_Player;
        d_SourceCountry = p_SourceCountry;
        d_TargetCountry = p_TargetCountry;
        d_NumArmies = p_NumArmies;
    }

    /**
     * Executes the airlift order, moving armies from one country to another, provided the order is valid.
     * The method also removes the "Airlift" card from the player's cards.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_SourceCountry.setD_NoOfArmies(d_SourceCountry.getD_NoOfArmies() - d_NumArmies);
            d_TargetCountry.setD_NoOfArmies(d_TargetCountry.getD_NoOfArmies() + d_NumArmies);
            d_Player.getD_Cards().remove("Airlift");
        } else {
            d_Player.setD_SkipCommands(true);
            d_Player.setD_Result(d_Player.getD_Result() + "\nSkipping all the following commands of " + d_Player.getD_PlayerName());
        }

    }

    /**
     * This function evaluates the Airlift order's validity.
     *
     * @return true if the order is valid;
     * @return false, taking into account conditions such as the source and target countries being the same, the player lacking an airlift card
     * @return false, if the source country having less than one remaining army.
     */

    public boolean isValid() {
        if (!d_Player.getD_Cards().contains("Airlift")) {
            d_Player.setD_Result("Player does not have a Airlift card");
            return false;
        }
        if (d_SourceCountry == d_TargetCountry) {
            d_Player.setD_Result("The source country and target country cannot be same!");
            return false;
        } else if (d_SourceCountry.getD_NoOfArmies() - d_NumArmies < 1) {
            d_Player.setD_Result("The source country should be left with at least one army!");
            return false;
        } else {
            if (d_Player.getD_PlayerOwnedCountries().contains(d_SourceCountry) && d_Player.getD_PlayerOwnedCountries().contains(d_TargetCountry)) {
                d_Player.setD_Result("The source country and target country belong to the same player");
                return true;
            } else {
                d_Player.setD_Result("You can only airlift armies to your own countries. " + d_TargetCountry.getD_CountryName() + " does not belongs to " + d_Player.getD_PlayerName());
                return false;
            }
        }
    }

    public Player getPlayer() {
        return d_Player;
    }

    /**
     * Sets the player who issues the blockade order.
     *
     * @param d_Player The player who issues the blockade order.
     */
    public void setPlayer(Player d_Player) {
        this.d_Player = d_Player;
    }
}