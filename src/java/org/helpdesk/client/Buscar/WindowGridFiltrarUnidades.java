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
import org.helpdesk.client.ModeloDatos.BaseModelUnidad;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;

/**
 *
 * @author Administrador
 */
public class WindowGridFiltrarUnidades extends ConfiguracionUI{
        private Window window = new Window();
        private TextField<String> TextFieldBuscar = new TextField<String>();
        private LayoutContainer LCBuscar = new LayoutContainer();
        private String DescripcionToolTipBuscarUnidad = "Buscar Unidad por nombre";
        private Button ButtonActualizar = new Button();
        private Button ButtonAceptar = new Button("Aceptar");
        private Button ButtonCancelar = new Button("Cancelar");
        private EVENTO_BOTON_ACTUALIZAR EventoActualizar = new EVENTO_BOTON_ACTUALIZAR();
        private EVENTO_BOTON_ACEPTAR EventoAceptar = new EVENTO_BOTON_ACEPTAR();
        private EVENTO_BOTON_CANCELAR EventoCancelar = new EVENTO_BOTON_CANCELAR();
        private EVENTO_SELECCIONAR_GRID EventoSeleccionaGrid = new EVENTO_SELECCIONAR_GRID();
        private String DescripcionToolTipActualizar = "Actualiza el listado segun filtro";
        private String DescripcionToolTipAceptar = "Acepta el Uso de la Unidad Seleccionada";
        private String DescripcionToolTipCancelar = "Rechaza el Uso de la Unidad Seleccionada";

        private FormPanel PanelGridFolios = new FormPanel();
        private List<ColumnConfig> ListColumnConfigGrid;
        private ColumnModel ColumnModelGrid;
        private Grid<BaseModelUnidad> grid;
        private ListStore<BaseModelUnidad> ListStoreUnidadGrid = new ListStore<BaseModelUnidad>();
        RETORNO_ACTUALIZAR_LISTSTORE_GRID RetornoActualizarListStoreGrid = new RETORNO_ACTUALIZAR_LISTSTORE_GRID();
        RPCUnidadAsync RPCUnidad;
        BaseModelUnidad UnidadSeleccionada = new BaseModelUnidad();
        public WindowGridFiltrarUnidades(RPCUnidadAsync RPCUnidad){
            this.RPCUnidad = RPCUnidad;
            window.setHeading("Buscar en Catalogo Unidades");
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
            TextFieldBuscar.setFieldLabel("Unidad");
            TextFieldBuscar.setToolTip(getToolTipConfig());
            TextFieldBuscar.setToolTip(DescripcionToolTipBuscarUnidad);

            ListColumnConfigGrid = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            column.setId("Nombre");
            column.setHeader("Unidades");
            column.setWidth(200);
            //column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            ColumnModelGrid = new ColumnModel(ListColumnConfigGrid);

            grid = new Grid<BaseModelUnidad>(ListStoreUnidadGrid,ColumnModelGrid);
            grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            grid.setHideHeaders(false);
            grid.setAutoExpandColumn("Nombre");
            grid.setBorders(true);
            grid.setHeight(200);
            grid.setStyleAttribute("borderTop", "none");
            grid.setStripeRows(true);
            grid.setHideHeaders(true);
            grid.getSelectionModel().addListener(Events.SelectionChange,EventoSeleccionaGrid);

            PanelGridFolios.setHeaderVisible(true);
            PanelGridFolios.setBodyBorder(false);
            PanelGridFolios.setBorders(false);
            PanelGridFolios.setHeading("Listado de Unidades");
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
        public BaseModelUnidad getBMSeleccionado(){
            return UnidadSeleccionada;
        }
        public ModeloUnidad getModeloUnidadSeleccionado(){
            System.out.println("getModeloUnidadSeleccionado:"+UnidadSeleccionada.getNombre());
            return UnidadSeleccionada.getModeloUnidad();
        }
        public void show(String CadenaFiltro){
            window.setVisible(true);
            window.mask("Obteniendo Listado de Unidades");
            window.center();
            window.setFocusWidget(TextFieldBuscar);
            TextFieldBuscar.setValue(CadenaFiltro);
            RPCUnidad.getListado(CadenaFiltro,RetornoActualizarListStoreGrid);
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
                window.mask("Obteniendo Listado de Unidades");
                if(CadenaFiltro != null && CadenaFiltro.length() > 0){

                    RPCUnidad.getListado(CadenaFiltro,RetornoActualizarListStoreGrid);
                }
                else{
                    MessageBox.alert("Alerta", "Debe teclar el nombre de alguna unidad para realizar la busqueda",null);
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
        class EVENTO_SELECCIONAR_GRID implements Listener<SelectionChangedEvent<BaseModelUnidad>>{
            public void handleEvent(SelectionChangedEvent<BaseModelUnidad> be){
                UnidadSeleccionada = be.getSelectedItem();
                System.out.println("EVENTO_SELECCIONAR_GRID:"+UnidadSeleccionada.getNombre());
            }
        }
        class RETORNO_ACTUALIZAR_LISTSTORE_GRID implements AsyncCallback<String[][]>{
            public void onSuccess(String[][] result) {
                String obj[][] = (String[][])result;
                ListStoreUnidadGrid = new ListStore<BaseModelUnidad>();

                int posy = 0;
                while(posy < obj.length){
                    ListStoreUnidadGrid.add(new BaseModelUnidad(Integer.parseInt(obj[posy][0].toString()),obj[posy][1].toString()));
                    posy++;
                }

                grid.reconfigure(ListStoreUnidadGrid, ColumnModelGrid);
                grid.repaint();

                window.unmask();
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }
