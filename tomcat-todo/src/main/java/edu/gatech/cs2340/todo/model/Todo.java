package edu.gatech.cs2340.todo.model;

public class Todo {
    
    String title;
    String task;
    int turn;
    int armysize;

    public Todo(String title, String task) {
        this.title = title;
        this.task = task;
        turn = 0;
        armysize = 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String  getTitle() {
        return title;
    }

    public void setTask(String task) {
        this.task = task;
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
    public String  getTask() {
        return task;
    }
    public String toString() {
    	
    	return title +" from "+task+" has an army with " +armysize+" units in it and goes on turn "+turn+"\n\n";
    }
    
}
