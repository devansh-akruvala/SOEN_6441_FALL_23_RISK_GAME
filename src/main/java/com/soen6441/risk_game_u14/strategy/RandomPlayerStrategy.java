package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Player;

/**
 * Class that implements the Random Player Strategy
 * This class extends the parent Strategy class which has createOrder method to be implemented here.
 *
 */
public class RandomPlayerStrategy extends Strategy implements Serializable {

	private Player d_Player;
	private GameModel d_GameModel;
	
	
	/**
	 * List containing deploy order countries.
	 */
	ArrayList<Country> d_deployCountries = new ArrayList<Country>();
	
	
	/**
	 * Constructor setup variables for random strategy
	 * @param p_Player Player object
	 * @param p_GameModel GameModel object
	 */

	public RandomPlayerStrategy(Player p_Player, GameModel p_GameModel) {
		d_Player = p_Player;
		d_GameModel = p_GameModel;
	}

	/**
	 * This method creates a new order.
	 * 
	 * @return Order object of order class
	 */
	@Override
	public String createOrder() {
		// TODO Auto-generated method stub
		System.out.println("Creating order for: "+d_Player.getD_PlayerName());
		String l_command;
		if(d_Player.getD_PlayerOwnedCountries().size()==0)
			return null;
		
		if(!checkIfArmiesDepoyed()) {
			if(d_Player.getD_ArmiesCount()>0) {
				l_command = createDeployOrder();
			}else{
				l_command = createAdvanceOrder();
			}
		}
		else {
			if(d_Player.getD_Cards().size()>0){
				System.out.println("Enters Card Logic");
				int l_index = (int) (Math.random() * 3) +1;
				switch (l_index) {
					case 1:
						System.out.println("Deploy!");
						l_command = createDeployOrder();
						break;
					case 2:
						System.out.println("Advance!");
						l_command = createAdvanceOrder();
						break;
					case 3:
						if (d_Player.getD_Cards().size() == 1) {
							System.out.println("Cards!");
							l_command = createCardOrder(d_Player.getD_Cards().get(0));
							break;
						} else {
							Random l_random = new Random();
							int l_randomIndex = l_random.nextInt(d_Player.getD_Cards().size());
							l_command = createCardOrder(d_Player.getD_Cards().get(l_randomIndex));
							break;
						}
					default:
						l_command = createAdvanceOrder();
						break;
				}
			} else{
				Random l_random = new Random();
				Boolean l_randomBoolean = l_random.nextBoolean();
				if(l_randomBoolean){
					System.out.println("Without Card Deploy Logic");
					l_command = createDeployOrder();
				}else{
					System.out.println("Without Card Advance Logic");
					l_command = createAdvanceOrder();
				}
			}
		}
		return l_command;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Random";
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String createDeployOrder() {
		if (d_Player.getD_ArmiesCount()>0) {
			Random l_random = new Random();
			Country l_randomCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());
			if(l_randomCountry==null)
				return null;
			d_deployCountries.add(l_randomCountry);
			int l_armiesToDeploy = 1;
				l_armiesToDeploy = l_random.nextInt(1,d_Player.getD_ArmiesCount() + 1);
			return String.format("deploy %s %d", l_randomCountry.getD_CountryName(), l_armiesToDeploy);
		} else {
			return createAdvanceOrder();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String createAdvanceOrder() {
		int l_armiesToSend;
		Random l_random = new Random();
		Country l_randomOwnCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());
		if(l_randomOwnCountry==null)
			return null;
		while(l_randomOwnCountry.getD_NoOfArmies()==0) {
			l_randomOwnCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());
		}
		int l_randomIndex = l_random.nextInt(l_randomOwnCountry.getD_Neighbors().size());
		Country l_randomNeighbor;
		if (l_randomOwnCountry.getD_Neighbors().size()>1) {
			l_randomNeighbor = d_GameModel.getD_Map().findCountryByName(l_randomOwnCountry.getD_Neighbors().get(l_randomIndex));
		} else {
			l_randomNeighbor = d_GameModel.getD_Map().findCountryByName(l_randomOwnCountry.getD_Neighbors().get(0));
		}


			l_armiesToSend = l_random.nextInt(1,l_randomOwnCountry.getD_NoOfArmies() + 1);
			return "advance "+l_randomOwnCountry.getD_CountryName()+" "+l_randomNeighbor.getD_CountryName()+" "+ l_armiesToSend;
	}

	/**
	 * Creates card order 
	 */
	@Override
	public String createCardOrder(String p_CardName) {
		int l_armiesToSend;
		Random l_random = new Random();
		Country l_randomOwnCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());
		while(l_randomOwnCountry.getD_NoOfArmies()==0) {
			l_randomOwnCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());
		}
		if(l_randomOwnCountry==null)
			return null;
		Country l_randomNeighbour = d_GameModel.getD_Map().findCountryByName(l_randomOwnCountry.getD_Neighbors().get(l_random.nextInt(l_randomOwnCountry.getD_Neighbors().size())));
		Player l_randomPlayer = getRandomPlayer(d_Player);
		
			l_armiesToSend = l_random.nextInt(1,l_randomOwnCountry.getD_NoOfArmies() + 1);
		
		switch(p_CardName){
			case "Bomb":
				return "bomb "+ l_randomNeighbour.getD_CountryName();
			case "Blockade":
				return "blockade "+ l_randomOwnCountry.getD_CountryName();
			case "Airlift":
				return "airlift "+ l_randomOwnCountry.getD_CountryName()+" "+getRandomCountry(d_Player.getD_PlayerOwnedCountries()).getD_CountryName()+" "+l_armiesToSend;
			case "Negotiate":
				Player p = getRandomPlayer(d_Player); ;
				if(p==null)
					return null;
				return "negotiate" + " " +p.getD_PlayerName() ;
		}
		return null;

	}
	
	
	/**
	 * Check if it is first turn.
	 *
	 * @param p_player player instance
	 * @return boolean
	 */
	private Boolean checkIfArmiesDepoyed(){
		if(d_Player.getD_PlayerOwnedCountries().stream().anyMatch(l_country -> l_country.getD_NoOfArmies()>0)){
			return true;
		}
		return false;
	}
	/**
	 *
	 * returns a random country owned by player.
	 *
	 * @param p_listOfCountries list of countries owned by player
	 * @return a random country from list
	 */
	private Country getRandomCountry(List<Country> p_listOfCountries){
		Random l_random = new Random();
		if(p_listOfCountries.size()==0)
			return null;
		
		return p_listOfCountries.get(l_random.nextInt(p_listOfCountries.size()));
	}

	/**
	 * Chooses a random player to negotiate.
	 *
	 * @param p_player player object
	 * @return player object
	 */
	private Player getRandomPlayer(Player p_player){
		ArrayList<Player> l_playerList = new ArrayList<Player>();
		Random l_random = new Random();

		for(Player l_player : d_GameModel.getD_Players()){
			if(!l_player.equals(p_player) && !l_player.getD_PlayerName().equalsIgnoreCase("Neutral Player"))
				l_playerList.add(l_player);
		}
		if(l_playerList.size()==0)
			return null;
		return l_playerList.get(l_random.nextInt(l_playerList.size()));
 	}

}