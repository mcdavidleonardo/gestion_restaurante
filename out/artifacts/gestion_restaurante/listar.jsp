<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
    List<Platos> platos = (List<Platos>) request.getAttribute("platos");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Platos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container my-5">
    <h1 class="text-center mb-4"><i class="bi bi-list"></i> Lista de Platos</h1>
    <div class="d-flex justify-content-end mb-3">
        <a href="${pageContext.request.contextPath}/plato/form" class="btn btn-primary">
            <i class="bi bi-plus-circle"></i> Ingresar Plato
        </a>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark text-center">
            <tr>
                <th>ID PLATO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>CATEGORÍA</th>
                <th>PRECIO</th>
                <th>STOCK</th>
                <th>ESTADO</th>
                <th>OPCIONES</th>
            </tr>
            </thead>
            <tbody>
            <% for (Platos p : platos) { %>
            <tr>
                <td class="text-center"><%= p.getIdplato() %></td>
                <td><%= p.getNombre() %></td>
                <td><%= p.getDescripcion() %></td>
                <td><%= p.getCategoria() %></td>
                <td class="text-end">S/. <%= p.getPrecio() %></td>
                <td class="text-center"><%= p.getStock() %></td>
                <td class="text-center">
                    <% if(p.getEstado() == 1) { %>
                    <span class="badge bg-success"><i class="bi bi-check-circle"></i> Activo</span>
                    <% } else if(p.getEstado() == 0) { %>
                    <span class="badge bg-danger"><i class="bi bi-x-circle"></i> Desactivado</span>
                    <% } %>
                </td>
                <td class="text-center">
                    <a href="<%= request.getContextPath() %>/plato/form?idPlato=<%= p.getIdplato() %>" class="btn btn-warning btn-sm me-1">
                        <i class="bi bi-pencil-square"></i> Editar
                    </a>
                    <form action="<%= request.getContextPath() %>/plato/eliminar" method="POST" style="display:inline;">
                        <input type="hidden" name="idPlato" value="<%= p.getIdplato() %>">
                        <input type="hidden" name="_method" value="DELETE">
                        <button type="submit" class="btn btn-danger btn-sm"
                                onclick="return confirm('¿Estás seguro de que deseas eliminar este plato?');">
                            <i class="bi bi-trash3"></i> Eliminar
                        </button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <div class="text-center mt-4">
            <a href="<%=request.getContextPath()%>/LoginServlet" class="btn btn-primary">Menú Principal</a>
        </div>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
