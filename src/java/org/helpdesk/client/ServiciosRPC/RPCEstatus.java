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
@RemoteServiceRelativePath("rpcestatus")
public interface RPCEstatus extends RemoteService {
    public String[] getDatos(int Id);
    public String[][] getListado(String valor);    
}
