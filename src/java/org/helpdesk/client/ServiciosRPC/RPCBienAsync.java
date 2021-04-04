/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloBien;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoBien;

/**
 *
 * @author Administrador
 */
public interface RPCBienAsync {
    public void getDatos(int Id, AsyncCallback<ModeloBien> asyncCallback);
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void getListado(int IdTipoIncidencia, String valor, AsyncCallback<String[][]> asyncCallback);
    public void getListado2(int IdTipoIncidencia,String valor, AsyncCallback<BMMDTipoBien[]> asyncCallback);
    public void Agregar(int IdTipoIncidencia, String Nombre, AsyncCallback<Boolean> asyncCallback);    
    public void Editar(ModeloBien modelo, AsyncCallback<Boolean> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);
}
