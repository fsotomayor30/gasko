package com.usuario.empresa.web.administracion.controladores;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usuario.empresa.web.administracion.entidades.Pagar;
import com.usuario.empresa.web.administracion.servicios.PagarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.logout.SecurityContextLogoutHandler;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.usuario.empresa.web.administracion.entidades.Users;
import com.usuario.empresa.web.administracion.servicios.InicioService;

import java.util.List;

//esta clase sirve para obtener los datos de usuarios para posterior direccionar a la home correspondiente
public class InicioController extends MultiActionController {

    private int rol =1;
    private InicioService service = null;
    private ApplicationContext ctx = null;
    private Users objetoUsers = new Users();
    private PagarService serviceP = null;

    public InicioController() {
        ctx = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext.xml");
        service = (InicioService) ctx.getBean("iniciosService");
        serviceP = (PagarService) ctx.getBean("pagosService");
    }


    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        objetoUsers = service.getUsers(userDetails.getUsername());
        rol = objetoUsers.getRol();
        java.util.Date fechaActual = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
        int mesActual = sqlDate.getMonth() + 1;
        List<Pagar> pagarList = serviceP.getPagos();
        for (Pagar pago : pagarList) {
            if ((pago.getFecha().getMonth() + 1 == mesActual && pago.getFecha().getDay() + 1 > 20) || (pago.getFecha().getMonth() + 1 < mesActual)) {
                pago.setEstado("Moroso");
                serviceP.updatePago(pago);
            }

        }

        if(rol ==2) {
            return new ModelAndView("indexAdmin", "usuario", userDetails.getUsername());
        }
        return new ModelAndView("indexUser", "usuario", userDetails.getUsername());
    }

}
