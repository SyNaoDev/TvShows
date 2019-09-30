<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Shows</title>
</head>
<body>
	<h1>Hello, <c:out value="${user.firstName}"/>!</h1>
	<a href="/logout">Log Out</a>
	<h2>TV Shows</h2>
	<table>
		<thead>
			<tr>
				<th>Show</th><th>Network</th><th>Avg Rating</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="show" items="${shows}">
				<tr>
					<td>
						<a href="/shows/${show.id}"><c:out value="${show.title}"/></a>
					</td>
					<td><c:out value="${show.network}"/></td>
					<td><c:out value="${show.averageRating}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/shows/new">Add a Show!</a>
</body>
</html>