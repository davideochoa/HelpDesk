/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUnidad;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcunidad")
public interface RPCUnidad extends RemoteService {
    public ModeloUnidad getDatos(int Id);
    public String[][] getListado(String valor);
    public BMMDUnidad[] getListado2(String valor);
    public Boolean Agregar(String NombreUnidad);
    public Boolean Editar(ModeloUnidad unidad);
    public Boolean Elimiar(int Id);
}
