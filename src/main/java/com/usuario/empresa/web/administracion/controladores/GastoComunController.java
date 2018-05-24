package com.usuario.empresa.web.administracion.controladores;

import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.servicios.GastoComunService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GastoComunController extends MultiActionController {

    List<GastoComun> listaGastosComunes;
    private GastoComunService service = null;
    private ApplicationContext ctx = null;

    /**
     * constructor
     */
    public GastoComunController() {

        listaGastosComunes = new ArrayList<GastoComun>();
        ctx = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext.xml");
        service = (GastoComunService) ctx.getBean("gastosComunesService");
    }

    /**
     * M�todo que lista los gastosComunes
     *
     * @param request
     * @param response
     * @return Lista de Usuarios
     * @throws Exception
     */
    public ModelAndView pantallaInicioMiembro(HttpServletRequest request,HttpServletResponse response) throws Exception {

        List<GastoComun> listaGastosComunes = service.getGastosComunes();
        return new ModelAndView("usuarios/pantallaInicioMiembro", "gastosComunes", listaGastosComunes);
    }


}
