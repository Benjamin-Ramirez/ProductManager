<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalles</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	
		<div class="d-flex justify-content-between align-items-center">
			<h1>Details Project</h1>
			<a href="/dashboard" class="btn btn-secondary">Dashboard</a>
		</div>
		
		
		<ul class="list-group list-group-flush">
			<li class="list-group-item">Project:  ${proyecto.title}</li>
			<li class="list-group-item">Description: ${proyecto.description}</li>
			<li class="list-group-item">Due Date: ${proyecto.due_date}</li>
		</ul>
		
		<div class="row">
		
		<c:if test="${proyecto.lead.id == user.id }">
			<form action="/projects/delete/${proyecto.id}" method="post">
				<input type="hidden" name="_method" value="DELETE">
				<input type="submit" value="Eliminar" class="btn btn-danger">
			</form>
		</c:if>
			<a href="/projects/${proyecto.id}/tasks">See Tasks</a>
		</div>		

	</div>
</body>
</html>