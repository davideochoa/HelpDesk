/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Sistema;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.helpdesk.client.ModeloDatosNuevo.BMMDArea;
import org.helpdesk.client.ModeloDatosNuevo.BMMDDocReferencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDGridBusqueda;
import org.helpdesk.client.ModeloDatosNuevo.BMMDMarca;
import org.helpdesk.client.ModeloDatosNuevo.BMMDModelo;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoBien;
import org.helpdesk.client.ModeloDatosNuevo.BMMDTipoIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUnidad;
import org.helpdesk.client.ModeloDatosNuevo.BMMDUsuarioReporta;
import org.helpdesk.client.ServiciosRPC.RPCDocRef;
import org.helpdesk.client.ServiciosRPC.RPCDocRefAsync;
import org.helpdesk.client.ServiciosRPC.RPCArea;
import org.helpdesk.client.ServiciosRPC.RPCAreaAsync;
import org.helpdesk.client.ServiciosRPC.RPCBien;
import org.helpdesk.client.ServiciosRPC.RPCBienAsync;
import org.helpdesk.client.ServiciosRPC.RPCMarca;
import org.helpdesk.client.ServiciosRPC.RPCMarcaAsync;
import org.helpdesk.client.ServiciosRPC.RPCModelo;
import org.helpdesk.client.ServiciosRPC.RPCModeloAsync;
import org.helpdesk.client.ServiciosRPC.RPCMovimientosFolios;
import org.helpdesk.client.ServiciosRPC.RPCMovimientosFoliosAsync;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidencia;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUnidad;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReporta;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReportaAsync;

/**
 *
 * @author Usuario
 */
public class Buscar extends Window {
    Window estaVentana = null;
    ComboBox<BMMDUnidad> cmbxUnidad = new ComboBox<BMMDUnidad>();
    ListStore<BMMDUnidad> ListStoreUnidad = new ListStore<BMMDUnidad>();
    
    ComboBox<BMMDArea> cmbxArea = new ComboBox<BMMDArea>();
    ListStore<BMMDArea> ListStoreArea = new ListStore<BMMDArea>();
    
    ComboBox<BMMDUsuarioReporta> cmbxUsuarioReporta = new ComboBox<BMMDUsuarioReporta>();
    ListStore<BMMDUsuarioReporta> ListStoreUsuarioReporta = new ListStore<BMMDUsuarioReporta>();
    
    ComboBox<BMMDDocReferencia> cmbxDocReferencia = new ComboBox<BMMDDocReferencia>();
    ListStore<BMMDDocReferencia> ListStoreDocReferencia = new ListStore<BMMDDocReferencia>();
    TextField<String> txtfldReferenciaDocumental = new TextField<String>();    
    
    ComboBox<BMMDTipoIncidencia> cmbxTipoIncidencia = new ComboBox<BMMDTipoIncidencia>();
    ListStore<BMMDTipoIncidencia> ListStoreTipoIncidencia = new ListStore<BMMDTipoIncidencia>();
    
    ComboBox<BMMDTipoBien> cmbxTipoBien = new ComboBox<BMMDTipoBien>();
    ListStore<BMMDTipoBien> ListStoreTipoBien = new ListStore<BMMDTipoBien>();
    
    ComboBox<BMMDMarca> cmbxMarca = new ComboBox<BMMDMarca>();
    ListStore<BMMDMarca> ListStoreMarca = new ListStore<BMMDMarca>();
    
    ComboBox<BMMDModelo> cmbxModelo = new ComboBox<BMMDModelo>();
    ListStore<BMMDModelo> ListStoreModelo = new ListStore<BMMDModelo>();
    
    TextField<String> txtfldNumSerie = new TextField<String>();
    TextField<String> txtfldNumInventario = new TextField<String>();
    
    ListStore<BMMDGridBusqueda> LSGridBuscar = new ListStore<BMMDGridBusqueda>();
    ColumnModel CMGridBuscar;
    Grid<BMMDGridBusqueda> grid;
    
    RPCUnidadAsync RPCUnidad; 
    RPCAreaAsync RPCArea;
    RPCUsuarioReportaAsync RPCUsuarioReporta;
    RPCDocRefAsync RPCDocRef;
    RPCTipoIncidenciaAsync RPCTipoIncidencia;
    RPCBienAsync RPCTipoBien;
    RPCMarcaAsync RPCMarca;
    RPCModeloAsync RPCModelo;
    
