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
import java.util.List;

@WebServlet ("/mesa")
public class MesaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        MesaService service = new MesaServiceJdbcImplement(conn);
        List<Mesas> mesas = service.listar();
        req.setAttribute("mesas",mesas);
        //Direccionamos la vista
        getServletContext().getRequestDispatcher("/listarMesa.jsp").forward(req,resp);


    }
}
