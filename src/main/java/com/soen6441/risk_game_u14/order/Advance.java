package com.soen6441.risk_game_u14.order;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.Order;
import com.soen6441.risk_game_u14.model.Player;

/**
 * The Advance class represents a type of order issued by a player in the game. This order allows a player to attack a territory belonging to another player using a specified number of armies. The attack outcome is determined by a 60% success rate for the attacker and a 70% success rate for the defender.
 * The order format is: "advance sourceCountry targetCountry NumberOfArmies".
 * @author Devansh Akruvala
 */
public class Advance implements Order {


	/**
	 * The country from which the attack is launched (attacker's country) and the targeted defending country.
	 */
	Country d_SourceCountry,d_TargetCountry;
	/**
	 * The player issuing the attack.
	 */
	Player d_Player;
	/**
	 * The number of armies designated for the attack.
	 */
	int d_NumArmies;

	/**
	 * Default Constructor
	 */
	public Advance() {
		
	}

	/**
	 * Constructs an Advance order issued by a player.
	 *
	 * @param p_Player          The attacking player issuing the order.
	 * @param p_SourceCountry   The player's country launching the attack.
	 * @param p_TargetCountry   The enemy country being attacked.
	 * @param p_NumArmies1       The number of armies used in the attack.
	 */

	public Advance(Player p_Player, Country p_SourceCountry, Country p_TargetCountry, int p_NumArmies1) 
	{
		d_Player = p_Player;
		d_SourceCountry = p_SourceCountry;
		d_TargetCountry = p_TargetCountry;
		d_NumArmies = p_NumArmies1;

	}

