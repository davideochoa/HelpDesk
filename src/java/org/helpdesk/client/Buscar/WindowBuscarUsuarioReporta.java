/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helpdesk.client.Buscar;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.helpdesk.client.ModeloDatos.BaseModelUsuarioReporta;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReporta;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReportaAsync;

/**
 *
 * @author Administrador
 */
public class WindowBuscarUsuarioReporta extends Window {
    private RPCUsuarioReportaAsync RPCUsuarioReporta;
    ListStore<BaseModelUsuarioReporta> ListStore = new ListStore<BaseModelUsuarioReporta>();
    Grid grid_1;
    ColumnModel CMGrid;
    BaseModelUsuarioReporta BMUsuarioReporta = new BaseModelUsuarioReporta();
    Window estaVentana = null;
    
    TextField txtfldNombre = new TextField();
    public WindowBuscarUsuarioReporta() {
        RPCUsuarioReporta = (RPCUsuarioReportaAsync) GWT.create(RPCUsuarioReporta.class);
        
        this.setHeading("Buscar Usuario Reporta");
        this.setMaximizable(false);
        this.setMinimizable(false);
        this.setBlinkModal(true);
        this.setClosable(false);
        this.setResizable(false);
        this.setModal(true);
        this.setOnEsc(false);
        this.setFrame(true);
        this.setBodyBorder(false);
        this.setBorders(false);        
        this.setButtonAlign(HorizontalAlignment.CENTER);
        
        FormPanel frmpnlFpprincipal = new FormPanel();
        frmpnlFpprincipal.setButtonAlign(HorizontalAlignment.CENTER);
        frmpnlFpprincipal.setHeading("FPPrincipal");
        frmpnlFpprincipal.setHeaderVisible(false);
        frmpnlFpprincipal.setBodyBorder(false);
        frmpnlFpprincipal.setBorders(false);        
        
        ColumnConfig Nombre = new ColumnConfig("Nombre", "Nombre", 150);
        Nombre.setSortable(false);
        Nombre.setGroupable(false);
        Nombre.setResizable(false);
        Nombre.setMenuDisabled(true);
        
        List<ColumnConfig> configs_1 = new ArrayList<ColumnConfig>();
        configs_1.add(Nombre);
        
        
        txtfldNombre.setFieldLabel("Nombre");
        txtfldNombre.addKeyListener(new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    if(txtfldNombre.getValue() != null && txtfldNombre.getValue().toString().length() > 0)
                        actualizar(txtfldNombre.getValue().toString());                        
                }
            }
        });
                
        
        CMGrid = new ColumnModel(configs_1);
        
        grid_1 = new Grid(ListStore,CMGrid);
        grid_1.setAutoExpandColumn("Nombre");
        grid_1.setHeight(300);
        grid_1.setBorders(true);        
        grid_1.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid_1.setHideHeaders(false);
        grid_1.setStyleAttribute("borderTop", "none");
        grid_1.setStripeRows(true);
        grid_1.getSelectionModel().addListener(Events.SelectionChange,new EVENTO_SELECCIONAR_GRID());
        
        frmpnlFpprincipal.add(txtfldNombre, new FormData("100%"));    
        frmpnlFpprincipal.add(grid_1, new FormData("100%"));
        
        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");
        
        btnAceptar.addSelectionListener(new EVENTO_BOTON_ACEPTAR());
        btnCancelar.addSelectionListener(new EVENTO_BOTON_CANCELAR());
        
        frmpnlFpprincipal.addButton(btnAceptar);
        frmpnlFpprincipal.addButton(btnCancelar);
        
        add(frmpnlFpprincipal);
        estaVentana = this;
    }
    public void inicializar(){
        BMUsuarioReporta = new BaseModelUsuarioReporta();
        txtfldNombre.setValue("");
        ListStore = new ListStore<BaseModelUsuarioReporta>();
        grid_1.reconfigure(ListStore,CMGrid);
        grid_1.repaint();
        grid_1.clearState();        
    }
    public void actualizar(String cadena){
        if(cadena.length() > 0){
            cadena = cadena.toUpperCase();
            String cadenaSentencia = cadena.replace(' ','%');    
            estaVentana.mask("Espere un momento... actualizando");
            RPCUsuarioReporta.getListado(cadenaSentencia,new RETORNO_ACTUALIZAR_LISTSTORE());
            
            txtfldNombre.setValue(cadena);
        }
        
    }
    public BaseModelUsuarioReporta getSeleccion(){
        return BMUsuarioReporta;
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
            
            grid_1.reconfigure(ListStore,CMGrid);
            grid_1.repaint();
            estaVentana.unmask();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    class EVENTO_SELECCIONAR_GRID implements Listener<SelectionChangedEvent<BaseModelUsuarioReporta>>{
            public void handleEvent(SelectionChangedEvent<BaseModelUsuarioReporta> be){
                BMUsuarioReporta = be.getSelectedItem();
                estaVentana.setVisible(false);
            }
        }
    
    class EVENTO_BOTON_ACEPTAR extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce){
                if(grid_1.getSelectionModel().getSelectedItem()!= null){
                    estaVentana.setVisible(false);
                }
            }
        }
    class EVENTO_BOTON_CANCELAR extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            if(txtfldNombre.getValue() != null && txtfldNombre.getValue().toString().length() > 0){                         
                BMUsuarioReporta = new BaseModelUsuarioReporta();
                BMUsuarioReporta.setNombre(txtfldNombre.getValue().toString().toUpperCase());
                estaVentana.setVisible(false);   
            }
            
        }
    }
}
