package repositories;

import models.Mesas;
import services.ServiceJdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesasRepositoryJdbcImplement implements Repository<Mesas>{
    // declaramos una variable de tipo connection
    private Connection conn;
    //creamos el contructor
    public MesasRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    //Sobreescribimos los metodos del repository
    @Override
    public List<Mesas> listar() throws SQLException {
        String SQL = "SELECT m.idmesa, m.codigoMesa, m.capacidad, m.estado FROM mesa AS m";
        List<Mesas> mesa = new ArrayList<>();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while ((rs.next())){
                Mesas mesas = getMesas(rs);
                mesa.add(mesas);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mesa;
    }

    @Override
    public Mesas porId(Long idMesa) throws SQLException {
        String SQL = "SELECT * FROM mesa WHERE idmesa = ?";
        Mesas mesa = null;
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setLong(1, idMesa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mesa = getMesas(rs);
                }
            }
        }
         return mesa;
    }


    @Override
    public void guardar(Mesas mesas) throws SQLException {
        String SQL;
        boolean isUpdate = mesas.getIdmesa() != null && mesas.getIdmesa() > 0;
        if (isUpdate) {
            SQL = "UPDATE mesa SET  capacidad = ?, estado = ?, codigoMesa = ? WHERE idmesa = ?";
        } else {
            SQL = "INSERT INTO mesa (capacidad, estado, codigoMesa) VALUES (?, ?, ?)";
        }
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, mesas.getCapacidad());
            ps.setInt(2, mesas.getEstado());
            ps.setString(3,mesas.getCodigoMesa());
            if (isUpdate) {
                ps.setLong(4, mesas.getIdmesa());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error al guardar la mesa: " + e.getMessage(), e);
        }
    }


    @Override
    public void eliminar(Long id) throws SQLException {
        String SQL = "DELETE FROM mesa WHERE idmesa = ?";
        try(PreparedStatement ps = conn.prepareStatement(SQL)){
            ps.setLong(1,id);
            ps.executeUpdate();
        }
    }

    private static Mesas getMesas(ResultSet rs) throws SQLException {
        Mesas mesas = new Mesas();
        mesas.setIdmesa(rs.getLong("idmesa"));
        mesas.setCodigoMesa(rs.getString("codigoMesa"));
        mesas.setCapacidad(rs.getInt("capacidad"));
        mesas.setEstado(rs.getInt("estado"));
        return mesas;
    }
}
