package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 * Bomb class implements the Order interface and overrides the execute method.
 * The object of this class is created when a player issues Bomb order.
 */
public class Bomb implements Order {
    private Player d_Player;
    private Player d_PlayerBombed;
    private Country d_Country;
    private int d_NoOfArmies;

    /**
     * Constructor which is called when a new object of Bomb class is created.
     *
     * @param p_Player      The player who issues the bomb order.
     * @param p_TempCountry The country on which the bomb to be dropped, if valid.
     */
    public Bomb(Player p_Player, Country p_TempCountry) {
        this.setPlayer(p_Player);
        this.d_Country = p_TempCountry;
        setBombedPlayer();
    }

    /**
     * This method sets the player whose country is going to be bombed.
     */
    private void setBombedPlayer() {
        this.d_PlayerBombed = d_Country.getD_Owner();
    }

    /**
     * This method checks the validity of the Bomb order and execute if it passes.
     * If the order is valid, it will half the number of armies on the country which is bombed.
     * This method removes the bomb card from the player's card list so that can't be used again.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_NoOfArmies = d_Country.getD_NoOfArmies() / 2;
            int l_NoOfArmiesReduced = d_Country.getD_NoOfArmies() - d_NoOfArmies;
            d_Country.setD_NoOfArmies(d_Country.getD_NoOfArmies() - d_NoOfArmies);
            d_PlayerBombed.setD_ArmiesCount(d_PlayerBombed.getD_ArmiesCount() - l_NoOfArmiesReduced);
            d_Player.getD_Cards().remove("Bomb");
        } else {
            d_Player.setD_SkipCommands(true);
            d_Player.setD_Result(d_Player.getD_Result() + "\nSkipping all the following commands of " + d_Player.getD_PlayerName());
        }
    }

    /**
     * This method checks the validity of the Bomb order.
     * It returns false if country is owned by negotiated player, player does not have bomb card.
     * It also returns false if country is owned by the same player who issues the bomb order.
     * it returns false if country to be bombed is not a neighbor country.
     *
     * @return true if Bomb order is valid, else false.
     */
    public boolean isValid() {
        if (d_Player.getD_NegotiatedPlayers().size() > 0) {
            d_Player.setD_Result("Player is a negotiated player");
            return false;
        }
        if (!d_Player.getD_Cards().contains("Bomb")) {
            d_Player.setD_Result("Player does not have a bomb card");
            return false;
        }

        if (d_Player.getD_PlayerOwnedCountries().contains(d_Country)) {
            d_Player.setD_Result("Player cannot bomb its own country");
            return false;
        }
        int l_Flag = 0;
        for (Country l_Country : d_Player.getD_PlayerOwnedCountries()) {
            if (l_Country.getD_Neighbors().contains(d_Country.getD_CountryName())) {
                l_Flag = 1;
            }

        }

        if (l_Flag == 0) {
            d_Player.setD_Result("The bombing country is not a neighbour of player");
            return false;
        } else {
            d_Player.setD_Result("The country is bombed");
            return true;
        }


    }

    /**
     * This method returns the player who issues the Bomb order
     *
     * @return The player who issues the bomb order
     */
    public Player getPlayer() {
        return d_Player;
    }

    /**
     * Sets the player who issues the bomb order.
     *
     * @param d_Player Player who issues the bomb order.
     */
    public void setPlayer(Player d_Player) {
        this.d_Player = d_Player;
    }
}
