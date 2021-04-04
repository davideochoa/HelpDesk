/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Administrador
 */
public interface RPCImpresionAsync {
    public void RPTIncidenciaXFolio(Integer folio, AsyncCallback<Void> asyncCallback);

    public void RPTFoliosXUnidad(Integer IdUnidad, AsyncCallback<Void> asyncCallback);

    public void RPTFoliosXUnidadXDiagnosticos(Integer IdUnidad, AsyncCallback<Void> asyncCallback);

    public void RPTFoliosXUnidadXDiagnosticosFinales(Integer IdUnidad, AsyncCallback<Void> asyncCallback);

    public void RPTFoliosXAreaSoporte(Integer IdArea, AsyncCallback<Void> asyncCallback);
}
