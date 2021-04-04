/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.helpdesk.client.ModeloDatos.ModeloPrioridad;

import org.helpdesk.client.ServiciosRPC.RPCPrioridad;

/**
 *
 * @author Administrador
 */
public class RPCPrioridadImpl extends RemoteServiceServlet implements RPCPrioridad {
    public ModeloPrioridad getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_prioridad "+
                                                    "WHERE Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_prioridad "+
                                                    "ORDER BY Nombre,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public Boolean Agregar(String Nombre){
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_prioridad (Nombre) VALUES ('"+Nombre+"')");

        estado = true;
        
        conexion.cerrarConexion();
        
        return estado;
    }
    public Boolean Editar(ModeloPrioridad modelo) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_prioridad "+
                                    "SET Nombre = '"+modelo.getNombre()+"' "+
                                    "WHERE Id = '"+modelo.getId()+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_prioridad "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
}
