package com.usuario.empresa.web.administracion.controladores;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.usuario.empresa.web.administracion.entidades.Login;
import com.usuario.empresa.web.administracion.entidades.Users;
import com.usuario.empresa.web.administracion.servicios.InicioService;

public class InicioController extends MultiActionController {
	
	private int rol =1; 
	private InicioService service = null;
	private ApplicationContext ctx = null; 
	private Login objetoLogin = new Login();
	private String username;
	private Users objetoUsers = new Users();
	
	public InicioController() {  
		ctx = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext.xml");
		service = (InicioService) ctx.getBean("iniciosService"); //PREGUNTAR
	}
	
	private Login obtenerDatosLogin(HttpServletRequest request, Login login) { 	
		login.setCorreo(request.getParameter("j_username"));
		login.setContrasena(request.getParameter("j_password"));
		return login;
	}   
	
	/*public void obtenerrol() throws Exception {
		HttpServletRequest request = null;
		objetoLogin = obtenerDatosLogin(request, objetoLogin);
		objetoUsers = service.getUsers(objetoLogin.getCorreo());
		rol = objetoUsers.getRol();
	}*/
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
		
		/*objetoLogin = obtenerDatosLogin(request, objetoLogin);
		objetoUsers = service.getUsers(objetoLogin.getCorreo());
		rol = objetoUsers.getRol();*/
		
		if(rol ==2) {
			return new ModelAndView("indexAdmin");
		}
		return new ModelAndView("indexUser");
	}

}
