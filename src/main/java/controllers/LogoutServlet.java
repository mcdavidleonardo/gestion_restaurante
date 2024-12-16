package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Login;
import services.LoginService;
import services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginAuth = new LoginServiceSessionImplement();
        Optional<Login> loginData = loginAuth.getUserdata(req);
        if(loginData.isPresent()) {
            HttpSession session = req.getSession();
            //Este método elimina la sesión
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath()+"/index.html");
    }
}
