package repositories;

import models.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImpl {
    private Connection conn;

    public LoginRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public String validarLogin(Login login) throws SQLException {
        String nombre_rol = null;
        String sql;

        sql = "select ro.nombre as nombre_rol from usuario as us, usuario_rol as ur, rol as ro" +
                " where us.login = ur.login" +
                " and us.estado = 'V'" +
                " and us.login = ?" +
                " and ur.idrol = ?" +
                " and us.password = ?" +
                " and ur.idrol = ro.idrol";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login.getUsuario());
            stmt.setInt(2, login.getRol().getId_rol());
            stmt.setString(3, login.getPassword());
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    nombre_rol = rs.getString("nombre_rol");
                }
            }
        }
        return nombre_rol;
    }
}
