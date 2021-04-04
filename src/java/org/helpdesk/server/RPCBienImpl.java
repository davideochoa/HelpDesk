/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatos.ModeloBien;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoBien;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoIncidencia;
import org.helpdesk.client.ServiciosRPC.RPCBien;

/**
 *
 * @author Administrador
 */
public class RPCBienImpl extends RemoteServiceServlet implements RPCBien {
    public ModeloBien getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,IdTipoIncidencia,Nombre FROM dbo.catalogo_bien "+
                                                    "WHERE Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,IdTipoIncidencia,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,IdTipoIncidencia,Nombre FROM dbo.catalogo_bien "+
                                                    "ORDER BY Nombre,IdTipoIncidencia,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public String[][] getListado(int IdTipoIncidencia,String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,IdTipoIncidencia,Nombre FROM dbo.catalogo_bien "+
                                                    "WHERE IdTipoIncidencia = "+IdTipoIncidencia+" "+
                                                    "AND Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,IdTipoIncidencia,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,IdTipoIncidencia,Nombre FROM dbo.catalogo_bien "+
                                                    "WHERE IdTipoIncidencia = "+IdTipoIncidencia+" "+
                                                    "ORDER BY Nombre,IdTipoIncidencia,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public BMMDTipoBien[] getListado2(int IdTipoIncidencia,String valor) {
        BMMDTipoBien[] TipoBien = new BMMDTipoBien[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_bien "+
                        "WHERE IdTipoIncidencia = "+IdTipoIncidencia+" "+
                        "AND Nombre like '%"+valor+"%' "+
                        "ORDER BY Nombre,IdTipoIncidencia,Id";
        }
        else{
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_bien "+
                        "WHERE IdTipoIncidencia = "+IdTipoIncidencia+" "+
                        "ORDER BY Nombre,IdTipoIncidencia,Id";
        }
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            TipoBien = new BMMDTipoBien[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                TipoBien[posy] = new BMMDTipoBien(resultset.getInt("Id"),resultset.getString("Nombre"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCTipoIncidenciaImpl:getListado2()");
            TipoBien = new BMMDTipoBien[0];
        }
        

        conexion.cerrarConexion();

        return TipoBien;
    }
    public Boolean Agregar(int IdTipoIncidencia,String Nombre){
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_bien (IdTipoIncidencia,Nombre) VALUES ('"+IdTipoIncidencia+"','"+Nombre+"')");

        estado = true;
        conexion.cerrarConexion();
        return estado;
    }
    public Boolean Editar(ModeloBien modelo) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_bien "+
                                    "SET Nombre = '"+modelo.getNombre()+"' "+
                                    "WHERE Id = '"+modelo.getId()+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_bien "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
}
