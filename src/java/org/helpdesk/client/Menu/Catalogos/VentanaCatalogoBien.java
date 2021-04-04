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
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarBienes;
import org.helpdesk.client.InterfazUsuario.UIBien;
import org.helpdesk.client.InterfazUsuario.UITipoIncidencia;
import org.helpdesk.client.ModeloDatos.BaseModelIncidencia;
import org.helpdesk.client.ModeloDatos.ModeloBien;
import org.helpdesk.client.ServiciosRPC.RPCBienAsync;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoBien{
    UITipoIncidencia UITipoIncidencia;
    UIBien UIBien;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarBienes WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();
    
    private ModeloBien modeloBien = new ModeloBien();
    private BaseModelIncidencia BMTipoIncidencia = new BaseModelIncidencia();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionCBNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();

    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCBienAsync RPCBien;
    private RPCTipoIncidenciaAsync RPCTipoIncidencia;
    public VentanaCatalogoBien(RPCBienAsync RPCBien,RPCTipoIncidenciaAsync RPCTipoIncidencia){
        this.RPCBien = RPCBien;
        WindowGridBuscar = new WindowGridFiltrarBienes(RPCBien);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIBien = new UIBien(50,RPCBien);
        UIBien.setGridBuscar(WindowGridBuscar);
        
        this.RPCTipoIncidencia = RPCTipoIncidencia;
        UITipoIncidencia = new UITipoIncidencia(100,RPCTipoIncidencia);
        UITipoIncidencia.setaddSelectionChangedListenerCBNombre(EventoSeleccionCBNombre);

        window.setHeading("Catalogo Bienes");
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
        Nuevo.setToolTip(UIBien.getToolTipConfig());
        Nuevo.setToolTip("Crear un Nuevo Bien");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UIBien.getToolTipConfig());
        Editar.setToolTip("Edita un Bien Existente");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UIBien.getToolTipConfig());
        Eliminar.setToolTip("Elimina un Bien Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UIBien.getToolTipConfig());
        Grabar.setToolTip("Grabar el Nuevo o Editado Bien");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UIBien.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UITipoIncidencia.getLCComboBoxActualizar());
        Form.add(UIBien.getLCNombreBuscar());

        window.add(Form);//, new FormData("100%"));
        window.addButton(Nuevo);
        window.addButton(Editar);
        window.addButton(Eliminar);
        window.addButton(Grabar);
        window.addButton(Cancelar);
        window.setIcon(IconHelper.create("catalogo-area"));
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UITipoIncidencia.getCBNombre());
        window.center();
        ActivarEstadoUIAgregar();
    }
    private void ActivarEstadoUIAgregar(){
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        
        UITipoIncidencia.setClear();
        UIBien.setClear();
        
        modeloBien = new ModeloBien();
        BMTipoIncidencia = new BaseModelIncidencia();
        
        UITipoIncidencia.getCBNombre().setReadOnly(false);
        UITipoIncidencia.setEnableBotonActualizar(true);
        
        UIBien.setReadOnlyTFNombre(false);
        UIBien.setEnableBotonBuscar(true);

        UITipoIncidencia.getCBNombre().focus();
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        
        UITipoIncidencia.getCBNombre().setReadOnly(true);
        UITipoIncidencia.setEnableBotonActualizar(false);
        
        UIBien.setEnableBotonBuscar(false);
        UIBien.setReadOnlyTFNombre(true);
    }
    private void ActivarBotonGrabarCancelar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(true);
        Cancelar.setEnabled(true);

        UIBien.getTFNombre().setReadOnly(false);
        UIBien.getTFNombre().focus();
    }

    private class EVENTO_BOTON extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){

            if(ce.getButton().getId().equals("nuevo")){
                UltimoIdBoton = ce.getButton().getId();
                ActivarBotonGrabarCancelar();
            }
            
            if(ce.getButton().getId().equals("editar")){
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
                UIBien.setEnableBotonBuscar(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                MessageBox.confirm("Advertencia","Eliminando este Bien,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UIBien.setModelo(modeloBien);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    ActivarEstadoUIAgregar();
                    UltimoIdBoton = new String();
                }
                
            }

            if(ce.getButton().getId().equals("grabar")){
                if(BMTipoIncidencia.getId() > 0 && UIBien.getTFNombre().getValue().length() > 0){
                    window.mask("Espere un momento");
                    modeloBien.setNombre(UIBien.getTFNombre().getValue());
                    if(UltimoIdBoton == "nuevo")
                        RPCBien.Agregar(BMTipoIncidencia.getId(),modeloBien.getNombre(), RetornoEventoAgregarEditarElimianr);
                    if(UltimoIdBoton == "editar")
                        RPCBien.Editar(modeloBien, RetornoEventoAgregarEditarElimianr);
                }//fin if
                else
                    MessageBox.alert("ERROR","Favor de seleccionar primero una Tipo de Incidencia y/o escribir el nombre de un Bien", null);

            }

        }
    }
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelIncidencia>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelIncidencia> se) {
            BMTipoIncidencia = se.getSelectedItem();
            
            UIBien.setIdTipoIncidencia(BMTipoIncidencia.getId());
        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                modeloBien = WindowGridBuscar.getBMSeleccionado().getModelo();
                UIBien.setModelo(modeloBien);
                ActivarBotonEditarModificar();
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && modeloBien.getId() > 0){
                RPCBien.Elimiar(modeloBien.getId(), RetornoEventoAgregarEditarElimianr);
                ActivarEstadoUIAgregar();
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
                        MessageBox.alert("Grabada","El grabado del Bien se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion del Bien se realizo con exito", null);
                    ActivarEstadoUIAgregar();
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
