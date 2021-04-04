/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUsuarioReporta;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcusuarioreporta")
public interface RPCUsuarioReporta extends RemoteService {
    public String[][] getListado();
    public String[][] getListado(String cadena);
    public BMMDUsuarioReporta[] getListado2(String valor);
}
