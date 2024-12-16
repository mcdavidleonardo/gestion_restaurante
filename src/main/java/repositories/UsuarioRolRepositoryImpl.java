package repositories;

import models.Rol;
import models.Usuario;
import models.UsuarioRol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRolRepositoryImpl {
    private Connection conn;

    public UsuarioRolRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public List<UsuarioRol> listar_usuariorol(String userlogin) throws SQLException {
        List<UsuarioRol> usuariorol = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(
                "select ur.idusuario_rol as idusuario_rol, ro.idrol as idrol, ro.nombre as nombre from usuario_rol as ur, rol as ro where ur.idrol = ro.idrol and ur.login = ?")) {
            stmt.setString(1, userlogin);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Rol r = new Rol();
                    r.setId_rol(rs.getInt("idrol"));
                    r.setNombre(rs.getString("nombre"));

                    UsuarioRol ur = new UsuarioRol();
                    ur.setId_usuariorol(rs.getInt("idusuario_rol"));
                    ur.setRol(r);

                    Usuario u = new Usuario();
                    u.setLogin(userlogin);
                    ur.setUsuario(u);

                    usuariorol.add(ur);
                }
            }
        }
        return usuariorol;
    }

    public void guardar_usuariorol(UsuarioRol usuariorol) throws SQLException {
        String verificarSql = "select count(*) from usuario_rol where login = ? and idrol = ?";
        String insertarSql = "insert into usuario_rol (login, idrol) values (?, ?)";

        try (PreparedStatement verificarStmt = conn.prepareStatement(verificarSql)) {
            verificarStmt.setString(1, usuariorol.getUsuario().getLogin());
            verificarStmt.setInt(2, usuariorol.getRol().getId_rol());

            try (ResultSet rs = verificarStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new SQLException("El rol ya está asignado a este usuario.");
                }
            }
        }

        try (PreparedStatement insertarStmt = conn.prepareStatement(insertarSql)) {
            insertarStmt.setString(1, usuariorol.getUsuario().getLogin());
            insertarStmt.setInt(2, usuariorol.getRol().getId_rol());
            insertarStmt.executeUpdate();
        }
    }

    public void eliminar_usuariorol(int idusuariorol) throws SQLException {
        String sql = "delete from usuario_rol where idusuario_rol = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idusuariorol);
            stmt.executeUpdate();
        }
    }
}
