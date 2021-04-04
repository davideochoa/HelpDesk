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
public interface RPCAreaSoporteAsync {
    public void getDatos(int Id, AsyncCallback<String[]> asyncCallback);
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void Agregar(String Nombre, AsyncCallback<Boolean> asyncCallback);
    public void Editar(int id, String nombre, AsyncCallback<Boolean> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);
}
