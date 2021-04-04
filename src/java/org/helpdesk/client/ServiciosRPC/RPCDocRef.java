/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatosNuevo.BMMDDocReferencia;

/**
 *
 * @author Usuario
 */
@RemoteServiceRelativePath("rpcdocref")
public interface RPCDocRef extends RemoteService {
    public BMMDDocReferencia[] getListado2(String valor);
}
