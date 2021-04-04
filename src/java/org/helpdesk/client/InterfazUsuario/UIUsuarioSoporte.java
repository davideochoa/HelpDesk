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
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.DualListField.Mode;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarUsuariosSoporte;
import org.helpdesk.client.ModeloDatos.BaseModelAreaSoporte;
import org.helpdesk.client.ModeloDatos.BaseModelUsuarioSoporte;
import org.helpdesk.client.ModeloDatos.ModeloAreasSoporte_Asignadas_NoAsignadas;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporteAsync;

/**
 *
 * @author Administrador
 */
public class UIUsuarioSoporte extends ConfiguracionUI{
    private LayoutContainer ContenedorNombreBuscar = new LayoutContainer();
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private TextField<String> TextFieldNombrePropio = new TextField<String>();
    private TextField<String> TextFieldCorreo = new TextField<String>();
    private TextField<String> TextFieldNombreUsuario = new TextField<String>();
    private CheckBox CehckBoxEsAdministrador = new CheckBox();
    private CheckBox CehckBoxEsReseteadoPassword = new CheckBox();

    private ComboBox<BaseModelUsuarioSoporte> ComboBoxNombre = new ComboBox<BaseModelUsuarioSoporte >();
    private ListStore<BaseModelUsuarioSoporte> ListStore = new ListStore<BaseModelUsuarioSoporte>();

    private ComboBox<BaseModelAreaSoporte> ComboBoxNombreSiAsignadas = new ComboBox<BaseModelAreaSoporte >();
    private ComboBox<BaseModelAreaSoporte> ComboBoxNombreNoAsignadas = new ComboBox<BaseModelAreaSoporte >();

    private DualListField<BaseModelAreaSoporte> DLFAreasSoporte = new DualListField<BaseModelAreaSoporte>();
    private ListField<BaseModelAreaSoporte> LFAreasNoAsignadas;
    private ListField<BaseModelAreaSoporte> LFAreasSiAsignadas;

    private ListStore<BaseModelAreaSoporte> LSFromAreaNoAsignadas = new ListStore<BaseModelAreaSoporte>();
    private ListStore<BaseModelAreaSoporte> LSToAreaSiAsignadas = new ListStore<BaseModelAreaSoporte>();

