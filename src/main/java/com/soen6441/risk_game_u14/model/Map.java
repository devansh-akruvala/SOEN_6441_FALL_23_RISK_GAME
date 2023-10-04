package com.soen6441.risk_game_u14.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * This is a map model class which stores all the countries and continents of
 * the map. It handles operations like adding and removing continents, countries
 * and their neighbors. It also handles 'savemap' and 'loadmap' functionalities
 * as well as validates that each all continents and the map a
 * 
 * @author Devansh, Smridhi, Meshva
 */
public class Map {
	private ArrayList<Continent> d_ContinentObjects;
	private ArrayList<Country> d_CountryObjects;
	private HashMap<Integer, ArrayList<Integer>> d_AdjList;
	private HashMap<String, Integer> d_CountryNameIdMap;
	private HashMap<Integer, String> d_CountryIdNameMap;
	private HashMap<Integer, String> d_ContinentIdNameMap;

	/***
	 * This constructor initializes the map
	 */
	public Map() {
		d_CountryObjects = new ArrayList<Country>();
		d_ContinentObjects = new ArrayList<Continent>();
		d_AdjList = new HashMap<Integer, ArrayList<Integer>>();
		d_CountryNameIdMap = new HashMap<>();
		d_ContinentIdNameMap = new HashMap<>();
		d_CountryIdNameMap = new HashMap<>();
	}

	/**
	 * getter for list of country objects
	 * 
	 * @return list of country objects
	 */
	public ArrayList<Country> getD_CountryObjects() {
		return d_CountryObjects;
	}

	/**
	 * setter for list of country objects
	 * 
	 * @param p_CountryObjects list of country objects
	 */
	public void setD_CountryObjects(ArrayList<Country> p_CountryObjects) {
		this.d_CountryObjects = p_CountryObjects;
	}

	/**
	 * getter for list of continent objects
	 * 
	 * @return list of continent objects
	 */
	public ArrayList<Continent> getD_ContinentObjects() {
		return d_ContinentObjects;
	}

	/**
	 * setter for list of continent objects
	 * 
	 * @param p_ContinentObjects list of continent objects
	 */
	public void setD_ContinentObjects(ArrayList<Continent> p_ContinentObjects) {
		this.d_ContinentObjects = p_ContinentObjects;
	}

	/**
	 * getter for map adjacency list
	 * 
	 * @return adjacency list
	 */
	public HashMap<Integer, ArrayList<Integer>> getD_Neighbors() {
		return d_AdjList;
	}

	/**
	 * setter for adjacency list
	 * 
	 * @param p_Neighbors adjacency list
	 */
	public void setD_Neighbors(HashMap<Integer, ArrayList<Integer>> p_Neighbors) {
		this.d_AdjList = p_Neighbors;
	}

	/***
	 * This function empties every data structure when called and initialises the
	 * country and continent 0.
	 */
	public void reset() {
		d_CountryObjects.clear();
		d_ContinentObjects.clear();
		d_AdjList.clear();
		d_CountryNameIdMap.clear();
		d_ContinentIdNameMap.clear();
		d_CountryIdNameMap.clear();
		Continent.setD_Count(0);
		Country.setD_Count(0);

	}

	/***
	 * This method handles continent input and adds the continent to the list of the
	 * list of continents object.
	 * 
	 * @param p_ContinentName  this is the name of the continent
	 * @param p_ContinentValue this is the value of the continent
	 * @throws Exception it throws an exception if the continent is already present
	 *                   in the map
	 */
	public void addContinent(String p_ContinentName, int p_ContinentValue) throws Exception {
		// check continent already Exist
		if (continentAlreadyExist(p_ContinentName)) {
			throw new Exception("Continent Already Exist!!");
		} else {
			this.d_ContinentObjects.add(new Continent(p_ContinentName, p_ContinentValue));
		}
	}

	/***
	 * This method removes a continent, all the countries in that continent along
	 * cuts the ties of those countries with their neighbors.
	 * 
	 * @param p_ContinentName this is the name of the continent
	 * @throws Exception it throws an exception if the continent does not exist
	 */
	public void removeContinent(String p_ContinentName) throws Exception {
		if (!continentAlreadyExist(p_ContinentName)) {
			throw new Exception("Continent Doesnt exist!!");
		} else {
			Continent l_TempContinent = findContinentByName(p_ContinentName);
			List<Country> l_ContinentsCountries = new ArrayList(l_TempContinent.getD_CountryList());
			for (Country l_country : l_ContinentsCountries) {
				removeCountry(l_country.getD_CountryName());
			}
			this.d_ContinentObjects.remove(l_TempContinent);
		}
	}

