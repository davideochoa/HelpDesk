/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatos.ModeloArea;
import org.helpdesk.client.ModeloDatosNuevo.BMMDArea;
import org.helpdesk.client.ServiciosRPC.RPCArea;

/**
 *
 * @author Administrador
 */
public class RPCAreaImpl extends RemoteServiceServlet implements RPCArea {
    @Override
    public ModeloArea getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,IdUnidad,Nombre FROM dbo.catalogo_areas "+
                                                    "WHERE Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,IdUnidad,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,IdUnidad,Nombre FROM dbo.catalogo_areas "+
                                                    "ORDER BY Nombre,IdUnidad,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    @Override
    public String[][] getListado(int IdUnidad,String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT Id,IdUnidad,Nombre FROM dbo.catalogo_areas "+
                                                    "WHERE IdUnidad = "+IdUnidad+" "+
                                                    "AND Nombre like '%"+valor+"%' "+
                                                    "ORDER BY Nombre,IdUnidad,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT Id,IdUnidad,Nombre FROM dbo.catalogo_areas "+
                                                    "WHERE IdUnidad = "+IdUnidad+" "+
                                                    "ORDER BY Nombre,IdUnidad,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    
    @Override
    public BMMDArea[] getListado2(String valor) {
        BMMDArea Unidades[] = new BMMDArea[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";
        
        if(valor.length() > 0)
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_areas "+
                        "WHERE Nombre like '%"+valor+"%' "+
                        "ORDER BY Nombre,Id";        
        else
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_areas "+
                        "ORDER BY Nombre,Id";
        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Unidades = new BMMDArea[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Unidades[posy] = new BMMDArea(resultset.getInt("Id"),resultset.getString("Nombre"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCUnidadImpl:getListado2()");
            Unidades= new BMMDArea[0];
        }
        conexion.cerrarConexion();

        return Unidades;
    }
    @Override
    public BMMDArea[] getListado2(int IdUnidad,String valor) {
        BMMDArea Unidades[] = new BMMDArea[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";
        
        if(valor.length() > 0)
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_areas "+
                        "WHERE IdUnidad = "+IdUnidad+" "+
                        "AND Nombre like '%"+valor+"%' "+
                        "ORDER BY Nombre,Id";        
        else
            cadena = "SELECT Id,Nombre FROM dbo.catalogo_areas "+
                        "WHERE IdUnidad = "+IdUnidad+" "+
                        "ORDER BY Nombre,IdUnidad,Id";
        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Unidades = new BMMDArea[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Unidades[posy] = new BMMDArea(resultset.getInt("Id"),resultset.getString("Nombre"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCUnidadImpl:getListado2()");
            Unidades= new BMMDArea[0];
        }
        conexion.cerrarConexion();

        return Unidades;
    }
    
    @Override
    public Boolean Agregar(int IdUnidad,String NombreUnidad){
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_areas (IdUnidad,Nombre) VALUES ('"+IdUnidad+"','"+NombreUnidad+"')");

        estado = true;
        
        conexion.cerrarConexion();
        return estado;
    }
    @Override
    public Boolean Editar(ModeloArea modelo) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_areas "+
                                    "SET Nombre = '"+modelo.getNombre()+"' "+
                                    "WHERE Id = '"+modelo.getId()+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    @Override
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_areas "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
}
