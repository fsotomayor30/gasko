package com.usuario.empresa.web.administracion.entidades;


public class TipoGasto {
    private int id;
    private String descripcion;

    public TipoGasto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipoGasto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}