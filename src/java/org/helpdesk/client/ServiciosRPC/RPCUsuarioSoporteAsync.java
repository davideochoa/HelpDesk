/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloAreasSoporte_Asignadas_NoAsignadas;
import org.helpdesk.client.ModeloDatos.ModeloDatosUsuario;

/**
 *
 * @author Administrador
 */
public interface RPCUsuarioSoporteAsync {
    public void getDatos(int Id, AsyncCallback<String[]> asyncCallback);
    public void getListado(String valor, AsyncCallback<String[][]> asyncCallback);
    public void Elimiar(int Id, AsyncCallback<Boolean> asyncCallback);

    public void getListadosAreasAsignadas(int IdUsuarioSoporte, AsyncCallback<ModeloAreasSoporte_Asignadas_NoAsignadas> asyncCallback);

    public void Agregar(String NombrePropio, String Correo, String NombreUsuario, boolean EsAdministrador, ModeloAreasSoporte_Asignadas_NoAsignadas Areas, AsyncCallback<Boolean> asyncCallback);

    public void verificaLogin(ModeloDatosUsuario MDUsuario, AsyncCallback<ModeloDatosUsuario> asyncCallback);

    public void EditarPassword(ModeloDatosUsuario MDUsuario, AsyncCallback<Void> asyncCallback);

    public void Editar(int Id, String NombrePropio, String Correo, String NombreUsuario, boolean EsAdministrador, boolean EsReseteadoPassword, ModeloAreasSoporte_Asignadas_NoAsignadas Areas, AsyncCallback<Boolean> asyncCallback);
}
