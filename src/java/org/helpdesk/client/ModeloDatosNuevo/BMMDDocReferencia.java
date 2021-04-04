/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Administrador
 */
public class BMMDDocReferencia extends BaseModelBasico {
    public BMMDDocReferencia() {
        super();
        setCadena("Nombre","");
    }    
    public BMMDDocReferencia(String Nombre){
        setCadena("Nombre",Nombre);
    }
    public void setNombre(String Nombre) {
        setCadena("Nombre",Nombre);
    }    
    public String getNombre() {        
        return getCadena("Nombre");
    }
}
