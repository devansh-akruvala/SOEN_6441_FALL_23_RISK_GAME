package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;
import com.soen6441.risk_game_u14.order.Advance;
import com.soen6441.risk_game_u14.order.Deploy;


/**
 * This is a class which creates orders from the AggresivePlayer according to his strategy. 
 * This class extends the parent Strategy class which has createOrder method to be implemented here. 
 */
public class AggresivePlayerStrategy extends Strategy implements Serializable {

	
	private Player d_Player;
	private GameModel d_GameModel;
	
	ArrayList<Country> d_deployCountries = new ArrayList<Country>();
	
	public AggresivePlayerStrategy(Player p_Player,GameModel p_GameModel) {
		d_Player=p_Player;
		d_GameModel = p_GameModel;
	}
	
	@Override
	public String createOrder() {
		// TODO Auto-generated method stub
		System.out.println("Creating order for: "+d_Player.getD_PlayerName());
		String l_command;
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

	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Aggressive";
	}

	@Override
	public String createDeployOrder() {
		Random l_random = new Random();
		// get strongest country then deploy
		Country l_strongestCountry = getStrongestCountry();
		d_deployCountries.add(l_strongestCountry);
		int l_armiesToDeploy = 1;
		if (d_Player.getD_ArmiesCount()>1) {
			l_armiesToDeploy = l_random.nextInt(2,d_Player.getD_ArmiesCount()+1);
		}
		return String.format("deploy %s %d", l_strongestCountry.getD_CountryName(), l_armiesToDeploy);	
	}

	@Override
	public String createAdvanceOrder() {
//		Country l_randomSourceCountry = getRandomCountry(d_deployCountries);
		Country l_randomSourceCountry = getStrongestCountry();
		
		moveArmiesFromItsNeighbors(d_Player, l_randomSourceCountry);

		Random l_random = new Random();
		Country l_randomTargetCountry = d_GameModel.getD_Map()
				.findCountryByName(l_randomSourceCountry.getD_Neighbors()
						.get(l_random.nextInt(l_randomSourceCountry.getD_Neighbors().size())));
		

		int l_armiesToSend = l_randomSourceCountry.getD_NoOfArmies() > 1 ? l_randomSourceCountry.getD_NoOfArmies()-1: 1;

		System.out.println("advance " + l_randomSourceCountry.getD_CountryName() + " " + l_randomTargetCountry.getD_CountryName()
				+ " " + l_armiesToSend);
		// attacks with strongest country
		return "advance " + l_randomSourceCountry.getD_CountryName() + " " + l_randomTargetCountry.getD_CountryName()
				+ " " + l_armiesToSend;
	}

	@Override
	public String createCardOrder(String p_CardName) {
		Random l_random = new Random();
		Country l_StrongestSourceCountry = getStrongestCountry();

		Country l_randomTargetCountry = d_GameModel.getD_Map()
				.findCountryByName(l_StrongestSourceCountry.getD_Neighbors()
						.get(l_random.nextInt(l_StrongestSourceCountry.getD_Neighbors().size())));

		int l_armiesToSend = l_StrongestSourceCountry.getD_NoOfArmies() > 1 ? l_StrongestSourceCountry.getD_NoOfArmies()-1 : 1;

		switch (p_CardName) {
		case "Bomb":
			return "bomb " + l_randomTargetCountry.getD_CountryName();
		case "Blockade":
			return "blockade " + l_StrongestSourceCountry.getD_CountryName();
		case "Airlift":
			return "airlift " + l_StrongestSourceCountry.getD_CountryName() + " "
					+ getRandomCountry(d_Player.getD_PlayerOwnedCountries()).getD_CountryName() + " " + l_armiesToSend;
		case "Negotiate":
			return "negotiate" + " " + getRandomEnemyPlayer(d_Player).getD_PlayerName();
		}
		return null;
	}
	
	private Boolean checkIfArmiesDepoyed(){
		if(d_Player.getD_PlayerOwnedCountries().stream().anyMatch(l_country -> l_country.getD_NoOfArmies()>0)){
			return true;
		}
		return false;
	}
	
	
	public Country getStrongestCountry() {
		List<Country> l_countriesOwnedByPlayer = d_Player.getD_PlayerOwnedCountries();
		Country l_Country = calculateStrongestCountry(l_countriesOwnedByPlayer);
		return l_Country;
	}
	
	public Country calculateStrongestCountry(List<Country> l_listOfCountries) {
		LinkedHashMap<Country, Integer> l_CountryWithArmies = new LinkedHashMap<Country, Integer>();

		int l_largestNoOfArmies;
		Country l_Country = null;
		// return strongest country from owned countries of player.
		for (Country l_country : l_listOfCountries) {
			l_CountryWithArmies.put(l_country, l_country.getD_NoOfArmies());
		}
		l_largestNoOfArmies = Collections.max(l_CountryWithArmies.values());
		for (Entry<Country, Integer> entry : l_CountryWithArmies.entrySet()) {
			if (entry.getValue().equals(l_largestNoOfArmies)) {
				return entry.getKey();
			}
		}
		return l_Country;

	}
	
	private Country getRandomCountry(List<Country> p_listOfCountries) {
		Random l_random = new Random();
		return p_listOfCountries.get(l_random.nextInt(p_listOfCountries.size()));
	}
	
	
	public void moveArmiesFromItsNeighbors(Player d_Player, Country p_randomSourceCountry) {
		List<String> l_adjacentCountryIds = p_randomSourceCountry.getD_Neighbors();
		List<Country> l_listOfNeighbors = new ArrayList<Country>();
		for (int l_index = 0; l_index < l_adjacentCountryIds.size(); l_index++) {
			Country l_country = d_GameModel.getD_Map().findCountryByName(l_adjacentCountryIds.get(l_index));
			// check if neighbor belongs to player and then add to list
			if (d_Player.getD_PlayerOwnedCountries().contains(l_country)) {
				l_listOfNeighbors.add(l_country);
			}
		}

		int l_ArmiesToMove = 0;
		// send armies from neighbor to source country
		for (Country l_con : l_listOfNeighbors) {
			l_ArmiesToMove += p_randomSourceCountry.getD_NoOfArmies() > 0
					? p_randomSourceCountry.getD_NoOfArmies() + (l_con.getD_NoOfArmies())
					: (l_con.getD_NoOfArmies());

		}
		p_randomSourceCountry.setD_NoOfArmies(l_ArmiesToMove);
	}

	private Player getRandomEnemyPlayer(Player p_player) {
		ArrayList<Player> l_playerList = new ArrayList<Player>();
		Random l_random = new Random();

		for (Player l_player : d_GameModel.getD_Players()) {
			if (!l_player.equals(p_player))
				l_playerList.add(p_player);
		}
		return l_playerList.get(l_random.nextInt(l_playerList.size()));
	}

}
