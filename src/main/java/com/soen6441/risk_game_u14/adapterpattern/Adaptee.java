package com.soen6441.risk_game_u14.adapterpattern;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.soen6441.risk_game_u14.controller.GameEngine;
import com.soen6441.risk_game_u14.model.Continent;
import com.soen6441.risk_game_u14.model.Country;

/**
 * This is the Adaptee class which has loadmap and savemap functions for the conquest map type. 
 * Adptee's functionality is similar to the target but it is used for another type of map file.
 */	
public class Adaptee {
	/**
	 * This method load another type of map file called the conquest file.
	 * After reading from the conquest file, it stores in to the same Map object which is created for the domination file type.
	 * @param p_S argument which contains the name of the conquest map file
	 * @param p_GameEngine GameEngine object which will internally have Map object in which we will load all the continents, countries and their borders. 
	 * @return success or error for the implementation of this function
	 */
	
	/**
	 * This method saves another type of map in the file type called the conquest map file.
	 * For each continent, country and their respective border stored in the Map object inside GameEngine, it saves them into the file at their respective places.
	 * @param p_S argument This argument contains name of the file inside we want to save. 
	 * @param p_GameEngine GameEngine object which will internally have Map object from which we will save all the continents, countries and their borders. 
	 * @return success or error for the implementation of this function
	 */
	public String saveConquestMap(String p_S,GameEngine p_GameEngine) {
		try {
			String l_Result=p_GameEngine.getD_MapController().validateMap();
			if(" Map is not valid!!".equals(l_Result)){
				return l_Result;
			}
			String l_Path="saved_maps\\";
			ArrayList<String> l_Borders;
			File l_File=new File(l_Path+p_S);
			FileWriter l_Fw = new FileWriter(l_File);
			PrintWriter l_Pr = new PrintWriter(l_Fw);
			l_Pr.println("");
			l_Pr.println("[Continents]");
			if(p_GameEngine.getD_GameModel().getD_Map().getD_ContinentObjects().size()<=0) {
				l_Pr.close();
				throw new Exception("No Continent to Save");
			}
			//adding all the continents in the file
			for(Continent l_Co: p_GameEngine.getD_GameModel().getD_Map().getD_ContinentObjects()){
				l_Pr.println(l_Co.getD_ContinentName()+"="+l_Co.getD_ContinentValue());
			}
			l_Pr.println("");
			l_Pr.println("[Territories]");
			for(Country l_C: p_GameEngine.getD_GameModel().getD_Map().getD_CountryObjects()){
				String l_ContinentName = l_C.getD_CountryContinent();
				l_Borders=l_C.getD_Neighbors();
				l_Pr.print(l_C.getD_CountryName()+",0,0,"+l_ContinentName);
				for(String l_Co : l_Borders)
				{
					l_Pr.print(","+l_Co);
				}
				l_Pr.println("");
			}
			l_Pr.println("");

			l_Pr.close();
			l_Fw.close();
		}catch(Exception p_E){

		}
		return "Map has been saved succesfully\n";
	}
	
	public String loadConquestMap(String p_S,GameEngine p_GameEngine) {
		try {
			p_GameEngine.getD_GameModel().getD_Map().reset();
			String l_Path="saved_maps\\";
			File l_File =new File(l_Path+p_S);
			Scanner l_Sc = new Scanner(l_File);
			while(l_Sc.hasNextLine()){
				String l_Line=l_Sc.nextLine();
				if(l_Line.contains("[Continents]")){	
					l_Line=l_Sc.nextLine();
					while(!"".equals(l_Line) && l_Sc.hasNextLine()){
						String[] l_Arr = l_Line.split("=", 2);
						p_GameEngine.getD_GameModel().getD_Map().addContinent(l_Arr[0],Integer.parseInt(l_Arr[1]));
						l_Line=l_Sc.nextLine();
					}
				}
				if(l_Line.contains("[Territories]")){
					l_Line=l_Sc.nextLine();
					while(!"".equals(l_Line) && l_Sc.hasNextLine()){
						String[] l_Arr1=l_Line.split(",");
						p_GameEngine.getD_GameModel().getD_Map().addCountries(l_Arr1[0], l_Arr1[3]);
						l_Line=l_Sc.nextLine();
					}
				}
			}
			File l_File2 =new File(l_Path+p_S);
			Scanner l_Sc2 = new Scanner(l_File2);
			while(l_Sc2.hasNextLine()){
				String l_Line=l_Sc2.nextLine();
				if(l_Line.contains("Territories")){
					l_Line=l_Sc2.nextLine();
					while(!"".equals(l_Line) && l_Sc2.hasNextLine()){
						String[] l_Arr1=l_Line.split(",");
						for(int l_K=4;l_K<l_Arr1.length;l_K++){
							p_GameEngine.getD_GameModel().getD_Map().addCountryNeighbour(l_Arr1[0], l_Arr1[l_K]);
						}
						l_Line=l_Sc2.nextLine();
					}
				}
			}
			l_Sc.close();
			l_Sc2.close();
			String l_Result=p_GameEngine.getD_MapController().validateMap();
			if(" Map is not valid!!".equals(l_Result)){
				return l_Result;
			}
		}catch(Exception p_E){}
		return "Map file loaded successfully!!";
	}

}
