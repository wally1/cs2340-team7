<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="edu.gatech.cs2340.todo.model.Territory" %>
<%@ page import="edu.gatech.cs2340.todo.model.Resource" %>
<%@ page import="edu.gatech.cs2340.todo.model.Unit" %>
<%@ page import="java.util.*" %>


<% //some initializations
//nearly all of this code needs to merged into the RiskGame class.
%>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<%//instantiating the map 
ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
Territory[][] map = (Territory[][]) request.getAttribute("map");
Player currPlayer = (Player) request.getAttribute("currplayer");
TreeMap<Integer,Unit> occupants = (TreeMap<Integer,Unit>) request.getAttribute("occupants");
Collections.sort(players);
currPlayer.resetArmy();
int gameID = (Integer)request.getAttribute("gameID");
int turnCount = (Integer)request.getAttribute("turnCount");
int playerTurn = (Integer)request.getAttribute("playerTurn");
//int playerActionsSoFar = (Integer)request.getAttribute("playerActionsSoFar"); 
int imageTopPX = 300;
int imageLeftPX = 0;%>


<html>
<head>
<title>The Game!</title>
GameID: <%=gameID%><br>
<form action=/todo/update method="POST">
<input type="hidden" name="operation" value="LOAD" />
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type ="Submit" value = "Refresh" ><br>
</form>

<% for (Player player: players) { 
if(!player.hasLost()) {%>
<span style ="color:<%= player.getColor() %>"> <%= player %></span><br>
<% }} %>
<br>

<font face="courier">
<span style="color:<%=currPlayer.getColor()%>">
It's <%=currPlayer.getName()%>'s turn to spawn a new Unit!</span>
<form action = "update" method="POST">
<input type="hidden" name = "operation" value = "SPAWN"/>
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="turnCount" value="<%=turnCount%>">
<input type="hidden" name="playerTurn" value="<%=playerTurn%>">
<input type="text" name="Coord1"/>
<input type="text" name="Coord2"/>

<input type="submit" value="Spawn!" />
</form>
</font>
<font face="courier" color="red" size="2px">
<img style="position:absolute; top:<%=imageTopPX%>px; left:<%=imageLeftPX%>px; width:1125px; height:675px" src="images/grid.png">
<%//printing the map 
  for(int a = 0; a <9; a++)
  { %><%
	for(int b = 0; b < 15; b++)
	{
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