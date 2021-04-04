/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author Usuario
 */
public class BaseModelBasico extends BaseModel implements IsSerializable{
    public BaseModelBasico() {}   
    
    public void setBoolean(String Key,boolean Valor){
        set(Key,Valor);
    }
    
    public void setCadena(String Key,String Valor){
        set(Key,verficarString(Valor));
    }    
    public void setInt(String Key,int Valor){
        set(Key,Valor);
    }
    
    public int getInt(String Key) {
        return ((Integer)this.get(Key)).intValue();
    }    
    public String getCadena(String Key) {
        return ((String)this.get(Key));
    }
    
    private String verficarString(String valor){        
        if(valor == null) {
            valor = "";
        }
        valor = valor.toUpperCase();
        
        if(valor.contains("SELECT") || valor.contains("UPDATE") || valor.contains("DELETE") || valor.contains("INSERT")
                || valor.contains("CREATE") || valor.contains("ALTER") || valor.contains("DROP")){
            valor.replaceAll("SELECT","");
            valor.replaceAll("UPDATE","");
            valor.replaceAll("DELETE","");
            valor.replaceAll("INSERT","");
            
            valor.replaceAll("CREATE","");
            valor.replaceAll("ALTER","");
            valor.replaceAll("DROP","");
        }            
        
        return valor;
    }
}
