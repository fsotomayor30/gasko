package com.usuario.empresa.web.administracion.servicios;

import com.usuario.empresa.web.administracion.entidades.GastoComun;


import java.sql.Date;
import java.util.List;

public class GastoComunService extends ServiceImpl{

    /**
     * Retorna la lista de gastos comunes
     *
     * @return lista de gastos comunes
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<GastoComun> getGastosComunes() throws Exception {
        return (List<GastoComun>) sqlMap.queryForList("getGastosComunes");
    }

    /**
     * Retorna un gastoC comun dado su correspondiente fecha
     *
     * @param fechaGastoComun
     * @return gastoComun
     * @throws Exception
     */
    public GastoComun getGastoComun(Date fechaGastoComun) throws Exception {
        return (GastoComun) sqlMap.queryForObject("getGastoComun", fechaGastoComun);
    }

    public List<GastoComun> getGCFechaDesde(String fechaGastoComun) throws Exception {
        return (List<GastoComun>) sqlMap.queryForList("getGCFechaDesde",fechaGastoComun);
    }

    public List<GastoComun> getGCFechaHasta(String fechaGastoComun) throws Exception {
        return (List<GastoComun>) sqlMap.queryForList("getGCFechaHasta",fechaGastoComun);
    }
}
