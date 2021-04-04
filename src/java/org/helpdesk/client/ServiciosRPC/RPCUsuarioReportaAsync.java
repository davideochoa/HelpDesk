/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUsuarioReporta;

/**
 *
 * @author Administrador
 */
public interface RPCUsuarioReportaAsync {
    public void getListado(AsyncCallback<String[][]> asyncCallback);
    public void getListado(String cadena,AsyncCallback<String[][]> asyncCallback);
    public void getListado2(String valor,AsyncCallback<BMMDUsuarioReporta[]> asyncCallback);
}
