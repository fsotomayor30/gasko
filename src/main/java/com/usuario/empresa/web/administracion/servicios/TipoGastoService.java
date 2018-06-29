package com.usuario.empresa.web.administracion.servicios;

import com.usuario.empresa.web.administracion.entidades.TipoGasto;

import java.util.List;

public class TipoGastoService extends ServiceImpl {


    @SuppressWarnings("unchecked")

    public List<TipoGasto> getTiposGastos() throws Exception {
        return (List<TipoGasto>) sqlMap.queryForList("getTiposGastos");
    }

    public TipoGasto getTipoGastoDescripcion(String descripcion) throws Exception {
        return (TipoGasto) sqlMap.queryForObject("getTipoGastoDescripcion", descripcion);
    }

    public TipoGasto getTipoGastoId(int id) throws Exception {
        return (TipoGasto) sqlMap.queryForObject("getTipoGastoId", id);
    }

    public void insertTipoGasto(TipoGasto tipoGasto) throws Exception {
        sqlMap.insert("insertTipoGasto", tipoGasto);
    }


}
