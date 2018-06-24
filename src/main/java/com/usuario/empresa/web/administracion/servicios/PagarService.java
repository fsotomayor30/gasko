package com.usuario.empresa.web.administracion.servicios;


import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.entidades.Pagar;


import java.sql.Date;
import java.util.List;

public class PagarService extends ServiceImpl{


    @SuppressWarnings("unchecked")
    //devuelve una lista compuesta por objetos de la clase Pagar, esta lista la obtiene de la consulta getPagos de Ibatis
    public List<Pagar> getPagos() throws Exception {
        return (List<Pagar>) sqlMap.queryForList("getPagos");
    }


    public Pagar getPagosPorFecha(Date fechaPago) throws Exception {
        return (Pagar) sqlMap.queryForObject("getPagosPorFecha", fechaPago);
    }

    public void insertPago(Pagar pago) throws Exception {
        sqlMap.insert("insertPago", pago);
    }

    public void updatePago(Pagar pago) throws Exception {
        sqlMap.update("updatePago", pago);
    }

    public void deletePago(Pagar pago) throws Exception {
        sqlMap.delete("deletePago", pago);
    }
}
