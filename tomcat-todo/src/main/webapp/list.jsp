<%@ page import="edu.gatech.cs2340.todo.model.Todo" %>
<%@ page import="java.util.*" %>

<% TreeMap<Integer, Todo> todos =
   (TreeMap<Integer, Todo>) request.getAttribute("todos"); %>

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

The number of players is <%=todos.keySet().size() %><br>

<% for (Integer id: todos.keySet()) { %>
<% Todo todo = todos.get(id); %>
<tr>
<form action="/todo/update/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP PUT method in server -->
  <input type="hidden" name="operation" value="PUT"/>
  <td><input type="text" name="title" value="<%= todo.getTitle() %>"/></td>
 <td><select name="Country">
<option value="Dominaria">Dominaria</option>
<option value="Kamigawa">Kamigawa</option>
<option value="Ravnica">Ravnica</option>
<option value="Rath">Rath</option>
<option value="Mirrodin">Mirrodin</option>
<option value="Innistrad">Innistrad</option></td>
  <td><input type="submit" value="Update"/></td>

Player <%=id+1%>'s name is <%= todo.getTitle() %> <br>
Player <%=id+1%> is from <%= todo.getTask() %> <br>
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
  <td><input type="text" name="title"/></td><td>
 <select name="Country">
<option value="Dominaria">Dominaria</option>
<option value="Kamigawa">Kamigawa</option>
<option value="Ravnica">Ravnica</option>
<option value="Rath">Rath</option>
<option value="Mirrodin">Mirrodin</option>
<option value="Innistrad">Innistrad</option></td>
  <td><input type="submit" value="Add"/></td>
</form>
<td></td> <!-- empty cell to align with previous cells -->
</tr>
</table>
<form action = /todo/confirmation.jsp>

<input type ="submit" value ="Finish!">
</form>

</body>
</html>