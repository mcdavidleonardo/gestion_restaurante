package services;

import models.UsuarioRol;
import repositories.UsuarioRolRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRolServiceImpl implements UsuarioRolService{

    private UsuarioRolRepositoryImpl repositoryUsuarioRol;

    public UsuarioRolServiceImpl(Connection connection) {
        this.repositoryUsuarioRol = new UsuarioRolRepositoryImpl(connection);
    }

    @Override
    public List<UsuarioRol> listar_usuariorol(String userlogin) {
        try{
            return repositoryUsuarioRol.listar_usuariorol(userlogin);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar_usuariorol(UsuarioRol usuariorol) {
        try {
            repositoryUsuarioRol.guardar_usuariorol(usuariorol);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar_usuariorol(int idusuariorol) {
        try {
            repositoryUsuarioRol.eliminar_usuariorol(idusuariorol);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
