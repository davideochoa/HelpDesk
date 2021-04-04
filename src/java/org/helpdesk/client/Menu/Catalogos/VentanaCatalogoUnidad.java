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
import org.helpdesk.client.Buscar.WindowGridFiltrarUnidades;
import org.helpdesk.client.InterfazUsuario.UIUnidad;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoUnidad{
    UIUnidad UIUnidad;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarUnidades WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();
    
    private ModeloUnidad modeloUnidad = new ModeloUnidad();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCUnidadAsync RPCUnidad;
    public VentanaCatalogoUnidad(RPCUnidadAsync RPCUnidad){
        this.RPCUnidad = RPCUnidad;
        WindowGridBuscar = new WindowGridFiltrarUnidades(RPCUnidad);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIUnidad = new UIUnidad(90,RPCUnidad);
        UIUnidad.setGridBuscar(WindowGridBuscar);

        window.setHeading("Catalogo Unidades");        
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
        Nuevo.setToolTip(UIUnidad.getToolTipConfig());
        Nuevo.setToolTip("Crear una Nueva Unidad");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UIUnidad.getToolTipConfig());
        Editar.setToolTip("Edita una Unidad Existente");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UIUnidad.getToolTipConfig());
        Eliminar.setToolTip("Elimina una Unidad Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UIUnidad.getToolTipConfig());
        Grabar.setToolTip("Grabar la Nueva o Editada Unidad");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UIUnidad.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UIUnidad.getLCNombreBuscar());

        window.add(Form);//, new FormData("100%"));
        window.addButton(Nuevo);
        window.addButton(Editar);
        window.addButton(Eliminar);
        window.addButton(Grabar);
        window.addButton(Cancelar);
        window.setIcon(IconHelper.create("catalogo-unidad"));
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UIUnidad.getTFNombre());
        window.center();
        ActivarBotonNuevo();
    }
    private void ActivarBotonNuevo(){
        modeloUnidad = new ModeloUnidad();
        UIUnidad.setClear();
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        UIUnidad.setEnableBotonBuscar(true);
        UIUnidad.setReadOnlyTFNombre(false);
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        UIUnidad.setEnableBotonBuscar(false);
        UIUnidad.setReadOnlyTFNombre(true);
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
                UIUnidad.setClear();
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
            }
            
            if(ce.getButton().getId().equals("editar")){
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
                UIUnidad.setEnableBotonBuscar(false);
                UIUnidad.setReadOnlyTFNombre(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                MessageBox.confirm("Advertencia","Eliminando esta Unidad,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UIUnidad.setModeloUnidad(modeloUnidad);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    UIUnidad.setClear();
                    ActivarBotonNuevo();
                    UltimoIdBoton = new String();
                }
                
            }

            if(ce.getButton().getId().equals("grabar")){
                window.mask("Espere un momento");

                modeloUnidad.setNombre(UIUnidad.getTFNombre().getValue());

                if(UltimoIdBoton == "nuevo")
                    RPCUnidad.Agregar(modeloUnidad.getNombre(), RetornoEventoAgregarEditarElimianr);
                if(UltimoIdBoton == "editar")
                    RPCUnidad.Editar(modeloUnidad, RetornoEventoAgregarEditarElimianr);
            }

        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                modeloUnidad = WindowGridBuscar.getModeloUnidadSeleccionado();
                UIUnidad.setModeloUnidad(modeloUnidad);
                ActivarBotonEditarModificar();
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && modeloUnidad.getId() > 0){
                RPCUnidad.Elimiar(modeloUnidad.getId(), RetornoEventoAgregarEditarElimianr);
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
                        MessageBox.alert("Grabada","El grabado de la unidad se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion de la unidad se realizo con exito", null);
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
