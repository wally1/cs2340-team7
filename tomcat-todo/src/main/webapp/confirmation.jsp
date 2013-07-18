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
<title>The Game!</title>



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


<<<<<<< HEAD
<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "TERRITORY"/>
<select name = "Players">
<%TreeMap<String,Integer> terr = currPlayer.getOccupiedTerritories();
	for(String key: terr.keySet()) {%>
<option value = "<%=key%>"><%=key%></option>
<%} %>
<input type="submit" value="Update"/>
</select>
</form>

<form action = "update" method="POST">
<intput type="hidden" name ="operation" value = "UNITS" />
<%	Collection entries = occupants.values();
	Iterator itr = entries.iterator();
	while(itr.hasNext()) {
	String option = itr.next().toString();%>
	<input type="checkbox" name="unit" value="<%=option%>"><font size="2"><%=option%></font><br>
<%} %>
</select>
</form>	
<br>


<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "ATTACK"/>
<input type="text" name="Attack_Co1"/>
<input type="text" name="Attack_Co2"/>

<input type="submit" value="Attack!" />
</form>

<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "MOVE"/>
<input type="text" name="Move_Co1"/>
<input type="text" name="Move_Co2"/>

<input type="submit" value="Move!" />
</form>

<br>


[~]<% for(int a = 0; a < 14;a++){ %>[<%=a%>]<%}%>
=======
[~]<% for(int a = 0; a < 15;a++){ %>[<%=a%>]<%}%>
>>>>>>> a6d3338974276d8d43902d66fbee4662e2769504
<br>
<%//printing the map 
  for(int a = 0; a <9; a++)
  { %>
	[<%=a%>]<%
	for(int b = 0; b < 15; b++)	
	{
 	if(map[a][b].hasResources()) { %>[x]<% }   
	if(map[a][b].isHomeBase()) {%>[<span style="color:<%=map[a][b].getPlayer().getColor()%>">O</span>]<%} 
 	if(map[a][b].isOccupied()) { %>[<span style="color:<%=map[a][b].getPlayer().getColor()%>"><%=map[a][b].getOccupants().size()%></span>]<%} 
	if (!map[a][b].hasResources() && !map[a][b].isHomeBase() && !map[a][b].isOccupied()) {%>[ ]<%}  
	 }%>
<br>	
<%}%>
</font> 

</head>
</html>