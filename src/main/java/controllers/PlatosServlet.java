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
import java.util.List;

@WebServlet("/platos")
public class PlatosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //llamamos alaconeccion bbdd
        Connection conn = (Connection) req.getAttribute("conn");
        //creamos el obejeto
        PlatosService service = new PlatosServiceJdbcImplement(conn);
        List<Platos> platos = service.listar();
        //seteamos los atributos de platos
        req.setAttribute("platos",platos);
        //Direccionamos la vista
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);

    }
}
