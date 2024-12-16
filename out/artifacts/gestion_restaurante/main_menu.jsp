<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
    List<MenuWeb> menus = (List<MenuWeb>) request.getAttribute("opciones_menu");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center text-primary mb-4">Gestión Restaurante</h1>
    <% if (username.isPresent()) { %>
    <div class="alert alert-success text-center">
        Bienvenido a la aplicación <strong><%= username.get() %></strong>
    </div>
    <% } %>
    <h3 class="mb-3">Opciones Principales</h3>
    <div class="list-group">
        <% for (MenuWeb m : menus) { %>
        <a href="<%=request.getContextPath() %>/<%=m.getUrl_funcionalidad()%>" class="list-group-item list-group-item-action">
            <%=m.getNombre_funcionalidad()%>
        </a>
        <% } %>
    </div>
    <div class="text-center mt-4">
        <a href="logout" class="btn btn-danger">Logout</a>
    </div>
</div>
<br>
<!-- Footer -->
<footer class="bg-dark text-white text-center py-3">
    <p class="mb-0">© 2024 Restaurante. Todos los derechos reservados.</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
