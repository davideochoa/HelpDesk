/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.helpdesk.client.ModeloDatosNuevo.BMMDArea;

import org.helpdesk.client.ServiciosRPC.RPCAreaSoporte;

/**
 *
 * @author Administrador
 */
public class RPCAreaSoporteImpl extends RemoteServiceServlet implements RPCAreaSoporte {
    public String[] getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_area_soporte "+
                                                    "WHERE Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_area_soporte "+
                                                    "ORDER BY Nombre,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public Boolean Agregar(String Nombre){
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_area_soporte (Nombre) VALUES ('"+Nombre+"')");

        estado = true;
        
        conexion.cerrarConexion();
        
        return estado;
    }
    public Boolean Editar(int id,String nombre) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_area_soporte "+
                                    "SET Nombre = '"+nombre+"' "+
                                    "WHERE Id = '"+id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_area_soporte "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
}
