/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.extjs.gxt.ui.client.data.BaseModel;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class BaseModelVistaGridFolios extends BaseModel{
    public BaseModelVistaGridFolios(int Folio,boolean Activo,
                        String Unidad,String Area,String UsuarioReporta,String TipoIncidencia,
                        String Bien,String Marca,String Modelo,String NumeroSerie,
                        String NumeroInventario,String Motivo,String Prioridad,String Estatus,String Anotacion,
                        String UsuarioAsignado,Date Fecha){
        set("Folio",Folio);
        set("Activo",Activo);
        set("Unidad",Unidad.toUpperCase());
        set("Area",Area.toUpperCase());
        set("UsuarioReporta",UsuarioReporta.toUpperCase());
        set("TipoIncidencia",TipoIncidencia.toUpperCase());
        set("Bien",Bien.toUpperCase());
        set("Marca",Marca.toUpperCase());
        set("Modelo",Modelo.toUpperCase());
        set("NumeroSerie",NumeroSerie.toUpperCase());
        set("NumeroInventario",NumeroInventario.toUpperCase());
        set("Motivo",Motivo.toUpperCase());
        set("Prioridad",Prioridad.toUpperCase());
        set("Estatus",Estatus.toUpperCase());
        set("Anotacion",Anotacion.toUpperCase());
        set("UsuarioAsignado",UsuarioAsignado.toUpperCase());
        set("Fecha",Fecha);
        set("Estatus",Estatus);
    }
    public BaseModelVistaGridFolios(String[] datos){
        set("Folio",Integer.parseInt(datos[0]));
        set("Activo",Boolean.parseBoolean(datos[1]));
        set("Unidad",datos[2].toUpperCase());
        set("Area",datos[3].toUpperCase());
        set("UsuarioReporta",datos[4].toUpperCase());
        set("TipoIncidencia",datos[5].toUpperCase());
        set("Bien",datos[6].toUpperCase());
        set("Marca",datos[7].toUpperCase());
        set("Modelo",datos[8].toUpperCase());
        set("NumeroSerie",datos[9].toUpperCase());
        set("NumeroInventario",datos[10].toUpperCase());
        set("Motivo",datos[11].toUpperCase());
        set("Prioridad",datos[12].toUpperCase());
        set("Anotacion",datos[13].toUpperCase());
        set("UsuarioAsignado",datos[14].toUpperCase());
        set("Fecha",new Date(Timestamp.valueOf(datos[15]).getTime()));
        set("Estatus",datos[16].toUpperCase());
    }
    public int getFolio(){
        return ((Integer)this.get("Folio")).intValue();
    }
    public boolean getActivo(){
        return ((Boolean)this.get("Activo")).booleanValue();
    }
    public String getUnidad(){
        return this.get("Unidad");
    }
    public String getArea(){
        return this.get("Area");
    }
    public String getUsuarioReporta(){
        return this.get("UsuarioReporta");
    }
    public String getTipoIncidencia(){
        return this.get("TipoIncidencia");
    }
    public String getBien(){
        return this.get("Bien");
    }
    public String getMarca(){
        return this.get("Marca");
    }
    public String getModelo(){
        return this.get("Modelo");
    }
    public String getNumeroSerie(){
        return this.get("NumeroSerie");
    }
    public String getNumeroInventario(){
        return this.get("NumeroInventario");
    }
    public String getMotivo(){
        return this.get("Motivo");
    }
    public String getPrioridad(){
        return this.get("Prioridad");
    }
    public String getUsuarioAsignado(){
        return this.get("UsuarioAsignado");
    }
    public Date getFecha(){
        return ((Date)this.get("Fecha"));
    }
}
