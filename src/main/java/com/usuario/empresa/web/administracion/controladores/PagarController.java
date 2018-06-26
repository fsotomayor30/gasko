package com.usuario.empresa.web.administracion.controladores;

import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.entidades.Pagar;
import com.usuario.empresa.web.administracion.entidades.Users;
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
    private InicioService serviceU = null;
    private ApplicationContext ctx = null;

    public PagarController() {


        ctx = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext.xml");
        serviceGC = (GastoComunService) ctx.getBean("gastosComunesService");
        serviceP = (PagarService) ctx.getBean("pagosService");
        serviceU = (InicioService) ctx.getBean("iniciosService");

    }

    public ModelAndView PagoGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        ModelAndView modelAndView=new ModelAndView("administradores/PagoGC");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView PagoGCE(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        ModelAndView modelAndView = new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario", userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView generarPago(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        //creo una lista con objetos de la clase Users y la lleno con el servicio getUsersUsuarioNormal() el cual obtiene una lista de
        // objetos Users preguntando a la BD por las tuplas Users con rol 1 (osea obtiene todos los miembros de la tabla Users, no toma
        //encuenta las tuplas con rol 2 de administrador)
        List<Users> listaUsuariosNormal = serviceU.getUsersUsuarioNormal();

        java.util.Date fechaActual = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
        int mesActual = sqlDate.getMonth() + 1;

        List<GastoComun> gastoComunList = serviceGC.getGastosComunes();

        int saldoMes = 0;
        for (GastoComun gc : gastoComunList) {
            if (gc.getFecha().getMonth() + 1 == mesActual) {
                saldoMes = saldoMes + gc.getMonto();
                gc.setGenerado(1);
                serviceGC.updateGC(gc);
            }
        }

        if (saldoMes != 0) {
            for (Users us : listaUsuariosNormal) {
                Pagar pago = new Pagar("pendiente de pago", (saldoMes / listaUsuariosNormal.size()), sqlDate, us.getUsername());
                serviceP.insertPago(pago);
            }
        }

        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }
}
