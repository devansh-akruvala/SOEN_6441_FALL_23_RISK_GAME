package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

import java.util.Iterator;

/**
 * The Blockade class implements the Order interface and overrides the execute method.
 * It is used when a player issues a blockade command.
 */
public class Blockade implements Order {
    private Player d_Player;
    private Country d_Country;
    private GameModel d_GameObj;

    /**
     * Constructor called when creating a Blockade object.
     *
     * @param p_Player      The player issuing the blockade card.
     * @param p_TempCountry The country on which the blockade is applied.
     */
    public Blockade(Player p_Player, Country p_TempCountry) {
        setPlayer(p_Player);
        d_Country = p_TempCountry;
        this.d_GameObj = p_Player.getD_GameModel();
    }

    /**
     * This method is called during the execute order phase.
     * If the blockade order is valid, it triples the number of armies in the
     * country and makes it neutral.
     * It also removes the blockade card from the player's inventory.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_Country.setD_NoOfArmies(d_Country.getD_NoOfArmies() * 3);
            d_Country.getD_Owner().getD_PlayerOwnedCountries().remove(d_Country);
            for (Player l_Player : d_GameObj.getD_Players()) {
                if (l_Player.getD_PlayerName().equals("Neutral Player")) {
                    d_Country.setD_Owner(l_Player);
                    l_Player.setD_ArmiesCount(l_Player.getD_ArmiesCount() + d_Country.getD_NoOfArmies());
                    l_Player.addCountry(d_Country);
                }
            }
            d_Player.setD_Result(d_Player.getD_PlayerName() + ": Blockade was Success!!");
            getPlayer().getD_Cards().remove("Blockade");
        } else {
            d_Player.setD_SkipCommands(true);
            d_Player.setD_Result(d_Player.getD_Result() + "\nSkipping all the following commands of " + d_Player.getD_PlayerName());
        }

    }

    /**
     * Checks the validity of the blockade order issued by the player.
     * Returns false if the country doesn't belong to the player or the player does
     * not have a blockade card.
     *
     * @return true if the order is valid, otherwise false.
     */
    public boolean isValid() {

        if (!getPlayer().getD_Cards().contains("Blockade")) {
            d_Player.setD_Result("Player does not have a blockade card");
            return false;
        }

        if (d_Country != null) {
            d_Player.setD_Result("The blockade was successfull");
            return true;
        } else {
            d_Player.setD_Result("\nThis country " + d_Country.getD_CountryName() + " doesnot belongs to "
                    + getPlayer().getD_PlayerName());
            return false;
        }
    }

    /**
     * Returns the player who issued the blockade order.
     *
     * @return the player who issued the blockade order.
     */
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
