package com.usuario.empresa.web.administracion.entidades;


public class Miembro {
    private int id_miembro;
    private String correo_miembro;
    private String contrasena_miembro;

    public Miembro(int id_miembro, String correo_miembro, String contrasena_miembro) {
        this.id_miembro = id_miembro;
        this.correo_miembro = correo_miembro;
        this.contrasena_miembro = contrasena_miembro;
    }

    public Miembro() {
    }

    public int getId_miembro() {
        return id_miembro;
    }

    public void setId_miembro(int id_miembro) {
        this.id_miembro = id_miembro;
    }

    public String getCorreo_miembro() {
        return correo_miembro;
    }

    public void setCorreo_miembro(String correo_miembro) {
        this.correo_miembro = correo_miembro;
    }

    public String getContrasena_miembro() {
        return contrasena_miembro;
    }

    public void setContrasena_miembro(String contrasena_miembro) {
        this.contrasena_miembro = contrasena_miembro;
    }
}
