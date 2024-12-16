package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Platos;
import services.PlatosService;
import services.PlatosServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/plato/form","/plato/eliminar"})
public class PlatosFormControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        PlatosService service = new PlatosServiceJdbcImplement(conn);
        Long id;
        try {
            id = Long.parseLong(req.getParameter("idPlato"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Platos platos = new Platos();
        if (id > 0) {
            Optional<Platos> o = service.agregarPorId(id);
            if (o.isPresent()) {
                platos = o.get();
            }
        }
        req.setAttribute("platos", platos);
        getServletContext().getRequestDispatcher("/formularioPlato.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        PlatosService service = new PlatosServiceJdbcImplement(conn);

        // Verificamos si se debe eliminar el plato
        String method = req.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            // Obtener el idPlato desde la solicitud
            Long idPlato = Long.valueOf(req.getParameter("idPlato"));
            if (idPlato > 0) {
                // Eliminar el plato utilizando el servicio
                service.eliminar(idPlato);
            }
            // Redirigir a la lista de platos después de la eliminación
            resp.sendRedirect(req.getContextPath() + "/platos");
            return;
        }
        // Si no es eliminación, procesamos el formulario para agregar/actualizar plato
        String nombrePlato = req.getParameter("nombrePlato");
        String descripcionPlato = req.getParameter("descripcionPlato");
        String categoriaPlato = req.getParameter("categoriaPlato");
        Double precioPlato;
        try {
            precioPlato = Double.parseDouble(req.getParameter("precioPlato"));
        } catch (NumberFormatException e) {
            precioPlato = 0.0;
        }
        int stockPlato;
        try {
            stockPlato = Integer.parseInt(req.getParameter("stockPlato"));
        } catch (NumberFormatException e) {
            stockPlato = 0;
        }
        int estadoPlato = 0;  // Valor predeterminado para Desactivar
        try {
            estadoPlato = Integer.parseInt(req.getParameter("estadoPlato"));
        } catch (NumberFormatException e) {
            estadoPlato = 0;  // En caso de error, mantén el valor predeterminado
        }
        Long idPlato;
        try {
            idPlato = Long.valueOf(req.getParameter("idPlato"));
        } catch (NumberFormatException e) {
            idPlato = 0L;
        }
        Platos platos = new Platos();
        platos.setIdplato(idPlato);
        platos.setNombre(nombrePlato);
        platos.setDescripcion(descripcionPlato);
        platos.setCategoria(categoriaPlato);
        platos.setPrecio(precioPlato);
        platos.setStock(stockPlato);
        platos.setEstado(estadoPlato);

        service.guardar(platos);

        // Redireccionar a un listado para que no ejecute el método doPost nuevamente
        resp.sendRedirect(req.getContextPath() + "/platos");
    }
}
