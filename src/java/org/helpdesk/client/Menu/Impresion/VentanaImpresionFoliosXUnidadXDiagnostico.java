/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Impresion;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.Buscar.WindowGridFiltrarUnidades;
import org.helpdesk.client.InterfazUsuario.UIUnidad;
import org.helpdesk.client.InterfazUsuario.Visor;
import org.helpdesk.client.ModeloDatos.ModeloUnidad;
import org.helpdesk.client.ServiciosRPC.RPCImpresionAsync;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;

/**
 *
 * @author Administrador
 */
public class VentanaImpresionFoliosXUnidadXDiagnostico{
    UIUnidad UIUnidad;
    private Window window = new Window();

    private Button Nuevo = new Button();

    private WindowGridFiltrarUnidades WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();
    
    private ModeloUnidad modeloUnidad = new ModeloUnidad();
    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();

    private String UltimoIdBoton = new String();
    private RPCUnidadAsync RPCUnidad;
    RPCImpresionAsync RPCImpresion;
    public VentanaImpresionFoliosXUnidadXDiagnostico(RPCImpresionAsync RPCImpresion,RPCUnidadAsync RPCUnidad){
        this.RPCUnidad = RPCUnidad;
        this.RPCImpresion = RPCImpresion;

        WindowGridBuscar = new WindowGridFiltrarUnidades(RPCUnidad);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIUnidad = new UIUnidad(90,RPCUnidad);
        UIUnidad.setGridBuscar(WindowGridBuscar);

        window.setHeading("Reporte de Diagnosticos X Folios X Unidad");
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setBlinkModal(true);
        window.setClosable(true);
        window.setResizable(false);
        window.setModal(true);
        window.setOnEsc(true);        
        window.setWidth(450);
        window.setButtonAlign(HorizontalAlignment.CENTER);

        Nuevo.setText("GENERAR");
        Nuevo.setIconAlign(IconAlign.RIGHT);
        Nuevo.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UIUnidad.getLCNombreBuscar());

        window.add(Form);//, new FormData("100%"));
        window.addButton(Nuevo);
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UIUnidad.getTFNombre());
        window.center();
        UIUnidad.getCBNombre().clearSelections();
    }
    private class EVENTO_BOTON extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            RPCImpresion.RPTFoliosXUnidadXDiagnosticos(modeloUnidad.getId(),new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR());
        }
    }
    private class RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR implements AsyncCallback<Void>{
        public void onSuccess(Void result) {
            Visor.show("ServletFoliosXUnidadXDiagnosticos");
            window.hide();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                modeloUnidad = WindowGridBuscar.getModeloUnidadSeleccionado();
                UIUnidad.setModeloUnidad(modeloUnidad);
            }
        }
    }
}