	/***
	 * This is a method to check whether the continent exists in the map or not
	 * 
	 * @param p_ContinentName this is the name of the continent
	 * @return 'true' if the continent exists, otherwise 'false'
	 */
	public boolean continentAlreadyExist(String p_ContinentName) {
		for (Continent l_ContinentIterator : this.d_ContinentObjects) {
			if (l_ContinentIterator.getD_ContinentName().equalsIgnoreCase(p_ContinentName)) {
				return true;
			}
		}
		return false;
	}

	/***
	 * This method handles countries input and adds the countries to the list of the
	 * list of countries object
	 * 
	 * @param p_CountryName   this is the name of the country
	 * @param p_ContinentName this is the name of the continent where the country is
	 *                        located
	 * @throws Exception throws an exception if the country already exists or the
	 *                   given continent does not exist
	 */
	public void addCountries(String p_CountryName, String p_ContinentName) throws Exception {
		if (continentAlreadyExist(p_ContinentName)) {
			if (countryAlreadyExist(p_CountryName)) {
				throw new Exception("Country Already Exist!!");
			} else {
				Country l_TempCountry = new Country(p_CountryName, p_ContinentName);
				this.d_CountryIdNameMap.put(l_TempCountry.getD_CountryId(), l_TempCountry.getD_CountryName());
				this.d_CountryNameIdMap.put(l_TempCountry.getD_CountryName(), l_TempCountry.getD_CountryId());
				Continent l_CountrysContinent = findContinentByName(p_ContinentName);
				l_CountrysContinent.getD_CountryList().add(l_TempCountry);
				d_AdjList.putIfAbsent(Integer.valueOf(l_TempCountry.getD_CountryId()), new ArrayList<Integer>());
				this.d_CountryObjects.add(l_TempCountry);
			}
		} else {
			throw new Exception("Continent Doesnt Exist");
		}
	}

	/***
	 * This is a method to check whether the country exists in the map or not
	 * 
	 * @param p_CountryName this is the name of the country
	 * @return 'true' if the country exists, otherwise 'false'
	 */
	public boolean countryAlreadyExist(String p_CountryName) {
		for (Country l_CountryIterator : this.d_CountryObjects) {
			if (l_CountryIterator.getD_CountryName().equalsIgnoreCase(p_CountryName)) {
				return true;
			}
		}
		return false;
	}

	/***
	 * This is a method to establish a neighboring connection between two countries
	 * 
	 * @param p_CountryName  this is the name of the first country
	 * @param p_NeighborName this is the name of the country to be added as a
	 *                       neighbor
	 * @throws Exception if the country name is wrong
	 */
	public void addCountryNeighbour(String p_CountryName, String p_NeighborName) throws Exception {
		if (countryAlreadyExist(p_CountryName) && countryAlreadyExist(p_NeighborName)) {
			// find country object and add neighbor
			Country l_CountryObject = findCountryByName(p_CountryName);

			Country l_NeighborCountryObject = findCountryByName(p_NeighborName);

			d_AdjList.putIfAbsent(Integer.valueOf(l_CountryObject.getD_CountryId()), new ArrayList<Integer>());
			d_AdjList.get(Integer.valueOf(l_CountryObject.getD_CountryId()))
					.add(l_NeighborCountryObject.getD_CountryId());
			l_CountryObject.addNeighbours(p_NeighborName);

		} else {
			throw new Exception("Invalid Command Check country names");
		}

	}

	/***
	 * This method is used to remove a country and cut its ties with the neighbors
	 * 
	 * @param p_CountryName it is the name of the country to be deleted
	 * @throws Exception if the country does not exist on the map
	 */
	public void removeCountry(String p_CountryName) throws Exception {
		// 1 check country exist
		// 2 remove from country list
		// 3 remove country from continent country list
		// 4 remove neighbors;
		if (!countryAlreadyExist(p_CountryName)) {
			throw new Exception("Country Doesnt exist!!");
		}

		Country l_CountryObject = findCountryByName(p_CountryName);
		Continent l_CountrysContinent = findContinentByName(l_CountryObject.getD_CountryContinent());
		l_CountrysContinent.getD_CountryList().remove(l_CountryObject);

		List<String> l1 = new ArrayList(l_CountryObject.getD_Neighbors());
		for (String l_CountrysNeighbor : l1) {
			removeNeighbor(p_CountryName, l_CountrysNeighbor, true);

		}

		d_AdjList.remove(Integer.valueOf(l_CountryObject.getD_CountryId()));
		this.d_CountryObjects.remove(l_CountryObject);
	}

