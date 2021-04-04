/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Administrador
 */
public class BMMDMarca extends BaseModelBasico {
    public BMMDMarca() {
        super();
        setCadena("Nombre","");
    }    
    public BMMDMarca(String Nombre){
        setCadena("Nombre",Nombre);
    }
    public void setNombre(String Nombre) {
        setCadena("Nombre",Nombre);
    }    
    public String getNombre() {        
        return getCadena("Nombre");
    }
}
