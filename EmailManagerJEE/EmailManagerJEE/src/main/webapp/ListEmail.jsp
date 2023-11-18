<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="com.java.Model"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ListEmail</title>
</head>
<body>
<h1>Membres:</h1>
<% for (Object Model : (ArrayList<Model>)session.getAttribute("file")) {
     out.println(Model);%><br><%} %>
<hr><br>
<form  method="post" action="ListEmailServlet">
<h4>Entrer votre adresse email</h4> : <INPUT TYPE=TEXT NAME=adrs><BR>
<INPUT TYPE=SUBMIT NAME="button" VALUE="Subscribe"/>
<INPUT TYPE=SUBMIT NAME="button" VALUE="Unsubscribe"/>
</form>
</body>
</html>