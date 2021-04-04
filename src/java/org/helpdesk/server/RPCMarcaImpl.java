/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatosNuevo.BMMDDocReferencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDMarca;

import org.helpdesk.client.ServiciosRPC.RPCMarca;

/**
 *
 * @author Administrador
 */
public class RPCMarcaImpl extends RemoteServiceServlet implements RPCMarca {
    public String[][] getListado(){
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        obj = conexion.ObtenerArregloString("SELECT Marca "+
                                            "FROM dbo.concentrado_folios_incidencias "+
                                            "WHERE Marca != 'NULL' "+
                                            "AND Marca != '' "+
                                            "GROUP BY Marca "+
                                            "ORDER BY Marca ASC");

        conexion.cerrarConexion();

        return obj;
    }
    public BMMDMarca[] getListado2(int IdTipoIncidencia,int IdTipoBien,String cadenaBusqueda){
        BMMDMarca[] Marcas = new BMMDMarca[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";
        
        if(cadenaBusqueda.length() > 0){
            cadena = "SELECT Marca "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE IdTipoIncidencia = '"+IdTipoIncidencia+"' "+
                        "AND IdBien = '"+IdTipoBien+"' "+
                        "AND Marca != 'NULL' "+
                        "AND Marca != '' "+
                        "AND Marca LIKE '%"+cadenaBusqueda+"%' "+
                        "AND Marca LIKE '%"+cadenaBusqueda.toUpperCase()+"%' "+
                        "GROUP BY Marca "+
                        "ORDER BY Marca ASC";
        }            
        else{
            cadena = "SELECT Marca "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE IdTipoIncidencia = '"+IdTipoIncidencia+"' "+
                        "AND IdBien = '"+IdTipoBien+"' "+
                        "AND Marca != 'NULL' "+
                        "AND Marca != '' "+
                        "GROUP BY Marca "+
                        "ORDER BY Marca ASC";
        }
        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Marcas = new BMMDMarca[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Marcas[posy] = new BMMDMarca(resultset.getString("Marca"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCMarcaImpl:getListado2()");
            Marcas= new BMMDMarca[0];
        }

        conexion.cerrarConexion();

        return Marcas;
    }
}
