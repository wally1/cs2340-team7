package edu.gatech.cs2340.todo.model;
//intent of the Resource class is to implement an extra layer of depth to the game via "Resources" each player can acquire
//to boost army strength/size. it may or may not be implemented in the final game
//as of now it serves to set up impassable "Asteroid" terrain.



import edu.gatech.cs2340.todo.model.Territory;
import edu.gatech.cs2340.todo.model.Unit;

public class Resource
{
	String name;
	Unit ownedBy;
	Player inPossession;
	Territory location;
	
	public Resource(String name, Territory spawn)
	{
		this.name = name;
		ownedBy = null;
		inPossession = null;
		location = spawn;
	}
	public void pickedUpBy(Unit unit, Player player)
	{
		ownedBy = unit;
		inPossession = player;
	}
	public String getName()
	{
		return name;
	}
	public void setTerritory(Territory location)
	{
		this.location = location;
	}
}