<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.example.model.Rol" %>

<%@ page import="com.example.conexion.ConexionDB"%>
<!DOCTYPE html>
<html>
<head>
<title>Gestión de Roles</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<h1 class="text-center">Gestión de Roles</h1>

		<!-- Formulario de Inserción -->
		<div class="card shadow-sm p-4 mb-4">
			<form action="<%=request.getContextPath()%>/RolesServlet"
				method="post">
				<input type="hidden" name="accion"
					value="<%=request.getAttribute("rol") == null ? "insertar" : "actualizar"%>">
				<input type="hidden" name="id"
					value="<%=request.getAttribute("rol") != null ? ((Rol) request.getAttribute("rol")).getId() : ""%>">

				<div class="mb-3">
					<label for="nombre" class="form-label">Nombre del Rol</label> 
					<input
						type="text" class="form-control" id="nombre" name="nombre"
						value="<%=request.getAttribute("rol") != null ? ((Rol) request.getAttribute("rol")).getNombre() : ""%>"
						required>
				</div>

				<button type="submit" class="btn btn-primary">
					<%=request.getAttribute("rol") == null ? "Insertar Rol" : "Actualizar Rol"%>
				</button>
			</form>
		</div>

		<!-- Tabla de Visualización -->
		<div class="card shadow-sm p-4">
			<h2 class="text-center">Lista de Roles</h2>
			<div class="table-responsive">
				<table class="table table-striped table-bordered mt-3"
					id="rolesTable">
					<thead class="table-dark">
						<tr>
							<th>ID</th>
							<th>Nombre</th>
							<th>Verificador</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
						try {
							Connection con = ConexionDB.getConexionDB();
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery("SELECT * FROM roles");

							while (rs.next()) {
						%>
						<tr>
							<td><%=rs.getInt("id")%></td>
							<td><%=rs.getString("nombre")%></td>
							<td><%=rs.getString("verificador")%></td>

							<td><a
								href="<%=request.getContextPath()%>/RolesServlet?accion=editar&id=<%=rs.getInt("id")%>"
								class="btn btn-warning btn-sm">Editar</a> <a href="#"
								onclick="confirmarEliminacion(<%=rs.getInt("id")%>)"
								class="btn btn-danger btn-sm">Eliminar</a></td>
						</tr>
						<%
						}
						con.close();
						} catch (Exception e) {
						e.printStackTrace();
						}
						%>
					</tbody>
				</table>
			</div>

			<!-- Paginador -->
			<nav>
				<ul class="pagination justify-content-center mt-3" id="pagination">
				</ul>
			</nav>
		</div>
	</div>

	<script>
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							const table = document.getElementById("rolesTable")
									.getElementsByTagName("tbody")[0];
							const rows = table.getElementsByTagName("tr");
							const rowsPerPage = 5;
							let currentPage = 1;

							function showPage(page) {
								for (let i = 0; i < rows.length; i++) {
									rows[i].style.display = (i >= (page - 1)
											* rowsPerPage && i < page
											* rowsPerPage) ? "" : "none";
								}
							}

							function createPagination() {
								const totalPages = Math.ceil(rows.length
										/ rowsPerPage);
								const pagination = document
										.getElementById("pagination");
								pagination.innerHTML = "";

								for (let i = 1; i <= totalPages; i++) {
									const li = document.createElement("li");
									li.className = `page-item ${i == currentPage ? "active" : ""}`;
									const link = document.createElement("a");
									link.className = "page-link";
									link.href = "#";
									link.textContent = i;

									link.addEventListener("click", function(e) {
										e.preventDefault();
										currentPage = i;
										showPage(currentPage);
										createPagination();
									});

									li.appendChild(link);
									pagination.appendChild(li);
								}
							}

							showPage(currentPage);
							createPagination();
						});
	</script>
	
<script>
    function confirmarEliminacion(id) {
        if (confirm("¿Estás seguro de que deseas eliminar este rol?")) {
            window.location.href = "<%=request.getContextPath()%>/RolesServlet?accion=eliminar&id=" + id;
        }
    }
</script>

</body>
</html>
