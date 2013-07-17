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
	boolean attacked;
	boolean moved;
	
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
		moved = true;
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
//currently only 1 die roll per modifier. Maybe more alongside future implmentations ie. flanking attack bonus/certain items, etc.
		int attackDice = rand.nextInt(6)+1;
		int enemyDefenseDice = rand.nextInt(6)+1;
		int enemyAttackDice = rand.nextInt(6)+1;
		int defenseDice = rand.nextInt(6)+1;
		int counterAttackDice = rand.nextInt(6)+1;
		int damage = attackDice*strength-enemyDefenseDice*enemy.getDefense();
		enemy.takeDamage(damage);
		int enemyDamage = enemyAttackDice*enemy.getStrength()-defenseDice*defense;
		enemyDamage = counterAttackDice/6*enemyDamage; //counterattack damage reduced by a factor of die roll
		takeDamage(enemyDamage);
		attacked = true;
	
		int[] dice = {attackDice,enemyDefenseDice,enemyAttackDice,defenseDice,counterAttackDice,damage,enemyDamage};
		return dice;
	}


	public boolean getAttacked(){
		return attacked;
	}
	
	public boolean getMoved(){
		return moved;
	}	
	
	public String toString()
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
	}
	
	
	
	
	
	
	
	
	
	
	
}