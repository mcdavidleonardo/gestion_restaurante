package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Login;
import models.UsuarioRol;
import services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuariorol")
public class ListaUsuarioRolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtener la conexión a la BDD
        Connection conn = (Connection) req.getAttribute("conn");
        //Crear el nuevo objeto
        UsuarioRolService servicios = new UsuarioRolServiceImpl(conn);
        String userlogin = req.getParameter("userlogin");
        List<UsuarioRol> usuariorol = servicios.listar_usuariorol(userlogin);

        LoginService loginAuth = new LoginServiceSessionImplement(conn);
        Optional<Login> loginData = loginAuth.getUserdata(req);
        Optional<String> username = Optional.ofNullable(loginData.get().getUsuario());


        //Setear los atributos de usuarios y username
        req.setAttribute("username", username);
        req.setAttribute("usuariorol", usuariorol);
        req.setAttribute("userlogin", userlogin);

        //Redireccionar a la vista indicada listar_usuariorol.jsp
        getServletContext().getRequestDispatcher("/listar_usuariorol.jsp").forward(req, resp);
    }
}
