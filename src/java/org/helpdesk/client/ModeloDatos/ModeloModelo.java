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
public class ModeloModelo implements IsSerializable{
    private String Nombre;
    public ModeloModelo(){
        Nombre = new String();
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre.toUpperCase();
    }
}
