package com.usuario.empresa.web.administracion.entidades;

import java.sql.Date;

public class GastoComun {
    private Date fecha;
    private int monto;
    private int descripcion;
    private int id;
    int generado;

    public GastoComun(Date fecha, int id, int monto, int descripcion) {
        this.fecha = fecha;
        this.id = id;
        this.monto = monto;
        this.descripcion=descripcion;
    }

    public GastoComun() {
    }

    public int getGenerado() {
        return generado;
    }

    public void setGenerado(int generado) {
        this.generado = generado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }
}
