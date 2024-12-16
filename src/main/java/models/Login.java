package models;

/* Clase para manejar el login y rol del usuario que se conecta al sistema.
Esta información, junto con la contraseña, se envían a la BDD para validar la
autenticación.
*/
public class Login {
    private String usuario;
    private Rol rol;
    private String password;

    public Login() {
    }

    public Login(String usuario, Rol rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
