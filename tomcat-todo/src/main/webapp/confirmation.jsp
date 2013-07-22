<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="edu.gatech.cs2340.todo.model.Territory" %>
<%@ page import="edu.gatech.cs2340.todo.model.Resource" %>
<%@ page import="edu.gatech.cs2340.todo.model.Unit" %>
<%@ page import="java.util.*" %>


<% //some initializations
//nearly all of this code needs to merged into the RiskGame class.


%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<title>The Game!</title>
</head>


<body>
<%//instantiating the map 
ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
Territory[][] map = (Territory[][]) request.getAttribute("map"); 
Player currPlayer = (Player) request.getAttribute("currplayer");
TreeMap<Integer,Unit> occupants = (TreeMap<Integer,Unit>) request.getAttribute("occupants");
Collections.sort(players);
currPlayer.resetArmy();%>

<% for (Player player: players) { %>
<span style ="color:<%= player.getColor() %>"> <%= player %></span><br>
<% } %>

<br>
Let's make a map! <br><br>
The "O"s represent each player's home base!<br>
The "X"s represent impassable asteroids!<br>
The "#"s in each territory represent the number of Units in each territory!<br>
<br>
<br>

<font face="courier">

It's <span style="color<%=currPlayer.getColor()%>"><%=currPlayer.getName()%>'s turn to spawn a new Unit!</span>
<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "SPAWN"/>
<input type="text" name="Coord1"/>
<input type="text" name="Coord2"/>

<input type="submit" value="Spawn another Unit!" />
</form>

<div class=bg>

<%//printing the map 

  for(int a = 0; a <9; a++)
  { %>
	<%
	for(int b = 0; b < 15; b++)	
	{
 	if(map[a][b].hasResources()) { %>[x]<% }   
	else if(map[a][b].isHomeBase()) {%></span><img src="<%=map[a][b].getPlayer().getHomeBasePath()%>"><%} 
 	else if(map[a][b].isOccupied()) { %><img src="<%=map[a][b].getPlayer().getUnitImgPath()%>"><%} 
	else if (!map[a][b].hasResources() && !map[a][b].isHomeBase() && !map[a][b].isOccupied()) {%><img src="transparent.png"><%}  
	
	 }%>
<br>	
<%}%>
</div>
</font> 
</body>
</html>