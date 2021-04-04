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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarUnidades;
import org.helpdesk.client.ModeloDatos.BaseModelUnidad;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;

/**
 *
 * @author Administrador
 */
public class UIUnidad extends ConfiguracionUI{
    private LayoutContainer ContenedorNombreBuscar = new LayoutContainer();
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private TextField<String> TextFieldNombre = new TextField<String>();
    private ComboBox<BaseModelUnidad> ComboBoxNombre = new ComboBox<BaseModelUnidad >();
    ListStore<BaseModelUnidad> ListStoreUnidad = new ListStore<BaseModelUnidad>();    
    private String DescripcionToolTipUnidad = "Ubicacion fisica (Hospital,Centro de Salud,Unidad Administrativa)";
    private String DescripcionToolTipBuscarUnidad = "Buscar Unidad";
    private String DescripcionToolTipActualizarUnidad = "Actualizar Listado Unidades";
    private WindowGridFiltrarUnidades WindowGridBuscar;// = new WindowGridListadoUnidades();
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();
    private EVENTO_BOTON_BUSCAR EVENTO_PRESIONA_BOTON_BUSCAR = new EVENTO_BOTON_BUSCAR();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RPCUnidadAsync RPCUnidad;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();    
    private Button Buscar = new Button();
    private Button Actualizar = new Button();
    private ModeloUnidad modeloUnidad = new ModeloUnidad();
    private BaseModelUnidad BMUnidad = new BaseModelUnidad();
    public UIUnidad(int AnchoEtiqueta,RPCUnidadAsync RPCUnidad){
        this.RPCUnidad = RPCUnidad;

        TextFieldNombre.setAllowBlank(true);
        TextFieldNombre.setId("Nombre");
        TextFieldNombre.setSelectOnFocus(true);
        TextFieldNombre.setFieldLabel("Unidad");
        TextFieldNombre.setToolTip(getToolTipConfig());
        TextFieldNombre.setToolTip(DescripcionToolTipUnidad);

        Buscar.setToolTip(getToolTipConfig());
        Buscar.setToolTip(DescripcionToolTipBuscarUnidad);
        Buscar.setIcon(IconHelper.create("buscar"));
        Buscar.addSelectionListener(EVENTO_PRESIONA_BOTON_BUSCAR);

        ContenedorNombreBuscar = new TextField_ComboBox_Buton(AnchoEtiqueta,TextFieldNombre,Buscar);
        ContenedorNombreBuscar.setBorders(false);

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(false);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("unidad");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Unidad");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(DescripcionToolTipUnidad);
        ComboBoxNombre.setStore(ListStoreUnidad);
        ComboBoxNombre.setEditable(false);
        ComboBoxNombre.setTriggerAction(TriggerAction.ALL);

        //ComboBoxNombre.addSelectionChangedListener(EventoSeleccionComboboxNombre);
        
        Actualizar.setToolTip(getToolTipConfig());
        Actualizar.setToolTip(DescripcionToolTipActualizarUnidad);
        Actualizar.setIcon(IconHelper.create("actualizar"));
        Actualizar.addSelectionListener(EventoPresionaBotonActualizar);

        ContenedorComboBoxActualizar = new TextField_ComboBox_Buton(AnchoEtiqueta,ComboBoxNombre,Actualizar);
        ContenedorComboBoxActualizar.setBorders(false);

        ActualizarListStore();
    }
    public void setGridBuscar(WindowGridFiltrarUnidades WindowGridBuscar){
        this.WindowGridBuscar = WindowGridBuscar;
    }
    public BaseModelUnidad getBaseModelComboBox(){
        BaseModelUnidad BMU;
        if(ComboBoxNombre.getSelection().isEmpty())
            BMU = new BaseModelUnidad(0,"");
        else
            BMU = ComboBoxNombre.getSelection().get(0);

        return BMU;
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
    public ComboBox<BaseModelUnidad> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setModeloUnidad(ModeloUnidad modeloUnidad){
        this.modeloUnidad = modeloUnidad;
        TextFieldNombre.setValue(modeloUnidad.getNombre());
    }
    public void setClear(){        
        ComboBoxNombre.clearSelections();
        TextFieldNombre.clear();
        TextFieldNombre.focus();
        modeloUnidad = new ModeloUnidad();
        BMUnidad = new BaseModelUnidad();
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
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelUnidad> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }
    public BaseModelUnidad getBaseModel(){
        return BMUnidad;
    }

    private void ActualizarListStore(){
        //ContenedorComboBoxActualizar.mask("Un Momento, Actualizando");
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCUnidad.getListado("",RetornoActualizarListStore);
    }
    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelUnidad>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelUnidad> se) {
            BMUnidad = se.getSelectedItem();
        }
    }
    private class EVENTO_BOTON_BUSCAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            String CadenaFiltro = TextFieldNombre.getValue();

            if(CadenaFiltro != null && CadenaFiltro.length() > 0)
                WindowGridBuscar.show(CadenaFiltro);
            else
                MessageBox.alert("Alerta", "Debe teclar el nombre de alguna unidad para realizar la busqueda",null);
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
            ListStoreUnidad = new ListStore<BaseModelUnidad>();

            
            int posy = 0; 
            while(posy < obj.length){                
                ListStoreUnidad.add(new BaseModelUnidad(Integer.parseInt(obj[posy][0].toString()),
                                                        obj[posy][1].toString()));
                posy++;
            }
            ComboBoxNombre.setStore(ListStoreUnidad);
            //ContenedorComboBoxActualizar.unmask();
            ComboBoxNombre.unmask();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }

}
