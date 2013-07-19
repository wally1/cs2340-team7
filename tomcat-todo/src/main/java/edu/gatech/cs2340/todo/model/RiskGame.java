package edu.gatech.cs2340.todo.model;
// RiskGame


import edu.gatech.cs2340.todo.model.*;
import java.util.*;


//RiskGame

import java.util.*;


public class RiskGame {

 private int gameID;
 
 public static final int ADD_PLAYERS = 0, CONFIRMATION = 1, SPAWN_LOCATION = 2, MOVE_ATTACK = 3; 
 private int state; // 0-add players, 1-select territory, 
 
 ArrayList<Player> players;
 Territory[][] map;
 int playerTurn;
 int id = 1000;
 
 public RiskGame(int gameID) {
     this.gameID = gameID;
     this.state = ADD_PLAYERS;
     this.players = new ArrayList<Player>();
     this.playerTurn = 0;
     map = new Territory[0][0];
 }
 
 // general functionality

 public int getGameID() {
     return gameID;
 }
 
 public int getGameState() {
     return state;
 }
 public int getCurrTurn()
 {
	 return playerTurn;
 }
 public ArrayList<Player> getPlayers() {
     return players;
 }
 public Territory[][] getMap()
 {
 	return map;
 }
 public int getID()
 {
	 id++;
	 return id;
 }
 public void nextTurn()
 {
	 playerTurn++;
	 if (playerTurn == players.size())
		 playerTurn = 0;
 }

 /**
   * add players state functions
   *
   * @return null for success, or a string with an error message to be 
   * displayed to the player.
   */
 public String addPlayer(String name, String country) {
     String result = null;
     if (state == ADD_PLAYERS) {
         if (players.size() <= 6) {
             System.out.println("We're adding a player!");
             // make sure not a duplicate
             if (!duplicateCountry(country)) {
                 players.add(new Player(name, country));
             } else {
            	 System.out.println("Players cannot choose the same country!");
                 result = "Players cannot choose the same country!";
             }
         } else {
             result = "Too many players";
         }
     }
     return result;
 }
 public String removePlayer(int id)
 {
	 String result = "";
	 if(state == ADD_PLAYERS)
	 {
		 System.out.println("We're deleting a player!");
		 players.remove(id);
		 result = "Removed!";
	 }
	 return result;
	 
 }
 
 // checks if the new player has chosen the same country as another.
 private boolean duplicateCountry(String country) {
     boolean result = false;
     for (int i = 0; i < players.size(); i++) {
         if (country.equalsIgnoreCase(players.get(i).getCountry())) {
             result = true;
             break;
         }
     }
     return result;
 }
 
 public String finishAddingPlayers() {
     String result = null;
     if (state == ADD_PLAYERS) {
         if (players.size() >= 3) {
             System.out.println("Moving on to Confirmation!");
             calcArmiesAndTurnOrder();
             state = CONFIRMATION;
         } else {
             result = "Not enough players.";
         }
     } else {
         result = "State ERROR: Not adding players.";
     }
     return result;
 }
 
 private void calcArmiesAndTurnOrder() {
 	
 	ArrayList<Integer> turns = new ArrayList<Integer>();
 	for(int b = 1; b <= players.size(); b++)
 	{turns.add(b);}
 	Collections.shuffle(turns);
 	for(int c = 0; c < players.size(); c++)
 	{ players.get(c).setTurn(turns.get(c));
 	System.out.println(players.get(c));
 	 }
 	Collections.sort(players);

 }
 