	/***
	 * IThis method is used to remove country's neighbor
	 * 
	 * @param p_CountryName  this is the name of the country
	 * @param p_NeighborName this is the name of its neighbor country
	 * @param p_Both         if 'true', it removes the connection from both the
	 *                       countries else, only removes the connection from the
	 *                       country itself
	 * @throws Exception throws an exception if the country or its neighbor does not
	 *                   exist
	 */
	public void removeNeighbor(String p_CountryName, String p_NeighborName, boolean p_Both) throws Exception {
		// 1 check country exits
		// 2 check neighbor exist
		// 3. check if they are neighbors
		// 4. remove from list
		if (!countryAlreadyExist(p_CountryName)) {
			throw new Exception("Country Doesnt exist!!");
		}
		if (!countryAlreadyExist(p_NeighborName)) {
			throw new Exception("Neighobr Country Doesnt exist!!");
		}

		Country p_TempCountry = findCountryByName(p_CountryName);
		p_TempCountry.getD_Neighbors().remove(p_NeighborName);
		Country p_TempNeighborCountry = findCountryByName(p_NeighborName);
		d_AdjList.get(Integer.valueOf(p_TempCountry.getD_CountryId()))
				.remove(Integer.valueOf(p_TempNeighborCountry.getD_CountryId()));

		if (p_Both == true) {
			p_TempNeighborCountry.getD_Neighbors().remove(p_CountryName);
			d_AdjList.get(Integer.valueOf(p_TempNeighborCountry.getD_CountryId()))
					.remove(Integer.valueOf(p_TempCountry.getD_CountryId()));
		}
	}

	/***
	 * This method saves the map file
	 * 
	 * @param p_FileName this is the map file name
	 * @throws Exception throws exception if the map is not valid
	 */
	public void saveFile(String p_FileName) throws Exception {
		String l_Path = "saved_maps\\";
		File l_File = new File(l_Path + p_FileName);
		FileWriter l_FileWriterObject = new FileWriter(l_File);
		PrintWriter l_PrintWriterObject = new PrintWriter(l_FileWriterObject);

		l_PrintWriterObject.println("Continents");
		if (this.d_ContinentObjects.size() < 0) {
			l_PrintWriterObject.close();
			throw new Exception("No continent to save");
		}
		for (Continent l_Continent : this.d_ContinentObjects) {
			l_PrintWriterObject.println(l_Continent.getD_ContinentName() + " " + l_Continent.getD_ContinentValue());
		}
		l_PrintWriterObject.println("");
		l_PrintWriterObject.println("Countries");

		for (Country l_Country : this.d_CountryObjects) {
			String l_CountryContinentName = l_Country.getD_CountryContinent();
			// saving country name and id in map so it can be find in o(1) later on
			this.d_CountryNameIdMap.put(l_Country.getD_CountryName(), l_Country.getD_CountryId());
			// searching for continent to get continent id
			Continent l_ContinentObject = findContinentByName(l_CountryContinentName);
			if (l_ContinentObject != null) {
				l_PrintWriterObject.println(l_Country.getD_CountryName() + " " + l_ContinentObject.getD_ContinentId());
			}
		}
		l_PrintWriterObject.println("");
		l_PrintWriterObject.println("Neighbors");
		for (Country l_Country : this.d_CountryObjects) {
			l_PrintWriterObject.print(l_Country.getD_CountryId() + " ");
			List<String> l_CountrysNeighbors = l_Country.getD_Neighbors();
			for (String l_NeighborIter : l_CountrysNeighbors) {
				int l_NeighborCountryId = this.d_CountryNameIdMap.get(l_NeighborIter);
				l_PrintWriterObject.print(l_NeighborCountryId + " ");
			}
			l_PrintWriterObject.println("");
		}
		l_PrintWriterObject.println("");
		l_PrintWriterObject.close();
		l_FileWriterObject.close();

	}

	/***
	 * This method finds the continent object
	 * 
	 * @param p_ContinentName this is the name of the continent to be found
	 * @return This method returns the continent object if it exists else 'null'
	 */
	public Continent findContinentByName(String p_ContinentName) {
		Continent l_Continent = null;
		for (Continent l_ContIter : this.d_ContinentObjects) {
			if (l_ContIter.getD_ContinentName().equalsIgnoreCase(p_ContinentName)) {
				l_Continent = l_ContIter;
				break;
			}
		}
		return l_Continent;
	}

	/***
	 * This method finds the country object
	 * 
	 * @param p_CountryName this is the name of the country to be found
	 * @return This method returns the country object if it exists else 'null'
	 *
	 */
	public Country findCountryByName(String p_CountryName) {
		Country l_Country = null;
		for (Country l_ContIter : this.d_CountryObjects) {
			if (l_ContIter.getD_CountryName().equalsIgnoreCase(p_CountryName)) {
				l_Country = l_ContIter;
				break;
			}
		}
		return l_Country;
	}

