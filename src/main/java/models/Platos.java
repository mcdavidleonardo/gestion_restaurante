package models;

public class Platos {

    private Long idplato;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precio;
    private int stock;
    private int estado;

    public Platos() {
    }

    public Platos(Long idplato, String nombre, String descripcion, String categoria, double precio, int stock, int estado) {
        this.idplato = idplato;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public Long getIdplato() {
        return idplato;
    }

    public void setIdplato(Long idplato) {
        this.idplato = idplato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
