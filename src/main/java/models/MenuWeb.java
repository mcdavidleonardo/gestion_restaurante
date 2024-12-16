package models;

public class MenuWeb {
    private String nombre_rol;
    private String nombre_funcionalidad;
    private String url_funcionalidad;

    public MenuWeb() {
    }

    public MenuWeb(String nombre_rol, String nombre_funcionalidad, String url_funcionalidad) {
        this.nombre_rol = nombre_rol;
        this.nombre_funcionalidad = nombre_funcionalidad;
        this.url_funcionalidad = url_funcionalidad;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getNombre_funcionalidad() {
        return nombre_funcionalidad;
    }

    public void setNombre_funcionalidad(String nombre_funcionalidad) {
        this.nombre_funcionalidad = nombre_funcionalidad;
    }

    public String getUrl_funcionalidad() {
        return url_funcionalidad;
    }

    public void setUrl_funcionalidad(String url_funcionalidad) {
        this.url_funcionalidad = url_funcionalidad;
    }
}
