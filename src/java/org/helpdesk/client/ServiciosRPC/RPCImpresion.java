/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcimpresion")
public interface RPCImpresion extends RemoteService {
    public void RPTIncidenciaXFolio(Integer folio);
    public void RPTFoliosXUnidad(Integer IdUnidad);
    public void RPTFoliosXUnidadXDiagnosticos(Integer IdUnidad);
    public void RPTFoliosXUnidadXDiagnosticosFinales(Integer IdUnidad);
    public void RPTFoliosXAreaSoporte(Integer IdArea);
}
