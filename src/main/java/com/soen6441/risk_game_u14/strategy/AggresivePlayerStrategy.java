package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
	/**
	 * The Random reference to generate random numbers.
	 */
	private Random d_Random;
	/**
	 * GameModel new object to get the current map.
	 */
	private GameModel d_GameModel;
	/**
	 * Player reference of this strategy
	 */
	private Player d_Player;
	/**
	 * Constructor which accept the player of type aggresive and the gamemode which contains all other game related data. 
	 * @param p_Player	aggresive type of player
	 * @param p_GameModel GameModel object
	 */
	public AggresivePlayerStrategy(Player p_Player,GameModel p_GameModel) {
		this.d_Player = p_Player;
		this.d_GameModel = p_GameModel;
		d_Random = new Random();
		d_Leb.setResult("Aggressive Player");
	}

	/**
	 * This method returns the strongest country for the aggresive player. 
	 * It takes the list of players and sort them according to their number of armies. 
	 * @return Country returns the strongest country of the player
	 */
	@Override
	public Country toDefend() {
		Country l_TempCountry=null;
		HashMap <Country,Integer> l_PlayerCountryMap = new HashMap<>();
		for(Country l_Country : d_Player.getD_PlayerOwnedCountries()){
			l_PlayerCountryMap.put(l_Country, l_Country.getD_NoOfArmies());
		}
		List<Entry<Country, Integer>> l_List = new LinkedList<Entry<Country, Integer>>(l_PlayerCountryMap.entrySet()); 
		Collections.sort(l_List, new Comparator<Entry<Country, Integer>>()   {  
			@Override
			public int compare(Entry<Country, Integer> l_O1, Entry<Country, Integer> l_O2){   
				return l_O2.getValue().compareTo(l_O1.getValue());  
			}  
		});
		l_TempCountry = l_List.get(0).getKey();
		return l_TempCountry;
	}

	/**
	 * This method checks for all the neighbors of the source country and randomly choose any one country to attack. 
	 * If the country to be attacked is of same player then only transfer of armies happen else advance order is executed. 
	 * @return returns the list of source and target country for the attack
	 */
	@Override
	public ArrayList<Country> toAttack(){
		ArrayList<Country> l_ReturnCountries = new ArrayList<Country>();
		Country l_Country = toDefend();
		Country l_ReturnCountry=null;
		ArrayList<Country> l_BorderCountriesList = new ArrayList<>();
		for(Country l_C : this.d_GameModel.getD_Map().getD_CountryObjects()) {
			if(l_Country.getD_Neighbors().contains(l_C.getD_CountryName())) {
				l_BorderCountriesList.add(l_C);
			}
		}
		/// picking neighbor randomly and performing advance accordingly
		l_ReturnCountry = l_BorderCountriesList.get(d_Random.nextInt(l_BorderCountriesList.size()));
		l_ReturnCountries.add(0,l_Country);
		l_ReturnCountries.add(1,l_ReturnCountry);
		return l_ReturnCountries;
	}

	/**
	 * This method centralize its armies on the strongest country. 
	 * Meaning, it deploys on the strongest country initially and then when it has enough number of armies it attacks from the strongest country. 
	 * Deploy and Advance orders are created randomly. 
	 * @return Order returns the order which was created
	 */
	@Override
	public ArrayList<Order> createOrder() {
		System.out.println(d_Player.getD_PlayerName()+" order");
//		int l_RandomInt = d_Random.nextInt(2);
		ArrayList<Order> l_OrderToBeReturned = new ArrayList<>();
//		switch(l_RandomInt) {
//		case 0: 
//			Country l_DefendCountry1 = toDefend();
//			d_Leb.setResult("in agressive the armies are deployed to -" +l_DefendCountry1.getD_CountryName());
//			l_OrderToBeReturned = new Deploy(this.d_Player, l_DefendCountry1, Math.max(d_Random.nextInt(d_Player.getD_ArmiesCount()),2));
//			break;
//		case 1: 
//			ArrayList<Country> l_Countries = toAttack();
//			/*If player does not have enough armies, it will not advance. it will still deploy*/
//			if(l_Countries.get(0).getD_NoOfArmies()>1){
//				d_Leb.setResult("in aggressive defending country - "+l_Countries.get(0).getD_CountryName()+" Attacking country - "+l_Countries.get(1).getD_CountryName()+" with armies- "+(l_Countries.get(0).getD_NoOfArmies()-1));
//				l_OrderToBeReturned =  new Advance(this.d_Player, l_Countries.get(0), l_Countries.get(1), l_Countries.get(0).getD_NoOfArmies()-1);
//			}
//			else{
//				d_Leb.setResult("in agrressive the armies are deployed to -" +l_Countries.get(0).getD_CountryName());
//				l_OrderToBeReturned = new Deploy(this.d_Player, toDefend(),Math.max(d_Random.nextInt(d_Player.getD_ArmiesCount()),2));
//			}
//			break;	
//		}
		

		
		ArrayList<Country> l_Countries = toAttack();
		int l_ArmyCount = d_Player.getD_ArmiesCount();
		System.out.println("Deploying "+ l_ArmyCount +" to " + l_Countries.get(0).getD_CountryName());
		l_OrderToBeReturned.add(new Deploy(d_Player,l_Countries.get(0), l_ArmyCount));
		int attackArmies =l_ArmyCount+l_Countries.get(0).getD_NoOfArmies()-1;
		System.out.println("Advancing to "+l_Countries.get(1).getD_CountryName()+" with "+attackArmies+" Armies");
		l_OrderToBeReturned.add(new Advance(d_Player, l_Countries.get(0), l_Countries.get(1), attackArmies));
		
		d_Leb.setResult("in aggressive the order is - "+l_OrderToBeReturned);
		return l_OrderToBeReturned;
	}

	/**
	 * This method returns the strategy type of the player. 
	 * @return return String returns the type of player
	 */
	@Override
	public String strategyName() {
		return "Aggressive";
	}

}
