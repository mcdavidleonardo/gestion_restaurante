<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
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
    <h1 class="text-center text-primary mb-4">Listado de Usuarios</h1>

    <% if (username.isPresent()) { %>
    <div class="alert alert-success text-center">
        Hola, <strong><%= username.get() %></strong>, bienvenido a la aplicación!
    </div>
    <div class="text-end mb-3">
        <a href="${pageContext.request.contextPath}/usuario_form" class="btn btn-success">Ingresar nuevo usuario</a>
    </div>
    <% } %>

    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>ID Usuario</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Login</th>
                <th>Estado</th>
                <% if (username.isPresent()) { %>
                <th>Opciones</th>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <% for (Usuario u : usuarios) { %>
            <tr>
                <td><%= u.getIdusuario() %></td>
                <td><%= u.getNombre() %></td>
                <td><%= u.getApellido() %></td>
                <td><%= u.getLogin() %></td>
                <td><%= u.getEstado() %></td>
                <% if (username.isPresent()) { %>
                <td>
                    <a href="<%=request.getContextPath()%>/usuario_form?idUsuario=<%=u.getIdusuario()%>" class="btn btn-sm btn-primary">Editar</a>
                    <a href="<%=request.getContextPath()%>/usuariorol?userlogin=<%=u.getLogin()%>" class="btn btn-sm btn-secondary">Roles</a>
                </td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <div class="text-center mt-4">
        <a href="<%=request.getContextPath()%>/LoginServlet" class="btn btn-primary">Menú Principal</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
