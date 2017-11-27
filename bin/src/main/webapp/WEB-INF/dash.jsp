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
	
	<h2>Welcome to Dojoscriptions, ${user.first}!</h2>
	<h4>Please choose a subscription and start day</h4>

	<div class="col-sm-4">

		<form method="POST" action="/selection/subscribe">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class=form-group>Due Day<br>
				<select name="day" class="form-control input-sm">
					<c:forEach var="i" begin="1" end="${last}">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
			</div>
			<div class=form-group>Package<br>
				<select name="sub" class="form-control input-sm">
					<c:forEach items="${subs}" var="s">
						<option value="${s.id}">${s.type} ($${s.cost})</option>
					</c:forEach>
				</select>
			</div>
			<p><input type="submit" value="Sign Up!" class="btn btn-primary btn-md"/></p>
		</form>

	</div>

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