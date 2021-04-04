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
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.ModeloDatos.BaseModelMarca;
import org.helpdesk.client.ModeloDatos.ModeloMarca;
import org.helpdesk.client.ServiciosRPC.RPCMarcaAsync;

/**
 *
 * @author Administrador
 */
public class UIMarca extends ConfiguracionUI{
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();    
    private ComboBox<BaseModelMarca> ComboBoxNombre = new ComboBox<BaseModelMarca >();
    private ListStore<BaseModelMarca> ListStore = new ListStore<BaseModelMarca>();
    private String MsgTTGeneral = "Marca del Bien";
    private String MsqToolTipBotonActualizar = "Actualizar Listado de Marcas";
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();    
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RPCMarcaAsync ServicioRPC;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();        
    private Button Actualizar = new Button();
    private ModeloMarca Modelo = new ModeloMarca();
    private BaseModelMarca BaseModelo = new BaseModelMarca();
    public UIMarca(int AnchoEtiqueta,RPCMarcaAsync ServicioRPC){
        this.ServicioRPC = ServicioRPC;

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(false);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("nombre");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Marca");
        ComboBoxNombre.setToolTip(getToolTipConfig());
        ComboBoxNombre.setToolTip(MsgTTGeneral);
        ComboBoxNombre.setStore(ListStore);
        ComboBoxNombre.setEditable(true);
        ComboBoxNombre.setTriggerAction(TriggerAction.ALL);
        
        Actualizar.setToolTip(getToolTipConfig());
        Actualizar.setToolTip(MsqToolTipBotonActualizar);
        Actualizar.setIcon(IconHelper.create("actualizar"));
        Actualizar.addSelectionListener(EventoPresionaBotonActualizar);

        ContenedorComboBoxActualizar = new TextField_ComboBox_Buton(AnchoEtiqueta,ComboBoxNombre,Actualizar);
        ContenedorComboBoxActualizar.setBorders(false);

        ActualizarListStore();
    }
    public BaseModelMarca getBaseModelComboBox(){
        return ComboBoxNombre.getSelection().get(0);
    }
    public LayoutContainer getLCComboBoxActualizar(){
        return ContenedorComboBoxActualizar;
    }
    public ComboBox<BaseModelMarca> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setClear(){        
        ComboBoxNombre.clearSelections();
        Modelo = new ModeloMarca();
        BaseModelo = new BaseModelMarca();
    }
    public void setEnableBotonActualizar(boolean estado){
        Actualizar.setEnabled(estado);
    }
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelMarca> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }
    public BaseModelMarca getBaseModel(){
        return BaseModelo;
    }

    private void ActualizarListStore(){        
        ComboBoxNombre.mask("Un Momento, Actualizando");
        ServicioRPC.getListado(RetornoActualizarListStore);
    }
    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelMarca>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelMarca> se) {
            BaseModelo = se.getSelectedItem();
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
            ListStore = new ListStore<BaseModelMarca>();
            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelMarca(obj[posy][0].toString()));
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
