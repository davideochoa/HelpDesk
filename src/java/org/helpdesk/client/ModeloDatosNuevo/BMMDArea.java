/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Usuario
 */
public class BMMDArea extends BaseModelBasico{
    public BMMDArea() {
        super();
        setInt("Id",0);
        setCadena("Nombre","");
    }

    public BMMDArea(int Id, String Nombre) {
        setInt("Id",Id);
        setCadena("Nombre",Nombre);
    }
    
    public void setId(int Id) {
        setInt("Id",Id);
    }
    public void setNombre(String Nombre) {
        setCadena("Nombre",Nombre);
    }
    
    public int getId() {
        return getInt("Id");
    }    
    public String getNombre() {        
        return getCadena("Nombre");
    }
}
