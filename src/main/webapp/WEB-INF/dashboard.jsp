<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>


	<div class="container">
		<nav class="d-flex justify-content-between align-items-center">
			<h1>Bienvenid@ ${user_session.first_name}</h1>
			
			<a href="/projects/new" class="btn btn-success"> + Nuevo Proyecto </a>
			<a href="/logout" class="btn btn-danger">Cerrar Sesión</a>			
		</nav>
		
		<div class="row">
		
				<h2>All Projects:</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Project</th>
							<th>Team Lead</th>
							<th>Due Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${proyectos}" var="proyecto">
							<tr>
								<td> <a href="/projects/${proyecto.id}" class="btn btn-link">${proyecto.title }</a> </td>
								<td> ${proyecto.lead.first_name } </td>
								<td> ${proyecto.due_date }
								<td> 

									<c:if test="${proyecto.lead.id == user.id }">
										<a href="/projects/edit/${proyecto.id }" class="btn btn-warning"> Editar</a>
									</c:if>
									<c:if test="${proyecto.lead.id != user.id }">
										<c:choose>
											<c:when test="${proyecto.colaboradores.contains(user) }">
												<span>Se ha unido - <a href="/projects/remove/${proyecto.id }" class="btn btn-danger">Salirme</a></span>
											</c:when>
											<c:otherwise>
												<a href="/projects/join/${proyecto.id }" class="btn btn-success">Unirme</a>
											</c:otherwise>
										</c:choose>
									</c:if>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		<div class="row">
		
				<h2>Your Projects:</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Project</th>
							<th>Team Lead</th>
							<th>Due Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${colaboraciones}" var="proyecto">
							<tr>
								<td><a href="/projects/${proyecto.id}" class="btn btn-link">${proyecto.title }</a></td>
								<td> ${proyecto.lead.first_name } </td>
								<td> ${proyecto.due_date }
								<td>
	
									<c:if test="${proyecto.lead.id == user.id }">
										<a href="/projects/edit/${proyecto.id }" class="btn btn-warning"> Editar</a>
									</c:if>
									<c:if test="${proyecto.lead.id != user.id }">
										<c:choose>
											<c:when test="${proyecto.colaboradores.contains(user) }">
												<span>Se ha Unido - <a href="/projects/remove/${proyecto.id }" class="btn btn-danger">Salirme</a></span>
											</c:when>
											<c:otherwise>
												<a href="/projects/join/${proyecto.id }" class="btn btn-success">Unirme</a>
											</c:otherwise>
										</c:choose>
									</c:if>
	
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	
	
	
</body>
</html>
