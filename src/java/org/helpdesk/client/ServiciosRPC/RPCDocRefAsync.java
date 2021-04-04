/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatosNuevo.BMMDDocReferencia;

/**
 *
 * @author Usuario
 */
public interface RPCDocRefAsync {
    public void getListado2(String valor,AsyncCallback<BMMDDocReferencia[]> callback);
}
