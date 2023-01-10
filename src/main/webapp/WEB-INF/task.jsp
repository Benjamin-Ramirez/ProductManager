<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tasks</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1>Project: ${proyecto.title }</h1>
			<h2>Project Lead: ${proyecto.lead.first_name }</h2>
			<a href="/dashboard" class="btn btn-secondary">Dashboard</a>
		</div>
		
		<div class="d-flex justify-content-between align-items-center">
			<div>
				<form:form action="/create/task" method="post" modelAttribute="task">
					<div class="form-group">
							<form:label path="content">add a tasks ticket for this team:</form:label>
							<form:input path="content" class="form-control"/>
							<form:errors path="content" class="text-danger"/>
							<form:hidden path="author" value="${user_session.id }"/>
							<form:hidden path="proyecto" value="${proyecto.id }"/>
							<input type="submit" value="Enviar" class="btn btn-primary"/>
					</div>
				</form:form>			
			</div>
		</div>
		<div class="d-flex justify-content-between align-items-center">
		
		<ul class="list-group list-group-flush">
			<c:forEach items="${proyecto.tareas }" var="task">
				<li class="list-group-item"><b>Add by ${task.author.first_name} at ${task.created_at }</b></li>
				<li class="list-group-item"> ${task.content} </li>
			</c:forEach>	
		</ul>
		</div>
		
		
	</div>
</body>
</html>