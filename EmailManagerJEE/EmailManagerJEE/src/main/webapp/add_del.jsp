<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Adresse
<% 	out.print(session.getAttribute("email"));
	out.print(" ");
 	out.print(session.getAttribute("etat"));%> <br>
<a href=ListEmailServlet>Afficher la liste</a>
</body>
</html>