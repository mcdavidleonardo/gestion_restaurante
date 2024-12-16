package models;

public class Usuario {
    private int idusuario;
    private String nombre;
    private String apellido;
    private String login;
    private String password;
    private String Estado;

    public Usuario() {
    }

    public Usuario(int idusuario, String nombre, String apellido, String login, String password, String estado) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.login = login;
        this.password = password;
        Estado = estado;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
