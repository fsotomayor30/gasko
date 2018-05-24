package com.usuario.empresa.web.administracion.entidades;

import java.sql.Date;

public class GastoComun {
    private Date fecha;
    private int monto;

    public GastoComun(Date fecha, int monto) {
        this.fecha = fecha;
        this.monto = monto;
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
}
