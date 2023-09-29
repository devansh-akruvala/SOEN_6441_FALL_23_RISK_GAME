package com.soen6441.risk_game_u14.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * 
 * 
 *@author Devansh Smridhi
 * 
 */
public class Map {
	private ArrayList<Continent> d_ContinentObjects;
	private ArrayList<Country> d_CountryObjects; 
	private HashMap<Integer,ArrayList<Integer>> d_Neighbors;
	private HashMap<Integer,Integer>d_PreviousSave;
	private HashMap<String,Integer>d_CountryNameIdMap;
	public Map(){
		d_CountryObjects=new ArrayList<Country>();
		d_ContinentObjects=new ArrayList<Continent>();
		d_Neighbors=new HashMap<Integer,ArrayList<Integer>>();
		d_PreviousSave=new HashMap<Integer,Integer>();
		d_CountryNameIdMap=new HashMap<>();
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
	}
	
	public void addContinent(String p_ContinentName,int p_ContinentValue) throws Exception {
		//check continent already Exist
		if(continentAlreadyExist(p_ContinentName)) {
			throw new Exception("Continent Already Exist!!");
		}
		else {
			this.d_ContinentObjects.add(new Continent(p_ContinentName,p_ContinentValue));
		}
	}
	
	public boolean continentAlreadyExist(String p_ContinentName) {
		for(Continent l_ContinentIterator:this.d_ContinentObjects ) {
			if(l_ContinentIterator.getD_ContinentName().equalsIgnoreCase(p_ContinentName)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void addCountries(String p_CountryName,String p_ContinentName) throws Exception {
		if(continentAlreadyExist(p_ContinentName)) {
			if(countryAlreadyExist(p_CountryName)) {
				throw new Exception("Country Already Exist!!");
			}
			else {
				this.d_CountryObjects.add(new Country(p_CountryName, p_ContinentName));
			}
		}else {
			throw new Exception("Continent Doesnt Exist");
		}
	}
	
	public boolean countryAlreadyExist(String p_CountryName) {
		for(Country l_CountryIterator:this.d_CountryObjects ) {
			if(l_CountryIterator.getD_CountryName().equalsIgnoreCase(p_CountryName)) { 
				return true;
			}
		}
		return false;
	}
	
	public void addCountryNeighbour(String p_CountryName,String p_NeighborName) throws Exception {
		if(countryAlreadyExist(p_CountryName) && countryAlreadyExist(p_NeighborName)) {
			//find country object and add neighbor
			Country l_CountryObject=null;
			for(Country l_CountryIterator:this.d_CountryObjects ) {
				if(l_CountryIterator.getD_CountryName().equalsIgnoreCase(p_CountryName)) {
					l_CountryObject=l_CountryIterator;
					break;
				}
			}
			l_CountryObject.addNeighbours(p_NeighborName);
			
		}
		else {
			throw new Exception("Invalid Command Check country nanes");
		}
		
	}



public void removeCountry(String p_CountryName) throws Exception {
    boolean removed = false;
    Iterator<Country> iterator = d_CountryObjects.iterator();

    while (iterator.hasNext()) {
        Country country = iterator.next();
        if (country.getD_CountryName().equalsIgnoreCase(p_CountryName)) {
            iterator.remove(); // Remove country from list
            removed = true;
            break; //if country found
        }
    }

    if (!removed) {
        throw new Exception("Country doesn't exist");
    }
}


	public String saveFile(String p_FileName) throws Exception {
		
		String l_Path="saved_maps\\";
		File l_File=new File(l_Path+p_FileName);
		FileWriter l_FileWriterObject = new FileWriter(l_File);
		PrintWriter l_PrintWriterObject = new PrintWriter(l_FileWriterObject);
		
		
		l_PrintWriterObject.println("Continents");
		if(this.d_ContinentObjects.size()<0) {
			l_PrintWriterObject.close();
			throw new Exception("No continent to save");
		}
		for(Continent l_Continent:this.d_ContinentObjects) {
			l_PrintWriterObject.println(l_Continent.getD_ContinentName()+" "+l_Continent.getD_ContinentValue());
		}
		l_PrintWriterObject.println("");		
		l_PrintWriterObject.println("Countries");

		
		for(Country l_Country:this.d_CountryObjects) {
			String l_CountryContinentName = l_Country.getD_CountryContinent();
			// saving country name and id in map so it can be find in o(1) later on
			this.d_CountryNameIdMap.put(l_Country.getD_CountryName(),l_Country.getD_CountryId());
			// searching for continent to get continent id
			Continent l_ContinentObject = findContinentByName(l_CountryContinentName);
			if(l_ContinentObject!=null) {
				l_PrintWriterObject.println(l_Country.getD_CountryName()+" "+l_ContinentObject.getD_ContinentId());
			}	
		}
		l_PrintWriterObject.println("");		
		l_PrintWriterObject.println("Neighbors");
	
		for(Country l_Country:this.d_CountryObjects) {
			l_PrintWriterObject.print(l_Country.getD_CountryId()+" ");
			List<String> l_CountrysNeighbors = l_Country.getD_Neighbors();
			for(String l_NeighborIter:l_CountrysNeighbors) {
				int l_NeighborCountryId = this.d_CountryNameIdMap.get(l_NeighborIter);
				l_PrintWriterObject.print(l_NeighborCountryId+" ");
			}
			l_PrintWriterObject.println("");
		}
		
		l_PrintWriterObject.close();
		l_FileWriterObject.close();
		return "Map Saved SuccessFully";
	}


	public Continent findContinentByName(String p_ContinentName) {
		Continent l_Continent = null;
		for(Continent l_ContIter: this.d_ContinentObjects) {
			if(l_ContIter.getD_ContinentName().equalsIgnoreCase(p_ContinentName)) {
				l_Continent = l_ContIter;
				break;
			}
		}
		return l_Continent;
	}

	
	public void showMap() {
		System.out.println("------------------------------------------------");
		for(Continent l_ContinentIterator:d_ContinentObjects) {
			System.out.println("Continent: "+l_ContinentIterator.getD_ContinentName()+" value =>  "+l_ContinentIterator.getD_ContinentValue());
			if(d_CountryObjects.size()>0) {
				System.out.println("Countries:");
				for(Country l_CountryIterator: d_CountryObjects) {
					if(l_CountryIterator.getD_CountryContinent().equalsIgnoreCase(l_ContinentIterator.getD_ContinentName())) {
						System.out.print(l_CountryIterator.getD_CountryName()+ " Neighbors: ");
						for(String neighbors:l_CountryIterator.getD_Neighbors()) {
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
