package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 *	Negotiate class implements the Order interface and overrides the execute method.
 * 	During IssueOrder phase, when player issues negotiate command, it is stored in the orders list.
 *  During the ExecuteOrder phase, execute method of this class is called.
 */
public class Negotiate implements Order {
    private Player d_SourcePlayer, d_TargetPlayer;

    /**
     * Constructor of the Negotiate class which is called when object is created.
     * @param p_SourcePlayer The player who issues the negotiate command.
     * @param p_TargetPlayer The Player with whom source player wants to have a negotiation.
     */
    public Negotiate(Player p_SourcePlayer, Player p_TargetPlayer) {
        setSourcePlayer(p_SourcePlayer);
        d_TargetPlayer = p_TargetPlayer;

    }

    /**
     * This method adds the logic for the diplomacy(negotiation).
     * If the source player owns Negotiate Card, then this method will make sure source and target players can not attack on each other during the turn.
     * This method also removes the Negotiate Card from the source player so that he can not use it again.
     */
    @Override
    public void execute() {
        /*If Player has negotiate card, then it will add target player to negotiatedPlayerList and then remove that card*/
        if(this.getSourcePlayer().getD_Cards().contains("Negotiate")) {
            this.getSourcePlayer().getD_NegotiatedPlayers().add(d_TargetPlayer);
            this.d_TargetPlayer.getD_NegotiatedPlayers().add(getSourcePlayer());
            getSourcePlayer().getD_Cards().remove("Negotiate");
            System.out.println("Negotiation with "+d_TargetPlayer.getD_PlayerName()+" successfull.");
        }
        else{
            System.out.println("\n"+getSourcePlayer().getD_PlayerName()+" does not own Negotiate Card for Diplomacy with "+d_TargetPlayer.getD_PlayerName());
            d_TargetPlayer.setD_SkipCommands(true);
			System.out.println("Skipping all the following commands of " + d_TargetPlayer.getD_PlayerName());

        }

    }

    /**
     * This method returns the source player who issues the Negotiate order.
     * @return Player who issued the negotiate command.
     */
    public Player getSourcePlayer() {
        return d_SourcePlayer;
    }

    /**
     * This method sets the player who issues the Negotiate order.
     * @param d_SourcePlayer Player who issues the negotiate order.
     */
    public void setSourcePlayer(Player d_SourcePlayer) {
        this.d_SourcePlayer = d_SourcePlayer;
    }


}