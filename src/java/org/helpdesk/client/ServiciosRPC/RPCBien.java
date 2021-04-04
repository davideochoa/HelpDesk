/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloBien;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoBien;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcbien")
public interface RPCBien extends RemoteService {
    public ModeloBien getDatos(int Id);
    public String[][] getListado(String valor);
    public String[][] getListado(int IdTipoIncidencia,String valor);
    public BMMDTipoBien[] getListado2(int IdTipoIncidencia,String valor);
    public Boolean Agregar(int IdTipoIncidencia,String Nombre);
    public Boolean Editar(ModeloBien modelo);
    public Boolean Elimiar(int Id);
}
