package edu.gatech.cs2340.todo.model;

import java.util.*;

public class Unit
{
	String name; //name of unit ie Infantry, Artillery, Cavalry, Frigate, Destroyer, Carrier, etc.
	int id; //unique identifier per unit
	int health; //amount of damage Unit can take before destroyed
	int strength; //amount of damage Unit can deal -> number of dice?
	int defense; //lowers amount of damage Unit takes -> number of dice?
//	ArrayList<Resource> inventory; //inventory of unit ie speed boost, attack boost, etc.
	Player owner; //who owns this Unit
	Territory previouslyOccupied;
	Territory occupying;
	Random rand = new Random();
	
	public  Unit(String name, int health, int strength, int defense, Player owner)
	{
		this.name = name;
		id = 0;
		this.health = health;
		this.strength=strength;
		this.defense = defense;
	//	inventory = new ArrayList<Resource>();
		this.owner = owner;
		previouslyOccupied = null;
		occupying = null;
		
	}
	public String getName()
	{
		return name;
	}
	public void damage(int a)
	{
		health-=a;
	}
	public int getHealth()
	{
		return health;
	}
	public int getStrength()
	{
		return strength;
	}
	public int getDefense()
	{
		return defense;
	}
	public void setID(int id)
	{
		this.id = id;
	}
	public int getID()
	{
		return id;
	}
	public void setOwner(Player player)
	{
		owner = player;
	}
	public Player getOwner()
	{
		return owner;
	}
	//essentially the "move" method
	//if unit stays in place, must "move" to the same location
	public void setTerritory(Territory location)
	{
		previouslyOccupied = occupying;
		occupying = location;
	}
	public Territory getTerritory()
	{
		return occupying;
	}
	public Territory getPreviouslyOccupied()
	{
		return previouslyOccupied;
	}
	//this unit attacks the parameter unit, will probably involve a "dice roll" (RNG 1-6) multiplied by the attacker's attack minus 
	//the defender's defense multiplied by a dice roll. More or less die rolls may depends on the specific attack/defense modifier
	//the result will be subtracted from the defender's hp
	public int[] attack(Unit enemy)
	{	
//		int numdice = 1; //maybe more dice for whatever reason ie. flanking attack bonus, etc.
		int dmg = (rand.nextInt(6)+1)*strength-(rand.nextInt(6)+1)*enemy.getDefense();
		enemy.damage(dmg);
		
		int edmg = (rand.nextInt(6)+1)*enemy.getStrength()-(rand.nextInt(6)+1)*defense;
		damage(edmg);
		
		int[] ret = {dmg,edmg};
		return ret;
		//need to make get methods for health/str/def
	}
	public String toString()
	{
		return name+" with identification number "+getID()+" currently has "+health+" hit points, is located at "+occupying+
				" and is owned by "+owner.getName();
	}
	
	
	
	
	
	
	
	
	
	
	
}