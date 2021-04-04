package org.helpdesk.client.ModeloDatos;

import com.extjs.gxt.ui.client.data.BaseModel;

public class DataModelGridArchivos extends BaseModel{
    private static final long serialVersionUID = 1L;
    public DataModelGridArchivos(int Id,String Nombre){
        set("Id",Id);
        set("Nombre",Nombre.toUpperCase());
    }    
    public DataModelGridArchivos(){}
    
    public void setId(int Id){
        set("Id",Id);
    }
    public void setNombre(String Nombre){
        set("Nombre",verficarNull(Nombre).toUpperCase());
    }
    
    public int getId(){        
        return ((Integer)get("Id")).intValue();
    }
    public String getNombre(){
        return ((String)get("Nombre"));
    }
    private String verficarNull(String valor){        
        if(valor == null) {
            valor = "";
        }
        
        return valor;
    }
}
