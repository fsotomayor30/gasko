package com.usuario.empresa.web.administracion.controladores;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.usuario.empresa.web.administracion.entidades.GastoComun;
import com.usuario.empresa.web.administracion.entidades.Pagar;
import com.usuario.empresa.web.administracion.entidades.TipoGasto;
import com.usuario.empresa.web.administracion.entidades.Users;
import com.usuario.empresa.web.administracion.servicios.GastoComunService;
import com.usuario.empresa.web.administracion.servicios.InicioService;
import com.usuario.empresa.web.administracion.servicios.PagarService;
import com.usuario.empresa.web.administracion.servicios.TipoGastoService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GastoComunController extends MultiActionController {


    List<GastoComun> listaGastosComunes;
    List<GastoComun> listaGastosComunesResultantes;

    private GastoComunService serviceGC = null;
    private TipoGastoService serviceTG = null;
    private PagarService serviceP = null;
    private InicioService serviceU=null;

    private ApplicationContext ctx = null;
    private String usuario;
    private Users objetoUsers = new Users();

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

        //se obtienen los tipos de gastos comunes
        List<TipoGasto> tipoGastosComunes = serviceTG.getTiposGastos();

        ModelAndView modelAndView=new ModelAndView("usuarios/pantallaInicioMiembro");
        modelAndView.addObject("gastosComunes", listaGastosComunes);
        modelAndView.addObject("usuario",userDetails.getUsername());
        modelAndView.addObject("tiposGastosComunes", tipoGastosComunes);
        modelAndView.addObject("pagos",listaPagosResultantes);
        return modelAndView;
    }

    public ModelAndView buscarFecha(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        objetoUsers = serviceU.getUsers(userDetails.getUsername());
        int rol = objetoUsers.getRol();

        List<Pagar> resultadoDesde = new ArrayList<Pagar>();
        List<Pagar> resultadoHasta = new ArrayList<Pagar>();

        List<Pagar> listaPagosResultantesPorUsuario = new ArrayList<Pagar>();
        List<Pagar> listaPagosResultantesEntreFechas = new ArrayList<Pagar>();
        List<Pagar> listaPagosResultantes = new ArrayList<Pagar>();
        List<Pagar> listaPagos = serviceP.getPagos();
        listaPagosResultantesPorUsuario.clear();
        listaGastosComunes.clear();
        listaGastosComunes = serviceGC.getGastosComunes();
        resultadoDesde.clear();
        resultadoHasta.clear();
        usuario = userDetails.getUsername();

        if (rol == 2) {
            listaPagosResultantesPorUsuario = serviceP.getPagos();
        } else {
            for (Pagar Pagos : listaPagos) {
                if (Pagos.getUsername().equalsIgnoreCase(usuario)) {
                    listaPagosResultantesPorUsuario.add(Pagos);
                }
            }
        }

        resultadoDesde = serviceP.getPagoFechaDesde(request.getParameter("date_desde"));
        resultadoHasta = serviceP.getPagoFechaHasta(request.getParameter("date_hasta"));

        //se obtienen los tipos de gastos comunes
        List<TipoGasto> tipoGastosComunes = serviceTG.getTiposGastos();


        for (Pagar p1 : resultadoDesde) {
            for (Pagar p2 : resultadoHasta) {
                if (p1.getId_pagar() == p2.getId_pagar()) {
                    listaPagosResultantesEntreFechas.add(p1);
                }
            }
        }

        for (Pagar listaPagosEntreFechas : listaPagosResultantesEntreFechas) {
            for (Pagar listaPagosPorUsuario : listaPagosResultantesPorUsuario) {
                if (listaPagosEntreFechas.getId_pagar() == listaPagosPorUsuario.getId_pagar()) {
                    listaPagosResultantes.add(listaPagosEntreFechas);

                }
            }
        }
        ModelAndView modelAndView;
        if (rol == 1) {
            modelAndView = new ModelAndView("usuarios/pantallaInicioMiembro");
        } else {
            modelAndView = new ModelAndView("administradores/VerGCAdmin");
        }

        modelAndView.addObject("gastosComunes", listaGastosComunes);
        modelAndView.addObject("usuario",userDetails.getUsername());
        modelAndView.addObject("pagos",listaPagosResultantes);
        modelAndView.addObject("tiposGastosComunes", tipoGastosComunes);
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
        String mesActual = "";

        java.util.Date fechaActual = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
        int mesActualInt = sqlDate.getMonth() + 1;

        switch (mesActualInt) {
            case 1:
                mesActual = "Enero";
                break;
            case 2:
                mesActual = "Febrero";
                break;
            case 3:
                mesActual = "Marzo";
                break;
            case 4:
                mesActual = "Abril";
                break;
            case 5:
                mesActual = "Mayo";
                break;
            case 6:
                mesActual = "Junio";
                break;
            case 7:
                mesActual = "Julio";
                break;
            case 8:
                mesActual = "Agosto";
                break;
            case 9:
                mesActual = "Septiembre";
                break;
            case 10:
                mesActual = "Octubre";
                break;
            case 11:
                mesActual = "Noviembre";
                break;
            case 12:
                mesActual = "Diciembre";
                break;

        }

        modelAndView.addObject("gastosComunes", gc);
        modelAndView.addObject("tiposGastos", tipoGastos);
        modelAndView.addObject("mes", mesActual);
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
        GastoComun gcAux = new GastoComun();
        int id = Integer.parseInt(request.getParameter("id"));
        List<GastoComun> list = serviceGC.getGastosComunes();
        for (GastoComun gc : list) {
            if (gc.getId() == id) {
                gcAux = gc;
            }
        }
        List<TipoGasto> tipoGastoList = serviceTG.getTiposGastos();
        String tipoGasto = "";
        for (TipoGasto tp : tipoGastoList) {
            if (tp.getId() == gcAux.getDescripcion()) {
                tipoGasto = tp.getDescripcion();
            }
        }
        ModelAndView modelAndView=new ModelAndView("administradores/modificarGC");
        modelAndView.addObject("gastoComun", gcAux);
        modelAndView.addObject("tipoGasto", tipoGasto);
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView modificarGCE(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        int id = Integer.parseInt(request.getParameter("id"));
        GastoComun gcAux = new GastoComun();
        List<GastoComun> gastoComunList = serviceGC.getGastosComunes();
        for (GastoComun gc : gastoComunList) {
            if (gc.getId() == id) {
                gcAux = gc;
            }
        }
        gcAux.setMonto((Integer.parseInt(request.getParameter("montoNuevo"))));
        gcAux.setDescripcion(Integer.parseInt(request.getParameter("descripcionNueva")));
        serviceGC.updateGC(gcAux);

        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        modelAndView.addObject("usuario",userDetails.getUsername());
        return modelAndView;
    }

    public ModelAndView eliminarGC(HttpServletRequest request,HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        serviceGC.deleteGC(id);
        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) auth.getPrincipal();
        modelAndView.addObject("usuario",userDetails.getUsername());

        return modelAndView;
    }

    public ModelAndView ExportarExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        /* Creamos el documento y la primera hoja(Clientes) */
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Clientes");

        /* Configuramos ancho columna 1, las otras ya quedan bien por defecto */
        sheet.setColumnWidth((short) 0, (short) 7000);
        sheet.setColumnWidth((short) 1, (short) 7000);
        sheet.setColumnWidth((short) 2, (short) 7000);
        sheet.setColumnWidth((short) 3, (short) 7000);
        sheet.setColumnWidth((short) 4, (short) 7000);

        /* Configuramos  los estilos */
        HSSFFont bold = workbook.createFont();
        HSSFFont headerFont = workbook.createFont();;
        bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle styleBold = workbook.createCellStyle();
        HSSFCellStyle styleHeader = workbook.createCellStyle();
        HSSFCellStyle filaPar = workbook.createCellStyle();
        HSSFCellStyle filaImpar = workbook.createCellStyle();
        styleBold.setFont(bold);
        headerFont.setColor(HSSFColor.WHITE.index);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //estilo de la cabecera
        styleHeader.setFont(headerFont);
        styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeader.setFillForegroundColor(HSSFColor.BLUE.index);

        styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        styleHeader.setTopBorderColor(HSSFColor.WHITE.index);
        styleHeader.setLeftBorderColor(HSSFColor.WHITE.index);
        styleHeader.setRightBorderColor(HSSFColor.WHITE.index);
        styleHeader.setBottomBorderColor(HSSFColor.WHITE.index);

        //estilo de las filas pares
        filaPar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        filaPar.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        filaPar.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        //estilo de las filas impares
        filaImpar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        filaImpar.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        filaImpar.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);

        /* Definimos los datos a guardar */
        Vector<Object[]> data = new Vector<Object[]>();

        data.add( new Object[] {"NOMBRE DEL MIEMBRO",  "ESTADO", "MONTO", "FECHA GENERACION PAGO", "TIPO"} );

        /*Creamos y llenamos listas con datos de la BD que extraemos gracias a los servicios*/
        List <GastoComun> gcadmin=new ArrayList<GastoComun>();
        //Limpio la lista para que si salgo de la pantalla "Visualizar cuenta de gasto com�n" y luego vuelvo a entrar, no se dupliquen los datos de la lista gcadmin
        gcadmin.clear();
        List<Pagar> pgadmin=new ArrayList<Pagar>();
        //Limpio la lista para que si salgo de la pantalla "Visualizar cuenta de gasto com�n" y luego vuelvo a entrar, no se dupliquen los datos de la lista pgadmin
        pgadmin.clear();

        //lleno ambas listas con la lista entregada por cada servicio
        gcadmin=serviceGC.getGastosComunes();
        pgadmin = serviceP.getPagos();
        //Se obtienen los tipos de gastos comunes
        List<TipoGasto> tipoGastos = serviceTG.getTiposGastos();

        for(int i = 0; i<pgadmin.size(); i++) {
            String descripcion ="";
            for (int j = 0; j < gcadmin.size(); j++) {
                if(gcadmin.get(j).getFecha().getMonth() == pgadmin.get(i).getFecha().getMonth()) {
                    for(int k = 0; k<tipoGastos.size(); k++) {
                        if(gcadmin.get(j).getDescripcion()==tipoGastos.get(k).getId()) {
                            descripcion = descripcion + tipoGastos.get(k).getDescripcion();
                        }

                    }
                }
            }
            data.add( new Object[] {pgadmin.get(i).getUsername(),pgadmin.get(i).getEstado(), pgadmin.get(i).getMonto(),pgadmin.get(i).getFecha().toString(),descripcion} );
        }

        /* Guardamos los datos en el documento */
        int rownum = 0;
        for (Object [] objArr : data) {
            HSSFRow row = sheet.createRow(rownum++);

            short cellnum = 0;
            for (Object obj : objArr) {
                HSSFCell cell = row.createCell(cellnum++);
                if ( row.getRowNum()==0 ) cell.setCellStyle( styleHeader );
                if ( row.getRowNum()%2==0 && row.getRowNum()!=0) cell.setCellStyle( filaPar );
                if ( row.getRowNum()%2!=0 && row.getRowNum()!=0) cell.setCellStyle( filaImpar );
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else
                    cell.setCellValue(""+obj);
            }
        }

        /* Guardamos el archivo, en este caso lo devolvemos por un servlet */
        response.setContentType("application/excel");
        response.addHeader("Content-disposition", "inline; filename=" + URLEncoder.encode("Informe_de_gastos_comunes.xls", "UTF-8"));
        OutputStream os = response.getOutputStream();

        workbook.write(os);

        os.flush();
        os.close();


        ModelAndView modelAndView=new ModelAndView("indexAdmin");
        return modelAndView;
    }

    public ModelAndView ExportarPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();

        /*Creamos y llenamos listas con datos de la BD que extraemos gracias a los servicios*/
        List<GastoComun> gcadmin = new ArrayList<GastoComun>();
        //Limpio la lista para que si salgo de la pantalla "Visualizar cuenta de gasto com�n" y luego vuelvo a entrar, no se dupliquen los datos de la lista gcadmin
        gcadmin.clear();
        List<Pagar> pgadmin = new ArrayList<Pagar>();
        //Limpio la lista para que si salgo de la pantalla "Visualizar cuenta de gasto com�n" y luego vuelvo a entrar, no se dupliquen los datos de la lista pgadmin
        pgadmin.clear();

        //lleno ambas listas con la lista entregada por cada servicio
        gcadmin = serviceGC.getGastosComunes();
        pgadmin = serviceP.getPagos();
        //Se obtienen los tipos de gastos comunes
        List<TipoGasto> tipoGastos = serviceTG.getTiposGastos();

        String documento = "";
        Integer numColumns = 5;

        // We create the table (Creamos la tabla).
        PdfPTable table = new PdfPTable(numColumns);
        // Now we fill the PDF table
        // Ahora llenamos la tabla del PDF
        PdfPCell columnHeader;
        // Fill table rows (rellenamos las filas de la tabla).

        columnHeader = new PdfPCell(new Phrase("USUARIO "));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        columnHeader = new PdfPCell(new Phrase("ESTADO "));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        columnHeader = new PdfPCell(new Phrase("MONTO "));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        columnHeader = new PdfPCell(new Phrase("FECHA "));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        columnHeader = new PdfPCell(new Phrase("DESCRIPCION "));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(columnHeader);

        table.setHeaderRows(1);

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            for (Pagar pago : pgadmin) {

                String descripcion = "";
                for (GastoComun gc : gcadmin) {
                    if (pago.getFecha().getMonth() == gc.getFecha().getMonth()) {
                        for (TipoGasto tp : tipoGastos) {
                            if (gc.getDescripcion() == tp.getId()) {
                                descripcion = descripcion + " " + tp.getDescripcion();
                            }

                        }
                    }
                }
                table.addCell(pago.getUsername());
                table.addCell(pago.getEstado());
                table.addCell("" + pago.getMonto());
                table.addCell(pago.getFecha().toString());
                table.addCell(descripcion);
            }
            document.addTitle("ESTADOS DE PAGO");
            document.add(new Paragraph("ESTADOS DE PAGO"));
            document.add(new Paragraph("                      "));
            document.add(new LineSeparator());
            document.add(new Paragraph("                      "));
            document.add(table);
            document.close();

        } catch (DocumentException exc) {
            throw new IOException(exc.getMessage());
        } finally {
            out.close();
        }
        ModelAndView modelAndView = new ModelAndView("indexAdmin");
        return modelAndView;
    }
}
