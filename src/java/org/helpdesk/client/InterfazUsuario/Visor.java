/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.InterfazUsuario;

import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author Administrador
 */
public class Visor{
    Window window = new Window();
    public Visor(WindowListener listener){
        window.addWindowListener(listener);
    }
    public static void show(String servlet){
        Window window = new Window();
        window.setOnEsc(true);
        window.setModal(true);
        window.setSize(950,750);
        window.setMaximizable(true);        
        window.show();
        window.setUrl(GWT.getModuleBaseURL() + servlet);
        //windowGWT.open(GWT.getModuleBaseURL() + "ServletImprimirValeEntrada","WINDOW","");
    }
    public void show2(String servlet){
        window.setOnEsc(true);
        window.setModal(true);
        window.setSize(950,750);
        window.setMaximizable(true);        
        window.show();
        window.setUrl(GWT.getModuleBaseURL() + servlet);
        //windowGWT.open(GWT.getModuleBaseURL() + "ServletImprimirValeEntrada","WINDOW","");
    }
}
