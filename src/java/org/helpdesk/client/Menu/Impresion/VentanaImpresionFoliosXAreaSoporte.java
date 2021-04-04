/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Impresion;

import org.helpdesk.client.Menu.Catalogos.*;
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
import org.helpdesk.client.Buscar.WindowGridFiltrarAreaSoporte;
import org.helpdesk.client.InterfazUsuario.UIAreaSoporte;
import org.helpdesk.client.InterfazUsuario.Visor;
import org.helpdesk.client.ModeloDatos.BaseModelAreaSoporte;
import org.helpdesk.client.ServiciosRPC.RPCAreaSoporteAsync;
import org.helpdesk.client.ServiciosRPC.RPCImpresionAsync;

/**
 *
 * @author Administrador
 */
public class VentanaImpresionFoliosXAreaSoporte{
    UIAreaSoporte UIAreaSoporte;
    private Window window = new Window();

    private Button Generar = new Button();

    private WindowGridFiltrarAreaSoporte WindowGridBuscar;
    private EVENTO_BOTON_GRID EventoBotonAceptarCancelar = new EVENTO_BOTON_GRID();

    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    private RPCAreaSoporteAsync RPCAreaSoporte;
    private BaseModelAreaSoporte BMAreaSoporte = new BaseModelAreaSoporte();
    RPCImpresionAsync RPCImpresion;
    public VentanaImpresionFoliosXAreaSoporte(RPCImpresionAsync RPCImpresion,RPCAreaSoporteAsync RPCAreaSoporte){
        this.RPCImpresion = RPCImpresion;
        this.RPCAreaSoporte = RPCAreaSoporte;
        WindowGridBuscar = new WindowGridFiltrarAreaSoporte(RPCAreaSoporte);
        WindowGridBuscar.setEventoButton(EventoBotonAceptarCancelar);
        UIAreaSoporte = new UIAreaSoporte(90,RPCAreaSoporte);
        UIAreaSoporte.setGridBuscar(WindowGridBuscar);

        window.setHeading("Impresion de Folios x Area de Soporte");
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setBlinkModal(true);
        window.setClosable(true);
        window.setResizable(false);
        window.setModal(true);
        window.setOnEsc(true);        
        window.setWidth(450);
        window.setButtonAlign(HorizontalAlignment.CENTER);

        Generar.setText("GENERAR");
        Generar.setIconAlign(IconAlign.RIGHT);
        Generar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.add(UIAreaSoporte.getLCNombreBuscar());

        window.add(Form);//, new FormData("100%"));
        window.addButton(Generar);
    }
    public void show(){        
        window.show();
        window.setFocusWidget(UIAreaSoporte.getTFNombre());
        window.center();
    }

    private class EVENTO_BOTON extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            RPCImpresion.RPTFoliosXAreaSoporte(BMAreaSoporte.getId(),new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR());
        }
    }

    private class EVENTO_BOTON_GRID extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){
            WindowGridBuscar.hide();
            if(ce.getButton().getText().equals("Aceptar")){
                BMAreaSoporte = WindowGridBuscar.getBMSeleccionado();
                UIAreaSoporte.setBaseModel(BMAreaSoporte);
            }
        }
    }

    private class RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR implements AsyncCallback<Void>{
            public void onSuccess(Void result) {
                Visor.show("ServletRPTFoliosXAreaSoporte");
                window.hide();
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
}
