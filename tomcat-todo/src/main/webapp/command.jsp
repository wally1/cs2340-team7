<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="edu.gatech.cs2340.todo.model.Territory" %>
<%@ page import="edu.gatech.cs2340.todo.model.Resource" %>
<%@ page import="edu.gatech.cs2340.todo.model.Unit" %>
<%@ page import="java.util.*" %>

<html>
<head>


<%ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
Territory[][] map = (Territory[][]) request.getAttribute("map"); 
Player currPlayer = (Player) request.getAttribute("currplayer");
TreeMap<Integer,Unit> occupants = (TreeMap<Integer,Unit>) request.getAttribute("occupants");
String selectedTerritory = (String) request.getAttribute("selectedTerritory");
%>

<title>It is <%= currPlayer.getName() %>'s turn to command his Units!</title>
Choose one of your Territories to examine!
<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "TERRITORY"/>
<select name = "Players">
<%TreeMap<String,Integer> terr = currPlayer.getOccupiedTerritories();
	for(String key: terr.keySet()) {%>
<option value = "<%=key%>"><%=key%></option>
<%} %>
<input type="submit" value="Select this Territory"/>
</select>
</form>


The current selected territory is: <%= selectedTerritory%>
<br><br>
Select the units you'd like to attack or move with!
<form action = "update" method="POST">
<%	for(int id: occupants.keySet()) {%>
	<input type="checkbox" name="unit" value="<%=id%>"><%=occupants.get(id)%><br>
<%} %>
</select>

<br>

Choose the Territory you'd like to move to!
<br>
<input type="hidden" name = "operation" value = "MOVE"/>
<input type="text" name="MoveCoordY"/>
<input type="text" name="MoveCoordX"/>

<input type="submit" value="Move!" />
</form>
<br>
<br>

<form action = "update" method="POST">
<%	for(int id: occupants.keySet()) {%>
	<input type="checkbox" name="unit" value="<%=id%>"><%=occupants.get(id)%><br>
<%} %>
</select>
<br>
Choose the Territory you'd like to attack!
<br>
<input type="hidden" name = "operation" value = "ATTACK"/>
<input type="text" name="AttackCoordY"/>
<input type="text" name="AttackCoordX"/>

<input type="submit" value="Attack!" />
</form>
<br>
<form action = "update" method = "POST">
<input type = "hidden" name = "operation" value = "TURN"/>
<input type = "submit" value = "End The Turn"/>
</form>


<br>

<font face="courier">
[~]<% for(int a = 0; a < 15;a++){ %>[<%=a%>]<%}%>
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
