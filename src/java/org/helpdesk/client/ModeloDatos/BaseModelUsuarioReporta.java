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
public class BaseModelUsuarioReporta extends BaseModel{
    public BaseModelUsuarioReporta(String Nombre) {
        setNombre(Nombre);
    }
    public BaseModelUsuarioReporta(){}    
    public String getNombre() {
        return (String)get("Nombre");
    }
    public void setNombre(String Nombre) {
        set("Nombre",verficarNull(Nombre).toUpperCase());
    }
    public ModeloPrioridad getModeloDatos(){
        ModeloPrioridad ModeloDatos = new ModeloPrioridad();
        
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
