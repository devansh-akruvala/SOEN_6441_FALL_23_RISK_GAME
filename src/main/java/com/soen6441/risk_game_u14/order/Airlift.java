package com.soen6441.risk_game_u14.order;

import com.soen6441.risk_game_u14.model.Country;			import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 * Airlift class implements the Order interface and overrides the execute method.
 * The object of this method is created when the player issues airlift order.
 */
public class Airlift implements Order {
    Player d_Player;
    Country d_SourceCountry,d_TargetCountry;
    int d_NumArmies;

    /**
     * Constructor of the class which is called when the new object of Airlift class is created.
     * @param p_Player Player who issues the airlift order.
     * @param p_SourceCountry Country from which the airlift of armies should happen.
     * @param p_TargetCountry Country to which armies to be sent.
     * @param p_NumArmies Number of armies to be Airlift from one country to another.
     */
    public Airlift(Player p_Player, Country p_SourceCountry, Country p_TargetCountry, int p_NumArmies) {
        d_Player = p_Player;
        d_SourceCountry = p_SourceCountry;
        d_TargetCountry = p_TargetCountry;
        d_NumArmies = p_NumArmies;
    }

    /**
     * This method airlifts armies from one country to another country of the same player if order is valid.
     * This method also removes the card airlift.
     */
    @Override
    public void execute() {
        if(isValid()) {
            d_SourceCountry.setD_NoOfArmies(d_SourceCountry.getD_NoOfArmies()-d_NumArmies);
            d_TargetCountry.setD_NoOfArmies(d_TargetCountry.getD_NoOfArmies()+d_NumArmies);
            d_Player.getD_Cards().remove("Airlift");
        }
        else {
			d_Player.setD_SkipCommands(true);
			System.out.println("Skipping all the following commands of " + d_Player.getD_PlayerName());
		}
     
    }

    /**
     * This method checks for the validity of the Airlift order.
     * Returns false if the source and target countries are same, player doesn't have airlift card.
     * Return false if source country is not left with at least one army.
     * @return true if valid, else false.
     */
    public boolean isValid() {
        if(!d_Player.getD_Cards().contains("Airlift")) {
            System.out.println("Player does not have a Airlift card");
            return false;
        }
        if(d_SourceCountry==d_TargetCountry) {
            System.out.println("The source country and target country cannot be same!");
            return false;
        }
        else if(d_SourceCountry.getD_NoOfArmies()-d_NumArmies < 1) {
            System.out.println("The source country should be left with at least one army!");
            return false;
        }
        else {
            if(d_Player.getD_PlayerOwnedCountries().contains(d_SourceCountry) && d_Player.getD_PlayerOwnedCountries().contains(d_TargetCountry)) {
                System.out.println("The source country and target country belong to the same player");
                return true;
            }
            else {
                System.out.println("You can only airlift armies to your own countries. "+d_TargetCountry.getD_CountryName()+" does not belongs to "+d_Player.getD_PlayerName());
                return false;
            }
        }
    }
}