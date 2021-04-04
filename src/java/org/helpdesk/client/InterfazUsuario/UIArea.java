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
import org.helpdesk.client.Buscar.WindowGridFiltrarAreas;
import org.helpdesk.client.ModeloDatos.BaseModelArea;
import org.helpdesk.client.ModeloDatos.ModeloArea;
import org.helpdesk.client.ServiciosRPC.RPCAreaAsync;

/**
 *
 * @author Administrador
 */
public class UIArea extends ConfiguracionUI{
    private LayoutContainer ContenedorNombreBuscar = new LayoutContainer();
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private TextField<String> TextFieldNombre = new TextField<String>();
    private ComboBox<BaseModelArea> ComboBoxNombre = new ComboBox<BaseModelArea >();
    
    ListStore<BaseModelArea> ListStore = new ListStore<BaseModelArea>();
    
    private String MsgToolTipTextFieldNombre = "Lugar dentro de la unidad (Consulta Externa,Contabilidad,Direccion Administrativa)";
    private String MsgToolTipBotonBuscar = "Buscar Area";
    private String MsgToolTipBotonActualizar = "Actualizar Listado Areas";
    
    private WindowGridFiltrarAreas WindowGridBuscar;// = new WindowGridListadoUnidades();
    
    private EVENTO_BOTON_BUSCAR EVENTO_PRESIONA_BOTON_BUSCAR = new EVENTO_BOTON_BUSCAR();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();    
    
    private RPCAreaAsync RPCArea;
    
    private Button Buscar = new Button();
    private ModeloArea Modelo = new ModeloArea();
    public UIArea(int AnchoEtiqueta,RPCAreaAsync RPCUnidad){
        this.RPCArea = RPCUnidad;

        TextFieldNombre.setAllowBlank(true);
        TextFieldNombre.setId("Nombre");
        TextFieldNombre.setSelectOnFocus(true);
        TextFieldNombre.setFieldLabel("Area");
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
        ComboBoxNombre.setFieldLabel("Area");
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
    public void setGridBuscar(WindowGridFiltrarAreas WindowGridBuscar){
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
    public ComboBox<BaseModelArea> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setModelo(ModeloArea modelo){
        this.Modelo = modelo;
        TextFieldNombre.setValue(modelo.getNombre());
    }
    public BaseModelArea getBaseModelComboBox(){
        BaseModelArea BMU;
        if(ComboBoxNombre.getSelection().isEmpty())
            BMU = new BaseModelArea(0,0,"");
        else
            BMU = ComboBoxNombre.getSelection().get(0);

        return BMU;
    }
    public void setClear(){
        ComboBoxNombre.clearSelections();
        TextFieldNombre.clear();
        TextFieldNombre.focus();

        Modelo = new ModeloArea();

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
    public void setIdUnidad(int IdUnidad){
        Modelo.setIdUnidad(IdUnidad);
    }
    public void setFiltroComboBox(int FiltroIdUnidad){
        String ValorFiltro = Integer.toString(FiltroIdUnidad);
        ListStore.filter("IdUnidad",ValorFiltro);
    }

    private void ActualizarListStore(){
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCArea.getListado("",RetornoActualizarListStore);
    }
    public void ActualizarListStore(int FiltroIdUnidad){
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCArea.getListado(FiltroIdUnidad,"",RetornoActualizarListStore);
    }

    private class EVENTO_BOTON_BUSCAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce) {
            String CadenaFiltro = TextFieldNombre.getValue();

            if(CadenaFiltro != null && CadenaFiltro.length() > 0 && Modelo.getIdUnidad() > 0){
                WindowGridBuscar.show(Modelo.getIdUnidad(),CadenaFiltro);
            }
            else
                MessageBox.alert("Alerta", "Debe teclar el nombre de alguna Area y/o una Unidad para realizar la busqueda",null);
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
            ListStore = new ListStore<BaseModelArea>();
            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelArea(Integer.parseInt(obj[posy][0].toString()),
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
