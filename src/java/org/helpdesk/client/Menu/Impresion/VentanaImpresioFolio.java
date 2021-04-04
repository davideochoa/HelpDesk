/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Impresion;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.helpdesk.client.InterfazUsuario.Visor;
import org.helpdesk.client.ServiciosRPC.RPCImpresionAsync;

/**
 *
 * @author Administrador
 */
public class VentanaImpresioFolio{
    private Window window = new Window();

    private Button BotonGenerar = new Button();
    NumberField NF_Folio = new NumberField();
    private EVENTO_BOTON eventoBoton = new EVENTO_BOTON();
    private RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR RetornoEventoAgregarEditarElimianr = new RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR();
    
    private RPCImpresionAsync RPCImpresion;
    public VentanaImpresioFolio(RPCImpresionAsync RPCImpresion){
        this.RPCImpresion = RPCImpresion;
        
        NF_Folio.setAllowDecimals(false);
        NF_Folio.setAllowNegative(false);
        NF_Folio.setMinValue(0);
        NF_Folio.setFieldLabel("Folio");

        window.setHeading("Impresion Folios");
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setBlinkModal(true);
        window.setClosable(true);
        window.setResizable(false);
        window.setModal(true);
        window.setOnEsc(true);
        window.setButtonAlign(HorizontalAlignment.CENTER);

        BotonGenerar.setText("GENERAR");
        BotonGenerar.setId("GENERAR");
        BotonGenerar.addSelectionListener(eventoBoton);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        Form.setLabelWidth(30);

        Form.add(NF_Folio,new FormData("135%"));

        window.add(Form);//,new FormData("100%"));
        window.setWidth(150);
        window.addButton(BotonGenerar);
    }
    public void show(){        
        window.show();
        window.center();

        NF_Folio.clear();
    }
    private class EVENTO_BOTON extends SelectionListener<ButtonEvent>{
        @Override
        public void componentSelected(ButtonEvent ce){            
            RPCImpresion.RPTIncidenciaXFolio(NF_Folio.getValue().intValue(), RetornoEventoAgregarEditarElimianr);
        }
    }
    private class RETORNO_EVENTO_AGREGAR_EDITAR_ELIMIANR implements AsyncCallback<Void>{
        public void onSuccess(Void result) {
            Visor.show("ServletRPTIncidencia");
            window.hide();
        }
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
