<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<UsuarioRol> usuariorol = (List<UsuarioRol>) request.getAttribute("usuariorol");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    String userlogin = (String) request.getAttribute("userlogin");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administración de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center text-primary mb-4">Roles de Usuarios</h1>

    <% if (username.isPresent()) { %>
    <div class="alert alert-success text-center">
        Hola, <strong><%= username.get() %></strong>, bienvenido a la aplicación!
    </div>
    <div class="text-end mb-3">
        <a href="${pageContext.request.contextPath}/usuariorol_form?userlogin=<%=userlogin%>" class="btn btn-success">Ingresar nuevo rol</a>
    </div>
    <% } %>

    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Código Rol</th>
                <th>Rol</th>
                <% if (username.isPresent()) { %>
                <th>Opciones</th>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <% for (UsuarioRol ur : usuariorol) { %>
            <tr>
                <td><%= ur.getId_usuariorol() %></td>
                <td><%= ur.getUsuario().getLogin() %></td>
                <td><%= ur.getRol().getId_rol() %></td>
                <td><%= ur.getRol().getNombre() %></td>
                <% if (username.isPresent()) { %>
                <td>
                    <a href="<%=request.getContextPath()%>/usuariorol_form?idUsuarioRol=<%=ur.getId_usuariorol()%>" class="btn btn-sm btn-danger">Eliminar</a>
                </td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <div class="text-center mt-4">
        <a href="<%=request.getContextPath() %>/usuarios" class="btn btn-primary">Volver a Administración de Usuarios</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