    private String MsgTTGeneral = "Determinar el Usuario que dara el Soporte";
    private String MsgToolTipBotonBuscar = "Buscar Usuario de Soporte";
    private String MsqToolTipBotonActualizar = "Actualizar Listado de Usuarios de Soporte";
    private WindowGridFiltrarUsuariosSoporte WindowGridBuscar;// = new WindowGridListadoUnidades();
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();
    private EVENTO_BOTON_BUSCAR EVENTO_PRESIONA_BOTON_BUSCAR = new EVENTO_BOTON_BUSCAR();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RPCUsuarioSoporteAsync RPCUsuarioSoporte;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();    
    private Button Buscar = new Button();
    private Button Actualizar = new Button();
    private BaseModelUsuarioSoporte BMUsuarioSoporte = new BaseModelUsuarioSoporte();
    private ModeloAreasSoporte_Asignadas_NoAsignadas ModeloAreasSoporte_Asignadas_NoAsignadas;
    public UIUsuarioSoporte(int AnchoEtiqueta,RPCUsuarioSoporteAsync RPCUsuarioSoporte){
        this.RPCUsuarioSoporte = RPCUsuarioSoporte;

        TextFieldNombrePropio.setAllowBlank(true);
        TextFieldNombrePropio.setId("Nombre");
        TextFieldNombrePropio.setSelectOnFocus(true);
        TextFieldNombrePropio.setFieldLabel("Nombre Propio");
        TextFieldNombrePropio.setToolTip(getToolTipConfig());
        TextFieldNombrePropio.setToolTip(MsgTTGeneral);

        TextFieldCorreo.setAllowBlank(true);
        TextFieldCorreo.setId("Correo");
        TextFieldCorreo.setSelectOnFocus(true);
        TextFieldCorreo.setFieldLabel("Correo");
        TextFieldCorreo.setToolTip(getToolTipConfig());

        TextFieldNombreUsuario.setAllowBlank(true);
        TextFieldNombreUsuario.setId("NombreUsuario");
        TextFieldNombreUsuario.setSelectOnFocus(true);
        TextFieldNombreUsuario.setFieldLabel("Nombre Usuario");
        TextFieldNombreUsuario.setToolTip(getToolTipConfig());

        CehckBoxEsAdministrador.setId("EsAdministrador");
        CehckBoxEsAdministrador.setFieldLabel("Administrador");
        CehckBoxEsAdministrador.setToolTip(getToolTipConfig());

        CehckBoxEsReseteadoPassword.setId("EsReseteadoPassword");
        CehckBoxEsReseteadoPassword.setFieldLabel("Reset Password");
        CehckBoxEsReseteadoPassword.setToolTip(getToolTipConfig());

        Buscar.setToolTip(getToolTipConfig());
        Buscar.setToolTip(MsgToolTipBotonBuscar);
        Buscar.setIcon(IconHelper.create("buscar"));
        Buscar.addSelectionListener(EVENTO_PRESIONA_BOTON_BUSCAR);

        ContenedorNombreBuscar = new TextField_ComboBox_Buton(AnchoEtiqueta,TextFieldNombrePropio,Buscar);
        ContenedorNombreBuscar.setBorders(false);

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(true);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("NombrePropio");
        ComboBoxNombre.setDisplayField("NombrePropio");
        ComboBoxNombre.setFieldLabel("Usuario de Soporte");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(MsgTTGeneral);
        ComboBoxNombre.setStore(ListStore);
        ComboBoxNombre.setEditable(false);
        ComboBoxNombre.setTriggerAction(TriggerAction.ALL);

        ComboBoxNombreSiAsignadas.setAllowBlank(false);
        ComboBoxNombreSiAsignadas.setForceSelection(true);
        ComboBoxNombreSiAsignadas.setSelectOnFocus(true);
        ComboBoxNombreSiAsignadas.setId("Nombre");
        ComboBoxNombreSiAsignadas.setDisplayField("Nombre");
        ComboBoxNombreSiAsignadas.setFieldLabel("Area de Soporte");
        ComboBoxNombreSiAsignadas.setToolTip(getToolTipConfig());
        ComboBoxNombreSiAsignadas.setToolTip(MsgTTGeneral);
        ComboBoxNombreSiAsignadas.setStore(LSToAreaSiAsignadas);
        ComboBoxNombreSiAsignadas.setEditable(false);
        ComboBoxNombreSiAsignadas.setTriggerAction(TriggerAction.ALL);

        //ComboBoxNombre.addSelectionChangedListener(EventoSeleccionComboboxNombre);

        Actualizar.setToolTip(getToolTipConfig());
        Actualizar.setToolTip(MsqToolTipBotonActualizar);
        Actualizar.setIcon(IconHelper.create("actualizar"));
        Actualizar.addSelectionListener(EventoPresionaBotonActualizar);

        ContenedorComboBoxActualizar = new TextField_ComboBox_Buton(AnchoEtiqueta,ComboBoxNombre,Actualizar);
        ContenedorComboBoxActualizar.setBorders(false);

        DLFAreasSoporte.setMode(Mode.INSERT);
        DLFAreasSoporte.setFieldLabel("Usuario de Soporte");
        DLFAreasSoporte.setEnableDND(true);

        LFAreasNoAsignadas = DLFAreasSoporte.getFromList();
        LFAreasNoAsignadas.setDisplayField("Nombre");
        LFAreasNoAsignadas.setToolTip("Areas No Asignas");
        LFAreasNoAsignadas.setFieldLabel("Areas No Asignas");
        LFAreasNoAsignadas.setStore(LSFromAreaNoAsignadas);

        LFAreasSiAsignadas = DLFAreasSoporte.getToList();
        LFAreasSiAsignadas.setDisplayField("Nombre");
        LFAreasSiAsignadas.setToolTip("Areas SI Asignadas");
        LFAreasSiAsignadas.setStore(LSToAreaSiAsignadas);

        ActualizarListStore();
    }
    public void setGridBuscar(WindowGridFiltrarUsuariosSoporte WindowGridBuscar){
        this.WindowGridBuscar = WindowGridBuscar;
    }
    public LayoutContainer getLCNombreBuscar(){
        return ContenedorNombreBuscar;
    }
    public LayoutContainer getLCComboBoxActualizar(){
        return ContenedorComboBoxActualizar;
    }
    public TextField<String> getTFNombre(){
        return TextFieldNombrePropio;
    }
    public ComboBox<BaseModelUsuarioSoporte> getCBNombre(){
        return ComboBoxNombre;
    }
    public BaseModelUsuarioSoporte getBaseModel(){
        BMUsuarioSoporte.setNombrePropio(TextFieldNombrePropio.getValue());
        BMUsuarioSoporte.setCorreo(TextFieldCorreo.getValue());
        BMUsuarioSoporte.setNombreUsuario(TextFieldNombreUsuario.getValue());
        BMUsuarioSoporte.setEsAdministrador(CehckBoxEsAdministrador.getValue());
        BMUsuarioSoporte.setEsReseteadoPassword(CehckBoxEsReseteadoPassword.getValue());
        return BMUsuarioSoporte;
    }
    public DualListField getDualListField(){
        return DLFAreasSoporte;
    }
    public ListField<BaseModelAreaSoporte> getLFAreasNoAsignadas(){
        return LFAreasNoAsignadas;
    }
    public ListField<BaseModelAreaSoporte> getLFAreasSiAsignadas(){
        return LFAreasSiAsignadas;
    }
    public ModeloAreasSoporte_Asignadas_NoAsignadas getAreasAsignadas(){
        
        ModeloAreasSoporte_Asignadas_NoAsignadas areas_asignadas = new ModeloAreasSoporte_Asignadas_NoAsignadas();

        int size = LSToAreaSiAsignadas.getCount();
        int posy = 0;
        areas_asignadas.setAreasSiAsignadas(new String[size][2]);
        while(posy < size){
           areas_asignadas.setValorAreaSiAsignadas(posy,0,LSToAreaSiAsignadas.getAt(posy).get("Id").toString());
           areas_asignadas.setValorAreaSiAsignadas(posy,1,(String)LSToAreaSiAsignadas.getAt(posy).get("Nombre"));
           posy++;
        }
        
        return areas_asignadas;
    }
    public TextField<String> getTextFieldNombreUsuario(){
        return TextFieldNombreUsuario;
    }
    public TextField<String> getTextFieldCorreo(){
        return TextFieldCorreo;
    }
    public CheckBox getCehckBoxEsAdministrador(){
        return CehckBoxEsAdministrador;
    }
    public CheckBox getCehckBoxEsReseteadoPassword(){
        return CehckBoxEsReseteadoPassword;
    }
    public void setClear(){
        ComboBoxNombre.clearSelections();                
        TextFieldNombrePropio.clear();
        TextFieldCorreo.clear();
        TextFieldNombreUsuario.clear();
        CehckBoxEsAdministrador.setValue(Boolean.FALSE);
        CehckBoxEsReseteadoPassword.setValue(Boolean.FALSE);

        TextFieldNombrePropio.focus();
        
        BMUsuarioSoporte = new BaseModelUsuarioSoporte();
        DLFAreasSoporte.mask("Un Momento, Actualizando");
        RPCUsuarioSoporte.getListadosAreasAsignadas(0,new RETORNO_ACTUALIZAR_LISTSTORE_DUAL_LIST_FIELD());  
    }
    public void setBaseModel(BaseModelUsuarioSoporte Modelo){
        TextFieldNombrePropio.setValue(Modelo.getNombrePropio());
        TextFieldNombreUsuario.setValue(Modelo.getNombreUsuario());
        TextFieldCorreo.setValue(Modelo.getCorreo());
        CehckBoxEsAdministrador.setValue(Modelo.getEsAdministrador());
        CehckBoxEsReseteadoPassword.setValue(Modelo.getEsReseteadoPassword());

        this.BMUsuarioSoporte = Modelo;
    }
    public void setAreasAsignadas(ModeloAreasSoporte_Asignadas_NoAsignadas Areas){
        LSFromAreaNoAsignadas.removeAll();
        LSToAreaSiAsignadas.removeAll();
        int posy = 0;
        while(posy < Areas.getAreasSiAsignadas().length){
            LSToAreaSiAsignadas.add(new BaseModelAreaSoporte(Areas.getAreasSiAsignadas()[posy]));
            posy++;
        }

        posy = 0;
        while(posy < Areas.getAreasNoAsignadas().length){
            LSFromAreaNoAsignadas.add(new BaseModelAreaSoporte(Areas.getAreasNoAsignadas()[posy]));
            posy++;
        }
        
        LFAreasNoAsignadas.setStore(LSFromAreaNoAsignadas);
        LFAreasSiAsignadas.setStore(LSToAreaSiAsignadas);

        ComboBoxNombreSiAsignadas.setStore(LSToAreaSiAsignadas);
        ComboBoxNombreNoAsignadas.setStore(LSFromAreaNoAsignadas);

        LFAreasNoAsignadas.repaint();
        LFAreasSiAsignadas.repaint();
        DLFAreasSoporte.unmask();
    }
    public void setEnableBotonBuscar(boolean estado){
        Buscar.setEnabled(estado);
    }
    public void setEnableBotonActualizar(boolean estado){
        Actualizar.setEnabled(estado);
    }
    public void setReadOnlyTFNombre(boolean estado){
        //TextFieldNombre.setEnabled(estado);
        TextFieldNombrePropio.setReadOnly(estado);
    }
    public void setFocusTFNombre(){
        TextFieldNombrePropio.focus();
    }
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelUsuarioSoporte> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }

    private void ActualizarListStore(){        
        ComboBoxNombre.mask("Un Momento, Actualizando");
        DLFAreasSoporte.mask("Un Momento, Actualizando");
        RPCUsuarioSoporte.getListado("",RetornoActualizarListStore);   
        RPCUsuarioSoporte.getListadosAreasAsignadas(0,new RETORNO_ACTUALIZAR_LISTSTORE_DUAL_LIST_FIELD());   
    }
    public void ActualizarListStore(int IdUsuario){
        RPCUsuarioSoporte.getListadosAreasAsignadas(IdUsuario,new RETORNO_ACTUALIZAR_LISTSTORE_DUAL_LIST_FIELD());
    }
    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelUsuarioSoporte>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelUsuarioSoporte> se) {
            BMUsuarioSoporte = se.getSelectedItem();
        }
    }
    private class EVENTO_BOTON_BUSCAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            String CadenaFiltro = TextFieldNombrePropio.getValue();

            if(CadenaFiltro != null && CadenaFiltro.length() > 0)
                WindowGridBuscar.show(CadenaFiltro);
            else
                MessageBox.alert("Alerta", "Debe teclar el nombre de alguna Usuario de soporte para realizar la busqueda",null);
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
            ListStore = new ListStore<BaseModelUsuarioSoporte>();
            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelUsuarioSoporte(Integer.parseInt(obj[posy][0].toString()),
                                                        obj[posy][1].toString(),
                                                        obj[posy][2].toString(),
                                                        obj[posy][3].toString(),
                                                        "",
                                                        Boolean.parseBoolean(obj[posy][4].toString()),
                                                        Boolean.parseBoolean(obj[posy][5].toString())
                                                        ));
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
    private class RETORNO_ACTUALIZAR_LISTSTORE_DUAL_LIST_FIELD implements AsyncCallback<ModeloAreasSoporte_Asignadas_NoAsignadas>{        
        public void onSuccess(ModeloAreasSoporte_Asignadas_NoAsignadas result) {            
            ModeloAreasSoporte_Asignadas_NoAsignadas = (ModeloAreasSoporte_Asignadas_NoAsignadas)result;
            setAreasAsignadas(ModeloAreasSoporte_Asignadas_NoAsignadas);
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
