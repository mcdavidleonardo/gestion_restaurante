package models;

public class Mesas {

    private Long idmesa	;
    private String codigoMesa;
    private int capacidad;
    private int estado;

    public Mesas() {
    }

    public Mesas(Long idmesa, String codigoMesa, int capacidad, int estado) {
        this.idmesa = idmesa;
        this.codigoMesa = codigoMesa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Long getIdmesa() {
        return idmesa;
    }

    public void setIdmesa(Long idmesa) {
        this.idmesa = idmesa;
    }

    public String getCodigoMesa() {
        return codigoMesa;
    }

    public void setCodigoMesa(String codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
