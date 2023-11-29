package com.soen6441.risk_game_u14.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import com.soen6441.risk_game_u14.model.Country;
import com.soen6441.risk_game_u14.model.GameModel;
import com.soen6441.risk_game_u14.model.Map;
import com.soen6441.risk_game_u14.model.Player;
/**
 * This class represents a Cheater Player Strategy for a Risk game.
 * Implements strategy-specific behavior for the Cheater player type.
 * Extends the Strategy class and implements Serializable for object serialization.
 */
public class CheaterPlayerStrategy extends Strategy implements Serializable{
	private Player d_Player;
	private GameModel d_GameModel;
	
	ArrayList<Country> d_deployCountries = new ArrayList<Country>();
	/**
	 * Constructor for CheaterPlayerStrategy.
	 *
	 * @param p_Player     The player using this strategy.
	 * @param p_GameModel  The game model being used.
	 */
	public CheaterPlayerStrategy(Player p_Player,GameModel p_GameModel) {
		d_Player=p_Player;
		d_GameModel = p_GameModel;
	}
	/**
	 * Creates an order for the player based on the cheater strategy.
	 * Cheater strategy involves deploying armies, conquering neighboring enemies,
	 * and doubling armies on enemy-bordered countries.
	 *
	 * @return The order created.
	 */
	@Override
	public String createOrder() {

		if(d_Player.getD_ArmiesCount() != 0) {
			while(d_Player.getD_ArmiesCount() > 0) {
				Random l_random = new Random();
				Country l_randomCountry = getRandomCountry(d_Player.getD_PlayerOwnedCountries());
				int l_armiesToDeploy = l_random.nextInt(d_Player.getD_ArmiesCount()+1);

				l_randomCountry.setD_NoOfArmies(l_armiesToDeploy);
				d_Player.setD_ArmiesCount((d_Player.getD_ArmiesCount() - l_armiesToDeploy));                 

				String l_logMessage = "Cheater Player: " + d_Player.getD_PlayerName() +
						" assigned " + l_armiesToDeploy +
						" armies to  " + l_randomCountry.getD_CountryName();
				
				System.out.println(l_logMessage);
				// (l_logMessage, "effect");
			}
		}

		try {
			conquerNeighboringEnemies(d_Player);
		} catch (ConcurrentModificationException l_e){
			System.out.println(l_e.getMessage());
		}

		doubleArmyOnEnemyNeighboredCounties(d_Player);

		d_Player.setIsExit(true);
		return null;
	}
	/**
	 * Returns the name of this strategy.
	 *
	 * @return The strategy name.
	 */
	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Cheater";
	}
	/**
	 * Creates a deploy order for the player.
	 * Not implemented in this strategy.
	 *
	 * @return The deploy order created.
	 */
	@Override
	public String createDeployOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Creates an advance order for the player.
	 * Not implemented in this strategy.
	 *
	 * @return The advance order created.
	 */
	@Override
	public String createAdvanceOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Creates a card-related order for the player.
	 * Not implemented in this strategy.
	 *
	 * @param p_CardName The name of the card.
	 * @return The card order created.
	 */
	@Override
	public String createCardOrder(String p_CardName) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Retrieves a random country from the given list of countries.
	 *
	 * @param p_listOfCountries The list of countries.
	 * @return A random country from the list.
	 */
	private Country getRandomCountry(List<Country> p_listOfCountries){
		Random l_random = new Random();
		if(p_listOfCountries.size()==1)
			return p_listOfCountries.get(0);
		return p_listOfCountries.get(l_random.nextInt(p_listOfCountries.size()));
	}

	/**
	 * Doubles the number of armies on countries that are neighbors to enemies owned by the player.
	 *
	 * @param p_player The player object.
	 */
	private void doubleArmyOnEnemyNeighboredCounties(Player p_player){
		List<Country> l_countriesOwned = p_player.getD_PlayerOwnedCountries();

		for(Country l_ownedCountry : l_countriesOwned) {
			ArrayList<String> l_countryEnemies = getEnemies(p_player, l_ownedCountry);

			if(l_countryEnemies.size() == 0) continue;

			Integer l_arimiesInTerritory = l_ownedCountry.getD_NoOfArmies();

			if(l_arimiesInTerritory == 0) continue;

			l_ownedCountry.setD_NoOfArmies(l_arimiesInTerritory*2);

			String l_logMessage = "Cheater Player: " + p_player.getD_PlayerName() +
					" doubled the armies ( Now: " + l_arimiesInTerritory*2 +
					") in " + l_ownedCountry.getD_CountryName();

			System.out.println(l_logMessage);
			//p_gameState.updateLog(l_logMessage, "effect");

		}
	}


	/**
	 * Conquers all enemies that are neighbors to the countries owned by the player.
	 *
	 * @param p_player The player object.
	 */
	private void conquerNeighboringEnemies(Player p_player){
		List<Country> l_countriesOwned = p_player.getD_PlayerOwnedCountries();

		for(Country l_ownedCountry : l_countriesOwned) {
			ArrayList<String> l_countryEnemies = getEnemies(p_player, l_ownedCountry);

			for(String l_enemyId: l_countryEnemies) {
				Map l_loadedMap =  d_GameModel.getD_Map();
//				Player l_enemyCountryOwner = this.getCountryOwner(l_enemyId)s;
				Country l_enemyCountry = l_loadedMap.findCountryByName(l_enemyId);
				Player l_enemyCountryOwner =l_enemyCountry.getD_Owner();
				this.conquerTargetCountry(l_enemyCountryOwner ,d_Player, l_enemyCountry);

				String l_logMessage = "Cheater Player: " + p_player.getD_PlayerName() +
						" Now owns " + l_enemyCountry.getD_CountryName();
				System.out.println(l_logMessage);
//				p_gameState.updateLog(l_logMessage, "effect");
			}

		}
	}


	/**
	 * Retrieves the enemy neighbors of the player's country.
	 *
	 * @param p_player  The cheater player.
	 * @param p_country The player country.
	 * @return List of enemy neighbors.
	 */
	private ArrayList<String> getEnemies(Player p_player, Country p_country){
		ArrayList<String> l_enemyNeighbors = new ArrayList<>();

		for(String l_countryID : p_country.getD_Neighbors()){
			if(!p_player.getD_PlayerOwnedCountries().contains(d_GameModel.getD_Map().findCountryByName(l_countryID)))
				l_enemyNeighbors.add(l_countryID);
		}
		return l_enemyNeighbors;
	}


	/**
	 * Retrieves the owner of the given country.
	 *
	 * @param p_countryId The ID of the country.
	 * @return The owner of the country.
	 */

	private Player getCountryOwner(String p_countryId){
		List<Player> l_players = d_GameModel.getD_Players();
		Player l_owner = null;

		for(Player l_player: l_players){
			List<Country> l_countriesOwned = l_player.getD_PlayerOwnedCountries();
			if(l_countriesOwned.contains(d_GameModel.getD_Map().findCountryByName(p_countryId))){
			l_owner = l_player;
			break;
			}
		}

		return l_owner;
	}

	/**
	 * Conquers the target country when it doesn't have any armies.
	 *
	 * @param p_targetCPlayer The player owning the target country.
	 * @param p_cheaterPlayer The player owning the source country.
	 * @param p_targetCountry The target country.
	 */
	private void conquerTargetCountry(Player p_targetCPlayer, Player p_cheaterPlayer, Country p_targetCountry) {
		p_targetCPlayer.getD_PlayerOwnedCountries().remove(p_targetCountry);
		p_cheaterPlayer.getD_PlayerOwnedCountries().add(p_targetCountry);
		
		p_targetCountry.setD_Owner(p_cheaterPlayer);
		
		// Add Log Here
		this.updateContinents(p_cheaterPlayer, p_targetCPlayer);
	}

	/**
	 * Updates continents of players based on battle results.
	 *
	 * @param p_cheaterPlayer The player owning the source country.
	 * @param p_targetCPlayer The player owning the target country.
	 */
	private void updateContinents(Player p_cheaterPlayer, Player p_targetCPlayer) {
		List<Player> l_playesList = new ArrayList<>();
		p_cheaterPlayer.setD_PlayerOwnedContinent(new ArrayList<>());
		p_targetCPlayer.setD_PlayerOwnedContinent(new ArrayList<>());
		l_playesList.add(p_cheaterPlayer);
		l_playesList.add(p_targetCPlayer);
		for(Player pl:l_playesList) {
			pl.setPlayerContinent();
		}
	}


}
