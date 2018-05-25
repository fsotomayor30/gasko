package com.usuario.empresa.web.administracion.controladores;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.usuario.empresa.web.administracion.entidades.Users;
import com.usuario.empresa.web.administracion.servicios.InicioService;

public class InicioController extends MultiActionController {
	
	private int rol =1; 
	private InicioService service = null;
	private ApplicationContext ctx = null; 
	private String username;
	private Users objetoUsers = new Users();
	
	public InicioController() {  
		ctx = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext.xml");
		service = (InicioService) ctx.getBean("iniciosService"); //PREGUNTAR
	}
	


	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails=(UserDetails) auth.getPrincipal();
		objetoUsers = service.getUsers(userDetails.getUsername());
		rol = objetoUsers.getRol();
		
		if(rol ==2) {
			return new ModelAndView("indexAdmin");
		}
		return new ModelAndView("indexUser");
	}

}
