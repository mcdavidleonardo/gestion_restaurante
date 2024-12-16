package services;

import jakarta.servlet.http.HttpServletRequest;
import models.Login;

import java.util.Optional;

public interface LoginService {
    Optional<Login> getUserdata(HttpServletRequest request);
    Optional<String> validarLogin(Login login);
}
