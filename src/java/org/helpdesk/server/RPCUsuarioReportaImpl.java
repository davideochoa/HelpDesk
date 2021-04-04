/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUsuarioReporta;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReporta;

/**
 *
 * @author Administrador
 */
public class RPCUsuarioReportaImpl extends RemoteServiceServlet implements RPCUsuarioReporta {
    public String[][] getListado(){
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        obj = conexion.ObtenerArregloString("SELECT UsuarioReporta "+
                                            "FROM dbo.concentrado_folios_incidencias "+
                                            "WHERE UsuarioReporta != 'NULL' "+
                                            "AND UsuarioReporta != '' "+
                                            "GROUP BY UsuarioReporta "+
                                            "ORDER BY UsuarioReporta ASC");

        conexion.cerrarConexion();

        return obj;
    }
    public String[][] getListado(String cadena){
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        obj = conexion.ObtenerArregloString("SELECT UsuarioReporta "+
                                            "FROM dbo.concentrado_folios_incidencias "+
                                            "WHERE UsuarioReporta != 'NULL' "+
                                            "AND UsuarioReporta != '' "+
                                            "AND UsuarioReporta LIKE '%"+cadena+"%' "+
                                            "AND UsuarioReporta LIKE '%"+cadena.toUpperCase()+"%' "+
                                            "GROUP BY UsuarioReporta "+
                                            "ORDER BY UsuarioReporta ASC");

        conexion.cerrarConexion();

        return obj;
    }
    public BMMDUsuarioReporta[] getListado2(String valor){
        BMMDUsuarioReporta Unidades[] = new BMMDUsuarioReporta[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";
        
        if(valor.length() > 0)
            cadena = "SELECT UsuarioReporta "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE UsuarioReporta != 'NULL' "+
                        "AND UsuarioReporta != '' "+
                        "AND UsuarioReporta LIKE '%"+valor+"%' "+
                        "AND UsuarioReporta LIKE '%"+valor.toUpperCase()+"%' "+
                        "GROUP BY UsuarioReporta "+
                        "ORDER BY UsuarioReporta ASC";        
        else
            cadena = "SELECT UsuarioReporta "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE UsuarioReporta != 'NULL' "+
                        "AND UsuarioReporta != '' "+
                        "GROUP BY UsuarioReporta "+
                        "ORDER BY UsuarioReporta ASC";
        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Unidades = new BMMDUsuarioReporta[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Unidades[posy] = new BMMDUsuarioReporta(resultset.getString("UsuarioReporta"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCUsuarioReportaImpl:getListado2()");
            Unidades= new BMMDUsuarioReporta[0];
        }
        conexion.cerrarConexion();
        
        return Unidades;
    }
}
