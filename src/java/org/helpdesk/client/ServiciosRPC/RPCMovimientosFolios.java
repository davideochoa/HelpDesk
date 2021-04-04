
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.helpdesk.client.ModeloDatos.ModeloEstadoDiagnostico;
import org.helpdesk.client.ModeloDatos.ModeloFolioIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDConfiguracion;
import org.helpdesk.client.ModeloDatosNuevo.BMMDGridBusqueda;

/**
 *
 * @author Administrador
 */
@RemoteServiceRelativePath("rpcmovimientosfolios")
public interface RPCMovimientosFolios extends RemoteService {
    public ModeloFolioIncidencia Insertar(ModeloFolioIncidencia MDFolio);
    public ModeloFolioIncidencia ObtenerFolio(int Folio);    
    public String[][] AgregarEstatus(int Folio,int IdEstatus,int IdIncidenciaFinal,boolean Activo,int IdUsuario,String Anotacion,int IdUsuarioSoporteAsignado);
    public String[][] ObtenerEstatus(int Folio);
    public String[][] ListarFolios(int IdUsuarioSoporteAsignado,boolean Administrador);
    public ModeloEstadoDiagnostico EstadoDiagnostico(int Folio);
    public BMMDGridBusqueda[] buscar(int IdUnidad,
                                        int IdArea,
                                        String UsuarioReporta,
                                        String ReferenciaDocumental,
                                        int IdTipoIncidencia,
                                        int IdTipoBien,
                                        String Marca,
                                        String Modelo,
                                        String NumeroSerie,
                                        String NumeroInventario);
    public BMMDConfiguracion configuracion();
}
