/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 *
 * @author Administrador
 */
public class BaseModelUsuarioSoporte extends BaseModel{
    public BaseModelUsuarioSoporte(int Id,String NombrePropio,String Correo,String NombreUsuario,
                                    String Password,boolean EsAdministrador,boolean EsReseteadoPassword) {
        set("Id",Id);
        set("NombrePropio",NombrePropio.toUpperCase());
        set("Correo",Correo);
        setNombreUsuario(NombreUsuario);
        setPassword(Password);
        set("EsAdministrador",EsAdministrador);
        set("EsReseteadoPassword",EsReseteadoPassword);
    }
    public BaseModelUsuarioSoporte(){}
    
    public void setId(int Id) {
        set("Id",Id);
    }
    public void setNombrePropio(String NombrePropio) {
        set("NombrePropio",verficarNull(NombrePropio).toUpperCase());
    }
    public void setCorreo(String Correo){
        set("Correo",Correo);
    }
    public void setNombreUsuario(String NombreUsuario) {
        set("NombreUsuario",NombreUsuario);
    }
    public void setPassword(String Password){
        set("Password",Password);
    }
    public void setEsAdministrador(boolean EsAdministrador){
        set("EsAdministrador",EsAdministrador);
    }    
    public int getId() {
        Integer ValorEntero = (Integer)this.get("Id");
        if(ValorEntero!= null)
            return ValorEntero.intValue();
        else
            return 0;
    }
    public String getNombrePropio(){
        return (String)get("NombrePropio");
    }
    public String getCorreo(){
        return (String)get("Correo");
    }
    public String getNombreUsuario(){
        return (String)get("NombreUsuario");
    }
    public boolean getEsAdministrador(){
        Boolean valor = get("EsAdministrador");
        return valor.booleanValue();
    }
    public void setEsReseteadoPassword(boolean EsReseteadoPassword){
        set("EsReseteadoPassword",EsReseteadoPassword);
    }
    public boolean getEsReseteadoPassword(){
        Boolean valor = get("EsReseteadoPassword");
        return valor.booleanValue();
    }
    private String verficarNull(String valor){        
        if(valor == null) {
            valor = "";
        }
        
        return valor;
    }
}
