<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="edu.gatech.cs2340.todo.model.Territory" %>
<%@ page import="edu.gatech.cs2340.todo.model.Resource" %>
<%@ page import="edu.gatech.cs2340.todo.model.Unit" %>
<%@ page import="java.util.*" %>


<% ArrayList<Player> players = (ArrayList<Player>)request.getAttribute("players"); %>


<html>
<head>
<title>Pregame!</title>

<% for (Player id: players) { %>
<%= id %><br>
<% } %>

Let's make a map! <br>
<br>
<%Resource treasure = new Resource("Excalibur",null);%>

<%Territory[][] map = new Territory[10][10];%>

<%  for(int a = 0; a <10; a++){%>
<%	for(int b = 0; b < 10; b++)	{%>
<%			int[] coord = new int[2];%>
<%			coord[0] = a;%>
<%			coord[1] = b; %>
<%			map[a][b] = new Territory("Territory ["+a+","+b+"]",coord,"New Foundland"); %>
<%			if (a== 5 && b == 5){map[a][b].spawnWith(treasure);}	 %>
<%			System.out.println(map[a][b]);	%>
			[<%if(map[a][b].hasResources()) { %>X  <% }    %>]
<% } %>
<br>
<%}%>



</head>
</html>