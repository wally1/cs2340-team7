package edu.gatech.cs2340.todo.model;

public class Player {
    
    String name;
    String country;
    int turn;
    int armysize; //temporary - in future army will consist of different type of units -> treemap
//    ArrayList<Territory>;
    

    public Player(String title, String task) { //constructors may be temporary, may later intialize with x army, y turn, etc. 
        name = title;
        country = task;
        turn = 0;  
        armysize = 0;
 //       ArrayList<Territory> = new ArrayList<Territory>();
    }

    public void setName(String title) {
        name = title;
    }

    public String  getName() {
        return name;
    }

    public void setCountry(String task) {
        country = task;
    }
    public String getCountry() {
    	return country;
    }
    
    public void setTurn(int a){
    	turn = a;
    }
    public int getTurn(){
    	return turn;
    }
    public void setArmySize(int size)
    {
    	armysize = size;
    }
    public int getArmySize()
    {
    	return armysize;
    }
    
 
    
    

    public String toString() {
    	
    	return name +" from "+country+" has an army with " +armysize+" units in it and goes on turn "+turn+"\n\n";
    }
    
}