    RPCMovimientosFoliosAsync RPCMovimientosFolios;
    public Buscar(String etiquetaTipoIncidencia) {
        RPCUnidad = (RPCUnidadAsync) GWT.create(RPCUnidad.class);
        RPCArea = (RPCAreaAsync) GWT.create(RPCArea.class);
        RPCUsuarioReporta = (RPCUsuarioReportaAsync) GWT.create(RPCUsuarioReporta.class);
        RPCDocRef = (RPCDocRefAsync) GWT.create(RPCDocRef.class);
        RPCTipoIncidencia = (RPCTipoIncidenciaAsync) GWT.create(RPCTipoIncidencia.class);
        RPCTipoBien = (RPCBienAsync) GWT.create(RPCBien.class); 
        RPCMarca = (RPCMarcaAsync) GWT.create(RPCMarca.class);
        RPCModelo = (RPCModeloAsync) GWT.create(RPCModelo.class);
        RPCMovimientosFolios = (RPCMovimientosFoliosAsync) GWT.create(RPCMovimientosFolios.class);
                
        estaVentana = this;
        setHeading("New Window");
        setLayout(new RowLayout(Orientation.VERTICAL));
        this.setWidth(950);

        VerticalPanel verticalPanel = new VerticalPanel();

        HorizontalPanel horizontalPanel = new HorizontalPanel();

        FormPanel frmpnlNewFormpanel_1 = new FormPanel();
        frmpnlNewFormpanel_1.setBorders(false);
        frmpnlNewFormpanel_1.setBodyBorder(false);
        frmpnlNewFormpanel_1.setHeaderVisible(false);
        frmpnlNewFormpanel_1.setLabelWidth(95);
        frmpnlNewFormpanel_1.setHeading("New FormPanel");
		
        cmbxUnidad.setStore(ListStoreUnidad);
        cmbxUnidad.setEditable(false);
        cmbxUnidad.setTriggerAction(TriggerAction.ALL);
        cmbxUnidad.addSelectionChangedListener(new SelectionChangedListener<BMMDUnidad>() {
            public void selectionChanged(SelectionChangedEvent<BMMDUnidad> se) {
                BMMDUnidad unidad = cmbxUnidad.getSelection().get(0);
            
                if(unidad != null){
                    cmbxArea.mask("Un Momento, Actualizando");
                    RPCArea.getListado2(unidad.getId(),"",new RETORNO_ACTUALIZAR_LISTSTORE_AREA());
                }
                else
                    cmbxArea.unmask();
            }
        });
        cmbxUnidad.setDisplayField("Nombre");
        cmbxUnidad.setFieldLabel("Unidad");
        frmpnlNewFormpanel_1.add(cmbxUnidad, new FormData("200%"));        
        
        cmbxArea.setStore(ListStoreArea);
        cmbxArea.setEditable(false);
        cmbxArea.setTriggerAction(TriggerAction.ALL);
        cmbxArea.setDisplayField("Nombre");
        cmbxArea.setFieldLabel("Area");
        frmpnlNewFormpanel_1.add(cmbxArea, new FormData("200%"));
        
        cmbxUsuarioReporta.setStore(ListStoreUsuarioReporta);
        cmbxUsuarioReporta.setEditable(false);
        cmbxUsuarioReporta.setTriggerAction(TriggerAction.ALL);
        cmbxUsuarioReporta.setDisplayField("Nombre");
        cmbxUsuarioReporta.setFieldLabel("Usuario Reporta");
        frmpnlNewFormpanel_1.add(cmbxUsuarioReporta, new FormData("150%"));
        
        cmbxDocReferencia.setStore(ListStoreDocReferencia);
        cmbxDocReferencia.setTriggerAction(TriggerAction.ALL);
        cmbxDocReferencia.setDisplayField("Nombre");
        cmbxDocReferencia.setFieldLabel("Ref. Doc");
        //frmpnlNewFormpanel_1.add(cmbxDocReferencia, new FormData("150%"));
        
        txtfldReferenciaDocumental.setFieldLabel("Ref. Doc");
        frmpnlNewFormpanel_1.add(txtfldReferenciaDocumental, new FormData("150%"));
        
        horizontalPanel.add(frmpnlNewFormpanel_1);
		
        FormPanel frmpnlNewFormpanel_2 = new FormPanel();
        frmpnlNewFormpanel_2.setLabelWidth(95);
        frmpnlNewFormpanel_2.setBorders(false);
        frmpnlNewFormpanel_2.setBodyBorder(false);
        frmpnlNewFormpanel_2.setHeaderVisible(false);
        frmpnlNewFormpanel_2.setHeading("New FormPanel");
        
        cmbxTipoIncidencia.setStore(ListStoreTipoIncidencia);
        cmbxTipoIncidencia.setTriggerAction(TriggerAction.ALL);
        cmbxTipoIncidencia.setDisplayField("Nombre");
        cmbxTipoIncidencia.setFieldLabel(etiquetaTipoIncidencia);
        cmbxTipoIncidencia.addSelectionChangedListener(new SelectionChangedListener<BMMDTipoIncidencia>() {
            public void selectionChanged(SelectionChangedEvent<BMMDTipoIncidencia> se) {
                BMMDTipoIncidencia TipoIncidencia = cmbxTipoIncidencia.getSelection().get(0);
            
                if(TipoIncidencia != null){
                    cmbxTipoBien.mask("Un Momento, Actualizando");
                    RPCTipoBien.getListado2(TipoIncidencia.getId(),"",new RETORNO_ACTUALIZAR_LISTSTORE_TIPO_BIEN());
                                        
                    ListStoreMarca = new ListStore<BMMDMarca>();
                    cmbxMarca.setStore(ListStoreMarca);
                    cmbxMarca.clearSelections();
                    
                    ListStoreModelo = new ListStore<BMMDModelo>();
                    cmbxModelo.setStore(ListStoreModelo);
                    cmbxModelo.clearSelections();
                }
                else
                    cmbxArea.unmask();
            }
        });
        frmpnlNewFormpanel_2.add(cmbxTipoIncidencia, new FormData("150%"));
        
        cmbxTipoBien.setStore(ListStoreTipoBien);
        cmbxTipoBien.setTriggerAction(TriggerAction.ALL);
        cmbxTipoBien.setDisplayField("Nombre");
        cmbxTipoBien.setFieldLabel("Tipo Bien");        
        cmbxTipoBien.addSelectionChangedListener(new SelectionChangedListener<BMMDTipoBien>() {
            public void selectionChanged(SelectionChangedEvent<BMMDTipoBien> se) {
                BMMDTipoIncidencia TipoIncidencia = cmbxTipoIncidencia.getSelection().get(0);
                BMMDTipoBien TipoBien = cmbxTipoBien.getSelection().get(0);
            
                if(TipoBien != null){
                    cmbxMarca.mask("Un Momento, Actualizando");
                    RPCMarca.getListado2(TipoIncidencia.getId(),TipoBien.getId(),"",new RETORNO_ACTUALIZAR_LISTSTORE_MARCA());
                    
                    ListStoreModelo = new ListStore<BMMDModelo>();
                    cmbxModelo.setStore(ListStoreModelo);
                    cmbxModelo.clearSelections();
                }
                else
                    cmbxArea.unmask();
            }
        });
        frmpnlNewFormpanel_2.add(cmbxTipoBien, new FormData("150%"));
        
        cmbxMarca.setStore(ListStoreMarca);
        cmbxMarca.setTriggerAction(TriggerAction.ALL);
        cmbxMarca.setDisplayField("Nombre");
        cmbxMarca.setFieldLabel("Marca");
        cmbxMarca.addSelectionChangedListener(new SelectionChangedListener<BMMDMarca>() {
            public void selectionChanged(SelectionChangedEvent<BMMDMarca> se) {
                BMMDTipoIncidencia TipoIncidencia = cmbxTipoIncidencia.getSelection().get(0);
                BMMDTipoBien TipoBien = cmbxTipoBien.getSelection().get(0);
                BMMDMarca Marca = cmbxMarca.getSelection().get(0);
            
                if(TipoBien != null){
                    cmbxModelo.mask("Un Momento, Actualizando");
                    RPCModelo.getListado2(TipoIncidencia.getId(),TipoBien.getId(),Marca.getNombre(),"",new RETORNO_ACTUALIZAR_LISTSTORE_MODELO());
                }
                else
                    cmbxArea.unmask();
            }
        });
        frmpnlNewFormpanel_2.add(cmbxMarca, new FormData("150%"));
        
        cmbxModelo.setStore(ListStoreModelo);   
        cmbxModelo.setTriggerAction(TriggerAction.ALL);
        cmbxModelo.setDisplayField("Nombre");
        cmbxModelo.setFieldLabel("Modelo");      
        
        frmpnlNewFormpanel_2.add(cmbxModelo, new FormData("150%"));
        
        frmpnlNewFormpanel_2.add(txtfldNumSerie, new FormData("100%"));
        txtfldNumSerie.setFieldLabel("Num. Serie");
        
        frmpnlNewFormpanel_2.add(txtfldNumInventario, new FormData("100%"));
        txtfldNumInventario.setFieldLabel("Num. Inventario");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.addListener(Events.Select, new Listener<ButtonEvent>(){
            @Override
            public void handleEvent(ButtonEvent be) {
                int IdUnidad = 0;
                if(cmbxUnidad.getSelection().size() > 0)
                    IdUnidad = cmbxUnidad.getSelection().get(0).getId();   
                
                int IdArea = 0;                
                if(cmbxArea.getSelection().size() > 0)
                    IdArea = cmbxArea.getSelection().get(0).getId();                                    
                
                String UsuarioReporta = "";
                if(cmbxUsuarioReporta.getSelection().size() > 0)
                    UsuarioReporta = cmbxUsuarioReporta.getSelection().get(0).getNombre();                                    
                
                String DocReferencia = "";
                if(txtfldReferenciaDocumental.getValue() != null && txtfldReferenciaDocumental.getValue().length() > 0)
                    DocReferencia = txtfldReferenciaDocumental.getRawValue();
                    //DocReferencia = cmbxDocReferencia.getSelection().get(0).getNombre();                    
                    
                int IdTipoIncidencia = 0;
                if(cmbxTipoIncidencia.getSelection().size() > 0)
                    IdTipoIncidencia = cmbxTipoIncidencia.getSelection().get(0).getId();                                    
                
                int IdTipoBien = 0;
                if(cmbxTipoBien.getSelection().size() > 0)
                    IdTipoBien = cmbxTipoBien.getSelection().get(0).getId();                 
                
                String Marca = "";
                if(cmbxMarca.getSelection().size() > 0)
                    Marca = cmbxMarca.getSelection().get(0).getNombre();   
                
                String Modelo = "";
                if(cmbxModelo.getSelection().size() > 0)
                    Modelo = cmbxModelo.getSelection().get(0).getNombre(); 
                
                String NumeroSerie = "";
                if(txtfldNumSerie.getValue() != null && txtfldNumSerie.getValue().length() > 0)
                    NumeroSerie = txtfldNumSerie.getValue();                    
                
                String NumeroInventario = "";
                if(txtfldNumInventario.getValue() != null && txtfldNumInventario.getValue().length() > 0)
                    NumeroInventario = txtfldNumInventario.getValue(); 
                
                //System.out.println("handleEvent:"+IdUnidad+":"+IdTipoIncidencia+":"+DocReferencia);
                
                if(IdUnidad > 0 || IdTipoIncidencia > 0 || DocReferencia.length() > 0) {
                    borrar();
                    grid.mask("ACTUALIZANDO... Espere por favor");
                
                    RPCMovimientosFolios.buscar(IdUnidad,
                                                IdArea,
                                                UsuarioReporta,
                                                DocReferencia,
                                                IdTipoIncidencia,
                                                IdTipoBien,
                                                Marca,Modelo,NumeroSerie,NumeroInventario,
                                                new RETORNO_BUSCAR());
                }
                else{
                    MessageBox MensajeError = new MessageBox();
                    MensajeError.setModal(true);
                    MensajeError.alert("ERROR","Debe seleccionar Unidad o Tipo de Incidencia o Referencia documental",null);
                }
            }            
        });
        
        frmpnlNewFormpanel_2.addButton(btnBuscar);
        horizontalPanel.add(frmpnlNewFormpanel_2);
        verticalPanel.add(horizontalPanel);

        FormPanel frmpnlNewFormpanel = new FormPanel();
        frmpnlNewFormpanel.setHeight(400);
        
        frmpnlNewFormpanel.setHeaderVisible(true);
        frmpnlNewFormpanel.setPadding(0);
        frmpnlNewFormpanel.setReadOnly(true);
        frmpnlNewFormpanel.setScrollMode(Style.Scroll.AUTO);
        frmpnlNewFormpanel.setLayout(new FitLayout());
        
        //frmpnlNewFormpanel.setWidth(992);
        frmpnlNewFormpanel.setPadding(2);
        frmpnlNewFormpanel.setHeading("Resultados");
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        
         ColumnConfig clmncnfgStatus = new ColumnConfig("Estado", "Estado", 75);
        configs.add(clmncnfgStatus);
        
        ColumnConfig clmncnfgFolio = new ColumnConfig("Folio", "Folio", 75);
        clmncnfgFolio.setAlignment(Style.HorizontalAlignment.RIGHT);
        configs.add(clmncnfgFolio);
		
        ColumnConfig clmncnfgUnidad = new ColumnConfig("Unidad", "Unidad", 75);
        configs.add(clmncnfgUnidad);

        ColumnConfig clmncnfgArea = new ColumnConfig("Area", "Area", 75);
        configs.add(clmncnfgArea);

        ColumnConfig clmncnfgUsuarioReporta = new ColumnConfig("UsuarioReporta", "Usuario Reporta", 75);
        configs.add(clmncnfgUsuarioReporta);

        ColumnConfig clmncnfgRefDoc = new ColumnConfig("ReferenciaDocumental", "Ref. Doc.", 75);
        configs.add(clmncnfgRefDoc);

        ColumnConfig clmncnfgTipoIncidencia = new ColumnConfig("TipoIncidencia", "Tipo Incidencia", 75);
        configs.add(clmncnfgTipoIncidencia);

        ColumnConfig clmncnfgTipoBien = new ColumnConfig("TipoBien", "Tipo Bien", 75);
        configs.add(clmncnfgTipoBien);

        ColumnConfig clmncnfgMarca = new ColumnConfig("Marca", "Marca", 75);
        configs.add(clmncnfgMarca);

        ColumnConfig clmncnfgModelo = new ColumnConfig("Modelo", "Modelo", 75);
        configs.add(clmncnfgModelo);

        ColumnConfig clmncnfgSerie = new ColumnConfig("NumeroSerie", "# Serie", 75);
        configs.add(clmncnfgSerie);
		
        ColumnConfig clmncnfgInventario = new ColumnConfig("NumeroInventario", "# Inventario", 75);
        configs.add(clmncnfgInventario);
        
        CMGridBuscar = new ColumnModel(configs);

        grid = new Grid<BMMDGridBusqueda>(LSGridBuscar,CMGridBuscar);
        //grid.setWidth(992);
        //frmpnlNewFormpanel.add(grid, new FormData("100%"));
        frmpnlNewFormpanel.add(grid);
        grid.setBorders(true);
        
        verticalPanel.add(frmpnlNewFormpanel);
        add(verticalPanel);
        
        
        
        addWindowListener(new WindowListener() {
            @Override
            public void windowShow(WindowEvent we) {
                borrar();
                LSGridBuscar = new ListStore<BMMDGridBusqueda>();
                grid.reconfigure(LSGridBuscar, CMGridBuscar);
            }
        });        
    }
    private void borrar(){
        cmbxUnidad.clearSelections();
        ListStoreUnidad = new ListStore<BMMDUnidad>();
        cmbxUnidad.setStore(ListStoreUnidad);
        
        cmbxArea.clearSelections();
        ListStoreArea = new ListStore<BMMDArea>();
        cmbxArea.setStore(ListStoreArea);
        
        cmbxDocReferencia.clearSelections();
        ListStoreDocReferencia = new ListStore<BMMDDocReferencia>();
        cmbxDocReferencia.setStore(ListStoreDocReferencia);
        
        txtfldReferenciaDocumental.clear();
        
        cmbxTipoIncidencia.clearSelections();
        ListStoreTipoIncidencia = new ListStore<BMMDTipoIncidencia>();
        cmbxTipoIncidencia.setStore(ListStoreTipoIncidencia);
        
        cmbxTipoBien.clearSelections();
        ListStoreTipoBien = new ListStore<BMMDTipoBien>();
        cmbxTipoBien.setStore(ListStoreTipoBien);
        
        cmbxMarca.clearSelections();
        ListStoreMarca = new ListStore<BMMDMarca>();
        cmbxMarca.setStore(ListStoreMarca);
        
        cmbxModelo.clearSelections();
        ListStoreModelo = new ListStore<BMMDModelo>();
        cmbxModelo.setStore(ListStoreModelo);
        
        txtfldNumSerie.clear();
        txtfldNumInventario.clear();
        
        ActualizarListStore();
    }
    
