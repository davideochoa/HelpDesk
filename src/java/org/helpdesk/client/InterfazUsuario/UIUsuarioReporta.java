/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.InterfazUsuario;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowBuscarUsuarioReporta;
import org.helpdesk.client.ModeloDatos.BaseModelUsuarioReporta;
import org.helpdesk.client.ModeloDatos.ModeloMarca;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReportaAsync;

/**
 *
 * @author Administrador
 */
public class UIUsuarioReporta extends ConfiguracionUI{    
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();  
    private LayoutContainer ContenedorTextFieldBuscar = new LayoutContainer(); 
    private ComboBox<BaseModelUsuarioReporta> ComboBoxNombre = new ComboBox<BaseModelUsuarioReporta >();
    private TextField<String> TFNombre = new TextField<String>();
    private ListStore<BaseModelUsuarioReporta> ListStore = new ListStore<BaseModelUsuarioReporta>();
    private String MsgTTGeneral = "Usuario de contacto para cierre de folio";
    private String MsqToolTipBotonActualizar = "Buscar Usuarios que Reportan";
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();    
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();    
    private RPCUsuarioReportaAsync RPCUsuarioReporta;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();        
    private Button Actualizar = new Button();
    private Button Buscar = new Button();
    private ModeloMarca ModeloUsuarioReporta = new ModeloMarca();
    private BaseModelUsuarioReporta BMUsuarioReporta = new BaseModelUsuarioReporta();
    private WindowBuscarUsuarioReporta BBuscarUsuarioReporta = new WindowBuscarUsuarioReporta();
    public UIUsuarioReporta(int AnchoEtiqueta,RPCUsuarioReportaAsync RPCUsuarioReporta){
        this.RPCUsuarioReporta = RPCUsuarioReporta;

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(false);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("nombre");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Usuario Reporta");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(MsgTTGeneral);
        ComboBoxNombre.setStore(ListStore);
        ComboBoxNombre.setEditable(true);
        ComboBoxNombre.setTriggerAction(TriggerAction.ALL);
        
        TFNombre.setAllowBlank(true);
        TFNombre.setId("Nombre");
        TFNombre.setSelectOnFocus(true);
        TFNombre.setFieldLabel("Usuario Reporta");
        TFNombre.setToolTip(getToolTipConfig());
        //TFNombre.setToolTip(DescripcionToolTipUnidad);
        
        Actualizar.setToolTip(getToolTipConfig());
        Actualizar.setToolTip(MsqToolTipBotonActualizar);
        Actualizar.setIcon(IconHelper.create("actualizar"));
        Actualizar.addSelectionListener(EventoPresionaBotonActualizar);
        
        Buscar.setToolTip(getToolTipConfig());
        Buscar.setToolTip(MsqToolTipBotonActualizar);
        Buscar.setIcon(IconHelper.create("buscar"));
        Buscar.addSelectionListener(new EVENTO_BOTON_BUSCAR());

        ContenedorComboBoxActualizar = new TextField_ComboBox_Buton(AnchoEtiqueta,ComboBoxNombre,Actualizar);
        ContenedorComboBoxActualizar.setBorders(false);
        
        ContenedorTextFieldBuscar = new TextField_ComboBox_Buton(AnchoEtiqueta,TFNombre,Buscar);
        ContenedorTextFieldBuscar.setBorders(false);

        ActualizarListStore();
        BBuscarUsuarioReporta.addListener(Events.Hide, new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent e) {
                BMUsuarioReporta = BBuscarUsuarioReporta.getSeleccion();  
                TFNombre.setValue(BMUsuarioReporta.getNombre());
                TFNombre.setRawValue(BMUsuarioReporta.getNombre());
                BBuscarUsuarioReporta.inicializar();
            }
        });
        
        BBuscarUsuarioReporta.addListener(Events.Show, new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent e) {
                BBuscarUsuarioReporta.inicializar();
                BBuscarUsuarioReporta.actualizar(TFNombre.getValue());
            }
        });
        
    }
    public LayoutContainer getLCComboBoxActualizar(){
        return ContenedorComboBoxActualizar;
    }
    public LayoutContainer getLCTextFieldBuscar(){
        return ContenedorTextFieldBuscar;
    }
    
    public BaseModelUsuarioReporta getBaseModelComboBox(){
        return ComboBoxNombre.getSelection().get(0);
    }
    public ComboBox<BaseModelUsuarioReporta> getCBNombre(){
        return ComboBoxNombre;
    }
    public TextField<String> getTFNombre(){
        return TFNombre;
    }
    public void setClear(){        
        ComboBoxNombre.clearSelections();
        ModeloUsuarioReporta = new ModeloMarca();
        BMUsuarioReporta = new BaseModelUsuarioReporta();
        TFNombre.clear();
        
    }
    public void setEnableBotonActualizar(boolean estado){
        Actualizar.setEnabled(estado);
    }
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelUsuarioReporta> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }
    public BaseModelUsuarioReporta getBaseModel(){
        return BMUsuarioReporta;
    }

    private void ActualizarListStore(){        
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCUsuarioReporta.getListado(RetornoActualizarListStore);
    }
    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelUsuarioReporta>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelUsuarioReporta> se) {
            BMUsuarioReporta = se.getSelectedItem();
        }
    }
    private class EVENTO_BOTON_ACTUALIZAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            ActualizarListStore();
        }
    }
    private class EVENTO_BOTON_BUSCAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            if(TFNombre != null && TFNombre.getValue().length() > 0)
                BBuscarUsuarioReporta.setVisible(true);                            
        }
    }
    
    private class RETORNO_ACTUALIZAR_LISTSTORE implements AsyncCallback<String[][]>{
        public void onSuccess(String[][] result) {
            String obj[][] = (String[][])result;
            ListStore = new ListStore<BaseModelUsuarioReporta>();
            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelUsuarioReporta(obj[posy][0].toString()));
                posy++;
            }

            ComboBoxNombre.setStore(ListStore);
            ComboBoxNombre.unmask();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
