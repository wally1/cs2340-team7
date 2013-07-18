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

 	RiskGame game = new RiskGame(1);
    ArrayList<Player> players = game.getPlayers();

    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
    	
        System.out.println("In doPost()");
   
        // Handle the hidden HTML form field that simulates
        // HTTP PUT and DELETE methods.
        String operation = (String) request.getParameter("operation");
        System.out.println(operation);
        System.out.println("It is turn "+game.getCurrTurn());

		
		  
	
				
		  
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
     
        //probably more elegant fix later, like just changing operation to "Confirmation"
        if (null == operation){operation = "CONFIRMATION";}
    
        
        if (operation.equalsIgnoreCase("PUT")) {
            System.out.println("Delegating to doPut().");
            doPut(request, response);
        } 
        else if (operation.equalsIgnoreCase("DELETE")) {
            System.out.println("Delegating to doDelete().");
            doDelete(request, response);
        }
        else if(operation.equalsIgnoreCase("TURN"))
        {
        	System.out.println("We're going to the next turn!");
        	game.nextTurn();
        	System.out.println("It is now turn "+game.getCurrTurn());
        	Player currplayer = game.getPlayers().get(game.getCurrTurn());
        	request.setAttribute("currplayer",currplayer);
        	Territory[][] newMap = game.getMap();
        	players = game.getPlayers();
        	request.setAttribute("players",players);
        	request.setAttribute("map",newMap);
        	Unit unit = new Unit("Space Frigate",40,6,0,currplayer);
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
			
    		RequestDispatcher dispatcher = 
                    getServletContext().getRequestDispatcher("/confirmation.jsp");
                    dispatcher.forward(request,response);
        	
        }
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
            	int[] co = {a,b};
            	
            	if(check(co,currplayer))
            	{
            		game.spawn(game.getMap(),unit, 1, co, game.getID());
/*            		game.nextTurn();
            		System.out.println("It is now turn "+game.getCurrTurn());*/
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
                	
            	}
            	
            } 
        else if(operation.equalsIgnoreCase("MOVE"))
        {
        	//grabs checked units
        	//need to make method that passes all values
        	Player currplayer = game.getPlayers().get(game.getCurrTurn());
           	request.setAttribute("currplayer",currplayer);
        	Territory[][] newMap = game.getMap();
        	players = game.getPlayers();
        	request.setAttribute("players",players);
        	request.setAttribute("map",newMap);
        	Unit unit = new Unit("Space Frigate",40,6,0,currplayer);
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
        	
        	
        	if(checkAdjacent(startCo,moveCo))
        	{
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
                    dispatcher.forward(request,response);
        }
        else if(operation.equalsIgnoreCase("ATTACK"))
        {
        	Player currplayer = game.getPlayers().get(game.getCurrTurn());
           	request.setAttribute("currplayer",currplayer);
        	Territory[][] newMap = game.getMap();
        	players = game.getPlayers();
        	request.setAttribute("players",players);
        	request.setAttribute("map",newMap);
        	Unit unit = new Unit("Space Frigate",40,6,0,currplayer);
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
				
        	//grabs checked Units
	        	
	        	String[] selectedUnitIDS = request.getParameterValues("unit");

	        	ArrayList<Unit> selectedUnits = new ArrayList<Unit>();
	        	
	        	for(String ids: selectedUnitIDS)
	        		selectedUnits.add(currplayer.getArmy().get(Integer.parseInt(ids)));
	        	
	           	int[] startCo = selectedUnits.get(0).getTerritory().getCoords();
	        	int x = Integer.parseInt(request.getParameter("AttackCoordX"));
	        	int y = Integer.parseInt(request.getParameter("AttackCoordY"));
	        	int[] moveCo = {y,x};

	        	Territory location = game.getMap()[moveCo[0]][moveCo[1]];
	        	System.out.println("About to move!");
	        	
	        	ArrayList<Unit> victims = new ArrayList<Unit>();
	        	for(int ids: location.getOccupants().keySet())
	        		victims.add(location.getOccupants().get(ids));
	        	
	        	boolean attackSucceed = true;
	        	
	        	if(checkAdjacent(startCo,moveCo))
	        	{
	        		System.out.println("We're attacking!");
	        		for(Unit a: selectedUnits)
	        		{
	        			if(a.getAttacked())
	        				attackSucceed = false;
	        		}
	        		
	        		if(attackSucceed)
	        			game.fight(selectedUnits, victims);
	        		else
	        			System.out.println("Some of the selected units can't attack!");
	        	}
	        	else
	        		System.out.println("You can't attack there!");
	        	
	       		RequestDispatcher dispatcher = 
	                    getServletContext().getRequestDispatcher("/command.jsp");
	                    dispatcher.forward(request,response);
        }

		else if(operation.equalsIgnoreCase("TERRITORY")){
			System.out.println("Displaying Units");
			String territory = request.getParameter("Players");
			request.setAttribute("selectedTerritory",territory);
			int index = territory.indexOf('[');
			int index2 = territory.indexOf(']');
			int indexC = territory.indexOf(',');
			String subA = territory.substring(index+1,indexC);
			String subB = territory.substring(indexC+1,index2);
			int coA = Integer.parseInt(subA);
			int coB = Integer.parseInt(subB);
			TreeMap<Integer,Unit> occupants = game.getMap()[coA][coB].getOccupants();
										
			Territory[][] newMap = game.getMap();
			Player currplayer = game.getPlayers().get(game.getCurrTurn());
           	request.setAttribute("currplayer",currplayer);
           	players = game.getPlayers();
           	request.setAttribute("players",players);
            	request.setAttribute("map",newMap);
					request.setAttribute("occupants",occupants);
            	currplayer = game.getPlayers().get(game.getCurrTurn());
            	request.setAttribute("currplayer",currplayer);
            	
            	RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/command.jsp");
                        dispatcher.forward(request,response);
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
			
			TreeMap<String,Integer> terr = currplayer.getOccupiedTerritories();
			String territory = terr.firstKey();
				int index = territory.indexOf('[');
				int index2 = territory.indexOf(']');
				int indexC = territory.indexOf(',');
				String subA = territory.substring(index+1,indexC);
				String subB = territory.substring(indexC+1,index2);
				int coA = Integer.parseInt(subA);
				int coB = Integer.parseInt(subB);
				TreeMap<Integer,Unit> occupants = game.getMap()[coA][coB].getOccupants();
				request.setAttribute("occupants",occupants);
			
        	System.out.println("We're starting the game! It's turn "+game.getCurrTurn());
            RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request,response);
        	}
        	else
        	{
        		System.out.println("The number of players in the game isn't correct!");
            	request.setAttribute("players", players);
        		RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/list.jsp");
                        dispatcher.forward(request,response);
        	}
      
        } else if (operation.equalsIgnoreCase("ADD")){ //add
        	
        	if(players.size() <6)
        	{
        	String name = request.getParameter("Name");
        	String country = request.getParameter("Country");
        	game.addPlayer(name,country);
        	players = game.getPlayers();
            request.setAttribute("players", players);
        	request.setAttribute("turn",game.getCurrTurn());
            RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);
        	}
        	else
        	{
        		System.out.println("There are too many players!!!");
        		   request.setAttribute("players", players);
        		 	request.setAttribute("turn",game.getCurrTurn());
        		   RequestDispatcher dispatcher = 
        	                getServletContext().getRequestDispatcher("/list.jsp");
        	            dispatcher.forward(request,response);
        	}
        }
    }
    protected boolean check(int[] coords, Player player)
    {
    	int homebasey = player.getHomebaseCoords()[0];
    	int homebasex = player.getHomebaseCoords()[1];
    	
    	int y = coords[0];
    	int x = coords[1];
    	
    	return Math.abs(homebasey-y) <= 1 && Math.abs(homebasex-x) <=1;
    	
    	
    }

	protected boolean checkAdjacent(int[] homeCo, int[] adjacentCo){
		int homeX = homeCo[1];
		int homeY = homeCo[0];
		
		int adjX = adjacentCo[1];
		int adjY = adjacentCo[0];
		
		return Math.abs(homeX-adjX) <= 1 && Math.abs(homeY-adjY) <=1;
		
		
	

	}
		

    //makes sure each player is representing a different country
	//I think this is redundant with addPlayer in RiskGame
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
    
    }
    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException 
    {
        System.out.println("In doGet()");
//        players = new ArrayList<Player>();
   //     System.out.println("Starting a new game, erasing the player ArrayList");

        request.setAttribute("players", players);
    	request.setAttribute("turn",game.getCurrTurn());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException     
    {

        System.out.println("In doPut()");
        String title = (String) request.getParameter("Name");
        String task = (String)  request.getParameter("Country");
        int id = getId(request);
        players.set(id, new Player(title, task)); 
        for(Player a:players)
        {
        	System.out.println(a);
        }
        
     
        
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

   

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException 
    {
        System.out.println("In doDelete()");
        int id = getId(request);
        game.removePlayer(id);
        request.setAttribute("players", players);
    	request.setAttribute("turn",game.getCurrTurn());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    private int getId(HttpServletRequest request) 
    {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
        return Integer.parseInt(idStr);
    }

}
