package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Mesas;
import services.MesaService;
import services.MesaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/mesa/form","/mesa/eliminar"})
public class MesaFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        MesaService service = new MesaServiceJdbcImplement(conn);
        Long id;
        try {
            id = Long.parseLong(req.getParameter("idMesa"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Mesas mesas = new Mesas();
        if (id > 0) {
            Optional<Mesas> o = service.agregarPorId(id);
            if (o.isPresent()) {
                mesas = o.get();
            }
        }
        req.setAttribute("mesas", mesas);
        getServletContext().getRequestDispatcher("/formularioMesa.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        MesaService service = new MesaServiceJdbcImplement(conn);

        //Metodo para eliminar
        // Verificamos si se debe eliminar el plato
        String method = req.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            // Obtener el idPlato desde la solicitud
            Long idMesa = Long.valueOf(req.getParameter("idMesa"));
            if (idMesa > 0) {
                // Eliminar el plato utilizando el servicio
                service.eliminar(idMesa);
            }
            // Redirigir a la lista de platos después de la eliminación
            resp.sendRedirect(req.getContextPath() + "/mesa");
            return;
        }


        String codigoMesa = req.getParameter("codigoMesa");

        int capacidadMesa;
        try{
            capacidadMesa=Integer.parseInt(req.getParameter("capacidadMesa"));
        }catch (NumberFormatException e){
            capacidadMesa = 0;
        }
        int estadoMesa = 0;  // Valor predeterminado para Desactivar
        try {
            estadoMesa = Integer.parseInt(req.getParameter("estadoMesa"));
        } catch (NumberFormatException e) {
            estadoMesa = 0;  // En caso de error, mantén el valor predeterminado
        }
        Long idMesa;
        try {
            idMesa = Long.valueOf(req.getParameter("idMesa"));
        } catch (NumberFormatException e) {
            idMesa = 0L;
        }
        Mesas mesas = new Mesas();
        mesas.setIdmesa(idMesa);
        mesas.setCapacidad(capacidadMesa);
        mesas.setEstado(estadoMesa);
        mesas.setCodigoMesa(codigoMesa);

        service.guardar(mesas);

        // Redireccionar a un listado para que no ejecute el método doPost nuevamente
        resp.sendRedirect(req.getContextPath() + "/mesa");
    }
}
