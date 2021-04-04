/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Catalogos;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanelSelectionModel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.helpdesk.client.Menu.Impresion.VentanaImpresioFolio;
import org.helpdesk.client.Menu.Impresion.VentanaImpresionFoliosXAreaSoporte;
import org.helpdesk.client.Menu.Impresion.VentanaImpresionFoliosXUnidad;
import org.helpdesk.client.Menu.Impresion.VentanaImpresionFoliosXUnidadXDiagnostico;
import org.helpdesk.client.Menu.Impresion.VentanaImpresionFoliosXUnidadXDiagnosticosFinales;
import org.helpdesk.client.Menu.Sistema.Buscar;
import org.helpdesk.client.Menu.Sistema.VentanaSelectorTema;
import org.helpdesk.client.ServiciosRPC.RPCAreaAsync;
import org.helpdesk.client.ServiciosRPC.RPCAreaSoporteAsync;
import org.helpdesk.client.ServiciosRPC.RPCBienAsync;
import org.helpdesk.client.ServiciosRPC.RPCEstatusAsync;
import org.helpdesk.client.ServiciosRPC.RPCImpresionAsync;
import org.helpdesk.client.ServiciosRPC.RPCPrioridadAsync;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporteAsync;

/**
 *
 * @author Administrador
 */
public class PanelMenu {
    private ContentPanel Contenedor = new ContentPanel();
    private EVENTO_CLICK_MENU EventoClickMenu = new EVENTO_CLICK_MENU();
    private VentanaCatalogoUnidad VentanaCatalogoUnidad;// = new VentanaCatalogoUnidad();
    private VentanaCatalogoArea VentanaCatalogoArea;
    private VentanaCatalogoTipoIncidencia VentanaCatalogoTipoIncidencia;
    private VentanaCatalogoBien VentanaCatalogoBien;
    private VentanaCatalogoPrioridad VentanaCatalogoPrioridad;
    private VentanaCatalogoAreaSoporte VentanaCatalogoAreaSoporte;
    private VentanaSelectorTema VentanaSelectorTema = new VentanaSelectorTema();
    private VentanaCatalogoUsuarioSoporte VentanaCatalogoUsuarioSoporte;
    private boolean Administrador = false;

