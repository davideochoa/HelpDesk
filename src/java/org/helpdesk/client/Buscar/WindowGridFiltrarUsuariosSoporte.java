/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Buscar;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.helpdesk.client.InterfazUsuario.ConfiguracionUI;
import org.helpdesk.client.InterfazUsuario.TextField_ComboBox_Buton;
import org.helpdesk.client.ModeloDatos.BaseModelUsuarioSoporte;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporteAsync;

/**
 *
 * @author Administrador
 */
public class WindowGridFiltrarUsuariosSoporte extends ConfiguracionUI{
        private Window window = new Window();
        private TextField<String> TextFieldBuscar = new TextField<String>();
        private LayoutContainer LCBuscar = new LayoutContainer();
        private String MsgToolTipBuscar = "Buscar Usuarios de Soporte por nombre";
        private Button ButtonActualizar = new Button();
        private Button ButtonAceptar = new Button("Aceptar");
        private Button ButtonCancelar = new Button("Cancelar");
        private EVENTO_BOTON_ACTUALIZAR EventoActualizar = new EVENTO_BOTON_ACTUALIZAR();
        private EVENTO_BOTON_ACEPTAR EventoAceptar = new EVENTO_BOTON_ACEPTAR();
        private EVENTO_BOTON_CANCELAR EventoCancelar = new EVENTO_BOTON_CANCELAR();
        private EVENTO_SELECCIONAR_GRID EventoSeleccionaGrid = new EVENTO_SELECCIONAR_GRID();
        private String DescripcionToolTipActualizar = "Actualiza el listado segun filtro";
        private String DescripcionToolTipAceptar = "Acepta el Usuarios de Soporte Seleccionada";
        private String DescripcionToolTipCancelar = "Rechaza el Usuarios de Soporte Seleccionada";
        
        private List<ColumnConfig> ListColumnConfigGrid;
        private ColumnModel ColumnModelGrid;
        private Grid<BaseModelUsuarioSoporte> grid;
        private ListStore<BaseModelUsuarioSoporte> ListStore = new ListStore<BaseModelUsuarioSoporte>();

        RETORNO_ACTUALIZAR_LISTSTORE RetornoActualizarListStore = new RETORNO_ACTUALIZAR_LISTSTORE();
        
