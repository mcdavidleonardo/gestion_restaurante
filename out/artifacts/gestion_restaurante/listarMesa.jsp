<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
  List<Mesas> mesas = (List<Mesas>) request.getAttribute("mesas");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Lista de Mesas</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container my-5">
  <h1 class="text-center mb-4"><i class="bi bi-table"></i> Lista de Mesas</h1>
  <div class="d-flex justify-content-end mb-3">
    <a href="${pageContext.request.contextPath}/mesa/form" class="btn btn-primary">
      <i class="bi bi-plus-circle"></i> Ingresar Mesa
    </a>
  </div>
  <div class="table-responsive">
    <table class="table table-striped table-hover table-bordered align-middle">
      <thead class="table-dark text-center">
      <tr>
        <th>ID Mesa</th>
        <th>N° Mesas</th>
        <th>Capacidad</th>
        <th>Estado</th>
        <th>Opciones</th>
      </tr>
      </thead>
      <tbody>
      <% for (Mesas m : mesas) { %>
      <tr>
        <td class="text-center"><%= m.getIdmesa() %></td>
        <td class="text-center"><%= m.getCodigoMesa() %></td>
        <td class="text-center"><%= m.getCapacidad() %></td>
        <td class="text-center">
          <% if(m.getEstado() == 1) { %>
          <span class="badge bg-success"><i class="bi bi-check-circle"></i> Disponible</span>
          <% } else { %>
          <span class="badge bg-danger"><i class="bi bi-x-circle"></i> Ocupado</span>
          <% } %>
        </td>
        <td class="text-center">
          <a href="<%= request.getContextPath() %>/mesa/form?idMesa=<%= m.getIdmesa() %>"
             class="btn btn-warning btn-sm me-1">
            <i class="bi bi-pencil-square"></i> Editar
          </a>
          <form action="<%= request.getContextPath() %>/mesa/eliminar" method="POST" style="display:inline;">
            <input type="hidden" name="idMesa" value="<%= m.getIdmesa() %>">
            <input type="hidden" name="_method" value="DELETE">
            <button type="submit" class="btn btn-danger btn-sm"
                    onclick="return confirm('¿Estás seguro de que deseas eliminar esta mesa?');">
              <i class="bi bi-trash3"></i> Eliminar
            </button>
          </form>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
