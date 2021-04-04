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
import org.helpdesk.client.ModeloDatos.BaseModelEstatus;
import org.helpdesk.client.ServiciosRPC.RPCEstatusAsync;

/**
 *
 * @author Administrador
 */
public class UIStatus extends ConfiguracionUI{
    private LayoutContainer ContenedorComboBoxActualizar = new LayoutContainer();
    private ComboBox<BaseModelEstatus> ComboBoxNombre = new ComboBox<BaseModelEstatus >();
    private ListStore<BaseModelEstatus> ListStore = new ListStore<BaseModelEstatus>();
    
    private String MsgTTGeneral = "Determinar en que estado se encunatra la atencion del incidente";
    private String MsgToolTipBotonBuscar = "Buscar estatus";
    private String MsqToolTipBotonActualizar = "Actualizar Listado de estatus";
    private EVENTO_SELECCIONA_COMBOBOX_NOMBRE EventoSeleccionComboboxNombre = new EVENTO_SELECCIONA_COMBOBOX_NOMBRE();
    private EVENTO_BOTON_ACTUALIZAR EventoPresionaBotonActualizar = new EVENTO_BOTON_ACTUALIZAR();
    private RPCEstatusAsync RPCEstatus;
    private RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();  
    private Button Actualizar = new Button();
    private BaseModelEstatus BMEstatus = new BaseModelEstatus();
    public UIStatus(int AnchoEtiqueta,RPCEstatusAsync RPCEstatus){
        this.RPCEstatus = RPCEstatus;

        ComboBoxNombre.setAllowBlank(true);
        ComboBoxNombre.setForceSelection(false);
        ComboBoxNombre.setSelectOnFocus(true);
        ComboBoxNombre.setId("estatus");
        ComboBoxNombre.setDisplayField("Nombre");
        ComboBoxNombre.setFieldLabel("Estatus");
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
    public LayoutContainer getLCComboBoxActualizar(){
        return ContenedorComboBoxActualizar;
    }
    public ComboBox<BaseModelEstatus> getCBNombre(){
        return ComboBoxNombre;
    }
    public void setClear(){
        ComboBoxNombre.clearSelections();
        BMEstatus = new BaseModelEstatus();
    }
    public void setEnableBotonActualizar(boolean estado){
        Actualizar.setEnabled(estado);
    }
    public void setaddSelectionChangedListenerCBNombre(SelectionChangedListener<BaseModelEstatus> evento){
        ComboBoxNombre.addSelectionChangedListener(evento);
    }
    public ListStore<BaseModelEstatus> getListStore(){
        return ListStore;
    }
    private void ActualizarListStore(){
        ComboBoxNombre.mask("Un Momento, Actualizando");
        RPCEstatus.getListado("",RetornoActualizarListStore);
    }    
    private class EVENTO_SELECCIONA_COMBOBOX_NOMBRE extends SelectionChangedListener<BaseModelEstatus>{
        @Override
        public void selectionChanged(SelectionChangedEvent<BaseModelEstatus> se) {
            BMEstatus = se.getSelectedItem();
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
            ListStore = new ListStore<BaseModelEstatus>();
            
            int posy = 0; 
            while(posy < obj.length){                
                ListStore.add(new BaseModelEstatus(Integer.parseInt(obj[posy][0].toString()),
                                                    obj[posy][1].toString(),
                                                    Boolean.parseBoolean(obj[posy][2].toString()),
                                                    Boolean.parseBoolean(obj[posy][3].toString()),
                                                    Boolean.parseBoolean(obj[posy][4].toString()),
                                                    Boolean.parseBoolean(obj[posy][5].toString()),
                                                    Boolean.parseBoolean(obj[posy][6].toString())
                        ));
                posy++;
            }
            ComboBoxNombre.setStore(ListStore);
            ContenedorComboBoxActualizar.unmask();
            ComboBoxNombre.unmask();

        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
