package edu.gatech.cs2340.todo.model;

import edu.gatech.cs2340.todo.model.Territory;
import edu.gatech.cs2340.todo.model.Unit;

public class Resource
{
	String name;
	Unit ownedBy;
	Player inPossession;
	Territory spawnLocation;
	
	public Resource(String name, Territory spawn)
	{
		this.name = name;
		ownedBy = null;
		inPossession = null;
		spawnLocation = spawn;
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
}