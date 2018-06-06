package com.usuario.empresa.web.administracion.controladores;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorController extends MultiActionController {

    public ModelAndView modificarGC(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView=new ModelAndView("administradores/modificarGC");
        return modelAndView;
    }
}
