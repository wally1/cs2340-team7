<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="java.util.*" %>

<% ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players"); 
   int gameID = (Integer)request.getAttribute("gameID");
   int playerActionsSoFar = (Integer)request.getAttribute("playerActionsSoFar");
   %>
 

<img class="logo" src="logo.png">
<html>
<head>
<title>New Game Setup</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body>
<span style="color:white">
<h1>Champion of the Stars</h1>
GameID: <%=gameID%><br>
<form action=/todo/update method="POST">
<input type="hidden" name="operation" value="LOAD" />
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type ="Submit" value = "Refresh" ><br>
</form>
<table>
<tr>

<th>Player</th><th>Country</th>
</span>
</tr>

The number of players is <%=players.size() %><br>
</span>
<% for (int id =0;id<players.size();id++) { %>
<% Player player = players.get(id); %>
<tr>

<form action="/todo/update/<%= id %>" method="POST">
  <input type="hidden" name="operation" value="PUT"/>
  <input type="hidden" name="gameID" value="<%=gameID%>">
  <input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
  <td><input type="text" name="Name" value="<%= player.getName() %>"/></td>
 <td><select name="Country">
<option value="Polaris">Polaris</option>
<option value="Alpha-Centauri">Alpha-Centauri</option>
<option value="Char">Char</option>
<option value="Midichloria">Midichloria</option>
<option value="Borg">Borg</option>
<option value="HAL Space Station">HAL Space Station</option></td>
  <td><input type="submit" value="Update"/></td>
  
<span style ="color:<%= player.getColor() %>">
Player <%=id+1%>'s name is <%= player.getName() %> <br>
Player <%=id+1%> is from  <%= player.getCountry() %></span><br>
</form>
<td valign="middle">
  <form action="/todo/delete/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP DELETE method in server -->
    <input type="hidden" name="operation" value="DELETE"/>
    <input type="hidden" name="gameID" value="<%=gameID%>">
    <input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
    <input type="submit" value="Delete"/>
  </form>
 </td>
</tr>
<% } %>
<tr>

<form action="/todo/update" method="POST">
 <td> <input type="text" name="Name"/></td>
<input type = "hidden" name = "operation" value="ADD" />
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
<td> <select name="Country">
<option value="Polaris">Polaris</option>
<option value="Alpha-Centauri">Alpha-Centauri</option>
<option value="Char">Char</option>
<option value="Midichloria">Midichloria</option>
<option value="Borg">Borg</option>
<option value="HAL Space Station">HAL Space Station</option></td>
  <td><input type="submit" value="Add"/></td>
</form>


</tr>
</table>

<form action = "/todo/update" method = "POST">
<input type="hidden" name="operation" value="CONFIRMATION">
<input type="hidden" name="gameID" value="<%=gameID%>">
<input type="hidden" name="playerActionsSoFar" value="<%=playerActionsSoFar%>">
<%
ArrayList<Integer> turns = new ArrayList<Integer>();
for(int b = 1; b <= players.size(); b++)
{turns.add(b);}
Collections.shuffle(turns);
for(int c = 0; c < players.size(); c++)
{ players.get(c).setTurn(turns.get(c));
 }
%>



<td></td>

<input type ="submit"  value ="Finish!"/>
</form>


</body>
</html>