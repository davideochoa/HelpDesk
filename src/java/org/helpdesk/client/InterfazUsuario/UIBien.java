/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.InterfazUsuario;

import com.extjs.gxt.ui.client.event.ButtonEvent;
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
import org.helpdesk.client.Buscar.WindowGridFiltrarBienes;
import org.helpdesk.client.ModeloDatos.BaseModelBien;
import org.helpdesk.client.ModeloDatos.ModeloBien;
import org.helpdesk.client.ServiciosRPC.RPCBienAsync;

/**
 *
 * @author Administrador
 */
public class UIBien extends ConfiguracionUI{
    private LayoutContainer ContenedorNombreBuscar = new LayoutContainer();
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private TextField<String> TextFieldNombre = new TextField<String>();
    private ComboBox<BaseModelBien> ComboBoxNombre = new ComboBox<BaseModelBien >();
    
    ListStore<BaseModelBien> ListStore = new ListStore<BaseModelBien>();
    
    private String MsgToolTipTextFieldNombre = "El Bien del folio (Soft:Office,Windows,Hard:Impresora,Pc,Otro:Red)";
    private String MsgToolTipBotonBuscar = "Buscar Bien";
    private String MsgToolTipBotonActualizar = "Actualizar Listado Bienes";
    
    private WindowGridFiltrarBienes WindowGridBuscar;// = new WindowGridListadoUnidades();
    
    private EVENTO_BOTON_BUSCAR EVENTO_PRESIONA_BOTON_BUSCAR = new EVENTO_BOTON_BUSCAR();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();    
    
    private RPCBienAsync RPCBien;
    
    private Button Buscar = new Button();
    private ModeloBien Modelo = new ModeloBien();
    public UIBien(int AnchoEtiqueta,RPCBienAsync RPCBien){
        this.RPCBien = RPCBien;

        TextFieldNombre.setAllowBlank(true);
        TextFieldNombre.setId("Nombre");
        TextFieldNombre.setSelectOnFocus(true);
        TextFieldNombre.setFieldLabel("Tipo Bien");
        TextFieldNombre.setToolTip(getToolTipConfig());
        TextFieldNombre.setToolTip(MsgToolTipTextFieldNombre);

        Buscar.setToolTip(getToolTipConfig());
        Buscar.setToolTip(MsgToolTipBotonBuscar);
        Buscar.setIcon(IconHelper.create("buscar"));
        Buscar.addSelectionListener(EVENTO_PRESIONA_BOTON_BUSCAR);

        ContenedorNombreBuscar = new TextField_ComboBox_Buton(AnchoEtiqueta,TextFieldNombre,Buscar);
        ContenedorNombreBuscar.setBorders(false);

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(false);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("area");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Tipo Bien");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(MsgToolTipTextFieldNombre);
        ComboBoxNombre.setStore(ListStore);
        ComboBoxNombre.setEditable(false);
        ComboBoxNombre.setTriggerAction(TriggerAction.ALL);

        Button Actualizar = new Button();
        Actualizar.setToolTip(getToolTipConfig());
        Actualizar.setToolTip(MsgToolTipBotonActualizar);
        Actualizar.setIcon(IconHelper.create("actualizar"));
        Actualizar.addSelectionListener(EventoPresionaBotonActualizar);

        ContenedorComboBoxActualizar = new TextField_ComboBox_Buton(AnchoEtiqueta,ComboBoxNombre,Actualizar);
        ContenedorComboBoxActualizar.setBorders(false);

        //ActualizarListStore();
    }
    public void setGridBuscar(WindowGridFiltrarBienes WindowGridBuscar){
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
    public ComboBox<BaseModelBien> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setModelo(ModeloBien modelo){
        this.Modelo = modelo;
        TextFieldNombre.setValue(modelo.getNombre());
    }
    public BaseModelBien getBaseModelComboBox(){
        BaseModelBien BMU;
        if(ComboBoxNombre.getSelection().isEmpty())
            BMU = new BaseModelBien(0,0,"");
        else
            BMU = ComboBoxNombre.getSelection().get(0);

        return BMU;
    }
    public void setClear(){
        ComboBoxNombre.clearSelections();
        TextFieldNombre.clear();
        TextFieldNombre.focus();

        Modelo = new ModeloBien();

    }
    public void setEnableBotonBuscar(boolean estado){
        Buscar.setEnabled(estado);
    }
    public void setReadOnlyTFNombre(boolean estado){
        TextFieldNombre.setReadOnly(estado);
    }
    public void setFocusTFNombre(){
        TextFieldNombre.focus();
    }
    public void setIdTipoIncidencia(int IdTipoIncidencia){
        Modelo.setIdTipoIncidencia(IdTipoIncidencia);
    }
    public void setFiltroComboBox(int FiltroIdUnidad){
        String ValorFiltro = Integer.toString(FiltroIdUnidad);
        ListStore.filter("IdTipoIncidencia",ValorFiltro);
    }

    private void ActualizarListStore(){
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCBien.getListado("",RetornoActualizarListStore);
    }
    public void ActualizarListStore(int FiltroIdUnidad){
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCBien.getListado(FiltroIdUnidad,"",RetornoActualizarListStore);
    }

    private class EVENTO_BOTON_BUSCAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            String CadenaFiltro = TextFieldNombre.getValue();

            if(CadenaFiltro != null && CadenaFiltro.length() > 0 && Modelo.getIdTipoIncidencia() > 0){
                WindowGridBuscar.show(Modelo.getIdTipoIncidencia(),CadenaFiltro);
            }
            else
                MessageBox.alert("Alerta", "Debe teclar el nombre de algun Bien y/o un Tipo de Incidencia para realizar la busqueda",null);
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
            ListStore = new ListStore<BaseModelBien>();
            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelBien(Integer.parseInt(obj[posy][0].toString()),
                                                Integer.parseInt(obj[posy][1].toString()),
                                                        obj[posy][2].toString()));
                posy++;
            }

            ComboBoxNombre.setStore(ListStore);
            ComboBoxNombre.clearSelections();
            ComboBoxNombre.unmask();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }

}
