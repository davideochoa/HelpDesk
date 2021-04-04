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
public class BaseModelArea extends BaseModel{
    public BaseModelArea(int Id,int IdUnidad,String Nombre) {
        set("Id",Id);
        set("IdUnidad",IdUnidad);
        set("Nombre",Nombre.toUpperCase());
    }
    public BaseModelArea(){}
    public int getId() {
        return ((Integer)this.get("Id")).intValue();
    }
    public void setId(int Id) {
        set("Id",Id);
    }
    public int getIdUnidad() {
        return ((Integer)this.get("IdUnidad")).intValue();
    }
    public void setIdUnidad(int Id) {
        set("IdUnidad",Id);
    }
    public String getNombre() {
        return (String)get("Nombre");
    }
    public void setNombre(String Nombre) {
        set("Nombre",verficarNull(Nombre).toUpperCase());
    }
    public ModeloArea getModelo(){
        ModeloArea modelo = new ModeloArea();

        modelo.setId(this.getId());
        modelo.setIdUnidad(this.getIdUnidad());
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
