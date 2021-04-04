/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helpdesk.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import org.helpdesk.client.Menu.Catalogos.PanelMenu;
import org.helpdesk.client.ModeloDatos.ModeloDatosUsuario;
import org.helpdesk.client.ModeloDatosNuevo.BMMDConfiguracion;
import org.helpdesk.client.ServiciosRPC.RPCArea;
import org.helpdesk.client.ServiciosRPC.RPCAreaAsync;
import org.helpdesk.client.ServiciosRPC.RPCAreaSoporte;
import org.helpdesk.client.ServiciosRPC.RPCAreaSoporteAsync;
import org.helpdesk.client.ServiciosRPC.RPCBien;
import org.helpdesk.client.ServiciosRPC.RPCBienAsync;
import org.helpdesk.client.ServiciosRPC.RPCEstatus;
import org.helpdesk.client.ServiciosRPC.RPCEstatusAsync;
import org.helpdesk.client.ServiciosRPC.RPCImpresion;
import org.helpdesk.client.ServiciosRPC.RPCImpresionAsync;
import org.helpdesk.client.ServiciosRPC.RPCMarca;
import org.helpdesk.client.ServiciosRPC.RPCMarcaAsync;
import org.helpdesk.client.ServiciosRPC.RPCModelo;
import org.helpdesk.client.ServiciosRPC.RPCModeloAsync;
import org.helpdesk.client.ServiciosRPC.RPCMovimientosFolios;
import org.helpdesk.client.ServiciosRPC.RPCMovimientosFoliosAsync;
import org.helpdesk.client.ServiciosRPC.RPCPrioridad;
import org.helpdesk.client.ServiciosRPC.RPCPrioridadAsync;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidencia;
import org.helpdesk.client.ServiciosRPC.RPCTipoIncidenciaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUnidad;
import org.helpdesk.client.ServiciosRPC.RPCUnidadAsync;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReporta;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioReportaAsync;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporte;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporteAsync;

/**
 * Main entry point.
 *
 * @author Administrador
 */
public class MainEntryPoint implements EntryPoint {
    RPCUnidadAsync RPCUnidad;
    RPCAreaAsync RPCUArea;
    RPCTipoIncidenciaAsync RPCUTipoIncidencia;
    RPCBienAsync RPCBien;
    RPCPrioridadAsync RPCPrioridad;
    RPCUsuarioReportaAsync RPCUsuarioReporta;
    RPCMarcaAsync RPCMarca;
    RPCModeloAsync RPCModelo;
    RPCEstatusAsync RPCEstatus;
    RPCAreaSoporteAsync RPCAreaSoporte;
    RPCUsuarioSoporteAsync RPCUsuarioSoporte;
    RPCMovimientosFoliosAsync RPCMovimientosFolios;
    RPCImpresionAsync RPCImpresion;

    RootPanel RootPanelPrincipal;

    LayoutContainer ContenedorPrincipal = new LayoutContainer();

    Login login;
    PanelGrid_Captura PanelGrid_Captura;
    PanelMenu PanelMenu;// = new PanelMenu();
    //BMMDConfiguracion configuracion = new BMMDConfiguracion();
    BorderLayoutData LayoutEditarFolio = new BorderLayoutData(LayoutRegion.CENTER);
    BorderLayoutData LayoutFoliosActivos = new BorderLayoutData(LayoutRegion.NORTH,200);
    BorderLayoutData LayoutMenus = new BorderLayoutData(LayoutRegion.WEST,160);
    public MainEntryPoint() {}
    public void onModuleLoad(){        
        IniciaServicios();
        
        login = new Login(RPCUsuarioSoporte);
        login.show();
        

        RootPanelPrincipal = RootPanel.get();
        ContenedorPrincipal.setBorders(false);
        final BorderLayout layout = new BorderLayout();
        ContenedorPrincipal.setLayout(layout);

        
        LayoutFoliosActivos.setCollapsible(false);
        LayoutFoliosActivos.setMargins(new Margins(0,0,5,0));

        
        LayoutMenus.setCollapsible(true);
        LayoutMenus.setMargins(new Margins(0,5,0,0));
        
        LayoutEditarFolio.setSplit(true);
        LayoutEditarFolio.setMargins(new Margins(0));
    }
    private void IniciaServicios(){
        RPCUnidad = (RPCUnidadAsync) GWT.create(RPCUnidad.class);
        RPCUArea = (RPCAreaAsync) GWT.create(RPCArea.class);
        RPCUTipoIncidencia = (RPCTipoIncidenciaAsync) GWT.create(RPCTipoIncidencia.class);
        RPCBien = (RPCBienAsync) GWT.create(RPCBien.class);
        RPCPrioridad = (RPCPrioridadAsync) GWT.create(RPCPrioridad.class);
        RPCUsuarioReporta = (RPCUsuarioReportaAsync) GWT.create(RPCUsuarioReporta.class);
        RPCMarca = (RPCMarcaAsync) GWT.create(RPCMarca.class);
        RPCModelo = (RPCModeloAsync) GWT.create(RPCModelo.class);
        RPCEstatus = (RPCEstatusAsync) GWT.create(RPCEstatus.class);
        RPCAreaSoporte = (RPCAreaSoporteAsync) GWT.create(RPCAreaSoporte.class);
        RPCUsuarioSoporte = (RPCUsuarioSoporteAsync) GWT.create(RPCUsuarioSoporte.class);
        RPCMovimientosFolios = (RPCMovimientosFoliosAsync) GWT.create(RPCMovimientosFolios.class);
        RPCImpresion = (RPCImpresionAsync) GWT.create(RPCImpresion.class);
        
        RPCMovimientosFolios.configuracion(new RETORNO_BUSCAR());
    }    
    
