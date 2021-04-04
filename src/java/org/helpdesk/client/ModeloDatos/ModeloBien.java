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
public class ModeloBien implements IsSerializable{
    private int Id;
    private int IdTipoIncidencia;
    private String Nombre;
    public ModeloBien(){
        Id = 0;
        IdTipoIncidencia = 0;
        Nombre = new String();
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }    
    public int getIdTipoIncidencia() {
        return IdTipoIncidencia;
    }
    public void setIdTipoIncidencia(int IdTipoIncidencia) {
        this.IdTipoIncidencia = IdTipoIncidencia;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre.toUpperCase();
    }
}
