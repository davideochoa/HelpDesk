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
public interface RPCEstatusAsync {    
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void getDatos(int Id, AsyncCallback<String[]> asyncCallback);
}
