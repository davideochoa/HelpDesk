/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatos;

import com.extjs.gxt.ui.client.data.BaseModel;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class BaseModelStatusGrid extends BaseModel{
    public BaseModelStatusGrid(String Estatus,String Anotacion,Date fecha,String NombrePropio){
        this.set("Estatus",Estatus);
        this.set("anotacion",Anotacion);
        this.set("fecha",fecha);
        this.set("nombre_propio",NombrePropio);
    }
}
