/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatosNuevo.BMMDArea;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcareasoporte")
public interface RPCAreaSoporte extends RemoteService {
    public String[] getDatos(int Id);
    public String[][] getListado(String valor);    
    public Boolean Agregar(String Nombre);
    public Boolean Editar(int id,String nombre);
    public Boolean Elimiar(int Id);
}
