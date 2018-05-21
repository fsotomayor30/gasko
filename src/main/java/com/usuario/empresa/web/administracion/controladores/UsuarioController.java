package com.usuario.empresa.web.administracion.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.usuario.empresa.web.administracion.entidades.Usuario;
import com.usuario.empresa.web.administracion.servicios.UsuarioService;

public class UsuarioController extends MultiActionController {
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private UsuarioService service = null;
	private ApplicationContext ctx = null;

	/**
	 * constructor
	 */
	public UsuarioController() {
		Usuario usuario = null;
		// usuario 1
		usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Pedro Perez");
		usuario.setEdad(30);
		usuarios.add(usuario);
		// usuario 1
		usuario = new Usuario();
		usuario.setId(2);
		usuario.setNombre("Maria Gonzalez");
		usuario.setEdad(25);
		usuarios.add(usuario);

		ctx = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext.xml");
		service = (UsuarioService) ctx.getBean("usuariosService");
	}

	/**
	 * Método que lista los usuarios
	 * 
	 * @param request
	 * @param response
	 * @return Lista de Usuarios
	 * @throws Exception
	 */
	public ModelAndView listar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// método de primera iteracion
		// return new ModelAndView("usuarios/listar", "usuarios", usuarios);

		// obtener la lista de usuarios
		List<Usuario> listaUsuarios = service.getUsuarios();
		/*
		// Obtener un usuario segun id
		Usuario usuario = service.getUsuario(1);
		// obtiene la cantidad total de usuarios
		int totalUsuarios = service.getTotalUsuarios();
		// inserta un nuevo usuario
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre("Cristian");
		nuevoUsuario.setEdad(45);
		int usuarioId = service.insertUsuario(nuevoUsuario);
		// /actualiza los datos del usuario
		service.updateUsuario(usuario);
		// eliminar usuario segun id
		service.deleteUsuario(1);
		 */
		return new ModelAndView("usuarios/listar", "usuarios", listaUsuarios);
	}

	/**
	 * Método que siempre devuelve el primer usuario
	 * 
	 * @param request
	 * @param response
	 * @return primer usuario
	 * @throws Exception
	 */
	public ModelAndView ver(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("usuarios/ver", "usuario", usuarios.get(0));
	}
}
