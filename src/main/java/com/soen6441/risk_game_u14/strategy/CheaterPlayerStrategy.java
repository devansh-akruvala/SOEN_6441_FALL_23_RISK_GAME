package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;
import com.soen6441.risk_game_u14.order.Advance;
import com.soen6441.risk_game_u14.order.Deploy;

public class CheaterPlayerStrategy extends Strategy implements Serializable {

	/**
	 * The Random reference to generate random numbers.
	 */
	private Random rand;
	/**
	 * GameModel new object to get the current map.
	 */
	private GameModel d_GameModel;
	/**
	 * Player reference of this strategy
	 */
	private Player d_Player;
	/**
	 * The constructor initializes the cheater player and the game model new object as well as the Random object is created.
	 * @param p_Player The Player whose strategy is Cheater.
	 * @param p_GameModel The Reference of GameModel to get the Map on which the match is to be played.
	 */
	public CheaterPlayerStrategy(Player p_Player,GameModel p_GameModel) {
		this.d_GameModel = p_GameModel;
		d_Player = p_Player;
		rand = new Random();
		d_Leb.setResult("Cheater Player");
	}
	/**
	 * This is an overridden toAttack method. The random player gets its own strongest country, the source country by calling the toDefend method.
	 * Then it randomly chooses one of the neighboring countries of the source country. 
	 * @return It returns an Arraylist containing the source country and the target country to attack to.
	 */
	@Override
	public ArrayList<Country> toAttack()
	{
		ArrayList<Country> l_ReturnCountries = new ArrayList<Country>();
		Country l_AttackCountry = null;
		Country l_DefendingCountry = toDefend();
		String l_ReturnCountryName="";
		l_ReturnCountryName  = l_DefendingCountry.getD_Neighbors().get(rand.nextInt(l_DefendingCountry.getD_Neighbors().size()));
		for(Country l_TempCountry:d_GameModel.getD_Map().getD_CountryObjects())
		{
			if(l_TempCountry.getD_CountryName().equals(l_ReturnCountryName))
			{
				l_AttackCountry = l_TempCountry;
				break;
			}

		}
		l_ReturnCountries.add(0, l_DefendingCountry);
		l_ReturnCountries.add(1, l_AttackCountry);
		d_Player.setD_Result("The Cheater player is attacking on "+l_AttackCountry.getD_CountryName());
		return l_ReturnCountries;
	}
	/**
	 * This is an overridden toDefend method. The random player randomly selects one of the countries owned by it.
	 * @return The country that it wants to deploy army to or move the armies from in case of an attack.
	 */
	@Override
	public Country toDefend()
	{
		Country l_ReturnCountry = null;
		try
		{
			l_ReturnCountry = d_Player.getD_PlayerOwnedCountries().get(rand.nextInt(d_Player.getD_PlayerOwnedCountries().size()));}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		d_Player.setD_Result("The Cheater player is defending from "+l_ReturnCountry.getD_CountryName());
		return l_ReturnCountry;
	}
	/**
	 * This is an overridden method to create an order. The cheater player can issue a deploy order or an advance order only.
	 * In the case of advance order if the source country has armies less than 1, then instead of an advance order the 
	 * random player chooses to issue a deploy order on that country.
	 * @return It returns the order object issued.
	 */
	@Override
	public ArrayList<Order> createOrder() {
		// TODO Auto-generated method stub
//		int l_rndOrder = rand.nextInt(2);
//		Order l_returnOrder = null;
//
//		switch(l_rndOrder) 
//		{
//		case 0: 
//			Country l_DefendCountry1 = toDefend();
//			d_Leb.setResult("in cheater the armies are deployed to -" +l_DefendCountry1.getD_CountryName());
//			l_returnOrder = new Deploy(d_Player,l_DefendCountry1,Math.max(rand.nextInt(d_Player.getD_OwnerArmies()),2));
//			break;
//
//		case 1: 
//			ArrayList<Country> l_Countries = toAttack();
//			if(l_Countries.get(0).getNoOfArmies()>1)
//			{
//				d_Leb.setResult("in cheater defending country - "+l_Countries.get(0).getD_CountryName()+" Attacking country - "+l_Countries.get(1).getD_CountryName()+" with armies - "+(l_Countries.get(0).getNoOfArmies()-1));
//				l_returnOrder =  new Advance(d_Player,l_Countries.get(0),l_Countries.get(1),l_Countries.get(0).getNoOfArmies()-1);
//			}
//			else
//			{
//				d_Leb.setResult("in cheater the armies are deployed to -" +l_Countries.get(0).getD_CountryName());
//				l_returnOrder = new Deploy(d_Player,l_Countries.get(0),Math.max(rand.nextInt(d_Player.getD_OwnerArmies()),2));
//			}
//
//			break;
//		}
//
//		d_Leb.setResult("in cheater player order is "+l_returnOrder);
//		return l_returnOrder;
		
		System.out.println("Cheater player: Capturing all neighbor countries and doubling armies on countries with enemy neighbors");
		
		List<Country> l_Enemies = new ArrayList<>();
        //find and conquer neighbor countries
		Player l_NeighborOwner = null;
        for (Country l_Country : d_Player.getD_PlayerOwnedCountries()) {
            for (String l_NeighborName : l_Country.getD_Neighbors()) {
                Country l_Neighbor = d_GameModel.getD_Map().findCountryByName(l_NeighborName);
            	if (l_Neighbor.getD_Owner() != d_Player) {
                    l_NeighborOwner = l_Neighbor.getD_Owner();
                    l_NeighborOwner.getD_PlayerOwnedCountries().remove(l_Neighbor);
                    l_Enemies.add(l_Neighbor);
                    l_Neighbor.setD_Owner(d_Player);
                    d_Leb.setResult("Conquered the neighbor country of enemy - " + l_Neighbor.getD_CountryName());
                }
            }
        }
        d_Player.getD_PlayerOwnedCountries().addAll(l_Enemies);
        
        
      //double the army of a country if it has an enemy
        for (Country l_Country : d_Player.getD_PlayerOwnedCountries()) {
            for (String l_NeighborName : l_Country.getD_Neighbors()) {
            	Country l_Neighbor = d_GameModel.getD_Map().findCountryByName(l_NeighborName);
            	if (l_Neighbor.getD_Owner() != d_Player) {
            		if(l_Country.getD_NoOfArmies()==0)
            			l_Country.setD_NoOfArmies( 2);
            			else
                    l_Country.setD_NoOfArmies(l_Country.getD_NoOfArmies() * 2);
                    d_Leb.setResult("Armies doubled in Cheater Player's country" + l_Country.getD_CountryName());
                }
            }
        }
        ArrayList<Order> eorder= new ArrayList<Order>();
        return eorder;
	}
	/**
	 * This is an overridden method to provide the strategy of the player.
	 * @return a String specifying "Cheater" - strategy of this class.
	 */
	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Cheater";
	}

}
