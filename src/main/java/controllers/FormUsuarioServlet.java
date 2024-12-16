package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;
import services.UsuarioService;
import services.UsuarioServiceImpl;
import utils.Encriptar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/usuario_form")
public class FormUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Presentar el formulario
        //Obtener la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        int idUsuario;
        try{
            idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        }catch(NumberFormatException e){
            idUsuario = 0;
        }
        Usuario usuario = new Usuario();
        if(idUsuario > 0){
            Optional<Usuario> u = service.buscar_usuario_porId(idUsuario);
            if(u.isPresent()){
                usuario = u.get();
            }
        }
        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/form_usuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String userlogin = req.getParameter("userlogin");
        String password = req.getParameter("password");
        String estado = req.getParameter("estado");

        //Obtener el idUsuario
        int idUsuario;
        try{
            idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        }catch (NumberFormatException e){
            idUsuario = 0;
        }

        Usuario usuario = new Usuario();
        usuario.setIdusuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setLogin(userlogin);
        usuario.setPassword(Encriptar.convertirSHA256(String.valueOf(password)));
        usuario.setEstado(estado);

        service.guardar_usuario(usuario);
        //Redireccionar a un listado para que no se ejecute el método doPost
        //nuevamente y se guarde los datos duplicados
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}
