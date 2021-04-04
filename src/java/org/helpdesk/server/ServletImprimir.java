/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helpdesk.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
//@WebServlet(name = "ServletImprimir", urlPatterns = {"/ServletImprimir"})
public class ServletImprimir extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{        
        CONEXION conexion = CONEXION.conectar_bd();
        ResultSet resultSet = conexion.resultado;
        Statement statement = conexion.sentencia;
        
        Integer Id = ((Integer)req.getSession().getAttribute("Id"));
        String NombreArchivo = ((String)req.getSession().getAttribute("NombreArchivo"));        
        
        String ContentType = "";
        byte[] fichero = new byte[0];// = JasperRunManager.runReportToPdf (jasperReport, parameters, conexion.conexion);
         try {
             resultSet = statement.executeQuery("SELECT ContentType,Archivo FROM Archivos WHERE Id = '"+Id+"'");
             //System.out.println("SENTENCIA:SELECT ContentType,Archivo FROM Archivos WHERE Id = '"+Id+"'");
             
             if(resultSet.next()){
                 ContentType = resultSet.getString("ContentType");
                 fichero = resultSet.getBytes("Archivo");
             }
             else{
                 fichero = new byte[0];
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServletImprimir.class.getName()).log(Level.SEVERE, null, ex);
         }

        response.setContentType (ContentType);
        response.setHeader ("Content-disposition", "inline; filename="+NombreArchivo);
        response.setHeader ("Cache-Control", "max-age=30");
        response.setHeader ("Pragma", "No-cache");
        response.setDateHeader ("Expires", 0);
        response.setContentLength (fichero.length);

        ServletOutputStream out = response.getOutputStream ();
        out.write (fichero, 0, fichero.length);
        out.flush ();
        out.close ();  
        
        conexion.cerrarConexion();
    }
}
