/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridViewConfig;
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.helpdesk.client.InterfazUsuario.UIArea;
import org.helpdesk.client.InterfazUsuario.UIAreaSoporte;
import org.helpdesk.client.InterfazUsuario.UIBien;
import org.helpdesk.client.InterfazUsuario.UIMarca;
import org.helpdesk.client.InterfazUsuario.UIModelo;
import org.helpdesk.client.InterfazUsuario.UIPrioridad;
import org.helpdesk.client.InterfazUsuario.UIStatus;
import org.helpdesk.client.InterfazUsuario.UITipoIncidencia;
import org.helpdesk.client.InterfazUsuario.UIUnidad;
import org.helpdesk.client.InterfazUsuario.UIUsuarioReporta;
import org.helpdesk.client.InterfazUsuario.UIUsuarioSoporte;
import org.helpdesk.client.InterfazUsuario.Visor;
import org.helpdesk.client.ModeloDatos.BaseModelArea;
import org.helpdesk.client.ModeloDatos.BaseModelEstatus;
import org.helpdesk.client.ModeloDatos.BaseModelIncidencia;
import org.helpdesk.client.ModeloDatos.BaseModelUnidad;
import org.helpdesk.client.ModeloDatos.BaseModelVistaGridFolios;
import org.helpdesk.client.ModeloDatos.ModeloDatosUsuario;
import org.helpdesk.client.ModeloDatos.ModeloFolioIncidencia;
import org.helpdesk.client.ModeloDatos.BaseModelStatusGrid;
import org.helpdesk.client.ModeloDatos.BaseModelUsuarioSoporte;
import org.helpdesk.client.ModeloDatos.DataModelGridArchivos;
import org.helpdesk.client.ModeloDatos.ModeloEstadoDiagnostico;
import org.helpdesk.client.ServiciosRPC.RPCArchivo;
import org.helpdesk.client.ServiciosRPC.RPCArchivoAsync;
import org.helpdesk.client.ServiciosRPC.RPCAreaAsync;
import org.helpdesk.client.ServiciosRPC.RPCAreaSoporteAsync;
import org.helpdesk.client.ServiciosRPC.RPCBienAsync;
import org.helpdesk.client.ServiciosRPC.RPCEstatusAsync;
import org.helpdesk.client.ServiciosRPC.RPCImpresionAsync;
import org.helpdesk.client.ServiciosRPC.RPCMarcaAsync;
import org.helpdesk.client.ServiciosRPC.RPCModeloAsync;
import org.helpdesk.client.ServiciosRPC.RPCMovimientosFoliosAsync;
import org.helpdesk.client.ServiciosRPC.RPCPrioridadAsync;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReportaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporteAsync;

/**
 *
 * @author Administrador
 */
public class PanelGrid_Captura {
    private RPCUnidadAsync RPCUnidad;
    private RPCAreaAsync RPCArea;
    private RPCTipoIncidenciaAsync RPCUTipoIncidencis;
    private RPCBienAsync RPCBien;
    private RPCPrioridadAsync RPCPrioridad;
    private RPCUsuarioReportaAsync RPCUsuarioReporta;
    private RPCMarcaAsync RPCMarca;
    private RPCModeloAsync RPCModelo;
    private RPCEstatusAsync RPCEstatus;
    private RPCAreaSoporteAsync RPCAreaSoporte;
    private RPCMovimientosFoliosAsync RPCMovimientosFolios;
    private RPCUsuarioSoporteAsync RPCUsuarioSoporte;    
    private PanelGridFolios PanelGridFolios;
    private PanelCapturaDatosFolio PanelCapturaDatosFolio;
    private ModeloDatosUsuario Usuario;
    int diasMinimoAmarillo = 7;
    int diasMaximoAmarillo = 14;
    String etiquetaTipoBien = "Tipo Bien";
    public PanelGrid_Captura(RPCUnidadAsync RPCUnidad,
                                RPCAreaAsync RPCArea,
                                RPCTipoIncidenciaAsync RPCUTipoIncidencia,
                                RPCBienAsync RPCBien,
                                RPCPrioridadAsync RPCPrioridad,
                                RPCUsuarioReportaAsync RPCUsuarioReporta,
                                RPCMarcaAsync RPCMarca,
                                RPCModeloAsync RPCModelo,
                                RPCEstatusAsync RPCEstatus,
                                RPCAreaSoporteAsync RPCAreaSoporte,
                                RPCMovimientosFoliosAsync RPCMovimientosFolios,
                                RPCUsuarioSoporteAsync RPCUsuarioSoporte,
                                RPCImpresionAsync RPCImpresion,
                                int diasMinimoAmarillo,
                                int diasMaximoAmarillo,
                                String etiquetaTipoBien){
        this.diasMinimoAmarillo = diasMinimoAmarillo;
        this.diasMaximoAmarillo = diasMaximoAmarillo;
        this.etiquetaTipoBien = etiquetaTipoBien;

        PanelGridFolios = new PanelGridFolios(RPCMovimientosFolios,RPCImpresion);
        PanelCapturaDatosFolio = new PanelCapturaDatosFolio(RPCUnidad,
                                                            RPCArea,
                                                            RPCUTipoIncidencia,
                                                            RPCBien,
                                                            RPCPrioridad,
                                                            RPCUsuarioReporta,
                                                            RPCMarca,
                                                            RPCModelo,
                                                            RPCEstatus,
                                                            RPCAreaSoporte,
                                                            RPCMovimientosFolios,
                                                            RPCUsuarioSoporte);

    }
    public class PanelGridFolios {
        private FormPanel PanelGridFolios = new FormPanel();
        private List<ColumnConfig> ListColumnConfigGrid;
        private ListStore<BaseModelVistaGridFolios> ListStore;
        private ColumnModel ColumnGrid;
        private Grid<BaseModelVistaGridFolios> grid;
        RPCMovimientosFoliosAsync RPCMovimientosFolios;
        RPCImpresionAsync RPCImpresion;

        Window WindowVerFolio = new Window();
        TextArea TA = new TextArea();
        
        Text TFolio = new Text();
        Text TEstado = new Text();
        Text TUnidad = new Text();
        Text TArea = new Text();
        Text TUsuarioReporta = new Text();
        Text TPrioridad = new Text();

        Text TTipoIncidencia = new Text();
        Text TBien = new Text();
        Text TMarca = new Text();
        Text TModelo = new Text();
        Text TNumeroSerie = new Text();
        Text TNumeroInventario = new Text();
        Text TMotivo = new Text();
        
        Text TAnotacion = new Text();
        Text TUsuarioAsignado = new Text();
        Text TFecha = new Text();

        Grid<BaseModelStatusGrid> GridEstatus;
        ListStore<BaseModelStatusGrid> ListStoreStatus;
        ColumnModel ColumnModelStatus;
        DateTimeFormat FormatoFechaCosto;
        StringFilter FiltroStringUsuarioAsignado = new StringFilter("UsuarioAsignado");
        
        public PanelGridFolios(final RPCMovimientosFoliosAsync RPCMovimientosFolios,final RPCImpresionAsync RPCImpresion) {
            
            this.RPCMovimientosFolios = RPCMovimientosFolios;
            this.RPCImpresion = RPCImpresion;
            ListColumnConfigGrid = new ArrayList<ColumnConfig>();

            //FormatoFechaCosto = DateTimeFormat.getShortDateFormat();
            FormatoFechaCosto = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT);
            
            ColumnConfig columnFolio = new ColumnConfig("Folio","Folio",50);              
            columnFolio.setAlignment(HorizontalAlignment.CENTER);
            ListColumnConfigGrid.add(columnFolio);

            ColumnConfig column = new ColumnConfig("Estatus","Estatus",100);            
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);
            
