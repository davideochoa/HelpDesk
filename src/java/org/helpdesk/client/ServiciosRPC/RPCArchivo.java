/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.DataModelGridArchivos;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcarchivo")
public interface RPCArchivo extends RemoteService {
    public DataModelGridArchivos[] listado(int Folio);
    public void imprimirArchivo(Integer Id,String NombreArchivo);
}