 // confirmation state functions
 public String finishConfirmation() {
     String result = null;
     if (state == CONFIRMATION) {
         state = SPAWN_LOCATION;// next state goes here.
     } else {
         result = "State ERROR: Not CONFIRMATION.";
     }
     playerTurn = 0;
     return result;
 }
 public void bombard(ArrayList<Unit> attackers, Territory homebase)
 {Random rand = new Random();
		int numAttackDice = (int) Math.floor(1+attackers.size()/4);
		System.out.println(attackers);
		Player victim = homebase.getPlayer();

		for(Unit attacker: attackers)
		{

			int damage = attacker.getStrength();
			String diceRolls = "";
			for(int a = 0; a<numAttackDice;a++)
			{	
				int roll = rand.nextInt(6)+1;
				damage+=roll;
				diceRolls+=roll+", ";
			}
			diceRolls = diceRolls.substring(0,diceRolls.length()-2);
			
			System.out.println(attacker.getName() +"-"+attacker.getID()+"- is attacking "+victim.getName()+"'s homebase!");
			System.out.println(attacker.getName() +"-"+attacker.getID()+"- rolled "+numAttackDice+" dice ("+diceRolls+") plus its strength ("+attacker.getStrength()+") for "+damage+" damage!");
		
			homebase.takeDamage(damage);
			
		}
		if(victim.hasLost())
		{
			for(Territory[] a: map)
				for(Territory b: a)
					b.removeDeadUnits();
			for(int a = 0; a<players.size()-2)
				nextTurn();
			System.out.println(victim.getName()+" has lost!!!");
			for(int a = 0; a<players.size();a++)
			{
				if(players.get(a).getName().equals(victim.getName()))
				{	players.remove(a);
					break;
				}
			}
		}
		if(players.size() == 1)
			state = ADD_PLAYERS;
		
 }
 
public void fight(ArrayList<Unit> attackers, ArrayList<Unit> defenders)
{

		Random rand = new Random();
		int numAttackDice = (int) Math.floor(1+attackers.size()/4);
		int numDefenseDice = (int) Math.floor(1+defenders.size()/4);
				
		int counterAttackDice = rand.nextInt(6)+1;
		
		System.out.println(attackers);
		System.out.println(defenders);
	
		for(Unit attacker: attackers)
		{

			int damage = attacker.getStrength();
			String diceRolls = "";
			for(int a = 0; a<numAttackDice;a++)
			{	
				int roll = rand.nextInt(6)+1;
				damage+=roll;
				diceRolls+=roll+", ";
			}
			diceRolls = diceRolls.substring(0,diceRolls.length()-2);
			Collections.shuffle(defenders);
			
			Unit victim = defenders.get(0);
			if(victim != null)
			{
			System.out.println(attacker.getName() +"-"+attacker.getID()+"- is attacking "+victim.getName()+"-"+victim.getID());
			System.out.println(attacker.getName() +"-"+attacker.getID()+"- rolled "+numAttackDice+" dice ("+diceRolls+") plus its strength ("+attacker.getStrength()+") for "+damage+" damage!");
			int defense = victim.getDefense();
			diceRolls = "";
			for(int a = 0;a<numDefenseDice;a++)
			{
				int roll = rand.nextInt(6)+1;
				defense+=roll;	
				diceRolls +=roll+", ";
			}
			diceRolls=diceRolls.substring(0,diceRolls.length()-2);
			System.out.println(victim.getName() +"-"+victim.getID()+"- rolled "+numDefenseDice+" dice ("+diceRolls+") plus its defense ("+victim.getDefense()+") to prevent "+defense+" damage!");
			damage -= defense;
			
			if(damage < 0)
				damage = 0;
			victim.takeDamage(damage);
			attacker.setAttacked(true);
			System.out.println(victim.getName() + "-"+victim.getID()+" was attacked for "+damage+" down to "+victim.getHealth()+"/"+victim.getMaxHealth()+"!");
			
			}
	
		}
		for(Player player:players)
			player.removeDeadUnits();
		for(Territory[] a: map)
			for(Territory b: a)
				b.removeDeadUnits();
	
}
 
