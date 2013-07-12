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

	//List of RiskGames so you can new/load game?
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
        if (null == operation)
        {
        	operation = "CONFIRMATION";
        }
    
        
        if (operation.equalsIgnoreCase("PUT")) 
        {
            System.out.println("Delegating to doPut().");
            doPut(request, response);
        } 
        else if (operation.equalsIgnoreCase("DELETE")) 
        {
            System.out.println("Delegating to doDelete().");
            doDelete(request, response);
        }
        else if(operation.equalsIgnoreCase("SPAWN")) 
        {
        	System.out.println("We're SPAWNING more units!");
           	int a = Integer.parseInt(request.getParameter("Coord1"));
           	int b = Integer.parseInt(request.getParameter("Coord2"));
           	Player currplayer = game.getPlayers().get(game.getCurrTurn());
           	request.setAttribute("currplayer",currplayer);
           	Unit unit = new Unit("Space Frigate",40,6,0,currplayer); //probably need to give all races same unit, or ugly fix later
           	int[] co = {a,b};
           	Territory[][] newMap = game.getMap();
           	players = game.getPlayers();
           	request.setAttribute("players",players);
           	request.setAttribute("map",newMap);
           	
           	if(check(co,currplayer))
           	{
           	game.spawn(game.getMap(),unit, 1, co, game.getID());
           	game.nextTurn();
           	System.out.println("It is now turn "+game.getCurrTurn());
           	newMap = game.getMap();
           	request.setAttribute("map",newMap);
           	currplayer = game.getPlayers().get(game.getCurrTurn());
           	request.setAttribute("currplayer",currplayer);
           	
           	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request,response);
            }
            else
            {
            	System.out.println("You can't spawn there!");
               	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmation.jsp");
                dispatcher.forward(request,response); 	
            }
            	
        } 
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

    //makes sure each player is representing a different country
    //redundant with RiskGame
    protected boolean seperateCountries(ArrayList<Player> players)
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
            throws IOException, ServletException {
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
            throws IOException, ServletException {
        System.out.println("In doDelete()");
        int id = getId(request);
        game.removePlayer(id);
        request.setAttribute("players", players);
    	request.setAttribute("turn",game.getCurrTurn());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    private int getId(HttpServletRequest request) {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
        return Integer.parseInt(idStr);
    }

}
