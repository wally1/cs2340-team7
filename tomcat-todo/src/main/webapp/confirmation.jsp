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
Collections.sort(players);%>



<% for (Player player: players) { %>
<span style ="color:<%= colorCode(player) %>"> <%= player %></span><br>
<% } %>
<br>
Let's make a map! <br><br>
The "O"s represent each player's home base!<br>
The "X"s represent impassable asteroids!<br>
The "#"s in each territory represent the number of Units in each territory!<br>
<br>
<br>

<select name = "Players">
<% TreeMap<String,Integer> terr = currPlayer.getOccupiedTerritories();
for(String key: terr.keySet()) {%>
<option value = "<%=key%>"><%=key%></option>
<%} %>
</select>

<font face="courier">

It's <span style="color<%=colorCode(currPlayer)%>"><%=currPlayer.getName()%>'s turn to spawn a new Unit!</span>
<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "SPAWN"/>
<input type="text" name="Coord1"/>
<input type="text" name="Coord2"/>

<input type="submit" value="Spawn another Unit!" />
</form>


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
<select name = "Units">
	for(String key: occupants.keySet()) {%>
	<option value = "<%=key%>"><%=key%></option>
<%} %>
<input type="submit" value="Update"/>
</select>
</form>	


<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "ATTACK"/>
<input type="text" name="Coord1"/>
<input type="text" name="Coord2"/>

<input type="submit" value="Attack!" />
</form>

<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "MOVE"/>
<input type="text" name="Coord1"/>
<input type="text" name="Coord2"/>

<input type="submit" value="Move!" />
</form>

<br>

[~]<% for(int a = 0; a < 15;a++){ %>[<%=a%>]<%}%>
<br>
<%//printing the map 
  for(int a = 0; a <9; a++)
  { %>
	[<%=a%>]<%
	for(int b = 0; b < 15; b++)	
	{
 	if(map[a][b].hasResources()) { %>[x]<% }   
	if(map[a][b].isHomeBase()) {%>[<span style="color:<%=colorCode(map[a][b].getPlayer())%>">O</span>]<%} 
 	if(map[a][b].isOccupied()) { %>[<span style="color:<%=colorCode(map[a][b].getPlayer())%>"><%=map[a][b].getOccupants().size()%></span>]<%} 
	if (!map[a][b].hasResources() && !map[a][b].isHomeBase() && !map[a][b].isOccupied()) {%>[ ]<%}  
	 }%>
<br>	
<%}%>
</font>
<%//color string can be probably be stored in player instead of making a method to calculate it
//returns the color associated with each country %>
<%! public String colorCode(Player player){ 
if (player==null) {return "pink";}
else if (player.getCountry().equals("Polaris")) { return "purple";}
else if (player.getCountry().equals("Alpha-Centauri")) { return "green";}
else if (player.getCountry().equals("Char")) { return "red";} 
else if (player.getCountry().equals("Midichloria")) { return "blue";}
else if (player.getCountry().equals("Borg")) { return "gray";} 
else if (player.getCountry().equals("HAL Space Station")) { return "orange";}
 return "";} %>
 




<% /* for(Player player: players) 
{
	Unit z = new Unit("Zergling",35,5,1,null);
	System.out.println(player.getArmy().keySet());
	TreeMap<Integer,Unit> army = player.getArmy();
	System.out.println(army.size());

System.out.println("~~~~");

} */  
%>

<%/*  for(Territory[] a: map) 
	{
	for(Territory b: a)
		{
			System.out.println(b);
			System.out.println(b.getOccupants());
		}
	System.out.println("=====");

	}
System.out.println("~~~~");
 */
%>


</head>
</html>