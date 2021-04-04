/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUnidad;
import org.helpdesk.client.ServiciosRPC.RPCUnidad;

/**
 *
 * @author Administrador
 */
public class RPCUnidadImpl extends RemoteServiceServlet implements RPCUnidad {
    public ModeloUnidad getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];
        
        if(valor == null)
            valor = new String();
        
        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_unidades "+
                                                    "WHERE Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre FROM dbo.catalogo_unidades "+
                                                    "ORDER BY Nombre,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public BMMDUnidad[] getListado2(String valor) {
        BMMDUnidad Unidades[] = new BMMDUnidad[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";
        
        if(valor.length() > 0)
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_unidades "+
                        "WHERE Nombre like '%"+valor+"%' "+
                        "ORDER BY Nombre,Id";        
        else
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_unidades "+
                        "ORDER BY Nombre,Id";
        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Unidades = new BMMDUnidad[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Unidades[posy] = new BMMDUnidad(resultset.getInt("Id"),resultset.getString("Nombre"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCUnidadImpl:getListado2()");
            Unidades= new BMMDUnidad[0];
        }
        conexion.cerrarConexion();

        return Unidades;
    }
    public Boolean Agregar(String NombreUnidad){
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_unidades (Nombre) VALUES ('"+NombreUnidad+"')");

        estado = true;
        
        conexion.cerrarConexion();
        
        return estado;
    }
    public Boolean Editar(ModeloUnidad unidad) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_unidades "+
                                    "SET Nombre = '"+unidad.getNombre()+"' "+
                                    "WHERE Id = '"+unidad.getId()+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_unidades "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
}
