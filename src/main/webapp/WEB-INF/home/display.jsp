<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Show</title>
</head>
<body>
	<h1><c:out value="${show.title}"/></h1>
	<h3><c:out value="${show.network}"/></h3><hr>
	<h1>Users who rated this show</h1>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Rating</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="rating" items="${show.ratings}">
				<tr>
					<td><c:out value="${rating.user.firstName}"/> <c:out value="${rating.user.lastName}"/></td>
					<td><c:out value="${rating.value}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table><br>
	<a href="/shows/${show.id}/edit">Edit!</a><br>
	<h3>Leave a rating!</h3>
	<form action="/shows/${show.id}/rate" method="POST">
		<input type="number" name="value" value="1.0">
		<input type="submit" value="Rate!">
	</form>
</body>
</html>