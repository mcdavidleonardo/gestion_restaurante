package services;

import models.MenuWeb;

import java.util.Arrays;
import java.util.List;

public class MenuWebServiceImpl implements MenuWebService {

    @Override
    public List<MenuWeb> listarMenuWeb(String nombre_rol) {
        if (nombre_rol.equals("root")) {
            return Arrays.asList(new MenuWeb("root", "Administración de Usuarios", "usuarios"),
                    new MenuWeb("root", "Administración de Mesas", "mesa"),
                    new MenuWeb("root", "Administración de Pedidos", "pedidos"),
                    new MenuWeb("root", "Administración de Clientes", "clientes"),
                    new MenuWeb("root", "Facturación", "facturacion"),
                    new MenuWeb("root", "Administración de Platos", "platos"));
        }
        if (nombre_rol.equals("Administrador")) {
            return Arrays.asList(new MenuWeb("Administrador", "Administración de Usuarios", "usuarios"));
        }
        if (nombre_rol.equals("Mesero")) {
            return Arrays.asList(new MenuWeb("Mesero", "Administración de Mesas", "mesa"),
                    new MenuWeb("Mesero", "Administración de Pedidos", "pedidos"));
        }
        if (nombre_rol.equals("Caja")) {
            return Arrays.asList(new MenuWeb("Caja", "Administración de Clientes", "clientes"),
                    new MenuWeb("Caja", "Facturación", "facturacion"));
        }
        if (nombre_rol.equals("Cocina")) {
            return Arrays.asList(new MenuWeb("Cocina", "Administración de Platos", "platos"),
                    new MenuWeb("Cocina", "Administración de Pedidos", "pedidos"));
        }
        return null;
    }
}
