package services;

import models.Platos;
import repositories.PlatosRepositoryJdbcImplement;
import repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PlatosServiceJdbcImplement implements PlatosService{
    //DECLARO UNA variable de de la clase repository llamamos al modelo platos
    private Repository<Platos> repositoryJdbc;
    // Creamos un constructor
    public PlatosServiceJdbcImplement(Connection connection) {
        this.repositoryJdbc = new PlatosRepositoryJdbcImplement(connection);
    }

    @Override
    public List<Platos> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Platos> agregarPorId(Long idplato) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(idplato));
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void guardar(Platos plato) {
        try{
            repositoryJdbc.guardar(plato);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long idPlato) {
        try{
            repositoryJdbc.eliminar(idPlato);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }


    }
}
