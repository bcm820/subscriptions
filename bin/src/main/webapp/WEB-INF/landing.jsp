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
        <title>Login & Registration</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class=container>
<div class=row>

<div class="col-sm-4 text-center" style="margin: 5% 0">
	<h3>Login</h3>
    
	<form method="POST" action="/">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 		<div class="form-group">
			<input type="text" name="username" class="form-control input-md" placeholder=Email />
		</div>
		<div class="form-group">
			<input type="password" name="password" class="form-control input-md" placeholder=Password />
		</div>
		<p>
			<input type="submit" value="Login" class="btn btn-success btn-md"/>
		</p>
	</form>

	<h4><br>
		<c:if test="${error != null}">
			<strong>${error}</strong>
			<br>Please try again.
		</c:if>

		<c:if test="${logout != null}">
			<strong>${logout}</strong>
			<br>See you next time!
		</c:if>
		
		<c:if test="${thanks != null}">
			<strong>${thanks}</strong>
			<br>You may now login.
		</c:if>
	</h4>
</div>

<div class="col-sm-4 text-center" style="margin: 5% 0">
	<h3>Register</h3>
    
	<form:form method="POST" action="/register" modelAttribute="user">
		<div class=form-group>
			<form:input path="username" cssClass="form-control input-md" placeholder="Email"/>
		</div>
		<div class=form-group>
			<form:input path="first" cssClass="form-control input-md" placeholder="First Name"/>
		</div>
		<div class=form-group>
			<form:input path="last" cssClass="form-control input-md" placeholder="Last Name"/>
		</div>
		<div class=form-group>
			<form:password path="password" cssClass="form-control input-md" placeholder="Password"/>
		</div>
		<div class=form-group>
			<form:password path="passwordConfirmation" cssClass="form-control input-md" placeholder="Confirmation"/>
		</div>
		<p><input type="submit" value="Register" class="btn btn-primary btn-md"/></p>
</div>

<div class="col-sm-4" style="margin: 10% 0">
	<h4>
		<c:if test="${errors != null}">
		<p><strong>Cannot create account!</strong>
		<p><form:errors path="username"/></p>
		<p><form:errors path="first"/></p>
		<p><form:errors path="last"/></p>
		<p><form:errors path="password"/></p>
		<p><form:errors path="passwordConfirmation"/></p>
		</c:if>
	</h4>
	</form:form>
</div>
	
</div>
</div>
</body>
</html>