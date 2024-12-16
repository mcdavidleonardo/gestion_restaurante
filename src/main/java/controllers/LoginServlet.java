package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Login;
import models.MenuWeb;
import models.Rol;
import services.*;
import utils.Encriptar;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService loginAuth = new LoginServiceSessionImplement();
        Optional<Login> loginData = loginAuth.getUserdata(req);
        if (loginData.isPresent()) {
            //Redireccionar a la vista menu principal
            HttpSession session=req.getSession();

            Login login = (Login) session.getAttribute("login");
            String username = login.getUsuario();
            List<MenuWeb> menu = (List<MenuWeb>) session.getAttribute("menurol");

            req.setAttribute("username", Optional.ofNullable(username));
            req.setAttribute("opciones_menu", menu);
            getServletContext().getRequestDispatcher("/main_menu.jsp").forward(req, resp);
        }else{
            //Obtener la conexión a la BDD
            //Para cargar los roles en pantalla
            Connection conn = (Connection) req.getAttribute("conn");
            RolService servicios = new RolServiceImpl(conn);
            List<Rol> roles = servicios.listarRoles();

            //Setear el atributo para roles
            req.setAttribute("roles", roles);

            //Redireccionar a la vista indicada login.jsp
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Obtener la conexión a la BDD
        //La validación de usuario, rol y contraseña se hace con la BDD
        Connection conn = (Connection) req.getAttribute("conn");
        LoginService loginAuth = new LoginServiceSessionImplement(conn);
        MenuWebService opciones_menu = new MenuWebServiceImpl();
        Login usuario_login = new Login();
        Rol rol = new Rol();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Datos el rol
        int id_rol;
        try{
            id_rol = Integer.valueOf(req.getParameter("rol"));
        }catch(NumberFormatException e){
            id_rol = 0;
        }

        // Se genera el objeto que maneja los datos del usuario que se loguea al sistema
        rol.setId_rol(id_rol);
        usuario_login.setUsuario(username);
        usuario_login.setPassword(Encriptar.convertirSHA256(String.valueOf(password)));
        usuario_login.setRol(rol);
        Optional<String> nombre_rol = loginAuth.validarLogin(usuario_login);
        if (nombre_rol.isPresent()){
            List<MenuWeb> mw = opciones_menu.listarMenuWeb(nombre_rol.get());
            //Se crea la sesión
            HttpSession session=req.getSession();
            session.setAttribute("login", usuario_login);
            session.setAttribute("menurol", mw);
            req.setAttribute("username", Optional.ofNullable(username));
            req.setAttribute("opciones_menu", mw);
            getServletContext().getRequestDispatcher("/main_menu.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
