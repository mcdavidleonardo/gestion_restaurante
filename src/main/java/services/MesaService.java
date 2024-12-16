package services;

import models.Mesas;

import java.util.List;
import java.util.Optional;

public interface MesaService {
    // creamos lo metodos crud para mesa
    //listar
    List<Mesas> listar();
    // agregar por si id
    Optional<Mesas> agregarPorId(Long idmesa);
    //guardar o editar
    void guardar(Mesas mesa);
    // Eliminar
    void eliminar(Long id);

}
