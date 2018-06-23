package com.usuario.empresa.web.administracion.entidades;

import java.sql.Date;

public class GastoComun {
    private Date fecha;
    private int monto;
    private String descripcion;

    public GastoComun(Date fecha, int monto, String descripcion) {
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion=descripcion;
    }

    public GastoComun() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
