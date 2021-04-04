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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarPrioridad;
import org.helpdesk.client.InterfazUsuario.UIPrioridad;
import org.helpdesk.client.ModeloDatos.ModeloPrioridad;
import org.helpdesk.client.ServiciosRPC.RPCPrioridadAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoPrioridad{
    UIPrioridad UIPrioridad;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarPrioridad WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();
    
    private ModeloPrioridad ModeloPrioridad = new ModeloPrioridad();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCPrioridadAsync RPCPrioridad;
    public VentanaCatalogoPrioridad(RPCPrioridadAsync RPCPrioridad){
        this.RPCPrioridad = RPCPrioridad;
        WindowGridBuscar = new WindowGridFiltrarPrioridad(RPCPrioridad);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIPrioridad = new UIPrioridad(90,RPCPrioridad);
        UIPrioridad.setGridBuscar(WindowGridBuscar);

        window.setHeading("Catalogo Prioridad");
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
        Nuevo.setToolTip(UIPrioridad.getToolTipConfig());
        Nuevo.setToolTip("Crear una Nueva Prioridad");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UIPrioridad.getToolTipConfig());
        Editar.setToolTip("Edita una Prioridad");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UIPrioridad.getToolTipConfig());
        Eliminar.setToolTip("Elimina una Prioridad Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UIPrioridad.getToolTipConfig());
        Grabar.setToolTip("Grabar la Nuevo o Editada Prioridad");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UIPrioridad.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UIPrioridad.getLCNombreBuscar());

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
        window.setFocusWidget(UIPrioridad.getTFNombre());
        window.center();
        ActivarBotonNuevo();
    }
    private void ActivarBotonNuevo(){
        ModeloPrioridad = new ModeloPrioridad();
        UIPrioridad.setClear();
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        UIPrioridad.setEnableBotonBuscar(true);
        UIPrioridad.setReadOnlyTFNombre(false);
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        UIPrioridad.setEnableBotonBuscar(false);
        UIPrioridad.setReadOnlyTFNombre(true);
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
                UIPrioridad.setClear();
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
            }
            
            if(ce.getButton().getId().equals("editar")){
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
                UIPrioridad.setEnableBotonBuscar(false);
                UIPrioridad.setReadOnlyTFNombre(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                window.mask("Espere un momento");
                MessageBox.confirm("Advertencia","Eliminando esta Prioridad,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UIPrioridad.setModelo(ModeloPrioridad);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    UIPrioridad.setClear();
                    ActivarBotonNuevo();
                    UltimoIdBoton = new String();
                }
                
            }

            if(ce.getButton().getId().equals("grabar")){
                window.mask("Espere un momento");

                ModeloPrioridad.setNombre(UIPrioridad.getTFNombre().getValue());

                if(UltimoIdBoton == "nuevo")
                    RPCPrioridad.Agregar(ModeloPrioridad.getNombre(), RetornoEventoAgregarEditarElimianr);
                if(UltimoIdBoton == "editar")
                    RPCPrioridad.Editar(ModeloPrioridad, RetornoEventoAgregarEditarElimianr);
            }

        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                ModeloPrioridad = WindowGridBuscar.getModeloSeleccionado();
                UIPrioridad.setModelo(ModeloPrioridad);
                ActivarBotonEditarModificar();
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && ModeloPrioridad.getId() > 0){

                RPCPrioridad.Elimiar(ModeloPrioridad.getId(), RetornoEventoAgregarEditarElimianr);
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
                        MessageBox.alert("Grabada","El grabado de la Prioridad se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion de la Prioridad se realizo con exito", null);
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
