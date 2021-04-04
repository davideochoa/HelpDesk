/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatos.ModeloTipoIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoIncidencia;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidencia;

/**
 *
 * @author Administrador
 */
public class RPCTipoIncidenciaImpl extends RemoteServiceServlet implements RPCTipoIncidencia {
    public ModeloTipoIncidencia getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_tipo_incidencias "+
                                                    "WHERE Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_tipo_incidencias "+
                                                    "ORDER BY Nombre,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public BMMDTipoIncidencia[] getListado2(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        BMMDTipoIncidencia[] ArrayTipoIncidencia = new BMMDTipoIncidencia[0];
        
        String cadena = "";
        
        if(valor.length() > 0)
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_tipo_incidencias "+
                        "WHERE Nombre like '%"+valor+"%' "+
                        "ORDER BY Nombre,Id";        
        else
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_tipo_incidencias "+
                        "ORDER BY Nombre,Id";

        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            ArrayTipoIncidencia = new BMMDTipoIncidencia[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                ArrayTipoIncidencia[posy] = new BMMDTipoIncidencia(resultset.getInt("Id"),resultset.getString("Nombre"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCTipoIncidenciaImpl:getListado2()");
            ArrayTipoIncidencia = new BMMDTipoIncidencia[0];
        }

        conexion.cerrarConexion();

        return ArrayTipoIncidencia;
    }
    public Boolean Agregar(String Nombre){
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_tipo_incidencias (Nombre) VALUES ('"+Nombre+"')");

        estado = true;
        
        conexion.cerrarConexion();
        
        return estado;
    }
    public Boolean Editar(ModeloTipoIncidencia modelo) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_tipo_incidencias "+
                                    "SET Nombre = '"+modelo.getNombre()+"' "+
                                    "WHERE Id = '"+modelo.getId()+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_tipo_incidencias "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
}
