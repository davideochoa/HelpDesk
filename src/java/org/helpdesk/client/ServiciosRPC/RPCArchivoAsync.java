/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.DataModelGridArchivos;

/**
 *
 * @author Administrador
 */
public interface RPCArchivoAsync {
    public void listado(int Folio, AsyncCallback<DataModelGridArchivos[]> callback);
    public void imprimirArchivo(Integer Id,String NombreArchivo,AsyncCallback<Void> callback);
}
