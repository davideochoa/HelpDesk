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
import org.helpdesk.client.Buscar.WindowGridFiltrarAreaSoporte;
import org.helpdesk.client.InterfazUsuario.UIAreaSoporte;
import org.helpdesk.client.ModeloDatos.BaseModelAreaSoporte;
import org.helpdesk.client.ServiciosRPC.RPCAreaSoporteAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoAreaSoporte{
    UIAreaSoporte UIAreaSoporte;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarAreaSoporte WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCAreaSoporteAsync RPCAreaSoporte;
    BaseModelAreaSoporte BMAreaSoporte = new BaseModelAreaSoporte();
    public VentanaCatalogoAreaSoporte(RPCAreaSoporteAsync RPCAreaSoporte){
        this.RPCAreaSoporte = RPCAreaSoporte;
        WindowGridBuscar = new WindowGridFiltrarAreaSoporte(RPCAreaSoporte);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIAreaSoporte = new UIAreaSoporte(90,RPCAreaSoporte);
        UIAreaSoporte.setGridBuscar(WindowGridBuscar);

        window.setHeading("Catalogo Area de Soporte");
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
        Nuevo.setToolTip(UIAreaSoporte.getToolTipConfig());
        Nuevo.setToolTip("Crear una Nueva Area de Soporte");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UIAreaSoporte.getToolTipConfig());
        Editar.setToolTip("Edita una Area de Soporte");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UIAreaSoporte.getToolTipConfig());
        Eliminar.setToolTip("Elimina una Area de Soporte Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UIAreaSoporte.getToolTipConfig());
        Grabar.setToolTip("Grabar la Nuevo o Editada Area de Soporte");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UIAreaSoporte.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UIAreaSoporte.getLCNombreBuscar());

        window.add(Form);//, new FormData("100%"));
        window.addButton(Nuevo);
        window.addButton(Editar);
        window.addButton(Eliminar);
        window.addButton(Grabar);
        window.addButton(Cancelar);
        window.setIcon(IconHelper.create("catalogo-soporte"));
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UIAreaSoporte.getTFNombre());
        window.center();
        ActivarBotonNuevo();
    }
    private void ActivarBotonNuevo(){
        //ModeloPrioridad = new ModeloPrioridad();
        UIAreaSoporte.setClear();
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        UIAreaSoporte.setEnableBotonBuscar(true);
        UIAreaSoporte.setReadOnlyTFNombre(false);
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        UIAreaSoporte.setEnableBotonBuscar(false);
        UIAreaSoporte.setReadOnlyTFNombre(true);
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
                UIAreaSoporte.setClear();
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
            }
            
            if(ce.getButton().getId().equals("editar")){
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
                UIAreaSoporte.setEnableBotonBuscar(false);
                UIAreaSoporte.setReadOnlyTFNombre(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                window.mask("Espere un momento");
                MessageBox.confirm("Advertencia","Eliminando esta Area de Soporte,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UIAreaSoporte.setBaseModel(BMAreaSoporte);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    UIAreaSoporte.setClear();
                    ActivarBotonNuevo();
                    UltimoIdBoton = new String();
                }
                
            }

            if(ce.getButton().getId().equals("grabar")){
                window.mask("Espere un momento");

                BMAreaSoporte = UIAreaSoporte.getBaseModel();

                if(UltimoIdBoton == "nuevo")
                    RPCAreaSoporte.Agregar(BMAreaSoporte.getNombre(), RetornoEventoAgregarEditarElimianr);
                if(UltimoIdBoton == "editar")
                    RPCAreaSoporte.Editar(BMAreaSoporte.getId(),BMAreaSoporte.getNombre(),RetornoEventoAgregarEditarElimianr);
            }

        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                BMAreaSoporte = WindowGridBuscar.getBMSeleccionado();
                UIAreaSoporte.setBaseModel(BMAreaSoporte);
                ActivarBotonEditarModificar();
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && BMAreaSoporte.getId() > 0){

                RPCAreaSoporte.Elimiar(BMAreaSoporte.getId(), RetornoEventoAgregarEditarElimianr);
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
                        MessageBox.alert("Grabada","El grabado de la Area de Soporte se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion de la Area de Soporte se realizo con exito", null);
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