	/***
	 * This method loads the map file from the storage
	 * 
	 * @param p_FileName the file name to be loaded
	 * @throws Exception if the file name does not exist or if it is corrupted
	 */
	public void loadFile(String p_FileName) throws Exception {
		reset();
		String l_Path = "saved_maps\\";
		File l_File = new File(l_Path + p_FileName);
		Scanner l_Sc = new Scanner(l_File);
		while (l_Sc.hasNextLine()) {
			String l_LineInput = l_Sc.nextLine();
			// reading continents and adding to list
			if (l_LineInput.equalsIgnoreCase("Continents")) {
				l_LineInput = l_Sc.nextLine();
				while (!l_LineInput.equalsIgnoreCase("") && l_Sc.hasNextLine()) {
					String l_LineSplit[] = l_LineInput.split(" ");
					Continent l_TempContinent = new Continent(l_LineSplit[0], Integer.parseInt(l_LineSplit[1]));
					this.d_ContinentIdNameMap.put(Integer.valueOf(l_TempContinent.getD_ContinentId()),
							l_TempContinent.getD_ContinentName());
					this.d_ContinentObjects.add(l_TempContinent);
					l_LineInput = l_Sc.nextLine();
				}
			}
			if (l_LineInput.equalsIgnoreCase("Countries")) {
				l_LineInput = l_Sc.nextLine();
				while (!l_LineInput.equalsIgnoreCase("") && l_Sc.hasNextLine()) {
					String l_LineSplit[] = l_LineInput.split(" ");
					String l_ContinentName = this.d_ContinentIdNameMap.get(Integer.parseInt(l_LineSplit[1]));
					Country l_TempCountry = new Country(l_LineSplit[0], l_ContinentName);

					Continent l_TemoContinent = findContinentByName(l_ContinentName);
					l_TemoContinent.getD_CountryList().add(l_TempCountry);
					this.d_CountryIdNameMap.put(Integer.valueOf(l_TempCountry.getD_CountryId()),
							l_TempCountry.getD_CountryName());
					this.d_CountryObjects.add(l_TempCountry);
					l_LineInput = l_Sc.nextLine();
				}
			}
			if (l_LineInput.equalsIgnoreCase("Neighbors")) {
				l_LineInput = l_Sc.nextLine();
				while (!l_LineInput.equalsIgnoreCase("") && l_Sc.hasNextLine()) {
					String l_LineSplit[] = l_LineInput.split(" ");
					String l_CountryName = d_CountryIdNameMap.get(Integer.parseInt(l_LineSplit[0]));
					for (int l_NeighborIterator = 1; l_NeighborIterator < l_LineSplit.length; l_NeighborIterator++) {
						String l_NeighborName = d_CountryIdNameMap
								.get(Integer.parseInt(l_LineSplit[l_NeighborIterator]));
						addCountryNeighbour(l_CountryName, l_NeighborName);
					}
					l_LineInput = l_Sc.nextLine();
				}
			}
		}
		l_Sc.close();
	}

	/***
	 * This method validates the map
	 * 
	 * @throws Exception if map is invalid
	 */
	public void ValidateMap() throws Exception {
		// 1 generate adjacency list
		// 2 check all COUNTRIES INSIDE continents is connected
		// 3. check whole map is connected
		if (checkCountriesInsideContinentIsConnected() && checkWholeMapIsConnectedSubGraph()) {

		} else
			throw new Exception("");
	}

	/***
	 * This method checks that each continent is a connected component or not
	 * 
	 * @return 'true' if it is a connected component, else 'false'
	 * @throws Exception if the continent does not have any country
	 */
	public boolean checkCountriesInsideContinentIsConnected() throws Exception {
		for (Continent l_Continent : this.d_ContinentObjects) {
			ArrayList<Country> l_ContinentsCountry = l_Continent.getD_CountryList();
			if (l_ContinentsCountry.size() == 0) {
				throw new Exception("Continent " + l_Continent.getD_ContinentName() + " doesnt contain any country");
			}
			if (!checkCountriesIsConnected(l_ContinentsCountry))
				return false;
		}
		return true;
	}

