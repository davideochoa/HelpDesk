/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Administrador
 */
public class ServletRPTIncidencia extends HttpServlet {
@Override
    public void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{
        Integer Folio = ((Integer)req.getSession().getAttribute("Folio"));

        CONEXION conexion = CONEXION.conectar_bd();

        try{
           //Map parameters = new HashMap ();
           Map<String, Integer> parameters = new HashMap<String, Integer>();           
           JasperReport jasperReport  = (JasperReport)JRLoader.loadObject("C:/reportes/HelpDeskRPTIncidencia.jasper");
           parameters.put("Folio", Folio);
           
           byte[] fichero = JasperRunManager.runReportToPdf (jasperReport, parameters, conexion.conexion);

           response.setContentType ("application/pdf");
           response.setHeader ("Content-disposition", "inline; filename=HelpDeskRPTIncidencia.pdf");
           response.setHeader ("Cache-Control", "max-age=30");
           response.setHeader ("Pragma", "No-cache");
           response.setDateHeader ("Expires", 0);
           response.setContentLength (fichero.length);

           ServletOutputStream out = response.getOutputStream ();
           out.write (fichero, 0, fichero.length);
           out.flush ();
           out.close ();
       }//fin try
        catch(JRException error_jasper){
            System.out.println(error_jasper.toString());
            System.out.println(error_jasper.getMessage());
            System.out.println(error_jasper.getLocalizedMessage());
        }//fin catch

        conexion.cerrarConexion();
    }
}
