package com.usuario.empresa.web.administracion.entidades;

import java.util.Date;

public class Pagar {

    private int  id_pagar;
    private String estado;
    private Date fecha;
    private int id_miembro;

    public Pagar(int id_pagar, String estado, Date fecha, int id_miembro) {
        this.id_pagar = id_pagar;
        this.estado = estado;
        this.fecha = fecha;
        this.id_miembro = id_miembro;
    }

    public Pagar() {
    }

    public int getId_pagar() {
        return id_pagar;
    }

    public void setId_pagar(int id_pagar) {
        this.id_pagar = id_pagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_miembro() {
        return id_miembro;
    }

    public void setId_miembro(int id_miembro) {
        this.id_miembro = id_miembro;
    }
}
