package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.Login;
import repositories.LoginRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService{

    private LoginRepositoryImpl repositoryLogin;

    public LoginServiceSessionImplement() {
    }

    public LoginServiceSessionImplement(Connection connection) {
        this.repositoryLogin = new LoginRepositoryImpl(connection);
    }

    @Override
    public Optional<Login> getUserdata(HttpServletRequest request) {
        //Obtengo al sesion
        HttpSession session = request.getSession();
        //Obtengo el objeto Login
        Login login = (Login) session.getAttribute("login");
        //Convierto el objeto de tipo sesion en String
        /*
         * Creo una condición en la cual valido
         * si al obtener el nombre del usuario es distinto de nulo
         * obtengo el username
         * Caso contrario devuelve la sesion vacia */
        if (login != null) {
            return Optional.of(login);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> validarLogin(Login login) {
        String nombre_rol;

        try {
            nombre_rol = repositoryLogin.validarLogin(login);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        if (nombre_rol != null) {
            return Optional.of(nombre_rol);
        }
        return Optional.empty();
    }
}
