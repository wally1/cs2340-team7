<%@ page import="edu.gatech.cs2340.todo.model.Todo" %>
<%@ page import="java.util.*" %>

<% TreeMap<Integer, Todo> todos = 
   (TreeMap<Integer, Todo>) request.getAttribute("todos"); %>

<html>
<head>


<title>New Game Setup</title>
</head>
<body>
<h1>Players</h1>
<%! int numplayers =  3;%>


<form action="">
<select name="Number of Players">
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
</select>

<% if(request.getParameter("Number of Players")!=null)
{ 
 numplayers = Integer.parseInt(request.getParameter("Number of Players")); 
} 
%>

<input type="Submit" value = "Go!">

</form>

The number of players is: <%= numplayers %>



<table>
<tr>
<th>Player</th><th>Country</th>
</tr>

<% for (Integer id: todos.keySet()) { %>
<% Todo todo = todos.get(id); %>
<tr>
<form action="/todo/update/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP PUT method in server -->
  <input type="hidden" name="operation" value="PUT"/>
  <td><input type="text" name="title" value="<%= todo.getTitle() %>"/></td>

  <td><input type="submit" value="Update"/></td>
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

<form action="/todo/update/" method="POST">

<% String name = ""; %>

<% for (int i=1; i<=numplayers; i++) { %>

  <td><input type="text" name="Player"/></td>

  <td><select name="Country">
<option value="3">Dominaria</option>
<option value="4">Kamigawa</option>
<option value="5">Ravnica</option>
<option value="6">Rath</option>
<option value="7">Mirrodin</option>
<option value="8">Innistrad</option>
  <td><input type="submit" value="Update"/></td></form>
</td><tr></tr>
</select>
<% } %>
  <td><input type="submit" value="Confirm"/></td>
</form>
<td></td> <!-- empty cell to align with previous cells -->
<br>

</tr>
</table>

</body>
</html>
