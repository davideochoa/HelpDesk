/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.helpdesk.client.ModeloDatosNuevo.BMMDDocReferencia;
import org.helpdesk.client.ServiciosRPC.RPCDocRef;

/**
 *
 * @author Usuario
 */
public class RPCDocRefImpl extends RemoteServiceServlet implements RPCDocRef {
    public BMMDDocReferencia[] getListado2(String valor){
        BMMDDocReferencia Unidades[] = new BMMDDocReferencia[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "";
        
        if(valor.length() > 0)
            cadena = "SELECT ReferenciaDocumental "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE ReferenciaDocumental != 'NULL' "+
                        "AND ReferenciaDocumental != '' "+
                        "AND ReferenciaDocumental LIKE '%"+valor+"%' "+
                        "AND ReferenciaDocumental LIKE '%"+valor.toUpperCase()+"%' "+
                        "GROUP BY ReferenciaDocumental "+
                        "ORDER BY ReferenciaDocumental ASC";        
        else
            cadena = "SELECT ReferenciaDocumental "+
                        "FROM dbo.concentrado_folios_incidencias "+
                        "WHERE ReferenciaDocumental != 'NULL' "+
                        "AND ReferenciaDocumental != '' "+
                        "GROUP BY ReferenciaDocumental "+
                        "ORDER BY ReferenciaDocumental ASC";
        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Unidades = new BMMDDocReferencia[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Unidades[posy] = new BMMDDocReferencia(resultset.getString("ReferenciaDocumental"));
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCDocRefImpl:getListado2()");
            Unidades= new BMMDDocReferencia[0];
        }
        conexion.cerrarConexion();
        
        return Unidades;
    }
    
}
