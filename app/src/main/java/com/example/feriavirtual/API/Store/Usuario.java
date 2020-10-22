package com.example.feriavirtual.API.Store;

public class Usuario {
    private String Rut;
    private String Email;
    private String Nombre;
    private String Token;
    private int RolId;
    private String NombreRol;

    public Usuario(String rut, String email, String nombre, String token, int rolId, String nombreRol) {
        this.Rut = rut;
        this.Email = email;
        this.Nombre = nombre;
        this.Token = token;
        this.RolId = rolId;
        this.NombreRol = nombreRol;
    }
    public String getRut() {
        return Rut;
    }

    public String getEmail() {
        return Email;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getToken() {
        return Token;
    }
    public int getRolId() {
        return RolId;
    }
    public String getNombreRol() {
        return NombreRol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Rut='" + Rut + '\'' +
                ", Email='" + Email + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Token='" + Token + '\'' +
                ", RolId=" + RolId +
                ", NombreRol='" + NombreRol + '\'' +
                '}';
    }
}
