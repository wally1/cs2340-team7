package edu.gatech.cs2340.todo.controller;

import edu.gatech.cs2340.todo.model.Territory;
import edu.gatech.cs2340.todo.model.Player;
import edu.gatech.cs2340.todo.model.Unit;
import edu.gatech.cs2340.todo.model.RiskGame;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={
        "/list", // GET
        "/create", // POST 
        "/update/*", // PUT
        "/delete/*", // DELETE
        "/confirmation"
    })
public class TodoServlet extends HttpServlet {
<<<<<<< HEAD
    
    ArrayList<RiskGame> games = new ArrayList<RiskGame>();
    
    
=======

	RiskGame game = new RiskGame(1);
   ArrayList<Player> players = game.getPlayers();
  
>>>>>>> master
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
    	
        System.out.println("In doPost()");
        
        // Handle the hidden HTML form field that simulates
        // HTTP PUT and DELETE methods.
        String operation = (String) request.getParameter("operation");
        System.out.println(operation);
<<<<<<< HEAD
        //System.out.println("It is turn "+game.getCurrTurn());
        
		
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
=======
        System.out.println("It is turn "+game.getCurrTurn());
				
		  
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
     
        if (null == operation){operation = "CONFIRMATION";}
    
>>>>>>> master
        
        //probably more elegant fix later, like just changing operation to "Confirmation"
        //if (null == operation){operation = "CONFIRMATION";}
        if (operation.equalsIgnoreCase("NEW")) {
            // no input
            
            // state
            RiskGame game = new RiskGame();
            games.add(game);
            
            // dispatch
            listDispatcher(request, response, game);
            
        } else if (operation.equalsIgnoreCase("PUT")) {
            // input, state and dispatch
            doPut(request, response);
            
        }
<<<<<<< HEAD
        else if (operation.equalsIgnoreCase("DELETE")) {
            // input, state and dispatch
            //doDelete(request, response);
            // input
            RiskGame game = getGameFromID(request);
            int id = getId(request);
            int iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            
            // state
            String success = null;
            boolean stateCheck = false;
            if (game != null) {
                System.out.println("Game State: " +game.getGameState());
                if (game.getGameState() == RiskGame.ADD_PLAYERS && 
                            game.getPlayerActionsSoFar() == iPlayerActionsSoFar) {
                    stateCheck = true;
                    success = game.removePlayer(id);
                }
            } else {
                System.out.println("can't find gameID");
            }
            
            // dispatch
            if (success != null) {
                listDispatcher(request, response, game);
            } else if (stateCheck) {
                System.out.println(success);
                listDispatcher(request, response, game);
            } else {
                indexDispatcher(request, response);
            }
            
        } else if (operation.equalsIgnoreCase("LOAD")) {
            // load
            RiskGame game = getGameFromID(request);
            
            // state
            
            // dispatch
            if (game == null) {
                indexDispatcher(request, response);
            } else if (game.getGameState() == RiskGame.ADD_PLAYERS) {
                listDispatcher(request, response, game);
            } else if (game.getGameState() == RiskGame.SELECT_LOCATION) {
                confirmationDispatcher(request, response, game);
            } else if (game.getGameState() == RiskGame.COMMAND) {
                commandDispatcher(request, response, game);
            }
            
            
        } else if(operation.equalsIgnoreCase("TURN"))
        {
        	System.out.println("We're going to the next turn!");
            // input
            RiskGame game = getGameFromID(request);
            int iTurnCount, iPlayerTurn, iPlayerActionsSoFar;
            iTurnCount = Integer.parseInt(request.getParameter("turnCount"));
            iPlayerTurn = Integer.parseInt(request.getParameter("playerTurn"));
            iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            int turnCount = -1;
            int playerTurn = -1;
            int playerActionsSoFar = -1;
            if (game != null) {
                turnCount = game.getTurnCount();
                playerTurn = game.getCurrTurn();
                playerActionsSoFar = game.getPlayerActionsSoFar();
            }
            
            // state
            boolean stateCheck = false;
            if (game!= null) {
                if (iTurnCount == turnCount && iPlayerTurn == playerTurn && 
                    iPlayerActionsSoFar == playerActionsSoFar) {
                    game.nextTurn();
                    stateCheck = true;
                }
            }
            
            // dispatch
            if (stateCheck)
                confirmationDispatcher(request, response, game);
            else
                indexDispatcher(request, response);
            /*
=======
		  
        else if(operation.equalsIgnoreCase("TURN"))
        {
        	game.nextTurn();
>>>>>>> master
        	System.out.println("It is now turn "+game.getCurrTurn());
        	Player currplayer = game.getPlayers().get(game.getCurrTurn());
        	request.setAttribute("currplayer",currplayer);
        	Territory[][] newMap = game.getMap();
        	players = game.getPlayers();
        	request.setAttribute("players",players);
        	request.setAttribute("map",newMap);
        	TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
			String territory = terr.firstKey();
			request.setAttribute("selectedTerritory",territory);
			TreeMap<Integer,Unit> occupants = getOccupantsFromString(territory,game);
			request.setAttribute("occupants",occupants);
			
    		RequestDispatcher dispatcher = 
                    getServletContext().getRequestDispatcher("/confirmation.jsp");
                    dispatcher.forward(request,response);*/
        	
        }
<<<<<<< HEAD
        else if(operation.equalsIgnoreCase("SPAWN")) {
                System.out.println("We're SPAWNING more units!");
                // input
            	int a = Integer.parseInt(request.getParameter("Coord1"));
            	int b = Integer.parseInt(request.getParameter("Coord2"));
                int[] co = {a,b};
                RiskGame game = getGameFromID(request);
                int iTurnCount, iPlayerTurn, iPlayerActionsSoFar;
                iTurnCount = Integer.parseInt(request.getParameter("turnCount"));
                iPlayerTurn = Integer.parseInt(request.getParameter("playerTurn"));
                //iPlayerActionsSoFar = request.getParameter("playerActionsSoFar");
                int turnCount = -1;
                int playerTurn = -1;
                int playerActionsSoFar = -1;
                if (game != null) {
                    turnCount = game.getTurnCount();
                    playerTurn = game.getCurrTurn();
                    //playerActionsSoFar = game.getPlayerActionsSoFar();
                }
                
                // state
                boolean stateCheck = false;
                boolean success = false;
                if (game != null ) {
                    if (iTurnCount == turnCount && iPlayerTurn == playerTurn) {
                        stateCheck = true;
                        success = game.spawnUpdate(co);
                    }
                }
                // dispatch
                if (success) {
                    commandDispatcher(request, response, game);
                } else if (stateCheck) {
                    confirmationDispatcher(request, response, game);
                }else {
                    indexDispatcher(request, response);
                }
                
                /*
            	Player currplayer = game.getCurrentPlayer();
            	request.setAttribute("currplayer",currplayer);
            	Territory[][] newMap = game.getMap();
            	players = game.getPlayers();
            	request.setAttribute("players",players);
            	request.setAttribute("map",newMap);
            	Unit unit = new Unit("Space Frigate",40,6,0,currplayer); // 
            	TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
    			String territory = terr.firstKey();
				request.setAttribute("selectedTerritory",territory);
    				int index = territory.indexOf('[');
    				int index2 = territory.indexOf(']');
    				int indexC = territory.indexOf(',');
    				String subA = territory.substring(index+1,indexC);
    				String subB = territory.substring(indexC+1,index2);
    				int coA = Integer.parseInt(subA);
    				int coB = Integer.parseInt(subB);
    				TreeMap<Integer,Unit> occupants = game.getMap()[coA][coB].getOccupants();
    				request.setAttribute("occupants",occupants);
            	
            	if(success)
            	{*/
            		//game.spawn(game.getMap(),unit, 1, co);
/*            		game.nextTurn();
            		System.out.println("It is now turn "+game.getCurrTurn());*//*
            		newMap = game.getMap();
            		players = game.getPlayers();
            		request.setAttribute("players",players);
            		request.setAttribute("map",newMap);
            		currplayer = game.getPlayers().get(game.getCurrTurn());
            		request.setAttribute("currplayer",currplayer);
                    
            		RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/command.jsp");
                        dispatcher.forward(request,response);
            	}
            	else
            	{
            		System.out.println("You can't spawn there!");
                    
            		RequestDispatcher dispatcher = 
                            getServletContext().getRequestDispatcher("/confirmation.jsp");
                            dispatcher.forward(request,response);
                	
            	}*/
            	
        }
        else if(operation.equalsIgnoreCase("MOVE"))
        {
            // input
            String[] selectedUnitIDs = request.getParameterValues("unit");
            ArrayList<Integer> selectedUnitIntIDs = new ArrayList<Integer>();
            for (String ids: selectedUnitIDs)
                selectedUnitIntIDs.add(Integer.parseInt(ids));
                
            int x = Integer.parseInt(request.getParameter("MoveCoordX"));
        	int y = Integer.parseInt(request.getParameter("MoveCoordY"));
        	int[] moveCo = {y,x};
            RiskGame game = getGameFromID(request);
            int iTurnCount, iPlayerTurn, iPlayerActionsSoFar;
            iTurnCount = Integer.parseInt(request.getParameter("turnCount"));
            iPlayerTurn = Integer.parseInt(request.getParameter("playerTurn"));
            iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            int turnCount = -1;
            int playerTurn = -1;
            int playerActionsSoFar = -1;
            if (game != null) {
                turnCount = game.getTurnCount();
                playerTurn = game.getCurrTurn();
                playerActionsSoFar = game.getPlayerActionsSoFar();
            }
            
            // state
            boolean success = false;
            boolean stateCheck = false;
            if (game!= null ) {
                if (iTurnCount == turnCount && iPlayerTurn == playerTurn && 
                    iPlayerActionsSoFar == playerActionsSoFar) {
                    stateCheck = true;
                    success = game.moveUpdate(selectedUnitIntIDs, moveCo);
                }
            }
            if (success)
                System.out.println("We are moving!");
            else
                System.out.println("You can't move there!");
            
            // dispatch
            if (success ) {
                commandDispatcher(request, response, game);
            } else if (stateCheck) {
                commandDispatcher(request, response, game);
            } else {
                indexDispatcher(request, response);
            }
            /*
        	//grabs checked units
        	//need to make method that passes all values
        	Player currplayer = game.getCurrentPlayer();
           	request.setAttribute("currplayer",currplayer);
=======
		  //SPAWN occurs on confirmation.jsp; It spawns a new Unit for the current player at designated coordinates
		else if(operation.equalsIgnoreCase("SPAWN")) {
			System.out.println("We're SPAWNING more units!");
			int a = Integer.parseInt(request.getParameter("Coord1"));
         int b = Integer.parseInt(request.getParameter("Coord2"));
         Player currplayer = game.getPlayers().get(game.getCurrTurn());
         request.setAttribute("currplayer",currplayer);
         Territory[][] newMap = game.getMap();
         players = game.getPlayers();
         request.setAttribute("players",players);
         request.setAttribute("map",newMap);
         Unit unit = new Unit("Space Frigate",40,6,0,currplayer);
         
			//Initialize Unit TreeMap as the first choice in list of occupied territories
			TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
    		String territory = terr.firstKey();
			request.setAttribute("selectedTerritory",territory);
    		TreeMap<Integer,Unit> occupants = getOccupantsFromString(territory,game);
    		request.setAttribute("occupants",occupants);
         int[] co = {a,b};
  				
			//checks to see if user's input coordinates are a valid choice          	
				if(check(co,currplayer)){
					game.spawn(game.getMap(),unit, 1, co, game.getID());
					System.out.println("It is now turn "+game.getCurrTurn());
            	newMap = game.getMap();
            	players = game.getPlayers();
            	request.setAttribute("players",players);
            	request.setAttribute("map",newMap);
            	currplayer = game.getPlayers().get(game.getCurrTurn());
            	request.setAttribute("currplayer",currplayer);
            	
            	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/command.jsp");
               dispatcher.forward(request,response);
            }
				
			//if user's input isn't valid, screen refreshes, and user chooses again
            else{
            	System.out.println("You can't spawn there!");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmation.jsp");
               dispatcher.forward(request,response); 	
            }
            	
		} 
		
		//MOVE occurs in command.jsp It allows a player to move their units.
		else if(operation.equalsIgnoreCase("MOVE")){
        	//grabs checked units
        	//need to make method that passes all values
        	Player currplayer = game.getPlayers().get(game.getCurrTurn());
         request.setAttribute("currplayer",currplayer);
>>>>>>> master
        	Territory[][] newMap = game.getMap();
        	players = game.getPlayers();
        	request.setAttribute("players",players);
        	request.setAttribute("map",newMap);
        	
        	TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
			String territory = terr.firstKey();
			request.setAttribute("selectedTerritory",territory);
<<<<<<< HEAD
				int index = territory.indexOf('[');
				int index2 = territory.indexOf(']');
				int indexC = territory.indexOf(',');
				String subA = territory.substring(index+1,indexC);
				String subB = territory.substring(indexC+1,index2);
				int coA = Integer.parseInt(subA);
				int coB = Integer.parseInt(subB);
				TreeMap<Integer,Unit> occupants = game.getMap()[coA][coB].getOccupants();
				request.setAttribute("occupants",occupants);
            
            
        	//ArrayList<Unit> selectedUnits = new ArrayList<Unit>();
        	
        	//for(String ids: selectedUnitIDS)
        	//	selectedUnits.add(currplayer.getArmy().get(Integer.parseInt(ids)));
            
           	//int[] startCo = selectedUnits.get(0).getTerritory().getCoords();
        	//int x = Integer.parseInt(request.getParameter("MoveCoordX"));
        	//int y = Integer.parseInt(request.getParameter("MoveCoordY"));
        	//int[] moveCo = {y,x};
            
        	//Territory location = game.getMap()[moveCo[0]][moveCo[1]];
        	//System.out.println("About to move!");
            
        	
        	if(checkAdjacent(startCo,moveCo))
        	{
=======
			TreeMap<Integer,Unit> occupants = getOccupantsFromString(territory,game);
			request.setAttribute("occupants",occupants); 	
        	String[] selectedUnitIDS = request.getParameterValues("unit");
        	ArrayList<Unit> selectedUnits = new ArrayList<Unit>();
        		for(String ids: selectedUnitIDS)
        			selectedUnits.add(currplayer.getArmy().get(Integer.parseInt(ids)));
           		
			int[] startCo = selectedUnits.get(0).getTerritory().getCoords();
			int x = Integer.parseInt(request.getParameter("MoveCoordX"));
        	int y = Integer.parseInt(request.getParameter("MoveCoordY"));
        	int[] moveCo = {y,x};
        	Territory location = game.getMap()[moveCo[0]][moveCo[1]];
        	System.out.println("About to move!");
        	
			//checks to see if starting and ending coordinates are adjacent
        	if(checkAdjacent(startCo,moveCo)){
>>>>>>> master
        		System.out.println("We're moving!");
        		for(Unit a: selectedUnits)
        		{
        			if(!a.getMoved())
        			a.move(location);
        		}
        	}
        	else
        		System.out.println("You can't move there!");
        	
       		RequestDispatcher dispatcher = 
                    getServletContext().getRequestDispatcher("/command.jsp");
<<<<<<< HEAD
                    dispatcher.forward(request,response);*/
        }
        else if(operation.equalsIgnoreCase("ATTACK")) 
        {
            // input 
            String[] selectedUnitIDs = request.getParameterValues("unit");
            ArrayList<Integer> selectedUnitIntIDs = new ArrayList<Integer>();
            for (String ids: selectedUnitIDs)
                selectedUnitIntIDs.add(Integer.parseInt(ids));
            
            int x = Integer.parseInt(request.getParameter("AttackCoordX"));
	        	int y = Integer.parseInt(request.getParameter("AttackCoordY"));
	        	int[] moveCo = {y,x};
            RiskGame game = getGameFromID(request);
            int iTurnCount, iPlayerTurn, iPlayerActionsSoFar;
            iTurnCount = Integer.parseInt(request.getParameter("turnCount"));
            iPlayerTurn = Integer.parseInt(request.getParameter("playerTurn"));
            iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            int turnCount = -1;
            int playerTurn = -1;
            int playerActionsSoFar = -1;
            if (game != null) {
                turnCount = game.getTurnCount();
                playerTurn = game.getCurrTurn();
                playerActionsSoFar = game.getPlayerActionsSoFar();
            }
            
            // state 
            boolean stateCheck = false;
            if (game!= null ) {
                if (iTurnCount == turnCount && iPlayerTurn == playerTurn && 
                    iPlayerActionsSoFar == playerActionsSoFar) {
                    stateCheck = true;
                    game.attackUpdate(selectedUnitIntIDs, moveCo);
                }
            }
            
            // dispatch
            if (stateCheck)
                commandDispatcher(request, response, game);
            else
                indexDispatcher(request, response);
            
        	/*Player currplayer = game.getPlayers().get(game.getCurrTurn());
           	request.setAttribute("currplayer",currplayer);
=======
                    dispatcher.forward(request,response);
	}
		  
		  //ATTACK occurs in command.jsp Form a currently selected territory, a player may attack an enemy adjacent to it.
		else if(operation.equalsIgnoreCase("ATTACK")){
			Player currplayer = game.getPlayers().get(game.getCurrTurn());
         request.setAttribute("currplayer",currplayer);
>>>>>>> master
        	Territory[][] newMap = game.getMap();
        	players = game.getPlayers();
        	request.setAttribute("players",players);
        	request.setAttribute("map",newMap);
<<<<<<< HEAD
        	//Unit unit = new Unit("Space Frigate",40,6,0,currplayer);
        	TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
			String territory = terr.firstKey();
			request.setAttribute("selectedTerritory",territory);
				int index = territory.indexOf('[');
				int index2 = territory.indexOf(']');
				int indexC = territory.indexOf(',');
				String subA = territory.substring(index+1,indexC);
				String subB = territory.substring(indexC+1,index2);
				int coA = Integer.parseInt(subA);
				int coB = Integer.parseInt(subB);
				TreeMap<Integer,Unit> occupants = game.getMap()[coA][coB].getOccupants();
				request.setAttribute("occupants",occupants);*/
				
        	//grabs checked Units
	        	
	        	//String[] selectedUnitIDS = request.getParameterValues("unit");

	        	//ArrayList<Unit> selectedUnits = new ArrayList<Unit>();
	        	
	        	//for(String ids: selectedUnitIDS)
	        	//	selectedUnits.add(currplayer.getArmy().get(Integer.parseInt(ids)));
	        	
	           	//int[] startCo = selectedUnits.get(0).getTerritory().getCoords();
	        	//int x = Integer.parseInt(request.getParameter("AttackCoordX"));
	        	//int y = Integer.parseInt(request.getParameter("AttackCoordY"));
	        	//int[] moveCo = {y,x};

	        	//Territory location = game.getMap()[moveCo[0]][moveCo[1]];
	        	//System.out.println("About to move!");
	        	
	        	//ArrayList<Unit> victims = new ArrayList<Unit>();
	        	//for(int ids: location.getOccupants().keySet())
	        	//	victims.add(location.getOccupants().get(ids));
	        	
                /*
	        	boolean attackSucceed = true;
=======
        	
        	TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
			String territory = terr.firstKey();
			request.setAttribute("selectedTerritory",territory);
			TreeMap<Integer,Unit> occupants = getOccupantsFromString(territory,game);
			request.setAttribute("occupants",occupants);
				
        	//grabs checked Units (checkbox)    	
	      String[] selectedUnitIDS = request.getParameterValues("unit");
	      ArrayList<Unit> selectedUnits = new ArrayList<Unit>();
	        	
				for(String ids: selectedUnitIDS)
	        		selectedUnits.add(currplayer.getArmy().get(Integer.parseInt(ids)));
					
	         int[] startCo = selectedUnits.get(0).getTerritory().getCoords();
	        	int x = Integer.parseInt(request.getParameter("AttackCoordX"));
	        	int y = Integer.parseInt(request.getParameter("AttackCoordY"));
	        	int[] moveCo = {y,x};

	       	Territory location = game.getMap()[moveCo[0]][moveCo[1]];

	        	if(location.isHomeBase())
	        	{
	        		game.bombard(selectedUnits,location);
	        	}
	        	else
	        	{
	        		ArrayList<Unit> victims = new ArrayList<Unit>();
	        		for(int ids: location.getOccupants().keySet())
	        			victims.add(location.getOccupants().get(ids));
	        		
	        		boolean attackSucceed = true;
>>>>>>> master
	        	
	        		if(checkAdjacent(startCo,moveCo)){
	        			System.out.println("We're attacking!");
	        				for(Unit a: selectedUnits){
	        					if(a.getAttacked())
	        						attackSucceed = false;
	        				}
	        		
	        			if(attackSucceed)
	        				game.fight(selectedUnits, victims);
	        			else
	        				System.out.println("Some of the selected units can't attack!");
	        		}
	        		else
<<<<<<< HEAD
	        			System.out.println("Some of the selected units can't attack!");
	        	}
	        	else
	        		System.out.println("You can't attack there!");
	        	
	       		RequestDispatcher dispatcher = 
	                    getServletContext().getRequestDispatcher("/command.jsp");
	                    dispatcher.forward(request,response);*/
        }
=======
	        	 			System.out.println("You can't attack there!");
	        	}
				
	        	if(game.getPlayers().size() == 1){
	        		request.setAttribute("winner",game.getPlayers().get(0).getName());
	        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/congratulations.jsp");
		        	dispatcher.forward(request,response);
	        	}
	        	else{
	       		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/command.jsp");
	            dispatcher.forward(request,response);
	        	}
		}

	//TERRITORY occurs in command.jsp It allows the player to select one of the territory to display units.
