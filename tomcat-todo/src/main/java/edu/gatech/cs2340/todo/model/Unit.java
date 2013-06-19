package edu.gatech.cs2340.todo.model;

public class Unit
{
	String name; //name of unit ie Infantry, Artillery, Cavalry, Frigate, Destroyer, Carrier, etc.
	int health; //amount of damage Unit can take before destroyed
	int strength; //amount of damage Unit can deal -> number of dice?
	int defense; //lowers amount of damage Unit takes -> number of dice?
//	ArrayList<Resource> inventory; //inventory of unit ie speed boost, attack boost, etc.
	Player owner; //who owns this Unit
	Territory occupying;
	
	public  Unit(String name, int health, int strength, int defense, Player owner)
	{
		this.name = name;
		this.health = health;
		this.strength=strength;
		this.defense = defense;
	//	inventory = new ArrayList<Resource>();
		this.owner = owner;
		occupying = null;
		
	}
	public String getName()
	{
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
}