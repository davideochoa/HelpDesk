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
public class BaseModelUsuario extends BaseModel{
    public BaseModelUsuario(int Id,int IdAreaSoporte,String NombrePropio,String Correo,String NombreUsuario,String Password,boolean EsAdministrador) {
        set("Id",Id);
        set("IdAreaSoporte",IdAreaSoporte);
        set("NombrePropio",NombrePropio.toUpperCase());
        set("Correo",NombrePropio);
        set("NombreUsuario",NombreUsuario);
        set("Pasword",Password);
        set("EsAdministrador",EsAdministrador);
    }
    public BaseModelUsuario(){}
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