            GridCellRenderer<BaseModelVistaGridFolios> BotonVerRenderer = new GridCellRenderer<BaseModelVistaGridFolios>() {
               public Object render(final BaseModelVistaGridFolios model, String property, ColumnData config, final int rowIndex,final int colIndex, ListStore<BaseModelVistaGridFolios> store, final Grid<BaseModelVistaGridFolios> grid) {
                 Image imagen = IconHelper.create("ver").createImage();
                 imagen.addClickHandler(new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            int Folio = model.getFolio();
                            RPCImpresion.RPTIncidenciaXFolio(Folio, new RetornoObtenerGridEstatus());
                        }
                    });
                 return imagen;
               }
             };

            column = new ColumnConfig("Ver","Ver",35);
            column.setRenderer(BotonVerRenderer);
            column.setAlignment(HorizontalAlignment.CENTER);
            ListColumnConfigGrid.add(column);

            GridCellRenderer<BaseModelVistaGridFolios> BotonEditarRenderer = new GridCellRenderer<BaseModelVistaGridFolios>() {
                public Object render(final BaseModelVistaGridFolios model, String property, ColumnData config, final int rowIndex,final int colIndex, ListStore<BaseModelVistaGridFolios> store, Grid<BaseModelVistaGridFolios> grid) {
                 Image imagen = IconHelper.create("editar").createImage();
                 imagen.addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent event) {
                                PanelCapturaDatosFolio.cargarFolio(((Integer)model.get("Folio")).intValue());
                            }
                        });
                 return imagen;
               }
             };

            column = new ColumnConfig("Editar","Editar",40);
            column.setRenderer(BotonEditarRenderer);
            column.setAlignment(HorizontalAlignment.CENTER);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Fecha","Fecha",50);
            column.setAlignment(HorizontalAlignment.LEFT);
            column.setDateTimeFormat(FormatoFechaCosto);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Unidad","Unidad",200);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Area","Area",200);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("UsuarioReporta","Usuario Reporta",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("TipoIncidencia","Tipo Incidencia",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Bien","Bien",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Marca","Marca",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Modelo","Modelo",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("NumeroSerie","#Serie",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("NumeroInventario","#Inventario",100);
            column.setAlignment(HorizontalAlignment.RIGHT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Motivo","Motivo",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("Prioridad","Prioridad",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);


            column = new ColumnConfig("Anotacion","Anotacion",100);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            column = new ColumnConfig("UsuarioAsignado","Usuario Asignado",150);
            column.setAlignment(HorizontalAlignment.LEFT);
            ListColumnConfigGrid.add(column);

            ColumnGrid = new ColumnModel(ListColumnConfigGrid);

            ListStore = new ListStore<BaseModelVistaGridFolios>();
            
            GridFilters filters = new GridFilters();
            filters.setLocal(true);            
            
            NumericFilter FiltroFolio = new NumericFilter("Folio");
            //BooleanFilter FiltroActivo = new BooleanFilter("Activo");
            StringFilter FiltroStringUnidad = new StringFilter("Unidad");
            StringFilter FiltroStringArea = new StringFilter("Area");
            StringFilter FiltroStringUsuarioReporta = new StringFilter("UsuarioReporta");
            StringFilter FiltroStringTipoIncidencia = new StringFilter("TipoIncidencia");
            StringFilter FiltroStringBien = new StringFilter("Bien");
            DateFilter FiltroFecha = new DateFilter("Fecha");
            StringFilter FiltroMotivo = new StringFilter("Motivo");
            StringFilter FiltroMarca = new StringFilter("Marca");
            StringFilter FiltroModelo = new StringFilter("Modelo");
            StringFilter FiltroNumeroSerie = new StringFilter("NumeroSerie");
            StringFilter FiltroNumeroInventario = new StringFilter("NumeroInventario");
            StringFilter FiltroStringPrioridad = new StringFilter("Prioridad");
            StringFilter FiltroEstatus = new StringFilter("Estatus");
            StringFilter FiltroAnotacion = new StringFilter("Anotacion");
            
            filters.addFilter(FiltroFolio);
            //filters.addFilter(FiltroActivo);
            filters.addFilter(FiltroFecha);

            filters.addFilter(FiltroStringUnidad);
            filters.addFilter(FiltroStringArea);
            filters.addFilter(FiltroStringUsuarioReporta);
            filters.addFilter(FiltroStringTipoIncidencia);
            filters.addFilter(FiltroStringBien);

            filters.addFilter(FiltroMarca);
            filters.addFilter(FiltroModelo);
            filters.addFilter(FiltroNumeroSerie);
            filters.addFilter(FiltroNumeroInventario);

            filters.addFilter(FiltroStringPrioridad);

            filters.addFilter(FiltroMotivo);
            filters.addFilter(FiltroAnotacion);
            filters.addFilter(FiltroEstatus);

            filters.addFilter(FiltroStringUsuarioAsignado);

            grid = new Grid<BaseModelVistaGridFolios>(ListStore,ColumnGrid);
            grid.getView().setViewConfig(new GridViewConfig() {
            @Override
            public String getRowStyle(ModelData model, int rowIndex, ListStore<ModelData> ds) {                
                BaseModelVistaGridFolios modelo = (BaseModelVistaGridFolios)model;
                
                int diffDays = (int)((Usuario.getFechaLogin().getTime() - modelo.getFecha().getTime()) / (24 * 60 * 60 * 1000));
                
                if(diffDays > diasMinimoAmarillo && diffDays <= diasMaximoAmarillo){
                    return "RowAmarillo";  
                }
                else{
                    if(diffDays > diasMaximoAmarillo){
                        return "RowRojo";  
                    }
                }                               
                //return "RowVerde";
                return "";                    
                }            
            });
            
            grid.setHideHeaders(false);
            grid.setBorders(true);
            grid.setStripeRows(true);
            grid.setColumnLines(true);
            //grid.addPlugin(checkColumn);
            grid.addPlugin(filters);

            PanelGridFolios.setHeaderVisible(true);
            PanelGridFolios.setHeading("FOLIOS");
            PanelGridFolios.setPadding(0);
            PanelGridFolios.setReadOnly(true);
            PanelGridFolios.setScrollMode(Scroll.AUTO);
            PanelGridFolios.setLayout(new FitLayout());

            PanelGridFolios.add(grid);

            
            Timer t = new Timer(){
              @Override
              public void run(){
                actualizar();
              }
            };

            t.scheduleRepeating(120000);
            
            configurarUI();
        }
        private void configurarUI(){
            WindowVerFolio.setHeading("FOLIO");
            WindowVerFolio.setMaximizable(false);
            WindowVerFolio.setMinimizable(false);
            WindowVerFolio.setBlinkModal(true);
            WindowVerFolio.setClosable(true);
            WindowVerFolio.setResizable(false);
            WindowVerFolio.setModal(true);
            WindowVerFolio.setOnEsc(true);
            WindowVerFolio.setWidth(600);
            WindowVerFolio.setButtonAlign(HorizontalAlignment.CENTER);

            FormPanel Form = new FormPanel();
            Form.setHeaderVisible(false);
            Form.setBodyBorder(false);
            Form.setBorders(false);
            Form.setFrame(false);
            Form.setPadding(10);

            HorizontalPanel ContenedorSuperior = new HorizontalPanel();
            ContenedorSuperior.setBorders(false);
            ContenedorSuperior.setVerticalAlign(VerticalAlignment.TOP);
            ContenedorSuperior.setLayout(new RowLayout(Orientation.HORIZONTAL));

            FormPanel ContenedorIzquierdo = new FormPanel();
            ContenedorIzquierdo.setBorders(false);
            ContenedorIzquierdo.setBodyBorder(false);
            ContenedorIzquierdo.setHeaderVisible(false);
            ContenedorIzquierdo.setLabelWidth(100);
            ContenedorIzquierdo.setPadding(0);
            ContenedorIzquierdo.setWidth(300);
            ContenedorIzquierdo.setHeading("ContenedorPrincipal1");
            ContenedorIzquierdo.add(TFolio,new FormData("100%"));
            ContenedorIzquierdo.add(TFecha,new FormData("100%"));
            ContenedorIzquierdo.add(TEstado,new FormData("100%"));
            ContenedorIzquierdo.add(TUnidad,new FormData("100%"));
            ContenedorIzquierdo.add(TArea,new FormData("100%"));
            ContenedorIzquierdo.add(TUsuarioReporta,new FormData("100%"));
            ContenedorIzquierdo.add(TUsuarioReporta,new FormData("100%"));

            FormPanel ContenedorDerecho = new FormPanel();
            ContenedorDerecho.setBorders(false);
            ContenedorDerecho.setBodyBorder(false);
            ContenedorDerecho.setHeaderVisible(false);
            ContenedorDerecho.setPadding(0);
            ContenedorDerecho.setWidth(300);
            ContenedorDerecho.setHeading("ContenedorPrincipal2");
            ContenedorDerecho.add(TTipoIncidencia,new FormData("100%"));
            ContenedorDerecho.add(TBien,new FormData("100%"));
            ContenedorDerecho.add(TMarca,new FormData("100%"));
            ContenedorDerecho.add(TModelo,new FormData("100%"));
            ContenedorDerecho.add(TNumeroSerie,new FormData("100%"));
            ContenedorDerecho.add(TNumeroInventario,new FormData("100%"));
            

            ContenedorSuperior.add(ContenedorIzquierdo);
            ContenedorSuperior.add(ContenedorDerecho);

            TA.setFieldLabel("Motivo Reporte");
            TA.setReadOnly(true);

            List<ColumnConfig> ListColumnConfigStatus;

            ListColumnConfigStatus = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            column.setId("anotacion");
            column.setHeader("Anotacion");
            ListColumnConfigStatus.add(column);

            column = new ColumnConfig();
            column.setId("fecha");
            column.setHeader("Fecha");
            column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy HH:MM"));
            column.setWidth(100);
            column.setRowHeader(true);
            ListColumnConfigStatus.add(column);

            column = new ColumnConfig();
            column.setId("nombre_propio");
            column.setHeader("Usuario Agrego");
            column.setWidth(200);
            ListColumnConfigStatus.add(column);

            ColumnModelStatus = new ColumnModel(ListColumnConfigStatus);

            ListStoreStatus = new ListStore<BaseModelStatusGrid>();
            GridEstatus = new Grid<BaseModelStatusGrid>(ListStoreStatus,ColumnModelStatus);
            GridEstatus.setHideHeaders(false);
            GridEstatus.setAutoExpandColumn("anotacion");
            //grid.setAutoWidth(true);
            GridEstatus.setBorders(true);
            GridEstatus.setHeight(150);

            Form.add(ContenedorSuperior,new FormData("100%"));
            Form.add(TA,new FormData("80%"));
            Form.add(TPrioridad,new FormData("100%"));
            Form.add(GridEstatus,new FormData("100%"));

            WindowVerFolio.add(Form);
        }
        private ModelData setModelData(String propiedad, String valor) {
            ModelData model = new BaseModelData();
            model.set(propiedad, valor);
            return model;
          }
        public FormPanel getPanelGridFolios(){
            return PanelGridFolios;
        }
        private void actualizar(){
            grid.mask("Espere un momento... Actualizando");
            
            RPCMovimientosFolios.ListarFolios(Usuario.getId(),Usuario.isAdministrador(), new RetornoActualizar());            
        }
        private class RetornoActualizar  implements AsyncCallback<String[][]>{
            public void onSuccess(String[][] result){
                String[][] datos = (String[][])result;
                ListStore = new ListStore<BaseModelVistaGridFolios>();
                int posy = 0;
                while(posy < datos.length){
                    ListStore.add(new BaseModelVistaGridFolios(datos[posy]));
                    posy++;
                }

                grid.reconfigure(ListStore, ColumnGrid);
                grid.unmask();

                //FiltroStringUsuarioAsignado.setValue(Usuario.getNombrePropio());
            }
            public void onFailure(Throwable caught){
                MessageBox MensajeError = new MessageBox();
                MensajeError.setClosable(false);
                MensajeError.setModal(true);
                MensajeError.alert("ERROR GRAVE","Ha sucedido un error  grave de comunicacion a la Base de Datos, contacte a su administrador. El sistema permanecera bloqueado",null);
            }
        }
        private class RetornoObtenerGridEstatus  implements AsyncCallback<Void>{
            public void onSuccess(Void result){
                Visor.show("ServletRPTIncidencia");                
            }
            public void onFailure(Throwable caught){
                MessageBox MensajeError = new MessageBox();
                MensajeError.setClosable(false);
                MensajeError.setModal(true);
                MensajeError.alert("ERROR GRAVE","Ha sucedido un error  grave de comunicacion a la Base de Datos, contacte a su administrador. El sistema permanecera bloqueado",null);
            }
        }
    }
    public class PanelCapturaDatosFolio {
        private FormPanel ContenedorA = new FormPanel();
        private FormPanel FormPanelCaptura = new FormPanel();
        private FormPanel FormPanelEstatus = new FormPanel();

        private ListStore ListStoreTMP = new ListStore();

        private int TamañoEtiquetas = 100;
        private ToolTipConfig tool = new ToolTipConfig();
        private boolean MostrarEncabezadoBordes = false;

        private UIUnidad UIUnidad;
        private UIArea UIArea;
        private UIUsuarioReporta UIUsuarioReporta;

        private TextField<String> TFTelefonoUsuarioReporta = new TextField<String>();
        private TextField<String> TFReferenciaDocumental = new TextField<String>();

        private UITipoIncidencia UITipoIncidencia;
        private UIBien UIBIen;
        private UIMarca UIMarca;
        private UIModelo UIModelo;
        private UIPrioridad UIPrioridad;
        private UIStatus UIEstatus;
        private UIAreaSoporte UIAreaSoporte;
        private UIUsuarioSoporte UIUsuarioSoporte;

        private BaseModelUnidad BMUnidad;
        private BaseModelArea BMArea;
        private BaseModelIncidencia BMTipoIncidencia;
        private BaseModelEstatus BMEstatus;
        private EVENTO_SELECCIONA_COMBOBOX_UNIDAD EventoSeleccionaCBUnidad = new EVENTO_SELECCIONA_COMBOBOX_UNIDAD();
        private EVENTO_SELECCIONA_COMBOBOX_TIPO_INCIDENCIA EventoSeelccionaCBTipoIncidencia = new EVENTO_SELECCIONA_COMBOBOX_TIPO_INCIDENCIA();

        private NumberField Folio = new NumberField();
        private TextField<String> NumeroSerie = new TextField<String>();
        private TextField<String> NumeroInventario = new TextField<String>();
        private TextArea MotivoReporte = new TextArea();
        private TextArea Anotacion = new TextArea();

        private Button GrabarNuevoFolio = new Button("GRABAR",new EventoGrabarNuevoFolio());

        private Button GrabarEstatus = new Button("GRABAR",new EventoBotonGrabarEstatus());
        private Button Nuevo = new Button("",new EventoBotonNuevoFolio());
        private int IdUsuario = 0;
        private ModeloFolioIncidencia MDFolio = new ModeloFolioIncidencia();
        private RPCMovimientosFoliosAsync RPCMovimientosFolios;

        private Grid<BaseModelStatusGrid> GridEstatus;
        private ListStore<BaseModelStatusGrid> ListStoreStatus;
        private ColumnModel ColumnModelStatus;

        private UITipoIncidencia UITipoIncidenciaFinal;
        private FormPanel FormPanelArchivo;
        private List<ColumnConfig> ListColumnConfigGridArchivo = new ArrayList<ColumnConfig>();
        private ColumnModel ColumnModelGridArchivo;
        private Grid<DataModelGridArchivos> gridArchivo;
        private ListStore<DataModelGridArchivos> ListStoreGridArchivos = new ListStore<DataModelGridArchivos>();
        private RPCArchivoAsync RPCArchivo;
        public PanelCapturaDatosFolio(RPCUnidadAsync RPCUnidad,
                                        RPCAreaAsync RPCArea,
                                        RPCTipoIncidenciaAsync RPCUTipoIncidencia,
                                        RPCBienAsync RPCBien,
                                        RPCPrioridadAsync RPCPrioridad,
                                        RPCUsuarioReportaAsync RPCUsuarioReporta,
                                        RPCMarcaAsync RPCMarca,
                                        RPCModeloAsync RPCModelo,
                                        RPCEstatusAsync RPCEstatus,
                                        RPCAreaSoporteAsync RPCAreaSoporte,
                                        RPCMovimientosFoliosAsync RPCMovimientosFolios,
                                        RPCUsuarioSoporteAsync RPCUsuarioSoporte) {

            this.RPCMovimientosFolios = RPCMovimientosFolios;
            
            RPCArchivo = (RPCArchivoAsync) GWT.create(RPCArchivo.class);

            UIUnidad = new UIUnidad(TamañoEtiquetas,RPCUnidad);
            UIUnidad.getCBNombre().addSelectionChangedListener(EventoSeleccionaCBUnidad);

            UIArea = new UIArea(TamañoEtiquetas,RPCArea);

            UITipoIncidencia = new UITipoIncidencia(TamañoEtiquetas,RPCUTipoIncidencia);
            UITipoIncidencia.setEtiqueta(etiquetaTipoBien);
            
            UITipoIncidencia.setaddSelectionChangedListenerCBNombre(EventoSeelccionaCBTipoIncidencia);

            UIBIen = new UIBien(TamañoEtiquetas,RPCBien);

            UIPrioridad = new UIPrioridad(TamañoEtiquetas,RPCPrioridad);

            UIUsuarioReporta = new UIUsuarioReporta(TamañoEtiquetas,RPCUsuarioReporta);

            UIMarca = new UIMarca(TamañoEtiquetas,RPCMarca);

            UIModelo = new UIModelo(TamañoEtiquetas,RPCModelo);

            UIEstatus = new UIStatus(TamañoEtiquetas-50,RPCEstatus);

            UIAreaSoporte = new UIAreaSoporte(TamañoEtiquetas,RPCAreaSoporte);

            UIUsuarioSoporte = new UIUsuarioSoporte(0,RPCUsuarioSoporte);

            UITipoIncidenciaFinal = new UITipoIncidencia(120,RPCUTipoIncidencia);
            UITipoIncidenciaFinal.getCBNombre().setFieldLabel("Tipo Incidencia Final");

            UIArea.setFiltroComboBox(0);

            ContenedorA.setHeaderVisible(false);
            ContenedorA.setFrame(false);
            ContenedorA.setBodyBorder(false);
            ContenedorA.setBorders(false);
            ContenedorA.setPadding(0);
            ContenedorA.setHeading("ContenedorA --- CAPTURA DE DATOS");
            ContenedorA.setLayout(new RowLayout(Orientation.VERTICAL));
            ContenedorA.setScrollMode(Scroll.AUTO);

    //*******************************************************************************************************************************

            tool = new ToolTipConfig();
            tool.setTrackMouse(true);

            Folio.setAllowBlank(true);
            Folio.setAllowDecimals(false);
            Folio.setAllowNegative(false);
            Folio.setSelectOnFocus(true);
            Folio.setId("folio");
            Folio.setFieldLabel("Folio");
            Folio.setToolTip(tool);
            Folio.setToolTip("Folio del evento(entero positivo sin signo)");
            Folio.addKeyListener(new OyenteTextFieldFolio());
            Folio.setPropertyEditorType(Integer.class);

            TFTelefonoUsuarioReporta.setFieldLabel("Tel. Contac.");
            TFReferenciaDocumental.setFieldLabel("Ref. Doc.");

            NumeroInventario.setAllowBlank(true);
            NumeroInventario.setSelectOnFocus(true);
            NumeroInventario.setId("numero_inventario");
            NumeroInventario.setToolTip(tool);
            NumeroInventario.setToolTip("Numero Inventario segun Depto. Activo Fijo");
            NumeroInventario.setFieldLabel("# Inventario");

            NumeroSerie.setAllowBlank(true);
            NumeroSerie.setSelectOnFocus(true);
            NumeroSerie.setId("numero_serie");
            NumeroSerie.setToolTip(tool);
            NumeroSerie.setToolTip("Numero de Serie de Fabricante");
            NumeroSerie.setFieldLabel("# Serie");

            MotivoReporte.setAllowBlank(false);
            MotivoReporte.setSelectOnFocus(true);
            MotivoReporte.setFieldLabel("Motivo Reporte");
            MotivoReporte.setToolTip(tool);
            MotivoReporte.setToolTip("Motivo del reporte (error localizado por el usuario, el cual debe contener el procedimiento exacto para reproducirlo)");

            Anotacion.setAllowBlank(false);
            Anotacion.setSelectOnFocus(true);
            Anotacion.setFieldLabel("Anotacion");
            Anotacion.setToolTip(tool);
            Anotacion.setToolTip("Anotacion");

            Nuevo.setToolTip(tool);
            Nuevo.setToolTip("Nuevo Folio");
            Nuevo.setIcon(IconHelper.create("nuevo"));

            UIEstatus.setaddSelectionChangedListenerCBNombre(new EVENTO_SELECCIONA_COMBOBOX_ESTATUS());

    //****************************************************************************************************************************

            HBoxLayout layout = new HBoxLayout();
            layout.setPadding(new Padding(0));
            layout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

            HBoxLayout LayoutFolio = new HBoxLayout();
            LayoutFolio.setPadding(new Padding(0));
            LayoutFolio.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

            HBoxLayoutData FlexFolio = new HBoxLayoutData(new Margins(0, 5, 0, 0));
            FlexFolio.setFlex(1);

            HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
            flex.setFlex(1);

            HBoxLayout layout2 = new HBoxLayout();
            layout2.setPadding(new Padding(0));
            layout2.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

            HBoxLayoutData flex2 = new HBoxLayoutData(new Margins(0, 5, 0, 0));
            flex2.setFlex(1);

    //***************************************************************************************************************************************

            LayoutContainer ContenedorA1 = new LayoutContainer();

            ContenedorA1.setBorders(MostrarEncabezadoBordes);
            ContenedorA1.setLayout(layout);

            FormPanel  ContenedorA1_1 = new FormPanel ();
            ContenedorA1_1.setHeaderVisible(MostrarEncabezadoBordes);
            ContenedorA1_1.setPadding(5);
            ContenedorA1_1.setFrame(false);
            ContenedorA1_1.setBodyBorder(MostrarEncabezadoBordes);
            ContenedorA1_1.setBorders(MostrarEncabezadoBordes);
            ContenedorA1_1.setHeading("ContenedorA1_1  -- FOLIO, UBICACION Y TIPO DE REPORTE --");
            ContenedorA1_1.setLabelWidth(TamañoEtiquetas);

            FormPanel  ContenedorA1_2 = new FormPanel ();
            ContenedorA1_2.setHeaderVisible(MostrarEncabezadoBordes);
            ContenedorA1_2.setPadding(5);
            ContenedorA1_2.setFrame(false);
            ContenedorA1_2.setBodyBorder(MostrarEncabezadoBordes);
            ContenedorA1_2.setBorders(MostrarEncabezadoBordes);
            ContenedorA1_2.setHeading("ContenedorA1_2  -- DESCRIPCION DEL BIEN --");
            ContenedorA1_2.setLabelWidth(TamañoEtiquetas);

            LayoutContainer ContenedorFolio = new LayoutContainer();
            ContenedorFolio.setBorders(MostrarEncabezadoBordes);
            ContenedorFolio.setLayout(LayoutFolio);

            FormPanel FormFolio = new FormPanel ();
            FormFolio.setPadding(0);
            FormFolio.setBodyBorder(MostrarEncabezadoBordes);
            FormFolio.setHeaderVisible(MostrarEncabezadoBordes);
            FormFolio.setHeading("FormFolio");
            FormFolio.setLabelWidth(TamañoEtiquetas);            

            FormPanel  ContenedorA2 = new FormPanel ();
            ContenedorA2.setHeaderVisible(MostrarEncabezadoBordes);
            ContenedorA2.setHeading("CONTENEDOR A2");
            ContenedorA2.setPadding(10);
            ContenedorA2.setLabelWidth(TamañoEtiquetas);
            ContenedorA2.setBodyBorder(MostrarEncabezadoBordes);

            FormPanel ContenedorA4 = new FormPanel();
            ContenedorA4.setBodyBorder(true);
            ContenedorA4.setBorders(true);
            ContenedorA4.setHeaderVisible(true);
            ContenedorA4.setHeading("ContenedorA4 Status");
            ContenedorA4.setPadding(0);
            ContenedorA4.setReadOnly(true);

            List<ColumnConfig> ListColumnConfigStatus;

            ListColumnConfigStatus = new ArrayList<ColumnConfig>();

            ColumnConfig column = new ColumnConfig();
            
            column.setId("Estatus");
            column.setHeader("Estatus");
            column.setWidth(100);
            ListColumnConfigStatus.add(column);

            column = new ColumnConfig();
            column.setId("anotacion");
            column.setHeader("Anotacion");
            ListColumnConfigStatus.add(column);

            column = new ColumnConfig();
            column.setId("fecha");
            column.setHeader("Fecha");
            column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy HH:MM"));
            column.setWidth(100);
            column.setRowHeader(true);
            ListColumnConfigStatus.add(column);

            column = new ColumnConfig();
            column.setId("nombre_propio");
            column.setHeader("Usuario Agrego");
            column.setWidth(150);
            ListColumnConfigStatus.add(column);

            ColumnModelStatus = new ColumnModel(ListColumnConfigStatus);

            ListStoreStatus = new ListStore<BaseModelStatusGrid>();
            GridEstatus = new Grid<BaseModelStatusGrid>(ListStoreStatus,ColumnModelStatus);
            GridEstatus.setHideHeaders(false);
            GridEstatus.setAutoExpandColumn("anotacion");
            //grid.setAutoWidth(true);
            GridEstatus.setStripeRows(true);
            GridEstatus.setBorders(true);
            GridEstatus.setHeight(150);

            FormPanelCaptura.setPadding(0);
            FormPanelCaptura.setFrame(true);
            FormPanelCaptura.setBorders(true);
            FormPanelCaptura.setBodyBorder(true);
            FormPanelCaptura.setHeaderVisible(true);
            FormPanelCaptura.setHeading("Control de Folios");
            FormPanelCaptura.setLabelWidth(TamañoEtiquetas);
            FormPanelCaptura.setButtonAlign(HorizontalAlignment.CENTER);

            FormPanelEstatus.setPadding(0);
            FormPanelEstatus.setFrame(true);
            FormPanelEstatus.setBorders(false);
            FormPanelEstatus.setBodyBorder(false);
            FormPanelEstatus.setHeaderVisible(true);
            FormPanelEstatus.setHeading("Control de Estatus");
            FormPanelEstatus.setLabelWidth(TamañoEtiquetas);
            FormPanelEstatus.setButtonAlign(HorizontalAlignment.CENTER);

    //********************************************************************************************************

            FormFolio.add(Folio, new FormData("100%"));

            ContenedorFolio.add(FormFolio, FlexFolio);
            ContenedorFolio.add(Nuevo);//, FlexFolio);

            ContenedorA1_1.add(ContenedorFolio,new FormData("50%"));
            ContenedorA1_1.add(UIUnidad.getLCComboBoxActualizar(),new FormData("95%"));
            ContenedorA1_1.add(UIArea.getCBNombre(),new FormData("95%"));
            ContenedorA1_1.add(UIUsuarioReporta.getLCTextFieldBuscar(),new FormData("95%"));
            //ContenedorA1_1.add(UIUsuarioReporta.getLCComboBoxActualizar(),new FormData("95%"));
            ContenedorA1_1.add(TFTelefonoUsuarioReporta,new FormData("95%"));
            ContenedorA1_1.add(TFReferenciaDocumental,new FormData("95%"));

            ContenedorA1_2.add(UITipoIncidencia.getLCComboBoxActualizar(),new FormData("95%"));
            ContenedorA1_2.add(UIBIen.getCBNombre(),new FormData("95%"));
            ContenedorA1_2.add(UIMarca.getLCComboBoxActualizar(),new FormData("95%"));
            ContenedorA1_2.add(UIModelo.getLCComboBoxActualizar(),new FormData("95%"));
            ContenedorA1_2.add(NumeroSerie,new FormData("95%"));
            ContenedorA1_2.add(NumeroInventario,new FormData("95%"));

            //ContenedorIzquierdo.add(UIEstatus.getLCComboBoxActualizar(), new FormData("100%"));            
            UIUsuarioSoporte.getCBNombre().setFieldLabel("");
            UIUsuarioSoporte.getCBNombre().setLabelSeparator("");

    //*************************************************************************************************************
            ContenedorA1.add(ContenedorA1_1, flex);
            ContenedorA1.add(ContenedorA1_2, flex);

            ContenedorA2.add(MotivoReporte,new FormData("80%"));
            ContenedorA2.add(UIPrioridad.getLCComboBoxActualizar(),new FormData("30%"));
    //*****************************************************************************************************************
            FormPanelCaptura.add(ContenedorA1,new FormData("100%"));
            FormPanelCaptura.add(ContenedorA2,new FormData("100%"));
            FormPanelCaptura.addButton(GrabarNuevoFolio);                                   
    //********************************************************************************************************
            HorizontalPanel horizontalPanel = new HorizontalPanel();
            horizontalPanel.setBorders(false);
		
            FormPanel FormPanelCombosEstatus = new FormPanel();
            FormPanelCombosEstatus.setBodyBorder(false);
            FormPanelCombosEstatus.setBorders(false);
            FormPanelCombosEstatus.setHeaderVisible(false);
            FormPanelCombosEstatus.setLabelWidth(120);
            FormPanelCombosEstatus.setHeading("New FormPanel");
            FormPanelCombosEstatus.setSize("300", "120");

            FormPanelCombosEstatus.add(UIEstatus.getLCComboBoxActualizar(), new FormData("100%"));		
            FormPanelCombosEstatus.add(UITipoIncidenciaFinal.getLCComboBoxActualizar(), new FormData("100%"));
            FormPanelCombosEstatus.add(new Label("Asignacion del usuario responsable del soporte"), new FormData("100%"));
            FormPanelCombosEstatus.add(UIUsuarioSoporte.getLCComboBoxActualizar(), new FormData("100%"));

            //Button btnGrabar = new Button("GRABAR");
                
            FormPanel FormPanelAnotacion = new FormPanel();
            FormPanelAnotacion.setBodyBorder(false);
            FormPanelAnotacion.setBorders(false);
            FormPanelAnotacion.setButtonAlign(HorizontalAlignment.CENTER);
            FormPanelAnotacion.setHeaderVisible(false);
            FormPanelAnotacion.setLabelWidth(60);
            FormPanelAnotacion.setHeading("New FormPanel");
            FormPanelAnotacion.setSize("300", "110");
            FormPanelAnotacion.add(Anotacion, new FormData("100%"));
            FormPanelAnotacion.add(GrabarEstatus, new FormData("30%"));
            //FormPanelAnotacion.add(btnGrabar, new FormData("30%"));

            HorizontalPanel HPCombosEstatusAnotacion = new HorizontalPanel();
            HPCombosEstatusAnotacion.setBorders(false);

            HPCombosEstatusAnotacion.add(FormPanelCombosEstatus);
            HPCombosEstatusAnotacion.add(FormPanelAnotacion);
            
            final FileUploadField flpldfldSeleccionararchivo = new FileUploadField();	   
            
            FormPanelArchivo = new FormPanel();
            FormPanelArchivo.setBodyBorder(false);
            FormPanelArchivo.setBorders(false);
            FormPanelArchivo.setHeaderVisible(false);
            FormPanelArchivo.setLabelWidth(50);
            FormPanelArchivo.setHeading("New FormPanel");
            FormPanelArchivo.setEncoding(FormPanel.Encoding.MULTIPART);
            FormPanelArchivo.setMethod(FormPanel.Method.POST);
            FormPanelArchivo.setAction(GWT.getModuleBaseURL()+"rpcarchivo");
            FormPanelArchivo.setSize("300", "260");	                
            FormPanelArchivo.addListener(Events.BeforeSubmit, new Listener<FormEvent>() {
                public void handleEvent(FormEvent be) {
                    // before submit event
                }
            });
            FormPanelArchivo.addListener(Events.Submit, new Listener<FormEvent>() {
                public void handleEvent(FormEvent be) {
                    String ResultHTML = be.getResultHtml();                       
                    MessageBox MB = new MessageBox();
                    MB.setMaxWidth(600);                
                    if(ResultHTML.contains("OK")){
                        MB.info("Grabar","El archivo se subio con exito", null);
                        RPCArchivo.listado(Folio.getValue().intValue(), new RETORNO_LISTADO_ARCHIVOS());
                    }
                    else
                        MB.info("Grabar","Hubo un error al subir el archivo, contacte con el administrador", null);    
                    
                    flpldfldSeleccionararchivo.clear();
                }
            });  
                
            VerticalPanel verticalPanel = new VerticalPanel();
            verticalPanel.setBorders(false);

            verticalPanel.add(HPCombosEstatusAnotacion);
            verticalPanel.add(GridEstatus);
                         
            flpldfldSeleccionararchivo.setName("upload");
            flpldfldSeleccionararchivo.setFieldLabel("Archivo");

            Button btnEnviar = new Button("ENVIAR");
            btnEnviar.addListener(Events.Select, new Listener<ButtonEvent>() {
                public void handleEvent(ButtonEvent e) { 
                        //Cookies.setCookie("Folio",""+MDFolio.getFolio()+"");
                        Cookies.setCookie("Folio",""+Folio.getValue().intValue());
                        
                        FormPanelArchivo.submit();  
                        
                }
            });                
		
            ListColumnConfigGridArchivo = new ArrayList<ColumnConfig>();		
            ColumnConfig clmncnfgNombre = new ColumnConfig();
            clmncnfgNombre.setId("Nombre");
            clmncnfgNombre.setHeader("Nombre");
            ListColumnConfigGridArchivo.add(clmncnfgNombre);
            
            ColumnModelGridArchivo =  new ColumnModel(ListColumnConfigGridArchivo);

            gridArchivo = new Grid(ListStoreGridArchivos,ColumnModelGridArchivo);
            gridArchivo.setAutoExpandColumn("Nombre");
            gridArchivo.setHeight("150");
            gridArchivo.setBorders(true);

            Button btnVer = new Button("VER");
            btnVer.addListener(Events.Select, new Listener<ButtonEvent>() {
            public void handleEvent(ButtonEvent e) {
                DataModelGridArchivos Archivo = gridArchivo.getSelectionModel().getSelectedItem();
                    if(Archivo != null){
                        //System.out.println("Id:"+Archivo.getId());
                        //System.out.println("Nombre:"+Archivo.getNombre());
                        RPCArchivo.imprimirArchivo(Archivo.getId(), Archivo.getNombre(),new RETORNO_IMPRIMIR_ARCHIVO());
                    }
                }
            });

            FormPanelArchivo.add(flpldfldSeleccionararchivo, new FormData("100%"));
            FormPanelArchivo.add(btnEnviar, new FormData("25%"));
            FormPanelArchivo.add(gridArchivo, new FormData("100%"));
            FormPanelArchivo.add(btnVer, new FormData("25%"));

            horizontalPanel.add(verticalPanel);
            horizontalPanel.add(FormPanelArchivo);

            FormPanelEstatus.add(horizontalPanel,new FormData("100%"));
    //*******************************************************************************************************************

            ContenedorA.add(FormPanelCaptura);
            ContenedorA.add(FormPanelEstatus);
            EstadoGrabarNuevo(); 
        }
        
        
        public FormPanel getPanelCapturaDatosFolio(){
            return ContenedorA;
        }
        private void EstadoGrabarNuevo(){
            Folio.clear();

            UIUnidad.setClear();
            UIArea.setClear();
            UIUsuarioReporta.setClear();
            TFTelefonoUsuarioReporta.clear();
            TFReferenciaDocumental.clear();
            
            UITipoIncidencia.setClear();
            UIBIen.setClear();
            UIMarca.setClear();
            UIModelo.setClear();
            NumeroSerie.clear();
            NumeroInventario.clear();
            MotivoReporte.clear();
            UIPrioridad.setClear();

            UIUsuarioSoporte.setClear();
            UITipoIncidenciaFinal.setClear();

            ListStoreStatus = new ListStore<BaseModelStatusGrid>();
            GridEstatus.reconfigure(ListStoreStatus, ColumnModelStatus);

            UIUnidad.getCBNombre().setReadOnly(false);
            UIArea.getCBNombre().setReadOnly(false);
            UIUnidad.getCBNombre().setReadOnly(false);
            UIArea.getCBNombre().setReadOnly(false);
            UITipoIncidencia.getCBNombre().setReadOnly(false);
            UIBIen.getCBNombre().setReadOnly(false);
            UIPrioridad.getCBNombre().setReadOnly(false);
            UIUsuarioReporta.getCBNombre().setReadOnly(false);
            UIMarca.getCBNombre().setReadOnly(false);
            UIModelo.getCBNombre().setReadOnly(false);
            NumeroSerie.setReadOnly(false);
            NumeroInventario.setReadOnly(false);
            MotivoReporte.setReadOnly(false);
            GrabarNuevoFolio.setEnabled(true);

            GrabarEstatus.setEnabled(false);
            UIUsuarioSoporte.getCBNombre().setEnabled(false);
            UITipoIncidenciaFinal.getCBNombre().setEnabled(false);
            
            ListStoreGridArchivos = new ListStore<DataModelGridArchivos>();
            gridArchivo.reconfigure(ListStoreGridArchivos, ColumnModelGridArchivo); 
            
        }
        private class EVENTO_SELECCIONA_COMBOBOX_UNIDAD extends SelectionChangedListener<BaseModelUnidad>{
            @Override
            public void selectionChanged(SelectionChangedEvent<BaseModelUnidad> se) {
                BMUnidad = se.getSelectedItem();
                UIArea.ActualizarListStore(BMUnidad.getId());
            }
        }
        private class EVENTO_SELECCIONA_COMBOBOX_TIPO_INCIDENCIA extends SelectionChangedListener<BaseModelIncidencia>{
            @Override
            public void selectionChanged(SelectionChangedEvent<BaseModelIncidencia> se) {
                BMTipoIncidencia = se.getSelectedItem();
                UIBIen.ActualizarListStore(BMTipoIncidencia.getId());
            }
        }
        private class EVENTO_SELECCIONA_COMBOBOX_ESTATUS extends SelectionChangedListener<BaseModelEstatus>{
            @Override
            public void selectionChanged(SelectionChangedEvent<BaseModelEstatus> se) {
                BMEstatus = se.getSelectedItem();
                UIUsuarioSoporte.getCBNombre().clearSelections();

                UIUsuarioSoporte.setClear();
                //UITipoIncidenciaFinal.setClear();

                UIUsuarioSoporte.getCBNombre().setEnabled(false);
                UITipoIncidenciaFinal.getCBNombre().setEnabled(false);

                if(MDFolio.getActivo()){
                    if(BMEstatus.getReasignar())
                        UIUsuarioSoporte.getCBNombre().setEnabled(true);                    
                    else
                        if(BMEstatus.getDiagnostinoFinal())
                            UITipoIncidenciaFinal.getCBNombre().setEnabled(true);
                }
            }
        }
        private class EventoBotonNuevoFolio extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce) {
                EstadoGrabarNuevo();
            }
        }

        public void setUsuario(int IdUsuario){
            this.IdUsuario = IdUsuario;
        }

        private void VisualizarFolio(){
            ContenedorA.unmask();
            Folio.setValue(0);
            UIUnidad.getCBNombre().setReadOnly(true);
            UIArea.getCBNombre().setReadOnly(true);
            UIUsuarioReporta.getCBNombre().setReadOnly(true);
            UITipoIncidencia.getCBNombre().setReadOnly(true);
            UIBIen.getCBNombre().setReadOnly(true);
            UIMarca.getCBNombre().setReadOnly(true);
            UIModelo.getCBNombre().setReadOnly(true);
            NumeroSerie.setReadOnly(true);
            NumeroInventario.setReadOnly(true);
            MotivoReporte.setReadOnly(true);
            UIPrioridad.getCBNombre().setReadOnly(true);
            GrabarNuevoFolio.setEnabled(false);
            
            

            Folio.setValue(MDFolio.getFolio());
            UIUnidad.getCBNombre().setRawValue(MDFolio.getUnidad());
            UIArea.getCBNombre().setRawValue(MDFolio.getArea());
            //UIUsuarioReporta.getCBNombre().setRawValue(MDFolio.getUsuarioReporta());
            UIUsuarioReporta.getTFNombre().setRawValue(MDFolio.getUsuarioReporta());

            TFTelefonoUsuarioReporta.setRawValue(MDFolio.getTelefonoContacto());
            TFReferenciaDocumental.setRawValue(MDFolio.getReferenciaDocumental());

            UITipoIncidencia.getCBNombre().setRawValue(MDFolio.getIncidencia());
            UIBIen.getCBNombre().setRawValue(MDFolio.getBien());
            UIMarca.getCBNombre().setRawValue(MDFolio.getMarca());
            UIModelo.getCBNombre().setRawValue(MDFolio.getModelo());
            NumeroSerie.setRawValue(MDFolio.getNumeroSerie());
            NumeroInventario.setRawValue(MDFolio.getNumeroInventario());
            MotivoReporte.setRawValue(MDFolio.getMotivoReporte());
            UIPrioridad.getCBNombre().setRawValue(MDFolio.getPrioridad());
            
            if(MDFolio.getActivo())
                GrabarEstatus.setEnabled(true);            
            else
                GrabarEstatus.setEnabled(false);            

            VisualizarGridEstatus();
        }
        private void VisualizarGridEstatus(){
            ListStoreStatus = new ListStore<BaseModelStatusGrid>();

            int posx = 0;
            while(posx < MDFolio.getDatosEstatus().length){
                ListStoreStatus.add(new BaseModelStatusGrid(MDFolio.getDatosEstatus()[posx][0],
                                                            MDFolio.getDatosEstatus()[posx][1],
                                                            new Date(Timestamp.valueOf(MDFolio.getDatosEstatus()[posx][2]).getTime()),
                                                            MDFolio.getDatosEstatus()[posx][3]));
                posx++;
            }

            GridEstatus.reconfigure(ListStoreStatus, ColumnModelStatus);
            
            if(MDFolio.getActivo())
                GrabarEstatus.setEnabled(true);
            else
                GrabarEstatus.setEnabled(false);

            if(MDFolio.getIdTipoIncidenciaFinal() == 0)
                UITipoIncidenciaFinal.setId(MDFolio.getIdTipoIncidencia());
            else
                UITipoIncidenciaFinal.setId(MDFolio.getIdTipoIncidenciaFinal());

            UIUsuarioSoporte.setClear();
            UIEstatus.setClear();
            
            //UIUsuarioSoporte.getCBNombre().setReadOnly(false);
            UIUsuarioSoporte.getCBNombre().setEnabled(false);

            UITipoIncidenciaFinal.getCBNombre().setEnabled(false);

            ContenedorA.unmask();
        }

        private class EventoGrabarNuevoFolio extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce) {
                MDFolio.setIdUnidad(UIUnidad.getBaseModelComboBox().getId());
                MDFolio.setIdArea(UIArea.getBaseModelComboBox().getId());
                MDFolio.setUsuarioReporta(UIUsuarioReporta.getTFNombre().getRawValue());

                MDFolio.setTelefonoContacto(TFTelefonoUsuarioReporta.getValue());
                MDFolio.setReferenciaDocumental(TFReferenciaDocumental.getValue());

                MDFolio.setIdTipoIncidencia(UITipoIncidencia.getBaseModelComboBox().getId());
                MDFolio.setIdBien(UIBIen.getBaseModelComboBox().getId());
                MDFolio.setMarca(UIMarca.getCBNombre().getRawValue());
                MDFolio.setModelo(UIModelo.getCBNombre().getRawValue());
                MDFolio.setNumeroSerie((String)NumeroSerie.getValue());
                MDFolio.setNumeroInventario((String)NumeroInventario.getValue());
                MDFolio.setMotivoReporte((String)MotivoReporte.getValue());
                MDFolio.setIdPrioridad(UIPrioridad.getBaseModelComboBox().getId());
                MDFolio.setIdUsuarioSoporteAsignado(IdUsuario);

                if(MDFolio.getNumeroSerie() == null)
                    MDFolio.setNumeroSerie("");

                if(MDFolio.getNumeroInventario() == null)
                    MDFolio.setNumeroInventario("");
                /*
                System.out.println("MDFolio.getIdUnidad():"+MDFolio.getIdUnidad());
                System.out.println("MDFolio.getIdArea():"+MDFolio.getIdArea());
                System.out.println("MDFolio.getUsuarioReporta():"+MDFolio.getUsuarioReporta());
                System.out.println("MDFolio.getIdTipoIncidencia():"+MDFolio.getIdTipoIncidencia());
                System.out.println("MDFolio.getIdBien():"+MDFolio.getIdBien());
                System.out.println("MDFolio.getMotivoReporte():"+MDFolio.getMotivoReporte());
                System.out.println("MDFolio.getIdPrioridad():"+MDFolio.getIdPrioridad());
                System.out.println("MDFolio.getIdUsuarioSoporteAsignado():"+MDFolio.getIdUsuarioSoporteAsignado());
                */
                if(MDFolio.getIdUnidad() > 0 &&
                    MDFolio.getIdArea() > 0 &&
                    MDFolio.getUsuarioReporta().length() > 0 &&
                    MDFolio.getIdTipoIncidencia() > 0 &&
                    MDFolio.getIdBien() > 0 &&
                    MDFolio.getMotivoReporte().length() > 0 &&
                    MDFolio.getIdPrioridad() > 0 &&
                    MDFolio.getIdUsuarioSoporteAsignado() > 0){
                    ContenedorA.mask("GRABANDO");
                    RPCMovimientosFolios.Insertar(MDFolio, new RetornoGrabarNuevoFolio());
                }
                else{
                    MessageBox.info("Grabar Nuevo Folio","Faltan campos por llenarse",null);
                }
            }
        }
        private class RetornoGrabarNuevoFolio  implements AsyncCallback<ModeloFolioIncidencia>{
            public void onSuccess(ModeloFolioIncidencia result){
                MDFolio = (ModeloFolioIncidencia)result;

                if(MDFolio.getFolio() > 0){
                    MessageBox.info("Grabar Nuevo Folio","Se genereo el Folio Numero: "+MDFolio.getFolio(),null);
                    VisualizarFolio();
                }
                else
                    MessageBox.info("Grabar Nuevo Folio","El folio no pudo ser grabado, favor de verificar",null);
            }
            public void onFailure(Throwable caught){
                MessageBox MensajeError = new MessageBox();
                MensajeError.setClosable(false);
                MensajeError.setModal(true);
                MensajeError.alert("ERROR GRAVE","Ha sucedido un error  grave de comunicacion a la Base de Datos, contacte a su administrador. El sistema permanecera bloqueado",null);
            }
        }

        class OyenteTextFieldFolio extends KeyListener{
            @Override
            public void componentKeyDown(ComponentEvent event){
                Integer folio1 = (Integer) Folio.getValue();
                if(event.getKeyCode() == KeyCodes.KEY_ENTER && folio1.intValue() > 0){
                    ContenedorA.mask("Buscando Folio ... Espere");
                    RPCMovimientosFolios.ObtenerFolio(folio1.intValue(), new RetornoObtenerFolio());
                    
                }
            }
        }

        public void cargarFolio(int Folio){
            ContenedorA.mask("Buscando Folio ... Espere");
            RPCMovimientosFolios.ObtenerFolio(Folio, new RetornoObtenerFolio());
        }
        private class RetornoObtenerFolio  implements AsyncCallback<ModeloFolioIncidencia>{
            public void onSuccess(ModeloFolioIncidencia result){
                MDFolio = (ModeloFolioIncidencia)result;
                
                if(MDFolio.getFolio() > 0){
                    Cookies.setCookie("Folio",""+MDFolio.getFolio()+"");
                    gridArchivo.mask("Buscando...");
                    RPCArchivo.listado(MDFolio.getFolio(), new RETORNO_LISTADO_ARCHIVOS());
                    VisualizarFolio();
                }                    
                else{
                    MessageBox.info("Obtener Folio","El folio no existe, favor de verificar",null);
                    Folio.clear();
                    ListStoreGridArchivos = new ListStore<DataModelGridArchivos>();
                    gridArchivo.reconfigure(ListStoreGridArchivos, ColumnModelGridArchivo);    
                }

                ContenedorA.unmask();
            }
            public void onFailure(Throwable caught){
                MessageBox MensajeError = new MessageBox();
                MensajeError.setClosable(false);
                MensajeError.setModal(true);
                MensajeError.alert("ERROR GRAVE","Ha sucedido un error  grave de comunicacion a la Base de Datos, contacte a su administrador. El sistema permanecera bloqueado",null);
            }
        }

        class EventoBotonGrabarEstatus extends SelectionListener<ButtonEvent>{
            @Override
            public void componentSelected(ButtonEvent ce){                 
                RPCMovimientosFolios.EstadoDiagnostico(MDFolio.getFolio(), new RetornoEstadoDiagnostico());                
            }
        }
        private class RetornoEstadoDiagnostico implements AsyncCallback<ModeloEstadoDiagnostico>{
            public void onSuccess(ModeloEstadoDiagnostico result){
                ModeloEstadoDiagnostico MEstadoDiagnostico = (ModeloEstadoDiagnostico)result;
                String anotacion = Anotacion.getValue();  
                List<BaseModelUsuarioSoporte> listado = UIUsuarioSoporte.getCBNombre().getSelection();
                BaseModelUsuarioSoporte UsuarioAsignado;

                BMEstatus = UIEstatus.getCBNombre().getValue();
                BMTipoIncidencia = UITipoIncidenciaFinal.getCBNombre().getValue();

                int IdEstatus = BMEstatus.getId();
                int idUsuarioNuevo = 0;
                
                if(anotacion != null && BMEstatus != null && MDFolio.getActivo() == true){
                    if(BMEstatus.getDiagnostinoInicial() == true){
                        if(MEstadoDiagnostico.getDiagnostinoInicial() == false){
                            GrabarEstatus(MDFolio.getFolio(),IdEstatus,
                                            BMTipoIncidencia.getId(),MDFolio.getActivo(),
                                            IdUsuario,anotacion,idUsuarioNuevo);
                        }
                        else{
                            MessageBox.alert("ERROR","Ya existe Diagnostico Inicial",null);
                        }
                    }
                    else{
                        if(BMEstatus.getDiagnostinoFinal() == true){
                            if(MEstadoDiagnostico.getDiagnostinoInicial() == true && MEstadoDiagnostico.getDiagnostinoFinal() == false){
                                GrabarEstatus(MDFolio.getFolio(),IdEstatus,
                                                BMTipoIncidencia.getId(),MDFolio.getActivo(),
                                                IdUsuario,anotacion,idUsuarioNuevo);
                            }
                            else{
                                MessageBox.alert("ERROR","Falta Diagnostico Inicial o ya existe Diagnostico Final",null);
                            }
                        }
                        else{
                            if(BMEstatus.getCerrado() == true){
                                if(MEstadoDiagnostico.getDiagnostinoInicial() == true && MEstadoDiagnostico.getDiagnostinoFinal() == true){
                                    MDFolio.setActivo(false);
                                    GrabarEstatus(MDFolio.getFolio(),IdEstatus,
                                                    BMTipoIncidencia.getId(),MDFolio.getActivo(),
                                                    IdUsuario,anotacion,idUsuarioNuevo);
                                }
                                else{
                                    MessageBox.alert("ERROR","Falta Diagnostico Inicial/Final",null);
                                    MDFolio.setActivo(true);
                                }
                            }
                            else{
                                if(BMEstatus.getReasignar() == true){
                                    if(listado.size() > 0){
                                        UsuarioAsignado = listado.get(0);
                                        idUsuarioNuevo = listado.get(0).getId();
                                        anotacion = anotacion+". Nuevo Usuario de Soporte Asignado:"+listado.get(0).getNombrePropio();
                                        GrabarEstatus(MDFolio.getFolio(),IdEstatus,
                                                        BMTipoIncidencia.getId(),MDFolio.getActivo(),
                                                        IdUsuario,anotacion,idUsuarioNuevo);
                                    }
                                    else{
                                        MessageBox.alert("ERROR","Falta usuario reasignado",null);
                                    }
                                }
                                else{
                                    GrabarEstatus(MDFolio.getFolio(),IdEstatus,
                                                    BMTipoIncidencia.getId(),MDFolio.getActivo(),
                                                    IdUsuario,anotacion,idUsuarioNuevo);
                                }
                            }
                        }
                    }
                }
                else{
                    MessageBox.alert("ERROR","Falta estatus, anotacion, tipo de incidencia y/o el Folio ya se encuentra cerrado",null);
                }
            }
            public void onFailure(Throwable caught){
                MessageBox MensajeError = new MessageBox();
                MensajeError.setClosable(false);
                MensajeError.setModal(true);
                MensajeError.alert("ERROR GRAVE","Ha sucedido un error  grave de comunicacion a la Base de Datos, contacte a su administrador. El sistema permanecera bloqueado",null);
            }
        }

        private void GrabarEstatus(int Folio,int IdEstatus,int IdIncidenciaFinal,boolean EstadoActivo,int IdUsuario,String anotacion,int IdUsuarioNuevo){
            GridEstatus.mask("Espere un momento.. Guardando Estatus");

            RPCMovimientosFolios.AgregarEstatus(MDFolio.getFolio(),
                                                IdEstatus,
                                                IdIncidenciaFinal,
                                                MDFolio.getActivo(),
                                                IdUsuario,
                                                anotacion,
                                                IdUsuarioNuevo,new RetornoGrabarEstatus());
            Anotacion.clear();
            UIUsuarioSoporte.getCBNombre().clearSelections();
            UIEstatus.getCBNombre().clearSelections();

            MDFolio.setIdTipoIncidenciaFinal(IdIncidenciaFinal);
        }
        private class RetornoGrabarEstatus  implements AsyncCallback<String[][]>{
            public void onSuccess(String[][] result){
                String[][] estatus = (String[][])result;
                MDFolio.setDatosEstatus(estatus);                
                
                VisualizarGridEstatus();
                GridEstatus.unmask();
            }
            public void onFailure(Throwable caught){
                MessageBox MensajeError = new MessageBox();
                MensajeError.setClosable(false);
                MensajeError.setModal(true);
                MensajeError.alert("ERROR GRAVE","Ha sucedido un error  grave de comunicacion a la Base de Datos, contacte a su administrador. El sistema permanecera bloqueado",null);
            }
        }
        class RETORNO_LISTADO_ARCHIVOS implements AsyncCallback<DataModelGridArchivos[]> {
            public void onSuccess(DataModelGridArchivos[] result) {
                ListStoreGridArchivos = new ListStore<DataModelGridArchivos>();
                if(result.length > 0){
                    int pos = 0;

                    while(pos < result.length){
                        ListStoreGridArchivos.add(result[pos]);                
                        pos++;
                    }
                    gridArchivo.reconfigure(ListStoreGridArchivos, ColumnModelGridArchivo);                    
                }
                gridArchivo.unmask();
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
        class RETORNO_IMPRIMIR_ARCHIVO implements AsyncCallback<Void> {
            public void onSuccess(Void result) {     
                /*Window window = new Window();
                window.setOnEsc(true);
                window.setModal(true);
                window.setSize(1,1);
                window.setMaximizable(true);        
                window.show();
                //window.setUrl(GWT.getModuleBaseURL()+"ServletImprimir");
                window.setUrl(GWT.getModuleBaseURL()+"ServletImprimir");*/
                
                Visor.show("ServletImprimir");
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }
    public FormPanel getPanelCapturaDatosFolio(){
        return PanelCapturaDatosFolio.getPanelCapturaDatosFolio();
    }
    public FormPanel getPanelGridFolios(){
        return PanelGridFolios.getPanelGridFolios();
    }
    public void setUsuario(int IdUsuario){
        PanelCapturaDatosFolio.setUsuario(IdUsuario);
    }
    public void setUsuario(ModeloDatosUsuario Usuario){
        this.Usuario = Usuario;
        PanelGridFolios.actualizar();
        PanelCapturaDatosFolio.setUsuario(Usuario.getId());
    }
    
    
}