	/***
	 * This method checks the countries present within a continent is a connected
	 * graph or not
	 * 
	 * @param p_Countries this is a list of countries present in the continent
	 * @return 'true' if it is a connected component, else 'false'
	 */
	public boolean checkCountriesIsConnected(ArrayList<Country> p_Countries) {

		HashMap<Integer, ArrayList<Integer>> l_AdjListCountries = new HashMap<>();
		for (Country l_Country : p_Countries) {
			l_AdjListCountries.putIfAbsent(Integer.valueOf(l_Country.getD_CountryId()), new ArrayList<Integer>());
			for (String l_Neighbor : l_Country.getD_Neighbors()) {
				Country l_neighbor = findCountryByName(l_Neighbor);
				if (p_Countries.indexOf(l_neighbor) >= 0)
					l_AdjListCountries.get(l_Country.getD_CountryId())
							.add(findCountryByName(l_Neighbor).getD_CountryId());
			}
		}
		// creating visited array
		HashMap<Integer, Boolean> l_Visited = new HashMap<>();

		for (java.util.Map.Entry<Integer, ArrayList<Integer>> es : l_AdjListCountries.entrySet()) {
			l_Visited.put(Integer.valueOf(es.getKey()), false);
		}

		// checking
		int l_NoOfComponents = 0;
		for (java.util.Map.Entry<Integer, ArrayList<Integer>> es : l_AdjListCountries.entrySet()) {
			if (!l_Visited.get(es.getKey())) {
				DFS(es.getKey(), l_Visited, l_AdjListCountries);
				l_NoOfComponents++;
			}
		}
		if (l_NoOfComponents != 1) {
			return false;
		}
		return true;

	}

	/***
	 * This method checks the whole map is a connected graph or not
	 * 
	 * @return 'true if is connected, else 'false'
	 */
	public boolean checkWholeMapIsConnectedSubGraph() {
		HashMap<Integer, Boolean> l_Visited = new HashMap<>();

		for (java.util.Map.Entry<Integer, ArrayList<Integer>> es : d_AdjList.entrySet()) {
			l_Visited.put(Integer.valueOf(es.getKey()), false);
		}
		int l_NoOfComponents = 0;
		for (java.util.Map.Entry<Integer, ArrayList<Integer>> es : d_AdjList.entrySet()) {
			if (!l_Visited.get(Integer.valueOf(es.getKey()))) {
				DFS(es.getKey(), l_Visited);
				l_NoOfComponents++;
			}
		}
		if (l_NoOfComponents != 1) {
			return false;
		}
		return true;
	}

	/***
	 * This is a utility method to perform DFS traversal on map
	 * 
	 * @param pStartNode this is the starting node
	 * @param p_Visited  this is the list of visited node
	 * @param p_AdjList  this is the adjacency list of the continent
	 */
	public void DFS(int pStartNode, HashMap<Integer, Boolean> p_Visited,
			HashMap<Integer, ArrayList<Integer>> p_AdjList) {
		p_Visited.put(Integer.valueOf(pStartNode), true);
		for (int l_NeighborNode : p_AdjList.get(pStartNode)) {
			if (!p_Visited.get(Integer.valueOf(l_NeighborNode))) {
				DFS(l_NeighborNode, p_Visited, p_AdjList);
			}
		}

	}

	/***
	 * This is a utility method to perform DFS traversal on map
	 * 
	 * @param pStartNode this is the starting node
	 * @param p_Visited  this is the list of visited node
	 */
	public void DFS(int pStartNode, HashMap<Integer, Boolean> p_Visited) {
		p_Visited.put(Integer.valueOf(pStartNode), true);
		for (int l_NeighborNode : d_AdjList.get(Integer.valueOf(pStartNode))) {
			if (!p_Visited.get(Integer.valueOf(l_NeighborNode))) {
				DFS(l_NeighborNode, p_Visited);
			}
		}

	}

	/***
	 * This method shows the players and the countries and continents owned by them
	 * along with the reinforcement armies and deployed armies
	 */
	public void showMap() {
		System.out.println("------------------------------------------------");
		for (Continent l_ContinentIterator : d_ContinentObjects) {
			System.out.println("Continent: " + l_ContinentIterator.getD_ContinentName() + " value =>  "
					+ l_ContinentIterator.getD_ContinentValue());
			if (d_CountryObjects.size() > 0) {
				System.out.println("Countries:");
				for (Country l_CountryIterator : d_CountryObjects) {
					if (l_CountryIterator.getD_CountryContinent()
							.equalsIgnoreCase(l_ContinentIterator.getD_ContinentName())) {
						System.out.print(l_CountryIterator.getD_CountryName() + " Neighbors: ");
						for (String neighbors : l_CountryIterator.getD_Neighbors()) {
							System.out.print(neighbors + ", ");
						}
						System.out.println();
					}
				}
			}
			System.out.println("------------------------------------------------");
		}
	}
}
