package edu.gatech.cs2340.todo.model;



public class Territory{
	
	String name; //Name of territory, ie states/countries
	int[] coordinates; //coordinate of Territory, chessboard style. length = 2, just 2 numbers. May be awkward if board is hexes and not squares
	String region; //Board is split up into regions; which region this territory belongs to ie continents
	//ArrayList<Resource> resources; //Resources this particular territory possesses, ie additional money to purchase troops, upgrades for troops, map information
	Boolean isOccupied; //true if there is a unit occupying the territory -> presence enough to occupy or need to spend turns capping?
//	Unit occupiedByUnit; //the unit occupying this territory
//	Player occupiedByPlayer; //the player who occupies this territory 
	
	public Territory(String name, int[] coords)
	{
		this.name = name;
		coordinates = coords;
		region = "";
	//	resources = new ArrayList<Resource>();
		isOccupied = false;
	//	occupiedByUnit = null;
	//	occupiedByPlayer = null;
	}
	public String getName(){
		return name;
	}
	public int[] getCoords(){
		return coordinates;
	}
	
	
	
	
	
	
	
	
	
}