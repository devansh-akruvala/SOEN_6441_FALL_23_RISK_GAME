package com.soen6441.risk_game_u14.model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 
 * 
 *@author Devansh 
 * 
 */
public class Map {
	private ArrayList<Continent> d_ContinentObjects;
	private ArrayList<Country> d_CountryObjects; 
	private HashMap<Integer,ArrayList<Integer>> d_Neighbors;
	private HashMap<Integer,Integer>d_PreviousSave;

	public Map(){
		d_CountryObjects=new ArrayList<Country>();
		d_ContinentObjects=new ArrayList<Continent>();
		d_Neighbors=new HashMap<Integer,ArrayList<Integer>>();
		d_PreviousSave=new HashMap<Integer,Integer>();
	}

	public ArrayList<Country> getD_CountryObjects() {
		return d_CountryObjects;
	}

	public void setD_CountryObjects(ArrayList<Country> d_CountryObjects) {
		this.d_CountryObjects = d_CountryObjects;
	}

	public ArrayList<Continent> getD_ContinentObjects() {
		return d_ContinentObjects;
	}

	public void setD_ContinentObjects(ArrayList<Continent> d_ContinentObjects) {
		this.d_ContinentObjects = d_ContinentObjects;
	}

	public HashMap<Integer, ArrayList<Integer>> getD_Neighbors() {
		return d_Neighbors;
	}

	public void setD_Neighbors(HashMap<Integer, ArrayList<Integer>> d_Neighbors) {
		this.d_Neighbors = d_Neighbors;
	}

	public HashMap<Integer, Integer> getD_PreviousSave() {
		return d_PreviousSave;
	}

	public void setD_PreviousSave(HashMap<Integer, Integer> d_PreviousSave) {
		this.d_PreviousSave = d_PreviousSave;
	}
	
	public void reset() {
		d_CountryObjects.clear();
		d_ContinentObjects.clear();
		d_Neighbors.clear();
		d_PreviousSave.clear();
	}
	
	public void addContinent(String continentName,int continentValue) throws Exception {
		//check continent already Ecist
		if(continentAlreadyExist(continentName)) {
			throw new Exception("Contient Already Exist!!");
		}
		else {
			this.d_ContinentObjects.add(new Continent(continentName,continentValue));
		}
	}
	
	public boolean continentAlreadyExist(String continentName) {
		for(Continent continentIterator:this.d_ContinentObjects ) {
			if(continentIterator.getD_ContinentName().equalsIgnoreCase(continentName)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void addCountries(String countryName,String continentName) throws Exception {
		if(continentAlreadyExist(continentName)) {
			if(countryAlreadyExist(countryName)) {
				throw new Exception("Country Already Exist!!");
			}
			else {
				this.d_CountryObjects.add(new Country(countryName, continentName));
			}
		}else {
			throw new Exception("Continent Doesnt Exist");
		}
	}
	
	public boolean countryAlreadyExist(String countryName) {
		for(Country countryIterator:this.d_CountryObjects ) {
			if(countryIterator.getD_CountryName().equalsIgnoreCase(countryName)) {
				System.out.println(countryIterator.getD_CountryName() +" NAme"); 
				return true;
			}
		}
		return false;
	}
	
	public void addCountryNeighbour(String countryName,String neighborname) throws Exception {
		if(countryAlreadyExist(countryName) && countryAlreadyExist(neighborname)) {
			//find country object and add neighbor
			Country c1=null;
			for(Country countryIterator:this.d_CountryObjects ) {
				if(countryIterator.getD_CountryName().equalsIgnoreCase(countryName)) {
					c1=countryIterator;
					break;
				}
			}
			c1.addNeighbours(neighborname);
			
		}
		else {
			throw new Exception("Invalid Command Check country nanes");
		}
		
	}
	
	public void showMap() {
		System.out.println("------------------------------------------------");
		for(Continent continentIterator:d_ContinentObjects) {
			System.out.println("Continent: "+continentIterator.getD_ContinentName()+" value =>  "+continentIterator.getD_ContinentValue());
			if(d_CountryObjects.size()>0) {
				System.out.println("Countries:");
				for(Country countryIterator: d_CountryObjects) {
					if(countryIterator.getD_CountryContinent().equalsIgnoreCase(continentIterator.getD_ContinentName())) {
						System.out.print(countryIterator.getD_CountryName()+ " Neighbors: ");
						for(String neighbors:countryIterator.getD_Neighbors()) {
							System.out.print(neighbors+", ");
						}
						System.out.println();
					}
				}
			}
			System.out.println("------------------------------------------------");
		}
	}
}
