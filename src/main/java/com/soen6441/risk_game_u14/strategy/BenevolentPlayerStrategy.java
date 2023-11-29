package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Player;

public class BenevolentPlayerStrategy extends Strategy implements Serializable{

	private Player d_Player;
	private GameModel d_GameModel;
	
	ArrayList<Country> d_deployCountries = new ArrayList<Country>();
	
	public BenevolentPlayerStrategy(Player p_Player,GameModel p_GameModel) {
		d_Player=p_Player;
		d_GameModel = p_GameModel;
	}

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


	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Benevolent";
	}

	@Override
	public String createDeployOrder() {
		if(d_Player.getD_ArmiesCount()>0) {
			Country l_weakestCountry = getWeakestCountry(d_Player);
			d_deployCountries.add(l_weakestCountry);

			Random l_random = new Random();
			int l_armiesToDeploy = 1;
			if (d_Player.getD_ArmiesCount()>1) {
				l_armiesToDeploy = l_random.nextInt(2,d_Player.getD_ArmiesCount() + 1);
			}
			System.out.println("deploy " + l_weakestCountry.getD_CountryName() + " " + l_armiesToDeploy);
			return String.format("deploy %s %d", l_weakestCountry.getD_CountryName(), l_armiesToDeploy);
		}else{
			return createAdvanceOrder();
		}
	}

	@Override
	public String createAdvanceOrder() {
		// advance on weakest country
		int l_armiesToSend;
		Random l_random = new Random();
		// weakest country neighbor
		List<Country> weakestcountryNeighbors = new ArrayList<>();
		for(String weak: getWeakestCountry(d_Player).getD_Neighbors()) {
			weakestcountryNeighbors.add(d_GameModel.getD_Map().findCountryByName(weak));
		}
		
		Country l_randomSourceCountry = getRandomCountry(weakestcountryNeighbors);
		System.out.println("Source country : "+ l_randomSourceCountry.getD_CountryName());
		
		Country l_weakestTargetCountry =  getWeakestCountry(d_Player);//getWeakestNeighbor(l_randomSourceCountry);
		if(l_weakestTargetCountry == null)
			return null;
		
		System.out.println("Target Country : "+l_weakestTargetCountry.getD_CountryName());
		if (l_randomSourceCountry.getD_NoOfArmies() > 1) {
			l_armiesToSend = l_random.nextInt(l_randomSourceCountry.getD_NoOfArmies() + 1);
		} else {
			l_armiesToSend = 1;
		}

		System.out.println("advance " + l_randomSourceCountry.getD_CountryName() + " "
				+ l_weakestTargetCountry.getD_CountryName() + " " + l_armiesToSend);
		return "advance " + l_randomSourceCountry.getD_CountryName() + " " + l_weakestTargetCountry.getD_CountryName()
				+ " " + l_armiesToSend;

	}

	@Override
	public String createCardOrder(String p_CardName) {
		int l_armiesToSend;
		Random l_random = new Random();
		Country l_randomOwnCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());

		if (l_randomOwnCountry.getD_NoOfArmies() > 1) {
			l_armiesToSend = l_random.nextInt(l_randomOwnCountry.getD_NoOfArmies() + 1);
		} else {
			l_armiesToSend = 1;
		}

		switch (p_CardName) {
		case "Bomb":
			System.err.println("I am benevolent player, I don't hurt anyone.");
			return "bomb" + " " + "false";
		case "blockade":
			return "blockade " + l_randomOwnCountry.getD_CountryName();
		case "Airlift":
			return "airlift " + l_randomOwnCountry.getD_CountryName() + " "
					+ getRandomCountry(d_Player.getD_PlayerOwnedCountries()).getD_CountryName() + " " + l_armiesToSend;
		case "Negotiate":
			return "negotiate " + getRandomEnemyPlayer(d_Player).getD_PlayerName();
		}
		return null;
	}
	
	public Country getWeakestCountry(Player p_player) {
		List<Country> l_countriesOwnedByPlayer = p_player.getD_PlayerOwnedCountries();
		Country l_Country = calculateWeakestCountry(l_countriesOwnedByPlayer);
		return l_Country;
	}
	public Country calculateWeakestCountry(List<Country> l_listOfCountries) {
		LinkedHashMap<Country, Integer> l_CountryWithArmies = new LinkedHashMap<Country, Integer>();

		int l_smallestNoOfArmies;
		Country l_Country = null;

		// return weakest country from owned countries of player.
		for (Country l_country : l_listOfCountries) {
			l_CountryWithArmies.put(l_country, l_country.getD_NoOfArmies());
		}
		System.out.println("-----------------"+l_CountryWithArmies.size());
		l_smallestNoOfArmies = Collections.min(l_CountryWithArmies.values());
		for (Entry<Country, Integer> entry : l_CountryWithArmies.entrySet()) {
			if (entry.getValue().equals(l_smallestNoOfArmies)) {
				return entry.getKey();
			}
		}
		return l_Country;

	}
	
	private Country getRandomCountry(List<Country> p_listOfCountries) {
		
		Random l_random = new Random();
		if(p_listOfCountries.size()==1)
			return p_listOfCountries.get(0);
		return p_listOfCountries.get(l_random.nextInt(p_listOfCountries.size()));
	}
	
	public Country getWeakestNeighbor(Country l_randomSourceCountry) {
		List<String> l_adjacentCountryIds = l_randomSourceCountry.getD_Neighbors();
		List<Country> l_listOfNeighbors = new ArrayList<Country>();
		for (int l_index = 0; l_index < l_adjacentCountryIds.size(); l_index++) {
			Country l_country = d_GameModel.getD_Map()
					.findCountryByName(l_adjacentCountryIds.get(l_index));
			if(d_Player.getD_PlayerOwnedCountries().contains(l_country))
				l_listOfNeighbors.add(l_country);
		}
		if(l_listOfNeighbors.size()>0)
			return calculateWeakestCountry(l_listOfNeighbors);

		return null;
	}

	private Player getRandomEnemyPlayer(Player p_player) {
		ArrayList<Player> l_playerList = new ArrayList<Player>();
		Random l_random = new Random();

		for (Player l_player : d_GameModel.getD_Players()) {
			if (!l_player.equals(d_Player))
				l_playerList.add(l_player);
		}
		return l_playerList.get(l_random.nextInt(l_playerList.size()));
	}
	
	private Boolean checkIfArmiesDepoyed(){
		if(d_Player.getD_PlayerOwnedCountries().stream().anyMatch(l_country -> l_country.getD_NoOfArmies()>0)){
			return true;
		}
		return false;
	}
}
