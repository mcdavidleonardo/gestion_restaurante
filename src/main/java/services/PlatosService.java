package services;

import models.Platos;

import java.util.List;
import java.util.Optional;

public interface PlatosService {
    //creamos los metodos crud
    List<Platos> listar();
    //metodo para añadir
    Optional<Platos> agregarPorId(Long idplato);
    //implemtamos el metodo para guardar o editar platos
    void guardar(Platos plato);
    //Metodo Eliminar
    void eliminar(Long id);
}
