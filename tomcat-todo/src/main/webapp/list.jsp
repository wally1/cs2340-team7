<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="java.util.*" %>

<% ArrayList<Player> players =
   (ArrayList<Player>) request.getAttribute("players"); %>

<html>
<head>
<title>New Game Setup</title>
<link rel="stylesheet" type="text/css" href="stylesheets/main.css">
</head>
<body>
<h1>Risk!</h1>

<table>
<tr>
<th>Player</th><th>Country</th>
</tr>

The number of players is <%=players.size() %><br>

<% for (int id =0;id<players.size();id++) { %>
<% Player player = players.get(id); %>
<tr>
<form action="/todo/update/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP PUT method in server -->
  <input type="hidden" name="operation" value="PUT"/>
  <td><input type="text" name="Name" value="<%= player.getName() %>"/></td>
 <td><select name="Country">
<option value="Polaris">Polaris</option>
<option value="Alpha-Centauri">Alpha-Centauri</option>
<option value="Char">Char</option>
<option value="Midichloria">Midichloria</option>
<option value="Borg">Borg</option>
<option value="HAL Space Station">HAL Space Station</option></td>
  <td><input type="submit" value="Update"/></td>


Player <%=id+1%>'s name is <%= player.getName() %> <br>
Player <%=id+1%> is from <%= player.getCountry() %> <br>
</form>
<td valign="bottom">
  <form action="/todo/delete/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP DELETE method in server -->
    <input type="hidden" name="operation" value="DELETE"/>
    <input type="submit" value="Delete"/>
  </form>
 </td>
</tr>
<% } %>
<tr>

<form action="/todo/update" method="POST">
  <td><input type="text" name="Name"/></td><td>
<input type = "hidden" name = "operation" value="ADD" /></td>
<td> <select name="Country">
<option value="Polaris">Polaris</option>
<option value="Alpha-Centauri">Alpha-Centauri</option>
<option value="Char">Char</option>
<option value="Midichloria">Midichloria</option>
<option value="Borg">Borg</option>
<option value="HAL Space Station">HAL Space Station</option></td>
  <td><input type="submit" value="Add"/></td>
</form>

<td></td> <!-- empty cell to align with previous cells -->
</tr>
</table>

<form action = "/todo/update" method = "POST">

<%
ArrayList<Integer> turns = new ArrayList<Integer>();
for(int b = 1; b <= players.size(); b++)
{turns.add(b);}
Collections.shuffle(turns);
for(int c = 0; c < players.size(); c++)
{ players.get(c).setTurn(turns.get(c));
players.get(c).setArmySize(35-((players.size()-3)*5)); }
%>



<td></td>

<input type ="submit"  value ="Finish!"/>
</form>


</body>
</html>