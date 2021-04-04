/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.helpdesk.client.ServiciosRPC.RPCImpresion;

/**
 *
 * @author Administrador
 */
public class RPCImpresionImpl extends RemoteServiceServlet implements RPCImpresion {
    public void RPTIncidenciaXFolio(Integer folio) {
        this.getThreadLocalRequest().getSession().setAttribute("Folio",folio);
    }
    public void RPTFoliosXUnidad(Integer IdUnidad) {
        this.getThreadLocalRequest().getSession().setAttribute("IdUnidad",IdUnidad);
    }
    public void RPTFoliosXUnidadXDiagnosticos(Integer IdUnidad) {
        this.getThreadLocalRequest().getSession().setAttribute("IdUnidad",IdUnidad);
    }
    public void RPTFoliosXUnidadXDiagnosticosFinales(Integer IdUnidad) {
        this.getThreadLocalRequest().getSession().setAttribute("IdUnidad",IdUnidad);
    }
    public void RPTFoliosXAreaSoporte(Integer IdAreaSoporte) {
        this.getThreadLocalRequest().getSession().setAttribute("IdAreaSoporte",IdAreaSoporte);
    }
}
