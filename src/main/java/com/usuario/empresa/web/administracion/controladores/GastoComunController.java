package com.usuario.empresa.web.administracion.controladores;

import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.entidades.Pagar;
import com.usuario.empresa.web.administracion.entidades.TipoGasto;
import com.usuario.empresa.web.administracion.entidades.Users;
import com.usuario.empresa.web.administracion.servicios.GastoComunService;
import com.usuario.empresa.web.administracion.servicios.InicioService;
import com.usuario.empresa.web.administracion.servicios.PagarService;
import com.usuario.empresa.web.administracion.servicios.TipoGastoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class GastoComunController extends MultiActionController {

    List<GastoComun> listaGastosComunes;
    List<GastoComun> listaGastosComunesResultantes;

    private GastoComunService serviceGC = null;
    private TipoGastoService serviceTG = null;
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
        serviceTG = (TipoGastoService) ctx.getBean("tipoGastoService");
    }


    public ModelAndView pantallaInicioMiembro(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //se crea una lista de los pagos
        List <Pagar> listaPagosResultantes=new ArrayList<Pagar>();

        //Se obtienen los datos del usuario el sesion para mostrar sus gc
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        usuario = userDetails.getUsername();
        List<Pagar> listaPagos=serviceP.getPagos();
        for (Pagar Pagos:listaPagos) {
            if (Pagos.getUsername().equalsIgnoreCase(usuario)){
                listaPagosResultantes.add(Pagos);
            }
        }

        //Se obtienen los gastso comunes
        listaGastosComunes=serviceGC.getGastosComunes();


        ModelAndView modelAndView=new ModelAndView("usuarios/pantallaInicioMiembro");
        modelAndView.addObject("gastosComunes", listaGastosComunes);
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

    //Creo y cargo dos listas, una con objetos de la clase GastoComun y otra con objetos de la clase Pagar, tambien obtengo el username que se utilizo para iniciar sesion. Luego envio los datos mencionados anteriormente a la vista VerGCAdmin
    public ModelAndView VerGCAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List <GastoComun> gcadmin=new ArrayList<GastoComun>();
        //Limpio la lista para que si salgo de la pantalla "Visualizar cuenta de gasto com�n" y luego vuelvo a entrar, no se dupliquen los datos de la lista gcadmin
        gcadmin.clear();
        List<Pagar> pgadmin=new ArrayList<Pagar>();
        //Limpio la lista para que si salgo de la pantalla "Visualizar cuenta de gasto com�n" y luego vuelvo a entrar, no se dupliquen los datos de la lista pgadmin
        pgadmin.clear();

        //lleno ambas listas con la lista entregada por cada servicio
        gcadmin=serviceGC.getGastosComunes();
        pgadmin=serviceP.getPagos();

        //defino la vista VerGCAdmin para entregarle las listas de gastos comunes y pagos posteriormente
        ModelAndView modelAndView=new ModelAndView("administradores/VerGCAdmin");

        //Se obtienen los tipos de gastos comunes
        List<TipoGasto> tipoGastos = serviceTG.getTiposGastos();

        //obtengo el username con el cual inicio sesion
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        //le paso el username a la vista VerGCAdmin para que pueda utilizarlo
        modelAndView.addObject("usuario",userDetails.getUsername());
        //le paso ambas listas a la vista VerGCAdmin para que pueda utilizarlas 
        modelAndView.addObject("gastosComunes",gcadmin);
        //le paso a la vista los tipos de gastos comunes
        modelAndView.addObject("tiposGastosComunes", tipoGastos);
        modelAndView.addObject("pagos",pgadmin);
        return modelAndView;
    }

    public ModelAndView IngresoGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //Se obtienen los datos del usuario que esta en sesion
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();

        //Se obtienen el listado de gastos comunes
        List<GastoComun> gc = serviceGC.getGastosComunes();

        //Se obtienen los tipos de gastos
        List<TipoGasto> tipoGastos = serviceTG.getTiposGastos();

        ModelAndView modelAndView=new ModelAndView("administradores/IngresoGC");
        modelAndView.addObject("gastosComunes", gc);
        modelAndView.addObject("tiposGastos", tipoGastos);
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView ingresoGCE(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //Se obtienen los datos del usuario en sesion
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();

        //obtengo los datos del formulario
        String montoS=request.getParameter("monto");
        int monto=Integer.valueOf(montoS);
        String fecha=request.getParameter("date");
        int cantidadUsuarios=serviceU.getTotalUsuariosNormales();
        Date fechaI=java.sql.Date.valueOf(fecha);
        String descipcion=request.getParameter("descripcion");

        //Se crea un gasto comun y se ingresa
        GastoComun gc = new GastoComun();
        gc.setFecha(fechaI);
        gc.setMonto(monto);
        gc.setDescripcion(Integer.parseInt(request.getParameter("descripcion")));
        serviceGC.insertGC(gc);

        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;

    }

    public ModelAndView modificarGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();

        String fecha=request.getParameter("fecha");
        Date fechaBusqueda=Date.valueOf(fecha);
        GastoComun gc=serviceGC.getGastoComun(fechaBusqueda);

        ModelAndView modelAndView=new ModelAndView("administradores/modificarGC");
        modelAndView.addObject("gastoComun",gc);
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView modificarGCE(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();

        int cantidadUsuarios=serviceU.getTotalUsuariosNormales();

        String fecha=request.getParameter("date");
        Date fechaBusqueda=Date.valueOf(fecha);
        GastoComun gc=serviceGC.getGastoComun(fechaBusqueda);
        gc.setMonto((Integer.parseInt(request.getParameter("montoNuevo")))/cantidadUsuarios);
        gc.setDescripcion(Integer.parseInt(request.getParameter("descripcionNueva")));
        serviceGC.updateGC(gc);

        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView eliminarGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String fecha=request.getParameter("fecha");
        Date fechaBusqueda=Date.valueOf(fecha);
        GastoComun gc=serviceGC.getGastoComun(fechaBusqueda);
        String username=request.getParameter("username");
        List<Pagar> listaPagos=serviceP.getPagos();
        for (Pagar pagos:listaPagos) {
            if (pagos.getFecha().compareTo(gc.getFecha())==0 && pagos.getUsername().equals(username)) {
                serviceP.deletePago(pagos);
            }
        }
        serviceGC.deleteGC(fecha);
        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        modelAndView.addObject("usuario",userDetails.getUsername());

        return modelAndView;
    }
}
