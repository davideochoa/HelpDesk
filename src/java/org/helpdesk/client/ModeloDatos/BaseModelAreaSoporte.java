/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author Administrador
 */
public class BaseModelAreaSoporte extends BaseModel{
    public BaseModelAreaSoporte(int Id,String Nombre) {
        set("Id",Id);
        set("Nombre",Nombre.toUpperCase());
    }
    public BaseModelAreaSoporte(String datos[]){
        set("Id",Integer.parseInt(datos[0]));
        set("Nombre",datos[1].toUpperCase());
        //GWT.log("Nombre:"+datos[1]);
    }
    public BaseModelAreaSoporte(){}
    public int getId() {
        Integer ValorEntero = (Integer)this.get("Id");
        if(ValorEntero!= null)
            return ValorEntero.intValue();
        else
            return 0;
    }
    public void setId(int Id) {
        set("Id",Id);
    }
    public String getNombre() {
        return (String)get("Nombre");
    }
    public void setNombre(String Nombre) {
        set("Nombre",verficarNull(Nombre).toUpperCase());
    }
    public ModeloPrioridad getModeloDatos(){
        ModeloPrioridad ModeloDatos = new ModeloPrioridad();

        ModeloDatos.setId(this.getId());
        ModeloDatos.setNombre(this.getNombre());

        return ModeloDatos;
    }
    private String verficarNull(String valor){        
        if(valor == null) {
            valor = "";
        }
        
        return valor;
    }
}
