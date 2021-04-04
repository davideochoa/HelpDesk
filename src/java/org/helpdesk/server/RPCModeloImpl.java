/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatosNuevo.BMMDModelo;
import org.helpdesk.client.ServiciosRPC.RPCModelo;

/**
 *
 * @author Administrador
 */
public class RPCModeloImpl extends RemoteServiceServlet implements RPCModelo {
    public String[][] getListado(){
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        obj = conexion.ObtenerArregloString("SELECT Modelo "+
                                            "FROM dbo.concentrado_folios_incidencias "+
                                            "WHERE Modelo != 'NULL' "+
                                            "AND Modelo != '' "+
                                            "GROUP BY Modelo "+
                                            "ORDER BY Modelo ASC");

        conexion.cerrarConexion();

        return obj;
    }
    public BMMDModelo[] getListado2(int IdTipoIncidencia,int IdTipoBien,String Marca,String cadenaBusqueda){
        BMMDModelo Modelo[] = new BMMDModelo[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "SELECT Modelo "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE IdTipoIncidencia = '"+IdTipoIncidencia+"' "+
                        "AND IdBien = '"+IdTipoBien+"' "+
                        "AND Marca != '"+Marca+"' "+
                        "AND Modelo != 'NULL' "+
                        "AND Modelo != '' "+
                        "GROUP BY Modelo "+
                        "ORDER BY Modelo ASC";
        
        if(cadenaBusqueda.length() > 0){            
            cadena = "SELECT Modelo "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE IdTipoIncidencia = '"+IdTipoIncidencia+"' "+
                        "AND IdBien = '"+IdTipoBien+"' "+
                        "AND Marca = '"+Marca+"' "+
                        "AND Modelo LIKE '%"+cadenaBusqueda+"%' "+
                        "AND Modelo LIKE '%"+cadenaBusqueda.toUpperCase()+"%' "+
                        "AND Modelo != 'NULL' "+
                        "AND Modelo != '' "+
                        "GROUP BY Modelo "+
                        "ORDER BY Modelo ASC";
        }            
        else{
            cadena = "SELECT Modelo "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE IdTipoIncidencia = '"+IdTipoIncidencia+"' "+
                        "AND IdBien = '"+IdTipoBien+"' "+
                        "AND Marca = '"+Marca+"' "+
                        "AND Modelo != 'NULL' "+
                        "AND Modelo != '' "+
                        "GROUP BY Modelo "+
                        "ORDER BY Modelo ASC";
        }
        //System.out.println("Cadena RPCmodelo:"+cadena);
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Modelo = new BMMDModelo[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Modelo[posy] = new BMMDModelo(resultset.getString("Modelo"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCMarcaImpl:getListado2()");
            Modelo= new BMMDModelo[0];
        }

        conexion.cerrarConexion();

        return Modelo;
    }
}
