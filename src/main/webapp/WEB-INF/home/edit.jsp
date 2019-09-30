<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Edit Show</title>
</head>
<body>
	<h1>Edit show!</h1>
	<form action="/shows/${show.id}/update" method="POST">
		<label for="title">Title: </label>
		<input type="text" name="title" value="${show.title}" id="title"><br>
		<label for="network">Network: </label> 
		<input type="text" name="network" value="${show.network}" id="network"><br>
		<input type="submit" value="Update"/>
	</form>
	<br>
	<form action="/shows/${show.id}/delete" method="POST">
		<input type="submit" value="Delete"/>
	</form>
</body>
</html>