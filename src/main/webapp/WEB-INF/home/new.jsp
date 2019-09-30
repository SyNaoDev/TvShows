<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Add a Show!</title>
</head>
<body>
	<h1>Create a new show!</h1>
	<p><form:errors path="show.*"/></p>
	<form:form action="/shows/new" method="POST" modelAttribute="show">
		<p>
			<form:label path="title">Title:</form:label>
			<form:input path="title"/>
		</p>
		<p>
			<form:label path="network">Network:</form:label>
			<form:input path="network"/>
		</p>
		<input type="submit" value="Create!"/>
	</form:form>
</body>
</html>