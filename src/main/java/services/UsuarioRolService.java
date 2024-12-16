package services;

import models.UsuarioRol;

import java.util.List;

public interface UsuarioRolService {
    List<UsuarioRol> listar_usuariorol(String userlogin);
    void guardar_usuariorol(UsuarioRol usuariorol);
    void eliminar_usuariorol(int idusuariorol);
}
