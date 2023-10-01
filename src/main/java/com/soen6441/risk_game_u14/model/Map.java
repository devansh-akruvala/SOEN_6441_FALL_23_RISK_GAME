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
 * 
 * 
 * @author Devansh Smridhi Meshva
 * 
 */
public class Map {
	private ArrayList<Continent> d_ContinentObjects;
	private ArrayList<Country> d_CountryObjects;
	private HashMap<Integer, ArrayList<Integer>> d_Neighbors;
	private HashMap<Integer, Integer> d_PreviousSave;
	private HashMap<String, Integer> d_CountryNameIdMap;
	private HashMap<Integer, String> d_CountryIdNameMap;
	private HashMap<Integer, String> d_ContinentIdNameMap;

	public Map() {
		d_CountryObjects = new ArrayList<Country>();
		d_ContinentObjects = new ArrayList<Continent>();
		d_Neighbors = new HashMap<Integer, ArrayList<Integer>>();
		d_PreviousSave = new HashMap<Integer, Integer>();
		d_CountryNameIdMap = new HashMap<>();
		d_ContinentIdNameMap = new HashMap<>();
		d_CountryIdNameMap = new HashMap<>();
	}

	public ArrayList<Country> getD_CountryObjects() {
		return d_CountryObjects;
	}

	public void setD_CountryObjects(ArrayList<Country> p_CountryObjects) {
		this.d_CountryObjects = p_CountryObjects;
	}

	public ArrayList<Continent> getD_ContinentObjects() {
		return d_ContinentObjects;
	}

	public void setD_ContinentObjects(ArrayList<Continent> p_ContinentObjects) {
		this.d_ContinentObjects = p_ContinentObjects;
	}

	public HashMap<Integer, ArrayList<Integer>> getD_Neighbors() {
		return d_Neighbors;
	}

	public void setD_Neighbors(HashMap<Integer, ArrayList<Integer>> p_Neighbors) {
		this.d_Neighbors = p_Neighbors;
	}

	public HashMap<Integer, Integer> getD_PreviousSave() {
		return d_PreviousSave;
	}

	public void setD_PreviousSave(HashMap<Integer, Integer> p_PreviousSave) {
		this.d_PreviousSave = p_PreviousSave;
	}

	public void reset() {
		d_CountryObjects.clear();
		d_ContinentObjects.clear();
		d_Neighbors.clear();
		d_PreviousSave.clear();
		d_CountryNameIdMap.clear();
		d_ContinentIdNameMap.clear();
		d_CountryIdNameMap.clear();
	}

	public void addContinent(String p_ContinentName, int p_ContinentValue) throws Exception {
		// check continent already Exist
		if (continentAlreadyExist(p_ContinentName)) {
			throw new Exception("Continent Already Exist!!");
		} else {
			this.d_ContinentObjects.add(new Continent(p_ContinentName, p_ContinentValue));
		}
	}
	
	public void removeContinent(String p_ContinentName) throws Exception {
		if (!continentAlreadyExist(p_ContinentName)) {
			throw new Exception("Continent Doesnt exist!!");
		} else {
			Continent l_TempContinent =  findContinentByName(p_ContinentName);
			List<Country> l_ContinentsCountries = new ArrayList(l_TempContinent.getD_CountryList());
			for(Country l_country: l_ContinentsCountries) {
				removeCountry(l_country.getD_CountryName());
			}
			this.d_ContinentObjects.remove(l_TempContinent);
		}
	}

	public boolean continentAlreadyExist(String p_ContinentName) {
		for (Continent l_ContinentIterator : this.d_ContinentObjects) {
			if (l_ContinentIterator.getD_ContinentName().equalsIgnoreCase(p_ContinentName)) {
				return true;
			}
		}
		return false;
	}

	public void addCountries(String p_CountryName, String p_ContinentName) throws Exception {
		if (continentAlreadyExist(p_ContinentName)) {
			if (countryAlreadyExist(p_CountryName)) {
				throw new Exception("Country Already Exist!!");
			} else {
				Country l_TempCountry = new Country(p_CountryName, p_ContinentName);
				this.d_CountryIdNameMap.put(l_TempCountry.getD_CountryId(),l_TempCountry.getD_CountryName());
				this.d_CountryNameIdMap.put(l_TempCountry.getD_CountryName(),l_TempCountry.getD_CountryId());
				Continent l_CountrysContinent =  findContinentByName(p_ContinentName);
				l_CountrysContinent.getD_CountryList().add(l_TempCountry);
				this.d_CountryObjects.add(l_TempCountry);
			}
		} else {
			throw new Exception("Continent Doesnt Exist");
		}
	}
	
	

	public boolean countryAlreadyExist(String p_CountryName) {
		for (Country l_CountryIterator : this.d_CountryObjects) {
			if (l_CountryIterator.getD_CountryName().equalsIgnoreCase(p_CountryName)) {
				return true;
			}
		}
		return false;
	}

	public void addCountryNeighbour(String p_CountryName, String p_NeighborName) throws Exception {
		if (countryAlreadyExist(p_CountryName) && countryAlreadyExist(p_NeighborName)) {
			// find country object and add neighbor
			Country l_CountryObject = null;
			for (Country l_CountryIterator : this.d_CountryObjects) {
				if (l_CountryIterator.getD_CountryName().equalsIgnoreCase(p_CountryName)) {
					l_CountryObject = l_CountryIterator;
					break;
				}
			}
			
			Country l_NeighborCountryObject = findCountryByName(p_NeighborName);
			//this.d_Neighbors.get(l_CountryObject.getD_CountryId()).add(l_NeighborCountryObject.getD_CountryId());
			l_CountryObject.addNeighbours(p_NeighborName);

		} else {
			throw new Exception("Invalid Command Check country names");
		}

	}

	public void removeCountry(String p_CountryName) throws Exception {
		// 1 cehck country exist
		// 2 remove from country list
		// 3 remove country from contiennst country list
		// 4 remove neighbors;
		if (!countryAlreadyExist(p_CountryName)) {
			throw new Exception("Country Doesnt exist!!");
		}
		
		Country l_CountryObject = findCountryByName(p_CountryName);
		
		Continent l_CountrysContinent = findContinentByName(l_CountryObject.getD_CountryContinent());
		l_CountrysContinent.getD_CountryList().remove(l_CountryObject);
		
		
		List<String> l1 = new ArrayList(l_CountryObject.getD_Neighbors());
		for(String l_CountrysNeighbor : l1) {
			removeNeighbor(p_CountryName,l_CountrysNeighbor, true);
		
		}
		
		this.d_CountryObjects.remove(l_CountryObject);
	}
	
	
	public void removeNeighbor(String p_CountryName, String p_NeighborName , boolean p_Both ) throws Exception {
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
		
		if(p_Both==true) {
			Country p_TempNeighborCountry = findCountryByName(p_NeighborName);
			p_TempNeighborCountry.getD_Neighbors().remove(p_CountryName);
		}
	}

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
					this.d_ContinentIdNameMap.put(l_TempContinent.getD_ContinentId(),
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
					this.d_CountryIdNameMap.put(l_TempCountry.getD_CountryId(), l_TempCountry.getD_CountryName());
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
