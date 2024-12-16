<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center text-primary mb-4">Formulario de Usuarios</h1>
    <div class="card shadow p-4">
        <form action="<%=request.getContextPath()%>/usuario_form" method="post">
            <!-- Nombre -->
            <input type="hidden" name="idUsuario" value="<%=usuario.getIdusuario()%>">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre del usuario</label>
                <input type="text" class="form-control" id="nombre" name="nombre"
                       value="<%=usuario.getNombre() != null ? usuario.getNombre() : "" %>"
                       placeholder="Nombre del Usuario" required>
            </div>
            <!-- Apellido -->
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellidos del usuario</label>
                <input type="text" class="form-control" id="apellido" name="apellido"
                       value="<%=usuario.getApellido() != null ? usuario.getApellido() : "" %>"
                       placeholder="Apellidos del Usuario" required>
            </div>
            <!-- Login -->
            <div class="mb-3">
                <label for="userlogin" class="form-label">Nombre de login</label>
                <input type="text" class="form-control" id="userlogin" name="userlogin"
                       value="<%=usuario.getLogin() != null ? usuario.getLogin() : "" %>"
                    <%=usuario.getLogin() != null ? "readonly" : "" %>
                       placeholder="Login del Usuario" required>
            </div>
            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="Password" required>
            </div>
            <!-- Estado -->
            <div class="mb-3">
                <label for="estado" class="form-label">Estado del usuario</label>
                <select class="form-select" id="estado" name="estado" required>
                    <option value="" selected>--- Seleccione un estado ---</option>
                    <option value="V" <%="V".equals(usuario.getEstado()) ? "selected" : "" %>>Vigente</option>
                    <option value="C" <%="C".equals(usuario.getEstado()) ? "selected" : "" %>>Cancelado</option>
                </select>
            </div>
            <!-- Botón Enviar -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Enviar</button>
            </div>
        </form>
    </div>
    <div class="text-center mt-4">
        <a href="<%=request.getContextPath() %>/usuarios" class="btn btn-secondary">Volver a Administración de Usuarios</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
