package edu.gatech.cs2340.todo.model;
import java.util.*;


    
public class Unit{
	
}
	private static int unitIDs = 0;
	String name;
	int id; 
	int health;
	int maxHealth;
	int strength;  
	int defense; 
	Player owner; 
	Territory previouslyOccupied;
	Territory occupying;
	Random rand = new Random();
	boolean attacked;
	boolean moved;
	
	public  Unit(String name, int health, int strength, int defense){
		this.name = name;
		id = unitIDs;
        unitIDs += 1;
		this.health = maxHealth = health;
		this.strength=strength;
		this.defense = defense;
		owner = null;
		previouslyOccupied = null;
		occupying = null;
		attacked = false;
		moved = false;		
	}

	public  Unit(String name, int health, int strength, int defense, Player owner){
		this.name = name;
		id = unitIDs;
        unitIDs += 1;
		this.health = maxHealth = health;
		this.strength=strength;
		this.defense = defense;
		this.owner = owner;
		previouslyOccupied = null;
		occupying = null;
		attacked = false;
		moved = false;		
	}
    
	public String getName(){
		return name;
	}
    
	public void takeDamage(int a){
		health-=a;
	}
    
	public void update(){
		owner.update(this);
		occupying.update(this);
	}
    
	public int getID()	{
		return id;
	}
    
	public void setOwner(Player player)	{
		owner = player;
	}
    
	public Player getOwner()	{
		return owner;
	}
	
	public String getName(){
		return name;
	}
	
	public void takeDamage(int a){
		health-=a;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getStrength(){
		return strength;
	}
	
	public int getDefense(){
		return defense;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public void setTerritory(Territory location){
		previouslyOccupied = occupying;
		occupying = location;
	}
	
	public void move(Territory location){
		setTerritory(location);
		owner.update(this);
		moved = true;
		previouslyOccupied.update(this);
		occupying.addUnit(this);
	}

	public Territory getTerritory(){
		return occupying;
	}
	
	public Territory getPreviouslyOccupied(){
		return previouslyOccupied;
	}
	
	public void setAttacked(){
		attacked = true;
	}
  
	public boolean getAttacked(){
		return attacked;
	}
	
	public boolean getMoved(){
		return moved;
	}	
	
	public void resetForTurn()	{
		attacked = moved = false;
	}
	
	public String toString(){
		return name+"-"+getID()+" has "+health+"/"+maxHealth+" health, is at "+occupying+
				"     has moved - "+moved+"     has attacked - "+attacked;
	}
}
	
	
	
	
	
	
	
