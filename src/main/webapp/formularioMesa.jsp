<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
    Mesas mesas = (Mesas) request.getAttribute("mesas");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Mesas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <h1 class="text-center mb-4"><i class="fas fa-chair"></i> Formulario de Mesas</h1>

    <form action="<%= request.getContextPath() %>/mesa/form" method="post" class="p-4 bg-white rounded shadow">

    <!-- Código de la Mesa -->
        <div class="mb-3">
            <label for="codigoMesa" class="form-label">
                <i class="fas fa-key"></i> Ingrese el código de la Mesa
            </label>
            <input type="hidden" name="idMesa" value="<%= mesas.getIdmesa() %>">
            <input type="text" id="codigoMesa" name="codigoMesa" class="form-control"
                   value="<%= mesas.getCodigoMesa() != null ? mesas.getCodigoMesa() : "" %>"
                   placeholder="Ejemplo: MESA001" required>
        </div>

        <!-- Capacidad de la Mesa -->
        <div class="mb-3">
            <label for="capacidadMesa" class="form-label">
                <i class="fas fa-users"></i> Ingrese la capacidad de la Mesa
            </label>
            <input type="number" id="capacidadMesa" name="capacidadMesa" class="form-control"
                   value="<%= mesas.getCapacidad() != 0 ? mesas.getCapacidad() : "" %>"
                   placeholder="Ejemplo: 4" required>
        </div>

        <!-- Estado de la Mesa -->
        <div class="mb-3">
            <label for="estadoMesa" class="form-label">
                <i class="fas fa-toggle-on"></i> Ingrese el estado
            </label>
            <select name="estadoMesa" id="estadoMesa" class="form-select" required>
                <option value="" disabled selected>Seleccione un estado</option>
                <option value="0" <%= mesas.getEstado() == 0 ? "selected" : "" %>>Desactivar</option>
                <option value="1" <%= mesas.getEstado() == 1 ? "selected" : "" %>>Activar</option>
            </select>
        </div>

        <!-- Botón -->
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-paper-plane"></i> Enviar
            </button>
        </div>
        <div class="text-center mt-4">
            <a href="<%=request.getContextPath()%>/mesa" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

