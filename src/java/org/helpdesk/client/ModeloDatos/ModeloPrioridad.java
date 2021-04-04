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
public class ModeloPrioridad implements IsSerializable{
    private int Id;
    private String Nombre;
    public ModeloPrioridad(){
        Id = 0;
        Nombre = new String();
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre.toUpperCase();
    }
}
