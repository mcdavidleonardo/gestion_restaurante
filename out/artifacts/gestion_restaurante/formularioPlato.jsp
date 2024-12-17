<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
    Platos platos = (Platos) request.getAttribute("platos");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingreso de Platos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card shadow">
        <div class="card-header text-center bg-primary text-white">
            <h2><i class="bi bi-clipboard"></i> Ingreso de Platos</h2>
        </div>
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/plato/form" method="post" class="needs-validation" >
                <!-- Nombre del Plato -->
                <div class="mb-3">
                    <label for="nombrePlato" class="form-label">
                        <i class="bi bi-pencil-square"></i> Nombre del Plato
                    </label>
                    <input type="hidden" name="idPlato" value="<%= platos.getIdplato() %>">
                    <input type="text" id="nombrePlato" name="nombrePlato" class="form-control"
                           placeholder="Ejemplo: Ensalada César"
                           value="<%= platos.getNombre() != null ? platos.getNombre() : "" %>" required>
                    <div class="invalid-feedback">
                        Por favor, ingrese el nombre del plato.
                    </div>
                </div>

                <!-- Descripción -->
                <div class="mb-3">
                    <label for="descripcionPlato" class="form-label">
                        <i class="bi bi-card-text"></i> Descripción
                    </label>
                    <input type="text" id="descripcionPlato" name="descripcionPlato" class="form-control"
                           placeholder="Descripción breve del plato"
                           value="<%= platos.getDescripcion() != null ? platos.getDescripcion() : "" %>" required>
                    <div class="invalid-feedback">
                        Por favor, ingrese una descripción.
                    </div>
                </div>

                <!-- Categoría -->
                <div class="mb-3">
                    <label for="categoriaPlato" class="form-label">
                        <i class="bi bi-tags"></i> Categoría
                    </label>
                    <input type="text" id="categoriaPlato" name="categoriaPlato" class="form-control"
                           placeholder="Ejemplo: Entrada"
                           value="<%= platos.getCategoria() != null ? platos.getCategoria() : "" %>" required>
                    <div class="invalid-feedback">
                        Por favor, ingrese la categoría del plato.
                    </div>
                </div>

                <!-- Precio -->
                <div class="mb-3">
                    <label for="precioPlato" class="form-label">
                        <i class="bi bi-currency-dollar"></i> Precio
                    </label>
                    <input type="text" id="precioPlato" name="precioPlato" class="form-control"
                           placeholder="Ejemplo: 12.50"
                           value="<%= platos.getPrecio() != 0 ? platos.getPrecio() : "" %>" required>
                    <div class="invalid-feedback">
                        Por favor, ingrese el precio del plato.
                    </div>
                </div>

                <!-- Stock -->
                <div class="mb-3">
                    <label for="stockPlato" class="form-label">
                        <i class="bi bi-box"></i> Stock
                    </label>
                    <input type="text" id="stockPlato" name="stockPlato" class="form-control"
                           placeholder="Ejemplo: 20"
                           value="<%= platos.getStock() != 0 ? platos.getStock() : "" %>" required>
                    <div class="invalid-feedback">
                        Por favor, ingrese el stock disponible.
                    </div>
                </div>

                <!-- Estado -->
                <div class="mb-3">
                    <label for="estadoPlato" class="form-label">
                        <i class="bi bi-toggle-on"></i> Estado
                    </label>
                    <select name="estadoPlato" id="estadoPlato" class="form-select">
                        <option value="0" <%= platos.getEstado() == 0 ? "selected" : "" %>>Desactivar</option>
                        <option value="1" <%= platos.getEstado() == 1 ? "selected" : "" %>>Activar</option>
                    </select>
                </div>

                <!-- Botón -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-send"></i> Enviar
                    </button>
                    <div class="text-center mt-4">
                        <a href="<%=request.getContextPath()%>/platos" class="btn btn-primary">Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
