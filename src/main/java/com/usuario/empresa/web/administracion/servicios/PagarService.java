package com.usuario.empresa.web.administracion.servicios;


import com.usuario.empresa.web.administracion.entidades.Pagar;


import java.sql.Date;
import java.util.List;

public class PagarService extends ServiceImpl{


    @SuppressWarnings("unchecked")
    public List<Pagar> getPagos() throws Exception {
        return (List<Pagar>) sqlMap.queryForList("getPagos");
    }


    public Pagar getPagosPorFecha(Date fechaPago) throws Exception {
        return (Pagar) sqlMap.queryForObject("getPagosPorFecha", fechaPago);
    }

    public void insertPago(Pagar pago) throws Exception {
        sqlMap.insert("insertPago", pago);
    }

}
