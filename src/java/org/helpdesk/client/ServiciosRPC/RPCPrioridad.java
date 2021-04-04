/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloPrioridad;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcprioridad")
public interface RPCPrioridad extends RemoteService {
    public ModeloPrioridad getDatos(int Id);
    public String[][] getListado(String valor);
    public Boolean Agregar(String Nombre);
    public Boolean Editar(ModeloPrioridad modelo);
    public Boolean Elimiar(int Id);
}