	/**
	 * This method, an implementation of the execute method from the Order interface, handles the attack mechanism in the game logic.
	 * The specific actions depend on the integer value returned from the isValid method. The different scenarios include:
	 * <ol>
	 * <li>1. If both the sourceCountry and targetCountry belong to the same player, the armies simply move from the source to the target country.</li>
	 * <li>2. If the target country has no defending armies, it's acquired by the attacking player, who deploys the specified attacking armies there.</li>
	 * <li>3. Otherwise, a standard attack takes place.</li>
	 * </ol>
	 * During the attack phase:
	 * - Each attacking army is randomly assigned a value between 0 and 6 (representing a maximum 60% chance of winning the attack).
	 * - Each defending army is randomly assigned a value between 0 and 7 (representing a maximum 70% chance of surviving the attack).
	 *
	 * The battle unfolds based on the relative strengths of attacking and defending armies:
	 * - If the attacking armies outnumber the defending armies, each side matches their best armies one-on-one.
	 * - The side with the higher assigned value in a one-on-one battle wins. The victorious armies determine the overall outcome.
	 *
	 * The aftermath is decided based on the number of winning armies:
	 * - If the attacking side has more winning armies, they conquer the target country and deploy remaining armies, including those uninvolved in the battle.
	 * - If the defender has an equal or greater number of winning armies, they succeed, maintaining remaining defending armies and those unengaged in the fight.
	 *
	 * In the event of a defender's victory, the remaining attacking armies return to their source country.
	 */
	@Override
	public void execute()
	{
		Random l_rand = new Random();
		int l_flag = isValid();
		if(l_flag==0) {
			d_Player.setD_SkipCommands(true);
			d_Player.setD_Result(d_Player.getD_Result() + "\nSkipping all the following commands of " + d_Player.getD_PlayerName());
		}
		
		else if(l_flag == 1)
		{
			d_SourceCountry.setD_NoOfArmies(d_SourceCountry.getD_NoOfArmies()-d_NumArmies);
			d_TargetCountry.setD_NoOfArmies(d_TargetCountry.getD_NoOfArmies()+d_NumArmies);
		}
		else if(l_flag==2)
		{
			if(d_TargetCountry.getD_NoOfArmies()==0)
			{
				d_TargetCountry.getD_Owner().getD_PlayerOwnedCountries().remove(d_TargetCountry);
				d_TargetCountry.setD_Owner(d_Player);
				d_TargetCountry.setD_NoOfArmies(d_NumArmies);
				d_SourceCountry.setD_NoOfArmies(d_SourceCountry.getD_NoOfArmies()-d_NumArmies);
				d_Player.addCountry(d_TargetCountry);
				d_Player.setD_AtleastOneBattleWon(true);


				d_Player.setD_Result("\n"+d_Player.getD_PlayerName()+" your attack on "+d_TargetCountry.getD_CountryName()+" was a Success!!");
				return;
			}
			TreeMap <Integer,Integer> l_AttackerArmies = new TreeMap<>(); 
			TreeMap <Integer,Integer> l_DefenderArmies = new TreeMap<>(); 
			TreeMap <Integer,Integer> l_AttackerArmiesinHand = new TreeMap<>();
			TreeMap <Integer,Integer> l_DefenderArmiesinHand = new TreeMap<>();
			
			//Assigning random values to the armies.
			for(int i=0;i<d_NumArmies;i++)
			{
				l_AttackerArmies.put(i, l_rand.nextInt(6));
			}
			for(int i=0;i<d_TargetCountry.getD_NoOfArmies();i++)
			{
				l_DefenderArmies.put(i, l_rand.nextInt(7));
			}
			l_AttackerArmiesinHand=l_AttackerArmies;
			l_DefenderArmiesinHand=l_DefenderArmies;
			
			// size difference between the attacking and defending armies
			int l_SizeDiff = l_AttackerArmies.size() - l_DefenderArmies.size(); 
			// Who has less number of armies.
			int l_MinArmies=Math.min(l_AttackerArmies.size(),l_DefenderArmies.size() );
//			d_Player.setD_Result("min armies"+l_MinArmies);

			try{
				// Getting the armies to fight in battleground based on who has less number of armies.
				TreeMap<Integer, Integer> returnedHashMap = ArmiestoFight(l_SizeDiff,l_MinArmies,l_AttackerArmies,l_DefenderArmies);
				d_Player.setD_Result("returned hashmap "+returnedHashMap);
				Iterator<Map.Entry<Integer,Integer>> itr_Attacker=l_AttackerArmiesinHand.entrySet().iterator();
				Iterator<Map.Entry<Integer,Integer>> itr_Defender=l_DefenderArmiesinHand.entrySet().iterator();
				
				// When attacking armies are greater than defending armies
				if(l_SizeDiff>0)
				{
					l_AttackerArmiesinHand = returnedHashMap;
					itr_Attacker = l_AttackerArmiesinHand.entrySet().iterator();
				}
				// When defending armies are greater than attacking armies.
				else if(l_SizeDiff<0)
				{
					l_DefenderArmiesinHand = returnedHashMap;
					itr_Defender = l_DefenderArmiesinHand.entrySet().iterator();
				}
				// When both have equal number of armies
				else
				{
					l_AttackerArmiesinHand = l_AttackerArmies;
					l_DefenderArmiesinHand = l_DefenderArmies;
				}
				//now attack

				int l_attackWin=0,l_defendWin=0;
				for(int i=0;i<returnedHashMap.size();i++)
				{
					Map.Entry<Integer,Integer> entry_Attack = itr_Attacker.next(); 
					Map.Entry<Integer,Integer> entry_Defend = itr_Defender.next(); 
					if(entry_Attack.getValue()>=entry_Defend.getValue())
					{
						l_attackWin++;
					}
					else
					{
						l_defendWin++;
					}
				}
				if(l_attackWin>l_defendWin)
				{
					d_TargetCountry.getD_Owner().removeCountry(d_TargetCountry);
					d_TargetCountry.setD_Owner(d_Player);
					d_TargetCountry.setD_NoOfArmies(d_NumArmies-l_MinArmies+l_attackWin);
					d_SourceCountry.setD_NoOfArmies(d_SourceCountry.getD_NoOfArmies()-d_NumArmies);
					d_Player.addCountry(d_TargetCountry);
					d_Player.setD_AtleastOneBattleWon(true);


					d_Player.setD_Result("\n"+d_Player.getD_PlayerName()+" your attack on "+d_TargetCountry.getD_CountryName()+" was a Success!!");
				}
				else
				{
					d_SourceCountry.setD_NoOfArmies(d_SourceCountry.getD_NoOfArmies()-l_MinArmies+l_attackWin);
					d_TargetCountry.setD_NoOfArmies(d_TargetCountry.getD_NoOfArmies()-l_MinArmies+l_defendWin);
					d_Player.setD_Result("\n"+d_Player.getD_PlayerName()+" your attack on "+d_TargetCountry.getD_CountryName()+" was a Failure!!");
				}
			}catch(Exception p_E) {p_E.printStackTrace();}
		}

	}

	/**
	 * This method determines the armies engaged in the battle.
	 * If the initial number of attacking armies exceeds the defending forces, it selects the top 'n' armies with the highest assigned values from the original set of armies, where 'n' is the number of defending armies.
	 * Similarly, if the original defending armies outnumber the attacking forces, it chooses the top 'n' armies with the greatest corresponding values from the initial set of armies, with 'n' representing the number of attacking armies.
	 * When the quantities of attacking and defending armies are equal, the method solely returns the attacking armies to maintain a size reference in the execute method.
	 *
	 * @param p_SizeDiffint     Indicates which side has a numerical advantage in armies.
	 * @param p_MinArmies       Signifies the quantity of armies participating in the battle.
	 * @param p_AttackerArmies  Represents the initial attacking armies with randomly assigned values.
	 * @param p_DefenderArmies  Denotes the original defending armies with randomly assigned values.
	 * @return The selection of armies based on their maximum corresponding values, dependent on which side holds a larger number of armies.
	 */
	
