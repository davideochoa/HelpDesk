/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.InterfazUsuario;

import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;

/**
 *
 * @author Administrador
 */
public class ConfiguracionUI{
    private ToolTipConfig tool = new ToolTipConfig();
    public ConfiguracionUI(){
        tool.setTrackMouse(true);
    }
    public ToolTipConfig getToolTipConfig(){
        return tool;
    }
}
