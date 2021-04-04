/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author Administrador
 */
public class ModeloEstadoDiagnostico implements IsSerializable{
    private boolean DiagnostinoInicial = false;
    private boolean DiagnostinoFinal = false;
    public ModeloEstadoDiagnostico(){
        this.setDiagnostinoInicial(false);
        this.setDiagnostinoFinal(false);
    }
     public void setDiagnostinoInicial(boolean DiagnostinoInicial) {
        this.DiagnostinoInicial = DiagnostinoInicial;
    }
    public void setDiagnostinoFinal(boolean DiagnostinoFinal) {
        this.DiagnostinoFinal = DiagnostinoFinal;
    }
    public boolean getDiagnostinoInicial() {
        return DiagnostinoInicial;
    }
    public boolean getDiagnostinoFinal(){
        return DiagnostinoFinal;
    }
}
