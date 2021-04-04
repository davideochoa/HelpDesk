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
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.helpdesk.client.Buscar.WindowGridFiltrarTipoIncidencia;
import org.helpdesk.client.ModeloDatos.BaseModelIncidencia;
import org.helpdesk.client.ModeloDatos.ModeloTipoIncidencia;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;

/**
 *
 * @author Administrador
 */
public class UITipoIncidencia extends ConfiguracionUI{
    private LayoutContainer ContenedorNombreBuscar = new LayoutContainer();
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private TextField<String> TextFieldNombre = new TextField<String>();
    private ComboBox<BaseModelIncidencia> ComboBoxNombre = new ComboBox<BaseModelIncidencia >();
    ListStore<BaseModelIncidencia> ListStore = new ListStore<BaseModelIncidencia>();
    private String MsgTTTipoReporte = "Perminte determinar el tipo de Incidencia(Hard,Soft,Otro)";
    private String MsgToolTipBotonBuscar = "Buscar Tipo Incidencia";
    private String MsqToolTipBotonActualizar = "Actualizar Listado Tipo Incidencia";
    private WindowGridFiltrarTipoIncidencia WindowGridBuscar;// = new WindowGridListadoUnidades();
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();
    private EVENTO_BOTON_BUSCAR EVENTO_PRESIONA_BOTON_BUSCAR = new EVENTO_BOTON_BUSCAR();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RPCTipoIncidenciaAsync RPCTipoIncidencia;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();    
    private Button Buscar = new Button();
    private Button Actualizar = new Button();
    private ModeloTipoIncidencia ModeloTipoIncidencia = new ModeloTipoIncidencia();
    private BaseModelIncidencia BMTipoIncidencia = new BaseModelIncidencia();
    public UITipoIncidencia(int AnchoEtiqueta,RPCTipoIncidenciaAsync RPCTipoIncidencia){
        this.RPCTipoIncidencia = RPCTipoIncidencia;

        TextFieldNombre.setAllowBlank(true);
        TextFieldNombre.setId("Nombre");
        TextFieldNombre.setSelectOnFocus(true);
        TextFieldNombre.setFieldLabel("Incidencia");
        TextFieldNombre.setToolTip(getToolTipConfig());
        TextFieldNombre.setToolTip(MsgTTTipoReporte);

        Buscar.setToolTip(getToolTipConfig());
        Buscar.setToolTip(MsgToolTipBotonBuscar);
        Buscar.setIcon(IconHelper.create("buscar"));
        Buscar.addSelectionListener(EVENTO_PRESIONA_BOTON_BUSCAR);

        ContenedorNombreBuscar = new TextField_ComboBox_Buton(AnchoEtiqueta,TextFieldNombre,Buscar);
        ContenedorNombreBuscar.setBorders(false);

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(false);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("tipo_incidencia");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Tipo Incidencia");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(MsgTTTipoReporte);
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
    public void setGridBuscar(WindowGridFiltrarTipoIncidencia WindowGridBuscar){
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
    public ComboBox<BaseModelIncidencia> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setModelo(ModeloTipoIncidencia ModeloDatos){
        this.ModeloTipoIncidencia = ModeloDatos;
        TextFieldNombre.setValue(ModeloDatos.getNombre());
    }
    public BaseModelIncidencia getBaseModelComboBox(){
        BaseModelIncidencia BMU;
        if(ComboBoxNombre.getSelection().isEmpty())
            BMU = new BaseModelIncidencia(0,"");
        else
            BMU = ComboBoxNombre.getSelection().get(0);

        return BMU;
    }
    public void setClear(){
        ComboBoxNombre.clearSelections();
        TextFieldNombre.clear();
        TextFieldNombre.focus();
        ModeloTipoIncidencia = new ModeloTipoIncidencia();
        BMTipoIncidencia = new BaseModelIncidencia();
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
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelIncidencia> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }
    public BaseModelIncidencia getBaseModel(){
        return BMTipoIncidencia;
    }
    
    public void setEtiqueta(String etiqueta){
        TextFieldNombre.setFieldLabel(etiqueta);
        ComboBoxNombre.setFieldLabel(etiqueta);
    }
    
    public void setId(int Id){
        ListStore<BaseModelIncidencia> LS = ComboBoxNombre.getStore();
        BaseModelIncidencia BMI = LS.findModel("Id",Id);

        ComboBoxNombre.setValue(BMI);
    }
    private void ActualizarListStore(){
        
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCTipoIncidencia.getListado("",RetornoActualizarListStore);
    }
    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelIncidencia>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelIncidencia> se) {
            BMTipoIncidencia = se.getSelectedItem();
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
            ListStore = new ListStore<BaseModelIncidencia>();

            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelIncidencia(Integer.parseInt(obj[posy][0].toString()),
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
