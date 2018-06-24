package com.usuario.empresa.web.administracion.entidades;

import java.sql.Date;

public class Pagar {

    private int  id_pagar;
    private String estado;
    private Date fecha;
    private String username;
    private int monto;

    public Pagar(String estado, int monto, Date fecha, String username) {
        this.estado = estado;
        this.monto = monto;
        this.fecha = fecha;
        this.username= username;
    }

    public Pagar() {
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
