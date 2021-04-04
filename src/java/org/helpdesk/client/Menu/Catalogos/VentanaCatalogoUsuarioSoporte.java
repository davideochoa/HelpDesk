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
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarUsuariosSoporte;
import org.helpdesk.client.InterfazUsuario.UIUsuarioSoporte;
import org.helpdesk.client.ModeloDatos.BaseModelUsuarioSoporte;
import org.helpdesk.client.ModeloDatos.ModeloAreasSoporte_Asignadas_NoAsignadas;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporteAsync;

/**
 *
 * @author Administrador
 */
public class VentanaCatalogoUsuarioSoporte{
    UIUsuarioSoporte UIUsuarioSoporte;
    private Window window = new Window();

    private Button Nuevo = new Button();
    private Button Editar = new Button();
    private Button Eliminar = new Button();
    private Button Grabar = new Button();
    private Button Cancelar = new Button();

    private WindowGridFiltrarUsuariosSoporte WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private String UltimoIdBoton = new String();
    private RPCUsuarioSoporteAsync RPCUsuarioSoporte;
    BaseModelUsuarioSoporte BMUsuarioSoporte = new BaseModelUsuarioSoporte();

    public VentanaCatalogoUsuarioSoporte(RPCUsuarioSoporteAsync RPCUsuarioSoporte){
        this.RPCUsuarioSoporte = RPCUsuarioSoporte;
        WindowGridBuscar = new WindowGridFiltrarUsuariosSoporte(RPCUsuarioSoporte);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIUsuarioSoporte = new UIUsuarioSoporte(100,RPCUsuarioSoporte);
        UIUsuarioSoporte.setGridBuscar(WindowGridBuscar);

        window.setHeading("Catalogo Usuarios de Soporte");
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setBlinkModal(true);
        window.setClosable(true);
        window.setResizable(false);
        window.setModal(true);
        window.setOnEsc(true);        
        window.setWidth(500);
        window.setButtonAlign(HorizontalAlignment.CENTER);

        Nuevo.setText("Nuevo");
        Nuevo.setId("nuevo");
        Nuevo.setIconAlign(IconAlign.RIGHT);
        Nuevo.setIcon(IconHelper.create("agregar"));
        Nuevo.setToolTip(UIUsuarioSoporte.getToolTipConfig());
        Nuevo.setToolTip("Crear un Nuevo Usuario de Soporte");
        Nuevo.addSelectionListener(eventoBoton);

        Editar.setText("Editar");
        Editar.setId("editar");
        Editar.setIconAlign(IconAlign.RIGHT);
        Editar.setIcon(IconHelper.create("editar"));
        Editar.setToolTip(UIUsuarioSoporte.getToolTipConfig());
        Editar.setToolTip("Edita un Nuevo Usuario de Soporte");
        Editar.addSelectionListener(eventoBoton);

        Eliminar.setText("Eliminar");
        Eliminar.setId("eliminar");
        Eliminar.setIconAlign(IconAlign.RIGHT);
        Eliminar.setIcon(IconHelper.create("eliminar"));
        Eliminar.setToolTip(UIUsuarioSoporte.getToolTipConfig());
        Eliminar.setToolTip("Elimina un Nuevo Usuario de Soporte Existente");
        Eliminar.addSelectionListener(eventoBoton);

        Grabar.setText("Grabar");
        Grabar.setId("grabar");
        Grabar.setIconAlign(IconAlign.RIGHT);
        Grabar.setIcon(IconHelper.create("grabar"));
        Grabar.setToolTip(UIUsuarioSoporte.getToolTipConfig());
        Grabar.setToolTip("Grabar el Nuevo o Editada Usuario de Soporte");
        Grabar.addSelectionListener(eventoBoton);

        Cancelar.setText("Cancelar");
        Cancelar.setId("cancelar");
        Cancelar.setIconAlign(IconAlign.RIGHT);
        Cancelar.setIcon(IconHelper.create("cancelar"));
        Cancelar.setToolTip(UIUsuarioSoporte.getToolTipConfig());
        Cancelar.setToolTip("Cancela cualquier Accion");
        Cancelar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.setLabelWidth(110);
        Form.add(UIUsuarioSoporte.getLCNombreBuscar());
        Form.add(UIUsuarioSoporte.getTextFieldNombreUsuario());
        Form.add(UIUsuarioSoporte.getTextFieldCorreo());
        Form.add(UIUsuarioSoporte.getCehckBoxEsAdministrador(),new FormData("26%"));
        Form.add(UIUsuarioSoporte.getCehckBoxEsReseteadoPassword(),new FormData("26%"));
        Form.add(UIUsuarioSoporte.getDualListField(),new FormData("100%"));

        window.add(Form);//, new FormData("100%"));
        window.addButton(Nuevo);
        window.addButton(Editar);
        window.addButton(Eliminar);
        window.addButton(Grabar);
        window.addButton(Cancelar);
        window.setIcon(IconHelper.create("catalogo-usuarios"));
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UIUsuarioSoporte.getTFNombre());
        window.center();
        ActivarBotonNuevo();
    }
    private void ActivarBotonNuevo(){
        UIUsuarioSoporte.setClear();
        Nuevo.setEnabled(true);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(false);
        UIUsuarioSoporte.setEnableBotonBuscar(true);
        UIUsuarioSoporte.setReadOnlyTFNombre(false);
    }
    private void ActivarBotonEditarModificar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(true);
        Eliminar.setEnabled(true);
        Grabar.setEnabled(false);
        Cancelar.setEnabled(true);
        UIUsuarioSoporte.setEnableBotonBuscar(false);
        UIUsuarioSoporte.setReadOnlyTFNombre(false);
    }
    private void ActivarBotonGrabarCancelar(){
        Nuevo.setEnabled(false);
        Editar.setEnabled(false);
        Eliminar.setEnabled(false);
        Grabar.setEnabled(true);
        Cancelar.setEnabled(true);
        UIUsuarioSoporte.setEnableBotonBuscar(false);
        UIUsuarioSoporte.setReadOnlyTFNombre(false);
    }

    private class EVENTO_BOTON extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            if(ce.getButton().getId().equals("nuevo")){
                UIUsuarioSoporte.setClear();
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
            }
            
            if(ce.getButton().getId().equals("editar")){
                ActivarBotonGrabarCancelar();
                UltimoIdBoton = ce.getButton().getId();
                UIUsuarioSoporte.setEnableBotonBuscar(false);
                UIUsuarioSoporte.setReadOnlyTFNombre(false);
            }
            
            if(ce.getButton().getId().equals("eliminar")){
                window.mask("Espere un momento");
                MessageBox.confirm("Advertencia","Eliminando este Usuario de Soporte,"+
                                "ya no tendra acceso a el",new CALLBAK_SI_NO_ELIMINAR());
            }

            if(ce.getButton().getId().equals("cancelar")){
                if(UltimoIdBoton.equals("editar")){
                    ActivarBotonEditarModificar();
                    UIUsuarioSoporte.setBaseModel(BMUsuarioSoporte);
                    UltimoIdBoton = "nuevo";
                }
                else{
                    UIUsuarioSoporte.setClear();
                    ActivarBotonNuevo();
                    UltimoIdBoton = new String();
                }                
            }

            if(ce.getButton().getId().equals("grabar")){
                window.mask("Espere un momento");
                BMUsuarioSoporte = UIUsuarioSoporte.getBaseModel();
                ModeloAreasSoporte_Asignadas_NoAsignadas Areas = UIUsuarioSoporte.getAreasAsignadas();

                //GWT.log("Size no asignada:"+UIUsuarioSoporte.getAreasAsignadas().getAreasNoAsignadas().length);
                //GWT.log("Size si asignada:"+UIUsuarioSoporte.getAreasAsignadas().getAreasSiAsignadas().length);

                if(UltimoIdBoton == "nuevo")
                    RPCUsuarioSoporte.Agregar(BMUsuarioSoporte.getNombrePropio(),
                                                BMUsuarioSoporte.getCorreo(),
                                                BMUsuarioSoporte.getNombreUsuario(),
                                                BMUsuarioSoporte.getEsAdministrador(),
                                                Areas,
                                                RetornoEventoAgregarEditarElimianr);
                if(UltimoIdBoton == "editar")
                    RPCUsuarioSoporte.Editar(BMUsuarioSoporte.getId(),
                                                BMUsuarioSoporte.getNombrePropio(),
                                                BMUsuarioSoporte.getCorreo(),
                                                BMUsuarioSoporte.getNombreUsuario(),
                                                BMUsuarioSoporte.getEsAdministrador(),
                                                BMUsuarioSoporte.getEsReseteadoPassword(),
                                                Areas,
                                                RetornoEventoAgregarEditarElimianr);
                //window.unmask();
            }

        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                ActivarBotonEditarModificar();
                BMUsuarioSoporte = WindowGridBuscar.getBMSeleccionado();
                UIUsuarioSoporte.setBaseModel(BMUsuarioSoporte);
                RPCUsuarioSoporte.getListadosAreasAsignadas(BMUsuarioSoporte.getId(), new RETORNO_GET_LISTADOS_AREAS_ASIGNADAS());
            }
        }
    }
    private class CALLBAK_SI_NO_ELIMINAR implements  Listener<MessageBoxEvent>{
        public void handleEvent(MessageBoxEvent be) {
            if(be.getButtonClicked().getItemId()== "yes" && BMUsuarioSoporte.getId() > 0){

                RPCUsuarioSoporte.Elimiar(BMUsuarioSoporte.getId(), RetornoEventoAgregarEditarElimianr);
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
                        MessageBox.alert("Grabada","El grabado del Usuario de Soporte se realizo con exito", null);
                    if(UltimoIdBoton.equals("editar"))
                        MessageBox.alert("Edicion","La edicion del Usuario de Soporte se realizo con exito", null);
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
    private class RETORNO_GET_LISTADOS_AREAS_ASIGNADAS implements AsyncCallback<ModeloAreasSoporte_Asignadas_NoAsignadas>{
        public void onSuccess(ModeloAreasSoporte_Asignadas_NoAsignadas result) {
           ModeloAreasSoporte_Asignadas_NoAsignadas areas = (ModeloAreasSoporte_Asignadas_NoAsignadas)result;
           
           UIUsuarioSoporte.setAreasAsignadas(areas);
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
