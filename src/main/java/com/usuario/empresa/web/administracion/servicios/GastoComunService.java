package com.usuario.empresa.web.administracion.servicios;

import com.usuario.empresa.web.administracion.entidades.GastoComun;


import java.sql.Date;
import java.util.List;

public class GastoComunService extends ServiceImpl{


    @SuppressWarnings("unchecked")
    //devuelve una lista compuesta por objetos de la clase GastoComun, esta lista la obtiene de la consulta getGastosComunes de Ibatis
    public List<GastoComun> getGastosComunes() throws Exception {
        return (List<GastoComun>) sqlMap.queryForList("getGastosComunes");
    }

    public GastoComun getGastoComun(Date fechaGastoComun) throws Exception {
        return (GastoComun) sqlMap.queryForObject("getGastoComun", fechaGastoComun);
    }

    public List<GastoComun> getGCFechaDesde(String fechaGastoComun) throws Exception {
        return (List<GastoComun>) sqlMap.queryForList("getGCFechaDesde",fechaGastoComun);
    }

    public List<GastoComun> getGCFechaHasta(String fechaGastoComun) throws Exception {
        return (List<GastoComun>) sqlMap.queryForList("getGCFechaHasta",fechaGastoComun);
    }

    public void insertGC(GastoComun gastoComun) throws Exception {
        sqlMap.insert("insertGC", gastoComun);
    }

    public void updateGC(GastoComun gc) throws Exception {
        sqlMap.update("updateGC", gc);
    }
    public void deleteGC(String fecha) throws Exception {
        sqlMap.delete("deleteGC", fecha);
    }


}
