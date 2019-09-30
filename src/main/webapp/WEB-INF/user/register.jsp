<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Register</title>
</head>
<body>
	<h1>Register Form</h1>
	<p><form:errors path="user.*"/></p>
	<form:form action="/register" method="POST" modelAttribute="user">
		<p>
			<form:label path="firstName">First Name:</form:label>
			<form:input path="firstName"/>
		</p>
		<p>
			<form:label path="lastName">Last Name:</form:label>
			<form:input path="lastName"/>
		</p>
		<p>
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
		</p>
		<p>
			<form:label path="password">Password:</form:label>
			<form:password path="password"/>
		</p>
		<p>
			<form:label path="passwordConfirm">Password Confirmation:</form:label>
			<form:password path="passwordConfirm"/>
		</p>
		<input type="submit" value="Register!"/>
	</form:form>
</body>
</html>