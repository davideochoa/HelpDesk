/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 *
 * @author Administrador
 */
public class BaseModelPrioridad extends BaseModel{
    public BaseModelPrioridad(int Id,String Nombre) {
        setId(Id);
        setNombre(Nombre);
    }
    public BaseModelPrioridad(){}
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
