/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloTipoIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoIncidencia;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpctipoincidencia")
public interface RPCTipoIncidencia extends RemoteService {
    public ModeloTipoIncidencia getDatos(int Id);
    public String[][] getListado(String valor);
    public BMMDTipoIncidencia[] getListado2(String valor);
    public Boolean Agregar(String Nombre);
    public Boolean Editar(ModeloTipoIncidencia modelo);
    public Boolean Elimiar(int Id);
}
