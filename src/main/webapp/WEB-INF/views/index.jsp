<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to our API</title>
</head>
<body>
<p><a href="/movies"> All Movies</a></p>
<p><a href="/movies/random"> Pick a Random Movie</a></p>
<p><a href="/movies/random-list?quantity=4"> Pick a Random List of Movies</a></p>
<p><a href="/movies/category"> Display Movie Category</a></p>
<p><a href="/movies/1"> Display Movie Info</a></p>
<p><a href="/movies"> Movies with a shared keyword</a></p>
</body>
</html>