    private void ActualizarListStore(){
        cmbxUnidad.mask("Un Momento, Actualizando");
        cmbxUsuarioReporta.mask("Un Momento, Actualizando");
        //cmbxDocReferencia.mask("Un Momento, Actualizando");
                
        RPCUnidad.getListado2("",new RETORNO_ACTUALIZAR_LISTSTORE_UNIDAD());
        RPCUsuarioReporta.getListado2("", new RETORNO_ACTUALIZAR_LISTSTORE_USUARIO_REPORTA());
        //RPCDocRef.getListado2("", new RETORNO_ACTUALIZAR_LISTSTORE_REF_DOCUMENTAL());
        RPCTipoIncidencia.getListado2("", new RETORNO_ACTUALIZAR_LISTSTORE_TIPO_INCIDENCIA());        
    }
    
    private class RETORNO_ACTUALIZAR_LISTSTORE_UNIDAD implements AsyncCallback<BMMDUnidad[]>{
        public void onSuccess(BMMDUnidad[] result) {
            ListStoreUnidad = new ListStore<BMMDUnidad>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreUnidad.add(result[posy]);
                posy++;
            }
            cmbxUnidad.setStore(ListStoreUnidad);
            cmbxUnidad.unmask(); 
            cmbxUnidad.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_AREA implements AsyncCallback<BMMDArea[]>{
        public void onSuccess(BMMDArea[] result) {
            ListStoreArea = new ListStore<BMMDArea>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreArea.add(result[posy]);
                posy++;
            }
            cmbxArea.setStore(ListStoreArea);
            cmbxArea.unmask();
            cmbxArea.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_USUARIO_REPORTA implements AsyncCallback<BMMDUsuarioReporta[]>{
        public void onSuccess(BMMDUsuarioReporta[] result) {
            ListStoreUsuarioReporta = new ListStore<BMMDUsuarioReporta>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreUsuarioReporta.add(result[posy]);
                posy++;
            }
            cmbxUsuarioReporta.setStore(ListStoreUsuarioReporta);
            cmbxUsuarioReporta.unmask();
            cmbxUsuarioReporta.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_REF_DOCUMENTAL implements AsyncCallback<BMMDDocReferencia[]>{
        public void onSuccess(BMMDDocReferencia[] result) {
            ListStoreDocReferencia = new ListStore<BMMDDocReferencia>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreDocReferencia.add(result[posy]);
                posy++;
            }
            cmbxDocReferencia.setStore(ListStoreDocReferencia);
            cmbxDocReferencia.unmask();
            cmbxDocReferencia.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_TIPO_INCIDENCIA implements AsyncCallback<BMMDTipoIncidencia[]>{
        public void onSuccess(BMMDTipoIncidencia[] result) {
            ListStoreTipoIncidencia = new ListStore<BMMDTipoIncidencia>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreTipoIncidencia.add(result[posy]);
                posy++;
            }
            cmbxTipoIncidencia.setStore(ListStoreTipoIncidencia);
            cmbxTipoIncidencia.unmask(); 
            cmbxTipoIncidencia.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_TIPO_BIEN implements AsyncCallback<BMMDTipoBien[]>{
        public void onSuccess(BMMDTipoBien[] result) {
            ListStoreTipoBien = new ListStore<BMMDTipoBien>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreTipoBien.add(result[posy]);
                posy++;
            }
            cmbxTipoBien.setStore(ListStoreTipoBien);
            cmbxTipoBien.unmask();
            cmbxTipoBien.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_MARCA implements AsyncCallback<BMMDMarca[]>{
        public void onSuccess(BMMDMarca[] result) {
            ListStoreMarca = new ListStore<BMMDMarca>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreMarca.add(result[posy]);
                posy++;
            }
            cmbxMarca.setStore(ListStoreMarca);
            cmbxMarca.unmask();
            cmbxMarca.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }    
    private class RETORNO_ACTUALIZAR_LISTSTORE_MODELO implements AsyncCallback<BMMDModelo[]>{
        public void onSuccess(BMMDModelo[] result) {
            ListStoreModelo = new ListStore<BMMDModelo>();
            
            int posy = 0; 
            while(posy < result.length){                
                ListStoreModelo.add(result[posy]);
                posy++;
            }
            cmbxModelo.setStore(ListStoreModelo);
            cmbxModelo.unmask();
            cmbxModelo.clearSelections();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }  
    private class RETORNO_BUSCAR implements AsyncCallback<BMMDGridBusqueda[]>{
        public void onSuccess(BMMDGridBusqueda[] result) {
            LSGridBuscar = new ListStore<BMMDGridBusqueda>();
                int posy = 0;
                while(posy < result.length){
                    LSGridBuscar.add(result[posy]);
                    //System.out.println("posy:"+posy+":"+result[posy].get("Folio"));
                    posy++;
                }

                grid.reconfigure(LSGridBuscar, CMGridBuscar);
                grid.unmask();
        }
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }  
}
