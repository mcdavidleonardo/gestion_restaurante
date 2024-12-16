package repositories;

import models.Rol;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolRepositoryImpl {
    private Connection conn;

    public RolRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Rol> listar() throws SQLException {
        List<Rol> roles = new ArrayList<>();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from rol");
            while(rs.next()) {
                Rol rol = getRol(rs);
                roles.add(rol);
            }
        }
        return roles;
    }

    private static Rol getRol(ResultSet rs) throws SQLException {
        Rol rol = new Rol();
        rol.setId_rol(rs.getInt("idrol"));
        rol.setNombre(rs.getString("nombre"));
        return rol;
    }
}
