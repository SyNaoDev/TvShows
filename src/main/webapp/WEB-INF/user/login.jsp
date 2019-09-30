<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Login</title>
</head>
<body>
	<h1>Login Form</h1>
	<form action="/login" method="POST">
		<label for="email">Email: </label>
		<input type="text" id="email" name="email"/><br>
		<label for="password">Password: </label>
		<input type="password" id="password" name="password"/><br>
		<input type="submit" value="Login"/>
	</form><br>
	<a href="/register">Register</a>
</body>
</html>