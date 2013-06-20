package edu.gatech.cs2340.todo.model;

import edu.gatech.cs2340.todo.model.Player;
import edu.gatech.cs2340.todo.model.Resource;
import java.util.*;

public class Territory{
	
	String name; //Name of territory, ie states/countries
	int[] coordinates; //coordinate of Territory, chessboard style. length = 2, just 2 numbers. May be awkward if board is hexes and not squares
	String region; //Board is split up into regions; which region this territory belongResourcecontinents
	ArrayList<Resource> resources; //Resources this particular territory possesses, ie additional money to purchase troops, upgrades for troops, map information
	boolean isOccupied; //true if there is a unit occupying the territory -> presence enough to occupy or need to spend turns capping?
	Unit occupiedByUnit; //the unit occupying this territory
	Player occupiedByPlayer; //the player who occupies this territory 
	boolean occupiable;
	boolean homeBase;
	
	public Territory(String name, int[] coords, String reg)
	{
		this.name = name;
		coordinates = coords;
		region = reg; //during initialization of all territories, should be added in with coords
		resources = new ArrayList<Resource>();
		isOccupied = false;
//		occupiedByUnit = null;
		occupiedByPlayer = null;
		occupiable = true;
		homeBase = false;
	}
	public void makeNotOccupiable(boolean occupy){
		occupiable = true;
	}
	
	public void makeHomeBase(boolean isHome){
		homeBase = true;
	}
	
	public void spawnWith(Resource treasure)
	{
		resources.add(treasure);
	}
	public boolean hasResources()
	{
		if (resources.size()>0)
			return true;
		else 
			return false;
	}
	public void occupy(Unit conquerer, Player occupant)
	{
		if(isOccupied)
		{
			//two Units trying to occupy same territory, if same Player nothing happens
			//if different player then units fight
		}
		else
		{
		isOccupied = true;
		occupiedByUnit = conquerer;
		occupiedByPlayer = occupant;
		}
	}
	public String getName(){
		return name;
	}
	public int[] getCoords(){
		return coordinates;
	}

	public Boolean isOccupied()
	{
		return isOccupied;
	}
	public Player getPlayer()
	{
		return occupiedByPlayer;
	}
	public String toString()
	{
		return "This is "+name+" at coordinates "+coordinates[0]+","+coordinates[1]+" from region "+region+".";
	}
	
	
	
	
	
	
	
}