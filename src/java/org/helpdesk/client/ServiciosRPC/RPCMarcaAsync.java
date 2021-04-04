/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatosNuevo.BMMDMarca;

/**
 *
 * @author Administrador
 */
public interface RPCMarcaAsync {
    public void getListado(AsyncCallback<String[][]> asyncCallback);
    public void getListado2(int IdTipoIncidencia,int IdTipoBien,String cadenaBusqueda,AsyncCallback<BMMDMarca[]> asyncCallback);
}
