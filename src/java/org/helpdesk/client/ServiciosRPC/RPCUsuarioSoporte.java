/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloAreasSoporte_Asignadas_NoAsignadas;
import org.helpdesk.client.ModeloDatos.ModeloDatosUsuario;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcusuariosoporte")
public interface RPCUsuarioSoporte extends RemoteService {
    public String[] getDatos(int Id);
    public String[][] getListado(String valor);
    public ModeloAreasSoporte_Asignadas_NoAsignadas getListadosAreasAsignadas(int IdUsuarioSoporte);
    public Boolean Agregar(String NombrePropio, String Correo,
                            String NombreUsuario,boolean EsAdministrador,
                            ModeloAreasSoporte_Asignadas_NoAsignadas Areas);
    public Boolean Editar(int Id,String NombrePropio, String Correo,
                            String NombreUsuario,boolean EsAdministrador,boolean EsReseteadoPassword,
                            ModeloAreasSoporte_Asignadas_NoAsignadas Areas);
    public Boolean Elimiar(int Id);
    public void EditarPassword(ModeloDatosUsuario MDUsuario);
    public ModeloDatosUsuario verificaLogin(ModeloDatosUsuario MDUsuario);
}
