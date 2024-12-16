package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Login;
import models.Usuario;
import services.LoginService;
import services.LoginServiceSessionImplement;
import services.UsuarioService;
import services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuarios")
public class ListaUsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtener la conexión a la BDD
        Connection conn = (Connection) req.getAttribute("conn");
        //Crear el nuevo objeto
        UsuarioService servicios = new UsuarioServiceImpl(conn);
        List<Usuario> usuarios = servicios.listar_usuario();

        LoginService loginAuth = new LoginServiceSessionImplement(conn);
        Optional<Login> loginData = loginAuth.getUserdata(req);
        Optional<String> username = Optional.ofNullable(loginData.get().getUsuario());

        //Setear los atributos de usuarios y username
        req.setAttribute("username", username);
        req.setAttribute("usuarios", usuarios);

        //Redireccionar a la vista indicada listarcategoria.jsp
        getServletContext().getRequestDispatcher("/listar_usuario.jsp").forward(req, resp);
    }
}
