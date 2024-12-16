package services;

import models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar_usuario();
    void guardar_usuario(Usuario usuario);
    Optional<Usuario> buscar_usuario_porId(int idUsuario);
}
