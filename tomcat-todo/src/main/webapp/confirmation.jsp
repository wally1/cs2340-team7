<%@ page import="edu.gatech.cs2340.todo.model.Todo" %>
<%@ page import="java.util.*" %>


<% ArrayList<Todo> todos = (ArrayList<Todo>)request.getAttribute("todos"); %>


<html>
<head>
<title>Pregame!</title>

<% for (Todo id: todos) { %>
<%= id %><br>
<% } %>



</head>
</html>