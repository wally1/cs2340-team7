package edu.gatech.cs2340.todo.model;



import java.util.*;

public class Unit
{
	String name;
	int id; 
	int health;
	int maxHealth;
	int strength; //amount of damage Unit can deal 
	int defense; //lowers amount of damage Unit takes 
//	ArrayList<Resource> inventory; //inventory of unit ie speed boost, attack boost, etc.
	Player owner; 
	Territory previouslyOccupied;
	Territory occupying;
	Random rand = new Random();
	boolean attacked;
	boolean moved;
	
	public  Unit(String name, int health, int strength, int defense)
	{
		this.name = name;
		id = 0;
		this.health = maxHealth = health;
		this.strength=strength;
		this.defense = defense;
	//	inventory = new ArrayList<Resource>();
		owner = null;
		previouslyOccupied = null;
		occupying = null;
		attacked = false;
		moved = false;
		
		
	}
	public  Unit(String name, int health, int strength, int defense, Player owner)
	{
		this.name = name;
		id = 0;
		this.health = maxHealth = health;
		this.strength=strength;
		this.defense = defense;
	//	inventory = new ArrayList<Resource>();
		this.owner = owner;
		previouslyOccupied = null;
		occupying = null;
		attacked = false;
		moved = false;
		
	}
	
	public String getName()
	{
		return name;
	}
	public void takeDamage(int a)
	{
		health-=a;
	}
	public void update()
	{
		owner.update(this);
		occupying.update(this);
	}
	public int getHealth()
	{
		return health;
	}
	public int getMaxHealth()
	{
		return maxHealth;
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
	//setTerritory and move methods separated to smooth over Player.update()
	public void setTerritory(Territory location)
	{
		previouslyOccupied = occupying;
		occupying = location;

	}
	public void move(Territory location)
	{
		setTerritory(location);
		owner.update(this);
		moved = true;
		previouslyOccupied.update(this);
		occupying.addUnit(this);
	}

	public Territory getTerritory()
	{
		return occupying;
	}
	public Territory getPreviouslyOccupied()
	{
		return previouslyOccupied;
	}
	public void setAttacked()
	{
		attacked = true;
	}
	public boolean getAttacked(){
		return attacked;
	}
	
	public boolean getMoved(){
		return moved;
	}	
	public void resetForTurn()
	{
		attacked = moved = false;
	}
	public String toString()
<<<<<<< HEAD
	{	
		String moveString = null;
		String attackString = null;
		
		if (moved){
			moveString = "Can't Move";
		}
		else {
			moveString = "Can Move";
		}
		if (attacked){
			attackString = "Can't Attack";
		}
		else {
			attackString = "Can Attack";
		}
			
		return name+" ID:"+getID()+" Health: "+health+" Located:"+occupying+
				" Owner:"+owner.getName()+" | "+moveString+" | "+attackString+" |";
=======
	{
		return name+"-"+getID()+" has "+health+"/"+maxHealth+" health, is at "+occupying+
				"     has moved - "+moved+"     has attacked - "+attacked;

>>>>>>> a6d3338974276d8d43902d66fbee4662e2769504
	}

}
	
	
	
	
	
	
	
