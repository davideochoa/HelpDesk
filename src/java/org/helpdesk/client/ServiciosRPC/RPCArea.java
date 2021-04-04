/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloArea;
import org.helpdesk.client.ModeloDatosNuevo.BMMDArea;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcarea")
public interface RPCArea extends RemoteService {
    public ModeloArea getDatos(int Id);
    public String[][] getListado(String valor);
    public String[][] getListado(int IdUnidad,String valor);
    public BMMDArea[] getListado2(String valor);
    public BMMDArea[] getListado2(int IdUnidad,String valor);
    public Boolean Agregar(int IdUnidad,String NombreUnidad);
    public Boolean Editar(ModeloArea unidad);
    public Boolean Elimiar(int Id);
}
