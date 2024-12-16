package services;

import models.Rol;
import repositories.RolRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RolServiceImpl implements RolService{
    private RolRepositoryImpl repositoryRol;

    public RolServiceImpl(Connection connection) {
        this.repositoryRol = new RolRepositoryImpl(connection);
    }

    @Override
    public List<Rol> listarRoles() {
        try{
            return repositoryRol.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
