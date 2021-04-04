/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class ModeloDatosUsuario implements IsSerializable{
    private int Id = 0;
    private String NombrePropio = new String();
    private String NombreUsuario = new String();
    private String Pasword = new String();
    private boolean Administrador = false;
    private boolean ReseteadoPassword = false;
    private String AreasSiAsignadas[][];
    private String AreasNoAsignadas[][];
    private Date fechaLogin = new Date();
    public ModeloDatosUsuario(){
        Id = 0;
        NombrePropio = new String();
        NombreUsuario = new String();
        Pasword = new String();
        Administrador = false;
        ReseteadoPassword = false;

        AreasSiAsignadas = new String[0][0];
        AreasNoAsignadas = new String[0][0];
        fechaLogin = new Date();
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getNombrePropio() {
        return NombrePropio;
    }
    public void setNombrePropio(String NombrePropio) {
        this.NombrePropio = NombrePropio.toUpperCase();
    }
    public String getNombreUsuario() {
        return NombreUsuario;
    }
    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
    public String getPasword() {
        return Pasword;
    }
    public void setPasword(String Pasword) {
        this.Pasword = Pasword;
    }
    public boolean isReseteadoPassword() {
        return ReseteadoPassword;
    }
    public void setReseteadoPassword(boolean ReseteadoPassword) {
        this.ReseteadoPassword = ReseteadoPassword;
    }
    public boolean isAdministrador() {
        return Administrador;
    }
    public void setAdministrador(boolean Administrador) {
        this.Administrador = Administrador;
    }
    public void setAreas(String[][] AreasSiAsignadas,String[][] AreasNoAsignadas){
        this.AreasSiAsignadas = AreasSiAsignadas;
        this.AreasNoAsignadas = AreasNoAsignadas;
    }
    public String[][] getAreasAsignadad(){
        return AreasSiAsignadas;
    }

    public Date getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }
    
    
}
