package com.usuario.empresa.web.administracion.servicios;

import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.entidades.Users;

import java.sql.Date;
import java.util.List;

public class InicioService extends ServiceImpl{

	public Users getUsers(String username) throws Exception {
		return (Users) sqlMap.queryForObject("getUsers", username);
	} 
}