/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloPrioridad;

/**
 *
 * @author Administrador
 */
public interface RPCPrioridadAsync {
    public void getDatos(int Id, AsyncCallback<ModeloPrioridad> asyncCallback);
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void Agregar(String Nombre, AsyncCallback<Boolean> asyncCallback);
    public void Editar(ModeloPrioridad modelo, AsyncCallback<Boolean> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);    
}
