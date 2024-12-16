<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<Rol> roles = (List<Rol>) request.getAttribute("roles");
    String userlogin = (String) request.getAttribute("userlogin");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario Roles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center text-primary mb-4">Formulario de Roles</h1>
    <div class="card shadow p-4">
        <form action="<%=request.getContextPath()%>/usuariorol_form" method="post">
            <!-- Login -->
            <div class="mb-3">
                <label for="userlogin" class="form-label">Login</label>
                <input type="text" class="form-control" id="userlogin" name="userlogin"
                       value="<%=userlogin%>" readonly placeholder="Login" required>
            </div>
            <!-- Rol -->
            <div class="mb-3">
                <label for="rol" class="form-label">Rol</label>
                <select class="form-select" id="rol" name="rol" required>
                    <option value="">--- Seleccione un Rol ---</option>
                    <% for (Rol rol : roles) { %>
                    <option value="<%=rol.getId_rol()%>"><%=rol.getNombre()%></option>
                    <% } %>
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