    VentanaImpresioFolio VImpresionFolio;
    VentanaImpresionFoliosXUnidad VImpresionFolioXUnidad;
    VentanaImpresionFoliosXUnidadXDiagnostico VImpresionFoliosXUnidadXDiagnostico;
    VentanaImpresionFoliosXUnidadXDiagnosticosFinales VImpresionFoliosXUnidadXDiagnosticoFinales;
    VentanaImpresionFoliosXAreaSoporte VImpresionFoliosXAreaSoporte;
    Buscar Buscar;
    public PanelMenu(RPCUnidadAsync RPCUnidad,
                        RPCAreaAsync RPCUArea,
                        RPCTipoIncidenciaAsync RPCUTipoIncidencia,
                        RPCBienAsync RPCBien,
                        RPCPrioridadAsync RPCPrioridad,
                        RPCEstatusAsync RPCEstatus,
                        RPCAreaSoporteAsync RPCAreaSoporte,
                        RPCUsuarioSoporteAsync RPCUsuarioSoporte,
                        RPCImpresionAsync RPCImpresion,
                        String etiquetaTipoIncidencia) {
        VentanaCatalogoUnidad = new VentanaCatalogoUnidad(RPCUnidad);
        VentanaCatalogoArea = new VentanaCatalogoArea(RPCUArea,RPCUnidad);
        VentanaCatalogoTipoIncidencia = new VentanaCatalogoTipoIncidencia(RPCUTipoIncidencia);
        VentanaCatalogoBien = new VentanaCatalogoBien(RPCBien,RPCUTipoIncidencia);
        VentanaCatalogoPrioridad = new VentanaCatalogoPrioridad(RPCPrioridad);
        VentanaCatalogoAreaSoporte = new VentanaCatalogoAreaSoporte(RPCAreaSoporte);
        VentanaCatalogoUsuarioSoporte = new VentanaCatalogoUsuarioSoporte(RPCUsuarioSoporte);
        Buscar = new Buscar(etiquetaTipoIncidencia);

        VImpresionFolio = new VentanaImpresioFolio(RPCImpresion);
        VImpresionFolioXUnidad = new VentanaImpresionFoliosXUnidad(RPCImpresion,RPCUnidad);
        VImpresionFoliosXUnidadXDiagnostico = new VentanaImpresionFoliosXUnidadXDiagnostico(RPCImpresion,RPCUnidad);
        VImpresionFoliosXUnidadXDiagnosticoFinales = new VentanaImpresionFoliosXUnidadXDiagnosticosFinales(RPCImpresion,RPCUnidad);
        VImpresionFoliosXAreaSoporte = new VentanaImpresionFoliosXAreaSoporte(RPCImpresion,RPCAreaSoporte);
        
        Contenedor.setHeading("MENU");
        Contenedor.setTitleCollapse(false);
        Contenedor.setHeaderVisible(true);
        TreeStore<ModelData> store = new TreeStore<ModelData>();
        TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
        tree.setDisplayProperty("name");
        tree.setIconProvider(new ModelIconProvider<ModelData>() {
            public AbstractImagePrototype getIcon(ModelData model){
                if (model.get("icon") != null) {
                    return IconHelper.createStyle((String) model.get("icon"));
                }
                else{
                    return null;
                }
            }
        });

        ModelData RamaCatalogos = newItem("Catalogos","catalogo");
        store.add(RamaCatalogos, false);
        
        store.add(RamaCatalogos, newItem("Unidad","catalogo-unidad"), false);
        store.add(RamaCatalogos, newItem("Area","catalogo-area"), false);
        store.add(RamaCatalogos, newItem("Tipo Incidencia","catalogo-incidencia"), false);        
        store.add(RamaCatalogos, newItem("Tipo Bien","catalogo-tipo-objeto"), false);
        store.add(RamaCatalogos, newItem("Prioridad","catalogo-tipo-prioridad"), false);
        store.add(RamaCatalogos, newItem("Estatus","catalogo-tipo-status"), false);
        store.add(RamaCatalogos, newItem("Area de Soporte","catalogo-soporte"), false);
        store.add(RamaCatalogos, newItem("Usuario de Soporte","catalogo-usuarios"), false);

        ModelData RamaImpresion = newItem("Impresion","impresion");
        store.add(RamaImpresion,false);

        store.add(RamaImpresion, newItem("Incidencia por Folio",null), false);
        store.add(RamaImpresion, newItem("Folios por Unidad",null), false);
        store.add(RamaImpresion, newItem("Folios por Unidad y Diagnosticos",null), false);
        store.add(RamaImpresion, newItem("Folios por Unidad y Diagnosticos Finales",null), false);
        store.add(RamaImpresion, newItem("Folios por Area de Soporte",null), false);

        ModelData RamaSistema = newItem("Sistema","sistema");
        store.add(RamaSistema,false);
        
        store.add(RamaSistema, newItem("Buscar",null), false);
        
        //store.add(RamaSistema, newItem("Tema","catalogo-tema"), false);

        tree.expandAll();
        tree.setSelectionModel(EventoClickMenu);

        Contenedor.add(tree);
    }
    public ContentPanel getPanelMenu(){
        return Contenedor;
    }
    private class EVENTO_CLICK_MENU extends TreePanelSelectionModel<ModelData>{
        @Override
        protected void onMouseClick(TreePanelEvent e) {
            super.onMouseClick(e);
            String ItemSeleccionado = e.getItem().get("name");
            
            if(Administrador){                
                if(ItemSeleccionado.equals("Unidad"))                    
                    VentanaCatalogoUnidad.show();                

                if(ItemSeleccionado.equals("Area"))
                    VentanaCatalogoArea.show();

                if(ItemSeleccionado.equals("Tipo Incidencia"))
                    VentanaCatalogoTipoIncidencia.show();

                if(ItemSeleccionado.equals("Tipo Bien"))
                    VentanaCatalogoBien.show();

                if(ItemSeleccionado.equals("Prioridad"))
                    VentanaCatalogoPrioridad.show();

                if(ItemSeleccionado.equals("Area de Soporte"))
                    VentanaCatalogoAreaSoporte.show();

                if(ItemSeleccionado.equals("Usuario de Soporte"))
                    VentanaCatalogoUsuarioSoporte.show();
            }
            
            if(ItemSeleccionado.equals("Incidencia por Folio"))
                VImpresionFolio.show();
            if(ItemSeleccionado.equals("Folios por Unidad"))
                VImpresionFolioXUnidad.show();
            if(ItemSeleccionado.equals("Folios por Unidad y Diagnosticos"))
                VImpresionFoliosXUnidadXDiagnostico.show();
            if(ItemSeleccionado.equals("Folios por Unidad y Diagnosticos Finales"))
                VImpresionFoliosXUnidadXDiagnosticoFinales.show();
            if(ItemSeleccionado.equals("Folios por Area de Soporte"))
                VImpresionFoliosXAreaSoporte.show();
            
            if(ItemSeleccionado.equals("Buscar")){
                Buscar.show();
                Buscar.center();
            }
        }
    }
    private ModelData newItem(String text, String iconStyle) {
        ModelData m = new BaseModelData();
        m.set("name", text);
        m.set("icon", iconStyle);
        return m;
      }
    public void setAdministrador(boolean Administrador){
        this.Administrador = Administrador;
    }
}
