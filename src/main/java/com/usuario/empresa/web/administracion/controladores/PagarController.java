package com.usuario.empresa.web.administracion.controladores;

import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.entidades.Pagar;
import com.usuario.empresa.web.administracion.servicios.GastoComunService;
import com.usuario.empresa.web.administracion.servicios.InicioService;
import com.usuario.empresa.web.administracion.servicios.PagarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;


public class PagarController extends MultiActionController {


    private GastoComunService serviceGC = null;
    private PagarService serviceP = null;
    private ApplicationContext ctx = null;

    public PagarController() {


        ctx = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext.xml");
        serviceGC = (GastoComunService) ctx.getBean("gastosComunesService");
        serviceP = (PagarService) ctx.getBean("pagosService");

    }

    public ModelAndView PagoGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        ModelAndView modelAndView=new ModelAndView("administradores/PagoGC");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView PagoGCE(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        String username=request.getParameter("username");
        String fecha=request.getParameter("date");
        Date fechaBusqueda=Date.valueOf(fecha);
        List<Pagar> pagos=serviceP.getPagos();
        Pagar pagoNuevo=new Pagar();
        for (Pagar pago:pagos) {
            if (pago.getUsername().equals(username) && pago.getFecha().compareTo(fechaBusqueda)==0){
                pagoNuevo=pago;
            }
        }
        pagoNuevo.setEstado("pagado");
        serviceP.updatePago(pagoNuevo);
        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }
}
