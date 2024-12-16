package services;

import models.MenuWeb;

import java.util.List;

public interface MenuWebService {
    List<MenuWeb> listarMenuWeb(String nombre_rol);
}
