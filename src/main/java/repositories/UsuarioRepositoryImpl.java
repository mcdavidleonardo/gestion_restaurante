package repositories;

import models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl {
    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Usuario> listar_usuario() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from usuario");
            while(rs.next()) {
                Usuario u = getUsuario(rs);
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    public Usuario buscar_usuario_porId(int idUsuario) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement stmt = conn.prepareStatement(
                "select * from usuario where idusuario = ?")) {
            stmt.setInt(1, idUsuario);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    public void guardar_usuario(Usuario usuario) throws SQLException {
        String sql;

        // Verificar si el login ya existe
        if (!validarLogin(usuario.getLogin())) {
            throw new SQLException("El login ya está en uso.");
        }

        if(usuario.getIdusuario() > 0){
            sql = "update usuario set" +
                    " nombres = ?, apellidos = ?, password = ?, estado = ? where idusuario = ?";
        }else{
            sql = "insert into usuario (nombres, apellidos, login, password, estado) values (?, ?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            if(usuario.getIdusuario() > 0){
                stmt.setString(3, usuario.getPassword());
                stmt.setString(4, usuario.getEstado());
                stmt.setInt(5, usuario.getIdusuario());
            }else{
                stmt.setString(3, usuario.getLogin());
                stmt.setString(4, usuario.getPassword());
                stmt.setString(5, usuario.getEstado());
            }
            stmt.executeUpdate();
        }
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdusuario(rs.getInt("idusuario"));
        u.setNombre(rs.getString("nombres"));
        u.setApellido(rs.getString("apellidos"));
        u.setLogin(rs.getString("login"));
        u.setEstado(rs.getString("estado"));
        return u;
    }

    private boolean validarLogin(String login) throws SQLException {
        String sql = "select count(*) from usuario where login = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }
}
