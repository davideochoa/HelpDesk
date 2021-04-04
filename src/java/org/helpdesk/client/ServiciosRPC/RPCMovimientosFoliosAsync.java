/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ServiciosRPC;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.ModeloEstadoDiagnostico;
import org.helpdesk.client.ModeloDatos.ModeloFolioIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDConfiguracion;
import org.helpdesk.client.ModeloDatosNuevo.BMMDGridBusqueda;

/**
 *
 * @author Administrador
 */
public interface RPCMovimientosFoliosAsync {
    public void Insertar(ModeloFolioIncidencia MDFolio,AsyncCallback<ModeloFolioIncidencia> asyncCallback);
    public void ObtenerFolio(int Folio,AsyncCallback<ModeloFolioIncidencia> asyncCallback);    
    public void ObtenerEstatus(int Folio, AsyncCallback<String[][]> asyncCallback);
    public void ListarFolios(int IdUsuarioSoporteAsignado,boolean Administrador, AsyncCallback<String[][]> asyncCallback);
    public void AgregarEstatus(int Folio, int IdEstatus,int IdIncidenciaFinal, boolean Activo, int IdUsuario, String Anotacion, int IdUsuarioSoporteAsignado, AsyncCallback<String[][]> asyncCallback);
    public void EstadoDiagnostico(int Folio, AsyncCallback<ModeloEstadoDiagnostico> asyncCallback);
    public void buscar(int IdUnidad,
                        int IdArea,
                        String UsuarioReporta,
                        String ReferenciaDocumental,
                        int IdTipoIncidencia,
                        int IdTipoBien,
                        String Marca,
                        String Modelo,
                        String NumeroSerie,
                        String NumeroInventario,AsyncCallback<BMMDGridBusqueda[]> asyncCallback);
    public void configuracion(AsyncCallback<BMMDConfiguracion> asyncCallback);
}
