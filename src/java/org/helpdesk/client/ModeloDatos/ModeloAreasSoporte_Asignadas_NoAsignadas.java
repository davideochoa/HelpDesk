/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author Administrador
 */
public class ModeloAreasSoporte_Asignadas_NoAsignadas implements IsSerializable{
    private String[][] AreasSiAsignadas = new String[0][0];
    private String[][] AreasNoAsignadas = new String[0][0];
    
    public ModeloAreasSoporte_Asignadas_NoAsignadas(){
        AreasSiAsignadas = new String[0][0];
        AreasNoAsignadas = new String[0][0];
    }
    public void setValorAreaSiAsignadas(int posy,int posx,String Valor){
        AreasSiAsignadas[posy][posx] = Valor.toUpperCase();
        //GWT.log("AreasSiAsignadas[posy][posx]:"+AreasSiAsignadas[posy][posx]);
    }
    public void setValorAreaNoAsignadas(int posy,int posx,String Valor){
        AreasNoAsignadas[posy][posx] = Valor.toUpperCase();
         //GWT.log("AreasNoAsignadas[posy][posx]:"+AreasNoAsignadas[posy][posx]);
    }
    public String[][] getAreasNoAsignadas() {
        return AreasNoAsignadas;
    }
    public void setAreasNoAsignadas(String[][] AreasNoAsignadas) {
        this.AreasNoAsignadas = AreasNoAsignadas;
    }

    public String[][] getAreasSiAsignadas() {
        return AreasSiAsignadas;
    }
    public void setAreasSiAsignadas(String[][] AreasSiAsignadas) {
        this.AreasSiAsignadas = AreasSiAsignadas;
    }
}