 public Territory[][] initializeBoard()
 {
 	map = new Territory[9][15];
 	for(int a = 0; a <9; a++)
 	{
 		for(int b = 0; b < 15; b++)	
 		{
 			int[] coord = new int[2];
 			coord[0] = a;
 			coord[1] = b; 
 			map[a][b] = new Territory("Territory ["+a+","+b+"]",coord); 				
 		} 
 	}
 
 	//pretty sure we're just going to give everyone the same generic unit in the future
 	Unit ACUnit = new Unit("Alpha-Centaurian Space Frigate",5,3,1,null);
 	Unit PolarisUnit = new Unit("Polarian Manta",4,5,2,null);
 	Unit CharUnit = new Unit("Char Swarmling",3,2,0,null);
 	Unit BorgUnit = new Unit("Borg Assimilator",8,2,2,null);
 	Unit HALUnit = new Unit("HSS Probe",5,3,1,null);
 	Unit MidiUnit = new Unit("Midichlorian Force",6,4,1,null);
 	int armysize = (10-players.size()); 
 	ArrayList<String> countries = new ArrayList<String>(); 
 	for (Player player: players) { 
 		 countries.add(player.getCountry()); 
 		 if(player.getCountry().equals("Alpha-Centauri")){ACUnit.setOwner(player);}
 		 else if(player.getCountry().equals("Polaris")){PolarisUnit.setOwner(player);}
 		 else if(player.getCountry().equals("Char")){CharUnit.setOwner(player);}
 		 else if(player.getCountry().equals("Borg")){BorgUnit.setOwner(player);}
 		 else if(player.getCountry().equals("HAL Space Station")){HALUnit.setOwner(player);}
 		 else if(player.getCountry().equals("Midichloria")){MidiUnit.setOwner(player);}
 	}
 	
 	 if(countries.contains("Alpha-Centauri")){
 		 map[0][1].makeHomeBase(players.get(countries.indexOf("Alpha-Centauri"))); 
 		 int[] coords ={0,0,1,0,1,1,1,2,0,2};
 		 id = spawn(map, ACUnit,armysize,coords,id); 
 		 }
 		 
 		 //upper middle is Polaris
 		 if(countries.contains("Polaris")){
 			map[0][7].makeHomeBase(players.get(countries.indexOf("Polaris"))); 
 			int[] coords = {0,6,1,6,1,7,1,8,0,8};
 		 	id=spawn(map, PolarisUnit,armysize,coords,id);  
 		 }
 		 //upper right is Midichloria
 		 if(countries.contains("Midichloria")){
 			map[0][13].makeHomeBase(players.get(countries.indexOf("Midichloria")));
 			int[] coords = {0,12,1,12,1,13,1,14,0,14};
 			id=spawn(map, MidiUnit,armysize,coords,id); 
 		}
 		 
 		 //bottom left Char
 		 if(countries.contains("Char")){
 			map[8][1].makeHomeBase(players.get(countries.indexOf("Char")));
 			int[] coords = {8,0,7,0,7,1,7,2,8,2};
 			id=spawn(map, CharUnit,armysize,coords,id); 
 		 }

 		 //bottom middle is HAL Space Station
 		 if(countries.contains("HAL Space Station")){
 			 map[8][7].makeHomeBase(players.get(countries.indexOf("HAL Space Station"))); 
 			 int[] coords = {8,6,7,6,7,7,7,8,8,8};
 			id= spawn(map, HALUnit,armysize,coords,id); 
 		}
 		 
 		 //bottom right is Borg
 		 if(countries.contains("Borg")){
 			 map[8][13].makeHomeBase(players.get(countries.indexOf("Borg"))); 
 			 int[] coords = {8,14,7,14,7,13,7,12,8,12};
 			id= spawn(map, BorgUnit,armysize,coords,id); 
 		}

 	return map;
 }
 public int spawn(Territory[][] maps, Unit unit, int amount, int[] coords,int idn) 
 {
	if(state == SPAWN_LOCATION)
	{
		for(int i = 0; i < coords.length; i+=2)
		{
			for(int a = 1; a <= amount; a++)
			{
				Unit toBeAdded = new Unit(unit.getName(),unit.getHealth(),unit.getStrength(),unit.getDefense(),unit.getOwner());
				toBeAdded.setID(idn++);
				toBeAdded.setTerritory(maps[coords[i]][coords[i+1]]);
				toBeAdded.getOwner().addUnit(toBeAdded);
				maps[coords[i]][coords[i+1]].addUnit(toBeAdded);
			}
			System.out.println("Spawning "+amount+" "+unit.getName()+"(s) at ["+coords[i]+","+coords[i+1]+"] for "+players.get(getCurrTurn()).getName());
	
		}
	}
	return idn;
 }
 
 
 
 
 public String toString()
 {
 	return "This is game id: "+gameID+" at state "+state;
 }
 
 // 
}