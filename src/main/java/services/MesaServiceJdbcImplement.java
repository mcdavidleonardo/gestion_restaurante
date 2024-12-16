package services;

import models.Mesas;
import repositories.MesasRepositoryJdbcImplement;
import repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MesaServiceJdbcImplement implements MesaService {
    private Repository<Mesas> repositoryJdbc;

    public MesaServiceJdbcImplement( Connection connection) {
        this.repositoryJdbc = new MesasRepositoryJdbcImplement(connection);
    }

    @Override
    public List<Mesas> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Mesas> agregarPorId(Long idmesa) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(idmesa));
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void guardar(Mesas mesa) {
        try{
            repositoryJdbc.guardar(mesa);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }

    }

    @Override
    public void eliminar(Long idMesa) {
        try{
            repositoryJdbc.eliminar(idMesa);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }

    }
}
