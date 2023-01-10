<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Proyecto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		
		<h1>Nuevo Proyecto</h1>
		
		<form:form action="/projects/create"  method="post" modelAttribute="project">
			<div class="form-group">
				<form:label path="title"> Titulo </form:label>
				<form:input path="title" class="form-control"/>
				<form:errors path="title" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="description"> Descripcion </form:label>
				<form:textarea path="description" class="form-control"/>
				<form:errors path="description" class="text-danger"/>
			</div>
			<div class="form-group">
				<form:label path="due_date"> Fecha Limite: </form:label>
				<form:input type="date" path="due_date" class="form-control"/>
				<form:errors path="due_date" class="text-danger"/>
			</div>
			<form:hidden path="lead" value="${user_session.id }"/>
			
			<a href="/dashboard" class="btn btn-danger">Cancelar</a>
			
			<input type="submit" value="guardar" class="btn btn-success">
		</form:form>
		
	</div>
</body>
</html>