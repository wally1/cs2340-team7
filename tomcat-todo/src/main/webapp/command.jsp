<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="edu.gatech.cs2340.todo.model.Territory" %>
<%@ page import="edu.gatech.cs2340.todo.model.Resource" %>
<%@ page import="edu.gatech.cs2340.todo.model.Unit" %>
<%@ page import="java.util.*" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">

<%ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
Territory[][] map = (Territory[][]) request.getAttribute("map");
Player currPlayer = (Player) request.getAttribute("currplayer");
TreeMap<Integer,Unit> occupants = (TreeMap<Integer,Unit>) request.getAttribute("occupants");
String selectedTerritory = (String) request.getAttribute("selectedTerritory");
int selectedA = (Integer) request.getAttribute("selectedA");
int selectedB = (Integer) request.getAttribute("selectedB");
int gameID = (Integer)request.getAttribute("gameID");
int turnCount = (Integer)request.getAttribute("turnCount");
int playerTurn = (Integer)request.getAttribute("playerTurn");
int playerActionsSoFar = (Integer)request.getAttribute("playerActionsSoFar");
int imageTopPX = 0;
int imageLeftPX = 850;%>
<span style="color:<%=currPlayer.getColor()%>">
GameID: <%=gameID%><br>
<form action=/todo/update method="POST">
<input type="hidden" name="operation" value="LOAD" />
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type ="Submit" value = "Refresh" ><br>
</form>

<title>It is <%= currPlayer.getName() %>'s turn to command his Units!</title>
Choose one of your Territories to examine!
<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "TERRITORY"/>
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="turnCount" value="<%=turnCount%>">
<input type="hidden" name="playerTurn" value="<%=playerTurn%>">
<input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
<select name = "Players">
<%TreeMap<String,Integer> terr = currPlayer.getOccupiedTerritories();
	for(String key: terr.keySet()) {%>
<option value = "<%=key%>"><%=key%></option>
<%} %>
<input type="submit" value="Select!"/>
</select>
</form>


The current selected territory is: <%= selectedTerritory%>
<br><br>
Select the units you'd like to attack or move with!
<frameset>
<frame>
<form action = "update" method="POST">
<%	for(int id: occupants.keySet()) {%>
	<input type="checkbox" name="unit" value="<%=id%>"><%=occupants.get(id)%><br>
<%} %>
</select>

<br>

Choose the Territory you'd like to move to!
<br>
<input type="hidden" name = "operation" value = "MOVE"/>
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="turnCount" value="<%=turnCount%>">
<input type="hidden" name="playerTurn" value="<%=playerTurn%>">
<input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
<input type="text" name="MoveCoordY"/>
<input type="text" name="MoveCoordX"/>
</frame>
<input type="submit" value="Move!" />
</form>

<br>
<br>
<frame>
<form action = "update" method="POST">
<%	for(int id: occupants.keySet()) {%>
	<input type="checkbox" name="unit" value="<%=id%>"><%=occupants.get(id)%><br>
<%} %>
</select>
<br>
Choose the Territory you'd like to attack!
<br>
<input type="hidden" name = "operation" value = "ATTACK"/>
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="turnCount" value="<%=turnCount%>">
<input type="hidden" name="playerTurn" value="<%=playerTurn%>">
<input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
<input type="text" name="AttackCoordY"/>
<input type="text" name="AttackCoordX"/>
</frame>
<input type="submit" value="Attack!" />
</form>
<br>
<form action = "update" method = "POST">
<input type = "hidden" name = "operation" value = "TURN"/>
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="turnCount" value="<%=turnCount%>">
<input type="hidden" name="playerTurn" value="<%=playerTurn%>">
<input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
<input type = "submit" value = "End The Turn"/>
</form>
</frameset>

<br>

<font face="courier" color="red" size="2px">
</span>

<img style="position:absolute; top:<%=imageTopPX%>px; left:<%=imageLeftPX%>px; width:1125px; height:675px" src="images/grid.png">
<%//printing the map 
  for(int a = 0; a <9; a++)
  { %><%
	for(int b = 0; b < 15; b++)
	{
    if(a==selectedA && b==selectedB){%><img style="position:absolute; top:<%=imageTopPX+a*75%>px; left:<%=imageLeftPX+b*75%>px; width:75px; height:75px" src="images/pointer.png"><%}
    if(map[a][b].isAsteroid()) { %><img style="position:absolute; top:<%=imageTopPX+a*75%>px; left:<%=imageLeftPX+b*75%>px; width:75px; height:75px" src="images/asteroid.png"><% }   
    if(map[a][b].isHomeBase()) {%><img style="position:absolute; top:<%=imageTopPX+a*75%>px; left:<%=imageLeftPX+b*75%>px; width:75px; height:75px" src="images/<%=map[a][b].getPlayer().getColor()%>_station.png"><%} 
    if(map[a][b].isOccupied()) { %><img style="position:absolute; top:<%=imageTopPX+a*75%>px; left:<%=imageLeftPX+b*75%>px; width:75px; height:75px" src="images/<%=map[a][b].getPlayer().getColor()%>_starship.png"> 
    <DIV style="position:absolute; top:<%=imageTopPX+a*75%>px; left:<%=imageLeftPX+b*75%>px; width:75px; height:75px">(<%=a%>,<%=b%>)<br>Units:<%=map[a][b].getOccupants().size()%></DIV>	
<%									}
    }
  }%>

</font>



</head>
</html>
