/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUnidad;

/**
 *
 * @author Administrador
 */
public interface RPCUnidadAsync {
    public void getDatos(int Id, AsyncCallback<ModeloUnidad> asyncCallback);
    public void getListado(String valor,AsyncCallback<String[][]> callback);
    public void getListado2(String valor,AsyncCallback<BMMDUnidad[]> callback);
    public void Agregar(String NombreUnidad, AsyncCallback<Boolean> asyncCallback);
    public void Editar(ModeloUnidad unidad, AsyncCallback<Boolean> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);
}
