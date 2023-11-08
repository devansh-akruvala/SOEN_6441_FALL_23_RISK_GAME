package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 * The Negotiate class implements the Order interface and is responsible for executing negotiation commands between players in the game.
 * During the Issue Order phase, when a player issues a negotiate command, it is stored in the orders list.
 * During the Execute Order phase, the execute method of this class is invoked.
 * @author Aditya Gupta
 */
public class Negotiate implements Order {
    private Player d_SourcePlayer, d_TargetPlayer;

    /**
     * Constructs a Negotiate object.
     *
     * @param p_SourcePlayer The player initiating the negotiation.
     * @param p_TargetPlayer The player with whom the source player wishes to negotiate.
     */
    public Negotiate(Player p_SourcePlayer, Player p_TargetPlayer) {
        setSourcePlayer(p_SourcePlayer);
        d_TargetPlayer = p_TargetPlayer;

    }

    /**
     * Implements the logic for diplomacy through negotiation between players.
     * If the source player owns a Negotiate Card, this method ensures that the source and target players cannot attack each other during the turn.
     * Additionally, it removes the Negotiate Card from the source player, preventing its reuse.
     */
    @Override
    public void execute() {
        /*If Player has negotiate card, then it will add target player to negotiatedPlayerList and then remove that card*/
        if(this.getSourcePlayer().getD_Cards().contains("Negotiate")) {
            this.getSourcePlayer().getD_NegotiatedPlayers().add(d_TargetPlayer);
            this.d_TargetPlayer.getD_NegotiatedPlayers().add(getSourcePlayer());
            getSourcePlayer().getD_Cards().remove("Negotiate");
            d_SourcePlayer.setD_Result("Negotiation with "+d_TargetPlayer.getD_PlayerName()+" successfull.");
        }
        else{
            d_SourcePlayer.setD_Result("\n"+getSourcePlayer().getD_PlayerName()+" does not own Negotiate Card for Diplomacy with "+d_TargetPlayer.getD_PlayerName());
            d_SourcePlayer.setD_SkipCommands(true);
			d_SourcePlayer.setD_Result(d_SourcePlayer.getD_Result()+"\nSkipping all the following commands of " + d_SourcePlayer.getD_PlayerName());

        }

    }

    /**
     * Retrieves the player who initiated the Negotiate order.
     *
     * @return The player initiating the negotiate command.
     */
    public Player getSourcePlayer() {
        return d_SourcePlayer;
    }

    /**
     * Sets the player initiating the Negotiate order.
     *
     * @param d_SourcePlayer The player initiating the negotiate order.
     */
    public void setSourcePlayer(Player d_SourcePlayer) {
        this.d_SourcePlayer = d_SourcePlayer;
    }


}