package repositories;

import models.Platos;
import services.ServiceJdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatosRepositoryJdbcImplement implements Repository<Platos>{
    /* Necesitamos una conexion a la base de datos , la conexxión se tiene que pasar al repository, luego
     * se lo pasa al Service y a su veez el servlet lo obtiene del objeto request de los atributos
     * que se setearon por request vuelve a oasar al seervice y el service pasa al repository*/
    private Connection conn;
    // implementamos un contructor para inicializar a la Conexion
    public PlatosRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }
    //Sobreescribimos alos metodos de la clase interfaz
    //Inicion de funcion para listar platos
    @Override
    public List<Platos> listar() throws SQLException {
        //declaro una variable que me ba ha contener el query de la bbdd gestion // platos
        String SQL="SELECT * FROM plato ORDER BY idplato ASC";
        //INICIALIZO PLATOS = DATOS DE MODELO
        List<Platos> platos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Platos plato = getPlatos(rs);
                platos.add(plato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return platos;
    }

    @Override
    public Platos porId(Long idPlato) throws SQLException {
       String SQL ="SELECT * FROM plato WHERE idplato=?";
       Platos plato = null;
       try(PreparedStatement ps = conn.prepareStatement(SQL)){
           ps.setLong(1,idPlato);
           try(ResultSet rs = ps.executeQuery()){
               if (rs.next()){
                   plato=getPlatos(rs);
               }
           }
       }

        return plato;
    }
// GUARDA O EDITA
@Override
public void guardar(Platos platos) throws SQLException {
    String SQL;
    boolean isUpdate = platos.getIdplato() != null && platos.getIdplato() > 0;

    if (isUpdate) {
        // Consulta para actualizar un registro existente
        SQL = "UPDATE plato SET nombre = ?, descripcion = ?, categoria = ?, precio = ?, stock = ?, estado = ? WHERE idplato = ?";
    } else {
        // Consulta para insertar un nuevo registro
        SQL = "INSERT INTO plato (nombre, descripcion, categoria, precio, stock, estado) VALUES (?, ?, ?, ?, ?, ?)";
    }

    try (PreparedStatement ps = conn.prepareStatement(SQL)) {
        // Asignación de parámetros comunes
        ps.setString(1, platos.getNombre());
        ps.setString(2, platos.getDescripcion());
        ps.setString(3, platos.getCategoria());
        ps.setDouble(4, platos.getPrecio());
        ps.setInt(5, platos.getStock());
        ps.setInt(6, platos.getEstado());
        if (isUpdate) {
            // Parámetro adicional para la consulta de actualización
            ps.setLong(7, platos.getIdplato());
        }
        ps.executeUpdate();
    } catch (SQLException e) {
        // Aquí se maneja la excepción adecuadamente
        throw new ServiceJdbcException("Error al guardar el plato: " + e.getMessage(), e);
    }
}
    @Override
    public void eliminar(Long idPlato) throws SQLException {
        String SQL = "DELETE FROM plato Where idplato = ?";
        try (PreparedStatement ps = conn.prepareStatement(SQL)){
            ps.setLong(1,idPlato);
            ps.executeUpdate();
        }

    }

    private static Platos getPlatos(ResultSet rs) throws SQLException {
        Platos plato = new Platos();
        plato.setIdplato(rs.getLong("idplato"));
        plato.setNombre(rs.getString("nombre"));
        plato.setDescripcion(rs.getString("descripcion"));
        plato.setCategoria(rs.getString("categoria"));
        plato.setPrecio(rs.getDouble("precio"));
        plato.setStock(rs.getInt("stock"));
        plato.setEstado(rs.getInt("estado"));
        return plato;
    }
}
