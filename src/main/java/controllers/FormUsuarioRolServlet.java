package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Rol;
import models.Usuario;
import models.UsuarioRol;
import services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/usuariorol_form")
public class FormUsuarioRolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Presentar el formulario
        //Obtener la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioRolService serviceUR = new UsuarioRolServiceImpl(conn);
        RolService serviceR = new RolServiceImpl(conn);
        List<Rol> roles = serviceR.listarRoles();
        String userlogin = req.getParameter("userlogin");

        int idUsuarioRol;
        try{
            idUsuarioRol = Integer.parseInt(req.getParameter("idUsuarioRol"));
        }catch(NumberFormatException e){
            idUsuarioRol = 0;
        }
        if(idUsuarioRol > 0){
            serviceUR.eliminar_usuariorol(idUsuarioRol);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        }else {
            req.setAttribute("roles", roles);
            req.setAttribute("userlogin", userlogin);
            getServletContext().getRequestDispatcher("/form_usuariorol.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioRolService service = new UsuarioRolServiceImpl(conn);
        String userlogin = req.getParameter("userlogin");

        //Obtener el Rol
        int idRol;
        try{
            idRol = Integer.parseInt(req.getParameter("rol"));
        }catch (NumberFormatException e){
            idRol = 0;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(userlogin);
        Rol rol = new Rol();
        rol.setId_rol(idRol);

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);

        service.guardar_usuariorol(usuarioRol);
        //Redireccionar a un listado para que no se ejecute el método doPost
        //nuevamente y se guarde los datos duplicados
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}
