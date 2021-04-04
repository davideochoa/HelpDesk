/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloTipoIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoIncidencia;

/**
 *
 * @author Administrador
 */
public interface RPCTipoIncidenciaAsync {
    public void getDatos(int Id, AsyncCallback<ModeloTipoIncidencia> asyncCallback);
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void getListado2(String valor,AsyncCallback<BMMDTipoIncidencia[]> asyncCallback);
    public void Agregar(String Nombre, AsyncCallback<Boolean> asyncCallback);
    public void Editar(ModeloTipoIncidencia modelo, AsyncCallback<Boolean> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);
}
