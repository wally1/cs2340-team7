package edu.gatech.cs2340.todo.controller;

import edu.gatech.cs2340.todo.model.Todo;
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

    ArrayList<Todo> todos = new ArrayList<Todo>();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPost()");
        // Handle the hidden HTML form field that simulates
        // HTTP PUT and DELETE methods.
        String operation = (String) request.getParameter("operation");
        System.out.println(operation);
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
     
        if (null == operation){operation = "CONFIRMATION";}
        System.out.println("operation is " + operation);
        if (operation.equalsIgnoreCase("PUT")) {
            System.out.println("Delegating to doPut().");
            doPut(request, response);
        } 
        else if (operation.equalsIgnoreCase("NEW")){
        	System.out.println("THIS IS A NEW GAME!");
        	todos = new ArrayList<Todo>();
        	//clears the players
        }
        else if (operation.equalsIgnoreCase("DELETE")) {
            System.out.println("Delegating to doDelete().");
            doDelete(request, response);
        } else if (operation.equalsIgnoreCase("CONFIRMATION")) {
        	if(todos.size() > 2 && todos.size() < 7){
        	System.out.println("CONFIRMATION CONFIRMATION CONFIRMATION!");
        	request.setAttribute("todos", todos);
            RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request,response);
        	}
        	else
        	{
        		System.out.println("The number of players in the game isn't correct!");
            	request.setAttribute("todos", todos);
        		RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/list.jsp");
                        dispatcher.forward(request,response);
        	}
        } else if (operation.equalsIgnoreCase("ADD")){ //add
        	
        	if(todos.size() <6)
        	{
            String title = request.getParameter("title");
            String task = request.getParameter("Country"); 
            todos.add(new Todo(title, task));
            request.setAttribute("todos", todos);

            RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);

        	}
        	else
        	{
        		System.out.println("There are too many players!!!");
        		   request.setAttribute("todos", todos);
        		   RequestDispatcher dispatcher = 
        	                getServletContext().getRequestDispatcher("/list.jsp");
        	            dispatcher.forward(request,response);
        	}
        }
    }

    //makes sure each player is representing a different country
//    protected Boolean seperateCountries(TreepMap<Integer,todo> players)
//    {
//    	boolean go = false;
//    	for(Integer key: players.keySet())
//    	{
//    		
//    	}
//    	
//    }
    
    
    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doGet()");
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException     
         {

        System.out.println("In doPut()");
        String title = (String) request.getParameter("title");
        String task = (String)  request.getParameter("Country");
        int id = getId(request);
        todos.set(id, new Todo(title, task));
        for(Todo a:todos)
        {
        	System.out.println(a);
        }
        
     
        
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    	}

         

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doDelete()");
        int id = getId(request);
        todos.remove(id);
        request.setAttribute("todos", todos);
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