	TreeMap<Integer,Integer> ArmiestoFight(int p_SizeDiffint,int  p_MinArmies, TreeMap <Integer,Integer> p_AttackerArmies,TreeMap <Integer,Integer> p_DefenderArmies)
	{
		TreeMap<Integer,Integer> returnHashMap = null;
		d_Player.setD_Result("size difference"+p_SizeDiffint);
		
		// Attacking armies greater than defending armies.
		if(p_SizeDiffint>0)
		{
			// Sorting the attacking armies on the basis of random values assigned in descending order.
			List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(p_AttackerArmies.entrySet()); 
			Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
			{  
				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
				{   
					return o2.getValue().compareTo(o1.getValue());  
				}  
			});

			TreeMap <Integer,Integer> l_AttackerArmiesinHand = new TreeMap<>(); 
			Iterator<Map.Entry<Integer,Integer>> itr = list.iterator();
			
			// Selecting top n armies to be sent to fight.
			for(int i=0;i<p_MinArmies;i++)
			{
				Map.Entry<Integer,Integer> entry = itr.next(); 
				l_AttackerArmiesinHand.put(entry.getKey(),entry.getValue());
			}
			d_Player.setD_Result("l_AttackerArmiesinHand"+l_AttackerArmiesinHand);
			returnHashMap = l_AttackerArmiesinHand;
		}
		
		// Defending armies greater than attacking armies.
		else if(p_SizeDiffint<0)
		{
			// Sorting the attacking armies on the basis of random values assigned in descending order.
			List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(p_DefenderArmies.entrySet()); 
			Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
			{  
				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
				{   
					return o2.getValue().compareTo(o1.getValue());  
				}  
			});

			TreeMap <Integer,Integer> l_DefenderArmiesinHand = new TreeMap<>(Collections.reverseOrder()); 
			Iterator<Map.Entry<Integer,Integer>> itr = list.iterator();
			
			// Selecting top n armies to be sent to fight.
			for(int i=0;i<p_MinArmies;i++)
			{	
				d_Player.setD_Result("inside for of min armies");
				Map.Entry<Integer,Integer> entry = itr.next(); 
				d_Player.setD_Result(entry.getKey()+" : "+ entry.getValue());
				l_DefenderArmiesinHand.put(entry.getKey(),entry.getValue());
			}
			d_Player.setD_Result("l_DefenderArmiesinHand"+l_DefenderArmiesinHand);
			returnHashMap =  l_DefenderArmiesinHand;
		}
		
		// When both have same number of armies.
		else
		{
			returnHashMap = p_AttackerArmies;
		}
		d_Player.setD_Result("in armies to fight"+returnHashMap);
		return returnHashMap;
	}

	/**
	 * This method validates the order and identifies several conditions that render the order invalid:
	 *<ol>
	 * <li> When the issuing player is currently in negotiations with the owner of the target country.</li>
	 * <li>When the sourceCountry and the targetCountry are identical.</li>
	 * <li>When the sourceCountry and the targetCountry are not neighboring territories.</li>
	 * <li>When the sourceCountry is left with fewer than one army after the order has been executed with a specific number of armies.</li>
	 *</ol>
	 * This method returns different integers based on various valid or invalid execution cases:
	 * <ol>
	 * <li> It returns an integer of 1 when both countries involved in the order belong to the issuing player.</li>
	 * <li> An integer of 2 is returned when the countries belong to different players.</li>
	 * </ol>
	 * @return An integer representing the different scenarios determining the validity of the order's execution.
	 */
	
	public int isValid()
	{
		
		int l_ReturnInt=0;
		
		
		if(d_Player.getD_NegotiatedPlayers().contains(d_TargetCountry.getD_Owner()))
		{
			d_Player.setD_Result("\nThe targeted country "+d_TargetCountry.getD_CountryName()+" belongs to "+d_TargetCountry.getD_Owner().getD_PlayerName()+" which is negotiated player!");
			l_ReturnInt=0;
		}
		else if(d_SourceCountry==d_TargetCountry)
		{
			d_Player.setD_Result("\nThe source country and target country cannot be same!");
			l_ReturnInt= 0;
		}
		else if(!d_SourceCountry.getD_Neighbors().contains(d_TargetCountry.getD_CountryName()))
		{
			d_Player.setD_Result("\nThe source country and target country are not neighbours!");
			l_ReturnInt= 0;
		}
		else if(d_SourceCountry.getD_NoOfArmies()-d_NumArmies < 1)
		{
			d_Player.setD_Result("\nThe source country should be left with atleast one army!");
			l_ReturnInt= 0;
		}
		else
		{
			if(d_Player.getD_PlayerOwnedCountries().contains(d_SourceCountry) && d_Player.getD_PlayerOwnedCountries().contains(d_TargetCountry))
			{
				
				d_Player.setD_Result("\nThe source country and target country belong to the same player");
				l_ReturnInt= 1;
			}
			else if(d_Player.getD_PlayerOwnedCountries().contains(d_SourceCountry) && !d_Player.getD_PlayerOwnedCountries().contains(d_TargetCountry))
			{
				l_ReturnInt= 2;
			}

		}
		return l_ReturnInt;

	}
	 public Player getPlayer() {
	        return d_Player;
	    }

	    /**
	     * Sets the player who issues the  order.
	     * 
	     * @param d_Player The player who issues the order.
	     */
	    public void setPlayer(Player d_Player) {
	        this.d_Player = d_Player;
	    }
}
