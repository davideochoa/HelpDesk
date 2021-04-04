/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloArea;
import org.helpdesk.client.ModeloDatosNuevo.BMMDArea;

/**
 *
 * @author Administrador
 */
public interface RPCAreaAsync {
    public void getDatos(int Id, AsyncCallback<ModeloArea> asyncCallback);
    
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void getListado(int IdUnidad, String valor, AsyncCallback<String[][]> asyncCallback);
    
    public void getListado2(String valor,AsyncCallback<BMMDArea[]> asyncCallback);    
    public void getListado2(int IdUnidad,String valor, AsyncCallback<BMMDArea[]> asyncCallback);
    
    public void Agregar(int IdUnidad,String NombreUnidad, AsyncCallback<Boolean> asyncCallback);
    public void Editar(ModeloArea unidad, AsyncCallback<Boolean> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);
}
