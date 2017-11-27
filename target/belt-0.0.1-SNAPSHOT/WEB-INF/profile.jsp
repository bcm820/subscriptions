<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class=container>
<div class=row>

<div class="col-sm-6" style="margin: 3% 0">
	
	<h2>Welcome, ${user.first}!</h2>
	<p>Current Package: ${user.subscription.type}</p>
	<p>Next Due Date: <fmt:formatDate type="date" dateStyle="long" value="${user.dueDate}" /></p>
	<p>Amount Due: <fmt:formatNumber value="${user.amtDue}" type="currency"/></p>
	<p>User Since: <fmt:formatDate type="date" dateStyle="long" value="${user.createdAt}" /></p>

</div>

<div class="col-sm-2" style="margin: 3% 0">

	<form method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Logout" />
	</form><br>

</div>

	
</div>
</div>
</body>
</html>