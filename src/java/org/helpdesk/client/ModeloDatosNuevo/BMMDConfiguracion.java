/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Administrador
 */
public class BMMDConfiguracion extends BaseModelBasico {
    public BMMDConfiguracion() {
        super();
        setInt("diasMinimoAmarillo",7);
        setInt("diasMaximoAmarillo",14);
        setCadena("etiquetaTipoBien","TipoBien");
    }    
    public BMMDConfiguracion(int diasMinimoAmarillo,int diasMaximoAmarillo, String etiquetaTipoBien){
        setInt("diasMinimoAmarillo",diasMinimoAmarillo);
        setInt("diasMaximoAmarillo",diasMaximoAmarillo);
        setCadena("etiquetaTipoBien",etiquetaTipoBien);
    }
    
    public void setdiasMinimoAmarillo(int setdiasMinimoAmarillo) {
        setInt("setdiasMinimoAmarillo",setdiasMinimoAmarillo);
    }
    public void setdiasMaximoAmarillo(int diasMaximoAmarillo) {
        setInt("diasMaximoAmarillo",diasMaximoAmarillo);
    }
    public void setetiquetaTipoBien(String etiquetaTipoBien) {
        setCadena("etiquetaTipoBien",etiquetaTipoBien);
    }
    
    public int getdiasMinimoAmarillo() {
        return getInt("diasMinimoAmarillo");
    }    
    public int getdiasMaximoAmarillo() {
        return getInt("diasMaximoAmarillo");
    } 
    public String getetiquetaTipoBien() {        
        return getCadena("etiquetaTipoBien");
    }
}
