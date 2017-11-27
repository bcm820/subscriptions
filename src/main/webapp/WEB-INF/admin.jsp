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
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class=container>
<div class=row>

<div class="col-sm-6" style="margin: 3% 0">
	
	<h2>Customers</h2>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<td><strong>Name</strong></td>
				<td><strong>Next Due Date</strong></td>
				<td><strong>Amount Due</strong></td>
				<td><strong>Package Type</strong></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customers}" var="u">
			<tr>
				<td>${u.first} ${u.last}</td>
				<td><fmt:formatDate type="date" dateStyle="long" value="${u.dueDate}" /></td>
				<td><fmt:formatNumber value="${u.amtDue}" type="currency"/></td>
				<td>${u.subscription.type}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<h2>Packages</h2>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<td><strong>Package</strong></td>
				<td><strong>Package Cost</strong></td>
				<td><strong>Available</strong></td>
				<td><strong>Users</strong></td>
				<td><strong>Actions</strong></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${subs}" var="s">
			<tr>
				<td>${s.type}</td>
				<td><fmt:formatNumber value="${s.cost}" type="currency"/></td>
				<td>
					<c:choose>
					<c:when test="${s.active}">Available</c:when>
					<c:otherwise>Unavailable</c:otherwise>
					</c:choose>
				</td>
				<td>${s.users.size()}</td>
				<td>
				<c:choose>
					<c:when test="${s.active}">
						<a href="/admin/sub${s.id}/deactivate">Deactivate</a>
					</c:when>
					<c:when test="${!s.active}">
						<a href="/admin/sub${s.id}/activate">Activate</a>
					</c:when>
				</c:choose>
					<c:if test="${s.users.size() == 0}">
					 <a href="/admin/sub${s.id}/delete">Delete</a>
			   		</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<h4>Create Package</h4>
	<div class="col-sm-6">
	<form:form method="POST" action="/admin/create" modelAttribute="subscription">
		<div class=form-group>Name<br>
			<form:input path="type" cssClass="form-control input-md" placeholder="Name"/>
		</div>
		<div class=form-group>Cost ($)<br>
			<form:input path="cost" cssClass="form-control input-md"/>
		</div>
		<p><input type="submit" value="Create package" class="btn btn-primary btn-md"/></p>
	</form:form>
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