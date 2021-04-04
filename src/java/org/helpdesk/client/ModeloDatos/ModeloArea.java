/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author Administrador
 */
public class ModeloArea implements IsSerializable{
    private int Id;
    private int IdUnidad;
    private String Nombre;
    public ModeloArea(){
        Id = 0;
        IdUnidad = 0;
        Nombre = new String();
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }    
    public int getIdUnidad() {
        return IdUnidad;
    }
    public void setIdUnidad(int IdUnidad) {
        this.IdUnidad = IdUnidad;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre.toUpperCase();
    }
}
