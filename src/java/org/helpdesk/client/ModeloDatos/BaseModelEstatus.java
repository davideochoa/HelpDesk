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
public class BaseModelEstatus extends BaseModel{
    public BaseModelEstatus(int Id,String Nombre,boolean Abierto,boolean Cerrado,boolean Reasignar,boolean DiagnostinoInicial,boolean DiagnostinoFinal ) {
        this.set("Id",Id);
        setNombre(Nombre);
        this.set("Abierto",Abierto);
        this.set("Cerrado",Cerrado);
        this.set("Reasignar",Reasignar);
        this.set("DiagnostinoInicial",DiagnostinoInicial);
        this.set("DiagnostinoFinal",DiagnostinoFinal);
    }
    public BaseModelEstatus(){}
    public String getNombre() {
        return (String)get("Nombre");
    }
    public void setNombre(String Nombre) {
        set("Nombre",verficarNull(Nombre).toUpperCase());
    }
    public boolean getAbierto() {
        Boolean Abierto = this.get("Abierto");
        return Abierto.booleanValue();
    }
    public boolean getCerrado() {
        Boolean Cerrado = this.get("Cerrado");
        return Cerrado.booleanValue();
    }
    public boolean getReasignar() {
        Boolean Reasignar = this.get("Reasignar");
        return Reasignar.booleanValue();
    }
    public int getId() {
        Integer Id = this.get("Id");
        return Id.intValue();
    }
    public boolean getDiagnostinoInicial() {
        Boolean DiagnostinoInicial = this.get("DiagnostinoInicial");
        return DiagnostinoInicial.booleanValue();
    }
    public boolean getDiagnostinoFinal() {
        Boolean DiagnostinoFinal = this.get("DiagnostinoFinal");
        return DiagnostinoFinal.booleanValue();
    }
    private String verficarNull(String valor){        
        if(valor == null) {
            valor = "";
        }
        
        return valor;
    }
}
