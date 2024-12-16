package services;

import models.Usuario;
import repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepositoryImpl repositoryUsuario;

    public UsuarioServiceImpl(Connection connection) {
        this.repositoryUsuario = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public List<Usuario> listar_usuario() {
        try{
            return repositoryUsuario.listar_usuario();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar_usuario(Usuario usuario) {
        try {
            repositoryUsuario.guardar_usuario(usuario);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Usuario> buscar_usuario_porId(int idUsuario) {
        try{
            return Optional.ofNullable(repositoryUsuario.buscar_usuario_porId(idUsuario));
        }catch(SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
