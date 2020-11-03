package com.example.feriavirtual.API;

public class SolicitudCompra {

    private int Id;
    private String Descripcion;
    private int PrecioVenta;
    private String UserRut;
    private int EstadoId;
    private String NombreEstado;

    public SolicitudCompra(int id, String descripcion, int precioVenta, String userRut, int estadoId, String nombreEstado) {
        Id = id;
        Descripcion = descripcion;
        PrecioVenta = precioVenta;
        UserRut = userRut;
        EstadoId = estadoId;
        NombreEstado = nombreEstado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        PrecioVenta = precioVenta;
    }

    public String getUserRut() {
        return UserRut;
    }

    public void setUserRut(String userRut) {
        UserRut = userRut;
    }

    public int getEstadoId() {
        return EstadoId;
    }

    public void setEstadoId(int estadoId) {
        EstadoId = estadoId;
    }

    public String getNombreEstado() {
        return NombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        NombreEstado = nombreEstado;
    }
}