        RPCUsuarioSoporteAsync RPCUsuarioSoporte;
        BaseModelUsuarioSoporte BMUsuarioSoporte = new BaseModelUsuarioSoporte();
        public WindowGridFiltrarUsuariosSoporte(RPCUsuarioSoporteAsync RPCUsuarioSoporte){
            this.RPCUsuarioSoporte = RPCUsuarioSoporte;
            window.setHeading("Buscar en Catalogo Usuarios de Soporte");
            window.setMaximizable(false);
            window.setMinimizable(false);
            window.setBlinkModal(true);
            window.setClosable(false);
            window.setResizable(false);
            window.setModal(true);
            window.setOnEsc(false);
            window.setFrame(true);
            window.setWidth(400);
            //window.setHeight(200);
            window.setButtonAlign(HorizontalAlignment.CENTER);

            ButtonActualizar.addSelectionListener(EventoActualizar);
            ButtonActualizar.setIconAlign(IconAlign.RIGHT);
            ButtonActualizar.setIcon(IconHelper.create("actualizar"));
            ButtonActualizar.setToolTip(getToolTipConfig());
            ButtonActualizar.setToolTip(DescripcionToolTipActualizar);

            ButtonAceptar.addSelectionListener(EventoAceptar);
            ButtonAceptar.setIconAlign(IconAlign.RIGHT);
            ButtonAceptar.setIcon(IconHelper.create("aceptar"));
            ButtonAceptar.setToolTip(getToolTipConfig());
            ButtonAceptar.setToolTip(DescripcionToolTipAceptar);

            ButtonCancelar.addSelectionListener(EventoCancelar);
            ButtonCancelar.setIconAlign(IconAlign.RIGHT);
            ButtonCancelar.setIcon(IconHelper.create("cancelar"));
            ButtonCancelar.setToolTip(getToolTipConfig());
            ButtonCancelar.setToolTip(DescripcionToolTipCancelar);

            TextFieldBuscar.setAllowBlank(false);
            TextFieldBuscar.setId("buscar");
            TextFieldBuscar.setSelectOnFocus(true);
            TextFieldBuscar.setFieldLabel("Tipo Reporte");
            TextFieldBuscar.setToolTip(getToolTipConfig());
            TextFieldBuscar.setToolTip(MsgToolTipBuscar);

            ListColumnConfigGrid = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            column.setId("NombrePropio");
            column.setHeader("Nombre");
            
            column.setWidth(200);
            //column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            ColumnModelGrid = new ColumnModel(ListColumnConfigGrid);

            grid = new Grid<BaseModelUsuarioSoporte>(ListStore,ColumnModelGrid);
            grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            grid.setHideHeaders(false);
            grid.setAutoExpandColumn("NombrePropio");
            grid.setBorders(true);
            grid.setHeight(200);
            grid.setStyleAttribute("borderTop", "none");
            grid.setStripeRows(true);
            grid.setHideHeaders(true);
            grid.getSelectionModel().addListener(Events.SelectionChange,EventoSeleccionaGrid);

            FormPanel PanelGridFolios = new FormPanel();
            PanelGridFolios.setHeaderVisible(true);
            PanelGridFolios.setBodyBorder(false);
            PanelGridFolios.setBorders(false);
            PanelGridFolios.setHeading("Listado de Usuarios de Soporte");
            PanelGridFolios.setPadding(0);
            PanelGridFolios.setReadOnly(true);
            PanelGridFolios.setScrollMode(Scroll.AUTO);

            PanelGridFolios.add(grid,new FormData("100%"));

            FormPanel Form = new FormPanel();
            Form.setHeaderVisible(false);
            Form.setBodyBorder(false);
            Form.setBorders(false);
            Form.setFrame(false);

            Form.add(new TextField_ComboBox_Buton(50,TextFieldBuscar,ButtonActualizar));
            Form.add(PanelGridFolios,new FormData("100%"));

            window.add(Form);//, new FormData("100%"));
            //window.addButton(ButtonActualizar);
            window.addButton(ButtonAceptar);
            window.addButton(ButtonCancelar);
        }
        public BaseModelUsuarioSoporte getBMSeleccionado(){
            return BMUsuarioSoporte;
        }
        public void show(String CadenaFiltro){
            window.setVisible(true);
            window.mask("Obteniendo Listado de Usuarios de Soporte");
            window.center();
            window.setFocusWidget(TextFieldBuscar);
            TextFieldBuscar.setValue(CadenaFiltro);
            RPCUsuarioSoporte.getListado(CadenaFiltro,RetornoActualizarListStore);
        }
        public void hide(){
            window.setVisible(false);
        }
        public void setEventoButton(SelectionListener<ButtonEvent> evento){
            ButtonAceptar.addSelectionListener(evento);
            ButtonCancelar.addSelectionListener(evento);
        }
        class EVENTO_BOTON_ACTUALIZAR extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce) {
                String CadenaFiltro = TextFieldBuscar.getValue();
                window.mask("Obteniendo Listado de Usuarios de Soporte");
                if(CadenaFiltro != null && CadenaFiltro.length() > 0){

                    RPCUsuarioSoporte.getListado(CadenaFiltro,RetornoActualizarListStore);
                }
                else{
                    MessageBox.alert("Alerta", "Debe teclar el nombre de algun Usuario de Soporte para realizar la busqueda",null);
                    window.unmask();
                }
            }
        }
        class EVENTO_BOTON_ACEPTAR extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce){}
        }
        class EVENTO_BOTON_CANCELAR extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce){}
        }
        class EVENTO_SELECCIONAR_GRID implements Listener<SelectionChangedEvent<BaseModelUsuarioSoporte>>{
            public void handleEvent(SelectionChangedEvent<BaseModelUsuarioSoporte> be){
                BMUsuarioSoporte = be.getSelectedItem();
            }
        }
        class RETORNO_ACTUALIZAR_LISTSTORE implements AsyncCallback<String[][]>{
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

                grid.reconfigure(ListStore, ColumnModelGrid);
                grid.repaint();

                window.unmask();
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }
