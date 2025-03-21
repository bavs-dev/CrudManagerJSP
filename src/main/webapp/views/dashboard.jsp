<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- CSS de Bootstrap (en la sección <head>) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

<!-- JS de Bootstrap (antes de cerrar la etiqueta </body>) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container-fluid">
        <!-- Header -->
        <%@ include file="header.jsp" %>

        <!-- Sidebar -->
        <div class="row">
            <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
                <div class="position-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="home.jsp">
                                🏠 Inicio
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="users.jsp">
                                👥 Usuarios
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="app/Roles.jsp">
                                🔒 Roles
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- Contenido principal -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <h1 class="mt-4">Bienvenido al Dashboard</h1>
                <p>Este es el panel principal donde puedes gestionar el sistema.</p>
            </main>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </div>

    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
