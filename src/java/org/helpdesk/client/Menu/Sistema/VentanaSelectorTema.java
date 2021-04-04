/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.Menu.Sistema;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.custom.ThemeSelector;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;

/**
 *
 * @author Administrador
 */
public class VentanaSelectorTema {
    private Window window = new Window();
    public VentanaSelectorTema(){
        window.setHeading("Selector de Tema");
        window.setMaximizable(false);
        window.setMinimizable(false);
        window.setBlinkModal(true);
        window.setClosable(true);
        window.setResizable(false);
        window.setModal(true);
        window.setOnEsc(true);
        //window.setWidth(450);

        FormPanel Form = new FormPanel();
        Form.setHeaderVisible(false);
        Form.setBodyBorder(false);
        Form.setBorders(false);
        Form.setFrame(false);
        
        ThemeSelector TS = new ThemeSelector();
        TS.setFieldLabel("Tema");
        Form.add(TS, new FormData("100%"));
        
        window.add(Form, new FormData("100%"));
    }
    public void show(){
        window.setVisible(true);
    }
    public void hide(){
        window.setVisible(false);
    }
}
