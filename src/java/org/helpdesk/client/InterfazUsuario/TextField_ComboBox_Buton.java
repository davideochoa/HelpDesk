/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.InterfazUsuario;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;

/**
 *
 * @author Administrador
 */
public class TextField_ComboBox_Buton extends LayoutContainer{
    public TextField_ComboBox_Buton(int AnchoEtiqueta,TextField TF,Button Boton){
        super();

        FormPanel  FormPanelNombre = new FormPanel ();
        FormPanelNombre.setPadding(0);
        FormPanelNombre.setBodyBorder(false);
        FormPanelNombre.setHeaderVisible(false);
        FormPanelNombre.setLabelWidth(AnchoEtiqueta);
        FormPanelNombre.add(TF, new FormData("100%"));

        HBoxLayout LayoutHB = new HBoxLayout();
        LayoutHB.setPadding(new Padding(0));
        LayoutHB.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

        HBoxLayoutData FlexFolio = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        FlexFolio.setFlex(1);

        setBorders(false);
        setLayout(LayoutHB);
        add(FormPanelNombre, FlexFolio);
        add(Boton);
    }
    public TextField_ComboBox_Buton(int AnchoEtiqueta,ComboBox CB,Button Boton){
        super();

        FormPanel  FormPanelCombobox = new FormPanel ();
        FormPanelCombobox.setPadding(0);
        FormPanelCombobox.setBodyBorder(false);
        FormPanelCombobox.setHeaderVisible(false);
        FormPanelCombobox.setLabelWidth(AnchoEtiqueta);
        FormPanelCombobox.add(CB, new FormData("100%"));

        HBoxLayout LayoutHBcb = new HBoxLayout();
        LayoutHBcb.setPadding(new Padding(0));
        LayoutHBcb.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

        HBoxLayoutData FlexFoliocb = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        FlexFoliocb.setFlex(1);

        setBorders(false);
        setLayout(LayoutHBcb);
        add(FormPanelCombobox, FlexFoliocb);
        add(Boton);
    }
}
