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
public class BaseModelBien extends BaseModel{
    public BaseModelBien(int Id,int IdUnidad,String Nombre) {
        setId(Id);
        setIdTipoIncidencia(IdUnidad);
        setNombre(Nombre);
    }
    public BaseModelBien(){}
    public int getId() {
        return ((Integer)this.get("Id")).intValue();
    }
    public void setId(int Id) {
        set("Id",Id);
    }
    public int getIdTipoIncidencia() {
        return ((Integer)this.get("IdTipoIncidencia")).intValue();
    }
    public void setIdTipoIncidencia(int Id) {
        set("IdTipoIncidencia",Id);
    }
    public String getNombre() {
        return (String)get("Nombre");
    }
    public void setNombre(String Nombre) {
        set("Nombre",verficarNull(Nombre).toUpperCase());
    }
    public ModeloBien getModelo(){
        ModeloBien modelo = new ModeloBien();

        modelo.setId(this.getId());
        modelo.setIdTipoIncidencia(this.getIdTipoIncidencia());
        modelo.setNombre(this.getNombre());

        return modelo;
    }
    private String verficarNull(String valor){        
        if(valor == null) {
            valor = "";
        }
        
        return valor;
    }
}
