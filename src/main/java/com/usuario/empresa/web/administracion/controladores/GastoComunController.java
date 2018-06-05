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
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GastoComunController extends MultiActionController {

    List<GastoComun> listaGastosComunes;
    List<GastoComun> listaGastosComunesResultantes;

    private GastoComunService serviceGC = null;
    private PagarService serviceP = null;
    private InicioService serviceU=null;
    private ApplicationContext ctx = null;
    private String usuario;

    /**
     * constructor
     */
    public GastoComunController() {

        listaGastosComunes = new ArrayList<GastoComun>();
        listaGastosComunesResultantes=new ArrayList<GastoComun>();


        ctx = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext.xml");
        serviceGC = (GastoComunService) ctx.getBean("gastosComunesService");
        serviceP = (PagarService) ctx.getBean("pagosService");
        serviceU=(InicioService) ctx.getBean("iniciosService");
    }


    public ModelAndView pantallaInicioMiembro(HttpServletRequest request,HttpServletResponse response) throws Exception {
        List <Pagar> listaPagosResultantes=new ArrayList<Pagar>();
        listaGastosComunesResultantes=new ArrayList<GastoComun>();
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        usuario = userDetails.getUsername();
        List<Pagar> listaPagos=serviceP.getPagos();
        for (Pagar Pagos:listaPagos) {
            if (Pagos.getUsername().equalsIgnoreCase(usuario)){
                listaPagosResultantes.add(Pagos);
            }
        }
        listaGastosComunes=serviceGC.getGastosComunes();
        for (Pagar listaPago:listaPagosResultantes) {
            for (GastoComun gastoComun: listaGastosComunes) {
                if (listaPago.getFecha().compareTo(gastoComun.getFecha())== 0){
                    listaGastosComunesResultantes.add(gastoComun);

                }
            }
        }

        ModelAndView modelAndView=new ModelAndView("usuarios/pantallaInicioMiembro");
        modelAndView.addObject("gastosComunes",listaGastosComunesResultantes);
        modelAndView.addObject("usuario",userDetails.getUsername());
        modelAndView.addObject("pagos",listaPagosResultantes);
        return modelAndView;
    }

    public ModelAndView buscarFecha(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        List<GastoComun>resultadoDesde=new ArrayList<GastoComun>();
        List<GastoComun>resultadoHasta=new ArrayList<GastoComun>();
        List <Pagar> listaPagosResultantes=new ArrayList<Pagar>();
        List<Pagar> listaPagos=new ArrayList<Pagar>();
        listaGastosComunesResultantes.clear();
        listaGastosComunes.clear();
        listaPagosResultantes.clear();
        resultadoDesde.clear();
        resultadoHasta.clear();
        listaPagos.clear();
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        usuario = userDetails.getUsername();
        listaPagos=serviceP.getPagos();
        for (Pagar Pagos:listaPagos) {
            if (Pagos.getUsername().equalsIgnoreCase(usuario)){
                listaPagosResultantes.add(Pagos);
            }
        }

        resultadoDesde=serviceGC.getGCFechaDesde(request.getParameter("date_desde"));
        resultadoHasta=serviceGC.getGCFechaHasta(request.getParameter("date_hasta"));

        for(GastoComun gc1: resultadoDesde){
            for(GastoComun gc2: resultadoHasta){
                if (gc1.getFecha().compareTo(gc2.getFecha())==0) {
                    listaGastosComunes.add(gc1);
                }
            }
        }

        for (Pagar listaPago:listaPagosResultantes) {
            for (GastoComun gastoComun: listaGastosComunes) {
                if (listaPago.getFecha().compareTo(gastoComun.getFecha())== 0){
                    listaGastosComunesResultantes.add(gastoComun);

                }
            }
        }

        ModelAndView modelAndView=new ModelAndView("usuarios/pantallaInicioMiembro");
        modelAndView.addObject("gastosComunes",listaGastosComunesResultantes);
        modelAndView.addObject("usuario",userDetails.getUsername());
        modelAndView.addObject("pagos",listaPagosResultantes);
        return modelAndView;

    }

    public ModelAndView VerGCAdmin(HttpServletRequest request,HttpServletResponse response) throws Exception {
        List <GastoComun> gcadmin=new ArrayList<GastoComun>();
        gcadmin.clear();
        List<Pagar> pgadmin=new ArrayList<Pagar>();
        pgadmin.clear();

        gcadmin=serviceGC.getGastosComunes();
        pgadmin=serviceP.getPagos();

        ModelAndView modelAndView=new ModelAndView("administradores/VerGCAdmin");
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        modelAndView.addObject("usuario",userDetails.getUsername());
        modelAndView.addObject("gastosComunes",gcadmin);
        modelAndView.addObject("pagos",pgadmin);
        return modelAndView;
    }

    public ModelAndView IngresoGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        ModelAndView modelAndView=new ModelAndView("administradores/IngresoGC");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView ingresoGCE(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        List<Users> listaUsuariosNormal=serviceU.getUsersUsuarioNormal();
        String montoS=request.getParameter("monto");
        int monto=Integer.valueOf(montoS);
        String fecha=request.getParameter("date");
        int cantidadUsuarios=serviceU.getTotalUsuariosNormales();
        Date fechaI=java.sql.Date.valueOf(fecha);
        serviceGC.insertGC(new GastoComun(fechaI,(monto/cantidadUsuarios)));
        for (Users user:listaUsuariosNormal) {
            serviceP.insertPago(new Pagar("pendiente de pago", fechaI, user.getUsername()));
        }

        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }


}
