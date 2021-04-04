/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.helpdesk.client.ServiciosRPC.RPCEstatus;

/**
 *
 * @author Administrador
 */
public class RPCEstatusImpl extends RemoteServiceServlet implements RPCEstatus {
    public String[] getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();
        
            obj = conexion.ObtenerArregloString("SELECT Id,Nombre, "+
                                                "CASE Abrir WHEN 0 THEN 'false' ELSE 'true' END  AS Abierto, "+
                                                "CASE Cerrar WHEN 0 THEN 'false' ELSE 'true' END  AS Cerrado, "+
                                                "CASE Reasignar WHEN 0 THEN 'false' ELSE 'true' END  AS Reasignar, "+
                                                "CASE DiagnostinoInicial WHEN 0 THEN 'false' ELSE 'true' END  AS DiagnostinoInicial, "+
                                                "CASE DiagnostinoFinal WHEN 0 THEN 'false' ELSE 'true' END  AS DiagnostinoFinal "+
                                                "FROM dbo.catalogo_estatus "+
                                                "ORDER BY Nombre");
       

        conexion.cerrarConexion();

        return obj;
    }
}
