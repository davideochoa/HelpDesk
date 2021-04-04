/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Administrador
 */
public class BMMDUnidad extends BaseModelBasico {
    public BMMDUnidad() {
        super();
        setInt("Id",0);
        setCadena("Nombre","");
    }    
    public BMMDUnidad(int Id, String Nombre){
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
