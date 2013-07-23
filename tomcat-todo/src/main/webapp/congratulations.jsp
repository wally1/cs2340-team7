<html>
<head>
<title>And The Winner Is...</title>

<% String winner = (String) request.getAttribute("winner");
String country = (String) request.getAttribute("country"); %>

<font size = "18">
Congratulations <%=winner%> of <%=country %>! You are the Champion of the Stars!

</font>

<form = "index.html">
<input type ="Submit" value ="Play another game?">
</form>