<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TaskOne</title>
</head>
<body>
<%!
    String concat(int id, String name) {
        return id + " " + name;
    }
%>

<%
    int id = 10;
    String name = "Loai";

    String result = concat(id, name);
%>
 <h2><%= result %></h2>
</body>
</html>