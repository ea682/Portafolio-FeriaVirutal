package com.example.feriavirtual.API.Store;

import android.os.Parcel;
import android.os.Parcelable;

public class SolicitudCompra implements Parcelable {

    private int Id;
    private String Descripcion;
    private int PrecioVenta;
    private String UsuarioRut;
    private int EstadoId;
    private String NombreEstado;

    public SolicitudCompra(int id, String descripcion, int precioVenta, String usuarioRut, int estadoId, String nombreEstado) {
        this.Id = id;
        this.Descripcion = descripcion;
        this.PrecioVenta = precioVenta;
        this.UsuarioRut = usuarioRut;
        this.EstadoId = estadoId;
        this.NombreEstado = nombreEstado;
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

    public String getUsuarioRut() {
        return UsuarioRut;
    }

    public void setUsuarioRut(String usuarioRut) {
        UsuarioRut = usuarioRut;
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

    protected SolicitudCompra(Parcel in) {
        Id = in.readInt();
        Descripcion = in.readString();
        PrecioVenta = in.readInt();
        UsuarioRut = in.readString();
        EstadoId = in.readInt();
        NombreEstado = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Descripcion);
        dest.writeInt(PrecioVenta);
        dest.writeString(UsuarioRut);
        dest.writeInt(EstadoId);
        dest.writeString(NombreEstado);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SolicitudCompra> CREATOR = new Parcelable.Creator<SolicitudCompra>() {
        @Override
        public SolicitudCompra createFromParcel(Parcel in) {
            return new SolicitudCompra(in);
        }

        @Override
        public SolicitudCompra[] newArray(int size) {
            return new SolicitudCompra[size];
        }
    };
}