<%@ page import="edu.gatech.cs2340.todo.model.Todo" %>
<%@ page import="java.util.*" %>

<% TreeMap<Integer, Todo> todos =   (TreeMap<Integer, Todo>) request.getAttribute("todos"); %>
<% //String todos = request.getParameter("todos"); %>


<html>
<head>
<title>Pregame!</title>

<%= todos %>




</head>
</html>