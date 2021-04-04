/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Catalogos;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarTipoIncidencia;
import org.helpdesk.client.InterfazUsuario.UITipoIncidencia;
import org.helpdesk.client.ModeloDatos.ModeloTipoIncidencia;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoTipoIncidencia{
    UITipoIncidencia UITipoReporte;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarTipoIncidencia WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();
    
    private ModeloTipoIncidencia ModeloTipoReporte = new ModeloTipoIncidencia();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCTipoIncidenciaAsync RPCTipoReporte;
    public VentanaCatalogoTipoIncidencia(RPCTipoIncidenciaAsync RPCTipoIncidencia){
        this.RPCTipoReporte = RPCTipoIncidencia;
        WindowGridBuscar = new WindowGridFiltrarTipoIncidencia(RPCTipoIncidencia);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UITipoReporte = new UITipoIncidencia(90,RPCTipoIncidencia);
        UITipoReporte.setGridBuscar(WindowGridBuscar);

        window.setHeading("Catalogo Tipo Incidencias");
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setBlinkModal(true);
        window.setClosable(true);
        window.setResizable(false);
        window.setModal(true);
        window.setOnEsc(true);        
        window.setWidth(450);
        window.setButtonAlign(HorizontalAlignment.CENTER);

        Nuevo.setText("Nuevo");
        Nuevo.setId("nuevo");
        Nuevo.setIconAlign(IconAlign.RIGHT);
        Nuevo.setIcon(IconHelper.create("agregar"));
        Nuevo.setToolTip(UITipoReporte.getToolTipConfig());
        Nuevo.setToolTip("Crear una Nuevo Tipo de Incidencia");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UITipoReporte.getToolTipConfig());
        Editar.setToolTip("Edita un Tipo de Incidencia Existente");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UITipoReporte.getToolTipConfig());
        Eliminar.setToolTip("Elimina un Tipo de Incidencia Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UITipoReporte.getToolTipConfig());
        Grabar.setToolTip("Grabar el Nuevo o Editado Tipo de Incidencia");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UITipoReporte.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UITipoReporte.getLCNombreBuscar());

        window.add(Form);//, new FormData("100%"));
        window.addButton(Nuevo);
        window.addButton(Editar);
        window.addButton(Eliminar);
        window.addButton(Grabar);
        window.addButton(Cancelar);
        window.setIcon(IconHelper.create("catalogo-incidencia"));
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UITipoReporte.getTFNombre());
        window.center();
        ActivarBotonNuevo();
    }
    private void ActivarBotonNuevo(){
        ModeloTipoReporte = new ModeloTipoIncidencia();
        UITipoReporte.setClear();
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        UITipoReporte.setEnableBotonBuscar(true);
        UITipoReporte.setReadOnlyTFNombre(false);
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        UITipoReporte.setEnableBotonBuscar(false);
        UITipoReporte.setReadOnlyTFNombre(true);
    }
    private void ActivarBotonGrabarCancelar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(true);
        Cancelar.setEnabled(true);
    }

    private class EVENTO_BOTON extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){


            if(ce.getButton().getId().equals("nuevo")){
                UITipoReporte.setClear();
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
            }
            
            if(ce.getButton().getId().equals("editar")){
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
                UITipoReporte.setEnableBotonBuscar(false);
                UITipoReporte.setReadOnlyTFNombre(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                MessageBox.confirm("Advertencia","Eliminando este Tipo de Incidencia,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UITipoReporte.setModelo(ModeloTipoReporte);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    UITipoReporte.setClear();
                    ActivarBotonNuevo();
                    UltimoIdBoton = new String();
                }
                
            }

            if(ce.getButton().getId().equals("grabar")){
                window.mask("Espere un momento");

                ModeloTipoReporte.setNombre(UITipoReporte.getTFNombre().getValue());

                if(UltimoIdBoton == "nuevo")
                    RPCTipoReporte.Agregar(ModeloTipoReporte.getNombre(), RetornoEventoAgregarEditarElimianr);
                if(UltimoIdBoton == "editar")
                    RPCTipoReporte.Editar(ModeloTipoReporte, RetornoEventoAgregarEditarElimianr);
            }

        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                ModeloTipoReporte = WindowGridBuscar.getModeloSeleccionado();
                UITipoReporte.setModelo(ModeloTipoReporte);
                ActivarBotonEditarModificar();
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && ModeloTipoReporte.getId() > 0){
                RPCTipoReporte.Elimiar(ModeloTipoReporte.getId(), RetornoEventoAgregarEditarElimianr);
                ActivarBotonNuevo();
            }
            else{
                ActivarBotonEditarModificar();
            }
        }
    }
    private class RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR implements AsyncCallback<Boolean>{
            public void onSuccess(Boolean result) {
                Boolean estado = (Boolean)result;
                
                if(estado.booleanValue() == true){
                    if(UltimoIdBoton.equals("nuevo"))
                        MessageBox.alert("Grabada","El grabado del Tipo de Incidencia se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion del Tipo de Incidencia se realizo con exito", null);
                    ActivarBotonNuevo();
                }
                else
                    MessageBox.alert("ERROR","La accio no pudo realizarse con exito. Avise al administrador del sistema", null);                
                
                window.unmask();
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
}
