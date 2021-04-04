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
import org.helpdesk.client.Buscar.WindowGridFiltrarAreas;
import org.helpdesk.client.InterfazUsuario.UIArea;
import org.helpdesk.client.InterfazUsuario.UIUnidad;
import org.helpdesk.client.ModeloDatos.BaseModelUnidad;
import org.helpdesk.client.ModeloDatos.ModeloArea;
import org.helpdesk.client.ServiciosRPC.RPCAreaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoArea{
    UIUnidad UIUnidad;
    UIArea UIArea;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarAreas WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();
    
    private ModeloArea modeloArea = new ModeloArea();
    private BaseModelUnidad BMUnidad = new BaseModelUnidad();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private EVENTO_SELECCIONA_COMBOBOX_UNIDAD EventoSeleccionCBUnidad = new EVENTO_SELECCIONA_COMBOBOX_UNIDAD();

    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCAreaAsync RPCArea;
    private RPCUnidadAsync RPCUnidad;
    public VentanaCatalogoArea(RPCAreaAsync RPCArea,RPCUnidadAsync RPCUnidad){
        this.RPCArea = RPCArea;
        WindowGridBuscar = new WindowGridFiltrarAreas(RPCArea);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIArea = new UIArea(50,RPCArea);
        UIArea.setGridBuscar(WindowGridBuscar);
        
        this.RPCUnidad = RPCUnidad;        
        UIUnidad = new UIUnidad(50,RPCUnidad);
        UIUnidad.setaddSelectionChangedListenerCBNombre(EventoSeleccionCBUnidad);

        window.setHeading("Catalogo Areas");
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
        Nuevo.setToolTip(UIArea.getToolTipConfig());
        Nuevo.setToolTip("Crear una Nueva Area");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UIArea.getToolTipConfig());
        Editar.setToolTip("Edita una Area Existente");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UIArea.getToolTipConfig());
        Eliminar.setToolTip("Elimina una Area Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UIArea.getToolTipConfig());
        Grabar.setToolTip("Grabar la Nueva o Editada Area");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UIArea.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UIUnidad.getLCComboBoxActualizar());
        Form.add(UIArea.getLCNombreBuscar());

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
        window.setFocusWidget(UIUnidad.getCBNombre());
        window.center();
        ActivarEstadoUIAgregar();
    }
    private void ActivarEstadoUIAgregar(){
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        
        UIUnidad.setClear();
        UIArea.setClear();
        
        modeloArea = new ModeloArea();
        BMUnidad = new BaseModelUnidad();    
        
        UIUnidad.getCBNombre().setReadOnly(false);
        UIUnidad.setEnableBotonActualizar(true);
        
        UIArea.setReadOnlyTFNombre(false);  
        UIArea.setEnableBotonBuscar(true);

        UIUnidad.getCBNombre().focus();
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        
        UIUnidad.getCBNombre().setReadOnly(true);
        UIUnidad.setEnableBotonActualizar(false);
        
        UIArea.setEnableBotonBuscar(false);
        UIArea.setReadOnlyTFNombre(true);        
    }
    private void ActivarBotonGrabarCancelar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(true);
        Cancelar.setEnabled(true);

        UIArea.getTFNombre().setReadOnly(false);
        UIArea.getTFNombre().focus();
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
                UIArea.setEnableBotonBuscar(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                MessageBox.confirm("Advertencia","Eliminando esta Area,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UIArea.setModelo(modeloArea);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    ActivarEstadoUIAgregar();
                    UltimoIdBoton = new String();
                }
                
            }

            if(ce.getButton().getId().equals("grabar")){
                if(BMUnidad.getId() > 0 && UIArea.getTFNombre().getValue().length() > 0){
                    window.mask("Espere un momento");
                    modeloArea.setNombre(UIArea.getTFNombre().getValue());
                    if(UltimoIdBoton == "nuevo")
                        RPCArea.Agregar(BMUnidad.getId(),modeloArea.getNombre(), RetornoEventoAgregarEditarElimianr);
                    if(UltimoIdBoton == "editar")
                        RPCArea.Editar(modeloArea, RetornoEventoAgregarEditarElimianr);
                }//fin if
                else
                    MessageBox.alert("ERROR","Favor de seleccionar primero una Unidad y/o escribir el nombre de una area", null);

            }

        }
    }
    private class EVENTO_SELECCIONA_COMBOBOX_UNIDAD extends SelectionChangedListener<BaseModelUnidad>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelUnidad> se) {
            BMUnidad = se.getSelectedItem();
            
            UIArea.setIdUnidad(BMUnidad.getId());
        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                modeloArea = WindowGridBuscar.getModeloUnidadSeleccionado();
                UIArea.setModelo(modeloArea);
                ActivarBotonEditarModificar();
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && modeloArea.getId() > 0){
                RPCArea.Elimiar(modeloArea.getId(), RetornoEventoAgregarEditarElimianr);
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
                        MessageBox.alert("Grabada","El grabado de la area se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion de la area se realizo con exito", null);
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