>>>>>>> master
		else if(operation.equalsIgnoreCase("TERRITORY")){
			System.out.println("Displaying Units");
            // input
            RiskGame game = getGameFromID(request);
            int iTurnCount, iPlayerTurn, iPlayerActionsSoFar;
            iTurnCount = Integer.parseInt(request.getParameter("turnCount"));
            iPlayerTurn = Integer.parseInt(request.getParameter("playerTurn"));
            iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            int turnCount = -1;
            int playerTurn = -1;
            int playerActionsSoFar = -1;
            if (game != null) {
                turnCount = game.getTurnCount();
                playerTurn = game.getCurrTurn();
                playerActionsSoFar = game.getPlayerActionsSoFar();
            }
            
            // state
            boolean stateCheck = false;
            if (game!= null ) {
                if (iTurnCount == turnCount && iPlayerTurn == playerTurn && 
                    iPlayerActionsSoFar == playerActionsSoFar) {
                    stateCheck = true;
                    // might need to actuall update something here
                }
            }
            
            // dispatch
            if (stateCheck)
                commandDispatcher(request, response, game);
            else
                indexDispatcher(request, response);
            
            /*
			String territory = request.getParameter("Players");
			request.setAttribute("selectedTerritory",territory);
			TreeMap<Integer,Unit> occupants = getOccupantsFromString(territory,game);
			Territory[][] newMap = game.getMap();
			Player currplayer = game.getPlayers().get(game.getCurrTurn());
         request.setAttribute("currplayer",currplayer);
         players = game.getPlayers();
         request.setAttribute("players",players);
         request.setAttribute("map",newMap);
			request.setAttribute("occupants",occupants);
         currplayer = game.getPlayers().get(game.getCurrTurn());
         request.setAttribute("currplayer",currplayer);
            	
<<<<<<< HEAD
            	RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/command.jsp");
                        dispatcher.forward(request,response);*/
			}
				
				
		/*  else if (operation.equalsIgnoreCase("Territory")) {
		  //change to "Select Territories"
		  		System.out.println("Time to attack!");
														
					Player currplayer = game.getPlayers().get(game.getCurrTurn());
            	request.setAttribute("currplayer",currplayer);
					
					int[] occupiedTerritoryCoordinates = Integer.parseInt(request.getParameter("Coordinates"));
					TreeMap<Integer,Unit> units = map[occupiedTerritoryCoordinates[0]][occupiedTerritoryCoordinates[1]].getOccupants();
					request.setAttribute("units",units);
											
		//start "ATTACK!" here
					for (int i = 0; i < units.length; i++){
						
					int a = Integer.parseInt(request.getParameter("Coord1"));
            	int b = Integer.parseInt(request.getParameter("Coord2"));
					int[] ab = {a,b};
					
						if ((map[a][b].getOwner()!=currplayer) && (checkAdjacent(occupiedTerritoryCoordinates, ab)) && (map[a][b].getOwner()!=null){
							Player enemy = map[a][b].getOwner();
						}
					
							
		*/			
					
        else if (operation.equalsIgnoreCase("CONFIRMATION")) {
            // input
            RiskGame game = getGameFromID(request);
            int iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            
            // state
            String success = null;
            boolean stateCheck = false;
            if (game != null ) {
                if (game.getGameState() == RiskGame.ADD_PLAYERS && 
                        iPlayerActionsSoFar == game.getPlayerActionsSoFar()) {
                    stateCheck = true;
                    success = game.finishAddingPlayers();
                    game.finishConfirmation();
                }
            }
            
            // dispatch
            if (success == null) {
                confirmationDispatcher(request, response, game);
            } else if (stateCheck) {
                listDispatcher(request, response, game);
            } else {
                indexDispatcher(request, response);
            }
            
            /*
        	if(players.size() > 2 && players.size() < 7 && seperateCountries(players))
        	{
        	game.finishAddingPlayers();
        	game.finishConfirmation();
        	Territory[][] map = game.initializeBoard();
        	request.setAttribute("players",players);
        	request.setAttribute("turn",game.getCurrTurn());
        	Player currplayer = game.getPlayers().get(game.getCurrTurn());
        	request.setAttribute("currplayer",currplayer);
        	request.setAttribute("map",game.getMap());
=======
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/command.jsp");
         dispatcher.forward(request,response);
		}
				
				
	//CONFIRMATION occurs in list.jsp It initializes the game giving players armies and assigning them on the map
      else if (operation.equalsIgnoreCase("CONFIRMATION")) {
   
	 	//checks to see if # of players is between 3-6 and if each player has different countries    		
        	if(players.size() > 2 && players.size() < 7 && seperateCountries(players)){
        		game.finishAddingPlayers();
        		game.finishConfirmation();
        		Territory[][] map = game.initializeBoard();
        		request.setAttribute("players",players);
        		request.setAttribute("turn",game.getCurrTurn());
        		Player currplayer = game.getPlayers().get(game.getCurrTurn());
        		request.setAttribute("currplayer",currplayer);
        		request.setAttribute("map",game.getMap());
>>>>>>> master
			
				TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
				String territory = terr.firstKey();
				TreeMap<Integer,Unit> occupants = getOccupantsFromString(territory,game);
				request.setAttribute("occupants",occupants);
			
    	    	System.out.println("We're starting the game! It's turn "+game.getCurrTurn());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request,response);
        	}
			
        	else{
        		System.out.println("The number of players in the game isn't correct!");
<<<<<<< HEAD
            	request.setAttribute("players", players);
        		RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/list.jsp");
                        dispatcher.forward(request,response);
        	}*/
        } else if (operation.equalsIgnoreCase("ADD")){ //add
        	// input
            RiskGame game = getGameFromID(request);
            String name = request.getParameter("Name");
        	String country = request.getParameter("Country");
            int iPlayerActionsSoFar = Integer.parseInt(request.getParameter("playerActionsSoFar"));
            
            // state
            boolean stateCheck = false;
            String success = null;
            if (game != null) {
                System.out.println("Game State: " +game.getGameState());
                if (game.getGameState() == RiskGame.ADD_PLAYERS && 
                            game.getPlayerActionsSoFar() == iPlayerActionsSoFar) {
                    stateCheck = true;
                    success = game.addPlayer(name, country);
                }
            } else {
                System.out.println("can't find gameID");
            }
            
            // dispatch
            if (success != null) {
                listDispatcher(request, response, game);
            } else if (stateCheck) {
                System.out.println(success);
                listDispatcher(request, response, game);
            } else {
                indexDispatcher(request, response);
            }
            
            /*
        	if(players.size() <6)
        	{
        	String name = request.getParameter("Name");
        	String country = request.getParameter("Country");
        	game.addPlayer(name,country);
        	players = game.getPlayers();
=======
           	request.setAttribute("players", players);
        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);
        	}
      
		} 
		
		//ADD occurs in list.jsp It adds new players to the game
		else if (operation.equalsIgnoreCase("ADD")){ 
        	
        	if(players.size() <6){
        		String name = request.getParameter("Name");
        		String country = request.getParameter("Country");
        		game.addPlayer(name,country);
       	 	players = game.getPlayers();
>>>>>>> master
            request.setAttribute("players", players);
        		request.setAttribute("turn",game.getCurrTurn());
            RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);
        	}
			
        	else{
        		System.out.println("There are too many players!!!");
<<<<<<< HEAD
        		   request.setAttribute("players", players);
        		 	request.setAttribute("turn",game.getCurrTurn());
        		   RequestDispatcher dispatcher = 
        	                getServletContext().getRequestDispatcher("/list.jsp");
        	            dispatcher.forward(request,response);
        	}*/
        }
    }
    
    /*
    // delete this once everything is moved over to RiskGame.java <--------------------------------
    protected boolean check(int[] coords, Player player)
    {
=======
        		request.setAttribute("players", players);
        		request.setAttribute("turn",game.getCurrTurn());
        	 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/list.jsp");
        	   dispatcher.forward(request,response);
        	}
      }
}

	//method to check if coordinates are adjacent to player's home base
    protected boolean check(int[] coords, Player player){
>>>>>>> master
    	int homebasey = player.getHomebaseCoords()[0];
    	int homebasex = player.getHomebaseCoords()[1];
    	
    	int y = coords[0];
    	int x = coords[1];
    	
    	return Math.abs(homebasey-y) <= 1 && Math.abs(homebasex-x) <=1;
    }
<<<<<<< HEAD
    
    // delete this once everthing is moved over to RiskGame.java<---------------------------------
=======

	//checks if coordinates are adjacent to one another
>>>>>>> master
	protected boolean checkAdjacent(int[] homeCo, int[] adjacentCo){
		int homeX = homeCo[1];
		int homeY = homeCo[0];
		
		int adjX = adjacentCo[1];
		int adjY = adjacentCo[0];
		
		return Math.abs(homeX-adjX) <= 1 && Math.abs(homeY-adjY) <=1;
	}
	
	//pulls coordinates from territory string
	protected int[] getCoordinatesFromString(String terrString){
		int index = terrString.indexOf('[');
		int index2 = terrString.indexOf(']');
		int indexC = terrString.indexOf(',');
		String subA = terrString.substring(index+1,indexC);
		String subB = terrString.substring(indexC+1,index2);
		int coA = Integer.parseInt(subA);
		int coB = Integer.parseInt(subB);
		int[] coords = {coA,coB};
	
		return coords;
	}
	
	//pull occupants from territory string
	protected TreeMap<Integer,Unit> getOccupantsFromString(String terrString, RiskGame game){
		int[] coords = getCoordinatesFromString(terrString);
		int coA = coords[0];
    	int coB = coords[1];
		TreeMap<Integer,Unit> occupants = game.getMap()[coA][coB].getOccupants();
		return occupants;
	}

<<<<<<< HEAD
    //makes sure each player is representing a different country
	//I think this is redundant with addPlayer in RiskGame
    // delete this once everything is moved over to RiskGame.java<------------------------------------
    private boolean seperateCountries(ArrayList<Player> players)
    {
     System.out.println("The size of the array "+players.size());
     boolean go = true;
     //0-1 size =3
     for(int a = 0; a<players.size()-1;a++)
     { //1-2
     for(int b = a+1; b<players.size();b++)
     {
     System.out.println(a+" "+b);
     if(players.get(a).getCountry().equals(players.get(b).getCountry()))
     {	
     System.out.println("Brothers can't fight brothers!");
     go = false;
     break;
     }
     }
    
     }
     return go;
    
    }*/
    
    
    // HTTP methods need to retrieve the proper game object, if needed.
