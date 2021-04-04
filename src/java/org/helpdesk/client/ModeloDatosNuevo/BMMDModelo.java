/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Administrador
 */
public class BMMDModelo extends BaseModelBasico {
    public BMMDModelo() {
        super();
        setCadena("Nombre","");
    }    
    public BMMDModelo(String Nombre){
        setCadena("Nombre",Nombre);
    }
    public void setNombre(String Nombre) {
        setCadena("Nombre",Nombre);
    }    
    public String getNombre() {        
        return getCadena("Nombre");
    }
}
