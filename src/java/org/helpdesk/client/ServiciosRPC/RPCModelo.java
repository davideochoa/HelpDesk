/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatosNuevo.BMMDModelo;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcmodelo")
public interface RPCModelo extends RemoteService {
    public String[][] getListado();
    public BMMDModelo[] getListado2(int IdTipoIncidencia,int IdTipoBien,String Marca,String cadenaBusqueda);
}