=======
	//checks if all players have seperate countries
	private boolean seperateCountries(ArrayList<Player> players){
		System.out.println("The size of the array "+players.size());
		boolean go = true;
		
			for(int a = 0; a<players.size()-1;a++){ 
     			for(int b = a+1; b<players.size();b++){
					System.out.println(a+" "+b);
					if(players.get(a).getCountry().equals(players.get(b).getCountry())){	
     					System.out.println("Brothers can't fight brothers!");
     					go = false;
     					break;
     				}
    			}
     		}
     return go;
	}
	
>>>>>>> master
    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException 
    {
        System.out.println("In doGet()");
        RiskGame game = getGameFromID(request);
        
        //listDispatcher(request, response, aGame);
        if (game != null) {
            request.setAttribute("players", game.getPlayers());
            RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);
        } else {
            indexDispatcher(request, response);
        }
    }
<<<<<<< HEAD
    
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException
    {
        /*
        System.out.println("In doPut()");
        String title = (String) request.getParameter("Name");
        String task = (String)  request.getParameter("Country");
        int id = getId(request);
        players.set(id, new Player(title, task)); 
        for(Player a:players)
        {
        	System.out.println(a);
        }
        
        //listDispatcher(request, response, aGame);
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);*/
    }
    
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException 
    {
        /*
        System.out.println("In doDelete()");
        int id = getId(request);
        game.removePlayer(id);
        //listDispatcher(request, response, aGame);
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);*/
    }
    
    private int getId(HttpServletRequest request) 
    {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
=======

	protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException{

		System.out.println("In doPut()");
		String title = (String) request.getParameter("Name");
		String task = (String)  request.getParameter("Country");
		int id = getId(request);
		players.set(id, new Player(title, task)); 
			for(Player a:players){
        		System.out.println(a);
			}
        
		request.setAttribute("players", players);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/list.jsp");
		dispatcher.forward(request,response);
	}

	protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException{
				
		System.out.println("In doDelete()");
		int id = getId(request);
		game.removePlayer(id);
		request.setAttribute("players", players);
    	request.setAttribute("turn",game.getCurrTurn());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/list.jsp");
      dispatcher.forward(request,response);
   }

	private int getId(HttpServletRequest request){
		String uri = request.getPathInfo();
		        
	//Strip off the leading slash, e.g. "/2" becomes "2"
		String idStr = uri.substring(1, uri.length()); 
>>>>>>> master
        return Integer.parseInt(idStr);
    }
    
    private RiskGame getGameFromID(HttpServletRequest request) {
        String gameIDString = request.getParameter("gameID");
        System.out.println("The gameID string: " + gameIDString);
        RiskGame aGame = null;
        if (gameIDString != null && !gameIDString.equals("")) {
            int gameID = Integer.parseInt( gameIDString );
            System.out.println("getGameFromID:   " + gameID);
            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getGameID() == gameID) {
                    aGame = games.get(i);
                    break;
                }
            }
        }
        return aGame;
    }
    
    
    // dispatch functions, to make constructing the .jsp pages more straitforward.
    private void indexDispatcher(HttpServletRequest request, HttpServletResponse response)
                                    throws IOException, ServletException {
        System.out.println("Back to the index!");
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/index.html");
        dispatcher.forward(request,response);
    }
    
    private void listDispatcher(HttpServletRequest request, HttpServletResponse response, 
                                            RiskGame aGame) throws IOException, ServletException {
        System.out.println("In list dispatcher.");
        request.setAttribute("players", aGame.getPlayers());
        request.setAttribute("gameID", aGame.getGameID());
        request.setAttribute("playerActionsSoFar", aGame.getPlayerActionsSoFar());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void confirmationDispatcher(HttpServletRequest request, HttpServletResponse response, 
                                            RiskGame aGame) throws IOException, ServletException {
        Player currplayer = aGame.getCurrentPlayer();
        request.setAttribute("currplayer",currplayer);
        ArrayList<Player> playerList = aGame.getPlayers();
        request.setAttribute("gameID", aGame.getGameID());
        request.setAttribute("players",playerList);
        request.setAttribute("turnCount", aGame.getTurnCount());
        request.setAttribute("playerTurn", aGame.getCurrTurn());// needs player actions so far
        Territory[][] newMap = aGame.getMap();
        request.setAttribute("map",newMap);
        Unit unit = new Unit("Space Frigate",40,6,0, currplayer);
        TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
        String territory = terr.firstKey();
        request.setAttribute("selectedTerritory",territory);
        int index = territory.indexOf('[');
        int index2 = territory.indexOf(']');
        int indexC = territory.indexOf(',');
        String subA = territory.substring(index+1,indexC);
        String subB = territory.substring(indexC+1,index2);
        int coA = Integer.parseInt(subA);
        int coB = Integer.parseInt(subB);
        TreeMap<Integer,Unit> occupants = aGame.getMap()[coA][coB].getOccupants();
        request.setAttribute("occupants", occupants);
        
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request,response);
    }
    
    private void commandDispatcher(HttpServletRequest request, HttpServletResponse response, 
                                            RiskGame aGame) throws IOException, ServletException {
        String territory = null;
        try {
            territory = request.getParameter("Players");
        } catch (Exception e) {
            System.out.println("using default territory");
        }
        
        Player currplayer = aGame.getCurrentPlayer();
        request.setAttribute("currplayer",currplayer);
        request.setAttribute("gameID", aGame.getGameID());
        request.setAttribute("turnCount", aGame.getTurnCount());
        request.setAttribute("playerTurn", aGame.getCurrTurn());
        request.setAttribute("playerActionsSoFar", aGame.getPlayerActionsSoFar());
        Territory[][] newMap = aGame.getMap();
        request.setAttribute("map", newMap);
        ArrayList<Player> playerList = aGame.getPlayers();
        request.setAttribute("players", playerList);
        TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
        if (territory == null) {
            territory = terr.firstKey();
        }
        request.setAttribute("selectedTerritory",territory);
        int index = territory.indexOf('[');
        int index2 = territory.indexOf(']');
        int indexC = territory.indexOf(',');
        String subA = territory.substring(index+1,indexC);
        String subB = territory.substring(indexC+1,index2);
        int coA = Integer.parseInt(subA);
        int coB = Integer.parseInt(subB);
        request.setAttribute("selectedA", coA);
        request.setAttribute("selectedB", coB);
        TreeMap<Integer,Unit> occupants = aGame.getMap()[coA][coB].getOccupants();
        request.setAttribute("occupants", occupants);
        
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/command.jsp");
        dispatcher.forward(request,response);
    }
}
