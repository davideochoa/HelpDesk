/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.InterfazUsuario;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarPrioridad;
import org.helpdesk.client.ModeloDatos.BaseModelPrioridad;
import org.helpdesk.client.ModeloDatos.ModeloPrioridad;
import org.helpdesk.client.ServiciosRPC.RPCPrioridadAsync;

/**
 *
 * @author Administrador
 */
public class UIPrioridad extends ConfiguracionUI{
    private LayoutContainer ContenedorNombreBuscar = new LayoutContainer();
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private TextField<String> TextFieldNombre = new TextField<String>();
    private ComboBox<BaseModelPrioridad> ComboBoxNombre = new ComboBox<BaseModelPrioridad >();
    private ListStore<BaseModelPrioridad> ListStore = new ListStore<BaseModelPrioridad>();
    private String MsgTTGeneral = "Determinar el nivel de afectacion al usuario (Alto,Medio,Bajo,Otro)";
    private String MsgToolTipBotonBuscar = "Buscar Tipo de Prioridades";
    private String MsqToolTipBotonActualizar = "Actualizar Listado Tipo de Prioridades";
    private WindowGridFiltrarPrioridad WindowGridBuscar;// = new WindowGridListadoUnidades();
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();
    private EVENTO_BOTON_BUSCAR EVENTO_PRESIONA_BOTON_BUSCAR = new EVENTO_BOTON_BUSCAR();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RPCPrioridadAsync RPCPrioridad;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();    
    private Button Buscar = new Button();
    private Button Actualizar = new Button();
    private ModeloPrioridad ModeloPrioridad = new ModeloPrioridad();
    private BaseModelPrioridad BMPrioridad = new BaseModelPrioridad();
    public UIPrioridad(int AnchoEtiqueta,RPCPrioridadAsync RPCPrioridad){
        this.RPCPrioridad = RPCPrioridad;

        TextFieldNombre.setAllowBlank(true);
        TextFieldNombre.setId("Nombre");
        TextFieldNombre.setSelectOnFocus(true);
        TextFieldNombre.setFieldLabel("Prioridad");
        TextFieldNombre.setToolTip(getToolTipConfig());
        TextFieldNombre.setToolTip(MsgTTGeneral);

        Buscar.setToolTip(getToolTipConfig());
        Buscar.setToolTip(MsgToolTipBotonBuscar);
        Buscar.setIcon(IconHelper.create("buscar"));
        Buscar.addSelectionListener(EVENTO_PRESIONA_BOTON_BUSCAR);

        ContenedorNombreBuscar = new TextField_ComboBox_Buton(AnchoEtiqueta,TextFieldNombre,Buscar);
        ContenedorNombreBuscar.setBorders(false);

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(true);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("prioridad");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Prioridad");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(MsgTTGeneral);
        ComboBoxNombre.setStore(ListStore);
        ComboBoxNombre.setEditable(false);
        ComboBoxNombre.setTriggerAction(TriggerAction.ALL);

        //ComboBoxNombre.addSelectionChangedListener(EventoSeleccionComboboxNombre);
        
        Actualizar.setToolTip(getToolTipConfig());
        Actualizar.setToolTip(MsqToolTipBotonActualizar);
        Actualizar.setIcon(IconHelper.create("actualizar"));
        Actualizar.addSelectionListener(EventoPresionaBotonActualizar);

        ContenedorComboBoxActualizar = new TextField_ComboBox_Buton(AnchoEtiqueta,ComboBoxNombre,Actualizar);
        ContenedorComboBoxActualizar.setBorders(false);

        ActualizarListStore();
    }
    public BaseModelPrioridad getBaseModelComboBox(){
        BaseModelPrioridad BMU;
        if(ComboBoxNombre.getSelection().isEmpty())
            BMU = new BaseModelPrioridad(0,"");
        else
            BMU = ComboBoxNombre.getSelection().get(0);

        return BMU;
    }
    public void setGridBuscar(WindowGridFiltrarPrioridad WindowGridBuscar){
        this.WindowGridBuscar = WindowGridBuscar;
    }
    public LayoutContainer getLCNombreBuscar(){
        return ContenedorNombreBuscar;
    }
    public LayoutContainer getLCComboBoxActualizar(){
        return ContenedorComboBoxActualizar;
    }
    public TextField<String> getTFNombre(){
        return TextFieldNombre;
    }
    public ComboBox<BaseModelPrioridad> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setModelo(ModeloPrioridad ModeloDatos){
        this.ModeloPrioridad = ModeloDatos;
        TextFieldNombre.setValue(ModeloDatos.getNombre());
    }
    public void setClear(){        
        ComboBoxNombre.clearSelections();
        TextFieldNombre.clear();
        TextFieldNombre.focus();
        ModeloPrioridad = new ModeloPrioridad();
        BMPrioridad = new BaseModelPrioridad();
    }
    public void setEnableBotonBuscar(boolean estado){
        Buscar.setEnabled(estado);
    }
    public void setEnableBotonActualizar(boolean estado){
        Actualizar.setEnabled(estado);
    }
    public void setReadOnlyTFNombre(boolean estado){
        //TextFieldNombre.setEnabled(estado);
        TextFieldNombre.setReadOnly(estado);
    }
    public void setFocusTFNombre(){
        TextFieldNombre.focus();
    }
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelPrioridad> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }
    public BaseModelPrioridad getBaseModel(){
        return BMPrioridad;
    }

    private void ActualizarListStore(){
        
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCPrioridad.getListado("",RetornoActualizarListStore);
    }
    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelPrioridad>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelPrioridad> se) {
            BMPrioridad = se.getSelectedItem();
        }
    }
    private class EVENTO_BOTON_BUSCAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            String CadenaFiltro = TextFieldNombre.getValue();

            if(CadenaFiltro != null && CadenaFiltro.length() > 0)
                WindowGridBuscar.show(CadenaFiltro);
            else
                MessageBox.alert("Alerta", "Debe teclar el nombre de alguna prioridad para realizar la busqueda",null);
        }
    }
    private class EVENTO_BOTON_ACTUALIZAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            ActualizarListStore();
        }
    }
    
    private class RETORNO_ACTUALIZAR_LISTSTORE implements AsyncCallback<String[][]>{
        public void onSuccess(String[][] result) {
            String obj[][] = (String[][])result;
            ListStore = new ListStore<BaseModelPrioridad>();

            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelPrioridad(Integer.parseInt(obj[posy][0].toString()),
                                                        obj[posy][1].toString()));
                posy++;
            }
            ComboBoxNombre.setStore(ListStore);
            //ContenedorComboBoxActualizar.unmask();
            ComboBoxNombre.unmask();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }

}
