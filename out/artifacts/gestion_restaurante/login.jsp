<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<Rol> roles = (List<Rol>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión Restaurante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center">Inicia Sesión</h1>
    <form action="<%=request.getContextPath()%>/LoginServlet" method="post" class="mt-4 p-4 bg-white shadow rounded">
        <div class="mb-3">
            <label for="username" class="form-label">Login:</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="rol" class="form-label">Rol:</label>
            <select id="rol" name="rol" class="form-select" required>
                <option value="">--- Seleccione un Rol ---</option>
                <% for (Rol rol : roles) { %>
                <option value="<%=rol.getId_rol()%>"><%=rol.getNombre()%></option>
                <% } %>
            </select>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary w-100">Login</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