    private class RETORNO_BUSCAR implements AsyncCallback<BMMDConfiguracion>{
        @Override
        public void onSuccess(BMMDConfiguracion result) {
            PanelMenu = new PanelMenu(RPCUnidad,
                                    RPCUArea,
                                    RPCUTipoIncidencia,
                                    RPCBien,
                                    RPCPrioridad,
                                    RPCEstatus,
                                    RPCAreaSoporte,
                                    RPCUsuarioSoporte,
                                    RPCImpresion,
                                    result.getetiquetaTipoBien());
            
             PanelGrid_Captura = new PanelGrid_Captura(RPCUnidad,
                                                    RPCUArea,
                                                    RPCUTipoIncidencia,
                                                    RPCBien,
                                                    RPCPrioridad,
                                                    RPCUsuarioReporta,
                                                    RPCMarca,
                                                    RPCModelo,
                                                    RPCEstatus,
                                                    RPCAreaSoporte,
                                                    RPCMovimientosFolios,
                                                    RPCUsuarioSoporte,
                                                    RPCImpresion,
                                                    result.getdiasMinimoAmarillo(),
                                                    result.getdiasMaximoAmarillo(),
                                                    result.getetiquetaTipoBien());
            
            ContenedorPrincipal.add(PanelMenu.getPanelMenu(),LayoutMenus);   
            ContenedorPrincipal.add(PanelGrid_Captura.getPanelCapturaDatosFolio(),LayoutEditarFolio);
            ContenedorPrincipal.add(PanelGrid_Captura.getPanelGridFolios(),LayoutFoliosActivos);
            RootPanelPrincipal.add(ContenedorPrincipal);
        }
        @Override
        public void onFailure(Throwable caught) {
            System.out.println("RETORNO_ACTUALIZAR_LISTSTORE_AREA:onFailure");
            System.out.println(caught.getMessage());
            System.out.println(caught.getLocalizedMessage());
            System.out.println(caught.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }  
    
    class Login{
        private Window  Ventana = new Window ();
        private TextField<String> TFNombreUsuario = new TextField<String>();
        private TextField<String> TFPassword = new TextField<String>();

        private Window  VentanaNP = new Window ();
        private TextField<String> TFPassword1 = new TextField<String>();
        private TextField<String> TFPassword2 = new TextField<String>();
        private ModeloDatosUsuario Usuario = new ModeloDatosUsuario();
        RPCUsuarioSoporteAsync RPCUsuarioSoporte;
        public Login(RPCUsuarioSoporteAsync RPCUsuarioSoporte) {
            this.RPCUsuarioSoporte = RPCUsuarioSoporte;
            FormPanel FP = new FormPanel();
            FP.setBodyBorder(false);
            FP.setBorders(false);
            FP.setHeaderVisible(false);
            FP.setLabelWidth(110);

            TFNombreUsuario.setAllowBlank(true);
            TFNombreUsuario.setFieldLabel("Nombre de Usuario");
            TFNombreUsuario.setId("Nombre");
            TFNombreUsuario.addKeyListener(new OyenteGeneralTextField());

            TFPassword.setAllowBlank(true);
            TFPassword.setFieldLabel("Password");
            TFPassword.setId("Password");
            TFPassword.setPassword(true);
            TFPassword.addKeyListener(new OyenteGeneralTextField());

            FP.add(TFNombreUsuario,new FormData("100%"));
            FP.add(TFPassword,new FormData("100%"));

            Ventana.setHeaderVisible(true);
            Ventana.setClosable(false);
            Ventana.setOnEsc(false);
            Ventana.setModal(true);
            Ventana.setWidth(255);
            Ventana.setHeading("LOGIN");

            Ventana.add(FP);

            FormPanel FNP = new FormPanel();
            FNP.setBodyBorder(false);
            FNP.setBorders(false);
            FNP.setHeaderVisible(false);
            FNP.setLabelWidth(110);

            TFPassword1.setAllowBlank(true);
            TFPassword1.setFieldLabel("Nuevo Password");
            TFPassword1.setId("Password1");
            TFPassword1.setPassword(true);
            TFPassword1.addKeyListener(new OyenteTextFieldPassword());

            TFPassword2.setAllowBlank(true);
            TFPassword2.setFieldLabel("Repetir Password");
            TFPassword2.setId("Password2");
            TFPassword2.setPassword(true);
            TFPassword2.addKeyListener(new OyenteTextFieldPassword());

            FNP.add(TFPassword1,new FormData("100%"));
            FNP.add(TFPassword2,new FormData("100%"));

            VentanaNP.setHeaderVisible(true);
            VentanaNP.setClosable(false);
            VentanaNP.setOnEsc(false);
            VentanaNP.setModal(true);
            VentanaNP.setWidth(255);
            VentanaNP.setHeading("Nuevo Password");

            VentanaNP.add(FNP);
        }
        public void show(){
            Ventana.setVisible(true);
            Ventana.setFocusWidget(TFNombreUsuario);
        }
        class OyenteGeneralTextField extends KeyListener{
            @Override
            public void componentKeyDown(ComponentEvent event){
                if(event.getKeyCode() == KeyCodes.KEY_ENTER){
                    String NombreUsuario = TFNombreUsuario.getValue();
                    String Password = TFPassword.getValue();

                    //NombreUsuario = "dochoa"; Password = "dochoa";

                    TextField<String> TF = event.getComponent();

                    if(TF.getId().equals("Nombre"))
                        TFPassword.focus();
                    else
                        TFNombreUsuario.focus();

                    if(NombreUsuario == null)
                        NombreUsuario = "";

                    if(Password == null)
                        Password = "";

                    if(NombreUsuario.length() > 0 && Password.length() > 0){
                        Usuario.setNombreUsuario(NombreUsuario);
                        Usuario.setPasword(Password);
                        
                        Ventana.mask("Espere un momento... Verificando");
                        
                        RPCUsuarioSoporte.verificaLogin(Usuario, new RETORNO_LOGIN());
                    }
                    else
                        Ventana.setFocusWidget(TFNombreUsuario);
                }
            }
        }
        class RETORNO_LOGIN implements AsyncCallback<ModeloDatosUsuario> {
            public void onSuccess(ModeloDatosUsuario result) {                
                Usuario = (ModeloDatosUsuario)result;
                Ventana.unmask();
                if(Usuario.getId() == 0){
                    MessageBox.info("LOGIN","Nombre de Usuario o Contraseña erroneo", null);
                    TFNombreUsuario.clear();
                    TFPassword.clear();
                }
                else{
                    if(Usuario.isReseteadoPassword() == true){
                        VentanaNP.setVisible(true);
                        VentanaNP.setFocusWidget(TFPassword1);
                        
                    }
                    else{
                        Ventana.setVisible(false);
                        //Cookies.setCookie("IdUsuario",Integer.toString(Usuario.getId()));
                        //PanelGrid_Captura.setUsuario(Usuario.getId());
                        PanelGrid_Captura.setUsuario(Usuario);
                        PanelMenu.setAdministrador(Usuario.isAdministrador());                        
                    }
                }
            }
            public void onFailure(Throwable caught) {
                ContenedorPrincipal.mask("EROR AL CONECTAR LA BASE DE DATOS");                
            }
        }
        class OyenteTextFieldPassword extends KeyListener{
            @Override
            public void componentKeyDown(ComponentEvent event){
                if(event.getKeyCode() == KeyCodes.KEY_ENTER){
                    String Password1 = TFPassword1.getValue();
                    String Password2 = TFPassword2.getValue();

                    TextField<String> TF = event.getComponent();

                    if(TF.getId().equals("Password1"))
                        TFPassword2.focus();
                    else
                        TFPassword1.focus();

                    if(Password1 == null)
                        Password1 = "";

                    if(Password2 == null)
                        Password2 = "";

                    if(Password1.length() > 0 && Password2.length() > 0){
                        if(Password1.equals(Password2)){
                            Usuario.setPasword(Password1);
                            Usuario.setReseteadoPassword(false);

                            VentanaNP.mask("Espere un momento... Actualizando");

                            RPCUsuarioSoporte.EditarPassword(Usuario, new RETORNO_NUEVO_PASSWORD());
                        }
                        else{
                            MessageBox.info("LOGIN","Contraseñas no coinciden", null);
                            VentanaNP.setFocusWidget(TFPassword1);
                        }
                    }
                }
            }
        }
        class RETORNO_NUEVO_PASSWORD implements AsyncCallback<Void> {
            public void onSuccess(Void result) {
                VentanaNP.unmask();
                VentanaNP.hide();

                TFNombreUsuario.clear();
                TFPassword.clear();
                Ventana.setFocusWidget(TFNombreUsuario);
            }
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }
    
}
