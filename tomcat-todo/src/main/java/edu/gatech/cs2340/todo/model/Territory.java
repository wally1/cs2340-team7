package edu.gatech.cs2340.todo.model;

import edu.gatech.cs2340.todo.model.Player;
import edu.gatech.cs2340.todo.model.Resource;
import java.util.*;

public class Territory{
	
	String name; //Name of territory, ie states/countries
	int[] coordinates; //coordinate of Territory, chessboard style. length = 2, just 2 numbers. May be awkward if board is hexes and not squares
	String region; //Board is split up into countries; which country this territory belongResourcecontinents
	ArrayList<Resource> resources; //Resources this particular territory possesses, ie additional money to purchase troops, upgrades for troops, map information
	boolean isOccupied; //true if there is a unit occupying the territory -> presence enough to occupy or need to spend turns capping?
	ArrayList<Unit> occupiedByUnit; //the unit occupying this territory
	Player occupiedByPlayer; //the player who occupies this territory 
	boolean occupiable;
	boolean homeBase;

public Territory(String name, int[] coords, String reg){
	
	this.name=name;
	coordinates = coords;
	region = reg;
	resources = new ArrayList<Resource>();
	isOccupied = false;
    occupiedByUnit = new ArrayList<Unit>();
	occupiedByPlayer = null;
	occupiable = true;
	homeBase = false;
	}
	//if the territory is occupied by an asteroid
	public void makeNotOccupiable(){
	    occupiable = false;
	}  
	//the "home base" of the player. New units will spawn adjacent to the home base and if it is "conquered", that player loses.
	public void makeHomeBase(Player player){
	    homeBase = true;
	    occupiedByPlayer = player;
    } 
	public boolean isHomeBase()
	{
		return homeBase;
	}

	public void addResource(Resource treasure)
	{
		resources.add(treasure);
		if (treasure.getName().equals("Asteroid"))
			occupiable = false;
	}
	public boolean hasResources()
	{
		if (resources.size()>0)
			return true;
		else 
			return false;
	}
	
	//player parameter slightly redundant
	public void addUnits(Unit conquerer, int amount, Player occupant)
	{
		if(occupiable)// not an asteroid
		{
			//if territory is already occupied by another player, then fight
			if(isOccupied && !occupiedByPlayer.equals(occupant)) 
			{
				//fight
			}
			else
			{
				isOccupied = true;
				for(int a = 0; a< amount;a++)
					occupiedByUnit.add(conquerer);
				occupiedByPlayer = occupant;
			}
		}
		else
			System.out.println("There's something in the way!");
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
	public ArrayList<Unit> getOccupants()
	{
		return occupiedByUnit;
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