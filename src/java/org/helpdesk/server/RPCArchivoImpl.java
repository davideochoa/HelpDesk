/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.helpdesk.client.ModeloDatos.DataModelGridArchivos;
import org.helpdesk.client.ServiciosRPC.RPCArchivo;

/**
 *
 * @author Administrador
 */
public class RPCArchivoImpl extends RemoteServiceServlet implements RPCArchivo {
    
    @Override
    protected void service(final HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        CONEXION conexion = CONEXION.conectar_bd();
        
	boolean isMultiPart = ServletFileUpload.isMultipartContent(new ServletRequestContext(request));
 
	if(isMultiPart) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
                int Folio = 0;                
                Cookie[] ck = request.getCookies();
                int pos = 0;
                while(pos < ck.length){                    
                    //System.out.println("Name:"+ck[pos].getName()+" VALUE:"+ck[pos].getValue());
                    if(ck[pos].getName().equals("Folio")){
                        Folio = Integer.parseInt(ck[pos].getValue());
                    }
                    pos++;
                }
                
		try {
			@SuppressWarnings("unchecked")
			List items = upload.parseRequest(request);
                        
                        
			FileItem uploadedFileItem = (FileItem) items.get(0); // we only upload one file
                        
                        byte Archivo[] = uploadedFileItem.get();
			if(uploadedFileItem == null && uploadedFileItem.get().length == 0) {                                
				super.service(request, response);
				return;
			} 
                        else{ 
                            String fileName = uploadedFileItem.getName();
                            String ContentType = uploadedFileItem.getContentType();
                            try {
                                    conexion.conexion.setAutoCommit(false);
                                } catch (SQLException ex) {}
                                String Sentencia = "INSERT INTO Archivos (Folio,Nombre,ContentType,Archivo) "+
                                                    "VALUES (?,?,?,?)";
                                PreparedStatement SentenciaPreparada = conexion.getSentenciaPreparada(Sentencia);
                                try {                          
                                    SentenciaPreparada.setInt(1,Folio);
                                    SentenciaPreparada.setString(2, fileName);
                                    SentenciaPreparada.setString(3, ContentType);
                                    SentenciaPreparada.setBytes(4, uploadedFileItem.get());
                                    SentenciaPreparada.executeUpdate();
                                    conexion.conexion.setAutoCommit(true);
                                } catch (SQLException ex) {} 
                                
                            //if(uploadedFileItem.getFieldName().equalsIgnoreCase("uploadFormElement")) {
				//fileName = uploadedFileItem.getName();
                                //System.out.println("uploadFormElement");
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.getWriter().print("OK");                                    
                                //response.getWriter().print(uploadedFileItem.get());
				response.flushBuffer();
                            //}
                        }
 
		} 
                catch (org.apache.commons.fileupload.FileUploadException ex) {
                Logger.getLogger(RPCArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
 
	else {
		super.service(request, response);
		
	}
        
        conexion.cerrarConexion();
        return;
    }
    /*
    protected void service2(final HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        CONEXION conexion = CONEXION.conectar_bd();
        
        int Folio = 0;                
        Cookie[] ck = request.getCookies();
        int pos = 0;
        while(pos < ck.length){                    
            //System.out.println("Name:"+ck[pos].getName()+" VALUE:"+ck[pos].getValue());
            if(ck[pos].getName().equals("Folio")){
                Folio = Integer.parseInt(ck[pos].getValue());
            }
            pos++;
        }
        
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Parse the request
            List<org.apache.commons.fileupload.FileItem> items = upload.parseRequest(request);
            
            FileItem uploadedFileItem = (FileItem) items.get(0); // we only upload one file
            
            if(uploadedFileItem != null && uploadedFileItem.get().length >0){
                byte Archivo[] = uploadedFileItem.get();
                
                String fileName = uploadedFileItem.getName();
                String ContentType = uploadedFileItem.getContentType();
                try {
                        conexion.conexion.setAutoCommit(false);
                    } catch (SQLException ex) {}
                    String Sentencia = "INSERT INTO Archivos (Folio,Nombre,ContentType,Archivo) "+
                                        "VALUES (?,?,?,?)";
                    PreparedStatement SentenciaPreparada = conexion.getSentenciaPreparada(Sentencia);
                    try { 
                        SentenciaPreparada.setInt(1,Folio);
                        SentenciaPreparada.setString(2, fileName);
                        SentenciaPreparada.setString(3, ContentType);
                        SentenciaPreparada.setBytes(4, uploadedFileItem.get());
                        SentenciaPreparada.executeUpdate();
                        conexion.conexion.setAutoCommit(true);
                        
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        response.getWriter().print("OK"); 
                        response.flushBuffer();
                    } catch (SQLException ex) {
                    
                    } 
            }//fin if
        } 
        catch (org.apache.commons.fileupload.FileUploadException ex) {
            Logger.getLogger(RPCArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
	
        conexion.cerrarConexion();
        return;
    }
    */
    public DataModelGridArchivos[] listado(int Folio){
        DataModelGridArchivos archivos[] = new DataModelGridArchivos[0];
        
        CONEXION conexion = CONEXION.conectar_bd();
        ResultSet resultSet = conexion.resultado;
        Statement statement = conexion.sentencia;
        
        int Renglones = 0;
        
        try {
            //System.out.println("SELECT Id,Nombre FROM Archivos WHERE Folio = '"+Folio+"'");
            resultSet = statement.executeQuery("SELECT Id,Nombre FROM Archivos WHERE Folio = '"+Folio+"'");
            
            while(resultSet.next())
                Renglones++;
            
            resultSet.beforeFirst();
            
            if(Renglones > 0){
                archivos = new DataModelGridArchivos[Renglones];
                int pos = 0;
                while(pos < Renglones){
                    resultSet.next();                    
                    archivos[pos] = new DataModelGridArchivos(resultSet.getInt("Id"),resultSet.getString("Nombre"));
                    //System.out.println(archivos[pos].getNombre());
                    pos++;
                }
            }            
        } catch (SQLException ex) {}
        
        conexion.cerrarConexion();
        
        return archivos;
    }
    public void imprimirArchivo(Integer Id,String NombreArchivo) {
        this.getThreadLocalRequest().getSession().setAttribute("Id",Id);
        this.getThreadLocalRequest().getSession().setAttribute("NombreArchivo",NombreArchivo);
    }
}
