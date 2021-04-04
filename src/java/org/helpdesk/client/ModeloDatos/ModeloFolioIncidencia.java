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
public class ModeloFolioIncidencia implements IsSerializable{
    private int Folio = 0;
    private int IdUnidad = 0;
    private String Unidad = "";
    private int IdArea = 0;
    private String Area = "";
    private String UsuarioReporta = "";
    private String TelefonoContacto = "";
    private String ReferenciaDocumental = "";
    private int IdTipoIncidencia = 0;
    private String Incidencia = "";
    private int IdBien = 0;
    private String Bien = "";
    private String Marca = "";
    private String Modelo = "";
    private String NumeroSerie = "";
    private String NumeroInventario = "";
    private String MotivoReporte = "";
    private int IdPrioridad = 0;
    private String Prioridad = "";
    private int IdUsuarioSoporteAsignado = 0;
    private String UsuarioSoporteAsignado = "";
    private Date Fecha = new Date();
    boolean Activo = false;
    int IdTipoIncidenciaFinal = 0;
    private String[][] datosEstatus = new String[0][0];
    public ModeloFolioIncidencia(){
        Folio = 0;
        IdUnidad = 0;
        Unidad = "";
        IdArea = 0;
        Area = "";
        UsuarioReporta = "";
        TelefonoContacto = "";
        ReferenciaDocumental = "";
        IdTipoIncidencia = 0;
        Incidencia = "";
        IdBien = 0;
        Bien = "";
        Marca = "";
        Modelo = "";
        NumeroSerie = "";
        NumeroInventario = "";
        MotivoReporte = "";
        IdPrioridad = 0;
        Prioridad = "";
        IdUsuarioSoporteAsignado = 0;
        UsuarioSoporteAsignado = "";
        Fecha = new Date();
        Activo = false;
        datosEstatus = new String[0][0];
    }
    public String getArea() {
        return Area;
    }
    public void setArea(String Area) {        
        this.Area = verficarNull(Area).toUpperCase();
    }
    public String getBien() {
        return Bien;
    }
    public void setBien(String Bien) {
        this.Bien = verficarNull(Bien).toUpperCase();
    }
    public String getIncidencia() {
        return Incidencia;
    }
    public void setIncidencia(String Incidencia) {
        this.Incidencia = verficarNull(Incidencia).toUpperCase();
    }
    public String getPrioridad() {
        return Prioridad;
    }
    public void setPrioridad(String Prioridad) {
        this.Prioridad = verficarNull(Prioridad).toUpperCase();
    }
    public String getUnidad() {
        return Unidad;
    }
    public void setUnidad(String Unidad) {
        this.Unidad = verficarNull(Unidad).toUpperCase();
    }
    public String getUsuarioSoporteAsignado() {
        return UsuarioSoporteAsignado;
    }
    public void setUsuarioSoporteAsignado(String UsuarioSoporteAsignado) {
        this.UsuarioSoporteAsignado = verficarNull(UsuarioSoporteAsignado).toUpperCase();
    }
    public int getFolio() {
        return Folio;
    }
    public void setFolio(int Folio) {
        this.Folio = Folio;
    }
    public int getIdArea() {
        return IdArea;
    }
    public void setIdArea(int IdArea) {
        this.IdArea = IdArea;
    }
    public int getIdBien() {
        return IdBien;
    }
    public void setIdBien(int IdBien) {
        this.IdBien = IdBien;
    }
    public int getIdPrioridad() {
        return IdPrioridad;
    }
    public void setIdPrioridad(int IdPrioridad) {
        this.IdPrioridad = IdPrioridad;
    }
    public int getIdTipoIncidencia() {
        return IdTipoIncidencia;
    }
    public void setIdTipoIncidencia(int IdTipoIncidencia) {
        this.IdTipoIncidencia = IdTipoIncidencia;
    }
    public int getIdUnidad() {
        return IdUnidad;
    }
    public void setIdUnidad(int IdUnidad) {
        this.IdUnidad = IdUnidad;
    }
    public int getIdUsuarioSoporteAsignado() {
        return IdUsuarioSoporteAsignado;
    }
    public void setIdUsuarioSoporteAsignado(int IdUsuarioSoporteAsignado) {
        this.IdUsuarioSoporteAsignado = IdUsuarioSoporteAsignado;
    }
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String Marca) {
        this.Marca = verficarNull(Marca).toUpperCase();
    }
    public String getModelo() {
        return Modelo;
    }
    public void setModelo(String Modelo) {
        this.Modelo = verficarNull(Modelo).toUpperCase();
    }
    public String getMotivoReporte() {
        return MotivoReporte;
    }
    public void setMotivoReporte(String MotivoReporte) {
        this.MotivoReporte = verficarNull(MotivoReporte).toUpperCase();
    }
    public String getNumeroInventario() {
        return NumeroInventario;
    }
    public void setNumeroInventario(String NumeroInventario) {
        this.NumeroInventario = verficarNull(NumeroInventario).toUpperCase();
    }
    public String getNumeroSerie() {
        return NumeroSerie;
    }
    public void setNumeroSerie(String NumeroSerie) {
        this.NumeroSerie = verficarNull(NumeroSerie).toUpperCase();
    }
    public String getUsuarioReporta() {
        return UsuarioReporta;
    }
    public void setUsuarioReporta(String UsuarioReporta) {
        this.UsuarioReporta = verficarNull(UsuarioReporta).toUpperCase();
    }
    public Date getFecha() {
        return Fecha;
    }
    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    public boolean getActivo() {
        return Activo;
    }
    public void setActivo(boolean EsCerrado) {
        this.Activo = EsCerrado;
    }
    public String[][] getDatosEstatus() {
        return datosEstatus;
    }
    public void setDatosEstatus(String[][] datosEstatus) {
        this.datosEstatus = datosEstatus;
    }
    public int getIdTipoIncidenciaFinal() {
        return IdTipoIncidenciaFinal;
    }
    public void setIdTipoIncidenciaFinal(int IdTipoIncidenciaFinal) {
        this.IdTipoIncidenciaFinal = IdTipoIncidenciaFinal;
    }
    public String getReferenciaDocumental() {
        return ReferenciaDocumental;
    }
    public void setReferenciaDocumental(String ReferenciaDocumental) {
        this.ReferenciaDocumental = verficarNull(ReferenciaDocumental).toUpperCase();
    }
    public String getTelefonoContacto() {
        return TelefonoContacto;
    }
    public void setTelefonoContacto(String TelefonoContacto) {
        this.TelefonoContacto = verficarNull(TelefonoContacto).toUpperCase();
    }
    private String verficarNull(String valor){        
        if(valor == null) {
            valor = "";
        }
        
        return valor;
    }
}
